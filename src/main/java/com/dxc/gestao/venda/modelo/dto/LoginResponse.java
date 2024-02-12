package com.dxc.gestao.venda.modelo.dto;

import com.dxc.gestao.venda.modelo.entidade.Usuario;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private Usuario usuario;
    private boolean resposta;
}
