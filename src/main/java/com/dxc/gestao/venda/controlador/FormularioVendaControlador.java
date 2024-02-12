package com.dxc.gestao.venda.controlador;

import com.dxc.gestao.venda.visao.formulario.FormularioVenda;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FormularioVendaControlador implements ActionListener, KeyListener{
    
    private FormularioVenda formularioVenda;

    public FormularioVendaControlador(FormularioVenda formularioVenda) {
        this.formularioVenda = formularioVenda;
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
    
    
    
}
