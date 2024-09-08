package io.github.magnata19.sboot_security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
public class FooController {

    @GetMapping("/public")
    public ResponseEntity<String> publicRoute() {
        return  ResponseEntity.ok("Rota public ok!");
    }

    @GetMapping("/private")
    public ResponseEntity<String> privateRoute() {
        return  ResponseEntity.ok("Rota private ok!");
    }
}
