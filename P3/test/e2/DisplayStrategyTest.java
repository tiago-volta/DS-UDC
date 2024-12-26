package e2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DisplayStrategyTest {

    @Test
    public void testSimpleDisplayStrategy() {
        SimpleDisplayStrategy strategy = new SimpleDisplayStrategy();
        DatosAccion data = new DatosAccion("AAPL", 150.00, 155.00, 145.00, 1000);
        assertDoesNotThrow(() -> strategy.display(data));
        // Verificación manual de la salida: "SimpleClient - Símbolo: AAPL, Cierre: 150.0"
    }

    @Test
    public void testDetailedDisplayStrategy() {
        DetailedDisplayStrategy strategy = new DetailedDisplayStrategy();
        DatosAccion data = new DatosAccion("AAPL", 150.00, 155.00, 145.00, 1000);
        assertDoesNotThrow(() -> strategy.display(data));
        // Verificación manual de la salida:
        // "DetailedClient - Símbolo: AAPL, Cierre: 150.0, Máximo: 155.0, Mínimo: 145.0, Volumen: 1000"
    }
}
