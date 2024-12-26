package e2;

public class DatosAccion {
    private String symbol;
    private double closePrice;
    private double maxPrice;
    private double minPrice;
    private int volumen;

    // Constructor, getters, and setters
    public DatosAccion(String symbol, double closePrice, double maxPrice, double minPrice, int volumen) {
        if (!isValidSymbol(symbol)) {
            throw new IllegalArgumentException("Símbolo inválido: " + symbol);
        }
        this.symbol = symbol;
        this.closePrice = closePrice;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.volumen = volumen;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public int getVolumen() {
        return volumen;
    }

    public static boolean isValidSymbol(String symbol) {
        return (symbol != null && symbol.matches("[A-Z]{1,4}"));
    }

    public static boolean isValidPrices(double closePrice, double maxPrice, double minPrice) {
        return closePrice >= 0 && maxPrice >= 0 && minPrice >= 0 &&
                maxPrice >= closePrice && closePrice >= minPrice &&
                hasTwoDecimalPlaces(closePrice) &&
                hasTwoDecimalPlaces(maxPrice) &&
                hasTwoDecimalPlaces(minPrice);
    }

    private static boolean hasTwoDecimalPlaces(double price) {
        // Compara si el número es igual a sí mismo truncado a dos decimales
        return price == Math.floor(price * 100) / 100;
    }

}
