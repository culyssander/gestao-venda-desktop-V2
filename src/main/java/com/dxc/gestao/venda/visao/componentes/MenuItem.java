package com.dxc.gestao.venda.visao.componentes;

import com.dxc.gestao.venda.visao.modelo.MenuModelo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static com.dxc.gestao.venda.visao.modelo.MenuModelo.TipoMenu.*;
import java.awt.Font;

public class MenuItem extends javax.swing.JPanel {
    
    private boolean selected;
    private boolean over;

    public MenuItem(MenuModelo menuModelo) {
        initComponents();
        setOpaque(false);
        switch(menuModelo.getTipoMenu()) {
            case MENU -> {
                labelMenuItemIcon.setIcon(menuModelo.toIcon());
                labelMenuItem.setText(menuModelo.getNome());
            }
            case TITULO -> {
                labelMenuItemIcon.setText(menuModelo.getNome());
                labelMenuItemIcon.setFont(new Font("sansserif", 1, 12));
                labelMenuItem.setVisible(false);
            }
            case VAZIO -> {
                labelMenuItem.setText(" ");
            }
        }
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }

    public void setOver(boolean over) {
        this.over = over;
        repaint();
    }    

    @Override
    protected void paintComponent(Graphics g) {
        if (selected || over) {
            Graphics2D graphics2D = (Graphics2D) g;
        
            graphics2D.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
            
            if (selected) {
                graphics2D.setColor(new Color(255, 255, 255, 80));
            } else{
                graphics2D.setColor(new Color(255, 255, 255, 20));
            }
            
            graphics2D.fillRoundRect(10, 0, getWidth() - 20, getHeight(), 5, 5);
        }
        super.paintComponent(g); 
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelMenuItemIcon = new javax.swing.JLabel();
        labelMenuItem = new javax.swing.JLabel();

        setOpaque(false);

        labelMenuItemIcon.setForeground(new java.awt.Color(255, 255, 255));
        labelMenuItemIcon.setText(" ");

        labelMenuItem.setForeground(new java.awt.Color(255, 255, 255));
        labelMenuItem.setText("Menu item");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(labelMenuItemIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMenuItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelMenuItemIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addComponent(labelMenuItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelMenuItem;
    private javax.swing.JLabel labelMenuItemIcon;
    // End of variables declaration//GEN-END:variables
}
