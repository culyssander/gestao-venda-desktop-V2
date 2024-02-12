package com.dxc.gestao.venda.controlador;

import com.dxc.gestao.venda.modelo.entidade.Permissao;
import com.dxc.gestao.venda.modelo.entidade.PermissaoUsuario;
import com.dxc.gestao.venda.modelo.entidade.Usuario;
import com.dxc.gestao.venda.modelo.servico.PermissaoServico;
import com.dxc.gestao.venda.modelo.servico.UsuarioServico;
import com.dxc.gestao.venda.modelo.tabela.modelo.UsuarioModelo;
import com.dxc.gestao.venda.visao.componentes.Cabecalho;
import com.dxc.gestao.venda.visao.formulario.FormularioUsuario;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public final class FormularioUsuarioControlador implements ActionListener, KeyListener {
    
    private final FormularioUsuario formularioUsuario;
    private final PermissaoServico permissaoServico;
    private final UsuarioServico usuarioServico;
    private UsuarioModelo usuarioModelo;
    private List<Usuario> usuarios;
    private Long usuarioParaAtualizar;
    private final Long usuarioLogado;
    private String urlFoto;
    private final long PERMISSAO_ID_PARA_SALVAR_USUARIO = 1;
    private final long PERMISSAO_ID_PARA_ENCONTRAR_SOMENTE_TEUS_USUARIO = 3;
    private final long PERMISSAO_ID_PARA_ENCONTRAR_TODOS_USUARIO = 4;
    private final long PERMISSAO_ID_PARA_REMOVE_PELO_ID_USUARIO = 5;
    private final long PERMISSAO_ID_PARA_SALVAR_PERMISSAO = 20;

    public FormularioUsuarioControlador(FormularioUsuario formularioUsuario) {
        this.formularioUsuario = formularioUsuario;
        usuarioServico = new UsuarioServico();
        permissaoServico = new PermissaoServico();
        usuarioLogado = formularioUsuario.getUsuarioId();
        actualizarTabela();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand().toLowerCase();
        switch (action) {
            case "adicionar" -> { mostrarTelaCadastra("ADICIONAR USUARIO"); }
            case "atualizar" -> { atualizar(); }
            case "remover" -> { remover(); }
            case "imprimir" -> {}
            case "permissao" -> {mostrarTelaPermissao();}
            case "permissaosalvar" -> {permissaoSalvar();}
            case "salvar" -> { salvar(); }
            case "seleciona" -> {selecionaArquivo(); }
        }
    }
    
    private void actualizarTabela() {
        if (permissaoServico.validaPermissoes(usuarioLogado, PERMISSAO_ID_PARA_ENCONTRAR_TODOS_USUARIO)) {
            usuarios = usuarioServico.encontrarTodos();
        } else if (permissaoServico.validaPermissoes(usuarioLogado, PERMISSAO_ID_PARA_ENCONTRAR_SOMENTE_TEUS_USUARIO)) {
            usuarios = List.of(usuarioServico.encontrarPeloId(usuarioLogado));
        } else {
            usuarios = new ArrayList<>();
        }
        
        preencherValoresNaTabela();
    }
    
    private void preencherValoresNaTabela() {
        usuarioModelo = new UsuarioModelo(this.usuarios);
        formularioUsuario.getTabelaUsuario().setModel(usuarioModelo);
    }
    
    public void pesquisa(String texto) {
        if (texto.isEmpty()) {
            this.usuarios = usuarioServico.encontrarTodos();
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("nome", texto);
            map.put("email", texto);
            map.put("perfil", texto);
            this.usuarios = usuarioServico.encontrarPeloAtributo(map);
        }
        
        preencherValoresNaTabela();
    }
    
    private void mostrarTelaCadastra(String titulo) {
        permissaoServico.validaPermissao(usuarioLogado, PERMISSAO_ID_PARA_SALVAR_USUARIO);
        formularioUsuario.getLabelTitulo().setText(titulo);
        formularioUsuario.getDialogCadastro().pack();
        formularioUsuario.getDialogCadastro().setLocationRelativeTo(null);
        formularioUsuario.getDialogCadastro().setVisible(true);
        corDeMensagemNeutro();
        limpa();
    }
    
    private void validFormulario(String texto) {
        if (texto == null || texto.isBlank()) {
            String mensagem  = "Todos os campos são obrigatorios";
            formularioUsuario.getLabelMensagem().setText(mensagem);
            corDeMensagemDeErro();
            throw new RuntimeException(mensagem);
        }
    }
    
    private void validaComboBox() {
        if (formularioUsuario.getComboboxPerfil().getSelectedIndex() < 1) {
            String mensagem  = "Perfil é obrigatorios";
            formularioUsuario.getLabelMensagem().setText(mensagem);
            corDeMensagemDeErro();
            throw new RuntimeException(mensagem);
        }
    }
    
    private void salvar() {
        String nome = formularioUsuario.getTextoNome().getText();
        String email = formularioUsuario.getTextoEmail().getText();
        String senha = formularioUsuario.getTextoSenha().getText();
        String perfil = formularioUsuario.getComboboxPerfil().getSelectedItem().toString();
        
        validFormulario(nome);
        validFormulario(email);
        validFormulario(senha);
        validaComboBox();
        
        try {
            Usuario usuario = Usuario.builder()
                    .id(usuarioParaAtualizar)
                    .nome(nome)
                    .email(email)
                    .senha(senha)
                    .perfil(perfil)
                    .urlFoto(urlFoto)
                    .estado(valorDoRadioBotao())
                    .dataCriacao(LocalDateTime.now())
                    .build();
            
            String mensagem = usuarioServico.salvar(usuario);
            formularioUsuario.getLabelMensagem().setText(mensagem);
            corDeMensagemDeSucesso();
            actualizarTabela();
            
            if (usuarioLogado.equals(usuarioParaAtualizar)) {
                DashboardControlador.inicializarMenu(usuario);
            }
            limpa();
        } catch (Exception e) {
            corDeMensagemDeErro();
            formularioUsuario.getLabelMensagem().setText(e.getMessage());
        }
    }
    
    private void selecionaArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showDialog(null, "Selectiona");
        
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile().getAbsoluteFile();
            urlFoto = file.getAbsolutePath();
            formularioUsuario.getTextoFoto().setText(urlFoto);
        }
    }
    
    private void corDeMensagemDeErro() {
        formularioUsuario.getLabelMensagem().setBackground(Color.decode("#93291E"));
        formularioUsuario.getLabelMensagem().setOpaque(true);
        formularioUsuario.getLabelMensagem().setForeground(Color.WHITE);
    }
    
    private void corDeMensagemDeSucesso() {
        formularioUsuario.getLabelMensagem().setBackground(Color.decode("#45B649"));
        formularioUsuario.getLabelMensagem().setOpaque(true);
        formularioUsuario.getLabelMensagem().setForeground(Color.WHITE);
    }
    
    private void corDeMensagemNeutro() {
        formularioUsuario.getLabelMensagem().setBackground(Color.WHITE);
        formularioUsuario.getLabelMensagem().setOpaque(false);
        formularioUsuario.getLabelMensagem().setForeground(Color.WHITE);
    }
    
    private boolean valorDoRadioBotao() {
        return formularioUsuario.getRadioBotaoAtivo().isSelected();
    }
    
    private void limpa() {
        formularioUsuario.getTextoNome().setText("");
        formularioUsuario.getTextoEmail().setText("");
        formularioUsuario.getTextoSenha().setText("");
        formularioUsuario.getTextoFoto().setText("");
        formularioUsuario.getComboboxPerfil().setSelectedIndex(0);
        formularioUsuario.getRadioBotaoAtivo().setSelected(true);
        usuarioParaAtualizar = null;
        urlFoto = null;
    }
    
    private void preencher(Usuario usuario) {
        usuarioParaAtualizar = usuario.getId();
        formularioUsuario.getTextoNome().setText(usuario.getNome());
        formularioUsuario.getTextoEmail().setText(usuario.getEmail());
        formularioUsuario.getTextoSenha().setText("");
        formularioUsuario.getTextoFoto().setText(usuario.getUrlFoto());
        
        if (usuario.getPerfil().toLowerCase().startsWith("admin")) {
            formularioUsuario.getComboboxPerfil().setSelectedIndex(1);
        } else {
            formularioUsuario.getComboboxPerfil().setSelectedIndex(2);
        }
        
        if (usuario.getEstado()) {
            formularioUsuario.getRadioBotaoAtivo().setSelected(true);
        } else {
            formularioUsuario.getRadioBotaoDesativo().setSelected(true);
        }
    }
    
    private void atualizar() {
        permissaoServico.validaPermissao(usuarioLogado, PERMISSAO_ID_PARA_SALVAR_USUARIO);
        Usuario usuario = selectionaUsuarioNaTabela();
        mostrarTelaCadastra("ATUALIZAR USUARIO");
        preencher(usuario);
    }
    
    private void remover() {
        permissaoServico.validaPermissao(usuarioLogado, PERMISSAO_ID_PARA_REMOVE_PELO_ID_USUARIO);
        Usuario usuario = selectionaUsuarioNaTabela();
        
        if (usuario.getId().equals(usuarioLogado)) {
            String mensagem = "Não é permitido remover usuário logado";
            JOptionPane.showMessageDialog(null, mensagem, 
                    "Remover usuario", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(mensagem);
        }
        try {
            int confirma = JOptionPane.showConfirmDialog(null, 
                    "ID: " + usuario.getId() +
                    "\nNome: " + usuario.getNome() +
                    "\nEmail: " + usuario.getEmail() +
                    "\nPerfil: " + usuario.getPerfil()
                    , "Remove usuario", JOptionPane.YES_NO_OPTION);

            if (confirma == JOptionPane.YES_OPTION) {
                boolean result = usuarioServico.remover(usuario.getId());

                if (result) {
                    JOptionPane.showMessageDialog(null, "Usuário removido!");
                    actualizarTabela();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Usuario já tem atividade no sistema", 
                    "Remover usuario", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarTelaPermissao() {
        permissaoServico.validaPermissao(usuarioLogado, PERMISSAO_ID_PARA_SALVAR_PERMISSAO);
        limpaPermissao();
        Usuario usuario = selectionaUsuarioNaTabela();
        formularioUsuario.getDialogPermissao().pack();
        formularioUsuario.getDialogPermissao().setLocationRelativeTo(null);
        formularioUsuario.getDialogPermissao().setVisible(true);
        formularioUsuario.getLabelUsuarioName().setText(usuario.getNome());
        
        List<Permissao> permissoes = permissaoServico.encontrarTodosPeloUsuario(usuario.getId());
        preencherPermissao(permissoes);
        usuarioParaAtualizar = usuario.getId();
    }
    
    private void preencherPermissao(List<Permissao> permissoes) {
        List<JCheckBox> checkBoxs = formularioUsuario.allCheckBox();
        
        Map<String, JCheckBox> map = new HashMap<>();
        
        for(JCheckBox checkBox : checkBoxs) {
            map.put(checkBox.getName(), checkBox);
        }
        
        permissoes.stream()
                .forEach(p -> {
                    JCheckBox checkBox = map.get(p.getNome());                    
                    if (checkBox != null) {
                        checkBox.setSelected(true);
                    }
                });
        
    }
    
    private void limpaPermissao() {
        List<JCheckBox> checkBoxs = formularioUsuario.allCheckBox();
        
        for(JCheckBox checkBox : checkBoxs) {
            checkBox.setSelected(false);
        }
        
        formularioUsuario.getLabelPermissaoMensagem().setOpaque(false);
    }
    
    private Usuario selectionaUsuarioNaTabela() {        
        if (formularioUsuario.getTabelaUsuario().getSelectedRow() != -1) {
            int index = formularioUsuario.getTabelaUsuario().getSelectedRow();
            return usuarios.get(index);
        } else {
            String message = "É necessario seleciona usuario na tabela";
            JOptionPane.showMessageDialog(null, message);
            throw new RuntimeException(message);
        }
    }
    
    private void permissaoSalvar() {
        Long usuarioId = usuarioParaAtualizar;
        List<JCheckBox> checkBoxs = formularioUsuario.allCheckBox();

        List<PermissaoUsuario> adicionaPermissao = new ArrayList<>();
        List<PermissaoUsuario> removePermissao = new ArrayList<>();

        for (JCheckBox checkBox : checkBoxs) {
            String nome = checkBox.getName();

            List<Permissao> permissoes = permissaoServico.encontrarPeloNome(nome);

            if (!permissoes.isEmpty()) {
                Permissao permissao = permissoes.get(0);
                PermissaoUsuario permissaoUsuario = PermissaoUsuario.builder()
                        .permissaoId(permissao.getId())
                        .usuarioId(usuarioId)
                        .build();

                if (checkBox.isSelected()) {
                    adicionaPermissao.add(permissaoUsuario);
                } else {
                    removePermissao.add(permissaoUsuario);
                }
            }
        }

        var filtroSalva = adicionaPermissao.stream()
                .filter(pu -> !permissaoServico.validaPermissoes(pu.getUsuarioId(), pu.getPermissaoId()))
                .collect(Collectors.toList());

        if (!filtroSalva.isEmpty()) {
            permissaoServico.salvaPermissao(filtroSalva);
        }

        removePermissao.stream()
                .filter(pu -> permissaoServico.validaPermissoes(pu.getUsuarioId(), pu.getPermissaoId()))
                .forEach(pu -> permissaoServico.delete(pu.getUsuarioId(), pu.getPermissaoId()));

        mensagemDeSucessoDaPermissao();
    }
    
    private void mensagemDeSucessoDaPermissao() {
        formularioUsuario.getLabelPermissaoMensagem().setText("Permissão alterado com sucesso!");
        formularioUsuario.getLabelPermissaoMensagem().setBackground(Color.decode("#45B649"));
        formularioUsuario.getLabelPermissaoMensagem().setForeground(Color.WHITE);
        formularioUsuario.getLabelPermissaoMensagem().setOpaque(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        if (formularioUsuario.getMenuSelectionadoIndex() == 5) {
            pesquisa(formularioUsuario.getCabecalho().getPesquisar().getText());
        }
    }
}
