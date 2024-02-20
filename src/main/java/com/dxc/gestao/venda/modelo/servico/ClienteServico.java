package com.dxc.gestao.venda.modelo.servico;

import com.dxc.gestao.venda.modelo.entidade.Cliente;
import com.dxc.gestao.venda.modelo.repositorio.CrudRepositorio;
import com.dxc.gestao.venda.modelo.repositorio.impl.CrudRepositorioImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteServico {
    
    private final CrudRepositorio clienteRepositorio;
    

    public ClienteServico() {
        clienteRepositorio = new CrudRepositorioImpl(Cliente.class) {};
    }
    
    public List<Cliente> encontrarTodos() {
       return clienteRepositorio.encontrarTodos();
    }

    public String salvar(Cliente cliente) {
        try {
            boolean resultado = clienteRepositorio.salvar(cliente);
            
            if (resultado)
                return "Cliente salvo com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
        
        return "Erro ao salvar cliente";
    }

    public String remover(Long id) {
        try {
            boolean resultado = clienteRepositorio.removerPeloId(id);
            
            if (resultado) 
                return "Cliente removindo com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
        
        return "Erro ao remover cliente";
    }
        
    
    public List<Cliente> encontrarClientePeloAtributo(String texto) {
        Map<String, Object> map = new HashMap<>();
        map.put("nome", texto);
        map.put("telefone", texto);
        map.put("morada", texto);
        
        return clienteRepositorio.encontrarPeloAtributoUsandoOR(map, true);
    }
    
}
