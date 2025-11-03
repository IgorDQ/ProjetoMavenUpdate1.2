package model;

import org.example.model.Cliente;
import org.example.model.Endereco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private Endereco criaEnderecoExemplo() {
        return new Endereco(
                "Rua A",      // logradouro
                "123",        // numero
                "Apto 12",    // complemento
                "São Paulo",  // município
                "SP",         // unidade federativa (UF)2 letras maiúsculas
                "Brasil"      // país
        );
    }

    // Teste 1- criação de cliente com todos os dados
    @Test
    void deveCriarClienteComTodosOsDados() {
        Endereco endereco = criaEnderecoExemplo();
        Cliente cliente = new Cliente(1, " João ", 30, " São Paulo ", endereco);

        assertEquals(1, cliente.getId());
        assertEquals("João", cliente.getNome());
        assertEquals(30, cliente.getIdade());
        assertEquals("São Paulo", cliente.getCidade());
        assertEquals(endereco, cliente.getEndereco());
    }
    // Teste 2- metodo toString() do Cliente
    @Test
    void toStringDeveConterInformacoesDoCliente() {
        Endereco endereco = criaEnderecoExemplo();
        Cliente cliente = new Cliente(5, "Carlos", 50, "Niterói", endereco);

        String resultado = cliente.toString();

        assertTrue(resultado.contains("Carlos"));
        assertTrue(resultado.contains("50"));
        assertTrue(resultado.contains("Niterói"));
        assertTrue(resultado.contains("Endereço"));
    }
}
