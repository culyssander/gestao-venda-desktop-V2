package com.dxc.gestao.venda.visao.util;

import com.dxc.gestao.venda.visao.componentes.Mensagem;
import javax.swing.JLayeredPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MensagemUtil {
    
    private final JLayeredPane layeredPane;
    private final MigLayout layout;

    public MensagemUtil(JLayeredPane layeredPane, MigLayout layout) {
        this.layeredPane = layeredPane;
        this.layout = layout;
    }
    
    public void mostrarMensagem(Mensagem.TipoDeMensagem tipoDeMensagem, String mensagem) {
        Mensagem ms = new Mensagem();
        ms.mostrarMensagem(tipoDeMensagem, mensagem);

        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isMostrarMensagem()) {
                    layeredPane.add(ms, "pos 0.5al 20", 0); // adicionar no primeiro indice
                    ms.setVisible(true);
                    layeredPane.repaint();
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
                layeredPane.repaint();
                layeredPane.revalidate();
            }

            @Override
            public void end() {
                if (ms.isMostrarMensagem()) {
                    layeredPane.remove(ms);
                    layeredPane.repaint();
                    layeredPane.revalidate();
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
                () -> {
                    try {
                        Thread.sleep(2000);
                        animator.start();
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }).start();
    }

}
