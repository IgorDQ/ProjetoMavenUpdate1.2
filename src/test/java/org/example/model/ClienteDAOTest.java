package org.example.model;

import org.example.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteDAOTest {

    private ClienteDAO dao;
    private Cliente clienteMock;

    @BeforeEach
    void setup() {
        dao = new ClienteDAO();
        clienteMock = new Cliente("João", 30, "São Paulo");
    }

    // Test 1- salvar cliente com sucesso
    @Test
    void deveSalvarClienteComSucesso() throws Exception {
        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        try (MockedStatic<DatabaseConnection> mocked =
                     mockStatic(DatabaseConnection.class)) {

            mocked.when(DatabaseConnection::getConnection).thenReturn(conn);

            when(conn.prepareStatement(
                    anyString(),
                    eq(Statement.RETURN_GENERATED_KEYS))
            ).thenReturn(stmt);

            when(stmt.executeUpdate()).thenReturn(1);
            when(stmt.getGeneratedKeys()).thenReturn(rs);
            when(rs.next()).thenReturn(true);
            when(rs.getInt(1)).thenReturn(10);

            int idGerado = dao.salvar(clienteMock);

            assertEquals(10, idGerado);
            verify(stmt, times(1)).executeUpdate();
        }
    }

    // Test 2- salvar cliente falhando
    @Test
    void deveLancarExcecaoAoFalharSalvarCliente() throws Exception {
        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);

        try (MockedStatic<DatabaseConnection> mocked =
                     mockStatic(DatabaseConnection.class)) {

            mocked.when(DatabaseConnection::getConnection).thenReturn(conn);

            when(conn.prepareStatement(anyString(), anyInt())).thenReturn(stmt);
            when(stmt.executeUpdate()).thenThrow(new SQLException("Erro no banco"));

            SQLException ex = assertThrows(SQLException.class, () ->
                    dao.salvar(clienteMock)
            );

            assertTrue(ex.getMessage().contains("Erro ao salvar cliente"));
        }
    }

    // Test 3- buscar cliente por ID com sucesso
    @Test
    void deveBuscarClientePorIdComSucesso() throws Exception {
        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        try (MockedStatic<DatabaseConnection> mocked =
                     mockStatic(DatabaseConnection.class)) {

            mocked.when(DatabaseConnection::getConnection).thenReturn(conn);

            when(conn.prepareStatement(anyString())).thenReturn(stmt);
            when(stmt.executeQuery()).thenReturn(rs);
            when(rs.next()).thenReturn(true);

            when(rs.getInt("cliente_id")).thenReturn(1);
            when(rs.getString("nome")).thenReturn("João");
            when(rs.getInt("idade")).thenReturn(30);
            when(rs.getString("cidade")).thenReturn("São Paulo");

            when(rs.getString("logradouro")).thenReturn("Rua A");
            when(rs.getString("numero")).thenReturn("123");
            when(rs.getString("complemento")).thenReturn("Casa");
            when(rs.getString("municipio")).thenReturn("São Paulo");
            when(rs.getString("unidade_federal")).thenReturn("SP");
            when(rs.getString("pais")).thenReturn("Brasil");

            Cliente c = dao.buscarPorId(1);

            assertNotNull(c);
            assertEquals("João", c.getNome());
            assertEquals(30, c.getIdade());
        }
    }

    // Test 4- atualizar cliente com sucesso
    @Test
    void deveRetornarTrueQuandoAtualizarClienteComSucesso() throws Exception {
        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);

        try (MockedStatic<DatabaseConnection> mocked =
                     mockStatic(DatabaseConnection.class)) {

            mocked.when(DatabaseConnection::getConnection).thenReturn(conn);

            when(conn.prepareStatement(anyString())).thenReturn(stmt);
            when(stmt.executeUpdate()).thenReturn(1);

            boolean atualizado = dao.atualizar(
                    new Cliente(1, "Pedro", 40, "Campinas", null)
            );

            assertTrue(atualizado);
        }
    }
}
