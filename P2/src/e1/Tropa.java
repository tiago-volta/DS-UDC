package e1;

public abstract class Tropa {
    protected int PuntosdeAtaque;
    protected int PuntosdeDefensa;
    protected Armadura armadura;
    protected Arma arma;
    private Nombre nombre;

    public Tropa(Armadura armadura, Arma arma, Nombre nombre) {
        this.armadura = armadura;
        this.arma = arma;
        this.nombre = nombre;
        calcularPuntos();
    }

    protected abstract void calcularPuntos();

    public int getPuntosdeAtaque() {
        return PuntosdeAtaque;
    }

    public int getPuntosdeDefensa() {
        return PuntosdeDefensa;
    }

    public Nombre getNombre(){
        return nombre;
    }

    @Override
    public abstract String toString();

}


//Subclase para Legionarios
class Legionarios extends Tropa {
    public Legionarios(Armadura armadura, Arma arma) {
        super(armadura, arma, Nombre.Romanos);
    }

    @Override
    protected void calcularPuntos() {
        this.PuntosdeAtaque = 40;
        this.PuntosdeDefensa = 35;
    }

    @Override
    public String toString() {
        return "Legionarios";
    }
}

//Subclase para Pretorianos
class Pretorianos extends Tropa {
    public Pretorianos(Armadura armadura, Arma arma) {
        super(armadura, arma, Nombre.Romanos);
    }

    @Override
    protected void calcularPuntos() {
        this.PuntosdeAtaque = 30;
        this.PuntosdeDefensa = (armadura == Armadura.pesada) ? (int) (65 * 1.1) : 65;
    }

    @Override
    public String toString() {
        return "Pretorianos";
    }
}

//Subclase para Equites Imperatoris
class EquitesImperatoris extends Tropa {
    public EquitesImperatoris(Armadura armadura, Arma arma) {
        super(armadura, arma, Nombre.Romanos);
    }

    @Override
    protected void calcularPuntos() {
        this.PuntosdeAtaque = 120;
        this.PuntosdeDefensa = 65;
    }

    @Override
    public String toString() {
        return "Equites Imperatoris";
    }
}

//Subclase para Druidas
class Druidas extends Tropa {
    public Druidas(Armadura armadura, Arma arma) {
        super(armadura, arma, Nombre.Galos);
    }

    @Override
    protected void calcularPuntos() {
        this.PuntosdeAtaque = 45;
        this.PuntosdeDefensa = 115;
    }

    @Override
    public String toString() {
        return "Druidas";
    }
}

//Subclase para Rayos de Teutates
class RayosdeTeutates extends Tropa {
    public RayosdeTeutates(Armadura armadura, Arma arma) {
        super(armadura, arma, Nombre.Galos);
    }

    @Override
    protected void calcularPuntos() {
        this.PuntosdeAtaque = (armadura == Armadura.pesada) ? (int) (100 * 0.75) : 100;
        this.PuntosdeDefensa = (armadura == Armadura.pesada) ? (int) (25 * 1.25) : 25;
    }

    @Override
    public String toString() {
        return "Rayos de Teutates";
    }
}

//Subclase para Falanges
class Falanges extends Tropa {
    public Falanges(Armadura armadura, Arma arma) {
        super(armadura, arma, Nombre.Galos);
    }

    @Override
    protected void calcularPuntos() {
        this.PuntosdeAtaque = 15;
        this.PuntosdeDefensa = 40;
    }

    @Override
    public String toString() {
        return "Falanges";
    }
}

//Subclase para Guerreros de Hacha
class GuerrerosdeHacha extends Tropa {
    public GuerrerosdeHacha(Armadura armadura, Arma arma) {
        super(armadura, arma, Nombre.Teutones);
    }

    @Override
    protected void calcularPuntos() {
        this.PuntosdeAtaque = 60;
        this.PuntosdeDefensa = 30;
    }

    @Override
    public String toString() {
        return "Guerreros de Hacha";
    }
}

//Subclase para Guerreros de Maza
class GuerrerosdeMaza extends Tropa {
    public GuerrerosdeMaza(Armadura armadura, Arma arma) {
        super(armadura, arma, Nombre.Teutones);
    }

    @Override
    protected void calcularPuntos() {
        this.PuntosdeAtaque = (arma == Arma.metal) ? (int) (40 * 1.25) : 40;
        this.PuntosdeDefensa = 20;
    }

    @Override
    public String toString() {
        return "Guerreros de Maza";
    }
}

//Subclase para Paladines
class Paladines extends Tropa {
    public Paladines(Armadura armadura, Arma arma) {
        super(armadura, arma, Nombre.Teutones);
    }

    @Override
    protected void calcularPuntos() {
        this.PuntosdeAtaque = 55;
        this.PuntosdeDefensa = 100;
    }

    @Override
    public String toString() {
        return "Paladines";
    }
}