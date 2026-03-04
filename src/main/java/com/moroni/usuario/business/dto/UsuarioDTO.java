package com.moroni.usuario.business.dto;

import com.moroni.usuario.infraestructure.entity.Endereco;
import com.moroni.usuario.infraestructure.entity.Telefone;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTO> enderecos;
    private List<TelefoneDTO> telefone;

}
