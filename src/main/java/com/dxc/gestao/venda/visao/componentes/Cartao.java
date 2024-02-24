
package com.dxc.gestao.venda.visao.componentes;

import com.dxc.gestao.venda.visao.modelo.CartaoModelo;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import javax.swing.JLabel;

public class Cartao extends javax.swing.JPanel {

    private Color color1;
    private Color color2;
 
    public Cartao() {
        initComponents();
        setOpaque(false);
        color1 = Color.BLACK;
        color2 = Color.WHITE;
    }
    
    public void setData(CartaoModelo cartaoModelo) {
        labelCartaoIcon.setIcon(cartaoModelo.getIcon());
        labelCartaoTitulo.setText(cartaoModelo.getTitulo());
        labelCartaoValor.setText(cartaoModelo.getValor());
    }

    public JLabel getLabelCartaoValor() {
        return labelCartaoValor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        
        graphics2D.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        GradientPaint gradientPaint = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        graphics2D.setPaint(gradientPaint);
        graphics2D.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        
        graphics2D.setColor(new Color(255, 255, 255, 50));
        graphics2D.fillOval(getWidth() - (getHeight() /2), 10, getHeight(), getHeight());
        graphics2D.fillOval(getWidth() - (getHeight() /2) - 20, getHeight() / 2 + 20, getHeight(), getHeight());
        
        super.paintComponent(g); 
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelCartaoIcon = new javax.swing.JLabel();
        labelCartaoTitulo = new javax.swing.JLabel();
        labelCartaoValor = new javax.swing.JLabel();

        labelCartaoTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelCartaoTitulo.setForeground(new java.awt.Color(255, 255, 255));
        labelCartaoTitulo.setText("Produto");

        labelCartaoValor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelCartaoValor.setForeground(new java.awt.Color(255, 255, 255));
        labelCartaoValor.setText("Total");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelCartaoValor)
                    .addComponent(labelCartaoTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelCartaoIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(labelCartaoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCartaoTitulo)
                .addGap(18, 18, 18)
                .addComponent(labelCartaoValor)
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelCartaoIcon;
    private javax.swing.JLabel labelCartaoTitulo;
    private javax.swing.JLabel labelCartaoValor;
    // End of variables declaration//GEN-END:variables

    
    
    
    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    
}
