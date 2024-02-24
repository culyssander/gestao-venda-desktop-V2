package com.dxc.gestao.venda.visao.formulario;

import com.dxc.gestao.venda.controlador.FormularioVendaControlador;
import com.dxc.gestao.venda.modelo.entidade.VendaItem;
import com.dxc.gestao.venda.visao.componentes.BarraDeRolar;
import com.dxc.gestao.venda.visao.componentes.Botao;
import com.dxc.gestao.venda.visao.componentes.Cabecalho;
import com.dxc.gestao.venda.visao.componentes.CampoDeTexto;
import com.dxc.gestao.venda.visao.componentes.ComboBox;
import com.dxc.gestao.venda.visao.componentes.Mensagem;
import com.dxc.gestao.venda.visao.componentes.Tabela;
import com.dxc.gestao.venda.visao.util.MensagemUtil;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class FormularioVenda extends javax.swing.JPanel {
    
    private final FormularioVendaControlador formularioVendaControlador;
    private int menuSelectionadoIndex = -1;
    private boolean mostrar = true;
    private Cabecalho cabecalho;
    private MigLayout layout;
    private Long usuarioId;
    private MensagemUtil mensagem;

    public FormularioVenda(Long usuarioId, Cabecalho cabecalho) {
        initComponents();
        setOpaque(false);
        
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        jScrollPane1.setVerticalScrollBar(new BarraDeRolar());
        this.usuarioId = usuarioId;
        inicializacao();
        
        formularioVendaControlador = new FormularioVendaControlador(this);
        this.cabecalho = cabecalho;
        layout = new MigLayout("fill, insets");
        background.setLayout(layout);
        background.add(panelBoard2, "pos 0 0 100% 100%");
        this.mensagem = new MensagemUtil(background, layout);
        evento();
        eventoDoTeclado();
    }

    public MensagemUtil getMensagem() {
        return mensagem;
    }
    
    private void inicializacao() {
        panelCarrinho.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        panelCarrinho.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                panelCarrinho.setBackground(new Color(0, 0, 70));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                List<VendaItem> vendaItems = new ArrayList<>();
                
                VendaItem vendaItem = new VendaItem();
                vendaItems.add(vendaItem);
                
                if (!vendaItems.isEmpty()) {
                    mostrar = !mostrar;
                    mostrarCarrinho();
                } else {
                    mensagem.mostrarMensagem(Mensagem.TipoDeMensagem.ERRO, "Carrinho vazio!");
                }
            }
        });
        
        panelCarrinho.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                panelCarrinho.setBackground(new Color(28,181,224));
            }
            
        });
    }

    public Cabecalho getCabecalho() {
        return cabecalho;
    }

    public int getMenuSelectionadoIndex() {
        return menuSelectionadoIndex;
    }

    public void setMenuSelectionadoIndex(int menuSelectionadoIndex) {
        this.menuSelectionadoIndex = menuSelectionadoIndex;
    }

    private void eventoDoTeclado() {
        cabecalho.getPesquisar().addKeyListener(formularioVendaControlador);
        campoDeTextoBuscarPeloId.addKeyListener(formularioVendaControlador);
        campoDeTextoQuantidade.addKeyListener(formularioVendaControlador);
        campoDeTextoDesconto.addKeyListener(formularioVendaControlador);
    }

    private void evento() {
        botaoAdicionar.addActionListener(formularioVendaControlador);
        botaoAtualizar.addActionListener(formularioVendaControlador);
        botaoImprimir.addActionListener(formularioVendaControlador);
        botaoPermissao.addActionListener(formularioVendaControlador);
        botaoRemover.addActionListener(formularioVendaControlador);
        botaoAdicionarNoCarrinho.addActionListener(formularioVendaControlador);
        botaoCarrinhoLimpar.addActionListener(formularioVendaControlador);
        botaoCarrinhoRemover.addActionListener(formularioVendaControlador);
        botaoVender.addActionListener(formularioVendaControlador);
        botaoLimpa.addActionListener(formularioVendaControlador);
        comboBoxCategoria.addActionListener(formularioVendaControlador);
        comboBoxProduto.addActionListener(formularioVendaControlador);
    }
    
        public void mostrarCarrinho() {
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!mostrar) {
                    background.add(panelBoard4, "pos 0.5al 240", 0); // adicionar no primeiro indice
                    panelBoard4.setVisible(true);
                    background.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                
                if (mostrar) {
                    f = 240 * (1f - fraction);
                } else {
                    f = 240 * fraction;
                }

                layout.setComponentConstraints(panelBoard4, "pos 0.5al " + (int) (f - 240));
                background.repaint();
                background.revalidate();
            }

            @Override
            public void end() {
                if (mostrar) {
                    background.remove(panelBoard4);
                    background.repaint();
                    background.revalidate();
                } else {
                    mostrar = false;
                }
            }
        };
        
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Tabela getTabela() {
        return tabelaVenda;
    }

    public Tabela getTabelaCarrinho() {
        return tabelaCarrinho;
    }
    
    public JButton getBotaoAdicionar() {
        return botaoAdicionar;
    }

    public JButton getBotaoAtualizar() {
        return botaoAtualizar;
    }

    public JButton getBotaoImprimir() {
        return botaoImprimir;
    }

    public JButton getBotaoDetalhe() {
        return botaoPermissao;
    }

    public JButton getBotaoRemover() {
        return botaoRemover;
    }

    public JDialog getDialog() {
        return jDialog1;
    }

    public JLabel getLabelEstoqueQuantidade() {
        return labelEstoqueQuantidade;
    }

    public JLabel getLabelProdutoNome() {
        return labelProdutoNome;
    }

    public JLabel getLabelProdutoPreco() {
        return labelProdutoPreco;
    }

    public JLabel getLabelVendaDesconto() {
        return labelVendaDesconto;
    }

    public JLabel getLabelVendaTotal() {
        return labelVendaTotal;
    }

    public JLabel getLabelVendaTroco() {
        return labelVendaTroco;
    }

    public JLabel getLabelVendaValorPago() {
        return labelVendaValorPago;
    }

    public CampoDeTexto getCampoDeTextoBuscarPeloId() {
        return campoDeTextoBuscarPeloId;
    }

    public CampoDeTexto getCampoDeTextoClienteCPF() {
        return campoDeTextoClienteCPF;
    }

    public CampoDeTexto getCampoDeTextoDesconto() {
        return campoDeTextoDesconto;
    }

    public CampoDeTexto getCampoDeTextoQuantidade() {
        return campoDeTextoQuantidade;
    }

    public CampoDeTexto getCampoDeTextoValorPago() {
        return campoDeTextoValorPago;
    }

    public Botao getBotaoAdicionarNoCarrinho() {
        return botaoAdicionarNoCarrinho;
    }

    public Botao getBotaoCarrinhoLimpar() {
        return botaoCarrinhoLimpar;
    }

    public Botao getBotaoCarrinhoRemover() {
        return botaoCarrinhoRemover;
    }

    public Botao getBotaoLimpa() {
        return botaoLimpa;
    }

    public JButton getBotaoPermissao() {
        return botaoPermissao;
    }

    public Botao getBotaoVender() {
        return botaoVender;
    }

    public ComboBox getComboBoxCategoria() {
        return comboBoxCategoria;
    }

    public ComboBox getComboBoxProduto() {
        return comboBoxProduto;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        background = new javax.swing.JLayeredPane();
        panelBoard2 = new com.dxc.gestao.venda.visao.componentes.PanelBoard();
        jLabel4 = new javax.swing.JLabel();
        panelCarrinho = new com.dxc.gestao.venda.visao.componentes.PanelCiclo();
        jLabel5 = new javax.swing.JLabel();
        campoDeTextoBuscarPeloId = new com.dxc.gestao.venda.visao.componentes.CampoDeTexto();
        comboBoxProduto = new com.dxc.gestao.venda.visao.componentes.ComboBox();
        comboBoxCategoria = new com.dxc.gestao.venda.visao.componentes.ComboBox();
        campoDeTextoQuantidade = new com.dxc.gestao.venda.visao.componentes.CampoDeTexto();
        campoDeTextoDesconto = new com.dxc.gestao.venda.visao.componentes.CampoDeTexto();
        campoDeTextoValorPago = new com.dxc.gestao.venda.visao.componentes.CampoDeTexto();
        campoDeTextoClienteCPF = new com.dxc.gestao.venda.visao.componentes.CampoDeTexto();
        jPanel2 = new javax.swing.JPanel();
        botaoAdicionarNoCarrinho = new com.dxc.gestao.venda.visao.componentes.Botao();
        botaoVender = new com.dxc.gestao.venda.visao.componentes.Botao();
        botaoLimpa = new com.dxc.gestao.venda.visao.componentes.Botao();
        jLabel6 = new javax.swing.JLabel();
        panelBoard3 = new com.dxc.gestao.venda.visao.componentes.PanelBoard();
        labelProdutoNome = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        labelVendaTotal = new javax.swing.JLabel();
        labelEstoqueQuantidade = new javax.swing.JLabel();
        labelProdutoPreco = new javax.swing.JLabel();
        labelVendaValorPago = new javax.swing.JLabel();
        labelVendaDesconto = new javax.swing.JLabel();
        labelVendaTroco = new javax.swing.JLabel();
        panelBoard4 = new com.dxc.gestao.venda.visao.componentes.PanelBoard();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaCarrinho = new com.dxc.gestao.venda.visao.componentes.Tabela();
        jLabel20 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        botaoCarrinhoRemover = new com.dxc.gestao.venda.visao.componentes.Botao();
        botaoCarrinhoLimpar = new com.dxc.gestao.venda.visao.componentes.Botao();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        botaoAdicionar = new javax.swing.JButton();
        botaoAtualizar = new javax.swing.JButton();
        botaoRemover = new javax.swing.JButton();
        botaoPermissao = new javax.swing.JButton();
        botaoImprimir = new javax.swing.JButton();
        panelBoard1 = new com.dxc.gestao.venda.visao.componentes.PanelBoard();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaVenda = new com.dxc.gestao.venda.visao.componentes.Tabela();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setOpaque(true);

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 703, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );

        panelBoard2.setBackground(new java.awt.Color(255, 255, 255));
        panelBoard2.setOpaque(true);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(28, 181, 224));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Registro da venda");

        panelCarrinho.setBackground(new java.awt.Color(0, 0, 70));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-venda\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\carrinho.png")); // NOI18N
        jLabel5.setText("0");

        javax.swing.GroupLayout panelCarrinhoLayout = new javax.swing.GroupLayout(panelCarrinho);
        panelCarrinho.setLayout(panelCarrinhoLayout);
        panelCarrinhoLayout.setHorizontalGroup(
            panelCarrinhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
        );
        panelCarrinhoLayout.setVerticalGroup(
            panelCarrinhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCarrinhoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addContainerGap())
        );

        campoDeTextoBuscarPeloId.setDica("Busca produto pelo ID");
        campoDeTextoBuscarPeloId.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-venda\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\produto1.png")); // NOI18N

        comboBoxProduto.setActionCommand("comboBoxProduto");

        comboBoxCategoria.setActionCommand("comboBoxCategoria");

        campoDeTextoQuantidade.setDica("Quantidade");
        campoDeTextoQuantidade.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-venda\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\quantidade.png")); // NOI18N

        campoDeTextoDesconto.setToolTipText("");
        campoDeTextoDesconto.setDica("Desconto");
        campoDeTextoDesconto.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-venda\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\desconto.png")); // NOI18N

        campoDeTextoValorPago.setToolTipText("");
        campoDeTextoValorPago.setDica("Valor pago ");
        campoDeTextoValorPago.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-venda\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\preco.png")); // NOI18N

        campoDeTextoClienteCPF.setDica("CPF do cliente");
        campoDeTextoClienteCPF.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-venda\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\id.png")); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        botaoAdicionarNoCarrinho.setBackground(new java.awt.Color(28, 181, 224));
        botaoAdicionarNoCarrinho.setForeground(new java.awt.Color(255, 255, 255));
        botaoAdicionarNoCarrinho.setText("Adiciona");
        botaoAdicionarNoCarrinho.setActionCommand("carrinho");
        botaoAdicionarNoCarrinho.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel2.add(botaoAdicionarNoCarrinho);

        botaoVender.setBackground(new java.awt.Color(28, 181, 224));
        botaoVender.setForeground(new java.awt.Color(255, 255, 255));
        botaoVender.setText("Vender");
        botaoVender.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel2.add(botaoVender);

        botaoLimpa.setBackground(new java.awt.Color(28, 181, 224));
        botaoLimpa.setForeground(new java.awt.Color(255, 255, 255));
        botaoLimpa.setText("Limpa");
        botaoLimpa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel2.add(botaoLimpa);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(120, 120, 120));
        jLabel6.setText("Detalhes da venda");

        panelBoard3.setBackground(new java.awt.Color(0, 0, 70));
        panelBoard3.setOpaque(true);

        labelProdutoNome.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelProdutoNome.setForeground(new java.awt.Color(255, 255, 255));
        labelProdutoNome.setText("Nome do produto:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Qtd. estoque:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Preço:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Total da venda: ");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Valor pago:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Desconto:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Troco:");

        labelVendaTotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelVendaTotal.setForeground(new java.awt.Color(255, 255, 255));
        labelVendaTotal.setText("0.00");

        labelEstoqueQuantidade.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelEstoqueQuantidade.setForeground(new java.awt.Color(255, 255, 255));
        labelEstoqueQuantidade.setText("0");

        labelProdutoPreco.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelProdutoPreco.setForeground(new java.awt.Color(255, 255, 255));
        labelProdutoPreco.setText("0.00");

        labelVendaValorPago.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelVendaValorPago.setForeground(new java.awt.Color(255, 255, 255));
        labelVendaValorPago.setText("0.00");

        labelVendaDesconto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelVendaDesconto.setForeground(new java.awt.Color(255, 255, 255));
        labelVendaDesconto.setText("0.00");

        labelVendaTroco.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelVendaTroco.setForeground(new java.awt.Color(255, 255, 255));
        labelVendaTroco.setText("0.00");

        javax.swing.GroupLayout panelBoard3Layout = new javax.swing.GroupLayout(panelBoard3);
        panelBoard3.setLayout(panelBoard3Layout);
        panelBoard3Layout.setHorizontalGroup(
            panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelProdutoNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoard3Layout.createSequentialGroup()
                        .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelBoard3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(55, 55, 55)))
                        .addGap(17, 17, 17)
                        .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelEstoqueQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelProdutoPreco, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoard3Layout.createSequentialGroup()
                        .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelVendaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelVendaValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelVendaDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelVendaTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        panelBoard3Layout.setVerticalGroup(
            panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelProdutoNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(labelEstoqueQuantidade))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(labelProdutoPreco))
                .addGap(75, 75, 75)
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(labelVendaTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(labelVendaValorPago))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(labelVendaDesconto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(labelVendaTroco))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBoard2Layout = new javax.swing.GroupLayout(panelBoard2);
        panelBoard2.setLayout(panelBoard2Layout);
        panelBoard2Layout.setHorizontalGroup(
            panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard2Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBoard2Layout.createSequentialGroup()
                        .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                        .addComponent(panelCarrinho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBoard2Layout.createSequentialGroup()
                        .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(campoDeTextoClienteCPF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(campoDeTextoValorPago, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(campoDeTextoDesconto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(campoDeTextoQuantidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelBoard2Layout.createSequentialGroup()
                                    .addComponent(comboBoxCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(comboBoxProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(campoDeTextoBuscarPeloId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelBoard3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        panelBoard2Layout.setVerticalGroup(
            panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard2Layout.createSequentialGroup()
                .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBoard2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelCarrinho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBoard2Layout.createSequentialGroup()
                        .addComponent(campoDeTextoBuscarPeloId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoDeTextoQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDeTextoDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDeTextoValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDeTextoClienteCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelBoard3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        tabelaCarrinho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tabelaCarrinho);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(28, 181, 224));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Checkout");

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        botaoCarrinhoRemover.setText("Remover");
        jPanel3.add(botaoCarrinhoRemover);

        botaoCarrinhoLimpar.setText("Limpar");
        jPanel3.add(botaoCarrinhoLimpar);

        javax.swing.GroupLayout panelBoard4Layout = new javax.swing.GroupLayout(panelBoard4);
        panelBoard4.setLayout(panelBoard4Layout);
        panelBoard4Layout.setHorizontalGroup(
            panelBoard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBoard4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBoard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBoard4Layout.setVerticalGroup(
            panelBoard4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoard4Layout.createSequentialGroup()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("> Venda");

        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        botaoAdicionar.setBackground(new java.awt.Color(0, 0, 70));
        botaoAdicionar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoAdicionar.setForeground(new java.awt.Color(255, 255, 255));
        botaoAdicionar.setText("Adicionar");
        botaoAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAdicionarActionPerformed(evt);
            }
        });
        jPanel1.add(botaoAdicionar);

        botaoAtualizar.setBackground(new java.awt.Color(0, 0, 70));
        botaoAtualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoAtualizar.setForeground(new java.awt.Color(255, 255, 255));
        botaoAtualizar.setText("Atualizar");
        jPanel1.add(botaoAtualizar);

        botaoRemover.setBackground(new java.awt.Color(0, 0, 70));
        botaoRemover.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoRemover.setForeground(new java.awt.Color(255, 255, 255));
        botaoRemover.setText("Remover");
        botaoRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRemoverActionPerformed(evt);
            }
        });
        jPanel1.add(botaoRemover);

        botaoPermissao.setBackground(new java.awt.Color(0, 0, 70));
        botaoPermissao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoPermissao.setForeground(new java.awt.Color(255, 255, 255));
        botaoPermissao.setText("Detalhes");
        botaoPermissao.setActionCommand("permissao");
        botaoPermissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPermissaoActionPerformed(evt);
            }
        });
        jPanel1.add(botaoPermissao);

        botaoImprimir.setBackground(new java.awt.Color(0, 0, 70));
        botaoImprimir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoImprimir.setForeground(new java.awt.Color(255, 255, 255));
        botaoImprimir.setText("Imprimir");
        jPanel1.add(botaoImprimir);

        panelBoard1.setBackground(new java.awt.Color(255, 255, 255));
        panelBoard1.setOpaque(true);

        jScrollPane1.setBorder(null);

        tabelaVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaVenda);

        jDateChooser1.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser1.setForeground(new java.awt.Color(120, 120, 120));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(120, 120, 120));
        jLabel2.setText("Data inicial:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(120, 120, 120));
        jLabel3.setText("Data final:");

        jDateChooser2.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser2.setForeground(new java.awt.Color(120, 120, 120));

        javax.swing.GroupLayout panelBoard1Layout = new javax.swing.GroupLayout(panelBoard1);
        panelBoard1.setLayout(panelBoard1Layout);
        panelBoard1Layout.setHorizontalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                    .addGroup(panelBoard1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBoard1Layout.setVerticalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBoard1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBoard1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botaoRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoRemoverActionPerformed

    private void botaoPermissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPermissaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoPermissaoActionPerformed

    private void botaoAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoAdicionarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane background;
    private javax.swing.JButton botaoAdicionar;
    private com.dxc.gestao.venda.visao.componentes.Botao botaoAdicionarNoCarrinho;
    private javax.swing.JButton botaoAtualizar;
    private com.dxc.gestao.venda.visao.componentes.Botao botaoCarrinhoLimpar;
    private com.dxc.gestao.venda.visao.componentes.Botao botaoCarrinhoRemover;
    private javax.swing.JButton botaoImprimir;
    private com.dxc.gestao.venda.visao.componentes.Botao botaoLimpa;
    private javax.swing.JButton botaoPermissao;
    private javax.swing.JButton botaoRemover;
    private com.dxc.gestao.venda.visao.componentes.Botao botaoVender;
    private com.dxc.gestao.venda.visao.componentes.CampoDeTexto campoDeTextoBuscarPeloId;
    private com.dxc.gestao.venda.visao.componentes.CampoDeTexto campoDeTextoClienteCPF;
    private com.dxc.gestao.venda.visao.componentes.CampoDeTexto campoDeTextoDesconto;
    private com.dxc.gestao.venda.visao.componentes.CampoDeTexto campoDeTextoQuantidade;
    private com.dxc.gestao.venda.visao.componentes.CampoDeTexto campoDeTextoValorPago;
    private com.dxc.gestao.venda.visao.componentes.ComboBox comboBoxCategoria;
    private com.dxc.gestao.venda.visao.componentes.ComboBox comboBoxProduto;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelEstoqueQuantidade;
    private javax.swing.JLabel labelProdutoNome;
    private javax.swing.JLabel labelProdutoPreco;
    private javax.swing.JLabel labelVendaDesconto;
    private javax.swing.JLabel labelVendaTotal;
    private javax.swing.JLabel labelVendaTroco;
    private javax.swing.JLabel labelVendaValorPago;
    private com.dxc.gestao.venda.visao.componentes.PanelBoard panelBoard1;
    private com.dxc.gestao.venda.visao.componentes.PanelBoard panelBoard2;
    private com.dxc.gestao.venda.visao.componentes.PanelBoard panelBoard3;
    private com.dxc.gestao.venda.visao.componentes.PanelBoard panelBoard4;
    private com.dxc.gestao.venda.visao.componentes.PanelCiclo panelCarrinho;
    private com.dxc.gestao.venda.visao.componentes.Tabela tabelaCarrinho;
    private com.dxc.gestao.venda.visao.componentes.Tabela tabelaVenda;
    // End of variables declaration//GEN-END:variables
}
