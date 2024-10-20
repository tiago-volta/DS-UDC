package e4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EuroCoinTest {
    private EuroCoin coin1;
    private EuroCoin coin2;

    @BeforeEach
    void setUp() {
        coin1 = new EuroCoin(200, EuroCoin.Color.ORO, EuroCoin.Pais.PT, "SIUUU", 2016);
        coin2 = new EuroCoin(200, EuroCoin.Color.ORO, EuroCoin.Pais.PT, "SIUUU", 2016);
    }

    //Test del constructor y validación de getters
    @Test
    void testConstructor() {
        assertNotNull(coin1);
        assertEquals(200, coin1.getValor());
        assertEquals(EuroCoin.Color.ORO, coin1.getColor());
        assertEquals(EuroCoin.Pais.PT, coin1.getPais());
        assertEquals("SIUUU", coin1.getDiseno());
        assertEquals(2016, coin1.getAnoAcunacion());
    }

    //Prueba para verificar el comportamiento del constructor con valores límite
    @Test
    void testConstructor_LimitValues() {
        Exception minValueException = assertThrows(IllegalArgumentException.class, () -> {
            new EuroCoin(0, EuroCoin.Color.BRONCE, EuroCoin.Pais.ES, "Min", 2021);
        });
        assertEquals("El valor de la moneda debe ser uno de los valores permitidos: 1, 2, 5, 10, 20, 50, 100, 200 céntimos.", minValueException.getMessage());

        EuroCoin maxCoin = new EuroCoin(200, EuroCoin.Color.ORO, EuroCoin.Pais.FR, "Max", 2021);
        assertEquals(200, maxCoin.getValor());
    }

    //Test que verifica la igualdad y el hash code de dos monedas
    @Test
    void testEqualityAndHashCode() {
        assertEquals(coin1, coin2); //Verifica igualdad
        assertEquals(coin1.hashCode(), coin2.hashCode()); //Verifica hash code
    }

    //Test para validar diferencias entre monedas
    @Test
    void testCoinDifferences() {
        EuroCoin coinGold = new EuroCoin(100, EuroCoin.Color.ORO, EuroCoin.Pais.IE, "Diseño1", 2018);
        EuroCoin coinSilver18 = new EuroCoin(100, EuroCoin.Color.PLATA, EuroCoin.Pais.IE, "Diseño1", 2018);
        EuroCoin coinSilver24 = new EuroCoin(100, EuroCoin.Color.PLATA, EuroCoin.Pais.IE, "Diseño1", 2024);
        EuroCoin coinDesign1 = new EuroCoin(200, EuroCoin.Color.ORO, EuroCoin.Pais.PT, "Diseño A", 2020);
        EuroCoin coinDesign2 = new EuroCoin(200, EuroCoin.Color.ORO, EuroCoin.Pais.PT, "Diseño B", 2020);


        assertEquals(coinGold, coinSilver18);
        assertEquals(coinGold, coinSilver24);    //Se prueba que son iguales a pesar del año y del color
        assertNotEquals(coinGold, coinDesign1); //Diferentes  y países
        assertNotEquals(coinDesign1, coinDesign2); //Diferentes diseños
        assertNotEquals(coinGold, null); //Comparación con null
    }

    //Test de validación de atributos incorrectos
    @Test
    void testInvalidCoinAttributes() {
        //Valor negativo
        Exception valueException = assertThrows(IllegalArgumentException.class, () -> {
            new EuroCoin(-1, EuroCoin.Color.ORO, EuroCoin.Pais.ES, "Andre", 2021);
        });
        assertEquals("El valor de la moneda debe ser uno de los valores permitidos: 1, 2, 5, 10, 20, 50, 100, 200 céntimos.", valueException.getMessage());

        //Año de acuñación fuera de rango
        Exception yearException = assertThrows(IllegalArgumentException.class, () -> {
            new EuroCoin(100, EuroCoin.Color.ORO, EuroCoin.Pais.ES, "Tiago", 1000);
        });
        assertEquals("El año de acuñación debe estar entre 1999 y 2024.", yearException.getMessage());

        //País nulo
        Exception countryException = assertThrows(IllegalArgumentException.class, () -> {
            new EuroCoin(100, EuroCoin.Color.BRONCE, null, "Cristiano", 2021);
        });
        assertEquals("El país no puede ser nulo.", countryException.getMessage());
    }

    //Test para comprobar la salida del método toString
    @Test
    void testToString() {
        String expected1 = "EuroCoin{valor=200, color=ORO, pais=PT, diseno='SIUUU', anoAcunacion=2016}";
        assertEquals(expected1, coin1.toString());

        EuroCoin coinOther = new EuroCoin(100, EuroCoin.Color.PLATA, EuroCoin.Pais.ES, "Otro", 2019);
        String expected2 = "EuroCoin{valor=100, color=PLATA, pais=ES, diseno='Otro', anoAcunacion=2019}";
        assertEquals(expected2, coinOther.toString());

        //Validar que no produce null o excepciones
        assertNotNull(coinOther.toString());
    }
}

