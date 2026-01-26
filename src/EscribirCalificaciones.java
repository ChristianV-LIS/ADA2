import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class EscribirCalificaciones {

    private LeerArchivo lector;

    public EscribirCalificaciones() {
        lector = new LeerArchivo();
    }

    public int[] capturarCalificaciones() {

        Scanner scanner = new Scanner(System.in);
        String linea;

        int totalAlumnos = lector.contarAlumnos("alumnos.csv");
        int[] calificaciones = new int[totalAlumnos];

        int i = 0;
        boolean condicion = false;

        try (BufferedReader br = new BufferedReader(new FileReader("alumnos.csv"))) {

            br.readLine();

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                System.out.print("Calificación para matrícula " + datos[0] + " (1-100): ");
                int cal = scanner.nextInt();

                if (cal < 1 || cal > 100) {
                    condicion = true;
                    break;
                }

                calificaciones[i] = cal;
                i++;
            }

        } catch (IOException e) {
            System.out.println("Error al leer archivo");
            return null;
        }

        if (condicion) {
            System.out.println("No se ha generado el archivo");
            return null;
        }

        return calificaciones;
    }
}

