package org.example.model;

import org.example.DatabaseConnection;
import org.example.dto.PessoaEnderecoDTO;
import org.example.dto.RelatorioCidadeDTO;
import org.example.dto.RelatorioEstadoDTO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteDAO {


    public int salvar(Cliente cliente) throws SQLException {

        String sql = "INSERT INTO clientes (nome, idade, cidade) VALUES (?, ?, ?)";
        int idGerado = -1;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getIdade());
            stmt.setString(3, cliente.getCidade());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idGerado = rs.getInt(1);
            }
        }

        return idGerado;
    }

    public List<Cliente> listar() throws SQLException {

        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente_completo_view";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Endereco endereco = new Endereco(
                        rs.getString("logradouro"),
                        rs.getInt("numero"),
                        rs.getString("complemento"),
                        rs.getString("municipio"),
                        rs.getString("unidade_federal"),
                        rs.getString("pais"),
                        true
                );

                Cliente cliente = new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("cidade"),
                        endereco
                );

                clientes.add(cliente);
            }
        }

        return clientes;
    }

    public Cliente buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM cliente_completo_view WHERE cliente_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Endereco endereco = new Endereco(
                        rs.getString("logradouro"),
                        rs.getInt("numero"),
                        rs.getString("complemento"),
                        rs.getString("municipio"),
                        rs.getString("unidade_federal"),
                        rs.getString("pais"),
                        true
                );

                return new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("cidade"),
                        endereco
                );
            }
        }

        return null;
    }

    public boolean atualizar(Cliente cliente) throws SQLException {

        String sql = "UPDATE clientes SET nome = ?, idade = ?, cidade = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getIdade());
            stmt.setString(3, cliente.getCidade());
            stmt.setInt(4, cliente.getId());

            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deletar(int id) throws SQLException {

        String sql = "DELETE FROM clientes WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    public List<PessoaEnderecoDTO> listarPorLogradouro(String logradouro) throws SQLException {

        String sql = """
            SELECT nome, idade, cidade, unidade_federal AS estado
            FROM cliente_completo_view
            WHERE pais = 'Brasil'
              AND logradouro LIKE ?
        """;

        List<PessoaEnderecoDTO> pessoas = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + logradouro + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                pessoas.add(new PessoaEnderecoDTO(
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("cidade"),
                        rs.getString("estado")
                ));
            }
        }

        return pessoas;
    }

    public List<RelatorioCidadeDTO> buscarRelatorioPorCidade() throws SQLException {

        String sql = """
            SELECT
                cidade,
                unidade_federal AS estado,
                COUNT(*) AS quantidade_pessoas,
                AVG(idade) AS idade_media
            FROM cliente_completo_view
            WHERE pais = 'Brasil'
            GROUP BY cidade, unidade_federal
            ORDER BY cidade
        """;

        List<RelatorioCidadeDTO> resultado = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                resultado.add(new RelatorioCidadeDTO(
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getInt("quantidade_pessoas"),
                        rs.getDouble("idade_media")
                ));
            }
        }

        return resultado;
    }


    public List<RelatorioEstadoDTO> buscarRelatorioPorEstado() throws SQLException {

        String sql = """
            SELECT
                unidade_federal AS estado,
                COUNT(*) AS quantidade_pessoas,
                AVG(idade) AS idade_media
            FROM cliente_completo_view
            WHERE pais = 'Brasil'
            GROUP BY unidade_federal
            ORDER BY unidade_federal
        """;

        List<RelatorioEstadoDTO> resultado = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                resultado.add(new RelatorioEstadoDTO(
                        rs.getString("estado"),
                        rs.getInt("quantidade_pessoas"),
                        rs.getDouble("idade_media")
                ));
            }
        }

        return resultado;
    }
}
