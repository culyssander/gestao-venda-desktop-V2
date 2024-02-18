package com.dxc.gestao.venda.modelo.entidade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Categoria {
    private Long id;
    private String nome;
    private String descricao;
        
}
