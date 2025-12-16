package org.example.controller.relatorio;

import org.example.service.RelatorioService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final RelatorioService service;

    public RelatorioController(RelatorioService service) {
        this.service = service;
    }

    @GetMapping("/pessoas-por-cidade")
    public ResponseEntity<byte[]> gerarRelatorio() throws Exception {

        String csv = service.gerarCsvPessoasPorCidade();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"pessoas_por_cidade.csv\"")
                .contentType(MediaType.TEXT_PLAIN)
                .body(csv.getBytes());
    }
}
