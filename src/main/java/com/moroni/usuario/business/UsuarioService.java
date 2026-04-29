package com.moroni.usuario.business;
import com.moroni.usuario.business.converter.UsuarioConverter;
import com.moroni.usuario.business.dto.UsuarioDTO;
import com.moroni.usuario.infrastructure.entity.Usuario;
import com.moroni.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.moroni.usuario.infrastructure.repository.UsuarioRepository;
import com.moroni.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario)
        );
    }
    public void emailExiste(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new com.moroni.usuario.infrastructure.exceptions.ConflictException("Email já cadastrado " + email);
            }
        } catch (com.moroni.usuario.infrastructure.exceptions.ConflictException e) {
            throw new com.moroni.usuario.infrastructure.exceptions.ConflictException("Email já cadastrado ", e.getCause());
        }
    }
    public boolean verificaEmailExistente(String email) {

        return usuarioRepository.existsByEmail(email);
    }
    public Usuario buscaUsuarioPoremail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado" + email));
    }

    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }

    }
