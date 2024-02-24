package com.dxc.gestao.venda.modelo.tabela.modelo;

import com.dxc.gestao.venda.modelo.dto.VendaItemDto;
import com.dxc.gestao.venda.modelo.entidade.VendaItem;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

public class VendaItemModelo extends AbstractTableModel {
    
    private Map<String, VendaItemDto> vendaItems;
    private static final String [] colunas = {"Produto", "Preço", "Quantidade", "Desconto", "Total"};

    public VendaItemModelo(Map<String, VendaItemDto> vendaItems) {
        this.vendaItems = vendaItems;
    }

    @Override
    public int getRowCount() {
        return vendaItems.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VendaItemDto vendaItem = vendaItems
                .values()
                .stream()
                .toList()
                .get(rowIndex);
        
        switch(columnIndex) {
            case 0 -> { return vendaItem.getProdutoNome();}
            case 1 -> { return vendaItem.getPreco(); }
            case 2 -> { return vendaItem.getQuantidade();}
            case 3 -> { return vendaItem.getDesconto();}
            case 4 -> { return vendaItem.getTotal();}
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

    public Map<String, VendaItemDto> getVendaItems() {
        return vendaItems;
    }
    
    
}
