package e2;

public class SimpleDisplayStrategy implements DisplayStrategy {
    @Override
    public void display(DatosAccion data) {
        System.out.println("SimpleClient - Símbolo: " + data.getSymbol() +
                ", Cierre: " + data.getClosePrice());
    }
}