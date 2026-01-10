package org.example.service;

import org.example.dto.PessoaEnderecoDTO;
import org.example.model.ClienteDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PessoaService {

    private final ClienteDAO clienteDAO;

    public PessoaService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public List<PessoaEnderecoDTO> listarPorLogradouro(String logradouro) throws SQLException {

        if (logradouro == null || logradouro.isBlank()) {
            throw new IllegalArgumentException("Logradouro não informado");
        }

        if (logradouro.length() < 5) {
            throw new IllegalArgumentException(
                    "numero minimo de caracteres(5)"
            );
        }


        return clienteDAO.listarPorLogradouro(logradouro);
    }
}
