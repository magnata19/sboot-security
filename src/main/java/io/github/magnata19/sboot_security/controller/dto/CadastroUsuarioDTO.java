package io.github.magnata19.sboot_security.controller.dto;

import io.github.magnata19.sboot_security.domain.entity.Usuario;
import lombok.Data;

import java.util.List;

@Data
public class CadastroUsuarioDTO {
    private Usuario usuario;
    private List<String> permissoes;
}
