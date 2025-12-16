package org.example.controller;

import org.example.dto.PessoaEnderecoDTO;
import org.example.service.PessoaService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public List<PessoaEnderecoDTO> listar(@RequestParam String logradouro) throws SQLException {
        return service.listarPorLogradouro(logradouro);
    }
}
