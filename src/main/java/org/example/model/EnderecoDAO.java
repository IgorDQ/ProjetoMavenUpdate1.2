package org.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnderecoDAO {

    public void salvar(int clienteId, Endereco endereco) throws SQLException {

        String sql = """
            INSERT INTO endereco
            (logradouro, numero, complemento, municipio, uf, pais, ativo, cliente_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DatabaseConnectionModel.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, endereco.getLogradouro());
            stmt.setInt(2, endereco.getNumero());
            stmt.setString(3, endereco.getComplemento());
            stmt.setString(4, endereco.getMunicipio());
            stmt.setString(5, endereco.getUf());
            stmt.setString(6, endereco.getPais());
            stmt.setBoolean(7, endereco.isAtivo());
            stmt.setInt(8, clienteId);

            stmt.executeUpdate();
        }
    }
}
