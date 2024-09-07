import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

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
            case "L7":
                expresionRegular = "^(0+|[01]*000)$";
                break;
            case "L8":
                expresionRegular = "^(0*(1[01]{3,}|1[01]*1[01]*1[01]*|110))$";
                break;
            case "L9":
                expresionRegular = "^(0*|(0*10*10*10*10*10*)*)$";
                break;
            case "L10":
                expresionRegular = "^0*$|^0*1(00+1)*0*$";
                break;
            default:
                System.out.println("Lenguaje no reconocido. Usa L1, L2, L3, L4, L5, L6, L7, L8, L9 o L10.");
                return;
        }

        // Leer el archivo y verificar cada línea
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                boolean coincide = linea.matches(expresionRegular);
                System.out.println(linea + " : " + (coincide ? "Aceptado" : "Rechazado"));
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
