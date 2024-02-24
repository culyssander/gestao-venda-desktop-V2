
package com.dxc.gestao.venda.visao.formulario;

import com.dxc.gestao.venda.controlador.FormularioUsuarioControlador;
import com.dxc.gestao.venda.visao.componentes.BarraDeRolar;
import com.dxc.gestao.venda.visao.componentes.Cabecalho;
import com.dxc.gestao.venda.visao.componentes.Tabela;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class FormularioUsuario extends javax.swing.JPanel {
    
    private final FormularioUsuarioControlador formularioUsuarioControlador;
    private int menuSelectionadoIndex = -1;
    private Cabecalho cabecalho;
    private Long usuarioId;

    public FormularioUsuario(Long usuarioId, Cabecalho cabecalho) {
        initComponents();
        setOpaque(false);
        
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        jScrollPane1.setVerticalScrollBar(new BarraDeRolar());
        dialogPermissao.setBackground(Color.WHITE);
        dialogCadastro.setResizable(false);
        textoFoto.setColumns(15);
        this.usuarioId = usuarioId;
        
        formularioUsuarioControlador = new FormularioUsuarioControlador(this);
        this.cabecalho = cabecalho;
        evento();
        eventoDoTeclado();
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
    
    public void eventoDoTeclado() {
        cabecalho.getPesquisar().addKeyListener(formularioUsuarioControlador);
    }

    private void evento() {
        botaoAdicionar.addActionListener(formularioUsuarioControlador);
        botaoAtualizar.addActionListener(formularioUsuarioControlador);
        botaoImprimir.addActionListener(formularioUsuarioControlador);
        botaoPermissao.addActionListener(formularioUsuarioControlador);
        botaoRemover.addActionListener(formularioUsuarioControlador);
        botaoSalvar.addActionListener(formularioUsuarioControlador);
        botaoFoto.addActionListener(formularioUsuarioControlador);
        botaoSalvaPermissao.addActionListener(formularioUsuarioControlador);
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Tabela getTabelaUsuario() {
        return tabelaUsuario;
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

    public JButton getBotaoPermissao() {
        return botaoPermissao;
    }

    public JButton getBotaoRemover() {
        return botaoRemover;
    }

    public JDialog getDialogCadastro() {
        return dialogCadastro;
    }

    public JTextField getTextoNome() {
        return textoNome;
    }

    public JTextField getTextoEmail() {
        return textoEmail;
    }

    public JPasswordField getTextoSenha() {
        return textoSenha;
    }

    public JComboBox<String> getComboboxPerfil() {
        return comboboxPerfil;
    }

    public JButton getBotaoSalvar() {
        return botaoSalvar;
    }

    public JLabel getLabelTitulo() {
        return labelTitulo;
    }

    public JLabel getLabelMensagem() {
        return labelMensagem;
    }

    public JRadioButton getRadioBotaoAtivo() {
        return radioBotaoAtivo;
    }

    public JRadioButton getRadioBotaoDesativo() {
        return radioBotaoDesativo;
    }

    public JDialog getDialogPermissao() {
        return dialogPermissao;
    }

    public JLabel getLabelUsuarioName() {
        return labelUsuarioName;
    }

    public JTextField getTextoFoto() {
        return textoFoto;
    }

    public JButton getBotaoFoto() {
        return botaoFoto;
    }

    public JCheckBox getCheckPermissaoUsuarioSalvar() {
        return checkPermissaoUsuarioSalvar;
    }

    public JCheckBox getCheckPermissaoUsuarioEncontrarSomenteDados() {
        return checkPermissaoUsuarioEncontrarSomenteDados;
    }

    public JCheckBox getCheckPermissaoUsuarioEncontrarTodos() {
        return checkPermissaoUsuarioEncontrarTodos;
    }

    public JCheckBox getCheckPermissaoUsuarioRemover() {
        return checkPermissaoUsuarioRemover;
    }

    public JCheckBox getCheckPermissaoCategoriaRemove() {
        return checkPermissaoCategoriaRemove;
    }

    public JCheckBox getCheckPermissaoCategoriaSalva() {
        return checkPermissaoCategoriaSalva;
    }

    public JCheckBox getCheckPermissaoVendaRemove() {
        return checkPermissaoVendaRemove;
    }

    public JCheckBox getCheckPermissaoSalva() {
        return checkPermissaoSalva;
    }
    
    

    public JCheckBox getCheckPermissaoClienteRemove() {
        return checkPermissaoClienteRemove;
    }

    public JCheckBox getCheckPermissaoEstoqueRemove() {
        return checkPermissaoEstoqueRemove;
    }

    public JCheckBox getCheckPermissaoEstoqueSalva() {
        return checkPermissaoEstoqueSalva;
    }

    public JCheckBox getCheckPermissaoProdutoRemove() {
        return checkPermissaoProdutoRemove;
    }

    public JCheckBox getCheckPermissaoProdutoSalva() {
        return checkPermissaoProdutoSalva;
    }

    public JCheckBox getCheckPermissaoRemove() {
        return checkPermissaoVendaRemove;
    }

    public JCheckBox getCheckPermissaoVendaAtualiza() {
        return checkPermissaoVendaAtualiza;
    }

    public JCheckBox getCheckPermissaoVendaSalva() {
        return checkPermissaoVendaSalva;
    }

    public JCheckBox getCheckPermissaoEstoqueHistoricoTodo() {
        return checkPermissaoEstoqueHistoricoTodo;
    }

    public JCheckBox getCheckPermissaoEstoqueSomente() {
        return checkPermissaoEstoqueSomente;
    }
    
    public JButton getBotaoSalvaPermissao() {
        return botaoSalvaPermissao;
    }

    public JLabel getLabelPermissaoMensagem() {
        return labelPermissaoMensagem;
    }
    
    
    public List<JCheckBox> allCheckBox() {
        List<JCheckBox> lista = new ArrayList<>();
        
        lista.add(checkPermissaoCategoriaRemove);
        lista.add(checkPermissaoCategoriaSalva);
        lista.add(checkPermissaoClienteRemove);
        lista.add(checkPermissaoUsuarioEncontrarSomenteDados);
        lista.add(checkPermissaoUsuarioEncontrarTodos);
        lista.add(checkPermissaoUsuarioSalvar);
        lista.add(checkPermissaoUsuarioRemover);
        lista.add(checkPermissaoEstoqueRemove);
        lista.add(checkPermissaoEstoqueSalva);
        lista.add(checkPermissaoProdutoRemove);
        lista.add(checkPermissaoProdutoSalva);
        lista.add(checkPermissaoSalva);
        lista.add(checkPermissaoVendaSalva);
        lista.add(checkPermissaoVendaAtualiza);
        lista.add(checkPermissaoVendaRemove);
        lista.add(checkPermissaoEstoqueHistoricoTodo);
        lista.add(checkPermissaoEstoqueSomente);
        
        return lista;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogCadastro = new javax.swing.JDialog();
        panelBoard2 = new com.dxc.gestao.venda.visao.componentes.PanelBoard();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textoNome = new javax.swing.JTextField();
        textoEmail = new javax.swing.JTextField();
        textoSenha = new javax.swing.JPasswordField();
        comboboxPerfil = new javax.swing.JComboBox<>();
        botaoSalvar = new javax.swing.JButton();
        labelTitulo = new javax.swing.JLabel();
        labelMensagem = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        radioBotaoAtivo = new javax.swing.JRadioButton();
        radioBotaoDesativo = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        textoFoto = new javax.swing.JTextField();
        botaoFoto = new javax.swing.JButton();
        dialogPermissao = new javax.swing.JDialog();
        panelBoard3 = new com.dxc.gestao.venda.visao.componentes.PanelBoard();
        botaoSalvaPermissao = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        checkPermissaoUsuarioSalvar = new javax.swing.JCheckBox();
        checkPermissaoUsuarioEncontrarSomenteDados = new javax.swing.JCheckBox();
        checkPermissaoUsuarioEncontrarTodos = new javax.swing.JCheckBox();
        checkPermissaoUsuarioRemover = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        checkPermissaoCategoriaSalva = new javax.swing.JCheckBox();
        checkPermissaoCategoriaRemove = new javax.swing.JCheckBox();
        checkPermissaoProdutoSalva = new javax.swing.JCheckBox();
        checkPermissaoProdutoRemove = new javax.swing.JCheckBox();
        checkPermissaoEstoqueSalva = new javax.swing.JCheckBox();
        checkPermissaoEstoqueRemove = new javax.swing.JCheckBox();
        checkPermissaoVendaSalva = new javax.swing.JCheckBox();
        checkPermissaoVendaAtualiza = new javax.swing.JCheckBox();
        checkPermissaoVendaRemove = new javax.swing.JCheckBox();
        checkPermissaoSalva = new javax.swing.JCheckBox();
        checkPermissaoClienteRemove = new javax.swing.JCheckBox();
        jLabel16 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        labelUsuarioName = new javax.swing.JLabel();
        labelPermissaoMensagem = new javax.swing.JLabel();
        checkPermissaoEstoqueHistoricoTodo = new javax.swing.JCheckBox();
        checkPermissaoEstoqueSomente = new javax.swing.JCheckBox();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        botaoAdicionar = new javax.swing.JButton();
        botaoAtualizar = new javax.swing.JButton();
        botaoRemover = new javax.swing.JButton();
        botaoPermissao = new javax.swing.JButton();
        botaoImprimir = new javax.swing.JButton();
        panelBoard1 = new com.dxc.gestao.venda.visao.componentes.PanelBoard();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaUsuario = new com.dxc.gestao.venda.visao.componentes.Tabela();

        panelBoard2.setBackground(new java.awt.Color(255, 255, 255));
        panelBoard2.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Nome:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Email:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Senha:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Perfil:");

        comboboxPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleciona o Perfil", "Admin", "Padrao" }));

        botaoSalvar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoSalvar.setText("Salvar");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        labelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("CADASTRO");

        labelMensagem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Estado:");

        buttonGroup1.add(radioBotaoAtivo);
        radioBotaoAtivo.setSelected(true);
        radioBotaoAtivo.setText("Ativo");
        radioBotaoAtivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotaoAtivoActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioBotaoDesativo);
        radioBotaoDesativo.setText("Desativo");
        radioBotaoDesativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioBotaoDesativoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Foto:");

        textoFoto.setEditable(false);

        botaoFoto.setText("Seleciona");

        javax.swing.GroupLayout panelBoard2Layout = new javax.swing.GroupLayout(panelBoard2);
        panelBoard2.setLayout(panelBoard2Layout);
        panelBoard2Layout.setHorizontalGroup(
            panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBoard2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBoard2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textoFoto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoFoto))
                    .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(botaoSalvar)
                        .addGroup(panelBoard2Layout.createSequentialGroup()
                            .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(panelBoard2Layout.createSequentialGroup()
                                    .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18))
                                .addGroup(panelBoard2Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(17, 17, 17)))
                            .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textoNome)
                                .addComponent(textoEmail)
                                .addComponent(textoSenha)
                                .addComponent(comboboxPerfil, 0, 256, Short.MAX_VALUE)))
                        .addGroup(panelBoard2Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(radioBotaoAtivo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(radioBotaoDesativo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(44, 44, 44))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        panelBoard2Layout.setVerticalGroup(
            panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard2Layout.createSequentialGroup()
                .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBoard2Layout.createSequentialGroup()
                        .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(labelMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(textoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(textoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(textoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(comboboxPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(textoFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoFoto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(radioBotaoDesativo, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(radioBotaoAtivo))
                .addGap(12, 12, 12)
                .addComponent(botaoSalvar)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dialogCadastroLayout = new javax.swing.GroupLayout(dialogCadastro.getContentPane());
        dialogCadastro.getContentPane().setLayout(dialogCadastroLayout);
        dialogCadastroLayout.setHorizontalGroup(
            dialogCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBoard2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dialogCadastroLayout.setVerticalGroup(
            dialogCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBoard2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        dialogPermissao.setTitle("Permissão");
        dialogPermissao.setBackground(new java.awt.Color(255, 255, 255));

        panelBoard3.setBackground(new java.awt.Color(255, 255, 255));
        panelBoard3.setOpaque(true);

        botaoSalvaPermissao.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoSalvaPermissao.setText("Salvar");
        botaoSalvaPermissao.setActionCommand("permissaosalvar");
        botaoSalvaPermissao.setName(""); // NOI18N
        botaoSalvaPermissao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvaPermissaoActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Usuario");

        checkPermissaoUsuarioSalvar.setText("Salva");
        checkPermissaoUsuarioSalvar.setName("usuario:salvar"); // NOI18N
        checkPermissaoUsuarioSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPermissaoUsuarioSalvarActionPerformed(evt);
            }
        });

        checkPermissaoUsuarioEncontrarSomenteDados.setText("Somente teus dados");
        checkPermissaoUsuarioEncontrarSomenteDados.setName("usuario:encontrarSomenteDadosRelacionado"); // NOI18N
        checkPermissaoUsuarioEncontrarSomenteDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPermissaoUsuarioEncontrarSomenteDadosActionPerformed(evt);
            }
        });

        checkPermissaoUsuarioEncontrarTodos.setText("Buscar todos");
        checkPermissaoUsuarioEncontrarTodos.setName("usuario:encontrarTodos"); // NOI18N
        checkPermissaoUsuarioEncontrarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPermissaoUsuarioEncontrarTodosActionPerformed(evt);
            }
        });

        checkPermissaoUsuarioRemover.setText("Remove");
        checkPermissaoUsuarioRemover.setName("usuario:removerPeloId "); // NOI18N
        checkPermissaoUsuarioRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPermissaoUsuarioRemoverActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Produto");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Estoque");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Categoria");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Venda");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Permissão");

        checkPermissaoCategoriaSalva.setText("Salva");
        checkPermissaoCategoriaSalva.setName("categoria:salvar"); // NOI18N

        checkPermissaoCategoriaRemove.setText("Remove");
        checkPermissaoCategoriaRemove.setName("categoria:removerPeloId "); // NOI18N

        checkPermissaoProdutoSalva.setText("Salva");
        checkPermissaoProdutoSalva.setName("produto:salvar"); // NOI18N

        checkPermissaoProdutoRemove.setText("Remove");
        checkPermissaoProdutoRemove.setName("produto:removerPeloId"); // NOI18N

        checkPermissaoEstoqueSalva.setText("Salva");
        checkPermissaoEstoqueSalva.setName("estoque:salvar"); // NOI18N
        checkPermissaoEstoqueSalva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPermissaoEstoqueSalvaActionPerformed(evt);
            }
        });

        checkPermissaoEstoqueRemove.setText("Remove");
        checkPermissaoEstoqueRemove.setName("estoque:removerPeloId"); // NOI18N
        checkPermissaoEstoqueRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPermissaoEstoqueRemoveActionPerformed(evt);
            }
        });

        checkPermissaoVendaSalva.setText("Salva");
        checkPermissaoVendaSalva.setName("venda:salvar"); // NOI18N

        checkPermissaoVendaAtualiza.setText("Atualiza");
        checkPermissaoVendaAtualiza.setName("venda:actualizar"); // NOI18N

        checkPermissaoVendaRemove.setText("Remove");
        checkPermissaoVendaRemove.setName("venda:remover"); // NOI18N

        checkPermissaoSalva.setText("Salva");
        checkPermissaoSalva.setName("permissao:salvar"); // NOI18N

        checkPermissaoClienteRemove.setText("Remove");
        checkPermissaoClienteRemove.setName("cliente:remover"); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Cliente");

        labelUsuarioName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelUsuarioName.setText("Quitumba Ferreira");

        labelPermissaoMensagem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelPermissaoMensagem.setForeground(new java.awt.Color(255, 255, 255));
        labelPermissaoMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        checkPermissaoEstoqueHistoricoTodo.setText("Todo Historico");
        checkPermissaoEstoqueHistoricoTodo.setName("estoqueHistorico:encontrarTodos"); // NOI18N
        checkPermissaoEstoqueHistoricoTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPermissaoEstoqueHistoricoTodoActionPerformed(evt);
            }
        });

        checkPermissaoEstoqueSomente.setText("Somente o seu");
        checkPermissaoEstoqueSomente.setName("estoqueHistorico:somenteSeu"); // NOI18N
        checkPermissaoEstoqueSomente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPermissaoEstoqueSomenteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBoard3Layout = new javax.swing.GroupLayout(panelBoard3);
        panelBoard3.setLayout(panelBoard3Layout);
        panelBoard3Layout.setHorizontalGroup(
            panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoard3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel11)
                .addGap(270, 270, 270)
                .addComponent(jLabel13)
                .addContainerGap(138, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoard3Layout.createSequentialGroup()
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBoard3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(270, 270, 270)
                        .addComponent(jLabel15)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBoard3Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBoard3Layout.createSequentialGroup()
                                .addComponent(checkPermissaoVendaSalva, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkPermissaoSalva, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(panelBoard3Layout.createSequentialGroup()
                                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(checkPermissaoVendaAtualiza, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkPermissaoVendaRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkPermissaoClienteRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(44, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelBoard3Layout.createSequentialGroup()
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                    .addComponent(botaoSalvaPermissao, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator1)
                    .addGroup(panelBoard3Layout.createSequentialGroup()
                        .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBoard3Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel10)
                                .addGap(273, 273, 273)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBoard3Layout.createSequentialGroup()
                                .addGap(189, 189, 189)
                                .addComponent(labelUsuarioName))
                            .addGroup(panelBoard3Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)))
                .addGap(0, 32, Short.MAX_VALUE))
            .addComponent(labelPermissaoMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBoard3Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkPermissaoUsuarioEncontrarSomenteDados)
                    .addComponent(checkPermissaoUsuarioSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkPermissaoUsuarioEncontrarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkPermissaoUsuarioRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkPermissaoEstoqueSalva, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkPermissaoEstoqueRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkPermissaoEstoqueHistoricoTodo)
                    .addComponent(checkPermissaoEstoqueSomente, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
            .addGroup(panelBoard3Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(checkPermissaoProdutoSalva, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkPermissaoProdutoRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkPermissaoCategoriaSalva, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkPermissaoCategoriaRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72))
        );
        panelBoard3Layout.setVerticalGroup(
            panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoard3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labelUsuarioName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(labelPermissaoMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBoard3Layout.createSequentialGroup()
                        .addComponent(checkPermissaoUsuarioSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkPermissaoUsuarioEncontrarSomenteDados))
                    .addGroup(panelBoard3Layout.createSequentialGroup()
                        .addComponent(checkPermissaoEstoqueSalva)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkPermissaoEstoqueRemove)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkPermissaoUsuarioEncontrarTodos)
                    .addComponent(checkPermissaoEstoqueHistoricoTodo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkPermissaoUsuarioRemover)
                    .addComponent(checkPermissaoEstoqueSomente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13))
                .addGap(4, 4, 4)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBoard3Layout.createSequentialGroup()
                        .addComponent(checkPermissaoProdutoSalva)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkPermissaoProdutoRemove)
                        .addGap(18, 18, 18)
                        .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(checkPermissaoVendaSalva)
                            .addComponent(checkPermissaoSalva))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkPermissaoVendaAtualiza)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkPermissaoVendaRemove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(checkPermissaoClienteRemove)
                        .addGap(32, 32, 32)
                        .addComponent(botaoSalvaPermissao))
                    .addGroup(panelBoard3Layout.createSequentialGroup()
                        .addComponent(checkPermissaoCategoriaSalva)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkPermissaoCategoriaRemove)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dialogPermissaoLayout = new javax.swing.GroupLayout(dialogPermissao.getContentPane());
        dialogPermissao.getContentPane().setLayout(dialogPermissaoLayout);
        dialogPermissaoLayout.setHorizontalGroup(
            dialogPermissaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogPermissaoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelBoard3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        dialogPermissaoLayout.setVerticalGroup(
            dialogPermissaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogPermissaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBoard3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("> Usuário");

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
        botaoPermissao.setText("Permissão");
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

        tabelaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabelaUsuario);

        javax.swing.GroupLayout panelBoard1Layout = new javax.swing.GroupLayout(panelBoard1);
        panelBoard1.setLayout(panelBoard1Layout);
        panelBoard1Layout.setHorizontalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBoard1Layout.setVerticalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void checkPermissaoUsuarioSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPermissaoUsuarioSalvarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkPermissaoUsuarioSalvarActionPerformed

    private void botaoSalvaPermissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvaPermissaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoSalvaPermissaoActionPerformed

    private void checkPermissaoUsuarioEncontrarSomenteDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPermissaoUsuarioEncontrarSomenteDadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkPermissaoUsuarioEncontrarSomenteDadosActionPerformed

    private void checkPermissaoUsuarioEncontrarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPermissaoUsuarioEncontrarTodosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkPermissaoUsuarioEncontrarTodosActionPerformed

    private void checkPermissaoUsuarioRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPermissaoUsuarioRemoverActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_checkPermissaoUsuarioRemoverActionPerformed

    private void botaoPermissaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPermissaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoPermissaoActionPerformed

    private void botaoAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAdicionarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoAdicionarActionPerformed

    private void radioBotaoAtivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotaoAtivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioBotaoAtivoActionPerformed

    private void radioBotaoDesativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioBotaoDesativoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioBotaoDesativoActionPerformed

    private void checkPermissaoEstoqueSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPermissaoEstoqueSalvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkPermissaoEstoqueSalvaActionPerformed

    private void checkPermissaoEstoqueRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPermissaoEstoqueRemoveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkPermissaoEstoqueRemoveActionPerformed

    private void checkPermissaoEstoqueHistoricoTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPermissaoEstoqueHistoricoTodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkPermissaoEstoqueHistoricoTodoActionPerformed

    private void checkPermissaoEstoqueSomenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPermissaoEstoqueSomenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkPermissaoEstoqueSomenteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAdicionar;
    private javax.swing.JButton botaoAtualizar;
    private javax.swing.JButton botaoFoto;
    private javax.swing.JButton botaoImprimir;
    private javax.swing.JButton botaoPermissao;
    private javax.swing.JButton botaoRemover;
    private javax.swing.JButton botaoSalvaPermissao;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox checkPermissaoCategoriaRemove;
    private javax.swing.JCheckBox checkPermissaoCategoriaSalva;
    private javax.swing.JCheckBox checkPermissaoClienteRemove;
    private javax.swing.JCheckBox checkPermissaoEstoqueHistoricoTodo;
    private javax.swing.JCheckBox checkPermissaoEstoqueRemove;
    private javax.swing.JCheckBox checkPermissaoEstoqueSalva;
    private javax.swing.JCheckBox checkPermissaoEstoqueSomente;
    private javax.swing.JCheckBox checkPermissaoProdutoRemove;
    private javax.swing.JCheckBox checkPermissaoProdutoSalva;
    private javax.swing.JCheckBox checkPermissaoSalva;
    private javax.swing.JCheckBox checkPermissaoUsuarioEncontrarSomenteDados;
    private javax.swing.JCheckBox checkPermissaoUsuarioEncontrarTodos;
    private javax.swing.JCheckBox checkPermissaoUsuarioRemover;
    private javax.swing.JCheckBox checkPermissaoUsuarioSalvar;
    private javax.swing.JCheckBox checkPermissaoVendaAtualiza;
    private javax.swing.JCheckBox checkPermissaoVendaRemove;
    private javax.swing.JCheckBox checkPermissaoVendaSalva;
    private javax.swing.JComboBox<String> comboboxPerfil;
    private javax.swing.JDialog dialogCadastro;
    private javax.swing.JDialog dialogPermissao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel labelMensagem;
    private javax.swing.JLabel labelPermissaoMensagem;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelUsuarioName;
    private com.dxc.gestao.venda.visao.componentes.PanelBoard panelBoard1;
    private com.dxc.gestao.venda.visao.componentes.PanelBoard panelBoard2;
    private com.dxc.gestao.venda.visao.componentes.PanelBoard panelBoard3;
    private javax.swing.JRadioButton radioBotaoAtivo;
    private javax.swing.JRadioButton radioBotaoDesativo;
    private com.dxc.gestao.venda.visao.componentes.Tabela tabelaUsuario;
    private javax.swing.JTextField textoEmail;
    private javax.swing.JTextField textoFoto;
    private javax.swing.JTextField textoNome;
    private javax.swing.JPasswordField textoSenha;
    // End of variables declaration//GEN-END:variables
}
