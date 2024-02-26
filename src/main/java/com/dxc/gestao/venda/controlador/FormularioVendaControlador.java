package com.dxc.gestao.venda.controlador;

import com.dxc.gestao.venda.modelo.dto.VendaItemDto;
import com.dxc.gestao.venda.modelo.dto.VendaRequestDto;
import com.dxc.gestao.venda.modelo.dto.VendaResponseDto;
import com.dxc.gestao.venda.modelo.entidade.Cliente;
import com.dxc.gestao.venda.modelo.entidade.Estoque;
import com.dxc.gestao.venda.modelo.entidade.EstoqueHistorico;
import com.dxc.gestao.venda.modelo.entidade.EstoqueTipo;
import com.dxc.gestao.venda.modelo.entidade.Produto;
import com.dxc.gestao.venda.modelo.entidade.Venda;
import com.dxc.gestao.venda.modelo.entidade.VendaItem;
import com.dxc.gestao.venda.modelo.repositorio.impl.VendaItemRepositorioImpl;
import com.dxc.gestao.venda.modelo.repositorio.impl.VendaRepositorioImpl;
import com.dxc.gestao.venda.modelo.servico.CategoriaServico;
import com.dxc.gestao.venda.modelo.servico.ClienteServico;
import com.dxc.gestao.venda.modelo.servico.EstoqueHistoricoServico;
import com.dxc.gestao.venda.modelo.servico.EstoqueServico;
import com.dxc.gestao.venda.modelo.servico.ProdutoServico;
import com.dxc.gestao.venda.modelo.tabela.modelo.EstoqueHistoricoModelo;
import com.dxc.gestao.venda.modelo.tabela.modelo.EstoqueModelo;
import com.dxc.gestao.venda.modelo.tabela.modelo.VendaItemModelo;
import com.dxc.gestao.venda.modelo.tabela.modelo.VendaModelo;
import com.dxc.gestao.venda.modelo.util.ValidaCPForCNPJ;
import com.dxc.gestao.venda.visao.componentes.Mensagem;
import com.dxc.gestao.venda.visao.formulario.FormularioVenda;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

public class FormularioVendaControlador implements ActionListener, KeyListener, MouseListener {
    
    private final FormularioVenda formularioVenda;
    private final EstoqueServico estoqueServico;
    private final ProdutoServico produtoServico;
    private final CategoriaServico categoriaServico;
    private final ClienteServico clienteServico;
    private final EstoqueHistoricoServico estoqueHistoricoServico;
    private final VendaRepositorioImpl vendaRepositorio;
    private final VendaItemRepositorioImpl vendaItemRepositorio;
    private VendaItemModelo vendaItemModelo;
    private VendaModelo vendaModelo;
    private static Estoque estoque;
    private static Long vendaId;
    private static Optional<Produto> produto;
    private static Map<String, VendaItemDto> vendaItemDtosCarrinho;
    private static Map<String, Estoque> estoqueAtualiza;
    private static List<VendaResponseDto> vendaResponseDtos;
   

    public FormularioVendaControlador(FormularioVenda formularioVenda) {
        this.formularioVenda = formularioVenda;
        estoqueServico = new EstoqueServico();
        produtoServico = new ProdutoServico();
        categoriaServico = new CategoriaServico();
        clienteServico = new ClienteServico();
        estoqueHistoricoServico = new EstoqueHistoricoServico(formularioVenda.getUsuarioId());
        vendaRepositorio = new VendaRepositorioImpl();
        vendaItemRepositorio = new VendaItemRepositorioImpl();
        vendaItemDtosCarrinho = new HashMap<>();
        estoqueAtualiza = new HashMap<>();
        preencherComboBoxCategoria();
        atualizaTabelaVenda();
    }
    
    private void atualizaTabelaVenda() {
        vendaResponseDtos = vendaRepositorio.encontrarTodosPersonalizado();
        vendaModelo = new VendaModelo(vendaResponseDtos);
        formularioVenda.getTabela().setModel(vendaModelo);
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
            case "atualizar" -> {atualizarVenda();}
            case "comboboxproduto" -> { selecionaProdutoNoComboBox();}
            case "comboboxcategoria" -> { preencherComboBoxProduto();}
            case "carrinho" -> { adicionaNoCarrinho();}
            case "removercarrinho" -> { removerProdutoNoCarrinho();}
            case "limparcarrinho" -> { limpaCarrinho(); }
            case "limpa" -> {limpezaGeral();}
            case "vender" -> {vender();}
            case "detalhes" -> { mostrarDetalhes();}
            
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
        validaSeValorInsiridoENumero(descontoString);        
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
            validaCampo(quantidadeString, "quantidade");
            
            if (produto.isPresent()) {
                Produto produtoAtual = produto.get();
                
                if (vendaItemDtosCarrinho.containsKey(produtoAtual.getNome())) {
                    quantidadeExiste = vendaItemDtosCarrinho.get(produtoAtual.getNome()).getQuantidade();
                }
                
                int quantidade = Integer.parseInt(quantidadeString);
                quantidade += quantidadeExiste;
            
                desconto = converterStringParaBigDecimal(descontoString);
                total = produtoAtual.getPreco().subtract(desconto)
                        .multiply(BigDecimal.valueOf(quantidade));
            
                VendaItemDto vendaItemDto = VendaItemDto.builder()
                        .preco(produtoAtual.getPreco())
                        .desconto(desconto.multiply(BigDecimal.valueOf(quantidade)))
                        .produtoId(produtoAtual.getId())
                        .produtoNome(produtoAtual.getNome())
                        .total(total)
                        .quantidade(quantidade)
                        .build();
                
                vendaItemDtosCarrinho.put(produtoAtual.getNome(), vendaItemDto);
                
                atualizarCarrinho();
                atualizarValor();
                limpaCampos();
                estoque.setQuantidade(estoque.getQuantidade() - quantidade);
                estoqueAtualiza.put(produtoAtual.getNome(), estoque);
            }
            
        } else {
            formularioVenda.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, "Produto bloqueado ou nao no estoque");
        }
    }
    
    private void atualizarCarrinho() {
        vendaItemModelo = new VendaItemModelo(vendaItemDtosCarrinho);
        formularioVenda.getTabelaCarrinho().setModel(vendaItemModelo);
    }
    
    private void atualizarValor() {
        formularioVenda.getLabelVendaTotal().setText(getTotalVenda().setScale(2, RoundingMode.DOWN).toString());
        formularioVenda.getLabelVendaDesconto().setText(getDesconto().setScale(2, RoundingMode.DOWN).toString());
    }
    
    private BigDecimal getTotalVenda() {
        double totalVenda = this.vendaItemDtosCarrinho.values()
                .stream()
                .collect(Collectors.summingDouble(v -> v.getTotal().doubleValue()));
        return BigDecimal.valueOf(totalVenda);
    }
    
    private BigDecimal getDesconto() {
        double descontoVenda = this.vendaItemDtosCarrinho.values()
                .stream()
                .collect(Collectors.summingDouble(v -> v.getDesconto().doubleValue()));
        
        return BigDecimal.valueOf(descontoVenda);
    }
    
    private void limpaCampos() {
        formularioVenda.getCampoDeTextoBuscarPeloId().setText("");
        formularioVenda.getCampoDeTextoQuantidade().setText("");
        formularioVenda.getCampoDeTextoDesconto().setText("");
    }
    
    private void validaCampo(String texto, String campo) {
        if (texto.isBlank()) {
            formularioVenda.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, "Campos obrigatorios: " + campo);
            throw new RuntimeException();
        }
    }
    
    private BigDecimal converterStringParaBigDecimal(String texto) {
        try {
            if (!texto.isBlank()) {
                Double valor = Double.valueOf(texto);
                return BigDecimal.valueOf(valor);
            }
            return BigDecimal.ZERO;
        } catch (Exception e) {
            String mensagem = "Valor invalido";
            System.out.println(e);
            throw new RuntimeException(mensagem, e);
        }
    }
    
    private void limpaCarrinho() {
        vendaItemDtosCarrinho = new HashMap<>();
        atualizarCarrinho();
        atualizarValor();
        limpaDetalhesDaBuscaDoProduto();
    }
    
    private void removerProdutoNoCarrinho() {
        String nomeDoProduto = selectionaProdutoNoCarrinho();
        vendaItemDtosCarrinho.remove(nomeDoProduto);
        estoqueAtualiza.remove(nomeDoProduto);
        atualizarCarrinho();
        atualizarValor();
    }
    
    private String selectionaProdutoNoCarrinho() {
        int index = formularioVenda.getTabelaCarrinho().getSelectedRow();
        System.out.println("SELECIONADO : " + index);
        if (index != -1) {
           Object nomeProduto = formularioVenda.getTabelaCarrinho().getModel().getValueAt(index, 0);
           return nomeProduto.toString();
        } 
        String mensagem = "Deves seleciona o produto";
        formularioVenda.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, mensagem);
        throw new RuntimeException();
    }
    
    private void vender() {
        if (!vendaItemDtosCarrinho.isEmpty()) {
            String valorString = formularioVenda.getCampoDeTextoValorPago().getText().trim();
            String cliente = formularioVenda.getCampoDeTextoClienteCPF().getText().trim();
            
            validaCampo(valorString, "Valor Pago");
            validaCampo(cliente, "CPF do cliente");
            validaCliente(cliente);
            validaSeValorInsiridoENumero(valorString);
            
            Cliente clienteRegistado = buscaCliente(cliente);
            
            BigDecimal valorPago = converterStringParaBigDecimal(valorString);            
            BigDecimal totalVenda = getTotalVenda();
            BigDecimal desconto = getDesconto();
            
            if (valorPago.compareTo(totalVenda) >= 0 ) {
                Venda venda = Venda.builder()
                        .id(vendaId)
                        .totalVenda(totalVenda)
                        .valorPago(valorPago)
                        .troco(valorPago.subtract(totalVenda))
                        .desconto(desconto)
                        .clienteId(clienteRegistado.getId())
                        .usuarioId(formularioVenda.getUsuarioId())
                        .build();
                
                if (vendaId == null) {
                    venda.setDataCriacao(LocalDateTime.now());
                    
                } else {
                    venda.setUltimaActualizacao(LocalDateTime.now());
                }
                
                VendaRequestDto vendaRequestDto = VendaRequestDto.builder()
                        .venda(venda)
                        .vendaItems(getVendaItem())
                        .build();
                
                String mensagem = vendaRepositorio.salvarVenda(vendaRequestDto);
                
                if (mensagem.startsWith("Venda registrado")) {
                    formularioVenda.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.SUCESSO, mensagem);
                    atualizaDoEstoque();
                    limpaCarrinho();
                    atualizaTabelaVenda();
                    formularioVenda.getFormularioPrincipal().getCartao2().getLabelCartaoValor().setText("Total " + vendaResponseDtos.size());
                } else {
                    formularioVenda.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, mensagem);
                }
            } else {
                formularioVenda.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, "Valor de pagamento insuficiente");
            }            
            
        } else {
            formularioVenda.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, "Nao ha produto no carrinho");
        }
    }
    
    private void atualizaDoEstoque() {
        System.out.println("ESTOQUE::: " + estoqueAtualiza);
        try {
            estoqueAtualiza.values()
                    .stream()
                    .forEach(e -> {
                        estoqueServico.salvar(e);
                        Optional<Produto> produtoEncontrado = produtoServico.encontrarPeloId(e.getProdutoId());
                        if (produtoEncontrado.isPresent()) {
                            EstoqueHistorico estoqueHistorico = EstoqueHistorico.builder()
                                    .produto(produtoEncontrado.get().getNome())
                                    .tipo(EstoqueTipo.SAIDA.name())
                                    .observacao("Venda")
                                    .quantidade(e.getQuantidade())
                                    .usuario(e.getUsuarioId().toString())
                                    .dataCriacao(LocalDateTime.now())
                                    .build();
                            estoqueHistoricoServico.salvar(estoqueHistorico);
                        }
                    });
        } catch (Exception e) {
            System.out.println(e);
        }
        EstoqueModelo estoqueModelo = new EstoqueModelo(estoqueServico.encontrarTodos());
        EstoqueHistoricoModelo estoqueHistoricoModelo = new EstoqueHistoricoModelo(estoqueHistoricoServico.encontraTodos());
        formularioVenda.getFormularioEstoque().getTabela().setModel(estoqueModelo);
        formularioVenda.getFormularioPrincipal().getTabela().setModel(estoqueHistoricoModelo);
    }
    
    private List<VendaItem> getVendaItem() {
        return vendaItemDtosCarrinho.values()
                .stream()
                .map(vi -> {
                    
                    return VendaItem.builder()
                            .produtoId(vi.getProdutoId())
                            .quantidade(vi.getQuantidade())
                            .total(vi.getTotal())
                            .desconto(vi.getDesconto())
                            .build();
                    
                }).toList();
    }
    
    private Cliente buscaCliente(String cpf) {
        Cliente cliente = clienteServico.encontrarClientePeloAtributoCPF(cpf);
        
        if (cliente == null) {
            cliente = new Cliente();
            cliente.setCpf(cpf);
            clienteServico.salvar(cliente);
            cliente = clienteServico.encontrarClientePeloAtributoCPF(cpf);
        }
        return cliente;
    }
    
    private void validaCliente(String cliente) {
        if (!ValidaCPForCNPJ.isCPF(cliente) && !ValidaCPForCNPJ.isCNPJ(cliente)) {
            String mensagem = "Cliente invalido";
            formularioVenda.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, mensagem);
            throw new RuntimeException(mensagem);
        }
    }
    
    public void validaSeValorInsiridoENumero(String texto) {
        try {
            if (!texto.isBlank()) {
                Double.valueOf(texto);
            }
        } catch (Exception e) {
            formularioVenda.getMensagem().mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, "Valor pago invalida");
            throw new RuntimeException(e);
        }
    }
    
    private void limpezaGeral() {
        limpaCampos();
        limpaCarrinho();
        limpaDetalhesDaBuscaDoProduto();
        vendaId = null;
        formularioVenda.getCampoDeTextoClienteCPF().setText("");
        formularioVenda.getCampoDeTextoValorPago().setText("");
        vendaItemDtosCarrinho = new HashMap<>();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        selectionaProdutoNoCarrinho();
        selectionNaTabelaVenda();
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    private void mostrarDetalhes() {
        VendaResponseDto venda = selectionNaTabelaVenda();
        formularioVenda.getDialogDetalhes().pack();
        formularioVenda.getDialogDetalhes().setLocationRelativeTo(null);
        formularioVenda.getDialogDetalhes().setVisible(true);
        
        DefaultListModel model = new DefaultListModel();
        
        venda.getVendaItemDtos().forEach(vi -> {
//            System.out.println(vi);
//            model.addElement(vi.getProdutoNome());
            model.addElement(String.format("%d %s %.2f x %d = %.2f", 
                vi.getProdutoId(), vi.getProdutoNome(), vi.getPreco(), 
                vi.getQuantidade(), vi.getTotal()));
        });
        
        String cliente = venda.getCliente() == null ? "Nao informado" : venda.getCliente();
        
        formularioVenda.getLabelDetalheTotal().setText(String.format("TOTAL R$: %s", venda.getTotalVenda()));
        formularioVenda.getLabelDetalhesVendaId().setText(String.format("Extrato nº %s", venda.getId()));
        formularioVenda.getLabelDetalhesCliente().setText(String.format("CPF/CNPJ consumidor: %s", cliente));
        formularioVenda.getLabelDetalhesAtendente().setText(String.format("Atendente: %s", venda.getUsuario()));
        formularioVenda.getLabelDetalhesTroco().setText(String.format("Troco R$: %s", venda.getTroco()));
        formularioVenda.getLabelDetalhesValorPago().setText(String.format("Valor pago R$: %s", venda.getValorPago()));
        formularioVenda.getLabelDetalhesData().setText(venda.getDataCriacao().toString());
        formularioVenda.getListaDetalhesVenda().setModel(model);
    }
    
    private VendaResponseDto selectionNaTabelaVenda() {
        int index = formularioVenda.getTabela().getSelectedRow();

        if (index != -1) {
            VendaResponseDto venda = vendaResponseDtos.get(index);
            vendaId = venda.getId();
            List<VendaItemDto> vendaItemDtos = vendaItemRepositorio.encontrarVendaItemPelaVendaId(vendaId);
            venda.setVendaItemDtos(vendaItemDtos);
            return venda;
        }
        
        String mensagem = "Deves seleciona uma venda";
        JOptionPane.showMessageDialog(null, mensagem, "Seleciona tabela", JOptionPane.ERROR_MESSAGE);
        throw new RuntimeException(mensagem);
    }

    private void atualizarVenda() {
        limpezaGeral();
        VendaResponseDto vendaResponseDto = selectionNaTabelaVenda();
        vendaResponseDto.getVendaItemDtos()
                .forEach(vi -> vendaItemDtosCarrinho.put(vi.getProdutoNome(), vi));
        atualizarCarrinho();
        atualizarValor();
        formularioVenda.getCampoDeTextoClienteCPF().setText(vendaResponseDto.getCliente());
        formularioVenda.getCampoDeTextoValorPago().setText(vendaResponseDto.getValorPago().toString());
    }

}
