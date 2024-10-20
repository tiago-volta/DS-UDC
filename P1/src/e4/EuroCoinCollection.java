package e4;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class EuroCoinCollection {

    //Atributo para almacenar la colección de monedas
    private final Set<EuroCoin> collection;

    //Inicializa la colección
    public EuroCoinCollection() {
        this.collection = new HashSet<>();  // Usamos HashSet para evitar duplicados
    }

    //Método para añadir una moneda a la colección
    public boolean addCoin(EuroCoin coin) {
        if (coin == null) {
            throw new IllegalArgumentException("La moneda no puede ser nula");
        }
        //añade solo si no está repetida
        return collection.add(coin);  // add() en Set devuelve false si el objeto ya está presente
    }

    //Método para eliminar una moneda de la colección
    public boolean removeCoin(EuroCoin coin) {
        if (coin == null)
            throw new IllegalArgumentException("La moneda no puede ser nula");
        return collection.remove(coin);  // remove() devuelve true si se eliminó correctamente
    }

    //Método para contar cuántas monedas hay en la colección
    public int totalCoins() {
        return collection.size();
    }

    //Método para calcular el valor total de la colección en céntimos
    public int totalValue() {
        int total = 0;
        for (EuroCoin coin : collection) {
            total += coin.getValor();  //suma el valor de cada moneda
        }
        return total;
    }

    //Método para comprobar si una moneda ya está en la colección
    public boolean containsCoin(EuroCoin coin) {
        return collection.contains(coin);
    }

    //Método para mostrar la colección de monedas
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Colección de Monedas de Euro:\n");
        for (EuroCoin coin : collection) {
            sb.append(coin).append("\n");  //Utiliza el método toString() de EuroCoin
        }
        return sb.toString();
    }

    //Método para obtener una lista de monedas (copia de la colección)
    public List<EuroCoin> getCoins() {
        return new ArrayList<>(collection); //suponemos que `collection` es una lista de monedas
    }
}
