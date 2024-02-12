package com.dxc.gestao.venda.modelo.entidade;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VendaItem {
    private Long id;
    private Produto produto;
    private Integer quantidade;
    private BigDecimal desconto;
    private BigDecimal total;
    private Venda venda;
}
