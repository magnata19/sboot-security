package io.github.magnata19.sboot_security.controller;

import io.github.magnata19.sboot_security.domain.entity.Grupo;
import io.github.magnata19.sboot_security.domain.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GrupoController {

    private final GrupoRepository grupoRepository;

    @Transactional
    @PostMapping("/grupos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Grupo> salvar(@RequestBody Grupo grupo, Authentication authentication) {
        log.info("via salvar grupo, user: "+ authentication.getName());
        Grupo grupoSalvo = grupoRepository.save(grupo);
        return ResponseEntity.ok(grupoSalvo);
    }

    @GetMapping("/listargrupos")
    public ResponseEntity<List<Grupo>> listar(Authentication authentication) {
        List<Grupo> all = grupoRepository.findAll();
        log.info("Via listar grupos, user: " + authentication.getName());
        return ResponseEntity.ok(all);
    }
}
