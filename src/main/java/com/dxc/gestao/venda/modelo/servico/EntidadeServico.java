package com.dxc.gestao.venda.modelo.servico;

import com.dxc.gestao.venda.modelo.entidade.Produto;
import com.dxc.gestao.venda.modelo.entidade.Usuario;
import com.dxc.gestao.venda.modelo.repositorio.CrudRepositorio;
import com.dxc.gestao.venda.modelo.repositorio.impl.CrudRepositorioImpl;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EntidadeServico {
    
    private final CrudRepositorio repositorio;
    private PermissaoServico permissaoServico;
    private PasswordEncoder passwordEncoder;
    private EstoqueHistoricoServico estoqueHistoricoServico;

    public EntidadeServico() {
        repositorio = new CrudRepositorioImpl(Produto.class) {};
        permissaoServico = new PermissaoServico();
        estoqueHistoricoServico = new EstoqueHistoricoServico();
    }
    
    public boolean salva() {
        Usuario usuario1 = Usuario.builder()
//                .id(8L)
                .nome("Quitumba Ferreira")
                .email("quitumba@email.com")
                .senha(hashPassword("1234"))
                .perfil("ADMIN")
                .estado(true)
                .dataCriacao(LocalDateTime.now())
                .build();        
        Usuario usuario2 = Usuario.builder()
                .nome("Inock")
                .email("inock@email.com")
                .senha("1234")
                .perfil("ADMIN")
                .estado(true)
                .build();

        Map<String, Object> map = new HashMap<>();
        String email = usuario1.getEmail();
        map.put("email", email);
        
        repositorio.salvar(usuario1);
//        List<Usuario> list = repositorio.encontrarPeloAtributo(map, false);
        
//        if (list instanceof List) {
//            System.out.println("TRUE");
//        }
//
//            Object nextElement = list.get(0);
//            Map<String, Object>  id = (Map) nextElement;
//            permissaoServico.salvarPermissaoDoUsuario((Long)id.get("id"), Perfil.valueOf(usuario1.getPerfil()));
//            System.out.println("Next> " + id.get("id"));
        
        
//        if (!list.isEmpty()) {
//            try {
//                System.out.println("FFFFFFFFFFF" + list.size());
//                Usuario usuarioId = list.get(0);
//                System.out.println("U: " + usuarioId);
////                
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                System.out.println("List: " + list);
//                e.printStackTrace();
//                repositorio.removerPeloId(list.get(0).getId());
//            }
//        }
        
        return true;
    }
    
    private String hashPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
    
    public List<Usuario> encontrarTodos() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("nome", "Dell Ferreira");
//        map.put("email", "nelson6@email.com");
//        return repositorio.encontrarPeloAtributo(map, false);
        return repositorio.encontrarTodos();
    }
    
    public static void main(String[] args) {
        EntidadeServico servico = new EntidadeServico();
//        System.out.println(servico.encontrarTodos());
//        servico.salva();
//        servico.validaSenha("nelson10@email.com", "1234");
//        System.out.println(servico.repositorio.totalDeArquivo());


        servico.salva();
        System.out.println(servico.repositorio.encontrarPeloId(1L));
    }
    
    private void validaSenha(String email, String senha) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
//        List<Usuario> usuarios = repositorio.encontrarPeloAtributo(map, false);
//        
//        if (!usuarios.isEmpty()) {
//           var valida = new BCryptPasswordEncoder().matches(senha, usuarios.get(0).getSenha());
//            System.out.println(valida);
//        }
    }
}
