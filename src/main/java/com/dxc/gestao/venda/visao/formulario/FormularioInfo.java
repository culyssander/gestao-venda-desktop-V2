package com.dxc.gestao.venda.visao.formulario;

import com.dxc.gestao.venda.visao.componentes.BotaoContorno;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class FormularioInfo extends javax.swing.JPanel {
    
//    private FormularioProduto formularioProduto;
    private final DecimalFormat decimalFormat = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private ActionListener actionListener;
    private final MigLayout layout;
    private JLabel titulo;
    private JLabel descricao;
    private BotaoContorno botaoContorno;
//    private BotaoContorno botaoVolta;
    private final JLabel volta;
    private boolean estaCategoria;

    public FormularioInfo(FormularioProduto formularioProduto) {
        initComponents();
        setOpaque(false);
        volta = new JLabel();
        volta.setOpaque(false);
        this.layout = new MigLayout("wrap, fill", "[center]", "push[]25[]10[]25[]push");
        setLayout(layout);
        inicializacao();
        
        volta.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                formularioProduto.getDashboard().setForm(formularioProduto);
            }
            
        });
    }
    
    private void inicializacao() {
        volta.setIcon(new ImageIcon(System.getProperty("user.dir") +"\\src\\main\\java\\com\\dxc\\gestao\\venda\\visao\\icon\\esquerda.png"));
        volta.setCursor(new Cursor(Cursor.HAND_CURSOR) {
        });
        add(volta, "w 40%"); 
        
        titulo = new JLabel("Categoria");
        titulo.setFont(new Font("sansserif", 1, 30));
        titulo.setForeground(new Color(245, 245, 245));
        add(titulo);
        
        descricao = new JLabel("Todas as informações");
        descricao.setForeground(new Color(245, 245, 245));
        add(descricao);
        
        botaoContorno = new BotaoContorno();
        botaoContorno.setBackground(new Color(255, 255, 255));
        botaoContorno.setForeground(new Color(255, 255, 255));
        botaoContorno.setText("CATEGORIA");
        botaoContorno.addActionListener(e -> actionListener.actionPerformed(e));
        add(botaoContorno, "w 40%, h 30");
        
//        botaoVolta = new BotaoContorno();
//        botaoVolta.setBackground(new Color(255, 255, 255));
//        botaoVolta.setForeground(new Color(255, 255, 255));
        
               
    }
    
    public void event(ActionListener e) {
        this.actionListener = e;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        
        GradientPaint gradientPaint = new GradientPaint(0, 0, Color.decode("#1CB5E0"), 0, getHeight(), Color.decode("#000046"));
        graphics2D.setPaint(gradientPaint);
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g); 
    }
    
    public void produtoEsquerda(double v) {
        v = Double.parseDouble(decimalFormat.format(v));
        categoria(false);
        layout.setComponentConstraints(titulo, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(descricao, "pad 0 -" + v + "% 0 0");
        
    }
    
    public void produtoDireita(double v) {
        v = Double.parseDouble(decimalFormat.format(v));
        categoria(false);
        layout.setComponentConstraints(titulo, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(descricao, "pad 0 -" + v + "% 0 0");
    }
    
    public void categoriaEsquerda(double v) {
        v = Double.parseDouble(decimalFormat.format(v));
        categoria(true);
        layout.setComponentConstraints(titulo, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(descricao, "pad 0 " + v + "% 0 " + v + "%");
    }
    
    public void categoriaDireita(double v) {
        v = Double.parseDouble(decimalFormat.format(v));
        categoria(true);
        layout.setComponentConstraints(titulo, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(descricao, "pad 0 " + v + "% 0 " + v + "%");
    }
    
    private void categoria(boolean categoria) {
        if (estaCategoria != categoria) {
            if (categoria) {
                titulo.setText("Produto");
                descricao.setText("Cadastro e alteração de produto");
                botaoContorno.setText("PRODUTO");
            } else {
                titulo.setText("Categoria");
                descricao.setText("Todas as informações");
                botaoContorno.setText("CATEGORIA");
            }
            this.estaCategoria = categoria;
        }
    }

    public JLabel getVolta() {
        return volta;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
