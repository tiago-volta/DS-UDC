package e3;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ColeccionMonedas implements Iterable<Moneda> {
    private List<Moneda> monedas = new ArrayList<>();
    private String paisFiltro;
    private int modCount = 0;

    public ColeccionMonedas(String paisFiltro) {
        setPaisFiltro(paisFiltro);
    }

    public void addMoneda(Moneda moneda) {
        if (moneda == null) {
            throw new IllegalArgumentException("La moneda no puede ser nula");
        }
        monedas.add(moneda);
        modCount++;  //Incrementamos modCount para señalar una modificación en la colección
    }

    public void setPaisFiltro(String paisFiltro) {
        if (paisFiltro != null && !Moneda.VALID_COUNTRIES.contains(paisFiltro)) {
            throw new IllegalArgumentException("País no válido.");

        }
        this.paisFiltro = paisFiltro;
    }

    @Override
    public Iterator<Moneda> iterator() {
        return new ColeccionMonedasIterator();
    }

    private class ColeccionMonedasIterator implements Iterator<Moneda> {
        private int cursor = 0;
        private int expectedModCount = modCount;
        private Moneda lastReturned = null;

        @Override
        public boolean hasNext() {
            checkForConcurrentModification();
            while (cursor < monedas.size()) {  //Avanza hasta el siguiente elemento que coincida con el filtro de país
                Moneda moneda = monedas.get(cursor);
                if (paisFiltro == null || paisFiltro.equals(moneda.getPais())) {
                    return true;
                }
                cursor++;
            }
            return false;
        }

        @Override
        public Moneda next() {
            checkForConcurrentModification();
            if (!hasNext()) {
                throw new NoSuchElementException("No hay más elementos que coincidan con el filtro.");
            }
            lastReturned = monedas.get(cursor++);  //Guarda la última moneda retornada y avanza
            return lastReturned;
        }

        @Override
        public void remove() {
            checkForConcurrentModification();
            if (lastReturned == null) {
                throw new IllegalStateException("No se puede eliminar sin haber llamado a next() primero, o después de un remove() consecutivo.");
            }
            monedas.remove(--cursor);   //Remueve el último elemento devuelto por next()
            lastReturned = null;   //Reset de lastReturned
            expectedModCount = ++modCount;   //Actualiza modCount para reflejar el cambio en la colección
        }

        private void checkForConcurrentModification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("La colección fue modificada externamente durante la iteración.");
            }
        }
    }
}
