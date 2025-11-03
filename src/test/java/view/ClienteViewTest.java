package view;

import org.example.controller.ClienteController;
import org.example.model.Cliente;
import org.example.model.Endereco;
import org.example.view.ClienteView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteViewTest {

    private ClienteController controllerMock;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setup() {
        controllerMock = mock(ClienteController.class);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void cleanup() {
        System.setOut(originalOut);
    }

    // Test 1- Cadastro de cliente
    @Test
    void deveCadastrarClienteChamandoController() throws SQLException {
        String input = "João\n25\nSão Paulo\nRua A\n123\nApto 12\nSão Paulo\nSP\nBrasil\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ArgumentCaptor<String> nomeCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Integer> idadeCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> cidadeCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Endereco> enderecoCaptor = ArgumentCaptor.forClass(Endereco.class);

        ClienteViewTestHelper.cadastrar(sc, controllerMock);

        verify(controllerMock, times(1))
                .cadastrar(nomeCaptor.capture(), idadeCaptor.capture(), cidadeCaptor.capture(), enderecoCaptor.capture());

        assertEquals("João", nomeCaptor.getValue());
        assertEquals(25, idadeCaptor.getValue());
        assertEquals("São Paulo", cidadeCaptor.getValue());

        Endereco end = enderecoCaptor.getValue();
        assertEquals("Rua A", end.getLogradouro());
        assertEquals("123", end.getNumero());
        assertEquals("Apto 12", end.getComplemento());
        assertEquals("São Paulo", end.getMunicipio());
        assertEquals("SP", end.getUnidadeFederal());
        assertEquals("Brasil", end.getPais());
    }

    // Test 2: Listagem de clientes
    @Test
    void deveListarClientesNoConsole() throws SQLException {
        Cliente c1 = new Cliente(1, "João", 25, "São Paulo", null);
        Cliente c2 = new Cliente(2, "Maria", 30, "Rio de Janeiro", null);
        when(controllerMock.listar()).thenReturn(Arrays.asList(c1, c2));

        ClienteViewTestHelper.listar(controllerMock);

        String output = outContent.toString();
        assertTrue(output.contains("João"));
        assertTrue(output.contains("Maria"));
    }

    // Test 3- Buscar cliente por ID
    @Test
    void deveBuscarClientePorId() throws SQLException {
        Cliente c = new Cliente(1, "João", 25, "São Paulo", null);
        when(controllerMock.buscarPorId(1)).thenReturn(c);

        String input = "1\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ClienteViewTestHelper.buscar(sc, controllerMock);

        String output = outContent.toString();
        assertTrue(output.contains("João"));
        assertTrue(output.contains("25"));
        assertTrue(output.contains("São Paulo"));
    }

    // Test 4- Tratamento de entrada inválida
    @Test
    void deveTratarEntradaIdadeInvalida() throws SQLException {
        String input = "João\nabc\n25\nSão Paulo\nRua A\n123\nApto 12\nSão Paulo\nSP\nBrasil\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));

        assertDoesNotThrow(() -> ClienteViewTestHelper.cadastrar(sc, controllerMock));

        verify(controllerMock, times(1)).cadastrar(eq("João"), eq(25), eq("São Paulo"), any(Endereco.class));
    }

    // acessar métodos privados da view
    static class ClienteViewTestHelper {

        static void cadastrar(Scanner sc, ClienteController controller) throws SQLException {
            try {
                java.lang.reflect.Method m = ClienteView.class.getDeclaredMethod("cadastrar", Scanner.class, ClienteController.class);
                m.setAccessible(true);
                m.invoke(null, sc, controller);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        static void listar(ClienteController controller) throws SQLException {
            try {
                java.lang.reflect.Method m = ClienteView.class.getDeclaredMethod("listar", ClienteController.class);
                m.setAccessible(true);
                m.invoke(null, controller);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        static void buscar(Scanner sc, ClienteController controller) throws SQLException {
            try {
                java.lang.reflect.Method m = ClienteView.class.getDeclaredMethod("buscar", Scanner.class, ClienteController.class);
                m.setAccessible(true);
                m.invoke(null, sc, controller);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
