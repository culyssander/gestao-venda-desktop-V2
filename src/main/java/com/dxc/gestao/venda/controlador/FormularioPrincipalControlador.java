
package com.dxc.gestao.venda.controlador;

import com.dxc.gestao.venda.modelo.servico.EstoqueHistoricoServico;
import com.dxc.gestao.venda.modelo.servico.EstoqueServico;
import com.dxc.gestao.venda.modelo.servico.ProdutoServico;
import com.dxc.gestao.venda.modelo.tabela.modelo.EstoqueHistoricoModelo;
import com.dxc.gestao.venda.modelo.tabela.modelo.ProdutoModelo;
import com.dxc.gestao.venda.visao.formulario.FormularioPrincipal;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class FormularioPrincipalControlador {
    
    private final FormularioPrincipal formularioPrincipal;
    private final ProdutoServico produtoServico;
    private final ProdutoModelo produtoModelo;
    
    private final EstoqueServico estoqueServico;
    private final EstoqueHistoricoModelo estoqueHistoricoModelo;
    private final EstoqueHistoricoServico estoqueHistoricoServico;

    public FormularioPrincipalControlador(FormularioPrincipal formularioPrincipal) {
        this.formularioPrincipal = formularioPrincipal;
        produtoServico = new ProdutoServico();
        produtoModelo = new ProdutoModelo(produtoServico.encontrarTodos());
        estoqueServico = new EstoqueServico();
        estoqueHistoricoServico = new EstoqueHistoricoServico();
        estoqueHistoricoModelo = new EstoqueHistoricoModelo(estoqueHistoricoServico.encontraTodos());        
        actualiza();
        evento();
    }
    
    public void actualiza() {
        String totalProduto = String.format("Total %s", produtoServico.quantidadeDeProduto());
        String totalEstoque = String.format("Total %s", estoqueServico.quantidadeDeEstoque());
        this.formularioPrincipal.setTotalProduto(totalProduto);
        this.formularioPrincipal.setTotalEstoque(totalEstoque);
        this.formularioPrincipal.getTabela().setModel(estoqueHistoricoModelo);
        this.formularioPrincipal.setTotalVenda(totalProduto);
        
    }
    
    private void evento() {
        formularioPrincipal.getCartao1().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                formularioPrincipal.getLabelTitulo().setText("Tabela de produto");
                formularioPrincipal.getTabela().setModel(produtoModelo);
            }
            
        });
        
        formularioPrincipal.getCartao2().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                formularioPrincipal.getLabelTitulo().setText("Tabela da venda");
                formularioPrincipal.getTabela().setModel(produtoModelo);
            }
            
        });
        
        formularioPrincipal.getCartao3().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                formularioPrincipal.getLabelTitulo().setText("Historico do estoque");
                formularioPrincipal.getTabela().setModel(estoqueHistoricoModelo);
            }
            
        });
    }
    
}
