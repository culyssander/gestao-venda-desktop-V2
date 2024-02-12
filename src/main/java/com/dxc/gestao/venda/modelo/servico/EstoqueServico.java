package com.dxc.gestao.venda.modelo.servico;

import com.dxc.gestao.venda.modelo.entidade.Estoque;
import com.dxc.gestao.venda.modelo.repositorio.CrudRepositorio;
import com.dxc.gestao.venda.modelo.repositorio.impl.CrudRepositorioImpl;

public class EstoqueServico {
    
    private CrudRepositorio estoqueRepositorio;

    public EstoqueServico() {
        estoqueRepositorio = new CrudRepositorioImpl(Estoque.class) {};
    }
    
    public Long quantidadeDeEstoque() {
        return estoqueRepositorio.totalDeArquivo();
    }
    
    
}
