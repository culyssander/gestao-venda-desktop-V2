package com.dxc.gestao.venda.controlador;

import com.dxc.gestao.venda.visao.formulario.FormularioVenda;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FormularioVendaControlador implements ActionListener, KeyListener{
    
    private final FormularioVenda formularioVenda;

    public FormularioVendaControlador(FormularioVenda formularioVenda) {
        this.formularioVenda = formularioVenda;
    }
    
    private void adicionar() {
        formularioVenda.getjDialog().pack();
        formularioVenda.getjDialog().setLocationRelativeTo(null);
        formularioVenda.getjDialog().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand().toLowerCase();
        
        switch(action) {
            case "adicionar" -> {adicionar();}
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
    
    
    
}
