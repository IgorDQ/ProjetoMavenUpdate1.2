package org.example.service;

import org.example.dto.RelatorioCidadeDTO;
import org.example.dto.RelatorioEstadoDTO;
import org.example.model.ClienteDAO;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
@Service
public class RelatorioService {

    private final ClienteDAO clienteDAO;

    public RelatorioService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }


    public byte[] gerarRelatorioPorCidadeCsv() throws SQLException {

        List<RelatorioCidadeDTO> dados =
                clienteDAO.buscarRelatorioPorCidade();

        StringBuilder csv = new StringBuilder();

        csv.append("cidade,estado,quantidade_pessoas,idade_media\n");

        for (RelatorioCidadeDTO dto : dados) {
            csv.append(limparTexto(dto.getCidade())).append(",");
            csv.append(limparTexto(dto.getEstado())).append(",");
            csv.append(dto.getQuantidadePessoas()).append(",");
            csv.append(dto.getIdadeMedia()).append("\n");
        }

        return converterParaUtf8ComBom(csv.toString());
    }


    public byte[] gerarRelatorioPorEstadoCsv() throws SQLException {

        List<RelatorioEstadoDTO> dados =
                clienteDAO.buscarRelatorioPorEstado();

        StringBuilder csv = new StringBuilder();

        csv.append("estado,quantidade_pessoas,idade_media\n");

        for (RelatorioEstadoDTO dto : dados) {
            csv.append(limparTexto(dto.getEstado())).append(",");
            csv.append(dto.getQuantidadePessoas()).append(",");
            csv.append(dto.getIdadeMedia()).append("\n");
        }

        return converterParaUtf8ComBom(csv.toString());
    }


    public byte[] gerarRelatorioUnificadoCsv() throws SQLException { // <-- ADICIONADO

        List<RelatorioCidadeDTO> cidades =
                clienteDAO.buscarRelatorioPorCidade();

        List<RelatorioEstadoDTO> estados =
                clienteDAO.buscarRelatorioPorEstado();


        Map<String, RelatorioEstadoDTO> mapaEstados = new HashMap<>(); // <-- ADICIONADO
        for (RelatorioEstadoDTO estado : estados) {
            mapaEstados.put(estado.getEstado(), estado);
        }

        StringBuilder csv = new StringBuilder();

        csv.append(
                "cidade,estado," +
                        "quantidade_pessoas_estado," +
                        "quantidade_pessoas_cidade," +
                        "idade_media_estado," +
                        "idade_media_cidade\n"
        );

        for (RelatorioCidadeDTO cidade : cidades) {

            RelatorioEstadoDTO estado =
                    mapaEstados.get(cidade.getEstado());

            int quantidadeEstado = (estado != null)
                    ? estado.getQuantidadePessoas()
                    : 0;

            double idadeMediaEstado = (estado != null)
                    ? estado.getIdadeMedia()
                    : 0;

            csv.append(limparTexto(cidade.getCidade())).append(",");
            csv.append(limparTexto(cidade.getEstado())).append(",");
            csv.append(quantidadeEstado).append(",");
            csv.append(cidade.getQuantidadePessoas()).append(",");
            csv.append(idadeMediaEstado).append(",");
            csv.append(cidade.getIdadeMedia()).append("\n");
        }

        return converterParaUtf8ComBom(csv.toString());
    }


    private byte[] converterParaUtf8ComBom(String csv) {

        byte[] bom = new byte[] {
                (byte) 0xEF,
                (byte) 0xBB,
                (byte) 0xBF
        };

        byte[] conteudo = csv.getBytes(StandardCharsets.UTF_8);

        byte[] resultado = new byte[bom.length + conteudo.length];

        System.arraycopy(bom, 0, resultado, 0, bom.length);
        System.arraycopy(conteudo, 0, resultado, bom.length, conteudo.length);

        return resultado;
    }


    private String limparTexto(String texto) {

        if (texto == null) {
            return "";
        }

        return texto
                .replace(",", " ")
                .replace(";", " ")
                .trim();
    }
}
