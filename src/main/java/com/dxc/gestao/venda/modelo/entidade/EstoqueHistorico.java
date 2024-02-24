
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
public class EstoqueHistorico {
    
    private Long id;
    private String produto;
    private Integer quantidade;
    private String usuario;
    private String  tipo;
    private String observacao;
    private LocalDateTime dataCriacao;
}
