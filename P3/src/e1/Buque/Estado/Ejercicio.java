package e1.Buque.Estado;

import e1.Buque.Buque;
import e1.Buque.Dano;

public class Ejercicio implements EstadoBuque {
    private static final Ejercicio instancia = new Ejercicio();
    private Ejercicio(){}
    public static Ejercicio getInstancia(){return instancia;}

    @Override
    public boolean esEjercicio() {
        return true; // Este estado representa un ejercicio naval
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
        // Si un buque está en ejercicio no puede iniciar otro ejercicio
        System.out.println("Buque " + b.getNombre() + " ya está en un ejercicio naval y no puede iniciar otro");
    }
    @Override
    public void volverBase (Buque b, Dano d){
        b.setDano(d);
        if (d == Dano.Danado){
            b.setEstado(PendienteReparacion.getInstancia());
            System.out.println("Buque " + b.getNombre() + " (" + b.getTipo() + ") ahora está pendiente de reparación | Misiones: " + b.getMisiones() );
        } else if (d == Dano.DanoMasivo) {
            b.setEstado(Hundido.getInstancia());
            System.out.println("Buque " + b.getNombre() + " (" + b.getTipo() + ") se ha hundido | Misiones: " + b.getMisiones());
        }else{
            b.setEstado(Disponible.getInstancia());
            System.out.println("Buque " + b.getNombre() + " (" + b.getTipo() + ") ahora está disponible | Misiones: " + b.getMisiones());
        }
    }
    @Override
    public void Reparacion(Buque b) {
        System.out.println("Buque " + b.getNombre() + " no puede solicitar reparación mientras está en ejercicio naval");
    }

    @Override
    public void ReparacionExpress(Buque b) {
        System.out.println("Buque " + b.getNombre() + " no puede solicitar reparación express mientras está en ejercicio naval");
    }

    @Override
    public void cancelarReparacion(Buque b) {
        System.out.println("Buque " + b.getNombre() + " no puede cancelar reparaciones mientras está en ejercicio naval");
    }

    @Override
    public void finReparacion(Buque b, Dano d) {
        System.out.println("Buque " + b.getNombre() + " no puede finalizar reparaciones mientras está en ejercicio naval.");
    }

    @Override
    public void Desmantelamiento(Buque b) {
        System.out.println("Buque " + b.getNombre() + " no puede ser desmantelado mientras está en un ejercicio naval");
    }
}
