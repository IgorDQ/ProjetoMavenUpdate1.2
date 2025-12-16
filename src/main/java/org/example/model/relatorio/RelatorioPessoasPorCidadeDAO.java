package org.example.model.relatorio;

import org.example.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RelatorioPessoasPorCidadeDAO {

    public List<String[]> buscarDados() throws Exception {

        String sql = """
            SELECT
                cidade,
                unidade_federal AS estado,
                COUNT(*) AS quantidade_pessoas,
                ROUND(AVG(idade), 2) AS idade_media
            FROM cliente_completo_view
            WHERE pais = 'Brasil'
            GROUP BY cidade, unidade_federal
            ORDER BY estado, cidade
        """;

        List<String[]> linhas = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                linhas.add(new String[]{
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        String.valueOf(rs.getInt("quantidade_pessoas")),
                        rs.getString("idade_media")
                });
            }
        }

        return linhas;
    }
}
