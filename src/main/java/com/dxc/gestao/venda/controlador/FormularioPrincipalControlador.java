
package com.dxc.gestao.venda.controlador;

import com.dxc.gestao.venda.modelo.dto.HistoricoEstoqueDto;
import com.dxc.gestao.venda.modelo.dto.VendaResponseDto;
import com.dxc.gestao.venda.modelo.entidade.EstoqueHistorico;
import com.dxc.gestao.venda.modelo.entidade.Perfil;
import com.dxc.gestao.venda.modelo.entidade.Usuario;
import com.dxc.gestao.venda.modelo.repositorio.impl.VendaRepositorioImpl;
import com.dxc.gestao.venda.modelo.servico.EstoqueHistoricoServico;
import com.dxc.gestao.venda.modelo.servico.EstoqueServico;
import com.dxc.gestao.venda.modelo.servico.ProdutoServico;
import com.dxc.gestao.venda.modelo.servico.UsuarioServico;
import com.dxc.gestao.venda.modelo.tabela.modelo.EstoqueHistoricoModelo;
import com.dxc.gestao.venda.modelo.tabela.modelo.ProdutoModelo;
import com.dxc.gestao.venda.modelo.tabela.modelo.VendaModelo;
import com.dxc.gestao.venda.visao.formulario.FormularioPrincipal;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public final class FormularioPrincipalControlador implements KeyListener {
    
    private final FormularioPrincipal formularioPrincipal;
    private final ProdutoServico produtoServico;
    private final ProdutoModelo produtoModelo;
    
    private final EstoqueServico estoqueServico;
    private EstoqueHistoricoModelo estoqueHistoricoModelo;
    private final EstoqueHistoricoServico estoqueHistoricoServico;
    
    private final VendaRepositorioImpl vendaRepositorio;
    private VendaModelo vendaModelo;
    private List<VendaResponseDto> vendas;
    private List<EstoqueHistorico> estoqueHistoricos;
    private int cardSelecionado = 2;
    
    private UsuarioServico usuarioServico;

    public FormularioPrincipalControlador(FormularioPrincipal formularioPrincipal) {
        this.formularioPrincipal = formularioPrincipal;
        produtoServico = new ProdutoServico();
        produtoModelo = new ProdutoModelo(produtoServico.encontrarTodos());
        estoqueServico = new EstoqueServico();
        vendaRepositorio = new VendaRepositorioImpl();
        usuarioServico = new UsuarioServico();
        System.out.println("CONTROLER PRINCIPAL USUARIO ID: " + formularioPrincipal.getUsuarioId());
        estoqueHistoricoServico = new EstoqueHistoricoServico(formularioPrincipal.getUsuarioId());
        estoqueHistoricos = estoqueHistoricoServico.encontraTodos();
        estoqueHistoricoModelo = new EstoqueHistoricoModelo(estoqueHistoricos);
        vendas = vendaRepositorio.encontrarTodosPersonalizado();
        
        Usuario usuario = usuarioServico.encontrarPeloId(formularioPrincipal.getUsuarioId());
        
        if (usuario.getPerfil().equalsIgnoreCase(Perfil.PADRAO.name())) {
            vendas = vendas.stream()
                    .filter(v -> v.getUsuario().equals(usuario.getNome()))
                    .toList();
        }
        
        vendaModelo = new VendaModelo(vendas);
        actualiza();
        evento();
    }
    
    public void actualiza() {
        System.out.println(estoqueHistoricoModelo.getEstoqueHistoricos());
        String totalProduto = String.format("Total %s", produtoServico.quantidadeDeProduto());
        String totalEstoque = String.format("Total %s", estoqueServico.quantidadeDeEstoque());
        String totalVenda = String.format("Total %s", vendas.size());
        this.formularioPrincipal.setTotalProduto(totalProduto);
        this.formularioPrincipal.setTotalEstoque(totalEstoque);
        this.formularioPrincipal.getTabela().setModel(estoqueHistoricoModelo);
        this.formularioPrincipal.setTotalVenda(totalVenda);
        
    }
    
    private void evento() {
        formularioPrincipal.getCartao1().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardSelecionado = 0;
                formularioPrincipal.getLabelTitulo().setText("Tabela de produto");
                formularioPrincipal.getTabela().setModel(produtoModelo);
            }
            
        });
        
        formularioPrincipal.getCartao2().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardSelecionado = 1;
                formularioPrincipal.getLabelTitulo().setText("Tabela da venda");
                formularioPrincipal.getTabela().setModel(vendaModelo);
            }
            
        });
        
        formularioPrincipal.getCartao3().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardSelecionado = 2;
                formularioPrincipal.getLabelTitulo().setText("Historico do estoque");
                formularioPrincipal.getTabela().setModel(estoqueHistoricoModelo);
            }
            
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        String texto = formularioPrincipal.getCabecalho().getPesquisar().getText().trim();
        System.out.println(texto);
        System.out.println("MENU " + formularioPrincipal.getMenuSelectionadoIndex());
        
        System.out.println("CARD: " + cardSelecionado);
        if (formularioPrincipal.getMenuSelectionadoIndex() == 0) {
//            String texto = formularioPrincipal.getCabecalho().getPesquisar().getText().trim();
            System.out.println("TEXTO " + texto);
            System.out.println("CARD: " + cardSelecionado);
            switch(cardSelecionado) {
                case 0 -> {}
                case 1 -> {pesquisaVenda(texto);}
                case 2 -> {pesquisaEstoqueHistorico(texto);}
            }
        }
    }
    
    private void pesquisaEstoqueHistorico(String texto) {
        if (texto.isBlank()) {
            criarEstoqueHistorico(estoqueHistoricos);
        } else {
            List<EstoqueHistorico> lista = estoqueHistoricos.stream()
                    .filter(e -> e.getProduto().toLowerCase().contains(texto.toLowerCase())
                     || e.getTipo().toLowerCase().contains(texto.toLowerCase()) 
                     || e.getObservacao().toLowerCase().contains(texto.toLowerCase()))
                    .toList();
            criarEstoqueHistorico(lista);
        }
    }

    private void pesquisaVenda(String texto) {
        if (texto.isBlank()) {
            criarVendaModelo(vendas);
        } else {
            List<VendaResponseDto> lista = vendas.stream()
                    .filter(e -> {
                        if (e.getCliente() != null) {
                            return e.getCliente().contains(texto);
                        }
                        return false;
                    })
                    .toList();
            criarVendaModelo(lista);
        }
    }
    
    private void criarVendaModelo(List<VendaResponseDto> lista) {
        vendaModelo = new VendaModelo(lista);
        formularioPrincipal.getTabela().setModel(vendaModelo);
    }
    
    private void criarEstoqueHistorico(List<EstoqueHistorico> lista) {
        estoqueHistoricoModelo = new EstoqueHistoricoModelo(lista);
        formularioPrincipal.getTabela().setModel(estoqueHistoricoModelo);
    }
}
