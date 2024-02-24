package com.dxc.gestao.venda.modelo.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VendaItemDto {
    private String produtoNome;
    private BigDecimal preco;
    private Integer quantidade;
    private BigDecimal desconto;
    private BigDecimal total;
}
