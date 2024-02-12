package com.dxc.gestao.venda.modelo.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL implements Conexao{
    
    private Connection connection;
    private final String URL = "jdbc:mysql://localhost/gestao_venda?useTimezone=true&serverTimezone=Africa/Luanda";
    private final String USER = "root";
    private final String PASSWORD = "Password0";
    
    @Override
    public Connection obterConexao() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
                
        return connection;
    }

    @Override
    public void fechaConexao() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
}
