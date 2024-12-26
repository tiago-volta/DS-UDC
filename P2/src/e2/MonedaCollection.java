package e2;

import java.util.*;

public class MonedaCollection{

    //Atributo para almacenar la colección de Moneda
    private final Set<Monedas> collection;

    //Inicializa la colección
    public MonedaCollection() {
        this.collection = new HashSet<>();  // Usamos HashSet para evitar duplicados
    }

    //Método para añadir una moneda a la colección
    public boolean addMoneda(Monedas coin) {
        if (coin == null) {
            throw new IllegalArgumentException("La moneda no puede ser nula");
        }
        //añade solo si no está repetida
        return collection.add(coin);  // add() en Set devuelve false si el objeto ya está presente
    }

    //Método para eliminar una moneda de la colección
    public boolean removeCoin(Monedas coin) {
        if (coin == null)
            throw new IllegalArgumentException("La moneda no puede ser nula");
        return collection.remove(coin);  // remove() devuelve true si se eliminó correctamente
    }

    //Método para contar cuántas Moneda hay en la colección
    public int totalCoins() {
        return collection.size();
    }

    //Método para calcular el valor total de la colección en céntimos
    public int totalValue() {
        int total = 0;
        for (Monedas coin : collection) {
            total += coin.getValor();  //suma el valor de cada moneda
        }
        return total;
    }

    //Método para comprobar si una moneda ya está en la colección
    public boolean containsCoin(Monedas coin) {
        return collection.contains(coin);
    }

    /*Método para mostrar la colección de Moneda, usamos string builder porque es más eficiente que el operador de concatenación +,
    ya que, este crearía un string en cada iteración, es decir, se compila a StringBuilder y toString, mientras que el StringBuilder
     simplemente almacena los trozos del string y lo construye cuando se llama a toString de esa variable StringBuilder */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Colección de Moneda:\n");
        for (Monedas coin : collection) {
            sb.append(coin).append("\n");  //Utiliza el método toString() de Moneda
        }
        return sb.toString();
    }

    //Método para obtener una lista de Moneda (copia de la colección)
    public List<Monedas> getCoins() {
        return new ArrayList<>(collection); //suponemos que collection es una lista de Moneda
    }

    // Método para ordenar la colección según el orden natural de Moneda (Comparable)
    public List<Monedas> sort() {
        List<Monedas> listaOrdenada = getCoins();  // Obtenemos una copia de la colección
        listaOrdenada.sort(Monedas::compareTo);    // Ordenamos según el orden natural
        return listaOrdenada;
    }

    // Método para ordenar la colección usando un Comparator personalizado
    public List<Monedas> sortPersonalized(Comparator<Monedas> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator no puede ser nulo");
        }
        List<Monedas> listaOrdenada = getCoins();  // Obtenemos una copia de la colección
        listaOrdenada.sort(comparator);           // Ordenamos usando el Comparator dado
        return listaOrdenada;
    }
}