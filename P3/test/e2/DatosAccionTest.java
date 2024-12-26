package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DatosAccionTest {
    private DatosAccion datosAccion;

    @BeforeEach
    public void setUp() {
        datosAccion = new DatosAccion("AAPL", 150.00, 155.00, 145.00, 1450);
    }

    @Test
    public void testGetSymbol() {
        assertEquals("AAPL", datosAccion.getSymbol());
    }

    @Test
    public void testGetClosePrice() {
        assertEquals(150.00, datosAccion.getClosePrice());
    }

    @Test
    public void testGetMaxPrice() {
        assertEquals(155.00, datosAccion.getMaxPrice());
    }

    @Test
    public void testGetMinPrice() {
        assertEquals(145.00, datosAccion.getMinPrice());
    }

    @Test
    public void testGetVolume() {
        assertEquals(1450, datosAccion.getVolumen());
    }

    @Test
    public void testInvalidSymbol() {
        assertThrows(IllegalArgumentException.class, () -> new DatosAccion("INVALID", 150.00, 155.00, 145.00, 1000));
    }

    @Test
    public void testInvalidPrices() {
        assertFalse(DatosAccion.isValidPrices(155.00, 150.00, 160.00));
    }

    @Test
    public void testValidPrices() {
        assertTrue(DatosAccion.isValidPrices(150.00, 155.00, 145.00));
    }

    @Test
    public void testEdgeCasePrices() {
        assertDoesNotThrow(() -> new DatosAccion("AAPL", 0.00, 0.00, 0.00, 0));
    }

    @Test
    public void testValidSymbolLength() {
        assertDoesNotThrow(() -> new DatosAccion("GOO", 150.00, 155.00, 145.00, 1000));
    }

    @Test
    public void testValidSymbol() {
        assertTrue(DatosAccion.isValidSymbol("AAPL"));
        assertTrue(DatosAccion.isValidSymbol("GOOG"));
        assertTrue(DatosAccion.isValidSymbol("MSFT"));
    }

    @Test
    public void testInvalidSymbolNull() {
        assertFalse(DatosAccion.isValidSymbol(null));
    }

    @Test
    public void testInvalidSymbolEmpty() {
        assertFalse(DatosAccion.isValidSymbol(""));
    }

    @Test
    public void testInvalidSymbolTooLong() {
        assertFalse(DatosAccion.isValidSymbol("TOOLONG"));
    }

    @Test
    public void testInvalidSymbolLowerCase() {
        assertFalse(DatosAccion.isValidSymbol("aapl"));
    }

    @Test
    public void testInvalidSymbolSpecialCharacters() {
        assertFalse(DatosAccion.isValidSymbol("AAP!"));
    }

    @Test
    public void testInvalidPricesNegative() {
        assertFalse(DatosAccion.isValidPrices(-150.00, 155.00, 145.00));
        assertFalse(DatosAccion.isValidPrices(150.00, -155.00, 145.00));
        assertFalse(DatosAccion.isValidPrices(150.00, 155.00, -145.00));
    }

    @Test
    public void testInvalidPricesMaxLessThanClose() {
        assertFalse(DatosAccion.isValidPrices(150.00, 145.00, 140.00));
    }

    @Test
    public void testInvalidPricesCloseLessThanMin() {
        assertFalse(DatosAccion.isValidPrices(140.00, 145.00, 150.00));
    }

    @Test
    public void testInvalidPricesMoreThanTwoDecimalPlaces() {
        assertFalse(DatosAccion.isValidPrices(150.001, 155.00, 145.00));
        assertFalse(DatosAccion.isValidPrices(150.00, 155.001, 145.00));
        assertFalse(DatosAccion.isValidPrices(150.00, 155.00, 145.001));
    }

    @Test
    public void testConstructorInvalidSymbol() {
        assertThrows(IllegalArgumentException.class, () -> new DatosAccion("INVALID", 150.00, 155.00, 145.00, 1000));
    }

}
