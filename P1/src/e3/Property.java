package e3;
import java.util.Objects;

public record Property(
        PropertyType tipo,
        String catastro,
        String direccion,
        String codigoPostal,
        float areaEnMetros,
        int habitaciones,
        int banos
) {
    @Override
    public String toString() {
        return tipo + "\n" +
                catastro + "\n" +
                direccion + "\n" +
                codigoPostal + "\n" +
                areaEnMetros + " meters, " +
                habitaciones + " rooms, " +
                banos + " bathrooms\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Property other = (Property) obj;
        return Objects.equals(catastro, other.catastro);    //Objects maneja varios nulos de manera segura
    }
    @Override
    public int hashCode() {         //Si dos objetos son iguales con equals, y quiero definir el hashCode(), entonces tendrán el mismo hashcode() para asegurar un comportamiento consistente entre las estructuras
        return Objects.hash(catastro);
    }
}
