package e1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Aldea {
    private Nombre nombre;
    private int edad;
    private Muralla muralla;
    private List<Tropa> tropas;


    public Aldea(Nombre nombre, int edad, Muralla muralla, int puntosMuralla) {
        this.nombre = nombre;
        this.edad = edad;

        //Verifica si los puntos de la muralla son válidos
        if (!muralla.isValidPunto(puntosMuralla)) {
            throw new IllegalArgumentException("Nivel de muralla inválido para el tipo de muralla seleccionado");
        }

        this.muralla = muralla;
        this.tropas = new ArrayList<>();
    }

    //Métodos abstractos para ataque y defensa
    public abstract int calcularPuntosDeAtaque();

    public abstract int calcularPuntosDeDefensa();

    public void addTropa(Tropa tropa) {
        if (tropa == null) {
            throw new IllegalArgumentException("Tropa no puede ser nula");
        }
        if (tropa.getNombre().equals(this.nombre)) {
            tropas.add(tropa);
        } else {
            throw new IllegalArgumentException("La tropa no pertenece a esta aldea.");
        }
    }


    public List<Tropa> getTropas() {
        return new ArrayList<>(tropas); //Retorna una copia para mantener inmutabilidad
    }

    //Métodos getters
    public Nombre getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public Muralla getMuralla() {
        return muralla;
    }

}

class AldeaRomanos extends Aldea {

    public AldeaRomanos(int edad, int puntosMuralla) {
        super(Nombre.Romanos, edad, Muralla.piedra, puntosMuralla);
    }

    @Override
    public int calcularPuntosDeAtaque() {
        if (getTropas().isEmpty()) {
            return 0;
        }
        double multiplicador = 1.1; //10% más
        return (int) getTropas().stream()
                .mapToInt(tropa -> (int) (tropa.getPuntosdeAtaque() * multiplicador))
                .sum();
    }

    @Override
    public int calcularPuntosDeDefensa() {
        if (getTropas().isEmpty()) {
            return 0;
        }
        double multiplicador = 2.0; //Aumento en 2 puntos por nivel de muralla
        int puntosMuralla = Arrays.stream(getMuralla().getPuntos()).sum();
        return (int) getTropas().stream()
                .mapToInt(tropa -> (int) (tropa.getPuntosdeDefensa() + (multiplicador * puntosMuralla)))
                .sum();
    }
}

class AldeaGalos extends Aldea {

    public AldeaGalos(int edad, int puntosMuralla) {
        super(Nombre.Galos, edad, Muralla.madera, puntosMuralla); //Cambia según el tipo de muralla
    }

    @Override
    public int calcularPuntosDeAtaque() {
        if (getTropas().isEmpty()) {
            return 0;
        }
        double multiplicador = 1.2; //20% más
        return (int) getTropas().stream()
                .mapToInt(tropa -> (int) (tropa.getPuntosdeAtaque() * multiplicador))
                .sum();
    }

    @Override
    public int calcularPuntosDeDefensa() {
        if (getTropas().isEmpty()) {
            return 0;
        }
        double multiplicador = 1.5; //Aumento en 1.5 puntos por nivel de muralla
        int puntosMuralla = Arrays.stream(getMuralla().getPuntos()).sum();
        return (int) getTropas().stream()
                .mapToInt(tropa -> (int) (tropa.getPuntosdeDefensa() + (multiplicador * puntosMuralla)))
                .sum();
    }
}

class AldeaTeutones extends Aldea {

    public AldeaTeutones(int edad, int puntosMuralla) {
        super(Nombre.Teutones, edad, Muralla.tierra, puntosMuralla); //Cambia según el tipo de muralla
    }

    @Override
    public int calcularPuntosDeAtaque() {
        if (getTropas().isEmpty()) {
            return 0;
        }
        double multiplicador = 0.95; //5% menos
        return (int) getTropas().stream()
                .mapToInt(tropa -> (int) (tropa.getPuntosdeAtaque() * multiplicador))
                .sum();
    }

    @Override
    public int calcularPuntosDeDefensa() {
        if (getTropas().isEmpty()) {
            return 0;
        }
        double multiplicador = 1.0; //Aumento en 1 punto por nivel de muralla
        int puntosMuralla = Arrays.stream(getMuralla().getPuntos()).sum();
        return (int) getTropas().stream()
                .mapToInt(tropa -> (int) (tropa.getPuntosdeDefensa() + (multiplicador * puntosMuralla)))
                .sum();
    }
}
