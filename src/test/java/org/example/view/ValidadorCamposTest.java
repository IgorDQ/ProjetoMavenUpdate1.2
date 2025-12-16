package org.example.view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidadorCamposTest {

    // Teste 1 – Nome inválido
    @Test
    void deveLancarExcecaoParaNomeInvalido() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                ValidadorCampos.validarNomeOuCidade("Jo", "Nome")
        );

        assertEquals("Nome inválido. Deve ter pelo menos 3 caracteres.", ex.getMessage());
    }

    // Teste 2 – Cidade inválida
    @Test
    void deveLancarExcecaoParaCidadeInvalida() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                ValidadorCampos.validarNomeOuCidade("A", "Cidade")
        );

        assertEquals("Cidade inválido. Deve ter pelo menos 3 caracteres.", ex.getMessage());
    }

    // Teste 3 – Logradouro inválido
    @Test
    void deveLancarExcecaoParaLogradouroInvalido() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                ValidadorCampos.validarEndereco(
                        "R",        // logradouro inválido
                        123,        // ✅ numero como int
                        "",
                        "São Paulo",
                        "SP",
                        "Brasil"
                )
        );

        assertEquals("Logradouro inválido. Deve ter pelo menos 3 caracteres.", ex.getMessage());
    }

    // Teste 4 – UF inválida
    @Test
    void deveLancarExcecaoParaUFInvalida() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                ValidadorCampos.validarEndereco(
                        "Rua A",
                        123,
                        "",
                        "São Paulo",
                        "Sao",      // UF inválida
                        "Brasil"
                )
        );

        assertEquals("UF inválida. Use 2 letras maiúsculas.", ex.getMessage());
    }

    // Teste 5 – País inválido
    @Test
    void deveLancarExcecaoParaPaisInvalido() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                ValidadorCampos.validarEndereco(
                        "Rua A",
                        123,
                        "",
                        "São Paulo",
                        "SP",
                        "Br"        // país inválido
                )
        );

        assertEquals("País inválido. Deve ter pelo menos 3 caracteres.", ex.getMessage());
    }
}

