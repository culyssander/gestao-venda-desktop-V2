package com.dxc.gestao.venda.modelo.tabela.modelo;

import com.dxc.gestao.venda.modelo.entidade.Categoria;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class CategoriaModelo extends AbstractTableModel {
    
    private List<Categoria> categorias;
    private final String [] colunas = {"ID", "Nome", "Descrição", ""};
    
    public CategoriaModelo(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    public CategoriaModelo() {}

    @Override
    public int getRowCount() {
        return categorias.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Categoria categoria = categorias.get(rowIndex);
        
        switch (columnIndex) {
            case 0 -> {return categoria.getId();}
            case 1 -> {return categoria.getNome();}
            case 2 -> {return categoria.getDescricao();} 
            case 3 -> {return "REMOVER";}
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }
    
    
    
}
