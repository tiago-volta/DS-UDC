package e1.Buque.Estado;

import e1.Buque.Buque;
import e1.Buque.Dano;

public class Reparacion implements EstadoBuque {

    // Singleton para asegurar que solo exista una instancia de este estado.
    private static final Reparacion instancia = new Reparacion();
    private Reparacion() {}
    public static Reparacion getInstancia() {return instancia;}

    @Override
    public boolean esEjercicio() {
        return false;
    }

    @Override
    public boolean esPendReparacion() {
        return false;
    }

    @Override
    public boolean esReparacion() {
        return true;
    }
    @Override
    public boolean esInactivo() {
        return false;
    }

    @Override
    public void IniciarEjercicio(Buque b) {
        // No se permite iniciar un ejercicio mientras el buque está en reparación.
        System.out.println("El buque no puede iniciar un ejercicio mientras está siendo reparado.");
    }

    @Override
    public void volverBase(Buque b, Dano d) {
        // Ya está en la base y siendo reparado; no se realiza ninguna acción.
        System.out.println("El buque ya está en la base y en proceso de reparación.");
    }

    @Override
    public void Reparacion(Buque b) {
        // No aplica, el buque ya está en estado de reparación.
        System.out.println("El buque ya está en reparación");
    }

    @Override
    public void ReparacionExpress(Buque b) {
        //No se da un buque que puede optar a una reparación express nunca pasa por reparación
    }

    @Override
    public void cancelarReparacion(Buque b) {
        // Si se cancela, el buque vuelve al estado Pendiente de Reparación.
        b.setEstado(PendienteReparacion.getInstancia());
        b.setDano(Dano.Danado);
        System.out.println("La reparación del buque ha sido cancelada. El buque ahora está pendiente de reparación");
    }

    @Override
    public void finReparacion(Buque b, Dano d) {
        // Dependiendo del daño restante, el buque vuelve a Disponible o Pendiente de Reparación.
        b.setDano(d);
        if (d != Dano.NoDanado){
            b.setEstado(PendienteReparacion.getInstancia());
            System.out.println("La reparación ha concluido, pero el buque necesita más reparaciones. Ahora está pendiente de reparación");
        } else {
            b.setEstado(Disponible.getInstancia());
            System.out.println("La reparación ha concluido. El buque está ahora disponible");
        }
    }

    @Override
    public void Desmantelamiento(Buque b) {
        // Cambia el estado del buque a Desmantelado.
        b.setEstado(Desmantelado.getInstancia());
        System.out.println("El buque ha sido desmantelado");
    }
}
