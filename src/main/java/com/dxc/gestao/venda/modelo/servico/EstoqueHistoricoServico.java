
package com.dxc.gestao.venda.modelo.servico;

import com.dxc.gestao.venda.modelo.entidade.EstoqueHistorico;
import com.dxc.gestao.venda.modelo.repositorio.CrudRepositorio;
import com.dxc.gestao.venda.modelo.repositorio.impl.CrudRepositorioImpl;
import java.util.ArrayList;
import java.util.List;

public class EstoqueHistoricoServico {
    
    private CrudRepositorio estoqueHistoricoRepositorio;
    private PermissaoServico permissaoServico;
    private final long PERMISSAO_ID_PARA_ENCONTRAR_TODOS_HISTORICO = 21;
    private final long PERMISSAO_ID_PARA_ENCONTRAR_SOMENTE_SEU_HISTORICO = 22;
    private Long usuarioId;
    

    public EstoqueHistoricoServico(Long usuarioId) {
        estoqueHistoricoRepositorio = new CrudRepositorioImpl(EstoqueHistorico.class) {};
        permissaoServico = new PermissaoServico();
        this.usuarioId = usuarioId;
    }
    
    public List<EstoqueHistorico> encontraTodos() {
        List<EstoqueHistorico> estoqueHistoricos = estoqueHistoricoRepositorio.encontrarTodos();
        System.out.println("ID: " + usuarioId + " - " + PERMISSAO_ID_PARA_ENCONTRAR_SOMENTE_SEU_HISTORICO);
        System.out.println("permissaoServico.validaPermissoes(id, PERMISSAO_ID_PARA_ENCONTRAR_TODOS_HISTORICO: " +
                permissaoServico.validaPermissoes(usuarioId, PERMISSAO_ID_PARA_ENCONTRAR_TODOS_HISTORICO));
        if (permissaoServico.validaPermissoes(usuarioId, PERMISSAO_ID_PARA_ENCONTRAR_TODOS_HISTORICO)) {
            return estoqueHistoricos;
        } else if (permissaoServico.validaPermissoes(usuarioId, PERMISSAO_ID_PARA_ENCONTRAR_SOMENTE_SEU_HISTORICO)) {
            return estoqueHistoricos.stream()
                    .filter(e -> e.getUsuario().equals(usuarioId.toString()))
                    .toList();
        } else {
            return new ArrayList<>();
        }
    }

    public void salvar(EstoqueHistorico estoqueHistorico) {
        estoqueHistoricoRepositorio.salvar(estoqueHistorico);
    }
    
}
