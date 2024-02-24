package com.dxc.gestao.venda.controlador;

import com.dxc.gestao.venda.modelo.dto.VendaItemDto;
import com.dxc.gestao.venda.modelo.entidade.Estoque;
import com.dxc.gestao.venda.modelo.entidade.Produto;
import com.dxc.gestao.venda.modelo.entidade.VendaItem;
import com.dxc.gestao.venda.modelo.servico.CategoriaServico;
import com.dxc.gestao.venda.modelo.servico.EstoqueServico;
import com.dxc.gestao.venda.modelo.servico.ProdutoServico;
import com.dxc.gestao.venda.modelo.tabela.modelo.VendaItemModelo;
import com.dxc.gestao.venda.visao.componentes.Mensagem;
import com.dxc.gestao.venda.visao.formulario.FormularioVenda;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FormularioVendaControlador implements ActionListener, KeyListener {
    
    private final FormularioVenda formularioVenda;
    private final EstoqueServico estoqueServico;
    private final ProdutoServico produtoServico;
    private final CategoriaServico categoriaServico;
    private VendaItemModelo vendaItemModelo;
    private static Estoque estoque;
    private static Optional<Produto> produto;
    private static Map<String, VendaItemDto> vendaItemDtosCarrinho;

    public FormularioVendaControlador(FormularioVenda formularioVenda) {
        this.formularioVenda = formularioVenda;
        estoqueServico = new EstoqueServico();
        produtoServico = new ProdutoServico();
        categoriaServico = new CategoriaServico();
        vendaItemDtosCarrinho = new HashMap<>();
        preencherComboBoxCategoria();
    }
    
    private void adicionar() {
        formularioVenda.getDialog().pack();
        formularioVenda.getDialog().setLocationRelativeTo(null);
        formularioVenda.getDialog().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand().toLowerCase();
        System.out.println("ACTION: " + action);
        switch(action) {
            case "adicionar" -> {adicionar();}
            case "comboboxproduto" -> { selecionaProdutoNoComboBox();}
            case "comboboxcategoria" -> { preencherComboBoxProduto();}
            case "carrinho" -> { adicionaNoCarrinho();}
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        
        if (formularioVenda.getMenuSelectionadoIndex() == 4) {
            
        }

        String produtoIdString = formularioVenda.getCampoDeTextoBuscarPeloId().getText().trim();
        if (!produtoIdString.isBlank()) {
            try {
                Long produtoId = Long.valueOf(produtoIdString);
                produto = buscarProdutoPeloId(produtoId);
                mostrarValoresNoQuadro(produto);
            } catch (Exception ex) {
                formularioVenda.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, "Erro na busca do produto");
            }
        }
        
        String quantidadeString = formularioVenda.getCampoDeTextoQuantidade().getText().trim();
        
        try {
            if (!quantidadeString.isBlank()) {
                Integer.valueOf(quantidadeString);
            }
        } catch (NumberFormatException ex) {
            formularioVenda.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, "Quantidade invalida");
            throw new RuntimeException(ex);
        }
        
        String descontoString = formularioVenda.getCampoDeTextoDesconto().getText().trim();
        
        try {
            if (!descontoString.isBlank()) {
                Double.valueOf(descontoString);
            }
        } catch (NumberFormatException ex) {
            formularioVenda.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, "Desconto invalida");
            throw new RuntimeException(ex);
        }
        
    }
    
    private Optional<Produto> buscarProdutoPeloId(Long produtoId) {
        return produtoServico.encontrarPeloId(produtoId) ;
    }
    
    public void mostrarValoresNoQuadro(Optional<Produto> produto) {
        if (produto.isPresent()) {
            estoque = estoqueServico.encontrarPeloAtributoProdutoId(produto.get().getId());
            if (estoque == null) {
                estoque = Estoque.builder()
                        .quantidade(0)
                        .estado(false)
                        .build();
            }
            mostrarDetalhesDaBuscaDoProduto(produto.get());
        } else {
            formularioVenda.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, "Produto nao encontrado");
            limpaDetalhesDaBuscaDoProduto();
        }
    }
    
    private void mostrarDetalhesDaBuscaDoProduto(Produto produto) {
        formularioVenda.getLabelProdutoNome().setText(produto.getNome());
        formularioVenda.getLabelEstoqueQuantidade().setText(estoque.getQuantidade().toString());
        formularioVenda.getLabelProdutoPreco().setText(produto.getPreco().toString());
    }
    
    private void limpaDetalhesDaBuscaDoProduto() {
        formularioVenda.getLabelProdutoNome().setText("Nome do produto:");
        formularioVenda.getLabelEstoqueQuantidade().setText("0");
        formularioVenda.getLabelProdutoPreco().setText("0.00");
    }
    
    private void preencherComboBoxCategoria() {
        formularioVenda.getComboBoxCategoria().removeAllItems();
        formularioVenda.getComboBoxCategoria().addItem("Seleciona a categoria");
        
        removerItemDoComboProduto();
        
        categoriaServico.encontrarTodos()
                .stream()
                .forEach(c -> formularioVenda.getComboBoxCategoria().addItem(c.getNome()));
    }
    
    public void preencherComboBoxProduto() {
        removerItemDoComboProduto();
        String categoria = formularioVenda.getComboBoxCategoria().getSelectedItem().toString();
        List<Produto> produtos = produtoServico.encontraTodosPelaCategoriaNome(categoria);

        produtos.stream()
                .forEach(p
                        -> formularioVenda.getComboBoxProduto().addItem(p.getNome()));
    }

    
    private void removerItemDoComboProduto() {
        formularioVenda.getComboBoxProduto().removeAllItems();
        formularioVenda.getComboBoxProduto().addItem("Seleciona o produto");
    }
    
    private void selecionaProdutoNoComboBox() {
        if (formularioVenda.getComboBoxCategoria().getSelectedIndex() > 0
                && formularioVenda.getComboBoxProduto().getSelectedIndex() > 0) {
            String produtoNome = formularioVenda.getComboBoxProduto().getSelectedItem().toString();
            produto = Optional.ofNullable(produtoServico.encontraPeloNome(produtoNome));
            mostrarValoresNoQuadro(produto);
        } else {
            limpaDetalhesDaBuscaDoProduto();
        }
    }

    private void adicionaNoCarrinho() {
        if (estoque.getEstado() && estoque.getQuantidade() > 0) {
            int quantidadeExiste = 0;
            BigDecimal desconto = BigDecimal.ZERO;
            BigDecimal preco = BigDecimal.ZERO;
            BigDecimal total = BigDecimal.ZERO;
            
            String quantidadeString = formularioVenda.getCampoDeTextoQuantidade().getText().trim();
            String descontoString = formularioVenda.getCampoDeTextoDesconto().getText().trim();
            validaCampo(quantidadeString);
            
            if (produto.isPresent()) {
                Produto produtoAtual = produto.get();
                
                if (vendaItemDtosCarrinho.containsKey(produtoAtual.getNome())) {
                    quantidadeExiste = vendaItemDtosCarrinho.get(produtoAtual.getNome()).getQuantidade();
                }
                
                int quantidade = Integer.valueOf(formularioVenda.getCampoDeTextoQuantidade().getText().trim());
                quantidade += quantidadeExiste;
            
                
            
                VendaItemDto vendaItemDto = VendaItemDto.builder()
                        .preco(produtoAtual.getPreco())
                        .desconto(desconto)
                        .produtoNome(produtoAtual.getNome())
                        .total(total)
                        .quantidade(quantidade)
                        .build();
                
                vendaItemDto.setPreco(produto.get().getPreco());
                vendaItemDto.setProdutoNome(produto.get().getNome());
                vendaItemDto.setQuantidade(Integer.valueOf(quantidadeString));
                
                vendaItemDto.setTotal(produto.get().getPreco()
                        .multiply(BigDecimal.valueOf(vendaItemDto.getQuantidade())));
                
                System.out.println("Venda: " + vendaItemDto);
                
                vendaItemDto.setTotal(vendaItemDto.getPreco().subtract(vendaItemDto.getDesconto()));
                
                System.out.println("Venda1: " + vendaItemDto);
                
                vendaItemDtos.add(vendaItemDto);
                vendaItemModelo = new VendaItemModelo(vendaItemDtos);
                formularioVenda.getTabelaCarrinho().setModel(vendaItemModelo);
                System.out.println(vendaItemDtos);
            }
            
        } else {
            formularioVenda.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, "Produto nao no estoque ou bloqueado");
        }
    }
    
    private void validaCampo(String texto) {
        if (texto.isBlank()) {
            formularioVenda.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, "Campos obrigatorios");
            throw new RuntimeException();
        }
    }
}
