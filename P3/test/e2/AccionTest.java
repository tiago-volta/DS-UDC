package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.function.Predicate;

public class AccionTest {
    private Accion accion;
    private Client simpleClient;
    private Client detailedClient;

    @BeforeEach
    public void setUp() {
        accion = new Accion();
        simpleClient = new Client(new SimpleDisplayStrategy());
        detailedClient = new Client(new DetailedDisplayStrategy());
    }

    @Test
    public void testRegisterObserver() {
        accion.registerObserver(simpleClient);
        assertEquals(1, accion.getObservers().size());
    }

    @Test
    public void testRemoveObserver() {
        accion.registerObserver(simpleClient);
        accion.removeObserver(simpleClient);
        assertEquals(0, accion.getObservers().size());
    }

    @Test
    public void testNotifyObservers() {
        accion.registerObserver(simpleClient);
        accion.registerObserver(detailedClient);
        DatosAccion stockData = new DatosAccion("AAPL", 300.00, 301.00, 145.00, 1000);
        accion.setStockData(stockData);
    }

    @Test
    public void testNotifyObserversWithFilter() {
        accion.registerObserver(simpleClient);
        accion.registerObserver(detailedClient);
        DatosAccion stockData = new DatosAccion("AAPL", 300.00, 301.00, 145.00, 1000);
        accion.setStockData(stockData);

        Predicate<Observador> detailedFilter = observer -> observer instanceof Client && ((Client) observer).getStrategy() instanceof DetailedDisplayStrategy;
        accion.notifyObservers(detailedFilter);
    }

    @Test
    public void testSetStockDataValid() {
        DatosAccion stockData = new DatosAccion("AAPL", 150.00, 155.00, 145.00, 1450);
        accion.setStockData(stockData);
        assertEquals(stockData, accion.getStockData());
    }


    @Test
    public void testSetStockDataInvalidPrices() {
        // Se crea un objeto DatosAccion con un precio de cierre con más de dos decimales
        DatosAccion stockData = new DatosAccion("AAPL", 150.001, 155.00, 145.00, 333);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> accion.setStockData(stockData));
        assertTrue(thrown.getMessage().contains("Precios inválidos"));
    }

    @Test
    public void testGetObserversUnmodifiable() {
        accion.registerObserver(simpleClient);
        assertThrows(UnsupportedOperationException.class, () -> accion.getObservers().add(detailedClient));
    }
}
