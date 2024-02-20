package com.dxc.gestao.venda.modelo.servico;

import com.dxc.gestao.venda.modelo.dto.ProdutoDto;
import com.dxc.gestao.venda.modelo.entidade.Categoria;
import com.dxc.gestao.venda.modelo.entidade.Produto;
import com.dxc.gestao.venda.modelo.repositorio.CrudRepositorio;
import com.dxc.gestao.venda.modelo.repositorio.impl.CrudRepositorioImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProdutoServico {
    private CrudRepositorio produtoRepositorio;
    private CrudRepositorio categoriaRepositorio;

    public ProdutoServico() {
        produtoRepositorio = new CrudRepositorioImpl(Produto.class){};
        categoriaRepositorio = new CrudRepositorioImpl(Categoria.class) {};
    }
    
    public Long quantidadeDeProduto() {
        return produtoRepositorio.totalDeArquivo();
    }
    
    public List<ProdutoDto> encontrarTodos() {
        List<Produto> lista = produtoRepositorio.encontrarTodos();
        
        return lista.stream()
             .map(p -> {
                    Optional<Categoria> categoria = categoriaRepositorio.encontrarPeloId(p.getCategoriaId());
                    return ProdutoDto.builder()
                            .id(p.getId())
                            .nome(p.getNome())
                            .descricao(p.getDescricao())
                            .preco(p.getPreco())
                            .categoria(categoria.get())
                            .dataCriacao(p.getDataCriacao())
                            .build();
             }).collect(Collectors.toList());
    }
    
    public String salva(Produto produto) {
        Map<String, Object> map = new HashMap<>();
        map.put("nome", produto.getNome());
        
        if (produto.getId() == null && produtoRepositorio.existePeloAtributoEValor(map)) {
            return "Produto ja encontra-se cadastrado...";
        }
        
        try {
            boolean resultado = produtoRepositorio.salvar(produto);
            System.out.println("RESULTADO: " + resultado);
            if (resultado) 
                return "Produto cadastrado com sucesso";
        } catch (Exception e) {
            System.out.println("error: " + e);
            return e.getMessage();
        }
        
        return "erro ao cadastrar o produto";
    }
    
    public String remover(Long id) {
        try {
            boolean resultado = produtoRepositorio.removerPeloId(id);
            
            if (resultado) 
                return "Produto removido com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Erro ao remover o produto";
    }
    
}
