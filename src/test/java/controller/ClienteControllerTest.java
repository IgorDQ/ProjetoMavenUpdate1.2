package controller;

import org.example.controller.ClienteController;
import org.example.model.Cliente;
import org.example.model.ClienteDAO;
import org.example.model.Endereco;
import org.example.model.EnderecoDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    @Mock
    private ClienteDAO clienteDao;

    @Mock
    private EnderecoDAO enderecoDao;

    @InjectMocks
    private ClienteController controller;

    private Endereco enderecoExemplo;
    private Cliente clienteExemplo;

    @BeforeEach
    void setup() {
        enderecoExemplo = new Endereco(
                "Rua A", "123", "Bloco 2",
                "São Paulo", "SP", "Brasil"
        );

        clienteExemplo = new Cliente("João", 25, "São Paulo");
    }

    // TESTE 1: Cadastrod bem-sucedido
    @Test
    void deveCadastrarClienteComEnderecoValido() throws SQLException {
        when(clienteDao.salvar(any(Cliente.class))).thenReturn(1);

        controller.cadastrar("João", 25, "São Paulo", enderecoExemplo);

        verify(clienteDao, times(1)).salvar(any(Cliente.class));
        verify(enderecoDao, times(1)).salvarComProcedure(eq(1), eq(enderecoExemplo));
    }

    // TESTE 2: Falha ao salvar cliente
    @Test
    void deveLancarExcecaoQuandoSalvarClienteFalhar() throws SQLException {
        when(clienteDao.salvar(any(Cliente.class))).thenReturn(-1);

        SQLException ex = assertThrows(SQLException.class, () ->
                controller.cadastrar("Maria", 30, "Campinas", enderecoExemplo)
        );

        assertEquals("Falha ao obter ID do cliente após o cadastro.", ex.getMessage());
    }

    // TESTE 3: Lista de clientes
    @Test
    void deveListarClientesCorretamente() throws SQLException {
        List<Cliente> clientesMock = Arrays.asList(
                new Cliente("Ana", 22, "Curitiba"),
                new Cliente("Bruno", 28, "Belo Horizonte")
        );
        when(clienteDao.listar()).thenReturn(clientesMock);

        List<Cliente> resultado = controller.listar();

        assertEquals(2, resultado.size());
        assertEquals("Ana", resultado.get(0).getNome());
        verify(clienteDao, times(1)).listar();
    }

    // TESTE 4: Deletar de cliente
    @Test
    void deveDeletarClientePorId() throws SQLException {
        when(clienteDao.deletar(1)).thenReturn(true);

        boolean resultado = controller.deletar(1);

        assertTrue(resultado);
        verify(clienteDao, times(1)).deletar(1);
    }
}
