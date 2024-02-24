
package com.dxc.gestao.venda.modelo.entidade;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Estoque {
    
    private Long id;
    private Long produtoId;
    private Integer quantidade;
    private Long usuarioId;
    private Boolean  estado;
    private LocalDateTime dataCriacao;
}
