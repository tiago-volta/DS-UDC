package e1;

import e1.Buque.Buque;
import e1.Buque.Dano;
import e1.Buque.Estado.*;
import e1.Buque.TipoDeBuque;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class BaseNavalTest {

    private BaseNaval base;
    private Buque buque1;
    private Buque buque2;

    @BeforeEach
    void setUp() {
        base = new BaseNaval(100000); // Base con 10,000 fondos iniciales
        buque1 = new Buque("CruceroLigero1", TipoDeBuque.CL, 5000);
        buque2 = new Buque("Destructor1", TipoDeBuque.DD, 3000);

    }

    @Test
    void testCreacionBaseNaval() {
        assertEquals(100000,base.getFondos());
        List<Buque> flota = base.getFlotaActiva();
        assertEquals(0,flota.size());
    }

    @Test
    void testAnadirYEliminarBuque() {
        base.anadirBuque(buque1);
        base.anadirBuque(buque2);

        List<Buque> flota = base.getFlotaActiva();
        assertEquals(2, flota.size());
        assertTrue(flota.contains(buque1));
        assertTrue(flota.contains(buque2));

        base.eliminarBuque(buque1);
        flota = base.getFlotaActiva();
        assertEquals(1, flota.size());
        assertFalse(flota.contains(buque1));
        assertTrue(flota.contains(buque2));
    }

    @Test
    void testIngresarRecompensa() {
        base.anadirBuque(buque1);
        base.ingresarRecompensa(buque1);
        assertEquals(100000 + (5000 * 20), base.getFondos());
    }

    @Test
    void testCobrarCosteReparacion() {
        base.anadirBuque(buque2);
        boolean fondosSuficientes = base.cobrarCosteReparacion(buque2);

        assertTrue(fondosSuficientes);
        assertEquals(100000 - (3000 * 12), base.getFondos());

        base.setFondos(500); // Fondos insuficientes
        boolean fondosInsuficientes = base.cobrarCosteReparacion(buque2);
        assertFalse(fondosInsuficientes);
        assertEquals(500, base.getFondos());
    }

    @Test
    void testVolverBaseSinDano() {
        base.anadirBuque(buque1);
        base.InciarEjercicio(buque1);

        base.volverBase(buque1, Dano.NoDanado);
        assertEquals(Disponible.getInstancia(), buque1.getEstado());
        assertEquals(100000 + (5000 * 20), base.getFondos());
    }

    @Test
    void testVolverBaseConDanoLeve() {
        base.anadirBuque(buque1);
        base.InciarEjercicio(buque1);

        base.volverBase(buque1, Dano.Danado);
        assertEquals(PendienteReparacion.getInstancia(), buque1.getEstado());
        assertEquals(100000 + (5000 * 20), base.getFondos());
    }

    @Test
    void testVolverBaseConDanoMasivo() {
        base.anadirBuque(buque1);
        base.InciarEjercicio(buque1);

        base.volverBase(buque1, Dano.DanoMasivo);
        assertEquals(Hundido.getInstancia(), buque1.getEstado());
        assertEquals(100000, base.getFondos()); // No se recibe recompensa
    }

    @Test
    void testReparacionConFondos() {
        base.anadirBuque(buque1);
        buque1.setEstado(PendienteReparacion.getInstancia());

        base.Reparacion(buque1);
        assertEquals(Reparacion.getInstancia(), buque1.getEstado());
        assertEquals(100000 - (5000 * 12), base.getFondos());
    }

    @Test
    void testReparacionSinFondos() {
        base.anadirBuque(buque1);
        base.setFondos(500); // Fondos insuficientes
        buque1.setEstado(PendienteReparacion.getInstancia());

        base.Reparacion(buque1);
        assertEquals(PendienteReparacion.getInstancia(), buque1.getEstado());
        assertEquals(500, base.getFondos());
    }

    @Test
    void testReparacionSinEspacio(){
        base.anadirBuque(buque1);
        base.anadirBuque(buque2);
        buque1.setEstado(PendienteReparacion.getInstancia());
        buque2.setEstado(PendienteReparacion.getInstancia());

        base.Reparacion(buque1);
        assertTrue(buque1.esReparacion());
        base.Reparacion(buque2);
        assertTrue(buque2.esPendReparacion());
    }

    //No es buque ligero
    @Test
    void testReparacionExpress() {
        base.anadirBuque(buque2);
        buque2.setEstado(PendienteReparacion.getInstancia());

        base.ReparacionExpress(buque2);
        assertEquals(Disponible.getInstancia(), buque2.getEstado());

        //No es buque ligero
        base.anadirBuque(buque1);
        buque1.setEstado(PendienteReparacion.getInstancia());

        base.ReparacionExpress(buque1);
        assertTrue(buque1.esPendReparacion());
    }

    @Test
    void testCancelarReparacion() {
        base.anadirBuque(buque1);
        buque1.setEstado(Reparacion.getInstancia());

        base.cancelarReparacion(buque1);
        assertEquals(PendienteReparacion.getInstancia(), buque1.getEstado());
    }

    @Test
    void testCancelarReparacionPendiente() {
        base.anadirBuque(buque1);
        buque1.setEstado(PendienteReparacion.getInstancia());

        base.cancelarReparacion(buque1);
        // Si el buque ya está pendiente de reparación, no debería haber cambios
        assertEquals(PendienteReparacion.getInstancia(), buque1.getEstado());
    }

    @Test
    void testTerminarReparacionConDanoLeve() {
        base.anadirBuque(buque1);
        buque1.setEstado(Reparacion.getInstancia());

        base.terminarReparacion(buque1, Dano.NoDanado);
        assertEquals(Disponible.getInstancia(), buque1.getEstado());
        assertEquals(Dano.NoDanado, buque1.getDano());
    }

    @Test
    void testDesmantelarBuqueActivo() {
        base.anadirBuque(buque1);

        base.Desmantelar(buque1);
        assertTrue(buque1.esInactivo());
    }

    @Test
    void testDesmantelarBuqueInactivo() {
        base.anadirBuque(buque1);
        buque1.setEstado(Hundido.getInstancia());

        base.Desmantelar(buque1);
        // Si el buque ya está inactivo, no debería haber cambios
        assertEquals(Hundido.getInstancia(), buque1.getEstado());
        assertTrue(buque1.esInactivo());
    }

    @Test
    void testListarBuques() {
        base.anadirBuque(buque1);
        base.anadirBuque(buque2);

        base.Desmantelar(buque2);

        base.listarBuquesEnActivo();
        base.listarBuquesEnInactivo();

        // Verificar que buque1 esté activo y buque2 inactivo
        assertFalse(buque1.esInactivo());
        assertTrue(buque2.esInactivo());
    }

    @Test
    void testReporteFinanciero() {
        base.anadirBuque(buque1);
        base.anadirBuque(buque2);

        base.InciarEjercicio(buque2);
        base.InciarEjercicio(buque1);
        base.volverBase(buque2, Dano.Danado);

        base.reporteFinanciero();
        // Validar valores esperados de ingresos, gastos y fondos proyectados.
        double recompensaBuque2 = 20 * buque2.getPeso();
        double ingresosEsperados = 20 * 5000;
        double gastosEsperados = 12 * 3000;
        double fondosEsperados = (100000 + recompensaBuque2)  + ingresosEsperados - gastosEsperados;

        assertEquals(fondosEsperados, base.getFondos() + ingresosEsperados - gastosEsperados);
    }
}
