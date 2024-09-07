import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class VerificadorDeExpresiones {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java VerificadorDeExpresiones <archivo> <L#>");
            System.out.println("Ejemplo: java VerificadorDeExpresiones ejemplo1_aceptar.txt L1");
            return;
        }

        String nombreArchivo = args[0];
        String lenguaje = args[1];

        // Definir las expresiones regulares para cada lenguaje
        String expresionRegular = "";

        switch (lenguaje) {
            case "L1":
                expresionRegular = "^(?!.*(ab.*ab)).*ab.*$";
                break;
            case "L2":
                expresionRegular = "^(.*ab.*){2,}$";
                break;
            case "L3":
                expresionRegular = "^(?!.*ab.*ab.*ab).*ab.*ab.*$";
                break;
            case "L4":
                expresionRegular = ".*(a|b|c|d)*abb$";
                break;
            case "L5":
                expresionRegular = "^(?!abb).*";
                break;
            case "L6":
                expresionRegular = "^(?!.*abb$).*";
                break;
            default:
                System.out.println("Lenguaje no reconocido. Usa L1, L2, L3, L4, L5 o L6.");
                return;
        }

        // Leer el archivo y verificar cada línea
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                boolean coincide = Pattern.matches(expresionRegular, linea);
                System.out.println(linea + " : " + (coincide ? "Aceptado" : "Rechazado"));
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
