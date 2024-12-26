package e1.Buque.Estado;

import e1.Buque.Buque;
import e1.Buque.Dano;

public class Hundido implements EstadoBuque {
    private static final Hundido instancia = new Hundido();
    private Hundido(){}
    public static Hundido getInstancia(){return instancia;}

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
        //Si un buque está hundido ya no puede iniciar ejericio
    }
    @Override
    public void volverBase (Buque b, Dano d){
        //Si un buque está hundido no puede volver a base
    }
    @Override
    public void Reparacion(Buque b){
        //Si un buque está hundido no necesita reparaciones
    }
    @Override
    public void ReparacionExpress(Buque b) {
        //Si un buque está hundido no necesita reparaciones
    }

    @Override
    public void cancelarReparacion(Buque b) {
        //Si un buque está hundido no puede cancelar reparaciones
    }

    @Override
    public void finReparacion(Buque b, Dano d) {
        //Si un buque está hundido no puede finalizar reparaciones
    }

    @Override
    public void Desmantelamiento(Buque b) {
        //Si un buque está hundido no puede volver a desmantelarse
    }
}
