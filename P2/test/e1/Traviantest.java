package e1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Traviantest {

    private AldeaRomanos aldeaRomanos;
    private AldeaGalos aldeaGalos;
    private Tropa legionario;
    private Tropa pretoriano;
    private Tropa equitesImperatoris;
    private Tropa druida;
    private Tropa rayoDeTeutates;
    private Tropa falange;

    @BeforeEach
    public void setUp() {
        aldeaRomanos = new AldeaRomanos(10, 8);
        aldeaGalos = new AldeaGalos(8, 4);

        //Crear tropas para cada aldea
        legionario = new Legionarios(Armadura.NO_equipada, Arma.normal);
        pretoriano = new Pretorianos(Armadura.pesada, Arma.normal);
        equitesImperatoris = new EquitesImperatoris(Armadura.NO_equipada, Arma.normal);

        druida = new Druidas(Armadura.NO_equipada, Arma.normal);
        rayoDeTeutates = new RayosdeTeutates(Armadura.pesada, Arma.normal);
        falange = new Falanges(Armadura.NO_equipada, Arma.normal);

        //Añadir tropas a las aldeas
        aldeaRomanos.addTropa(legionario);
        aldeaRomanos.addTropa(pretoriano);
        aldeaRomanos.addTropa(equitesImperatoris);

        aldeaGalos.addTropa(druida);
        aldeaGalos.addTropa(rayoDeTeutates);
        aldeaGalos.addTropa(falange);
    }

    @Test
    public void testBatallaResultado() {
        //Ejecutar la simulación de batalla
        List<String> resultado =    Travian.batalla(aldeaRomanos, aldeaGalos, "Aldea1");

        //Verificar que el mensaje de inicio de la batalla es correcto
        assertEquals("### Starts the battle ! --> Romanos Attacks Galos ! ###", resultado.get(0));

        //Verificar detalles del ejército atacante (Romanos)
        assertTrue(resultado.contains("Romanos have the following soldiery :"), "El mensaje de soldados Romanos no se encuentra.");
        assertTrue(resultado.stream().anyMatch(linea -> linea.contains("Legionarios")), "No se encuentra la clase Legionarios.");
        assertTrue(resultado.stream().anyMatch(linea -> linea.contains("Pretorianos")), "No se encuentra la clase Pretorianos.");

        //Verificar detalles del ejército defensor (Galos)
        assertTrue(resultado.contains("Galos have the following soldiery :"), "El mensaje de soldados Galos no se encuentra.");
        assertTrue(resultado.stream().anyMatch(linea -> linea.contains("Druidas")), "No se encuentra la clase Druidas.");
        assertTrue(resultado.stream().anyMatch(linea -> linea.contains("Falanges")), "No se encuentra la clase Falanges.");

        //Verificar que los puntos de ataque y defensa se suman correctamente
        int puntosAtaqueRomanos = aldeaRomanos.calcularPuntosDeAtaque();
        int puntosDefensaGalos = aldeaGalos.calcularPuntosDeDefensa();

        assertTrue(resultado.contains("Total Romanos attack power : " + puntosAtaqueRomanos), "El poder de ataque total de Romanos no se encuentra en el resultado.");
        assertTrue(resultado.contains("Total Galos defense power : " + puntosDefensaGalos), "El poder de defensa total de Galos no se encuentra en el resultado.");

        //Determinar ganador y verificar mensaje
        if (puntosAtaqueRomanos > puntosDefensaGalos) {
            assertEquals("Romanos with an age of 10 years WINS !", resultado.get(resultado.size() - 1), "El resultado final no coincide con el esperado para Romanos.");
        } else {
            assertEquals("Galos with an age of 8 years WINS !", resultado.get(resultado.size() - 1), "El resultado final no coincide con el esperado para Galos.");
        }
    }

    @Test
    public void testAtaqueYDefensaModificadosPorAldea() {
        //Calculamos los puntos de ataque y defensa iniciales
        int ataqueInicial = legionario.getPuntosdeAtaque() + pretoriano.getPuntosdeAtaque();
        int defensaInicial = druida.getPuntosdeDefensa() + falange.getPuntosdeDefensa();

        //Ejecutar la simulación
        Travian.batalla(aldeaRomanos, aldeaGalos, "Aldea1");

        //Comprobamos que los puntos se han ajustado tras aplicar el cálculo de ataque y defensa
        int ataqueModificadoRomanos = aldeaRomanos.calcularPuntosDeAtaque();
        int defensaModificadaGalos = aldeaGalos.calcularPuntosDeDefensa();

        assertTrue(ataqueModificadoRomanos > ataqueInicial, "El ataque de Romanos no debe haber aumentado.");
        assertTrue(defensaModificadaGalos > defensaInicial, "La defensa de Galos no debe haber aumentado.");
    }

    @Test
    public void testAddTropasToAldeas() {
        assertEquals(3, aldeaRomanos.getTropas().size(), "La aldea Romana debería tener 3 tropas");
        assertEquals(3, aldeaGalos.getTropas().size(), "La aldea Gala debería tener 3 tropas");
    }

    @Test
    public void testResultadoBatallaBasico() {
        //Ejecutar una simulación y comprobar que contiene elementos clave para Romanos y Galos
        List<String> resultado = Travian.batalla(aldeaRomanos, aldeaGalos, "SimulacionTest");

        //Comprobar que los resultados reflejan el nombre de las aldeas y algún resultado
        assertTrue(resultado.stream().anyMatch(linea -> linea.contains("Romanos")), "El resultado debería incluir el nombre de la aldea Romana.");
        assertTrue(resultado.stream().anyMatch(linea -> linea.contains("Galos")), "El resultado debería incluir el nombre de la aldea Gala.");
    }
    @Test
    public void testBatallaConEjercitosVacios() {
        AldeaRomanos aldeaRomanosVacia = new AldeaRomanos(10, 8);
        AldeaGalos aldeaGalosVacia = new AldeaGalos(8, 4);

        List<String> resultado = Travian.batalla(aldeaRomanosVacia, aldeaGalosVacia, "AldeaVacia");

        assertTrue(resultado.contains("No troops to battle."), "El resultado debería indicar que no hay tropas para la batalla.");
    }

    @Test
    public void testBatallaConUnEjercitoVacio() {
        AldeaRomanos aldeaRomanosVacia = new AldeaRomanos(10, 8);

        List<String> resultado = Travian.batalla(aldeaRomanosVacia, aldeaGalos, "AldeaVacia");

        assertTrue(resultado.contains("No troops to battle."), "El resultado debería indicar que no hay tropas para la batalla.");
    }


    @Test
    public void testBatallaConCombinacionesDeTropas() {
        AldeaRomanos aldeaRomanosConMasTropas = new AldeaRomanos(10, 8);
        aldeaRomanosConMasTropas.addTropa(new Legionarios(Armadura.NO_equipada, Arma.normal));
        aldeaRomanosConMasTropas.addTropa(new Pretorianos(Armadura.pesada, Arma.normal));

        List<String> resultado = Travian.batalla(aldeaRomanosConMasTropas, aldeaGalos, "AldeaCombinaciones");

        //Verificar que el mensaje de inicio de la batalla es correcto
        assertEquals("### Starts the battle ! --> Romanos Attacks Galos ! ###", resultado.get(0));
    }

    @Test
    public void testBatallaConEjercitoRomanoVacio() {
        AldeaRomanos aldeaRomanosVacia = new AldeaRomanos(10, 8);
        List<String> resultado = Travian.batalla(aldeaRomanosVacia, aldeaGalos, "AldeaVaciaRomana");

        assertTrue(resultado.contains("No troops to battle."));
    }

}