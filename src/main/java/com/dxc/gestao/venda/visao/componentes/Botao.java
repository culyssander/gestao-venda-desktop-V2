package com.dxc.gestao.venda.visao.componentes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Botao extends JButton {
    
    private Animator animator;
    private int tamanhaDoAlvo;
    private float tamanhoDoAnimator;
    private Point ponto;
    private float alfa;
    private Color corDoEfeito = new Color(255, 255, 255);

    public Botao() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(5, 0, 5, 0));
        setBackground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                tamanhaDoAlvo = Math.max(getWidth(), getHeight()) * 2;
                tamanhoDoAnimator = 0;
                ponto = me.getPoint();
                alfa = 0.5f;
                if (animator.isRunning()) {
                    animator.stop();
                }
                animator.start();
            }
        });
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (fraction > 0.5f) {
                    alfa = 1 - fraction;
                }
                tamanhoDoAnimator = fraction * tamanhaDoAlvo;
                repaint();
            }
        };
        animator = new Animator(700, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, height, height);
        if (ponto != null) {
            g2.setColor(corDoEfeito);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alfa));
            g2.fillOval((int) (ponto.x - tamanhoDoAnimator / 2), (int) (ponto.y - tamanhoDoAnimator / 2), (int) tamanhoDoAnimator, (int) tamanhoDoAnimator);
        }
        g2.dispose();
        grphcs.drawImage(img, 0, 0, null);
        super.paintComponent(grphcs);
    }
    
}
