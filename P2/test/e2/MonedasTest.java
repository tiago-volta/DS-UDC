package e2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

public class MonedasTest {

    private Monedas Moneda1;
    private Monedas Moneda2;
    private Monedas Moneda3;
    private Monedas Moneda4;

    @BeforeEach
    public void setUp() {
        Moneda1 = new Monedas(5, Color.PLATA, Pais.ES, "abstracto", 2020);
        Moneda2 = new Monedas(10, Color.BRONCE, Pais.BE, "Prerromano", 2019);
        Moneda3 = new Monedas(10,Color.PLATA, Pais.BE, "Prerromano", 2019);
        Moneda4 = new Monedas(5, Color.PLATA,Pais.ES, "Exotico", 2022);

    }

    @Test
    public void testCompareTo() {
        Monedas Moneda5 = new Monedas(5, Color.ORO, Pais.FR, "Contemporaneo", 2021);

        //Comparación según el primer criterio: Valor
        assertTrue(Moneda2.compareTo(Moneda1) > 0, "Moneda2 debería ser que Moneda1 según el valor");
        assertTrue(Moneda5.compareTo(Moneda3) < 0, "Moneda3 debería ser mayor que Moneda3 según el valor");

        //Comparamos según el segundo criterio: Pais
        assertTrue(Moneda1.compareTo(Moneda5) < 0, "Monedas1 debería ser menor que Moneda5 según el Pais");

        //Comparamos según el tecer criterio: Diseño
        assertTrue(Moneda1.compareTo(Moneda4) < 0 , "Monedas1 debería ser menor que Moneda4 según el Diseño");

        //Comparación de si dos monedas son iguales según el orden natural
        assertEquals(0, Moneda2.compareTo(Moneda3),"Moneda2 y Moneda3 deberían ser iguales según el orden natural");

    }

    @Test
    public void testComparator() {
        Comparador comparator = new Comparador();
        Monedas Moneda6 = new Monedas(20,Color.ORO, Pais.VA,"guapisimo", 2005);
        Monedas Moneda7 = new Monedas (50, Color.BRONCE,Pais.ES, "insane", 2024);


        //Comparación según el primer criterio: el Pais
        assertTrue(comparator.compare(Moneda1, Moneda2) > 0, "Moneda1 debería ser mayor que Moneda2 según el Pais");
        assertTrue(comparator.compare(Moneda1,Moneda6) < 0, "Moneda1 debería ser menor que Moneda6 según el Pais");

        //Comparamos según el segundo criterio: el valor
        assertTrue(comparator.compare(Moneda1, Moneda7) < 0, "Moneda7 debería ser mayor que Moneda1 según el valor");

        //Comparación según el tercer criterio: el año de acuñación
        assertTrue(comparator.compare(Moneda1,Moneda4) < 0, "Moneda 1 debería ser más pequeña que Moneda8 según el año de acuñación");

        //Comparación de si dos monedas son iguales según el criterio del comparador
        assertEquals(0, comparator.compare(Moneda2, Moneda3), "Moneda2 y Moneda3 deberían ser iguales según el orden del comparador");
    }


    @Test
    public void testToString() {
        String expected = "Moneda: valor=5, color=PLATA, Pais=ES, diseno=abstracto, anoAcunacion=2020";
        assertEquals(expected, Moneda1.toString(), "el método toString debería devolver correctamente el valor los valores almacenados en Moneda1");
    }
}
