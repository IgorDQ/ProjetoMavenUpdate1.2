import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InicialTest {
    @Test
    void deveSomarCorretamente() {
        // ARRANGE
        int num1 = 5;
        int num2 = 3;
        int esperado = 8;

        // ACT
        int resultado = num1 + num2;

        // ASSERT
        assertEquals(esperado, resultado, "A soma de 5 + 3 deve ser 8");
    }

    @Test
    void deveSerFalso() {

        boolean valor = false;

        // ASSERT (Verifica)
        org.junit.jupiter.api.Assertions.assertFalse(valor);
    }

}
