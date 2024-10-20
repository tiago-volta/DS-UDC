package e4;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;


public class EuroCoin {
    //atributos de la moneda
    private int valor;
    private Color color;
    private Pais pais;
    private String diseno;
    private int anoAcunacion;

    public enum Color{
        ORO, PLATA, BRONCE
    }

    private static final EnumSet<Color> VALID_COLOR = EnumSet.allOf(Color.class);

    public enum Pais {
        AD, AT, BE, CY, HR, EE, FI, FR, DE, GR, IE, IT, LV, LT, LU, MT, MC, NL, PT, SM, SK, SI, ES, VA
    }

    private static final EnumSet<Pais> VALID_COUNTRIES = EnumSet.allOf(Pais.class);

    public static final Set<Integer> VALID_VALUES = Set.of(1, 2, 5, 10, 20, 50, 100, 200);

    public EuroCoin(int valor, Color color, Pais pais, String diseno, int anoAcunacion){

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
        if (pais == null) {
            throw new IllegalArgumentException("El país no puede ser nulo.");
        }
        if (!isValidPais(pais)) {
            throw new IllegalArgumentException("El país no es válido.");
        }
        this.valor = valor;
        this.color = color;
        this.pais = pais;
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

    //Métodos getter para acceder a los atributos, permiten acceso a los atributos privados de una clase
    public int getValor() {//devuelve el valor de la moneda
        return valor;
    }

    public Color getColor() {//devuelve el color de la moneda
        return color;
    }

    public Pais getPais() {//devuelve el pais de la moneda
        return pais;
    }

    public String getDiseno() {//devuelve el diseño de la moneda
        return diseno;
    }

    public int getAnoAcunacion() {//devuelve el año de acuñación de la moneda
        return anoAcunacion;
    }

    //Iguales las monedas si tienen el mismo valor, pais y diseño
    @Override
    public boolean equals(Object o) {   //Redefinimos el equals
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EuroCoin euroCoin = (EuroCoin) o;
        return valor == euroCoin.valor &&
                pais == euroCoin.pais &&
                Objects.equals(diseno, euroCoin.diseno);
    }
    //Redefinimos el hashCode() en función del valor, país y diseño.
    @Override
    public int hashCode() { //si dos objetos son iguales por el metodo equals, su valor de hash debe ser igual
        return Objects.hash(valor, pais, diseno);
    }

    @Override
    public String toString() { //se utiliza para devolver una representación en forma de cadena del objeto
        return "EuroCoin{" +
                "valor=" + valor +
                ", color=" + color +
                ", pais=" + pais +
                ", diseno='" + diseno + '\'' +
                ", anoAcunacion=" + anoAcunacion +
                '}';
    }

}
