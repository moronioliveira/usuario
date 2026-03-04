package com.moroni.usuario.controller;

import com.moroni.usuario.business.UsuarioService;
import com.moroni.usuario.business.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/usuario")
@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO){
        UsuarioDTO salvandoUsuarioDTO = usuarioService.salvaUsuario(usuarioDTO);
    return ResponseEntity.ok(salvandoUsuarioDTO);
    }
}
