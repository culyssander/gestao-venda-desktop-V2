package com.dxc.gestao.venda.modelo.entidade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Categoria {
    private Long id;
    private String nome;
    private String descricao;
        
}
