package com.dxc.gestao.venda.modelo.servico;

import com.dxc.gestao.venda.modelo.dto.EstoqueDto;
import com.dxc.gestao.venda.modelo.entidade.Estoque;
import com.dxc.gestao.venda.modelo.repositorio.impl.EstoqueRepositorioImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstoqueServico {
    
    private EstoqueRepositorioImpl estoqueRepositorio;

    public EstoqueServico() {
        estoqueRepositorio = new EstoqueRepositorioImpl();
    }
    
    public Long quantidadeDeEstoque() {
        return estoqueRepositorio.totalDeArquivo();
    }

    public List<EstoqueDto> encontrarTodos() {
        return estoqueRepositorio.encontrarTodosDto();
    }
    
    public Estoque encontrarPeloAtributoProdutoId(Long produtoId) {
        Map<String, Object> map = new HashMap<>();
        map.put("produtoId", produtoId);
        
        List<Estoque> estoques = estoqueRepositorio.encontrarPeloAtributoUsandoAND(map, false);
        
        if (estoques.size() != 1) 
            return null;
//            throw new RuntimeException("Erro ao buscar estoque");
        
        return estoques.get(0);
    }

    public String salvar(Estoque estoque) {
        try {
            boolean resultado = estoqueRepositorio.salvar(estoque);
            
            if (resultado) 
                return "Estoque registrado com sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
        return "erro ao salvar estoque";
    }

    public String remover(Long estoqueId) {
        try {
            boolean resultado = estoqueRepositorio.removerPeloId(estoqueId);
            
            if (resultado)
                return "Estoque removido com sucesso";
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Error ao remover estoque";
    }
}
