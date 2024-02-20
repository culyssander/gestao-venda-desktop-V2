
package com.dxc.gestao.venda.modelo.servico;

import com.dxc.gestao.venda.modelo.entidade.Perfil;
import com.dxc.gestao.venda.modelo.entidade.Usuario;
import com.dxc.gestao.venda.modelo.repositorio.CrudRepositorio;
import com.dxc.gestao.venda.modelo.repositorio.impl.CrudRepositorioImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioServico {
 
    private CrudRepositorio usuarioRepositorio;
    private PermissaoServico permissaoServico;

    public UsuarioServico() {
        usuarioRepositorio = new CrudRepositorioImpl(Usuario.class) {};
        permissaoServico = new PermissaoServico();
    }
    
    public List<Usuario> encontrarTodos() {
        return usuarioRepositorio.encontrarTodos();
    }
    
    public Usuario encontrarPeloId(Long id) {
        Optional<Usuario> usuario = usuarioRepositorio.encontrarPeloId(id);
        
        return usuario.isPresent() ? usuario.get() : null;
    }
    
    public String salvar(Usuario usuario) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", usuario.getEmail());
        if (usuario.getId() == null && usuarioRepositorio.existePeloAtributoEValor(map)) {
            throw new RuntimeException("Ja existe usuario registrado com esse email");
        }
        
        try {
            String hashSenha = new BCryptPasswordEncoder().encode(usuario.getSenha());
            usuario.setSenha(hashSenha);
           boolean result = usuarioRepositorio.salvar(usuario);
           
           if (result) {
                if (usuario.getId() == null) {
                    List<Usuario> lista = usuarioRepositorio.encontrarPeloAtributoUsandoAND(map, false);
                    if (!lista.isEmpty()) {
                        permissaoServico.salvarPermissaoDoUsuario(lista.get(0).getId(), Perfil.valueOf(usuario.getPerfil().toUpperCase()));
                    }
                }
                return "Usuario salvando com sucesso!!!";
            }
        } catch (Exception e) {
            List<Usuario> lista = usuarioRepositorio.encontrarPeloAtributoUsandoAND(map, false);
            usuarioRepositorio.removerPeloId(lista.get(0).getId());
            throw new RuntimeException("Erro ao salvar usuario " + e.getMessage());
        }
        return "Erro ao salvar usuario";
    }
    
    public boolean remover(Long id) {
        return usuarioRepositorio.removerPeloId(id);
    }
    
    public List<Usuario> encontrarPeloAtributo(Map<String, Object> map) {
        return usuarioRepositorio.encontrarPeloAtributoUsandoOR(map, true);
    }
}
