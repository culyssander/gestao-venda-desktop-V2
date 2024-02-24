package com.dxc.gestao.venda.modelo.repositorio.impl;

import com.dxc.gestao.venda.modelo.entidade.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepositorioImpl extends CrudRepositorioImpl<Produto> {

    public ProdutoRepositorioImpl() {
        super(Produto.class);
    }
    
    public List<Produto> encontraTodosPelaCategoriaNome(String categoriaNome) {
        List<Produto> lista = new ArrayList<>();
        try {
            String SQL = "SELECT p.* FROM produto p, categoria c WHERE p.categoriaId = c.id AND c.nome = ?";
            PreparedStatement preparedStatement = getConexao().obterConexao()
                    .prepareStatement(SQL);
            
            preparedStatement.setString(1, categoriaNome);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                lista.add(getT(resultSet, Produto.class));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
    
}
