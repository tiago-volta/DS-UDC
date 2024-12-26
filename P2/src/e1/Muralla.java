package e1;

public enum Muralla {
    tierra(new int[]{1, 2, 3}),
    madera(new int[]{4, 5, 6}),
    piedra(new int[]{7, 8, 9});

    private final int[] puntos;

    Muralla(int[] puntos) {
        this.puntos = puntos;
    }

    public int[] getPuntos() {
        return puntos;
    }

    //Método para verificar si un punto específico pertenece a una muralla
    public boolean isValidPunto(int punto) {
        for (int p : puntos) {
            if (p == punto) {
                return true;
            }
        }
        return false;
    }
}
