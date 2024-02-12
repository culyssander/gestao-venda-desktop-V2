package com.dxc.gestao.venda.modelo.servico;

import com.dxc.gestao.venda.modelo.entidade.Perfil;
import com.dxc.gestao.venda.modelo.entidade.Permissao;
import com.dxc.gestao.venda.modelo.entidade.PermissaoUsuario;
import com.dxc.gestao.venda.modelo.repositorio.CrudRepositorio;
import com.dxc.gestao.venda.modelo.repositorio.impl.CrudRepositorioImpl;
import com.dxc.gestao.venda.modelo.repositorio.impl.PermissaoRepositorioPersonalizado;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class PermissaoServico {
    
    private final CrudRepositorio<PermissaoUsuario> repositorioPermissaoUsuario;
    private final CrudRepositorio<Permissao> repositorioPermissao;
    private PermissaoRepositorioPersonalizado permissaoRepositorioPersonalizado;

    public PermissaoServico() {
        repositorioPermissaoUsuario = new CrudRepositorioImpl(PermissaoUsuario.class){};
        repositorioPermissao = new CrudRepositorioImpl<Permissao>(Permissao.class) {};
        permissaoRepositorioPersonalizado = new PermissaoRepositorioPersonalizado();
    }
    
    
    public void salvarPermissaoDoUsuario(Long usuarioId, Perfil perfil) {
        System.out.println("PER" + perfil);
        List<Long> permissaoIds = new ArrayList<>();
        if (perfil.equals(Perfil.ADMIN)) {
            List<Permissao> permissoes = repositorioPermissao.encontrarTodos();
            for(Permissao p : permissoes) {
                permissaoIds.add(p.getId());
            }
        } else {
            permissaoIds.add(3L);
        }        
        
        List<PermissaoUsuario> permissaoUsuarios = permissaoIds.stream()
                        .map(p -> {
                            return PermissaoUsuario.builder()
                            .permissaoId(p)
                            .usuarioId(usuarioId)
                            .build();
                    }).collect(Collectors.toList());
        repositorioPermissaoUsuario.salvarTodos(permissaoUsuarios);
    }
    
    public void salvaPermissao(List<PermissaoUsuario> permissaoUsuarios) {
        repositorioPermissaoUsuario.salvarTodos(permissaoUsuarios);
    }
    
    public List<Permissao> encontrarTodos() {
        return repositorioPermissao.encontrarTodos();
    }
    
    public List<Permissao> encontrarTodosPeloUsuario(Long usuarioId) {
        Map<String, Object> map = new HashMap<>();
        map.put("usuarioId", usuarioId);
        
        List<PermissaoUsuario> lista = repositorioPermissaoUsuario.encontrarPeloAtributoUsandoAND(map, false);
        
        return lista
                .stream()
                .map(p ->  {
                    return repositorioPermissao.encontrarPeloId(p.getPermissaoId()).get();
                }).collect(Collectors.toList());
    }
    
    public boolean validaPermissoes(Long usuarioId, Long permissaoId) {
        Map<String, Object> map = new HashMap<>();
        map.put("usuarioId", usuarioId);
        map.put("permissaoId", permissaoId);
        
        return repositorioPermissaoUsuario.existePeloAtributoEValor(map);
    }
    
    public void validaPermissao(Long usuarioId, Long permissaoId) {
        Map<String, Object> map = new HashMap<>();
        map.put("usuarioId", usuarioId);
        map.put("permissaoId", permissaoId);
        
        System.out.println(map);
        System.out.println(repositorioPermissaoUsuario.existePeloAtributoEValor(map));
        
        if (!repositorioPermissaoUsuario.existePeloAtributoEValor(map)) {
            String mensagem = "Sem permissão";
            JOptionPane.showMessageDialog(null, mensagem, "Sem permissão", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(mensagem);
        }
    }
    
    public void delete(Long usuarioId, Long permissaoId) {
        permissaoRepositorioPersonalizado.delete(usuarioId, permissaoId);
    }
    
    public List<Permissao> encontrarPeloNome(String nome) {
        Map<String, Object> map = new HashMap<>();
        map.put("nome", nome);
        return repositorioPermissao.encontrarPeloAtributoUsandoAND(map, false);
    }
}
