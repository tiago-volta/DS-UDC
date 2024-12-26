package e1;

import e1.Buque.Buque;
import e1.Buque.Dano;

import java.util.ArrayList;
import java.util.List;

public class BaseNaval{
    private double fondos;
    private List<Buque> flota;

    public BaseNaval(double initialFunds){
        this.fondos = initialFunds;
        this.flota = new ArrayList<>();
    }

    public double getFondos() {
        return this.fondos;
    }
    public List<Buque> getFlotaActiva() {
        return this.flota;
    }


    public void setFondos(double fondos) {
        this.fondos = fondos;
    }
    public void setFlota(List<Buque> flota) {
        this.flota = flota;
    }

    //Agregar un buque a la flota
    public void anadirBuque(Buque buque){
        flota.add(buque);
        System.out.println("Buque " + buque.getNombre() + " (" + buque.getTipo() + ") se ha unido a la flota y estás en activo");
    }

    //Eliminar un buque de la flota
    public void eliminarBuque(Buque buque) {
        flota.remove(buque);
        System.out.println("Buque " + buque.getNombre() + " (" + buque.getTipo() + ") se ha eliminado de la flota");
    }


    private double Recompensa(Buque buque){
        return 20 * buque.getPeso();
    }

    //Consideramos que cada buque recibe una recompensa de 25 por tonelada
    public void ingresarRecompensa(Buque buque){
        double recompensa = Recompensa(buque);
        fondos += recompensa;
        System.out.println("Recompensa: " + recompensa + " | Fondos Base: " + fondos);
    }


    //Consideramos que la reparación cuesta 12 por tonelada

    private double costeReparacion(Buque buque){
        return 12 * buque.getPeso();
    }

    public boolean cobrarCosteReparacion(Buque buque){
        double coste = costeReparacion(buque);
        if(fondos >= coste){
            fondos -= coste;
            System.out.println("Coste Reparación: " + coste + " | Fondos Base: " + fondos);
            return true;
        }else
            return false;
    }

    private boolean hayBuqueEnReparacion() {
        for (Buque buque : flota) {
            if (buque.getEstado().esReparacion()) {
                return true;
            }
        }
        return false;
    }

    public void InciarEjercicio(Buque buque){
        buque.IniciarEjercicio(buque);
    }

    public void volverBase (Buque b, Dano d){
        if (flota.contains(b)){
            b.volverBase(b,d);
            if (d != Dano.DanoMasivo){
                ingresarRecompensa(b);
            }
        }else{
            System.out.println("El buque no está en la flota");
        }
    }

    // Reparar buque
    public void Reparacion(Buque b) {
        if(!cobrarCosteReparacion(b)) {
            System.out.println("Fondos insuficientes para reparar" + b.getNombre() + ", queda pendiente de reparación");
            return;
        }
        if (!hayBuqueEnReparacion()) {
            b.Reparacion(b);
        }else
            System.out.println("No se puede reparar " + b.getNombre() + " porque hay otro buque en reparación, queda pendiente de reparación");
    }

    // Reparar buque express
    public void ReparacionExpress(Buque b) {
        if (!hayBuqueEnReparacion()) {
            b.ReparacionExpress(b);
        } else
            System.out.println("No se puede reparar" + b.getNombre() + " porque hay un buque en reparación, queda pendiente de reparación express");

    }

    public void cancelarReparacion(Buque b){
        b.cancelarReparacion(b);
    }
    public void terminarReparacion(Buque b, Dano d){
        b.finReparacion(b, d);
    }
    public void Desmantelar(Buque b){
        b.Desmantelamiento(b);
    }

    // Listar flota activa
    public void listarBuquesEnActivo() {
        System.out.println("BUQUES ACTIVOS\n--------------------------------\n");
        for (Buque buque : flota) {
            if(!buque.esInactivo()){
                System.out.println("Name: " + buque.getNombre() + " (" + buque.getTipo() + ") | State: " + buque.getEstado() + " | Missions: " + buque.getMisiones());
            }
        }
    }

    // Listar flota inactiva
    public void listarBuquesEnInactivo() {
        System.out.println("BUQUES INACTIVOS\n--------------------------------\n");
        for (Buque buque : flota) {
            if(buque.esInactivo()){
                System.out.println("Name: " + buque.getNombre() + " (" + buque.getTipo() + ") | Razón: " + buque.getEstado() + " | Missions: " + buque.getMisiones());
            }
        }
    }

    // Verificar ingresos y gastos proyectados
    public void reporteFinanciero() {
        double ingresosEsperados = 0, gastosEsperados = 0, fondosEsperados;
        for (Buque buque : flota) {
            if(!buque.esInactivo()) {
                if (buque.esEjercicio())
                    ingresosEsperados += Recompensa(buque);
                if (buque.esPendReparacion())
                    gastosEsperados += costeReparacion(buque);
            }
        }

        fondosEsperados = fondos + ingresosEsperados - gastosEsperados;

        System.out.println("REPORTE FINANCIERO\n----------------------------");
        System.out.println("Fondos Actuales: " + fondos);
        System.out.println("Ingresos esperados en ejercicios navales: " + ingresosEsperados);
        System.out.println("Gastos esperados en reparaciones: " + gastosEsperados);
        System.out.println("Fondos esperados: " + fondosEsperados);
    }
}
