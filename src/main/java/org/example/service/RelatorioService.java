package org.example.service;

import org.example.model.relatorio.RelatorioPessoasPorCidadeDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelatorioService {

    private final RelatorioPessoasPorCidadeDAO dao = new RelatorioPessoasPorCidadeDAO();

    public String gerarCsvPessoasPorCidade() throws Exception {

        List<String[]> dados = dao.buscarDados();

        StringBuilder csv = new StringBuilder();
        csv.append("cidade,estado,quantidade_pessoas,idade_media\n");

        for (String[] linha : dados) {
            csv.append(String.join(",", linha)).append("\n");
        }

        return csv.toString();
    }
}
