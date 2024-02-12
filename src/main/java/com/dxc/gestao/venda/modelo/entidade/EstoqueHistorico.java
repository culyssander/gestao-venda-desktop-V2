
package com.dxc.gestao.venda.modelo.entidade;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EstoqueHistorico {
    
    private Long id;
    private Long produtoId;
    private Integer quantidade;
    private Long usuarioId;
    private String  tipo;
    private String observacao;
    private LocalDateTime dataCriacao;
}
