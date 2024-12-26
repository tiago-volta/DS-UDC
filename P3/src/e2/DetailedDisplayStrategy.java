package e2;

public class DetailedDisplayStrategy implements DisplayStrategy {
    @Override
    public void display(DatosAccion data) {
        System.out.println("DetailedClient - Símbolo: " + data.getSymbol() +
                ", Cierre: " + data.getClosePrice() +
                ", Máximo: " + data.getMaxPrice() +
                ", Mínimo: " + data.getMinPrice() +
                ", Volumen: " + data.getVolumen());
    }
}