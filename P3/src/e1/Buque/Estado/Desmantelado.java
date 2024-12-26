package e1.Buque.Estado;

import e1.Buque.Buque;
import e1.Buque.Dano;

public class Desmantelado implements EstadoBuque {
    private static final Desmantelado instancia = new Desmantelado();
    private Desmantelado() {}
    public static Desmantelado getInstancia() {return instancia;}

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
        return true;
    }

    @Override
    public void IniciarEjercicio(Buque b) {
        System.out.println("El buque " + b.getNombre() + " no puede iniciar un ejercicio porque está desmantelado.");
    }

    @Override
    public void volverBase(Buque b, Dano d) {
        System.out.println("El buque " + b.getNombre() + " no puede volver a la base porque está desmantelado.");
    }

    @Override
    public void Reparacion(Buque b) {
        System.out.println("El buque " + b.getNombre() + " no puede solicitar reparación porque está desmantelado.");
    }

    @Override
    public void ReparacionExpress(Buque b) {
        System.out.println("El buque " + b.getNombre() + " no puede solicitar reparación express porque está desmantelado.");
    }

    @Override
    public void cancelarReparacion(Buque b) {
        System.out.println("El buque " + b.getNombre() + " no puede cancelar reparaciones porque está desmantelado.");
    }

    @Override
    public void finReparacion(Buque b, Dano d) {
        System.out.println("El buque " + b.getNombre() + " no puede finalizar reparaciones porque está desmantelado.");
    }

    @Override
    public void Desmantelamiento(Buque b) {
        System.out.println("El buque " + b.getNombre() + " ya está desmantelado y no puede volver a desmantelarse.");
    }
}