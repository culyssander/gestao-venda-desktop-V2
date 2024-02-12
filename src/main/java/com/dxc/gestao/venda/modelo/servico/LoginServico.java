
package com.dxc.gestao.venda.modelo.servico;

import com.dxc.gestao.venda.modelo.dto.LoginResponse;
import com.dxc.gestao.venda.modelo.entidade.Usuario;
import com.dxc.gestao.venda.modelo.repositorio.CrudRepositorio;
import com.dxc.gestao.venda.modelo.repositorio.impl.CrudRepositorioImpl;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LoginServico {

    private final CrudRepositorio usuarioRepositorio;
    
    public LoginServico() {
        usuarioRepositorio = new CrudRepositorioImpl(Usuario.class) {};
    }
    
    public LoginResponse login(String email, String senha) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        List<Usuario> usuarios = usuarioRepositorio.encontrarPeloAtributoUsandoAND(map, false);
        
        if (!usuarios.isEmpty()) {
            Usuario usuario = usuarios.get(0);
           
            boolean resposta = usuario.getEstado() && 
                    new BCryptPasswordEncoder().matches(senha, usuario.getSenha());
            
            usuario.setUltimoLogin(LocalDateTime.now());
            usuarioRepositorio.salvar(usuario);
            return LoginResponse.builder()
                    .resposta(resposta)
                    .usuario(usuario)
                    .build();
        }
            
        return LoginResponse.builder()
                .resposta(false)
                .build();
    }
     
}
