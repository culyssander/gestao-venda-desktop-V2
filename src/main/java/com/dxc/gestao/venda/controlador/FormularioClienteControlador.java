package com.dxc.gestao.venda.controlador;

import com.dxc.gestao.venda.visao.formulario.FormularioCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FormularioClienteControlador implements ActionListener, KeyListener{
    
    private FormularioCliente formularioCliente;

    public FormularioClienteControlador(FormularioCliente formularioCliente) {
        this.formularioCliente = formularioCliente;
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
