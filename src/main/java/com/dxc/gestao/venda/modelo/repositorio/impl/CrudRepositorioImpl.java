package com.dxc.gestao.venda.modelo.repositorio.impl;

import com.dxc.gestao.venda.modelo.conexao.Conexao;
import com.dxc.gestao.venda.modelo.conexao.ConexaoMySQL;
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.reflections.ReflectionUtils;
import com.dxc.gestao.venda.modelo.repositorio.CrudRepositorio;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;


public abstract class CrudRepositorioImpl<T extends Object> implements CrudRepositorio<T>{
    
    private Conexao conexao;
    private Class<T> t;
    
    public CrudRepositorioImpl(Class<T> t)  {
        conexao = new ConexaoMySQL();
        this.t = t;
    }   
    
    @Override
    public boolean salvar(T t) {
        try {
            Set<Field> fields = ReflectionUtils.getAllFields(t.getClass(), e -> true);
            Object id = null;
            
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equalsIgnoreCase("id")) {
                    id = field.get(t);
                }
            }
            
            List<T> list = new ArrayList<>();
            list.add(t);
            return id == null ? persiste(list, formaSQL(t, "INSERT INTO %s (%s) VALUES(%s)", id), false) 
                              : persiste(list, formaSQL(t, "UPDATE %s SET %s WHERE id=?", id), true);
        } catch (IllegalAccessException | IllegalArgumentException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
    
    private boolean persiste(List<T> t, String SQL, boolean atualiza) {       
        try {
            System.out.println("SQL: " + SQL);
            PreparedStatement preparedStatement = this.conexao.obterConexao()
                                                      .prepareStatement(SQL);
            preencherPreparedStatement(t, preparedStatement, atualiza);
            int resultado = preparedStatement.executeUpdate();
            
            if (resultado == 0)
                throw new SQLException("Erro ao persistir a entidade");
            
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void preencherPreparedStatement(List<T> list, PreparedStatement ps, boolean atualiza) {
        int count = 1;

        for (T t : list) {
            Set<Field> fields = ReflectionUtils.getAllFields(t.getClass(), e -> true);
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    
                    if (atualiza && field.getName().equals("id")) {
                        ps.setObject(fields.size(), field.get(t));
                        continue;
                    }
                    
                    ps.setObject(count, field.get(t));
                    count++;
                } catch (Exception e) {
                    System.out.println(e);
                    throw new RuntimeException(e);
                }
            }
        }
    }
    
    private String formaSQL(Object t, String SQL, Object id) {
        StringBuilder atributos = new StringBuilder();
        StringBuilder pontoDeInterrogacao = new StringBuilder();
        Set<Field> fields = ReflectionUtils.getAllFields(t.getClass(), e -> true);
        
        for (Field field : fields) {
            field.setAccessible(true);
            
            if(id == null) {
                atributos.append(field.getName()).append(",");
                pontoDeInterrogacao.append("?,");
            } else {
                if (field.getName().equalsIgnoreCase("id")) continue;
                atributos.append(field.getName()).append("=?,");
            }            
        }
        
        if (id == null) {
            return String.format(SQL, t.getClass().getSimpleName(),
                removeVirgulaNoFim(atributos.toString()), 
                removeVirgulaNoFim(pontoDeInterrogacao.toString()));
        } else {
            return String.format(SQL, t.getClass().getSimpleName(),
                removeVirgulaNoFim(atributos.toString()));
        }
    }
    
    private String removeVirgulaNoFim(String texto) {
        return texto.substring(0, texto.length() - 1);
    }

    @Override
    public List<T> encontrarTodos() {
        List<T> list = new ArrayList<>();
        try {
            String SQL = String.format("SELECT * FROM %s", t.getSimpleName());
            ResultSet resultSet = this.conexao.obterConexao()
                                .prepareStatement(SQL)
                                .executeQuery();
            
            while(resultSet.next()) {
                list.add(getT(resultSet, t));
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }        
        return list;
    }

    private T getT(ResultSet result, Class<T> t) {
        try {
            Set<Field> fields = ReflectionUtils.getAllFields(t, e -> true);
            Method method = null;
            T tNew = t.newInstance();
            
            for(Field field : fields) {
                String nome = field.getName();
                Object valor = null;
                
                switch(field.getType().getSimpleName().toUpperCase()) {
                    case "STRING" -> {
                        valor = result.getString(nome);
                        method = t.getMethod("set" + StringPrimeiraLetraMaiuscula(nome), String.class);
                    }
                    case "LONG" -> {
                        valor = result.getLong(nome);
                        method = t.getMethod("set" + StringPrimeiraLetraMaiuscula(nome), Long.class);
                    }
                    case "INTEGER" -> {
                        valor = result.getInt(nome);
                        method = t.getMethod("set" + StringPrimeiraLetraMaiuscula(nome), Integer.class);
                    }
                    case "BOOLEAN" -> {
                        valor = result.getBoolean(nome);
                        method = t.getMethod("set" + StringPrimeiraLetraMaiuscula(nome), Boolean.class);
                    }
                    case "BIGDECIMAL" -> {
                        valor = result.getBigDecimal(nome);
                        method = t.getMethod("set" + StringPrimeiraLetraMaiuscula(nome), BigDecimal.class);
                    }
                    case "LOCALDATETIME" -> {
                        valor = result.getObject(nome, LocalDateTime.class);
                        method = t.getMethod("set" + StringPrimeiraLetraMaiuscula(nome), LocalDateTime.class);
                    }
                    case "OBJECT" -> {
                        valor = result.getObject(nome);
                        method = t.getMethod("set" + StringPrimeiraLetraMaiuscula(nome), Object.class);
                    }
                }
                method.invoke(tNew, valor);
            }
            return tNew;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static String StringPrimeiraLetraMaiuscula(String str) {

        if (!str.isEmpty()) {
            return str.substring(0, 1).toUpperCase().concat(str.substring(1));
        }

        return "";
    }

    @Override
    public Optional<T> encontrarPeloId(Long id) {
        T tOptional = null;
        try {
            String SQL = String.format("SELECT * FROM %s WHERE id = ?", 
                            t.getSimpleName());
            PreparedStatement preparedStatement = this.conexao.obterConexao()
                                .prepareStatement(SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()) {
                tOptional = getT(resultSet, t);
            }
            
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        
        return Optional.ofNullable(tOptional);
    }

    @Override
    public boolean existePeloId(Long id) {
        return encontrarPeloId(id).isPresent();
    }

    @Override
    public boolean removerPeloId(Long id) {
        if (existePeloId(id)) {
            try {
                String SQL = String.format("DELETE FROM %s WHERE id = ?", 
                            t.getSimpleName());
                PreparedStatement preparedStatement = this.conexao.obterConexao()
                                .prepareStatement(SQL);
                preparedStatement.setLong(1, id);
                
                int resultado = preparedStatement.executeUpdate();
            
                if (resultado == 0)
                    throw new SQLException("Erro ao delete a entidade");
                
                return true;                
            } catch (SQLException e) {
                System.out.println(e);
                throw new RuntimeException(e);
            }
        }
        
        return false;
    }
    
    private List<T> encontrarPeloAtributo(Map<String, Object> map, boolean usarLIKE, StringBuilder stringBuilder) {
        String query = stringBuilder.toString().substring(0, (stringBuilder.length() - 4));
        String SQL = String.format("SELECT * FROM %s WHERE %s;",
                                t.getSimpleName(), query);
        List<T> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.conexao.obterConexao()
                                .prepareStatement(SQL);
            
            preencherPreparedStatementMap(preparedStatement, map, usarLIKE);
            ResultSet resultSet = preparedStatement.executeQuery();
               
            while (resultSet.next()) {
                list.add(getT(resultSet, t));
            }
            
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        
        return list;
    }

    @Override
    public List<T> encontrarPeloAtributoUsandoAND(Map<String, Object> map, boolean usarLIKE) {
        StringBuilder stringBuilder = new StringBuilder();
        
        map.forEach((key, value) -> {
            stringBuilder.append(key);
            
            if(usarLIKE)
                stringBuilder.append(" LIKE ");
            else
                stringBuilder.append("=");
            
            stringBuilder.append("?").append(" AND ") ;
        });
        
        return encontrarPeloAtributo(map, usarLIKE, stringBuilder);
    }
    
    @Override
    public List<T> encontrarPeloAtributoUsandoOR(Map<String, Object> map, boolean usarLIKE) {
        StringBuilder stringBuilder = new StringBuilder();
        
        map.forEach((key, value) -> {
            stringBuilder.append(key);
            
            if(usarLIKE)
                stringBuilder.append(" LIKE ");
            else
                stringBuilder.append("=");
            
            stringBuilder.append("?").append(" OR  ") ;
        });
        
        return encontrarPeloAtributo(map, usarLIKE, stringBuilder);
    }
    
    private void preencherPreparedStatementMap(PreparedStatement ps, Map<String, 
                                Object> map, boolean usarLIKE) throws SQLException { 
        int count = 1;
        
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            if (usarLIKE)
                ps.setString(count, "%"+ entry.getValue() + "%");
            else
                ps.setObject(count, entry.getValue());
            count++;           
        }
    }

    @Override
    public boolean existePeloAtributoEValor(Map<String, Object> map) {
        List<T> list = encontrarPeloAtributoUsandoAND(map, false);
        
        return !list.isEmpty();
    }

    @Override
    public boolean salvarTodos(List<T> list) {
        String SQL = formaSQLTodos(list.size(), "INSERT INTO %s (%s) VALUES %s");
        return persiste(list, SQL, false);
    }
 
    private String formaSQLTodos(int tamanho, String SQL) {
        StringBuilder atributos = new StringBuilder();
        StringBuilder pontoDeInterrogacao = new StringBuilder();
        Set<Field> fields = ReflectionUtils.getAllFields(t, e -> true);
        
        
        for (Field field : fields) {
                field.setAccessible(true);
                atributos.append(field.getName()).append(",");
                pontoDeInterrogacao.append("?,");
        }
        
        System.out.println("ATRI: " + atributos);
        
        String refinamentoAtributo = removeVirgulaNoFim(atributos.toString());
        String refinamentoPonto = removeVirgulaNoFim(pontoDeInterrogacao.toString());
        pontoDeInterrogacao = new StringBuilder();
        
        for(int i = 0; i < tamanho; i++) {        
            pontoDeInterrogacao
                    .append("(")
                    .append(refinamentoPonto)
                    .append("),");
        }
        
        refinamentoPonto = removeVirgulaNoFim(pontoDeInterrogacao.toString());
        
        return String.format(SQL, t.getSimpleName(), 
                refinamentoAtributo, refinamentoPonto);
    }

    @Override
    public Long totalDeArquivo() {
        try {
            String SQL = "SELECT count(*) FROM " + t.getSimpleName();
            var result = conexao.obterConexao().prepareStatement(SQL).executeQuery();
            
            if (result.next())
                return result.getLong(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0L;
    }

    public Conexao getConexao() {
        return conexao;
    }

}
