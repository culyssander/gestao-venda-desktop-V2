
package com.dxc.gestao.venda.modelo.entidade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PermissaoUsuario {
    private Long permissaoId;
    private Long usuarioId;
}
