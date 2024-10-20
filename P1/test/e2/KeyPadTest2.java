package e2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyPadTest2 {
    public static char[][] emptyKeyPad = {{}};

    public static char[][] largeKeyPad
            = {{'1', '2', '3', '4', '5', '6', '7'},
            {'8', '9', '0', 'A', 'B', 'C', 'D'},
            {'E', 'F', 'G', 'H', 'I', 'J', 'K'},
            {'L', 'M', 'N', 'O', 'P', 'Q', 'R'},
            {'S', 'T', 'U', 'V', 'W', 'X', 'Y'},
            {'Z', '1', '2', '3', '4', '5', '6'}};

    public static String[] wrongSymbols = {"ULL", "RR!DD", "LU@DL"};
    public static String[] emptyMovements = {"", "", ""};

    /*Verifica teclados que no cumplen los requisitos, como un teclado vacío, teclados con celdas inválidas,
o con caracteres no permitidos como símbolos.*/
    @Test
    void invalidKeyPadTest() {
        char[][] wrongSingleCell = {{'*'}};

        char[][] wrongSpecialChars
                = {{'1', '2', '3'},
                {'4', '@', '6'},
                {'7', '8', '9'}};

        assertFalse(KeyPad.isValidKeyPad(emptyKeyPad)); // Teclado vacío
        assertFalse(KeyPad.isValidKeyPad(wrongSingleCell)); // Una celda con carácter inválido
        assertFalse(KeyPad.isValidKeyPad(wrongSpecialChars)); // Carácter especial en teclado
    }

    //Valida que secuencias de movimientos que contienen caracteres no válidos (símbolos o espacios) no son aceptadas.
    @Test
    void invalidMovementsTest() {
        String[] wrongSpecial = {"U!LD", "RLLR", "DD@U"};

        assertFalse(KeyPad.areValidMovements(wrongSpecial)); // Movimientos con caracteres especiales
        assertFalse(KeyPad.areValidMovements(wrongSymbols)); // Movimientos con símbolos no válidos
        assertFalse(KeyPad.areValidMovements(emptyMovements)); // Movimientos vacíos
    }

    @Test
    void mixedValidInvalidMovementsTest() {
        String[] mixedMovements = {"UUDDLRLR", "RR!DD", "LU@DL"};
        assertFalse(KeyPad.areValidMovements(mixedMovements)); // Movimientos mixtos
    }
}
