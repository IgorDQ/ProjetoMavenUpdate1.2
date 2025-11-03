package view;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.view.ValidadorCampos;

class ValidadorCamposTest {

    // Test 1 – Nome inválido
    @Test
    void deveLancarExcecaoParaNomeInvalido() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                ValidadorCampos.validarNomeOuCidade("Jo", "Nome")
        );
        assertEquals("Nome inválido. Deve ter pelo menos 3 caracteres.", ex.getMessage());
    }

    // Test 2 – Cidade inválida
    @Test
    void deveLancarExcecaoParaCidadeInvalida() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                ValidadorCampos.validarNomeOuCidade("A", "Cidade")
        );
        assertEquals("Cidade inválido. Deve ter pelo menos 3 caracteres.", ex.getMessage());
    }

    // Test 3 – Logradouro inválido
    @Test
    void deveLancarExcecaoParaLogradouroInvalido() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                ValidadorCampos.validarEndereco("R", "123", "", "São Paulo", "SP", "Brasil")
        );
        assertEquals("Logradouro inválido. Deve ter pelo menos 3 caracteres.", ex.getMessage());
    }

    // Test 4 – UF inválida
    @Test
    void deveLancarExcecaoParaUFInvalida() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                ValidadorCampos.validarEndereco("Rua A", "123", "", "São Paulo", "Sao", "Brasil")
        );
        assertEquals("UF inválida. Use 2 letras maiúsculas.", ex.getMessage());
    }

    // Teste adicional – Pais inválido
    @Test
    void deveLancarExcecaoParaPaisInvalido() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                ValidadorCampos.validarEndereco("Rua A", "123", "", "São Paulo", "SP", "Br")
        );
        assertEquals("País inválido. Deve ter pelo menos 3 caracteres.", ex.getMessage());
    }
}
