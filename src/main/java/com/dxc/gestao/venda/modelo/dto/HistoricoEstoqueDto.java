package com.dxc.gestao.venda.modelo.dto;

import com.dxc.gestao.venda.modelo.entidade.EstoqueTipo;
import com.dxc.gestao.venda.modelo.entidade.Produto;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HistoricoEstoqueDto {
    private Long id;
    private Produto produto;
    private Integer quantidade;
    private Long usuarioId;
    private EstoqueTipo  tipo;
    private String observacao;
    private LocalDateTime dataCriacao;
}
