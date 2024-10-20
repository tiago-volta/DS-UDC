package e2;

import java.util.HashSet;
import java.util.Set;

public class KeyPad {

    //Comprueba si el teclado es valido
    public static boolean isValidKeyPad(char[][] teclado) {

        if (teclado == null || teclado.length == 0 || teclado[0].length == 0) {
            return false;  //Teclado nulo o vacío
        }

        int numColumnas = teclado[0].length;   // Longitud de la primera fila
        for (char[] fila : teclado) {
            if (fila == null || fila.length != numColumnas) {
                return false;  // Teclado no rectangular o fila nula
            }
        }
        //El primer carácter debe ser '1'
        if (teclado[0][0] != '1')
            return false;

        Set<Character> seenChars = new HashSet<>();  //Para detectar duplicados
        char[] secuenciaValida = {
                '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z'
        };

        for (char[] fila : teclado) {
            for (char c : fila) {
                if (!isValidChar(c) || !seenChars.add(c)) {
                    return false;  // Carácter no válido o duplicado
                }
            }
        }
        //Si la secuencia es válida por filas o por columnas devolvemos que el teclado es válido
        return ValidateSequenceByRows(teclado,secuenciaValida) || ValidateSequenceByColumns(teclado,secuenciaValida);
    }
    //Comprueba si el caracter es válido
    private static boolean isValidChar(char c) {
        return (c >= '1' && c <= '9') || (c >= 'A' && c <= 'Z') || c == '0';
    }

    //Función auxiliar que valida la secuencia por filas
    private static boolean ValidateSequenceByRows(char[][] teclado,char [] secuenciaValida) {
        int SequencePos = 0;
        for (char[] fila : teclado) {
            for (char c : fila) {
                if (SequencePos < secuenciaValida.length && c != secuenciaValida[SequencePos]) {
                    return false;
                }
                SequencePos++; //avanza en la secuencia
            }
        }
        return true;
    }

    //Función auxiliar que valida la secuencia por columnas
    private static boolean ValidateSequenceByColumns(char[][] teclado,char [] secuenciaValida) {
        int SequencePos = 0;
        for (int col = 0; col < teclado[0].length; col++) {
            for (char[] chars : teclado) {
                if (SequencePos >= secuenciaValida.length || chars[col] != secuenciaValida[SequencePos]) {
                    return false;  // Secuencia no sigue el orden correcto
                }
                SequencePos++;
            }
        }
        return true;
    }

    //Verifica si la secuencia contiene solo los movimientos permitidos
    public static boolean areValidMovements(String[] movimientos) {
        if (movimientos == null)
            return false; //Secuencia es nula, no es válida

        //Verificamos si los movimientos son válidos
        for (String secuencia : movimientos) {
            if (secuencia == null || secuencia.isEmpty())
                return false; //Secuencia vacía o nula

            //Itera sobre cada movimiento
            for (char movimiento : secuencia.toCharArray()) {
                if (movimiento != 'U' && movimiento != 'D' && movimiento != 'L' && movimiento != 'R') {
                    return false; //Movimiento no válido
                }
            }
        }
        return true; //Devuelve true si la secuencia es valida
    }



    //Aplica los movimientos y retorna el caracter final
    public static String obtainCode(char[][] teclado, String[] movimientos) {
        if (!isValidKeyPad(teclado)) {
            throw new IllegalArgumentException("Teclado inválido");
        }
        if(!areValidMovements(movimientos)){
            throw new IllegalArgumentException("Secuencia de movimientos inválida");
        }

        StringBuilder codigo = new StringBuilder(); // almacena la clave generada
        int fila = 0, columna = 0; //posición inicial

        //Itera sobre cada secuencia de movimientos
        for (String secuencia : movimientos) {
            //Itera sobre cada movimiento dentro de la secuencia
            for (char movimiento : secuencia.toCharArray()) {
                switch (movimiento) {
                    case 'U':
                        if (fila > 0)
                            fila--; //move hacia arriba
                        break;
                    case 'D':
                        if (fila < teclado.length - 1)
                            fila++; //move hacia abajo
                        break;
                    case 'L':
                        if (columna > 0)
                            columna--; //move hacia la izquierda
                        break;
                    case 'R':
                        if (columna < teclado[0].length - 1)
                            columna++; //move hacia la derecha
                        break;
                }
            }
            //Añade el carácter correspondiente a la posición final a la clave
            codigo.append(teclado[fila][columna]);
        }
        return codigo.toString(); //Clave generada
    }
}
