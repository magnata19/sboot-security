package io.github.magnata19.sboot_security.service;

import io.github.magnata19.sboot_security.domain.entity.Grupo;
import io.github.magnata19.sboot_security.domain.entity.Usuario;
import io.github.magnata19.sboot_security.domain.entity.UsuarioGrupo;
import io.github.magnata19.sboot_security.domain.repository.GrupoRepository;
import io.github.magnata19.sboot_security.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioGrupoService {

    private final UsuarioRepository usuarioRepository;
    private final GrupoRepository grupoRepository;

    @Transactional
    public Usuario salvar(Usuario usuario, List<Grupo> grupos) {
        usuarioRepository.save(usuario);
        grupos.stream().map(nomeGrupo -> {
            Optional<Grupo> grupoExistente = grupoRepository.findByNome(nomeGrupo);
            if(grupoExistente.isPresent()) {
                Grupo grupo = grupoExistente.get();
                return new UsuarioGrupo(usuario, grupo);
            }
            return null;
            }).filter(grupo -> grupo != null).collect(Collectors.toList());
        return usuario;
    }
}
