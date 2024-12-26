package e2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

public class MonedaCollectionTest {
    private MonedaCollection collection;
    private Monedas Moneda1;
    private Monedas Moneda2;
    private Monedas Moneda3;
    private Monedas Moneda4;

    @BeforeEach
    public void setUp() {
        collection = new MonedaCollection();
        Moneda1 = new Monedas(5, Color.PLATA, Pais.ES, "Sobrio", 2020);
        Moneda2 = new Monedas(10,Color.BRONCE, Pais.FR, "Estético", 2019);
        Moneda3 = new Monedas(5, Color.ORO, Pais.ES, "Austero", 2021);
        Moneda4 = new Monedas(2,Color.PLATA, Pais.IT, "Cálido", 2018);

    }

    @Test
    public void testAddMoneda() {
        assertTrue(collection.addMoneda(Moneda1), "Monedas se debería de insertar correctamente");
        assertEquals(1, collection.totalCoins(), "El tamaño de la colección de monedas debería ser 1 después de insertar 1");
    }

    @Test
    public void testAddMonedaDuplicada() {
        collection.addMoneda(Moneda1);
        assertFalse(collection.addMoneda(Moneda1), "No se debería añadir una moneda duplicada");
        assertEquals(1, collection.totalCoins(), "El tamaño de la colección después de intentar insertar un duplicado no debería cambiar");
    }

    @Test
    public void testBorrarMoneda() {
        collection.addMoneda(Moneda1);
        assertTrue(collection.removeCoin(Moneda1), "La moneda se debería de borrar correctamente");
        assertEquals(0, collection.totalCoins(), "La colección debería de estar vacía después de borrar la única");
    }

    @Test
    public void testBorrarMonedaNoEncontrada() {
        collection.addMoneda(Moneda1);
        assertFalse(collection.removeCoin(Moneda2), "Borrar una moneda que no está en la colección debería devolver falso");
    }

    @Test
    public void testTotalMonedas() {
        assertEquals(0, collection.totalCoins(), "La colección inicial debería de tener 0 monedas");
        collection.addMoneda(Moneda1);
        collection.addMoneda(Moneda2);
        assertEquals(2, collection.totalCoins(), "El total de monedas después de añadir Moneda1 y Moneda2 debería ser 2");
        collection.removeCoin(Moneda1);
        assertEquals(1, collection.totalCoins(), "El total de monedas debería de ser 1 después de borrar Moneda1");
    }

    @Test
    public void testValorTotalMonedas() {
        collection.addMoneda(Moneda1);
        collection.addMoneda(Moneda2);
        assertEquals(15, collection.totalValue(), "El valor total debería de ser 15");
    }

    @Test
    public void testValorTotalMonedasConRemove() {
        collection.addMoneda(Moneda1);
        collection.addMoneda(Moneda2);
        collection.removeCoin(Moneda1);
        assertEquals(10, collection.totalValue(), "El valor total debería de ser 10");
    }

    @Test
    public void testValorTotalColeccionVacia() {
        assertEquals(0, collection.totalValue(), "El valor total de la coleccion vacía debería ser 0");
    }

    @Test
    public void testContieneMoneda() {
        collection.addMoneda(Moneda1);
        assertTrue(collection.containsCoin(Moneda1), "La colección debería de contener Moneda1");
    }

    @Test
    public void testContieneMonedaNoEncontrada() {
        collection.addMoneda(Moneda1);
        assertFalse(collection.containsCoin(Moneda2), "La colección no debería de contener Moneda2");
    }

    @Test
    public void testSort() {
        collection.addMoneda(Moneda1);
        collection.addMoneda(Moneda2);
        collection.addMoneda(Moneda3);

        List<Monedas> sortedList = collection.sort();

        assertEquals(Moneda3, sortedList.get(0), "El primer elemento debería ser la Moneda3");
        assertEquals(Moneda1, sortedList.get(1), "El segundo elemento debería ser la Moneda1");
        assertEquals(Moneda2, sortedList.get(2), "El tercer elemento debería ser la Moneda2");
    }

    @Test
    public void testSortWithComparator() {
        collection.addMoneda(Moneda1);
        collection.addMoneda(Moneda2);
        collection.addMoneda(Moneda3);

        Comparador comparator = new Comparador();
        List<Monedas> sortedList = collection.sortPersonalized(comparator);

        assertEquals(Moneda1, sortedList.get(0), "El primer elemento debería ser la Moneda1");
        assertEquals(Moneda3, sortedList.get(1), "El segundo elemento debería ser la Moneda3");
        assertEquals(Moneda2, sortedList.get(2), "El tercer elemento debería ser la Moneda2");
    }

    @Test
    public void testToString() {
        collection.addMoneda(Moneda1);
        String expectedString = "Colección de Moneda:\n" + Moneda1 +"\n";
        assertEquals(expectedString, collection.toString(), "La salida debería ser la esperada");
    }

    @Test
    public void testAddNullMonedas() {
        assertThrows(IllegalArgumentException.class, () -> collection.addMoneda(null), "Añadir una moneda nula debería lanzar IllegalArgumentException");
    }

    @Test
    public void testRemoveNullMonedas() {
        assertThrows(IllegalArgumentException.class, () -> collection.removeCoin(null), "Borrar una moneda nula debería lanzar IllegalArgumentException");
    }

    @Test
    public void testAddNullMonedasInComparator() {
        assertThrows(IllegalArgumentException.class, () -> collection.sortPersonalized(null), "Ordenar con un nulo debería lanzar IllegalArgumentException");
    }



}
