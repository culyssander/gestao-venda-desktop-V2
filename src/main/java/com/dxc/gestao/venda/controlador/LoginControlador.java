
package com.dxc.gestao.venda.controlador;

import com.dxc.gestao.venda.modelo.dto.LoginResponse;
import com.dxc.gestao.venda.modelo.servico.LoginServico;
import com.dxc.gestao.venda.visao.componentes.Mensagem;
import com.dxc.gestao.venda.visao.formulario.Dashboard;
import com.dxc.gestao.venda.visao.formulario.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                login();
            }
        }
        
    }
    
    private void login() {
        validaCampos();
        String email = login.getCampoTextoLoginEmail().getText();
        String senha = String.valueOf(login.getCampoTextoLoginSenha().getPassword());
        LoginResponse loginResponse = loginServico.login(email, senha);
        if (loginResponse.isResposta()) {
            login.mostrMensagem(Mensagem.TipoDeMensagem.SUCESSO, "Login com sucesso!");
            login.getPanelLoading().setVisible(true);
            new Thread(
                    () -> {
                        try {
                            Thread.sleep(3000);
                            login.setVisible(false);
                            limpaTela();
                            new Dashboard(loginResponse.getUsuario()).setVisible(true);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(LoginControlador.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            ).start();
        } else {
            login.mostrMensagem(Mensagem.TipoDeMensagem.ERRO, "Email ou Senha invalida...");
        }
    }

    private void limpaTela() {
//        login.getLabelMensagem().setText("");
        login.getCampoTextoLoginEmail().setText("");
        login.getCampoTextoLoginSenha().setText("");
    }

    private void validaCampos() {
        String email = login.getCampoTextoLoginEmail().getText();
        String senha = login.getCampoTextoLoginSenha().getText();

        if (email.isBlank() || senha.isBlank()) {
            String mensagem = "O campo email e senha são obrigatorio...";
            login.mostrMensagem(Mensagem.TipoDeMensagem.ERRO, mensagem);
            throw new RuntimeException(mensagem);
        }
    }
    
}
