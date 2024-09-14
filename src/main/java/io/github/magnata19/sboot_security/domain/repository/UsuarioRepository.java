package io.github.magnata19.sboot_security.domain.repository;

import io.github.magnata19.sboot_security.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
