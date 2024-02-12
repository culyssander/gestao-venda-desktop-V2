package com.dxc.gestao.venda.modelo.entidade;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Usuario {
    
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Boolean estado;
    private String perfil;
    private String urlFoto;
    private LocalDateTime dataCriacao;
    private LocalDateTime ultimoLogin;   
}
