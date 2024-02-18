package com.dxc.gestao.venda.visao.componentes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class Mensagem extends javax.swing.JPanel {
    
    private TipoDeMensagem tipoDeMensagem = TipoDeMensagem.SUCESSO;
    private boolean mostrarMensagem;
    
    public Mensagem() {
        initComponents();
        setOpaque(false);
        setVisible(false);
    }
    
    public enum TipoDeMensagem {
        SUCESSO, ERRO
    }

    public void setMostrarMensagem(boolean mostrarMensagem) {
        this.mostrarMensagem = mostrarMensagem;
    }

    public boolean isMostrarMensagem() {
        return mostrarMensagem;
    }
    
    public void mostrarMensagem(TipoDeMensagem tipoDeMensagem, String mensagem) {
        this.tipoDeMensagem = tipoDeMensagem;
        labelMensagem.setText(mensagem);
        
        if (tipoDeMensagem.equals(TipoDeMensagem.SUCESSO)) {
            labelMensagem.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\sucesso.png"));
        } else {
            labelMensagem.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\erro.png"));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        
        if (tipoDeMensagem == TipoDeMensagem.SUCESSO) {
            graphics2D.setColor(new Color(15,174,37));
        } else {
            graphics2D.setColor(new Color(240,52,53));
        }
        
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        graphics2D.setComposite(AlphaComposite.SrcOver);
        graphics2D.setColor(new Color(245, 245, 245));
        graphics2D.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        super.paintComponent(g); 
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelMensagem = new javax.swing.JLabel();

        labelMensagem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelMensagem.setForeground(new java.awt.Color(255, 255, 255));
        labelMensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMensagem.setText("Mensagem");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelMensagem;
    // End of variables declaration//GEN-END:variables
}
