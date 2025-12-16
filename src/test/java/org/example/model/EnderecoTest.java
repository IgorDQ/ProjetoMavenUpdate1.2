package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    // Test 1– Criação de endereço válido e normalização dos campos
    @Test
    void deveCriarEnderecoValido() {
        Endereco end = new Endereco(" Rua A ", 123, "Apto 12", "São Paulo", "SP", "Brasil");

        assertEquals("Rua A", end.getLogradouro());
        assertEquals(123, end.getNumero()); // ✅ CORREÇÃO
        assertEquals("Apto 12", end.getComplemento());
        assertEquals("São Paulo", end.getMunicipio());
        assertEquals("SP", end.getUnidadeFederal());
        assertEquals("Brasil", end.getPais());
    }

    // Test 2– Lança exceção quando logradouro inválido
    @Test
    void deveLancarExcecaoQuandoLogradouroInvalido() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new Endereco("R", 123, "Apto 12", "São Paulo", "SP", "Brasil")
        );
        assertEquals("Logradouro inválido.", ex.getMessage());
    }

    // Teste 3– Lança exceção quando UF inválida
    @Test
    void deveLancarExcecaoQuandoUFInvalida() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                new Endereco("Rua A", 123, "Apto 12", "São Paulo", "Sao", "Brasil")
        );
        assertEquals("UF inválida. Use 2 caracteres.", ex.getMessage());
    }

    // Test 4– Verifica se o metodo toString retorna o endereço formatado corretamente
    @Test
    void toStringDeveRetornarEnderecoFormatado() {
        Endereco end = new Endereco("Rua A", 123, "", "São Paulo", "SP", "Brasil");

        String resultado = end.toString();
        assertTrue(resultado.contains("Rua A"));
        assertTrue(resultado.contains("123"));
        assertTrue(resultado.contains("São Paulo"));
        assertTrue(resultado.contains("SP"));
        assertTrue(resultado.contains("Brasil"));
        assertFalse(resultado.contains("Comp:"));
    }
}
