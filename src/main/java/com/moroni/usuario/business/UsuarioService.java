package com.moroni.usuario.business;

import com.moroni.usuario.business.converter.UsuarioConverter;
import com.moroni.usuario.business.dto.UsuarioDTO;
import com.moroni.usuario.infraestructure.entity.Usuario;
import com.moroni.usuario.infraestructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuarioConversor = usuarioConverter.paraUsuario(usuarioDTO);

        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuarioConversor));
    }
}
