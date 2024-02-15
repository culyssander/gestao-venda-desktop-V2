package com.dxc.gestao.venda.modelo.servico;

import com.dxc.gestao.venda.modelo.entidade.Categoria;
import com.dxc.gestao.venda.modelo.repositorio.CrudRepositorio;
import com.dxc.gestao.venda.modelo.repositorio.impl.CrudRepositorioImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CategoriaServico {
 
    private CrudRepositorio categoriaRepositorio;

    public CategoriaServico() {
        categoriaRepositorio = new CrudRepositorioImpl(Categoria.class) {};
    }
    
    public List<Categoria> encontrarTodos() {
        return categoriaRepositorio.encontrarTodos();
    }
    
    public String salva(Categoria categoria) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", categoria.getNome());
        
        if (categoria.getId() == null && categoriaRepositorio.existePeloAtributoEValor(map)) {
            throw new RuntimeException("Ja existe essa categoria cadastrado");
        }
        
        try {
            boolean result = categoriaRepositorio.salvar(categoria);
            
            if (result) {
                return "Categoria salvando com sucesso!";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Erro ao salvar categoria";
    }
    
    public Categoria encontrarPeloId(Long id) {
        Optional<Categoria> optionalCategoria = categoriaRepositorio.encontrarPeloId(id);
        
        if (optionalCategoria.isPresent()) {
            return optionalCategoria.get();
        }
        throw new RuntimeException("Categoria nao encontrado.");
    }
    
    
}
