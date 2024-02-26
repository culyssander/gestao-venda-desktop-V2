package com.dxc.gestao.venda.modelo.dto;

import com.dxc.gestao.venda.modelo.entidade.Venda;
import com.dxc.gestao.venda.modelo.entidade.VendaItem;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VendaRequestDto {
    private Venda venda;
    private List<VendaItem> vendaItems;
}
