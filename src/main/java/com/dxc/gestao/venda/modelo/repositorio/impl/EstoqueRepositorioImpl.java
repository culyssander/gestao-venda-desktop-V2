package com.dxc.gestao.venda.modelo.repositorio.impl;

import com.dxc.gestao.venda.modelo.dto.EstoqueDto;
import com.dxc.gestao.venda.modelo.entidade.Estoque;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EstoqueRepositorioImpl extends CrudRepositorioImpl<Estoque> {
    
    public EstoqueRepositorioImpl() {
        super(Estoque.class);
    }
    
    public List<EstoqueDto> encontrarTodosDto() {
        String sql = "SELECT e.id, p.nome, e.estado, e.quantidade, e.datacriacao FROM estoque e, produto p WHERE e.produtoid = p.id";
        List<EstoqueDto> estoqueDtos = new ArrayList<>();
        
        try {
            ResultSet resultSet = getConexao().obterConexao().prepareStatement(sql)
                    .executeQuery();
            
            while(resultSet.next()) {
                estoqueDtos.add(getEstoqueDto(resultSet));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return estoqueDtos;
    }
    
    private EstoqueDto getEstoqueDto(ResultSet resultSet) throws SQLException {
        return EstoqueDto.builder()
                .id(resultSet.getLong("id"))
                .produto(resultSet.getString("nome"))
                .estado(resultSet.getBoolean("estado"))
                .quantidade(resultSet.getInt("quantidade"))
                .dataCriacao(resultSet.getObject("datacriacao", LocalDateTime.class))
                .build();
    }
}
