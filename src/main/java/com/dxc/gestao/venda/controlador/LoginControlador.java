
package com.dxc.gestao.venda.controlador;

import com.dxc.gestao.venda.modelo.dto.LoginResponse;
import com.dxc.gestao.venda.modelo.servico.LoginServico;
import com.dxc.gestao.venda.visao.formulario.Dashboard;
import com.dxc.gestao.venda.visao.formulario.Login;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginControlador implements ActionListener{
    
    private Login login;
    private LoginServico loginServico;

    public LoginControlador(Login login) {
        this.login = login;
        loginServico = new LoginServico();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch(e.getActionCommand().toLowerCase()) {
            case "login" -> {
                validaCampos();
                String email = login.getCampoTextoLoginEmail().getText();
                String senha = login.getCampoTextoLoginSenha().getText();
                LoginResponse loginResponse = loginServico.login(email, senha);
                if (loginResponse.isResposta()) {
                    new Dashboard(loginResponse.getUsuario()).setVisible(true);
                    login.setVisible(false);
                    limpaTela();
                } else
                    login.getLabelMensagem().setText("Email ou Senha invalida...");
            }
        }
        
    }
    
    private void limpaTela() {
        login.getLabelMensagem().setText("");
        login.getCampoTextoLoginEmail().setText("");
        login.getCampoTextoLoginSenha().setText("");
    }
    
    private void validaCampos() {
        String email = login.getCampoTextoLoginEmail().getText();
        String senha = login.getCampoTextoLoginSenha().getText();
        
        if (email.isBlank() || senha.isBlank()) {
            String mensagem = "O campo email e senha são obrigatorio...";
            login.getLabelMensagem().setText(mensagem);
            throw new RuntimeException(mensagem);
        }
    }
    
}
