package com.dxc.gestao.venda.visao.componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import lombok.Getter;

@Getter
public class CampoDaSenha extends JPasswordField{
    
    private String dica;
    private Icon prefixoIcon;
    private Icon sufixoIcon;

    public CampoDaSenha() {
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(0, 0, 0, 0));
        setForeground(Color.decode("#1D1B86"));
        setFont(new Font("sansserif", 0, 13));
        setSelectionColor(new Color(75, 175, 152));
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(28, 181, 224, 80));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        paintIcon(g);
        super.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (getText().length() == 0) {
            int h = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            g.setColor(new Color(200, 200, 200));
            g.drawString(dica, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }

    private void paintIcon(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (prefixoIcon != null) {
            Image prefix = ((ImageIcon) prefixoIcon).getImage();
            int y = (getHeight() - prefixoIcon.getIconHeight()) / 2;
            g2.drawImage(prefix, 10, y, this);
        }
        if (sufixoIcon != null) {
            Image suffix = ((ImageIcon) sufixoIcon).getImage();
            int y = (getHeight() - sufixoIcon.getIconHeight()) / 2;
            g2.drawImage(suffix, getWidth() - sufixoIcon.getIconWidth() - 10, y, this);
        }
    }

    private void initBorder() {
        int esquerda = 15;
        int direito = 15;
        //  5 is default
        if (prefixoIcon != null) {
            esquerda = prefixoIcon.getIconWidth() + 15;
        }
        if (sufixoIcon != null) {
            direito = sufixoIcon.getIconWidth() + 15;
        }
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, esquerda, 10, direito));
    }
    
    public void setPrefixoIcon(Icon prefixoIcon) {
        this.prefixoIcon = prefixoIcon;
        initBorder();
    }
    
    public void setSufixoIcon(Icon sufixoIcon) {
        this.sufixoIcon = sufixoIcon;
        initBorder();
    }
    
    public void setDica(String dica) {
        this.dica = dica;
    }
}
