package org.example.model;

import org.example.DatabaseConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class EnderecoDAO {


    public void salvarComProcedure(int clienteId, Endereco endereco) throws SQLException {

        String sql = "{CALL sp_inserir_endereco(?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {


            cs.setInt(1, clienteId);
            cs.setString(2, endereco.getLogradouro());
            cs.setString(3, endereco.getNumero());
            cs.setString(4, endereco.getComplemento());
            cs.setString(5, endereco.getMunicipio());
            cs.setString(6, endereco.getUnidadeFederal());
            cs.setString(7, endereco.getPais());

            cs.execute();

        } catch (SQLException e) {
            throw new SQLException("Erro ao salvar endereço via Procedure: " + e.getMessage());
        }
    }
}