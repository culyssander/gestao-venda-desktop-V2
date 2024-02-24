package com.dxc.gestao.venda.modelo.tabela.modelo;

import com.dxc.gestao.venda.modelo.dto.EstoqueDto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class EstoqueModelo extends AbstractTableModel {
    
    private List<EstoqueDto> estoques;
    private static final String [] colunas = {"ID", "Produto", "Estado", "Quantidade", "Data"};

    public EstoqueModelo(List<EstoqueDto> estoques) {
        this.estoques = estoques;
    }

    @Override
    public int getRowCount() {
        return estoques.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        EstoqueDto estoque = estoques.get(rowIndex);
        
        switch (columnIndex) {
            case 0 -> { return estoque.getId();}
            case 1 -> { return estoque.getProduto();}
            case 2 -> { return estoque.isEstado();}
            case 3 -> { return estoque.getQuantidade();}
            case 4 -> { return estoque.getDataCriacao();}
        }
        
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];     
   }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public List<EstoqueDto> getEstoques() {
        return estoques;
    }
    
    
}
