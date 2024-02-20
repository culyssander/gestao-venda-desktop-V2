package com.dxc.gestao.venda.modelo.entidade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Cliente {
    
    private Long id;
    private String nome;
    private String telefone;
    private String morada;    
}
