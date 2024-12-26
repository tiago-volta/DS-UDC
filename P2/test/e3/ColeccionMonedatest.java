package e3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ColeccionMonedasTest {
    private ColeccionMonedas coleccion;

    @BeforeEach
    void setUp() {
        coleccion = new ColeccionMonedas(null);   //Iniciar sin filtro de país, requiere un parámetro String paisFiltro
    }

    //Prueba para añadir monedas a la colección
    @Test
    void testAddMonedas() {
        Moneda coin1 = new Moneda(200, "PT");
        Moneda coin2 = new Moneda(100, "FR");
        Moneda coin3 = new Moneda(50, "ES");

        //Añadir monedas diferentes
        coleccion.addMoneda(coin1);
        coleccion.addMoneda(coin2);
        coleccion.addMoneda(coin3);

        //Intentar añadir moneda nula
        Exception exception = assertThrows(IllegalArgumentException.class, () -> coleccion.addMoneda(null));
        assertEquals("La moneda no puede ser nula", exception.getMessage());
    }

    //Prueba de iteración de todas las monedas cuando paisFiltro es null
    @Test
    void testIteratorAllCoins() {
        coleccion.addMoneda(new Moneda(200, "PT"));
        coleccion.addMoneda(new Moneda(100, "FR"));
        coleccion.addMoneda(new Moneda(50, "ES"));

        Iterator<Moneda> iterator = coleccion.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            assertNotNull(iterator.next());
            count++;
        }
        assertEquals(3, count);   //Verifica si itera sobre todas las monedas
    }

    //Prueba de comportamiento fail-fast
    @Test
    void testFailFastBehavior() {
        coleccion.addMoneda(new Moneda(200, "PT"));
        coleccion.addMoneda(new Moneda(100, "FR"));

        Iterator<Moneda> iterator = coleccion.iterator();

        //Modificar la colección durante la iteración para activar fail-fast
        coleccion.addMoneda(new Moneda(50, "ES"));
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    //Prueba de remove() en el iterador
    @Test
    void testIteratorRemove() {
        Moneda coin1 = new Moneda(200, "PT");
        Moneda coin2 = new Moneda(100, "FR");

        coleccion.addMoneda(coin1);
        coleccion.addMoneda(coin2);

        Iterator<Moneda> iterator = coleccion.iterator();

        assertTrue(iterator.hasNext());
        Moneda coin = iterator.next();
        iterator.remove();   //Remueve la última moneda retornada por next()

        //Verifica si la moneda fue eliminada
        assertFalse(coleccion.iterator().hasNext() && coleccion.iterator().next().equals(coin));
    }

    //Prueba de NoSuchElementException al llamar a next() sin más elementos
    @Test
    void testNoSuchElementException() {
        coleccion.addMoneda(new Moneda(200, "PT"));
        Iterator<Moneda> iterator = coleccion.iterator();

        iterator.next();   //Primera llamada debería funcionar
        assertThrows(NoSuchElementException.class, iterator::next);   //Sin más elementos
    }

    //Prueba de IllegalStateException al llamar a remove() sin llamar a next() primero
    @Test
    void testIllegalStateException() {
        coleccion.addMoneda(new Moneda(200, "PT"));
        Iterator<Moneda> iterator = coleccion.iterator();

        assertThrows(IllegalStateException.class, iterator::remove);   //Llamada a remove sin next
    }

    //Test para setPaisFiltro()
    @Test
    void testIteratorWithCountryFilter() {
        coleccion.addMoneda(new Moneda(200, "PT"));
        coleccion.addMoneda(new Moneda(100, "FR"));
        coleccion.addMoneda(new Moneda(50, "ES"));
        coleccion.addMoneda(new Moneda(20, "ES"));
        coleccion.setPaisFiltro("ES");

        Iterator<Moneda> iterator = coleccion.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            Moneda moneda = iterator.next();
            assertEquals("ES", moneda.getPais());
            count++;
        }
        assertEquals(2, count);  //Dos monedas con país "ES"
    }

    //Test para setPaisFiltro() con país no válido
    @Test
    void testSetValidCountryFilter() {
        coleccion.addMoneda(new Moneda(200, "FR"));
        coleccion.setPaisFiltro("FR");
        assertEquals("FR", coleccion.iterator().next().getPais());
    }

    //Test para setPaisFiltro() con país no válido
    @Test
    void testSetInvalidCountryFilter() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> coleccion.setPaisFiltro("JP"));
        assertEquals("País no válido.", exception.getMessage());
    }
}
