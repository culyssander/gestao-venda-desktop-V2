
package com.dxc.gestao.venda.modelo.servico;

import com.dxc.gestao.venda.modelo.dto.HistoricoEstoqueDto;
import com.dxc.gestao.venda.modelo.entidade.EstoqueHistorico;
import com.dxc.gestao.venda.modelo.entidade.EstoqueTipo;
import com.dxc.gestao.venda.modelo.entidade.Produto;
import com.dxc.gestao.venda.modelo.repositorio.CrudRepositorio;
import com.dxc.gestao.venda.modelo.repositorio.impl.CrudRepositorioImpl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EstoqueHistoricoServico {
    
    private CrudRepositorio estoqueHistoricoRepositorio;
    private CrudRepositorio produtoRepositorio;
    

    public EstoqueHistoricoServico() {
        estoqueHistoricoRepositorio = new CrudRepositorioImpl(EstoqueHistorico.class) {};
        produtoRepositorio = new CrudRepositorioImpl(Produto.class) {};
    }
    
    public List<HistoricoEstoqueDto> encontraTodos() {
        List<EstoqueHistorico> historicos = estoqueHistoricoRepositorio.encontrarTodos();
        
        return historicos.stream()
                .map(e -> {
                    Optional<Produto> produtoOptional = produtoRepositorio.encontrarPeloId(e.getProdutoId());
                    System.out.println(produtoOptional);
                    return  HistoricoEstoqueDto.builder()
                            .quantidade(e.getQuantidade())
                            .observacao(e.getObservacao())
                            .tipo(EstoqueTipo.valueOf(e.getTipo()))
                            .dataCriacao(e.getDataCriacao())
                            .produto(produtoOptional.get())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public void salvar(EstoqueHistorico estoqueHistorico) {
        estoqueHistoricoRepositorio.salvar(estoqueHistorico);
    }
    
}
