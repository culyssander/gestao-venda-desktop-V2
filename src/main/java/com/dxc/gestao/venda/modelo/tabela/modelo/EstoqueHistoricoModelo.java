package com.dxc.gestao.venda.modelo.tabela.modelo;

import com.dxc.gestao.venda.modelo.dto.HistoricoEstoqueDto;
import com.dxc.gestao.venda.modelo.entidade.EstoqueHistorico;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class EstoqueHistoricoModelo extends AbstractTableModel {
    
    private List<HistoricoEstoqueDto> estoqueHistoricos;
    private final String [] colunas = {"ID", "Nome", "Quantidade", "Estado", "Motivo", "Data"};
    
    public EstoqueHistoricoModelo (List<HistoricoEstoqueDto> estoqueHistoricos) {
        this.estoqueHistoricos = estoqueHistoricos;
    }

    @Override
    public int getRowCount() {
       return estoqueHistoricos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        HistoricoEstoqueDto historico = estoqueHistoricos.get(rowIndex);
        
        switch (columnIndex) {
            case 0 -> {return historico.getProduto().getId();}
            case 1 -> { return historico.getProduto().getNome();}
            case 2 -> {return historico.getQuantidade();}
            case 3 -> {return historico.getTipo();}
            case 4 -> {return historico.getObservacao();}
            case 5 -> {return historico.getDataCriacao();}
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

    public List<HistoricoEstoqueDto> getEstoqueHistoricos() {
        return estoqueHistoricos;
    }

    public void setEstoqueHistoricos(List<HistoricoEstoqueDto> estoqueHistoricos) {
        this.estoqueHistoricos = estoqueHistoricos;
    }
    
}
