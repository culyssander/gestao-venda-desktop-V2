package com.dxc.gestao.venda.controlador;

import com.dxc.gestao.venda.modelo.dto.EstoqueDto;
import com.dxc.gestao.venda.modelo.dto.HistoricoEstoqueDto;
import com.dxc.gestao.venda.modelo.entidade.Estoque;
import com.dxc.gestao.venda.modelo.entidade.EstoqueHistorico;
import com.dxc.gestao.venda.modelo.entidade.EstoqueTipo;
import com.dxc.gestao.venda.modelo.entidade.Produto;
import com.dxc.gestao.venda.modelo.servico.EstoqueHistoricoServico;
import com.dxc.gestao.venda.modelo.servico.EstoqueServico;
import com.dxc.gestao.venda.modelo.servico.ProdutoServico;
import com.dxc.gestao.venda.modelo.tabela.modelo.EstoqueHistoricoModelo;
import com.dxc.gestao.venda.modelo.tabela.modelo.EstoqueModelo;
import com.dxc.gestao.venda.visao.componentes.Mensagem;
import com.dxc.gestao.venda.visao.formulario.FormularioEstoque;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

public class FormularioEstoqueControlador implements ActionListener, KeyListener{
    
    private final FormularioEstoque formularioEstoque;
    private final EstoqueServico estoqueServico;
    private final ProdutoServico produtoServico;
    private final EstoqueHistoricoServico estoqueHistoricoServico;
    private EstoqueModelo estoqueModelo;
    private static List<EstoqueDto> estoques;
    private static Long estoqueId;
    private static Optional<Produto> produto;;
    

    public FormularioEstoqueControlador(FormularioEstoque formularioEstoque) {
        this.formularioEstoque = formularioEstoque;
        estoqueServico = new EstoqueServico();
        produtoServico = new ProdutoServico();
        estoqueHistoricoServico = new EstoqueHistoricoServico();
        
        atualizaTabela();
    }
    
    private void atualizaTabela() {
        estoques = estoqueServico.encontrarTodos();
        estoqueModelo = new EstoqueModelo(estoques);
        formularioEstoque.getTabela().setModel(estoqueModelo);
    }
    
    private void atualizaTabelaHistorico() {
        List<HistoricoEstoqueDto> estoqueHistoricos = estoqueHistoricoServico.encontraTodos();
        EstoqueHistoricoModelo modelo = new EstoqueHistoricoModelo(estoqueHistoricos);
        formularioEstoque.getFormularioPrincipal().getTabela().setModel(modelo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand().toLowerCase();
        
        switch(action) {
            case "adicionar" -> { adicionar(); } 
            case "salvar" -> { salvar(); }
        }        
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        String produtoIdOrNome = formularioEstoque.getCampoDeTextoNome().getText().trim();
        try {
            long produtoId = Long.valueOf(produtoIdOrNome);
            produto = produtoServico.encontrarPeloId(produtoId);
        } catch (Exception ex) {
            produto = Optional.ofNullable(produtoServico.encontraPeloNome(produtoIdOrNome));
        }
        if (produto.isPresent()) {
            formularioEstoque.getLabelProduto().setText(produto.get().getNome());
        } else {
            System.out.println("Vazio");
            formularioEstoque.getLabelProduto().setText("");
        }
        
        System.out.println(produto);
    }

    private void adicionar() {
//        if (estoqueId == null) {
//            formularioEstoque.getCampoDeTextoObservacao().setVisible(false);
//        }
        formularioEstoque.getDialog().pack();
        formularioEstoque.getDialog().setLocationRelativeTo(null);
        formularioEstoque.getDialog().setVisible(true);
    }
    
    private void validaCampoVazio(String texto) {
        if (texto.isBlank()) {
            formularioEstoque.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, "Campo obrigatorio");
        }
    }

    private void salvar() {
        String produtoIdOrNome = formularioEstoque.getCampoDeTextoNome().getText().trim();
        String quantidadeString = formularioEstoque.getCampoDeTextoQuantidade().getText().trim();
        String observacao = formularioEstoque.getCampoDeTextoObservacao().getText().trim();

        validaCampoVazio(produtoIdOrNome);
        validaCampoVazio(quantidadeString);
        
        if (produto.isEmpty()) {
            formularioEstoque.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, "Produto nao encontrado");
            throw new RuntimeException("Produto nao encontrado");
        }
        
        Integer quantidade = 0;
        Estoque estoque;
        EstoqueHistorico estoqueHistorico = new EstoqueHistorico();
        
        try {
            quantidade = Integer.valueOf(quantidadeString);
            
            if (quantidade < 0) {
                String mensagem = "Não é permitido numeros negativos";
                formularioEstoque.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, mensagem);
                throw new RuntimeException(mensagem);
            } 
            
            if (estoqueId == null) {
                estoque = estoqueServico.encontrarPeloAtributoProdutoId(produto.get().getId());
                if (estoque == null) {
                    estoque = new Estoque();
                }
                estoqueHistorico.setQuantidade(quantidade);
                quantidade = estoque.getQuantidade() == null ? quantidade : estoque.getQuantidade() + quantidade;
                estoque.setQuantidade(quantidade);
                estoqueHistorico.setObservacao("adiciona: " + observacao);
            } else {
                validaCampoVazio(observacao);
                estoqueHistorico.setQuantidade(quantidade);
                estoque = estoqueServico.encontrarPeloAtributoProdutoId(produto.get().getId());
                estoqueHistorico.setObservacao("ALTERAR: " + observacao);
                int confirma = JOptionPane.showConfirmDialog(null, """
                                                                   Tens certeza que desejas atualizar? \n
                                                                   OBS: Na actualizacao o valor da quantidade sera substituido pelo atual""", 
                        "Atualizar o estoque", JOptionPane.YES_NO_OPTION);
                
                if (confirma == JOptionPane.YES_OPTION)
                    estoque.setQuantidade(quantidade);
                else
                    throw new RuntimeException("Nao atualizar estoque");
            }
            
//            estoque.setId(estoqueId);
            estoque.setEstado(formularioEstoque.getRadioAtivo().isSelected());
            estoque.setProdutoId(produto.get().getId());
            estoque.setUsuarioId(formularioEstoque.getUsuarioId());
            estoque.setDataCriacao(LocalDateTime.now());
            
            String mensagem = estoqueServico.salvar(estoque);
            
            if (mensagem.startsWith("Estoque registrado")) {
                
                estoqueHistorico.setProdutoId(estoque.getProdutoId());
                estoqueHistorico.setTipo(EstoqueTipo.ENTRADA.name());
                estoqueHistorico.setDataCriacao(estoque.getDataCriacao());
                estoqueHistorico.setUsuarioId(estoque.getUsuarioId());
                estoqueHistoricoServico.salvar(estoqueHistorico);
                
                formularioEstoque.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.SUCESSO, mensagem);
                limpo();
                atualizaTabela();
                atualizaTabelaHistorico();
            } else {
                formularioEstoque.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, mensagem);
            }
        } catch (Exception e) {
            formularioEstoque.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    private void limpo() {
        produto = Optional.empty();
        estoqueId = null;
        formularioEstoque.getCampoDeTextoNome().setText("");
        formularioEstoque.getCampoDeTextoQuantidade().setText("");
        formularioEstoque.getCampoDeTextoObservacao().setText("");
    }
}
