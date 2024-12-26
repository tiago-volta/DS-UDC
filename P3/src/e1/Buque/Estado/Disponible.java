package e1.Buque.Estado;

import e1.Buque.Buque;
import e1.Buque.Dano;

public class Disponible implements EstadoBuque {
    private static final Disponible instancia = new Disponible();
    private Disponible(){}
    public static Disponible getInstancia(){return instancia;}

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
        return false;
    }
    @Override
    public boolean esInactivo() {
        return false;
    }
    @Override
    public void IniciarEjercicio(Buque b) {
        b.setEstado(Ejercicio.getInstancia());
        b.incrementarMisiones();
        System.out.println(b.getNombre() + " ha iniciado un ejercicio naval");
    }
    @Override
    public void volverBase(Buque b, Dano d) {
        System.out.println("Buque " + b.getNombre() + " ya está disponible, no necesita volver a la base");
    }

    @Override
    public void Reparacion(Buque b) {
        System.out.println("Buque " + b.getNombre() + " no requiere reparaciones porque ya está disponible");
    }

    @Override
    public void ReparacionExpress(Buque b) {
        System.out.println("Buque " + b.getNombre() + " no requiere reparaciones express porque está en buen estado");
    }

    @Override
    public void cancelarReparacion(Buque b) {
        System.out.println("Buque " + b.getNombre() + " no tiene reparaciones en curso para cancelar.");
    }

    @Override
    public void finReparacion(Buque b, Dano d) {
        System.out.println("Buque " + b.getNombre() + " no está en reparación, ya está disponible.");
    }

    @Override
    public void Desmantelamiento(Buque b) {
        b.setEstado(Desmantelado.getInstancia());
        System.out.println("Buque " + b.getNombre() + " (" + b.getTipo() + ") ha sido desmantelado | Misiones: " + b.getMisiones());
    }
}
