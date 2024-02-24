package com.dxc.gestao.venda.modelo.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EstoqueDto {
    private Long id;
    private String produto;
    private Integer quantidade;
    private boolean  estado;
    private LocalDateTime dataCriacao;
    
}
