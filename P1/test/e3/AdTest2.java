package e3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AdTest2 {

    // Test 1: Comprobar que se lanza una excepción si la propiedad es nula
    @Test
    public void testNull() {
        Property property = new Property(PropertyType.APARTMENT, "1234567890", "Calle Falsa 123", "12345", 80, 2, 1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> { new Ad("Agency1", null, AdType.PURCHASE, 100000); });
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> { new Ad(null, property, AdType.PURCHASE, 100000); });
        IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class, () -> { new Ad("Agency1", property, null, 100000); });
        assertEquals("La propiedad o la agencia no puede ser nula", exception.getMessage());
        assertEquals("La propiedad o la agencia no puede ser nula", exception2.getMessage());
        assertEquals("El tipo de anuncio debe ser válido", exception3.getMessage());
    }

    // Test 2: Comprobar que se lanza una excepción si el precio es inválido (0 o negativo)
    @Test
    public void testInvalidPrice() {
        Property property = new Property(PropertyType.APARTMENT, "1234567890", "Calle Falsa 123", "12345", 80, 2, 1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> { new Ad("Agency1", property, AdType.PURCHASE, -50000); });
        assertEquals("El precio debe ser mayor que 0", exception.getMessage());
    }

    // Test 3: Comprobar que dos anuncios con la misma propiedad son considerados iguales
    @Test
    public void testSameProperty() {
        Property property1 = new Property(PropertyType.APARTMENT, "1234567890", "Calle Falsa 123", "12345", 80, 2, 1);
        Property property2 = new Property(PropertyType.APARTMENT, "1234567890", "Calle Falsa 123", "12345", 80, 2, 1);

        Ad ad1 = new Ad("Agency1", property1, AdType.PURCHASE, 100000);
        Ad ad2 = new Ad("Agency2", property2, AdType.RENTAL, 90000);

        assertTrue(ad1.isPropertyEqual(ad2), "Los anuncios deberían ser considerados de la misma propiedad");
    }

    // Test 4: Comprobar que se calcula correctamente el precio por metro cuadrado
    @Test
    public void testPricePerSquareMeter() {
        Property property = new Property(PropertyType.APARTMENT, "1234567890", "Calle Falsa 123", "12345", 80, 2, 1);
        Ad ad = new Ad("Agency1", property, AdType.PURCHASE, 160000);
        assertEquals(2000, ad.priceMetersEuros(), "El precio por metro cuadrado debería ser 2000");
    }

    // Test 5: Comprobar que se lanza una excepción si el porcentaje de reducción es inválido
    @Test
    public void testReducePriceInvalid() {
        Property property = new Property(PropertyType.APARTMENT, "1234567890", "Calle Falsa 123", "12345", 80, 2, 1);
        Ad ad = new Ad("Agency1", property, AdType.PURCHASE, 160000);

        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> { ad.dropPrice(-10); });
        assertEquals("El porcentaje debe estar entre 0 y 100", exception1.getMessage());
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> { ad.dropPrice(110); });
        assertEquals("El porcentaje debe estar entre 0 y 100", exception2.getMessage());
    }
}
