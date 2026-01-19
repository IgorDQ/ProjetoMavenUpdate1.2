package org.example.controller.relatorio;

import org.example.service.RelatorioService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/cidade/csv")
    public ResponseEntity<byte[]> relatorioPorCidade() throws SQLException {

        byte[] csv = relatorioService.gerarRelatorioPorCidadeCsv();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=RelatorioCidade.csv")
                .contentType(MediaType.TEXT_PLAIN)
                .body(csv);
    }

    @GetMapping("/estado/csv")
    public ResponseEntity<byte[]> relatorioPorEstado() throws SQLException {

        byte[] csv = relatorioService.gerarRelatorioPorEstadoCsv();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=RelatorioEstado.csv")
                .contentType(MediaType.TEXT_PLAIN)
                .body(csv);
    }

    @GetMapping("/unificado/csv") // <-- ADICIONADO (TASK 4)
    public ResponseEntity<byte[]> relatorioUnificado() throws SQLException {

        byte[] csv = relatorioService.gerarRelatorioUnificadoCsv();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=RelatorioUnificado.csv")
                .contentType(MediaType.TEXT_PLAIN)
                .body(csv);
    }
}
