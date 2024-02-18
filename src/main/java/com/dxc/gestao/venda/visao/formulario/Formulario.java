package com.dxc.gestao.venda.visao.formulario;

import com.dxc.gestao.venda.visao.componentes.Mensagem;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Formulario extends javax.swing.JPanel {
    
    private DecimalFormat decimalFormat = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private MigLayout layout;
    private FormularioInfo formularioInfo;
    private FormularioInfoProdutoCategoria produtoCategoria;
    private final double tamanhoInfo = 30;
    private final double tamanhoAdicional = 30;
    private final double tamanhoProduto = 67;
    private boolean estaCategoria;

    public Formulario(FormularioProduto formularioProduto) {
        initComponents();
        produtoCategoria = new FormularioInfoProdutoCategoria(formularioProduto);
        formularioInfo = new FormularioInfo(formularioProduto);
        inicializacao();
    }
    
    public void inicializacao() {
        layout = new MigLayout("fill, insets");
        
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
                    
                    if (fraction >= 0.5f) {
                        formularioInfo.produtoDireita(fractionInfo * 100);
                    } else {
                        formularioInfo.categoriaDireita(fractionProduto * 100);
                    }
                    
                } else {
                    fractionInfo = fraction;
                    fractionProduto = 1f - fraction;
                    
                    if (fraction <= 0.5f) {
                        formularioInfo.produtoEsquerda(fraction * 100);
                    } else {
                        formularioInfo.categoriaEsquerda((1f - fraction) * 100);
                    }
                }
                
                if (fraction >= 0.5f) {
                    produtoCategoria.mostrarProduto(estaCategoria);
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
        
        Animator animator = new Animator(800, target);
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

    public FormularioInfo getFormularioInfo() {
        return formularioInfo;
    }

    public FormularioInfoProdutoCategoria getProdutoCategoria() {
        return produtoCategoria;
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
