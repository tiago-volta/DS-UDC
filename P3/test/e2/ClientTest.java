package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    private Client simpleClient;
    private Client detailedClient;

    @BeforeEach
    public void setUp() {
        simpleClient = new Client(new SimpleDisplayStrategy());
        detailedClient = new Client(new DetailedDisplayStrategy());
    }

    @Test
    public void testClientInitialization() {
        assertNotNull(simpleClient);
        assertNotNull(detailedClient);
    }

    @Test
    public void testSimpleClientStrategy() {
        assertTrue(simpleClient.getStrategy() instanceof SimpleDisplayStrategy);
    }

    @Test
    public void testDetailedClientStrategy() {
        assertTrue(detailedClient.getStrategy() instanceof DetailedDisplayStrategy);
    }

    @Test
    public void testSimpleClientUpdate() {
        DatosAccion data = new DatosAccion("AAPL", 150.00, 155.00, 145.00, 1000);
        assertDoesNotThrow(() -> simpleClient.update(data));
    }

    @Test
    public void testDetailedClientUpdate() {
        DatosAccion data = new DatosAccion("AAPL", 150.00, 155.00, 145.00, 1000);
        assertDoesNotThrow(() -> detailedClient.update(data));
    }

    @Test
    public void testCreateSimpleClient() {
        // Prueba la creación de un cliente simple
        Client client = Client.createClient("sencillo");
        assertNotNull(client);
        assertTrue(client.getStrategy() instanceof SimpleDisplayStrategy);
    }

    @Test
    public void testCreateDetailedClient() {
        // Prueba la creación de un cliente detallado
        Client client = Client.createClient("detallado");
        assertNotNull(client);
        assertTrue(client.getStrategy() instanceof DetailedDisplayStrategy);
    }

    @Test
    public void testCreateClientWithUnknownType() {
        // Verifica que se lanza una excepción cuando se proporciona un tipo de cliente desconocido
        assertThrows(IllegalArgumentException.class, () -> Client.createClient("desconocido"));
    }

    @Test
    public void testCreateClientWithEmptyType() {
        // Verifica que se lanza una excepción cuando se pasa una cadena vacía como tipo
        assertThrows(IllegalArgumentException.class, () -> Client.createClient(""));
    }

    @Test
    public void testCreateClientWithNullType() {
        // Verifica que se lanza una excepción cuando se pasa un tipo de cliente null
        assertThrows(IllegalArgumentException.class, () -> Client.createClient(null));
    }

    @Test
    public void testCreateClientWithSpacesOnly() {
        // Verifica que se lanza una excepción si se pasa un tipo de cliente con espacios solamente
        assertThrows(IllegalArgumentException.class, () -> Client.createClient("   "));
    }

    @Test
    public void testCreateMultipleClients() {
        // Verifica la creación de múltiples clientes y asegura que no se solapan
        Client simpleClient = Client.createClient("sencillo");
        assertNotNull(simpleClient);
        Client detailedClient = Client.createClient("detallado");
        assertNotNull(detailedClient);

        // Verifica que los clientes son de tipos diferentes
        assertTrue(simpleClient.getStrategy() instanceof SimpleDisplayStrategy);
        assertTrue(detailedClient.getStrategy() instanceof DetailedDisplayStrategy);
    }

    @Test
    public void testCreateDetailedClientAfterSimple() {
        // Verifica que la creación de un cliente detallado después de uno simple no da errores
        Client simpleClient = Client.createClient("sencillo");
        assertNotNull(simpleClient);
        Client detailedClient = Client.createClient("detallado");
        assertNotNull(detailedClient);

        // Verifica que el cliente detallado sigue siendo del tipo correcto
        assertTrue(detailedClient.getStrategy() instanceof DetailedDisplayStrategy);
    }

    @Test
    public void testCreateSimpleClientAfterDetailed() {
        // Verifica que la creación de un cliente simple después de uno detallado no da errores
        Client detailedClient = Client.createClient("detallado");
        assertNotNull(detailedClient);
        Client simpleClient = Client.createClient("sencillo");
        assertNotNull(simpleClient);

        // Verifica que el cliente simple sigue siendo del tipo correcto
        assertTrue(simpleClient.getStrategy() instanceof SimpleDisplayStrategy);
    }
}
