package org.example.model;

import org.example.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO {

    public int salvar(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, idade, cidade) VALUES (?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getIdade());
            stmt.setString(3, cliente.getCidade());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {

            throw new SQLException("Erro ao salvar cliente (Pode ser violação de regra, ex: Idade): " + e.getMessage());
        }
        return generatedId;
    }

    public List<Cliente> listar() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente_completo_view";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Endereco endereco = null;
                if (rs.getString("logradouro") != null) {
                    endereco = new Endereco(
                            rs.getString("logradouro"),
                            rs.getString("numero"),
                            rs.getString("complemento"),
                            rs.getString("municipio"),
                            rs.getString("unidade_federal"),
                            rs.getString("pais"),
                            true
                    );
                }

                Cliente c = new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("cidade"),
                        endereco
                );
                clientes.add(c);
            }

        } catch (SQLException e) {
            throw new SQLException("Erro ao listar clientes: " + e.getMessage());
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
                Endereco endereco = null;
                if (rs.getString("logradouro") != null) {
                    endereco = new Endereco(
                            rs.getString("logradouro"),
                            rs.getString("numero"),
                            rs.getString("complemento"),
                            rs.getString("municipio"),
                            rs.getString("unidade_federal"),
                            rs.getString("pais"),
                            true
                    );
                }

                return new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("cidade"),
                        endereco
                );
            }
            return null;

        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar cliente: " + e.getMessage());
        }
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

        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar cliente: " + e.getMessage());
        }
    }
}