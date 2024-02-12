package com.dxc.gestao.venda.modelo.tabela.modelo;

import com.dxc.gestao.venda.modelo.dto.ProdutoDto;
import com.dxc.gestao.venda.modelo.entidade.Produto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ProdutoModelo extends AbstractTableModel {
    
    private List<ProdutoDto> produtos;
    private final String [] colunas = {"ID", "Nome", "Descrição", "Preço", "Categoria"};

    public ProdutoModelo(List<ProdutoDto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProdutoDto produtoDto = produtos.get(rowIndex);
        
        switch (columnIndex) {
            case 0 -> { return produtoDto.getId();}
            case 1 -> { return produtoDto.getNome();}
            case 2 -> { return produtoDto.getDescricao();}
            case 3 -> { return produtoDto.getPreco(); }
            case 4 -> { return produtoDto.getCategoria().getNome();}
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

    public List<ProdutoDto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDto> produtos) {
        this.produtos = produtos;
    }
    
    
}
