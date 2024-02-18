package com.dxc.gestao.venda.controlador;

import com.dxc.gestao.venda.modelo.dto.ProdutoDto;
import com.dxc.gestao.venda.modelo.entidade.Categoria;
import com.dxc.gestao.venda.modelo.entidade.Produto;
import com.dxc.gestao.venda.modelo.servico.CategoriaServico;
import com.dxc.gestao.venda.modelo.servico.ProdutoServico;
import com.dxc.gestao.venda.modelo.tabela.modelo.CategoriaModelo;
import com.dxc.gestao.venda.modelo.tabela.modelo.ProdutoModelo;
import com.dxc.gestao.venda.visao.componentes.Mensagem;
import com.dxc.gestao.venda.visao.formulario.FormularioProduto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.List;

public class FormularioProdutoControlador implements ActionListener, KeyListener, MouseListener {
    
    private final FormularioProduto formularioProduto;
    private final ProdutoServico produtoServico;
    private final CategoriaServico categoriaServico;
    private ProdutoModelo produtoModelo;
    private CategoriaModelo categoriaModelo;
    private List<ProdutoDto> produtos;
    private List<Categoria> categorias;
    
    private Long categoriaId;
    

    public FormularioProdutoControlador(FormularioProduto formularioProduto) {
        this.formularioProduto = formularioProduto;
        produtoServico = new ProdutoServico();
        categoriaServico = new CategoriaServico();
        categorias = categoriaServico.encontrarTodos();
        categoriaModelo = new CategoriaModelo(categorias);
        atualizaTabela();
    }
    
    private void atualizaTabela() {
        produtos = produtoServico.encontrarTodos();
        produtoModelo = new ProdutoModelo(produtos);
        formularioProduto.getTabelaProduto().setModel(produtoModelo);
    }
    
    private void atualizaTabelaCategoria() {
        categorias = categoriaServico.encontrarTodos();
        categoriaModelo = new CategoriaModelo(categorias);
        formularioProduto.getFormulario().getProdutoCategoria().getTabelaCategoria().setModel(categoriaModelo);
        
        formularioProduto.getFormulario()
                    .getProdutoCategoria()
                    .getComboBoxCategoriaProduto()
                    .removeAllItems();
        
        formularioProduto.getFormulario()
                    .getProdutoCategoria()
                    .getComboBoxCategoriaProduto()
                    .addItem("Seleciona a categoria");
        
        categorias.forEach(c -> {
            formularioProduto.getFormulario()
                    .getProdutoCategoria()
                    .getComboBoxCategoriaProduto()
                    .addItem(c.getNome());
        });
    }
    
    private void validaCampo(String texto) {
        if (texto.isBlank()){
            String mensagem = "Todos os campos sao obrigatorio.";
            formularioProduto.getFormulario().mostrMensagem(Mensagem.TipoDeMensagem.ERRO, mensagem);
            throw new RuntimeException(mensagem);
        }
    }
    
    private void selecionaTabelaCategoria() {
        int index = formularioProduto.getFormulario().getProdutoCategoria().getTabelaCategoria().getSelectedRow();
        System.out.println("INDEX: " + index);
        if (index != -1) {
            Categoria categoria = categorias.get(index);
            System.out.println(categoria);
            categoriaId = categoria.getId();
            formularioProduto.getFormulario().getProdutoCategoria().getTextoNomeCategoria().setText(categoria.getNome());
            formularioProduto.getFormulario().getProdutoCategoria().getTextoDescricaoCategoria().setText(categoria.getDescricao());
        }
    }
    
    private void salvaCategoria() {
        formularioProduto.getFormulario().getProdutoCategoria().getBotaoCategoria().addActionListener(e -> {
            String nomeCategoria = formularioProduto.getFormulario().getProdutoCategoria().getTextoNomeCategoria().getText().trim();
            String descricaoCategoria = formularioProduto.getFormulario().getProdutoCategoria().getTextoDescricaoCategoria().getText().trim();

            validaCampo(nomeCategoria);

            Categoria categoria = Categoria.builder()
                    .id(categoriaId)
                    .nome(nomeCategoria)
                    .descricao(descricaoCategoria)
                    .build();

            String mensagem = categoriaServico.salva(categoria);
            formularioProduto.getFormulario().mostrMensagem(Mensagem.TipoDeMensagem.SUCESSO, mensagem);

            atualizaTabelaCategoria();
            limpaCampoCategoria();
        });
    }
    
    private void limpaCampoCategoria() {
        categoriaId = null;
        formularioProduto.getFormulario().getProdutoCategoria().getTextoNomeCategoria().setText("");
        formularioProduto.getFormulario().getProdutoCategoria().getTextoDescricaoCategoria().setText("");
    }
    
    private void salvaProduto() {
        formularioProduto.getFormulario().getProdutoCategoria().getBotaoProduto().addActionListener(e -> {
            String nome = formularioProduto.getFormulario().getProdutoCategoria().getTextoNomeProduto().getText().trim();
            String descricao = formularioProduto.getFormulario().getProdutoCategoria().getTextoDescricaoProduto().getText().trim();
            String precoString = formularioProduto.getFormulario().getProdutoCategoria().getTextoPrecoProduto().getText().trim();
            String categoriaNome  = formularioProduto.getFormulario().getProdutoCategoria().getComboBoxCategoriaProduto().getSelectedItem().toString();
            
            validaCampo(nome);
            validaCampo(precoString);
            validaCategoriaSelecionada();
            
            BigDecimal preco = converterStringParaDecimal(precoString);
            Categoria categoria = categoriaServico.encontrarPeloNome(categoriaNome);
            
            if (categoria == null) {
                String mensagem = "Categoria nao encontrada...";
                formularioProduto.getFormulario().mostrMensagem(Mensagem.TipoDeMensagem.ERRO, mensagem);
                throw new RuntimeException(mensagem);
            }
            
            Produto produto = Produto.builder()
                    .nome(nome)
                    .descricao(descricao)
                    .preco(preco)
                    .categoriaId(categoria.getId())
                    .usuarioId(formularioProduto.getUsuarioId())
                    .build();
            
            String mensagem = produtoServico.salva(produto);
            
            if (mensagem.startsWith("Produto cadastrado")) {
                  formularioProduto.getFormulario().mostrMensagem(Mensagem.TipoDeMensagem.SUCESSO, mensagem);
                  limpaCampoProduto();
                  atualizaTabela();
            } else {
                  formularioProduto.getFormulario().mostrMensagem(Mensagem.TipoDeMensagem.ERRO, mensagem);
            }
            
        });
    }
    
    private void limpaCampoProduto() {
        formularioProduto.getFormulario().getProdutoCategoria().getTextoNomeProduto().setText("");
        formularioProduto.getFormulario().getProdutoCategoria().getTextoDescricaoProduto().setText("");
        formularioProduto.getFormulario().getProdutoCategoria().getTextoPrecoProduto().setText("");
        formularioProduto.getFormulario().getProdutoCategoria().getComboBoxCategoriaProduto().setSelectedIndex(0);
    }

    private void validaCategoriaSelecionada() {
        if (formularioProduto.getFormulario().getProdutoCategoria().getComboBoxCategoriaProduto().getSelectedIndex() < 1) {
            String mensagem = "Categoria é um campo obrigatorio...";
            formularioProduto.getFormulario().mostrMensagem(Mensagem.TipoDeMensagem.ERRO, mensagem);
            throw new RuntimeException(mensagem);
        }
    }
       
    private BigDecimal converterStringParaDecimal(String texto) {
        try {
            return new BigDecimal(texto);
        } catch (Exception e) {
            String mensagem = "Deves preencher somente com numeros...";
            throw new RuntimeException(mensagem);
        }
    }

    public CategoriaModelo getCategoriaModelo() {
        return categoriaModelo;
    }
    
    private void botaoReset() {
        formularioProduto.getFormulario().getProdutoCategoria().getBotaoResetCategoria().addActionListener(e -> {
            limpaCampoCategoria();
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand().toLowerCase();
        System.out.println(action);
        
        switch(action) {
            case "adicionar" -> {adicionar();}
            case "atualizar" -> {}
            case "remover" -> {}
            case "imprimir" -> {}
        }
        
        salvaCategoria();
        salvaProduto();
        botaoReset();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    private void adicionar() {
        formularioProduto.getDashboard().setForm(formularioProduto.getFormulario());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        selecionaTabelaCategoria();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
