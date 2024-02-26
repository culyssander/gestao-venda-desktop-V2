package com.dxc.gestao.venda.modelo.tabela.modelo;

import com.dxc.gestao.venda.modelo.dto.VendaResponseDto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class VendaModelo extends AbstractTableModel{
    
    private List<VendaResponseDto> vendaResponseDtos;
    private static final String [] colunas = {"ID", "Total", "Valor Pago", "Troco", "Desconto", "Cliente", "Usuario", "Data"};

    public VendaModelo(List<VendaResponseDto> vendaResponseDtos) {
        this.vendaResponseDtos = vendaResponseDtos;
    }

    @Override
    public int getRowCount() {
        return vendaResponseDtos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VendaResponseDto response = vendaResponseDtos.get(rowIndex);
        
        switch(columnIndex) {
            case 0 -> {return response.getId();}
            case 1 -> {return response.getTotalVenda();}
            case 2 -> {return response.getValorPago();}
            case 3 -> {return response.getTroco(); }
            case 4 -> {return response.getDesconto(); }
            case 5 -> {return response.getCliente(); }
            case 6 -> {return response.getUsuario(); }
            case 7 -> {return response.getDataCriacao(); }
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public List<VendaResponseDto> getVendaResponseDtos() {
        return vendaResponseDtos;
    }
    
}
