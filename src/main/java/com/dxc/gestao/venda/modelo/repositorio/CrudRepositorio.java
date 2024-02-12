package com.dxc.gestao.venda.modelo.repositorio;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CrudRepositorio<T> {
    
    public boolean salvar(T t);
    public boolean salvarTodos(List<T> list);
    public Optional<T> encontrarPeloId(Long id);
    public List<T> encontrarTodos();
    public boolean existePeloId(Long id);
    public boolean removerPeloId(Long id);
    public List<T> encontrarPeloAtributoUsandoAND(Map<String, Object> map, boolean usarLIKE);
    public List<T> encontrarPeloAtributoUsandoOR(Map<String, Object> map, boolean usarLIKE);
    public boolean existePeloAtributoEValor(Map<String, Object> map);
    public Long totalDeArquivo();
}
