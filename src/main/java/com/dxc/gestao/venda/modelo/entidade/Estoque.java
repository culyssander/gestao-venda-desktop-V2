
package com.dxc.gestao.venda.modelo.entidade;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Estoque {
    
    private Long id;
    private Produto produto;
    private Integer quantidade;
    private Usuario usuario;
    private boolean  estado;
    private LocalDateTime dataCriacao;
}
