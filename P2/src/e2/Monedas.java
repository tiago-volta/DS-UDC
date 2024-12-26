package e2;

import java.lang.Comparable;
import java.util.EnumSet;
import java.util.Set;


//Comparator, es decir, el método compare no se implementa dentro de la clase moneda porque no se trata de el orden natural
public class Monedas  implements Comparable<Monedas> {
    //atributos de la moneda
    private int valor;
    private Color color;
    private Pais Pais;
    private String diseno;
    private int anoAcunacion;

    private static final Set<Integer> VALID_VALUES = Set.of(1, 2, 5, 10, 20, 50, 100, 200);

    private static final EnumSet<Color> VALID_COLOR = EnumSet.allOf(Color.class);

    private static final EnumSet<Pais> VALID_COUNTRIES = EnumSet.allOf(Pais.class);

    public Monedas(int valor, Color color, Pais Pais, String diseno, int anoAcunacion){
        //Validación del valor de la moneda
        if (!isValidValue(valor)) {
            throw new IllegalArgumentException("El valor de la moneda debe ser uno de los valores permitidos: 1, 2, 5, 10, 20, 50, 100, 200 céntimos.");
        }

        //Validación del año de acuñación del euro desde el año que empezaron a acuñarse hasta la actualidad
        if (anoAcunacion < 1999 || anoAcunacion > 2024) {
            throw new IllegalArgumentException("El año de acuñación debe estar entre 1999 y 2024.");
        }

        //Validación del color
        if (color == null) {
            throw new IllegalArgumentException("El color no puede ser nulo.");
        }
        if (!isValidColor(color)) {
            throw new IllegalArgumentException("El color no es válido.");
        }

        //Validación del país
        if (Pais == null) {
            throw new IllegalArgumentException("El país no puede ser nulo.");
        }
        if (!isValidPais(Pais)) {
            throw new IllegalArgumentException("El país no es válido.");
        }
        this.valor = valor;
        this.color = color;
        this.Pais = Pais;
        this.diseno = diseno;
        this.anoAcunacion = anoAcunacion;
    }

    private boolean isValidValue (int value){
        return VALID_VALUES.contains(value);
    }

    //Método para verificar si el color es válido
    private boolean isValidColor(Color color) {
        return VALID_COLOR.contains(color);
    }

    //Método para verificar si el país es válido
    private boolean isValidPais(Pais pais) {
        return VALID_COUNTRIES.contains(pais);
    }

    public int getValor() {
        return valor;
    }

    public Color getColor() {
        return color;
    }

    public Pais getPais(){
        return Pais;
    }

    public String getDiseno(){
        return diseno;
    }

    public int getAnoAcunacion(){
        return anoAcunacion;
    }

    //Se implementa el orden natural
    //Los enums en java no tienen compareToIgnoreCase porque no heredan de String,además los enums ya tienen su propio método de comparación llamado compareTo, este método lo que hace es ignorar el caso en el que hello y HELLO no son iguales que con este método si lo serán
    @Override
    public int compareTo(Monedas otraMoneda) {
        // Comparar por valor
        int compareValor = Integer.compare(this.getValor(), otraMoneda.getValor());
        if (compareValor != 0) {
            return compareValor;
        }

        // Comparar por Pais
        int comparePais = this.getPais().compareTo(otraMoneda.getPais());
        if (comparePais != 0) {
            return comparePais;
        }

        // Comparar por diseño
        return this.getDiseno().compareToIgnoreCase(otraMoneda.getDiseno());
    }

    @Override
    public String toString(){
        return "Moneda: " +
                "valor=" + valor +
                ", color=" + color +
                ", Pais=" + Pais +
                ", diseno=" + diseno +
                ", anoAcunacion=" + anoAcunacion;
    }

}