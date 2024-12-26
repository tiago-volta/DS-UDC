package e2;

import java.util.Comparator;

//Ponemos compare en una clase por organización y para aprovechar que sea más fácil de usar en las instancias, además porque es un comparador distinto fuera del orden natural pedido
public class Comparador implements Comparator<Monedas> {
    @Override
    public int compare(Monedas o1, Monedas o2) {
        int comparePais = o1.getPais().compareTo(o2.getPais());
        if(comparePais != 0){
            return comparePais;
        }
        int compareValor = Integer.compare(o1.getValor(), o2.getValor());
        if (compareValor != 0) {
            return compareValor;
        }
        return Integer.compare(o1.getAnoAcunacion(), o2.getAnoAcunacion());
    }
}

