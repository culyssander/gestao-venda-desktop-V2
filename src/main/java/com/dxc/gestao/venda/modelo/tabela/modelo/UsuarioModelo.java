package com.dxc.gestao.venda.modelo.tabela.modelo;

import com.dxc.gestao.venda.modelo.entidade.Usuario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class UsuarioModelo extends AbstractTableModel {
    
    private List<Usuario> usuarios;
    private final String [] colunos = {"ID", "Nome", "Email", "Estado", "Perfil"};
    
    public UsuarioModelo (List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    public UsuarioModelo() {}

    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return colunos.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario usuario = usuarios.get(rowIndex);
        switch (columnIndex) {
            case 0 -> { return usuario.getId(); }
            case 1 -> { return usuario.getNome(); }
            case 2 -> { return usuario.getEmail(); }
            case 3 -> { return usuario.getEstado(); }
            case 4 -> { return usuario.getPerfil(); }
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
          return colunos[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
}
