
package com.dxc.gestao.venda.modelo.repositorio.impl;

import com.dxc.gestao.venda.modelo.conexao.Conexao;
import com.dxc.gestao.venda.modelo.conexao.ConexaoMySQL;
import java.sql.PreparedStatement;

public class PermissaoRepositorioPersonalizado {
    
    private Conexao conexao;
    
    public PermissaoRepositorioPersonalizado() {
        conexao = new ConexaoMySQL();
    }
    
    public void delete(Long usuarioId, Long permissaoId) {
        System.out.println("DELEL: " + usuarioId + " " + permissaoId);
        String SQL = "DELETE FROM permissaousuario WHERE usuarioId = ? AND permissaoId = ?";
        
        try {
            PreparedStatement preparedStatement = conexao.obterConexao().prepareStatement(SQL);
            
            preparedStatement.setLong(1, usuarioId);
            preparedStatement.setLong(2, permissaoId);
            
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
