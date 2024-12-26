package e2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class Accion implements Subject {
    private static final Logger logger = Logger.getLogger(Accion.class.getName());
    private final List<Observador> observers;
    private DatosAccion stockData;

    public Accion() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observador o) {
        observers.add(o);
        logger.info("Observador registrado: " + o.getClass().getSimpleName());
    }

    @Override
    public void removeObserver(Observador o) {
        observers.remove(o);
        logger.info("Observador eliminado: " + o.getClass().getSimpleName());
    }

    @Override
    public void notifyObservers() {
        for (Observador observer : observers) {
            observer.update(stockData);
            logger.info("Notificado: " + observer.getClass().getSimpleName());
        }
    }

    public void notifyObservers(Predicate<Observador> filter) {
        for (Observador observer : observers) {
            if (filter.test(observer)) {
                observer.update(stockData);
                logger.info("Notificado (filtrado): " + observer.getClass().getSimpleName());
            }
        }
    }

    public void setStockData(DatosAccion stockData) {
        if (DatosAccion.isValidSymbol(stockData.getSymbol())) {
            if (DatosAccion.isValidPrices(stockData.getClosePrice(), stockData.getMaxPrice(), stockData.getMinPrice())) {
                this.stockData = stockData;
                notifyObservers();
            } else {
                throw new IllegalArgumentException("Precios inválidos: cierre=" + stockData.getClosePrice() +
                        ", máximo=" + stockData.getMaxPrice() + ", mínimo=" + stockData.getMinPrice());
            }
        } else {
            throw new IllegalArgumentException("Símbolo inválido: " + stockData.getSymbol());
        }
    }

    public List<Observador> getObservers() {
        return Collections.unmodifiableList(observers);
    }

    public DatosAccion getStockData() {
        return stockData;
    }
}