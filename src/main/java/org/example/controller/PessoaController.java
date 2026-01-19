package org.example.controller;

import java.sql.SQLException;
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
    public ResponseEntity<?> listar(@RequestParam String logradouro)
            throws SQLException {

        return ResponseEntity.ok(
                service.listarPorLogradouro(logradouro)
        );
    }


}
