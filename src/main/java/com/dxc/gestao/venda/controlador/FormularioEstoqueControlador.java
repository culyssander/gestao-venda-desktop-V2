package com.dxc.gestao.venda.controlador;

import com.dxc.gestao.venda.visao.formulario.FormularioEstoque;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FormularioEstoqueControlador implements ActionListener, KeyListener{
    
    private FormularioEstoque formularioEstoque;

    public FormularioEstoqueControlador(FormularioEstoque formularioEstoque) {
        this.formularioEstoque = formularioEstoque;
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
