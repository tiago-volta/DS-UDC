package e1;

import e1.Buque.Buque;
import e1.Buque.Dano;
import e1.Buque.Estado.*;
import e1.Buque.TipoDeBuque;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static e1.Buque.Dano.DanoMasivo;
import static org.junit.jupiter.api.Assertions.*;

public class BuqueTest {

    private Buque buque;
    private Buque buque2;


    @BeforeEach
    void setUp() {
        //Buque ultraligero => puede hacer reparación express
        buque = new Buque("Destructor Beta", TipoDeBuque.DD, 3000);
        buque2 = new Buque("Acorazado Alfa", TipoDeBuque.BB, 10000);
    }

    @Test
    void testCreacionBuque() {
        assertEquals("Destructor Beta", buque.getNombre());
        assertEquals(TipoDeBuque.DD, buque.getTipo());
        assertEquals(3000, buque.getPeso());
        assertEquals(0, buque.getMisiones());
        assertEquals(Disponible.getInstancia(), buque.getEstado());
        assertEquals(Dano.NoDanado, buque.getDano());
    }

    @Test
    void testCambiarEstado() {
        buque.setEstado(Reparacion.getInstancia());
        assertEquals(Reparacion.getInstancia(), buque.getEstado());
    }

    @Test
    void testCambiarDano() {
        buque.setDano(Dano.Danado);
        assertEquals(Dano.Danado, buque.getDano());
    }

    @Test
    void testIncrementarMisiones() {
        buque.incrementarMisiones();
        assertEquals(1, buque.getMisiones());
    }

    @Test
    void testIniciarEjercicioConPolimorfismo() {
        buque.IniciarEjercicio(buque);
        assertTrue(buque.getEstado().esEjercicio());

        buque.setEstado(PendienteReparacion.getInstancia());
        assertFalse(buque.getEstado().esEjercicio());

        assertEquals(1, buque.getMisiones()); // Verificar si las misiones se incrementan.
    }

    @Test
    void testPolimorfismo(){
        buque.setEstado(PendienteReparacion.getInstancia());
        assertTrue(buque.getEstado().esPendReparacion());

        buque.setEstado(Reparacion.getInstancia());
        assertTrue(buque.getEstado().esReparacion());

        buque.setEstado(Hundido.getInstancia());
        assertTrue(buque.getEstado().esInactivo());
    }

    @Test
    void testVolverBaseSinDano() {
        buque.setEstado(Disponible.getInstancia());
        buque.IniciarEjercicio(buque);
        buque.volverBase(buque, Dano.NoDanado);
        assertEquals(Disponible.getInstancia(), buque.getEstado());
        assertEquals(Dano.NoDanado, buque.getDano());
    }


    @Test
    void testVolverBaseConDanoLeve() {
        buque.IniciarEjercicio(buque);
        buque.volverBase(buque, Dano.Danado);
        assertTrue(buque.esPendReparacion());
        assertEquals(Dano.Danado, buque.getDano());
    }

    @Test
    void testVolverBaseConDanoMasivo() {
        buque.setEstado(Disponible.getInstancia());
        buque.IniciarEjercicio(buque);
        buque.volverBase(buque, Dano.DanoMasivo);
        assertEquals(Hundido.getInstancia(), buque.getEstado());
        assertEquals(DanoMasivo, buque.getDano());
    }

    @Test
    void testReparacion() {
        buque.setEstado(PendienteReparacion.getInstancia());
        buque.Reparacion(buque);
        assertTrue(buque.esReparacion());
    }

    //No podemos hacer una reparación express de un buque que no sea ultraligero

    @Test
    void testReparacionExpressBuqueNoUltraligero() {
        buque2.setEstado(PendienteReparacion.getInstancia());
        buque2.ReparacionExpress(buque2);
        assertEquals(PendienteReparacion.getInstancia(), buque2.getEstado());
    }

    @Test
    void testReparacionExpressBuqueUltraligero() {
        buque.setEstado(PendienteReparacion.getInstancia());
        buque.ReparacionExpress(buque);
        assertEquals(Disponible.getInstancia(), buque.getEstado());
    }

    @Test
    void testCancelarReparacion() {
        buque.setEstado(Reparacion.getInstancia());
        buque.cancelarReparacion(buque);
        assertEquals(PendienteReparacion.getInstancia(), buque.getEstado());

        buque.setEstado(PendienteReparacion.getInstancia());
        buque.cancelarReparacion(buque);
        assertTrue(buque.esPendReparacion()); // No cambia el estado, sigue pendiente.
    }


    @Test
    void testFinalizarReparacion() {
        buque.setEstado(Reparacion.getInstancia());
        buque.finReparacion(buque, Dano.Danado);    //Se reporta que sigue dañado en el fin de la reparación
        assertEquals(PendienteReparacion.getInstancia(), buque.getEstado());
        assertEquals(Dano.Danado, buque.getDano());

        buque.setEstado(Reparacion.getInstancia());
        buque.finReparacion(buque, Dano.NoDanado);    //Se reporta que sigue dañado en el fin de la reparación
        assertEquals(Disponible.getInstancia(), buque.getEstado());
        assertEquals(Dano.NoDanado, buque.getDano());
    }

    @Test
    void testSolicitarDesmantelamiento() {
        buque.setEstado(Disponible.getInstancia());
        buque.Desmantelamiento(buque);
        assertEquals(Desmantelado.getInstancia(), buque.getEstado());

        buque.setEstado(Reparacion.getInstancia());
        buque.Desmantelamiento(buque);
        assertEquals(Desmantelado.getInstancia(), buque.getEstado());

        buque.setEstado(PendienteReparacion.getInstancia());
        buque.Desmantelamiento(buque);
        assertEquals(PendienteReparacion.getInstancia(), buque.getEstado());
    }
}
