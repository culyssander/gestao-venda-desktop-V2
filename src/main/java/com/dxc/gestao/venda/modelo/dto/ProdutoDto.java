
package com.dxc.gestao.venda.modelo.dto;

import com.dxc.gestao.venda.modelo.entidade.Categoria;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProdutoDto {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Categoria categoria;
    private LocalDateTime dataCriacao;
    
}
