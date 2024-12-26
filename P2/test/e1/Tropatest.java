package e1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Tropatest {

    @Test
    public void testLegionarios() {
        Tropa legionarios = new Legionarios(Armadura.normal, Arma.normal);
        assertEquals(40, legionarios.getPuntosdeAtaque());
        assertEquals(35, legionarios.getPuntosdeDefensa());
    }

    @Test
    public void testPretorianosConArmaduraPesada() {
        Tropa pretorianos = new Pretorianos(Armadura.pesada, Arma.normal);
        assertEquals(30, pretorianos.getPuntosdeAtaque());
        assertEquals((int) (65 * 1.1), pretorianos.getPuntosdeDefensa());
    }

    @Test
    public void testPretorianosConArmaduraNormal() {
        Tropa pretorianos = new Pretorianos(Armadura.normal, Arma.normal);
        assertEquals(30, pretorianos.getPuntosdeAtaque());
        assertEquals(65, pretorianos.getPuntosdeDefensa());
    }

    @Test
    public void testPretorianosSinArmadura() {
        Tropa pretorianos = new Pretorianos(Armadura.NO_equipada, Arma.normal);
        assertEquals(30, pretorianos.getPuntosdeAtaque());
        assertEquals(65, pretorianos.getPuntosdeDefensa());
    }

    @Test
    public void testEquitesImperatoris() {
        Tropa equites = new EquitesImperatoris(Armadura.normal, Arma.normal);
        assertEquals(120, equites.getPuntosdeAtaque());
        assertEquals(65, equites.getPuntosdeDefensa());
    }


    @Test
    public void testDruidas() {
        Tropa druidas = new Druidas(Armadura.normal, Arma.normal);
        assertEquals(45, druidas.getPuntosdeAtaque());
        assertEquals(115, druidas.getPuntosdeDefensa());
    }


    @Test
    public void testRayosdeTeutatesConArmaduraPesada() {
        Tropa rayos = new RayosdeTeutates(Armadura.pesada, Arma.normal);
        assertEquals((int) (100 * 0.75), rayos.getPuntosdeAtaque());
        assertEquals((int) (25 * 1.25), rayos.getPuntosdeDefensa());
    }

    @Test
    public void testRayosdeTeutatesConArmaduraNormal() {
        Tropa rayos = new RayosdeTeutates(Armadura.normal, Arma.normal);
        assertEquals(100, rayos.getPuntosdeAtaque());
        assertEquals(25, rayos.getPuntosdeDefensa());
    }

    @Test
    public void testFalanges() {
        Tropa falanges = new Falanges(Armadura.normal, Arma.normal);
        assertEquals(15, falanges.getPuntosdeAtaque());
        assertEquals(40, falanges.getPuntosdeDefensa());
    }

    @Test
    public void testGuerrerosdeHacha() {
        Tropa guerreroHacha = new GuerrerosdeHacha(Armadura.normal, Arma.normal);
        assertEquals(60, guerreroHacha.getPuntosdeAtaque());
        assertEquals(30, guerreroHacha.getPuntosdeDefensa());
    }

    @Test
    public void testGuerrerosdeMazaConArmaMetal() {
        Tropa guerreroMaza = new GuerrerosdeMaza(Armadura.normal, Arma.metal);
        assertEquals((int) (40 * 1.25), guerreroMaza.getPuntosdeAtaque());
        assertEquals(20, guerreroMaza.getPuntosdeDefensa());
    }

    @Test
    public void testGuerrerosdeMazaConArmaNormal() {
        Tropa guerreroMaza = new GuerrerosdeMaza(Armadura.normal, Arma.normal);
        assertEquals(40, guerreroMaza.getPuntosdeAtaque());
        assertEquals(20, guerreroMaza.getPuntosdeDefensa());
    }

    @Test
    public void testPaladines() {
        Tropa paladines = new Paladines(Armadura.normal, Arma.normal);
        assertEquals(55, paladines.getPuntosdeAtaque());
        assertEquals(100, paladines.getPuntosdeDefensa());
    }

}