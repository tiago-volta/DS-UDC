package e3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonedaTest {
    private Moneda coin1;
    private Moneda coin2;

    @BeforeEach
    void setUp() {
        coin1 = new Moneda(200, "PT" );
        coin2 = new Moneda(50, "ES");
    }

    //Test del constructor y validación de getters
    @Test
    void testConstructor() {
        assertNotNull(coin1);
        assertEquals(200, coin1.getValor());
        assertEquals("PT", coin1.getPais());
    }

    //Prueba para verificar el comportamiento del constructor con valores límite
    @Test
    void testConstructor_LimitValues() {
        Exception minValueException = assertThrows(IllegalArgumentException.class, () -> {
            new Moneda(0, "ES");
        });
        assertEquals("El valor de la moneda debe ser uno de los valores permitidos: 1, 2, 5, 10, 20, 50, 100, 200.", minValueException.getMessage());

        Moneda maxCoin = new Moneda(200, "FR");
        assertEquals(200, maxCoin.getValor());
    }


    //Test que verifica la igualdad y el hash code de dos monedas
    @Test
    void testEqualityAndHashCode() {
        Moneda coin1 = new Moneda(200, "PT");  //Mismo valor y pais
        Moneda coin2 = new Moneda(200, "PT");

        assertEquals(coin1, coin2);  //Verifica igualdad
        assertEquals(coin1.hashCode(), coin2.hashCode());  //Verifica hash code
    }

    //Test para validar diferencias entre monedas
    @Test
    void testCoinDifferences() {
        Moneda coinES = new Moneda(100, "ES");
        Moneda coinIE = new Moneda(100, "IE");

        assertNotEquals(coin1, coinES);  //Diferentes países
        assertNotEquals(coinES, coinIE);  //Diferentes países
        assertNotEquals(coin2, null);  //Comparação com null
    }

    //Test de validación de atributos incorrectos
    @Test
    void testInvalidCoinAttributes() {
        //Valor negativo
        Exception valueException = assertThrows(IllegalArgumentException.class, () -> {
            new Moneda(-1, "ES");
        });
        assertEquals("El valor de la moneda debe ser uno de los valores permitidos: 1, 2, 5, 10, 20, 50, 100, 200.", valueException.getMessage());

        //País nulo
        Exception countryException = assertThrows(IllegalArgumentException.class, () -> {
            new Moneda(100, null);
        });
        assertEquals("El país no puede ser nulo.", countryException.getMessage());
    }

    //Test para comprobar la salida del método toString
    @Test
    void testToString() {
        String expected1 = "EURO2-PT";
        assertEquals(expected1, coin1.toString());

        Moneda coinOther = new Moneda(50, "ES");
        String expected2 = "CENT50-ES";
        assertEquals(expected2, coinOther.toString());

        assertNotNull(coinOther.toString());  //Validar que no produce null o excepciones
    }
}

