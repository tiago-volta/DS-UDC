package e3;

import java.util.Objects;
import java.util.Set;

public class Moneda {
    private int valor;
    private String pais;

    public static final Set<Integer> VALID_VALUES = Set.of(1, 2, 5, 10, 20, 50, 100, 200);  //Valores válidos de las monedas
    public static final Set<String> VALID_COUNTRIES = Set.of(
            "AD", "AT", "BE", "CY", "HR", "EE", "FI", "FR", "DE", "GR", "IE",
            "IT", "LV", "LT", "LU", "MT", "MC", "NL", "PT", "SM", "SK", "SI",
            "ES", "VA"
    );  //Países válidos
    public Moneda(int valor, String pais) {
        if (!isValidValue(valor)) {  //Validación del valor de la moneda
            throw new IllegalArgumentException("El valor de la moneda debe ser uno de los valores permitidos: 1, 2, 5, 10, 20, 50, 100, 200.");
        }

        if (pais == null) {  //Validación del país
            throw new IllegalArgumentException("El país no puede ser nulo.");
        }
        if (!isValidPais(pais)) {
            throw new IllegalArgumentException("País no válido.");
        }
        this.valor = valor;
        this.pais = pais;
    }

    private boolean isValidValue(int valor) {
        return VALID_VALUES.contains(valor);
    }

    //Método para verificar si el país es válido
    private boolean isValidPais(String pais) {
        return VALID_COUNTRIES.contains(pais);
    }

    //Métodos getter
    public int getValor() {
        return valor;
    }

    public String getPais() {
        return pais;
    }

    //Igualdad entre monedas si tienen el mismo valor y país
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moneda moneda = (Moneda) o;
        return valor == moneda.valor && pais.equals(moneda.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor, pais);
    }

    @Override
    public String toString() {
        String tipo = (valor >= 100) ? "EURO" : "CENT";  //Si el valor es 100 o más, es "EURO"
        int displayValue = (valor >= 100) ? valor / 100 : valor;  //Convertimos a euros si es necesario
        return tipo + displayValue + "-" + pais;
    }
}
