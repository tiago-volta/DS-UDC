package e1;
import java.util.HashSet;
import java.util.Set;


public class StringGames {
    //Método 1: bestCharacters
    public static String bestCharacters(String string1, String string2) {
        //Clase conteo para tener más ordenadas las variables
        class Conteo {
            private int Minusculas;
            private int Mayusculas;
            private int Digitos;
        }
        Conteo datos_string1 = new Conteo();
        Conteo datos_string2 = new Conteo();
        datos_string1.Minusculas = 0;
        datos_string1.Mayusculas = 0;
        datos_string1.Digitos = 0;
        datos_string2.Mayusculas = 0;
        datos_string2.Minusculas = 0;
        datos_string2.Digitos = 0;

        // Si los strings no son de la misma longitud lanzar error porque "no compiten en las mismas condiciones"
        if (string1.length() != string2.length()) {
            throw new IllegalArgumentException("Los strings deben tener la misma longitud");
        }

        // Recorremos los strings y contamos los puntos que hace cada uno
        for (int i = 0; i < string1.length(); i++) {
            char ch1 = string1.charAt(i);
            char ch2 = string2.charAt(i);

            if (Character.isLowerCase(ch1))
                datos_string1.Minusculas++;
            if (Character.isLowerCase(ch2))
                datos_string2.Minusculas++;

            if (Character.isUpperCase(ch1))
                datos_string1.Mayusculas++;
            if (Character.isUpperCase(ch2))
                datos_string2.Mayusculas++;

            if (Character.isDigit(ch1))
                datos_string1.Digitos++;
            if (Character.isDigit(ch2))
                datos_string2.Digitos++;
        }

        // Contar quién ganó en cada categoría
        int PuntosString1 = 0, PuntosString2 = 0;

        if (datos_string1.Minusculas > datos_string2.Minusculas)
            PuntosString1++;
        else if (datos_string2.Minusculas > datos_string1.Minusculas)
            PuntosString2++;

        if (datos_string1.Mayusculas > datos_string2.Mayusculas)
            PuntosString1++;
        else if (datos_string2.Mayusculas > datos_string1.Mayusculas)
            PuntosString2++;

        if (datos_string1.Digitos > datos_string2.Digitos)
            PuntosString1++;
        else if (datos_string2.Digitos > datos_string1.Digitos)
            PuntosString2++;

        // Devolver el string que gane más categorías, o el primero en caso de empate
        if (PuntosString1 > PuntosString2) {
            return string1;
        } else if (PuntosString2 > PuntosString1) {
            return string2;
        } else {
            return string1;
        }
    }

    // Método 2: crossingWords
    public static int crossingWords(String string1, String string2) {
        int contador = 0;

        // Comparar cada carácter del primer string con cada carácter del segundo string
        for (int i = 0; i < string1.length(); i++) {
            for (int j = 0; j < string2.length(); j++) {
                if (string1.charAt(i) == string2.charAt(j)) {
                    contador++;
                }
            }
        }

        // Devolver la cantidad de letras compartidas
        return contador;
    }

    // Método 3: wackyAlphabet
    public static String wackyAlphabet(String string1, String alphabetOrder) {
        StringBuilder result = new StringBuilder();

        if (alphabetOrder.length() != 26) {
            throw new IllegalArgumentException("El alfabeto debe tener exactamente 26 letras");
        }


        // Conjunto para almacenar las letras del alfabeto de forma única y así verificar duplicados
        Set<Character> seenLetters = new HashSet<>();
        // Comprobar que el alfabeto no contiene dígitos ni caracteres repetidos
        for (int i = 0; i < alphabetOrder.length(); i++) {
            char ch = alphabetOrder.charAt(i);

            if (!Character.isLetter(ch)) {
                throw new IllegalArgumentException("El alfabeto no solo contiene letras");
            }

            // Verificar si hay caracteres duplicados
            if (!seenLetters.add(Character.toLowerCase(ch))) {          //Si add devuelve false eso es que ya estaba en el conjunto, es decir, en nuestro hashset
                throw new IllegalArgumentException("El alfabeto contiene caracteres duplicados");
            }
        }


        // Para cada carácter en el nuevo alfabeto, recorrer el primer string y ordenarlo
        for (int i = 0; i < alphabetOrder.length(); i++) {
            char alphabetChar = alphabetOrder.charAt(i);
            for (int j = 0; j < string1.length(); j++) {
                if (string1.charAt(j) == alphabetChar) {
                    result.append(string1.charAt(j));
                }
            }
        }

        // Devolver el primer string reordenado según el alfabeto
        return result.toString();
    }
}
