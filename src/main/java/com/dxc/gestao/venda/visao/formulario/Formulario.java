package com.dxc.gestao.venda.visao.formulario;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Formulario extends javax.swing.JPanel {
    
    DecimalFormat decimalFormat = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private MigLayout layout;
    private FormularioInfo formularioInfo;
    private FormularioInfoProdutoCategoria produtoCategoria;
    private final double tamanhoInfo = 30;
    private final double tamanhoAdicional = 30;
    private final double tamanhoProduto = 67;
    private boolean estaCategoria;

    public Formulario() {
        initComponents();
        inicializacao();
    }
    
    public void inicializacao() {
        layout = new MigLayout("fill, insets");
        formularioInfo = new FormularioInfo();
        produtoCategoria = new FormularioInfoProdutoCategoria();
        
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionInfo;
                double fractionProduto;
                double tamanho = tamanhoAdicional;
                double tamanhoProd = tamanhoProduto;
                
                if (fraction <= 0.5f) {
                    tamanho += fraction * tamanhoAdicional;
                } else {
                    tamanho += tamanho - fraction * tamanhoAdicional;
                }
                
                if (estaCategoria) {
                    fractionInfo = 1f - fraction;
                    fractionProduto = fraction;
                    
                } else {
                    fractionInfo = fraction;
                    fractionProduto = 1f - fraction;
                }
                
                fractionInfo = Double.parseDouble(decimalFormat.format(fractionInfo));
                fractionProduto = Double.parseDouble(decimalFormat.format(fractionProduto));
                
                layout.setComponentConstraints(formularioInfo, "width " + tamanho + "%, pos " + fractionInfo +"al 0 n 100%");
                layout.setComponentConstraints(produtoCategoria, "width " + tamanhoProd + "%, pos " + fractionProduto + "al 0 n 100%");
                background.revalidate();
            }

            @Override
            public void end() {
                estaCategoria = !estaCategoria;
            }
            
        };
        
        Animator animator = new Animator(1000, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
        
        background.setLayout(layout);
        background.add(formularioInfo, "width " + tamanhoInfo + "%, pos 0al 0 n 100%");
        background.add(produtoCategoria, "width " + tamanhoProduto + "%, pos 1al 0 n 100%");
        
        formularioInfo.event(e -> {
            if (!animator.isRunning()) {
                animator.start();
            }
        });
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JLayeredPane();

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 772, Short.MAX_VALUE)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 589, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane background;
    // End of variables declaration//GEN-END:variables
}
