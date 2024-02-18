package com.dxc.gestao.venda.visao.formulario;

import com.dxc.gestao.venda.controlador.DashboardControlador;
import com.dxc.gestao.venda.modelo.entidade.Usuario;
import com.dxc.gestao.venda.visao.componentes.Cabecalho;
import com.dxc.gestao.venda.visao.componentes.Menu;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

public class Dashboard extends javax.swing.JFrame {
    private DashboardControlador dashboardControlador;
//    private FormularioProdutoRegistra formularioProdutoRegistra;
    private Formulario formulario;
    private FormularioEstoqueRegistra formularioEstoqueRegistra;
    private FormularioProduto formularioProduto;
    private FormularioEstoque formularioEstoque;
    private FormularioCliente formularioCliente;
    private FormularioVenda formularioVenda;
    private FormularioPrincipal formularioPrincipal;
    private FormularioUsuario formularioUsuario;
    private Usuario usuario;
    private int menuSelectionadoIndex = 0;

    public Dashboard(Usuario usuario) {
        initComponents();
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
        inicializaFormulario(usuario.getId(), cabecalho1, this);
        menu1.addEventoMenuSelecionado(e -> {
            menuSelectionadoIndex = e;
            formularioUsuario.setMenuSelectionadoIndex(e);
            switch (e) {
                case 0 -> { setForm(formularioPrincipal); }
                case 1 -> { setForm(formularioProduto); }
                case 2 -> { setForm(formularioEstoque); }
                case 3 -> { setForm(formularioCliente); }
                case 4 -> { setForm(formularioVenda); }
                case 5 -> { setForm(formularioUsuario); }
                case 6 -> { setForm(formularioEstoqueRegistra); }
            }
        });
        
        setForm(formularioPrincipal);
        if (usuario == null) 
            throw new RuntimeException("Usuario deve estar logado");
        
        this.usuario = usuario;
        menu1.inicializar(usuario);
        dashboardControlador = new DashboardControlador(this);
    }
    
    private void inicializaFormulario(Long usuarioId, Cabecalho cabecalho, Dashboard dashboard) {
        formularioProduto = new FormularioProduto(usuarioId, cabecalho, dashboard);
        formularioEstoque = new FormularioEstoque(usuarioId, cabecalho);
        formularioCliente = new FormularioCliente(usuarioId, cabecalho);
        formularioVenda = new FormularioVenda(usuarioId, cabecalho);
        formularioPrincipal = new FormularioPrincipal();
        formularioUsuario = new FormularioUsuario(usuarioId, cabecalho);
        
//        formularioProdutoRegistra = new FormularioProdutoRegistra();
        formularioEstoqueRegistra = new FormularioEstoqueRegistra();
        formulario = new Formulario(formularioProduto);
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setForm(JComponent component) {
        panelPrincipal.removeAll();
        panelPrincipal.add(component);
        panelPrincipal.repaint();
        panelPrincipal.revalidate();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Menu getMenu() {
        return menu1;
    }

    public FormularioUsuario getFormularioUsuario() {
        return formularioUsuario;
    }

    public Cabecalho getCabecalho() {
        return cabecalho1;
    }

    public int getMenuSelectionadoIndex() {
        return menuSelectionadoIndex;
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBoard1 = new com.dxc.gestao.venda.visao.componentes.PanelBoard();
        menu1 = new com.dxc.gestao.venda.visao.componentes.Menu();
        cabecalho1 = new com.dxc.gestao.venda.visao.componentes.Cabecalho();
        panelPrincipal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelPrincipal.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBoard1Layout = new javax.swing.GroupLayout(panelBoard1);
        panelBoard1.setLayout(panelBoard1Layout);
        panelBoard1Layout.setHorizontalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBoard1Layout.createSequentialGroup()
                        .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panelBoard1Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(cabecalho1, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE))))
        );
        panelBoard1Layout.setVerticalGroup(
            panelBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
            .addGroup(panelBoard1Layout.createSequentialGroup()
                .addComponent(cabecalho1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBoard1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBoard1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.dxc.gestao.venda.visao.componentes.Cabecalho cabecalho1;
    private com.dxc.gestao.venda.visao.componentes.Menu menu1;
    private com.dxc.gestao.venda.visao.componentes.PanelBoard panelBoard1;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables
}
