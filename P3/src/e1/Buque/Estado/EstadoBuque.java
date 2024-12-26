package e1.Buque.Estado;

import e1.Buque.Buque;
import e1.Buque.Dano;

public interface EstadoBuque {
    boolean esEjercicio();
    boolean esPendReparacion();
    boolean esReparacion();
    boolean esInactivo();
    void IniciarEjercicio(Buque b);
    void volverBase(Buque b, Dano d);       
    void Reparacion(Buque b);          
    void ReparacionExpress(Buque b);       
    void cancelarReparacion (Buque b);  
    void finReparacion(Buque b, Dano d);       
    void Desmantelamiento(Buque b);
}
