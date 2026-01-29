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

                if (i >= calificaciones.length) {
                    break;
                }


                String[] datos = linea.split(",");

                System.out.print("Calificación para matrícula " + datos[0] + " (0-100)(0 = sin calificacion): ");
                int cal = scanner.nextInt();

                if (cal < 0 || cal > 100) {
                    calificaciones[i] = 0;  // FASE 3 → marca como S/C
                    break;
                } else {
                    calificaciones[i] = cal;
                }

                calificaciones[i] = cal;
                i++;
            }

        } catch (IOException e) {
            System.out.println("Error al leer archivo");
            return null;
        }

        return calificaciones;
    }
}