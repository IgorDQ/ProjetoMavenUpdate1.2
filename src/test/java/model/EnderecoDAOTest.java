package org.example.model;

import org.example.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class EnderecoDAOTest {

    private EnderecoDAO enderecoDAO;
    private Connection mockConn;
    private CallableStatement mockStmt;

    @BeforeEach
    void setUp() throws SQLException {
        enderecoDAO = new EnderecoDAO();
        mockConn = mock(Connection.class);
        mockStmt = mock(CallableStatement.class);
    }

    //  Test 1- Deve chamar a procedure corretamente com os parâmetros
    @Test
    void deveChamarProcedureComParametrosCorretos() throws Exception {
        try (MockedStatic<DatabaseConnection> mocked = Mockito.mockStatic(DatabaseConnection.class)) {
            mocked.when(DatabaseConnection::getConnection).thenReturn(mockConn);
            when(mockConn.prepareCall("{CALL sp_inserir_endereco(?, ?, ?, ?, ?, ?, ?)}")).thenReturn(mockStmt);

            Endereco endereco = new Endereco("Rua A", "100", "Casa", "São Paulo", "SP", "Brasil", true);

            enderecoDAO.salvarComProcedure(1, endereco);

            verify(mockStmt).setInt(1, 1);
            verify(mockStmt).setString(2, "Rua A");
            verify(mockStmt).setString(3, "100");
            verify(mockStmt).setString(4, "Casa");
            verify(mockStmt).setString(5, "São Paulo");
            verify(mockStmt).setString(6, "SP");
            verify(mockStmt).setString(7, "Brasil");
            verify(mockStmt).execute();
        }
    }

    // Test 2- Deve lançar SQLException se ocorrer erro na procedure
    @Test
    void deveLancarSQLExceptionAoFalhar() throws Exception {
        try (MockedStatic<DatabaseConnection> mocked = Mockito.mockStatic(DatabaseConnection.class)) {
            mocked.when(DatabaseConnection::getConnection).thenReturn(mockConn);

            when(mockConn.prepareCall(anyString())).thenThrow(new SQLException("Erro fake"));

            Endereco endereco = new Endereco("Rua B", "200", null, "Rio", "RJ", "Brasil", true);

            SQLException thrown = assertThrows(SQLException.class, () ->
                    enderecoDAO.salvarComProcedure(2, endereco)
            );

            assertTrue(thrown.getMessage().contains("Erro ao salvar endereço via Procedure"));
        }
    }

    // Test 3- Deve lidar com valores nulos em campos opcionais
    @Test
    void deveAceitarCamposNulosSemErro() throws Exception {
        try (MockedStatic<DatabaseConnection> mocked = Mockito.mockStatic(DatabaseConnection.class)) {
            mocked.when(DatabaseConnection::getConnection).thenReturn(mockConn);
            when(mockConn.prepareCall(anyString())).thenReturn(mockStmt);

            Endereco endereco = new Endereco(null, null, null, "Curitiba", null, "Brasil", true);

            assertDoesNotThrow(() -> enderecoDAO.salvarComProcedure(3, endereco));

            verify(mockStmt).execute();
        }
    }
}
