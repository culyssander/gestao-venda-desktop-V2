package com.dxc.gestao.venda.visao.formulario;

import com.dxc.gestao.venda.controlador.LoginControlador;
import com.dxc.gestao.venda.visao.componentes.Mensagem;
import com.dxc.gestao.venda.visao.componentes.PanelBoard;
import com.dxc.gestao.venda.visao.componentes.PanelLoading;
import com.dxc.gestao.venda.visao.util.MensagemUtil;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Login extends javax.swing.JFrame {
    
    private int x;
    private int y;
    private LoginControlador loginControlador;
    private MigLayout layout;
    private PanelLoading loading;
    private MensagemUtil mostrarMensagem;

    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
        loginControlador = new LoginControlador(this);
        moveTelaLogin(this);
        
        layout = new MigLayout("fill, insets");
        loading = new PanelLoading();
        background.setLayout(layout);
        background.add(loading, "pos 0 0 100% 100%");
        background.add(panelBoard3, "pos 0 0 100% 100%");
        mostrarMensagem = new MensagemUtil(background, layout);
        fechaTela();
        evento();
    }

    public MensagemUtil getMostrarMensagem() {
        return mostrarMensagem;
    }
    
//    public void mostrMensagem(Mensagem.TipoDeMensagem tipoDeMensagem, String mensagem) {
//        mostrarMensagem.mostrarMensagem(tipoDeMensagem, mensagem);
//    }

    /*
    public void mostrMensagem(Mensagem.TipoDeMensagem tipoDeMensagem, String mensagem) {
        Mensagem ms = new Mensagem();
        ms.mostrarMensagem(tipoDeMensagem, mensagem);
        
        
        
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isMostrarMensagem()) {
                    background.add(ms, "pos 0.5al 20", 0); // adicionar no primeiro indice
                    ms.setVisible(true);
                    background.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                
                if (ms.isMostrarMensagem()) {
                    f = 20 * (1f - fraction);
                } else {
                    f = 20 * fraction;
                }

                layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 20));
                background.repaint();
                background.revalidate();
            }

            @Override
            public void end() {
                if (ms.isMostrarMensagem()) {
                    background.remove(ms);
                    background.repaint();
                    background.revalidate();
                } else {
                    ms.setMostrarMensagem(true);
                }
            }
        };
        
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        
        new Thread(
                () ->{
                    try {
                        Thread.sleep(2000);
                        animator.start();
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }).start();
    }
 */   
    private void moveTelaLogin(JFrame frame) {
        panelMovimento.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = getX();
                y = getY();
            }
        });
        
        panelMovimento.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e) {
                
                frame.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
            }
        });
    }
    
    private void evento() {
        botaoLogin1.addActionListener(loginControlador);
    }

    public PanelLoading getPanelLoading() {
        return loading;
    }

    public PanelBoard getPanelBoard2() {
        return panelBoard2;
    }
    
    private void fechaTela() {
        labelFechar1.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                int opcao = JOptionPane.showConfirmDialog(null, "Tens certeza?", "Sair", JOptionPane.YES_NO_OPTION);
                if (opcao == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });
        
        labelFechar1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                labelFechar1.setBackground(Color.RED);
                labelFechar1.setForeground(Color.WHITE);
                labelFechar1.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                labelFechar1.setOpaque(false);
                labelFechar1.setForeground(Color.BLACK);
            }
         });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBoard2 = new com.dxc.gestao.venda.visao.componentes.PanelBoard();
        panelMovimento = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        labelFechar = new javax.swing.JLabel();
        labelMensagem = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        campoTextoLoginEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        campoTextoLoginSenha = new javax.swing.JPasswordField();
        botaoLogin = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        panelBoard3 = new com.dxc.gestao.venda.visao.componentes.PanelBoard();
        panelMovimento1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        labelFechar1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        botaoLogin1 = new com.dxc.gestao.venda.visao.componentes.Botao();
        campoDeTextoEmail = new com.dxc.gestao.venda.visao.componentes.CampoDeTexto();
        campoDaSenha = new com.dxc.gestao.venda.visao.componentes.CampoDaSenha();
        background = new javax.swing.JLayeredPane();

        panelBoard2.setColor1(new java.awt.Color(92, 37, 141));
        panelBoard2.setColor2(new java.awt.Color(67, 137, 162));

        panelMovimento.setBackground(new java.awt.Color(255, 255, 255));
        panelMovimento.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-venda\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\logo 39x45.png")); // NOI18N
        jLabel3.setText("SOFT");
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        labelFechar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelFechar.setForeground(new java.awt.Color(255, 255, 255));
        labelFechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFechar.setText("X");

        javax.swing.GroupLayout panelMovimentoLayout = new javax.swing.GroupLayout(panelMovimento);
        panelMovimento.setLayout(panelMovimentoLayout);
        panelMovimentoLayout.setHorizontalGroup(
            panelMovimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovimentoLayout.createSequentialGroup()
                .addGap(0, 53, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelMovimentoLayout.setVerticalGroup(
            panelMovimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovimentoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelMovimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)))
        );

        labelMensagem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelMensagem.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Email:");

        campoTextoLoginEmail.setForeground(new java.awt.Color(0, 0, 70));
        campoTextoLoginEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoLoginEmailActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Senha:");

        campoTextoLoginSenha.setForeground(new java.awt.Color(0, 0, 70));

        botaoLogin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        botaoLogin.setForeground(new java.awt.Color(0, 0, 70));
        botaoLogin.setIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-venda\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\login_16.png")); // NOI18N
        botaoLogin.setText("Login");
        botaoLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBoard2Layout = new javax.swing.GroupLayout(panelBoard2);
        panelBoard2.setLayout(panelBoard2Layout);
        panelBoard2Layout.setHorizontalGroup(
            panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelMensagem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelMovimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBoard2Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botaoLogin)
                    .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelBoard2Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(campoTextoLoginSenha))
                        .addGroup(panelBoard2Layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(campoTextoLoginEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        panelBoard2Layout.setVerticalGroup(
            panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelMovimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(campoTextoLoginEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBoard2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(campoTextoLoginSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoLogin)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        panelBoard3.setColor1(new java.awt.Color(255, 255, 255));
        panelBoard3.setColor2(new java.awt.Color(67, 137, 162));

        panelMovimento1.setBackground(new java.awt.Color(255, 255, 255));
        panelMovimento1.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-venda\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\logo 39x45.png")); // NOI18N
        jLabel6.setText("SOFT");
        jLabel6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        labelFechar1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelFechar1.setForeground(new java.awt.Color(255, 255, 255));
        labelFechar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFechar1.setText("X");

        javax.swing.GroupLayout panelMovimento1Layout = new javax.swing.GroupLayout(panelMovimento1);
        panelMovimento1.setLayout(panelMovimento1Layout);
        panelMovimento1Layout.setHorizontalGroup(
            panelMovimento1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovimento1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(labelFechar1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelMovimento1Layout.setVerticalGroup(
            panelMovimento1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovimento1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelMovimento1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addGroup(panelMovimento1Layout.createSequentialGroup()
                        .addComponent(labelFechar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        botaoLogin1.setBackground(new java.awt.Color(28, 181, 224));
        botaoLogin1.setForeground(new java.awt.Color(255, 255, 255));
        botaoLogin1.setIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-venda\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\login_16.png")); // NOI18N
        botaoLogin1.setText("LOGIN");
        botaoLogin1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        campoDeTextoEmail.setColor(new java.awt.Color(255, 255, 255));
        campoDeTextoEmail.setDica("Email");
        campoDeTextoEmail.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-venda\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\mail.png")); // NOI18N

        campoDaSenha.setColor(new java.awt.Color(255, 255, 255));
        campoDaSenha.setDica("Senha");
        campoDaSenha.setPrefixoIcon(new javax.swing.ImageIcon("C:\\Users\\qculissander\\netbeans-desktop\\gestao-venda\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\pass.png")); // NOI18N

        javax.swing.GroupLayout panelBoard3Layout = new javax.swing.GroupLayout(panelBoard3);
        panelBoard3.setLayout(panelBoard3Layout);
        panelBoard3Layout.setHorizontalGroup(
            panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMovimento1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoard3Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addGroup(panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoDeTextoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoDaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
            .addGroup(panelBoard3Layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(botaoLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBoard3Layout.setVerticalGroup(
            panelBoard3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelMovimento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(campoDeTextoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoDaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 411, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 291, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoLoginActionPerformed

    private void campoTextoLoginEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoLoginEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoLoginEmailActionPerformed


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane background;
    private javax.swing.JButton botaoLogin;
    private com.dxc.gestao.venda.visao.componentes.Botao botaoLogin1;
    private com.dxc.gestao.venda.visao.componentes.CampoDaSenha campoDaSenha;
    private com.dxc.gestao.venda.visao.componentes.CampoDeTexto campoDeTextoEmail;
    private javax.swing.JTextField campoTextoLoginEmail;
    private javax.swing.JPasswordField campoTextoLoginSenha;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelFechar;
    private javax.swing.JLabel labelFechar1;
    private javax.swing.JLabel labelMensagem;
    private com.dxc.gestao.venda.visao.componentes.PanelBoard panelBoard2;
    private com.dxc.gestao.venda.visao.componentes.PanelBoard panelBoard3;
    private javax.swing.JPanel panelMovimento;
    private javax.swing.JPanel panelMovimento1;
    // End of variables declaration//GEN-END:variables

    public JButton getBotaoLogin() {
        return botaoLogin1;
    }

    public JTextField getCampoTextoLoginEmail() {
        return campoDeTextoEmail;
    }

    public JPasswordField getCampoTextoLoginSenha() {
        return campoDaSenha;
    }
}
