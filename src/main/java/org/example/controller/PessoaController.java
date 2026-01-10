package org.example.controller;

import org.example.service.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(@RequestParam String logradouro) {
        try {
            return ResponseEntity.ok(service.listarPorLogradouro(logradouro));
        /*} catch (IllegalArgumentException e) {
            // erro de regra de negócio (min 5 caracteres)
            return ResponseEntity.badRequest().body(e.getMessage());*/
        } catch (Exception e) {
            // erro inesperado (banco, sistema, etc.)
            return ResponseEntity.internalServerError()
                    .body("Erro no sistema");
        }
    }
}
