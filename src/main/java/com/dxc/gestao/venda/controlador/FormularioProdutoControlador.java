package com.dxc.gestao.venda.controlador;

import com.dxc.gestao.venda.modelo.dto.ProdutoDto;
import com.dxc.gestao.venda.modelo.entidade.Categoria;
import com.dxc.gestao.venda.modelo.entidade.Produto;
import com.dxc.gestao.venda.modelo.servico.CategoriaServico;
import com.dxc.gestao.venda.modelo.servico.ProdutoServico;
import com.dxc.gestao.venda.modelo.tabela.modelo.CategoriaModelo;
import com.dxc.gestao.venda.modelo.tabela.modelo.ProdutoModelo;
import com.dxc.gestao.venda.visao.formulario.Dashboard;
import com.dxc.gestao.venda.visao.formulario.FormularioProduto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class FormularioProdutoControlador implements ActionListener, KeyListener{
    
    private final FormularioProduto formularioProduto;
    private final ProdutoServico produtoServico;
    private final CategoriaServico categoriaServico;
    private ProdutoModelo produtoModelo;
    private CategoriaModelo categoriaModelo;
    private List<ProdutoDto> produtos;
    private List<Categoria> categorias;
    

    public FormularioProdutoControlador(FormularioProduto formularioProduto) {
        this.formularioProduto = formularioProduto;
        produtoServico = new ProdutoServico();
        categoriaServico = new CategoriaServico();
        atualizaTabela();
        atualizaTabelaCategoria();
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
    }
    
    private void validaCampo(String texto) {
        if (texto.isBlank())
            throw new RuntimeException("Todos os campos sao obrigatorio.");
    }
    
    private void salvaCategoria() {
        String nomeCategoria = formularioProduto.getFormulario().getProdutoCategoria().getTextoNomeCategoria().getText().trim();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand().toLowerCase();
        
        switch(action) {
            case "adicionar" -> {adicionar();}
            case "atualizar" -> {}
            case "remover" -> {}
            case "imprimir" -> {}
        }
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
    
}
