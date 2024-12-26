package e2;

public class Client implements Observador {
    private final DisplayStrategy strategy;

    public Client(DisplayStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("La estrategia de visualización no puede ser nula.");
        }
        this.strategy = strategy;
    }

    @Override
    public void update(DatosAccion data) {
        strategy.display(data);
    }
    public DisplayStrategy getStrategy() {
        return strategy;
    }

    public static Client createClient(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de cliente no puede ser nulo o vacío.");
        }
        switch (type.toLowerCase()) {
            case "detallado":
                return new Client(new DetailedDisplayStrategy());
            case "sencillo":
                return new Client(new SimpleDisplayStrategy());
            default:
                throw new IllegalArgumentException("Tipo de cliente desconocido: " + type);
        }
    }
}