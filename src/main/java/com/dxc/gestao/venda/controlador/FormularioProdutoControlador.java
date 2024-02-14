package com.dxc.gestao.venda.controlador;

import com.dxc.gestao.venda.modelo.dto.ProdutoDto;
import com.dxc.gestao.venda.modelo.entidade.Produto;
import com.dxc.gestao.venda.modelo.servico.ProdutoServico;
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
    private ProdutoModelo produtoModelo;
    private List<ProdutoDto> produtos;
    

    public FormularioProdutoControlador(FormularioProduto formularioProduto) {
        this.formularioProduto = formularioProduto;
        produtoServico = new ProdutoServico();
        atualizaTabela();
    }
    
    private void atualizaTabela() {
        produtos = produtoServico.encontrarTodos();
        produtoModelo = new ProdutoModelo(produtos);
        formularioProduto.getTabelaProduto().setModel(produtoModelo);
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
