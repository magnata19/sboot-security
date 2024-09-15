package io.github.magnata19.sboot_security.controller;

import io.github.magnata19.sboot_security.controller.dto.CadastroUsuarioDTO;
import io.github.magnata19.sboot_security.domain.entity.Usuario;
import io.github.magnata19.sboot_security.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/usuarios")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> salvar(@RequestBody CadastroUsuarioDTO dto) {
        Usuario usuarioSalvo = usuarioService.salvar(dto.getUsuario(), dto.getPermissoes());
        return ResponseEntity.ok(usuarioSalvo);
    }

}
