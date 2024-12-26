package e1;

import java.util.ArrayList;
import java.util.List;

public class Travian {
    public static List<String> batalla(Aldea aldea1, Aldea aldea2, String atacante) {
        List<String> resultado = new ArrayList<>();

        // Verificar si no hay tropas en uno o ambos equipos
        if (aldea1.getTropas().isEmpty() || aldea2.getTropas().isEmpty()) {
            resultado.add("No troops to battle.");
            return resultado;
        }

        if (aldea1.getTropas().isEmpty() && aldea2.getTropas().isEmpty()) {
            resultado.add("No troops to battle.");
            return resultado;
        }

        //Calcular puntos de ataque y defensa utilizando los nuevos métodos
        int puntosAtaqueAldea1 = aldea1.calcularPuntosDeAtaque();
        int puntosDefensaAldea2 = aldea2.calcularPuntosDeDefensa();

        //Mensajes de inicio de batalla
        resultado.add("### Starts the battle ! --> " + aldea1.getNombre() + " Attacks " + aldea2.getNombre() + " ! ###");

        //Detalles del ejército atacante
        resultado.add(aldea1.getNombre() + " have the following soldiery :");
        for (Tropa tropa : aldea1.getTropas()) {
            resultado.add(tropa.toString());
        }
        resultado.add("Total " + aldea1.getNombre() + " attack power : " + puntosAtaqueAldea1);

        //Detalles del ejército defensor
        resultado.add(aldea2.getNombre() + " have the following soldiery :");
        for (Tropa tropa : aldea2.getTropas()) {
            resultado.add(tropa.toString());
        }
        resultado.add("Total " + aldea2.getNombre() + " defense power : " + puntosDefensaAldea2);

        //Determinación del ganador
        if (puntosAtaqueAldea1 > puntosDefensaAldea2) {
            resultado.add(aldea1.getNombre() + " with an age of " + aldea1.getEdad() + " years WINS !");
        } else if (puntosAtaqueAldea1 < puntosDefensaAldea2) {
            resultado.add(aldea2.getNombre() + " with an age of " + aldea2.getEdad() + " years WINS !");
        } else {
            //En caso de empate
            resultado.add("No winner");
        }

        return resultado;
    }

    public static void main(String[] args) {


        //Crea aldeas
        Aldea aldeaRomanos = new AldeaRomanos(10, 8);
        Aldea aldeaGalos = new AldeaGalos(8, 4);

        //Tropas para Romanos
        Tropa legionario = new Legionarios(Armadura.NO_equipada, Arma.normal);
        Tropa pretoriano = new Pretorianos(Armadura.normal, Arma.normal);
        Tropa equitesImperatoris = new EquitesImperatoris(Armadura.NO_equipada, Arma.normal);

        //Tropas para Galos
        Tropa druida = new Druidas(Armadura.NO_equipada, Arma.normal);
        Tropa rayoDeTeutates = new RayosdeTeutates(Armadura.pesada, Arma.normal);
        Tropa falange = new Falanges(Armadura.NO_equipada, Arma.normal);

        //Añade tropas a Romanos
        aldeaRomanos.addTropa(legionario);
        aldeaRomanos.addTropa(pretoriano);
        aldeaRomanos.addTropa(equitesImperatoris);

        //Añade tropas a Galos
        aldeaGalos.addTropa(druida);
        aldeaGalos.addTropa(rayoDeTeutates);
        aldeaGalos.addTropa(falange);

        //Simula la batalla
        List<String> resultado = Travian.batalla(aldeaRomanos, aldeaGalos, "Aldea1");

        //Imprime el resultado
        for (String linea : resultado) {
            System.out.println(linea);
        }
    }
}