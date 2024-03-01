package com.dxc.gestao.venda.modelo.repositorio.impl;

import com.dxc.gestao.venda.modelo.dto.VendaRequestDto;
import com.dxc.gestao.venda.modelo.dto.VendaResponseDto;
import com.dxc.gestao.venda.modelo.entidade.Venda;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VendaRepositorioImpl extends CrudRepositorioImpl<Venda>{
    
    private VendaItemRepositorioImpl vendaItemRepositorio;

    public VendaRepositorioImpl() {
        super(Venda.class);
        vendaItemRepositorio = new VendaItemRepositorioImpl();
    }
    
    
    public String salvarVenda(VendaRequestDto vendaRequestDto) {
        try {
            removeItemSeForAtualizaDaVenda(vendaRequestDto.getVenda().getId());
            
            if (vendaRequestDto.getVenda().getId() != null) {
                Optional<Venda> vendaEncontrado = encontrarPeloId(vendaRequestDto.getVenda().getId());
                if (vendaEncontrado.isPresent()) {
                    vendaRequestDto.getVenda().setDataCriacao(vendaEncontrado.get().getDataCriacao());
                }
            }
            boolean resultado = salvar(vendaRequestDto.getVenda());

            if (resultado) {
                Long vendaId = buscarIdDaUltimaVenda();
                
                if (vendaId != null) {
                    vendaRequestDto.getVendaItems()
                            .forEach(vendaItem -> {
                                vendaItem.setVendaId(vendaId);
                                try {
                                    vendaItemRepositorio.salvar(vendaItem);
                                } catch (Exception e) {
                                    vendaItemRepositorio.removerPeloId(vendaId);
                                }
                            });
                }
                return "Venda registrado com sucesso!";
            }
        } catch (Exception e) {
            Long vendaId = buscarIdDaUltimaVenda();
            removerPeloId(vendaId);
            return e.getMessage();
        }
        return "Erro ao salvar venda";
    }
    
    private void removeItemSeForAtualizaDaVenda(Long vendaId) {
        System.out.println("DDDDDDDDD: " + vendaId);
        try {
            if (vendaId != null) {
                vendaItemRepositorio.removerVendaItemPeloId(vendaId);
            }
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
    }

    private Long buscarIdDaUltimaVenda() {
        String sql = "SELECT max(id) FROM venda";

        try {
            ResultSet result = getConexao().obterConexao().prepareStatement(sql).executeQuery();

            if (result.next()) {
                return result.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
        }

        return null;
    }
    
    
    public List<VendaResponseDto> encontrarTodosPersonalizado() {
        String SQL = "SELECT v.*, u.nome, c.cpf FROM 	venda v, cliente c, usuario u "
                + "WHERE v.clienteid = c.id AND v.usuarioId = u.id ORDER BY v.id";
        List<VendaResponseDto> lista = new ArrayList<>();
        try {
            ResultSet resultSet = getConexao().obterConexao()
                    .prepareStatement(SQL)
                    .executeQuery();
            
            while(resultSet.next()) {
                lista.add(getVendaResponseDto(resultSet));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
      
    private VendaResponseDto getVendaResponseDto(ResultSet resultSet) throws SQLException {
        return VendaResponseDto.builder()
                .id(resultSet.getLong("id"))
                .totalVenda(resultSet.getBigDecimal("totalVenda"))
                .troco(resultSet.getBigDecimal("troco"))
                .desconto(resultSet.getBigDecimal("desconto"))
                .cliente(resultSet.getString("cpf"))
                .dataCriacao(resultSet.getObject("dataCriacao", LocalDateTime.class))
                .ultimaActualizacao(resultSet.getObject("ultimaActualizacao", LocalDateTime.class))
                .usuario(resultSet.getString("nome"))
                .valorPago(resultSet.getBigDecimal("valorPago"))
                .vendaItemDtos(null)
                .build();
    }
    
    public List<VendaResponseDto> encontrarPelaDataCriacao(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        List<VendaResponseDto> lista = new ArrayList<>();
        try {
            String SQL = "SELECT v.*, u.nome, c.cpf FROM 	venda v, cliente c, usuario u WHERE v.clienteid = c.id AND v.usuarioId = u.id AND " +
                            "v.dataCriacao BETWEEN ? AND ?  ORDER BY v.id";
            
            PreparedStatement preparedStatement = getConexao().obterConexao()
                    .prepareStatement(SQL);
            
            preparedStatement.setObject(1, dataInicial);
            preparedStatement.setObject(2, dataFinal);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                lista.add(getVendaResponseDto(resultSet));
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return lista;
    }
}
