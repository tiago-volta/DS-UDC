package e1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Aldeatest {

    private AldeaRomanos aldeaRomanos;
    private AldeaGalos aldeaGalos;
    private AldeaTeutones aldeaTeutones;

    //Romanos
    private Tropa legionarios;
    private Tropa pretorianos;
    private Tropa equites_imperatoris;

    //Galos
    private Tropa druida;
    private Tropa rayo_de_teutes;
    private Tropa falange;

    //Teutones
    private Tropa guerrero_de_maza;
    private Tropa paladin;

    @BeforeEach
    public void setUp() {
        //Inicializa las tropas
        legionarios = new Legionarios(Armadura.NO_equipada, Arma.normal);
        pretorianos = new Pretorianos(Armadura.normal, Arma.normal);
        equites_imperatoris = new EquitesImperatoris(Armadura.NO_equipada, Arma.normal);
        druida = new Druidas(Armadura.NO_equipada, Arma.normal);
        rayo_de_teutes = new RayosdeTeutates(Armadura.pesada, Arma.normal);
        falange = new Falanges(Armadura.NO_equipada, Arma.normal);
        guerrero_de_maza = new GuerrerosdeMaza(Armadura.NO_equipada, Arma.metal);
        paladin = new Paladines(Armadura.NO_equipada, Arma.normal);

        //Inicializa las aldeas
        aldeaRomanos = new AldeaRomanos(10, 7); // representa Muralla.piedra
        aldeaRomanos.addTropa(legionarios);
        aldeaRomanos.addTropa(pretorianos);
        aldeaRomanos.addTropa(equites_imperatoris);

        aldeaGalos = new AldeaGalos(8, 4);
        aldeaGalos.addTropa(druida);
        aldeaGalos.addTropa(rayo_de_teutes);
        aldeaGalos.addTropa(falange);

        aldeaTeutones = new AldeaTeutones(12, 2);
        aldeaTeutones.addTropa(guerrero_de_maza);
        aldeaTeutones.addTropa(paladin);
    }

    @Test
    @DisplayName("Test de añadir tropas a las aldeas")
    public void testAddTropa() {
        assertAll("Verificar tropas añadidas correctamente",
                () -> assertEquals(3, aldeaRomanos.getTropas().size(), "Romanos debería tener 3 tropas"),
                () -> assertEquals(3, aldeaGalos.getTropas().size(), "Galos debería tener 3 tropas"),
                () -> assertEquals(2, aldeaTeutones.getTropas().size(), "Teutones debería tener 2 tropas")
        );
    }

    @Test
    @DisplayName("Test de añadir tropa nula a la aldea")
    public void testAddTropaNula() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            aldeaRomanos.addTropa(null);
        });
        assertEquals("Tropa no puede ser nula", exception.getMessage());
    }

    @Test
    @DisplayName("Test de ataque de la aldea Romanos")
    public void testAtaqueAldeaRomanos() {
        int ataqueTotal = aldeaRomanos.calcularPuntosDeAtaque();
        //Ajusta los valores de ataque de acuerdo a los puntos de ataque de las tropas
        assertEquals((int) (legionarios.getPuntosdeAtaque() * 1.1) +
                        (int) (pretorianos.getPuntosdeAtaque() * 1.1) +
                        (int) (equites_imperatoris.getPuntosdeAtaque() * 1.1),
                ataqueTotal, "Ataque total de la aldea Romanos incorrecto");
    }

    @Test
    @DisplayName("Test de defensa de la aldea Romanos")
    public void testDefensaAldeaRomanos() {
        int defensaTotal = aldeaRomanos.calcularPuntosDeDefensa();
        //Ajusta los valores de defensa de acuerdo a los puntos de defensa de las tropas
        assertEquals((int) (legionarios.getPuntosdeDefensa() + (2.0 * Arrays.stream(aldeaRomanos.getMuralla().getPuntos()).sum())) +
                        (int) (pretorianos.getPuntosdeDefensa() + (2.0 * Arrays.stream(aldeaRomanos.getMuralla().getPuntos()).sum())) +
                        (int) (equites_imperatoris.getPuntosdeDefensa() + (2.0 * Arrays.stream(aldeaRomanos.getMuralla().getPuntos()).sum())),
                defensaTotal, "Defensa total de la aldea Romanos incorrecta");
    }

    @Test
    @DisplayName("Test calcular puntos de ataque con ejército vacío")
    public void testCalcularAtaqueConEjercitoVacioRomanos() {
        AldeaRomanos aldeaVacia = new AldeaRomanos(5, 7); // Crea una aldea sin tropas
        assertEquals(0, aldeaVacia.calcularPuntosDeAtaque(), "El ataque debería ser 0 para una aldea sin tropas.");
    }

    @Test
    @DisplayName("Test calcular puntos de defensa con ejército vacío")
    public void testCalcularDefensaConEjercitoVacioRomanos() {
        AldeaRomanos aldeaVacia = new AldeaRomanos(5, 7); // Crea una aldea sin tropas
        assertEquals(0, aldeaVacia.calcularPuntosDeDefensa(), "La defensa debería ser 0 para una aldea sin tropas.");
    }

    @Test
    @DisplayName("Test de ataque de la aldea Galos")
    public void testAtaqueAldeaGalos() {
        int ataqueTotal = aldeaGalos.calcularPuntosDeAtaque();
        assertEquals((int) (druida.getPuntosdeAtaque() * 1.2) +
                        (int) (rayo_de_teutes.getPuntosdeAtaque() * 1.2) +
                        (int) (falange.getPuntosdeAtaque() * 1.2),
                ataqueTotal, "Ataque total de la aldea Galos incorrecto");
    }

    @Test
    @DisplayName("Test de defensa de la aldea Galos")
    public void testDefensaAldeaGalos() {
        int defensaTotal = aldeaGalos.calcularPuntosDeDefensa();
        assertEquals((int) (druida.getPuntosdeDefensa() + (1.5 * Arrays.stream(aldeaGalos.getMuralla().getPuntos()).sum())) +
                        (int) (rayo_de_teutes.getPuntosdeDefensa() + (1.5 * Arrays.stream(aldeaGalos.getMuralla().getPuntos()).sum())) +
                        (int) (falange.getPuntosdeDefensa() + (1.5 * Arrays.stream(aldeaGalos.getMuralla().getPuntos()).sum())),
                defensaTotal, "Defensa total de la aldea Galos incorrecta");
    }

    @Test
    @DisplayName("Test calcular puntos de ataque con ejército vacío")
    public void testCalcularAtaqueConEjercitoVacioGalos() {
        AldeaGalos aldeaVacia = new AldeaGalos(6, 4); // Crea una aldea sin tropas
        assertEquals(0, aldeaVacia.calcularPuntosDeAtaque(), "El ataque debería ser 0 para una aldea sin tropas.");
    }

    @Test
    @DisplayName("Test calcular puntos de defensa con ejército vacío")
    public void testCalcularDefensaConEjercitoVacioGalos() {
        AldeaGalos aldeaVacia = new AldeaGalos(6, 4); // Crea una aldea sin tropas
        assertEquals(0, aldeaVacia.calcularPuntosDeDefensa(), "La defensa debería ser 0 para una aldea sin tropas.");
    }

    @Test
    @DisplayName("Test de ataque de la aldea Teutones")
    public void testAtaqueAldeaTeutones() {
        int ataqueTotal = aldeaTeutones.calcularPuntosDeAtaque();
        assertEquals((int) (guerrero_de_maza.getPuntosdeAtaque() * 0.95) +
                        (int) (paladin.getPuntosdeAtaque() * 0.95),
                ataqueTotal, "Ataque total de la aldea Teutones incorrecto");
    }

    @Test
    @DisplayName("Test de defensa de la aldea Teutones")
    public void testDefensaAldeaTeutones() {
        int defensaTotal = aldeaTeutones.calcularPuntosDeDefensa();
        assertEquals((int) (guerrero_de_maza.getPuntosdeDefensa() + (1 * Arrays.stream(aldeaTeutones.getMuralla().getPuntos()).sum())) +
                        (int) (paladin.getPuntosdeDefensa() + (1 * Arrays.stream(aldeaTeutones.getMuralla().getPuntos()).sum())),
                defensaTotal, "Defensa total de la aldea Teutones incorrecta");
    }

    @Test
    @DisplayName("Test calcular puntos de ataque con ejército vacío")
    public void testCalcularAtaqueConEjercitoVacioTeutones() {
        AldeaTeutones aldeaVacia = new AldeaTeutones(6, 2); // Crea una aldea sin tropas
        assertEquals(0, aldeaVacia.calcularPuntosDeAtaque(), "El ataque debería ser 0 para una aldea sin tropas.");
    }

    @Test
    @DisplayName("Test calcular puntos de defensa con ejército vacío")
    public void testCalcularDefensaConEjercitoVacioTeutones() {
        AldeaTeutones aldeaVacia = new AldeaTeutones(6, 2); // Crea una aldea sin tropas
        assertEquals(0, aldeaVacia.calcularPuntosDeDefensa(), "La defensa debería ser 0 para una aldea sin tropas.");
    }


    @Test
    @DisplayName("Test isValidPunto method")
    public void testIsValidPunto() {
        Muralla murallaTierra = Muralla.tierra;
        Muralla murallaMadera = Muralla.madera;
        Muralla murallaPiedra = Muralla.piedra;

        //Test para murallaTierra
        assertTrue(murallaTierra.isValidPunto(1), "Punto 1 es válido para murallaTierra");
        assertTrue(murallaTierra.isValidPunto(2), "Punto 2 es válido para murallaTierra");
        assertTrue(murallaTierra.isValidPunto(3), "Punto 3 es válido para murallaTierra");
        assertFalse(murallaTierra.isValidPunto(4), "Punto 4 es inválido para murallaTierra");
        assertFalse(murallaTierra.isValidPunto(-1), "Punto -1 es inválido para murallaTierra");

        //Test para murallaMadera
        assertTrue(murallaMadera.isValidPunto(4), "Punto 4 es válido para murallaMadera");
        assertTrue(murallaMadera.isValidPunto(5), "Punto 5 es válido para murallaMadera");
        assertTrue(murallaMadera.isValidPunto(6), "Punto 6 es válido para murallaMadera");
        assertFalse(murallaMadera.isValidPunto(7), "Punto 7 es inválido para murallaMadera");
        assertFalse(murallaMadera.isValidPunto(-1), "Punto -1 es inválido para murallaMadera");

        //Test para murallaPiedra
        assertTrue(murallaPiedra.isValidPunto(7), "Punto 7 es válido para murallaPiedra");
        assertTrue(murallaPiedra.isValidPunto(8), "Punto 8 es válido para murallaPiedra");
        assertTrue(murallaPiedra.isValidPunto(9), "Punto 9 es válido para murallaPiedra");
        assertFalse(murallaPiedra.isValidPunto(10), "Punto 10 es inválido para murallaPiedra");
        assertFalse(murallaPiedra.isValidPunto(-1), "Punto -1 es inválido para murallaPiedra");
    }

    @Test
    public void testAddTropaCorrecta() {
        //Usar uma aldeia específica e uma tropa correta
        Aldea aldeaRomana = new AldeaRomanos(10, 7);
        Tropa legionario = new Legionarios(Armadura.NO_equipada, Arma.normal);

        aldeaRomana.addTropa(legionario);
        assertTrue(aldeaRomana.getTropas().contains(legionario));
    }

    @Test
    public void testAddTropaIncorrecta() {
        //Usar uma aldeia específica e uma tropa de uma facção diferente
        Aldea aldeaRomana = new AldeaRomanos(10, 7);
        Tropa falange = new Falanges(Armadura.NO_equipada, Arma.normal); // Tropas dos Galos

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            aldeaRomana.addTropa(falange);
        });
        assertEquals("La tropa no pertenece a esta aldea.", exception.getMessage());
    }

}