import java.io.*;

public class VerificadorBinario {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java VerificadorBinario <nombreArchivo> <L#>");
            return;
        }

        String nombreArchivo = args[0];
        String lenguaje = args[1];

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    verificarCadena(linea, lenguaje);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void verificarCadena(String cadena, String lenguaje) {
        boolean aceptada = false;

        switch (lenguaje) {
            case "L7": // Números binarios múltiplos de 8
                aceptada = esMultiploDeOcho(cadena);
                break;
            case "L8": // Números binarios mayores a 5
                aceptada = esMayorQueCinco(cadena);
                break;
            case "L9": // Valores binarios con un número de unos múltiplo de 5
                aceptada = tieneUnosMultiploDeCinco(cadena);
                break;
            case "L10": // Números binarios con al menos 2 ceros entre cada par de unos
                aceptada = alMenosDosCerosEntreUnos(cadena);
                break;
            default:
                System.out.println("Lenguaje no reconocido.");
                return;
        }

        if (aceptada) {
            System.out.println("Cadena aceptada: " + cadena);
        } else {
            System.out.println("Cadena rechazada: " + cadena);
        }
    }

    public static boolean esMultiploDeOcho(String cadena) {
        return cadena.matches("^(0+|[01]*000)$");
    }
    public static boolean esMayorQueCinco(String cadena) {
        return cadena.matches("^(0*(1[01]{3,}|1[01]*1[01]*1[01]*|110))$");
    }
    public static boolean tieneUnosMultiploDeCinco(String cadena) {
        return cadena.matches("^(0*|(0*10*10*10*10*10*)*)$");
    }
    public static boolean alMenosDosCerosEntreUnos(String cadena) {
        return cadena.matches("^0*$|^0*1(00+1)*0*$");
    }
}
