package e1.Buque.Estado;

import e1.Buque.Buque;
import e1.Buque.Dano;
import e1.Buque.TipoDeBuque;

public class PendienteReparacion implements EstadoBuque {

    // Implementación del Singleton para garantizar que solo hay una instancia de este estado.
    private static final PendienteReparacion instancia = new PendienteReparacion();
    private PendienteReparacion() {}
    public static PendienteReparacion getInstancia() {return instancia;}

    @Override
    public boolean esEjercicio() {
        return false;
    }

    @Override
    public boolean esPendReparacion() {
        return true;
    }

    @Override
    public boolean esReparacion() {
        return false;
    }
    @Override
    public boolean esInactivo() {
        return false;
    }

    @Override
    public void IniciarEjercicio(Buque b) {
        // No se permite iniciar ejercicios desde el estado PendienteReparacion.
        System.out.println("El buque no puede iniciar un ejercicio mientras está pendiente de reparación");
    }

    @Override
    public void volverBase(Buque b, Dano d) {
        // Ya está en el estado de reparación pendiente; no hace falta cambiar nada.
        System.out.println("El buque ya está en la base y pendiente de reparación");
    }

    @Override
    public void Reparacion(Buque b) {
        b.setEstado(Reparacion.getInstancia());
        System.out.println("El buque ha comenzado el proceso de reparación");
    }

    //Se salta el estado de reparación, es una reparación instantánea
    @Override
    public void ReparacionExpress(Buque b) {
        if (b.getTipo() == TipoDeBuque.DE || b.getTipo() == TipoDeBuque.DD) {
            b.setEstado(Disponible.getInstancia());
            System.out.println("Buque " + b.getNombre() + " (" + b.getTipo() + ") ahora está disponible");
        }else
            System.out.println("No se puede hacer una reparación express de " + b.getNombre() + " porque no es un buque ultraligero");

    }

    @Override
    public void cancelarReparacion(Buque b) {
        // Si se cancela la reparación, el buque permanece en este estado.
        System.out.println("No se puede cancelar la reparación porque el buque está pendiente de reparación");
    }

    @Override
    public void finReparacion(Buque b, Dano d) {
        // No aplica; el buque debe pasar primero al estado Reparación para completar la reparación.
        System.out.println("No es posible finalizar la reparación desde el estado Pendiente de Reparación");
    }

    @Override
    public void Desmantelamiento(Buque b) {
        // No se puede desmantelar el buque
        System.out.println("No se puede desmantelar buque hay que revisarlo en la reparación");
    }
}
