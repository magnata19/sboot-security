package io.github.magnata19.sboot_security.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String login;
    private String password;
    private String nome;

    @Transient // essa annotation ignora o mapeanmento jpa
    private List<String> permissoes;
}
