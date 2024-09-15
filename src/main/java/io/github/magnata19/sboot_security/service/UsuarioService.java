package io.github.magnata19.sboot_security.service;

import io.github.magnata19.sboot_security.config.SecurityConfig;
import io.github.magnata19.sboot_security.domain.entity.Grupo;
import io.github.magnata19.sboot_security.domain.entity.Usuario;
import io.github.magnata19.sboot_security.domain.entity.UsuarioGrupo;
import io.github.magnata19.sboot_security.domain.repository.GrupoRepository;
import io.github.magnata19.sboot_security.domain.repository.UsuarioGrupoRepository;
import io.github.magnata19.sboot_security.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final GrupoRepository grupoRepository; //pra pegar registro do grupo
    private final UsuarioGrupoRepository usuarioGrupoRepository;
    private final SecurityConfig securityConfig;

    @Transactional
    public Usuario salvar(Usuario usuario, List<String> grupos) {
        String passwordEncode = securityConfig.passwordEncoder().encode(usuario.getPassword());
        usuario.setPassword(passwordEncode);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        List<UsuarioGrupo> listaUsuarioGrupo = grupos.stream().map(nomeGrupo -> {
            Optional<Grupo> grupoEncontrado = grupoRepository.findByNome(nomeGrupo);
            if (grupoEncontrado.isPresent()) {
                Grupo grupo = grupoEncontrado.get();
                return new UsuarioGrupo(usuarioSalvo, grupo);
            }
            return null;
        }).filter(grupo -> grupo != null).collect(Collectors.toList());
        usuarioGrupoRepository.saveAll(listaUsuarioGrupo);
        return usuario;
    }
    
    public Usuario obterUsuarioComPermissoes(String login) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(login);
        if(usuarioOptional.isEmpty()) {
            return null;
        }
        Usuario usuario = usuarioOptional.get();
        List<String> permissoes = usuarioGrupoRepository.findPermissoesByUsuario(usuario);
        usuario.setPermissoes(permissoes);
        return usuario;
    }

}
