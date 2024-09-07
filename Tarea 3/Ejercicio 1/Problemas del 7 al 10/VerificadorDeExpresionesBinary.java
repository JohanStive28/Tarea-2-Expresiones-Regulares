import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class VerificadorDeExpresionesBinary {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java VerificadorDeExpresionesBinary <archivo> <L#> (7<=#<=10)");
            System.out.println("Ejemplo: java VerificadorDeExpresionesBinary 'ejemplo 7 (aceptar).txt' L7");
            return;
        }

        String nombreArchivo = args[0];
        String lenguaje = args[1];

        String expresionRegular = "";

        switch (lenguaje) {
            case "L7":
                expresionRegular = "^(0|00|.*000)$";
                break;
            case "L8":
                expresionRegular = "^(1(1[01]|0[1-9])|[01]{4,})$";
                break;
            case "L9":
                expresionRegular = "^(0*1{5}0*)*$";
                break;
            case "L10":
                expresionRegular = "1(00+1)*";
                break;
            default:
                System.out.println("Lenguaje no reconocido. Usa L7, L8, L9 o L10.");
                return;
        }

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