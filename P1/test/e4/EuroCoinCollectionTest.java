package e4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EuroCoinCollectionTest {
    private EuroCoinCollection collection;

    @BeforeEach
    void setUp() {
        collection = new EuroCoinCollection();
    }

    //Prueba para añadir monedas a la colección
    @Test
    void testAddCoins() {
        EuroCoin coin1 = new EuroCoin(200, EuroCoin.Color.ORO, EuroCoin.Pais.PT, "SIUUU", 2016);
        EuroCoin coin2 = new EuroCoin(100, EuroCoin.Color.PLATA, EuroCoin.Pais.FR, "Benzema", 2019);
        EuroCoin coin3 = new EuroCoin(50, EuroCoin.Color.BRONCE, EuroCoin.Pais.ES, "CoinES", 2021);

        //Añadir monedas diferentes
        assertTrue(collection.addCoin(coin1));
        assertTrue(collection.addCoin(coin2));
        assertTrue(collection.addCoin(coin3));

        //Verificar número de monedas
        assertEquals(3, collection.totalCoins());

        //Añadir moneda duplicada
        assertFalse(collection.addCoin(coin1));  //Ya existe

        //Intentar añadir moneda nula
        Exception exception = assertThrows(IllegalArgumentException.class, () -> collection.addCoin(null));
        assertEquals("La moneda no puede ser nula", exception.getMessage());
    }

    //Prueba para eliminar monedas de la colección y verificacion con colección vacía
    @Test
    void testRemoveCoins() {
        EuroCoin coin1 = new EuroCoin(200, EuroCoin.Color.ORO, EuroCoin.Pais.PT, "SIUUU", 2016);
        EuroCoin coin2 = new EuroCoin(100, EuroCoin.Color.PLATA, EuroCoin.Pais.FR, "Benzema", 2019);

        //Intento de eliminar moneda de colección vacía
        assertFalse(collection.removeCoin(coin1));

        //Añadir y eliminar monedas
        collection.addCoin(coin1);
        assertTrue(collection.removeCoin(coin1));  //Moneda eliminada correctamente
        assertFalse(collection.removeCoin(coin1)); //Moneda ya no existe

        //Intento de eliminar moneda que no existe en la colección
        assertFalse(collection.removeCoin(coin2));

        //Intentar eliminar moneda nula
        Exception exception = assertThrows(IllegalArgumentException.class, () -> collection.removeCoin(null));
        assertEquals("La moneda no puede ser nula", exception.getMessage());
    }

    //Prueba para verificar el valor total de las monedas y comportamiento con colección vacía
    @Test
    void testTotalValueAndEmptyCollection() {
        //Verificar comportamiento con colección vacía
        assertEquals(0, collection.totalCoins());
        assertEquals(0, collection.totalValue());

        //Añadir varias monedas y verificar el valor total
        collection.addCoin(new EuroCoin(50, EuroCoin.Color.BRONCE, EuroCoin.Pais.ES, "Coin1", 2021));
        collection.addCoin(new EuroCoin(100, EuroCoin.Color.PLATA, EuroCoin.Pais.FR, "Coin2", 2019));
        collection.addCoin(new EuroCoin(200, EuroCoin.Color.ORO, EuroCoin.Pais.PT, "Coin3", 2016));

        assertEquals(3, collection.totalCoins());

        assertEquals(350, collection.totalValue());
    }

    //Prueba para verificar si la colección contiene una moneda específica
    @Test
    void testContainsCoin() {
        EuroCoin coin = new EuroCoin(200, EuroCoin.Color.ORO, EuroCoin.Pais.PT, "SIUUU", 2016);
        assertFalse(collection.containsCoin(coin)); //Antes de añadir
        collection.addCoin(coin);
        assertTrue(collection.containsCoin(coin)); //Después de añadir
    }
}
