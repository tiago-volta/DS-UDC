package e1.Buque;

import e1.Buque.Estado.Disponible;
import e1.Buque.Estado.EstadoBuque;

public class Buque {
    private String nombre;
    private TipoDeBuque tipo;
    private int peso;           //En toneladas
    private int misiones;
    private EstadoBuque estado;
    private Dano dano;

    public Buque(String name, TipoDeBuque type, int weight) {
        this.nombre = name;
        this.tipo = type;
        this.peso = weight;
        this.misiones = 0;
        this.estado = Disponible.getInstancia(); // Estado inicial
        this.dano = Dano.NoDanado;
    }

    public String getNombre() {
        return nombre;
    }
    public TipoDeBuque getTipo() {
        return tipo;
    }
    public int getPeso() {
        return peso;
    }
    public int getMisiones() {
        return misiones;
    }
    public EstadoBuque getEstado(){
        return estado;
    }
    public Dano getDano() {
        return dano;
    }

    public void setEstado(EstadoBuque estado){
        this.estado = estado;
    }
    public void setDano(Dano dano){
        this.dano = dano;
    }
    public void incrementarMisiones(){
        this.misiones++;
    }

    //Funciones que emplean el polimorfismo dinámico
    public boolean esEjercicio() {
        return estado.esEjercicio();
    }
    public boolean esPendReparacion() {
        return estado.esPendReparacion();
    }
    public boolean esReparacion() {
        return estado.esReparacion();
    }
    public boolean esInactivo(){
        return estado.esInactivo();
    }

    public void IniciarEjercicio(Buque b) {
       estado.IniciarEjercicio(b);
    }
    public void volverBase(Buque b, Dano d) {
        estado.volverBase(b, d);
    }
    public void Reparacion(Buque b){
        estado.Reparacion(b);
    }
    public void ReparacionExpress(Buque b) {
        estado.ReparacionExpress(b);
    }
    public void cancelarReparacion(Buque b) {
        estado.cancelarReparacion(b);
    }
    public void finReparacion(Buque b, Dano d) {
        estado.finReparacion(b, d);
    }
    public void Desmantelamiento(Buque b) {
       estado.Desmantelamiento(b);
    }

}
