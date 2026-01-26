import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String linea;
        LeerArchivo lector = new LeerArchivo();

        int totalAlumnos = lector.contarAlumnos("alumnos.csv");

        if (totalAlumnos == 0) {
            return;
        }

        EscribirCalificaciones ec = new EscribirCalificaciones();
        int[] calificaciones = ec.capturarCalificaciones();

        if (calificaciones == null) {
            return;
        }

        int i = 0;
        try (
                BufferedReader br = new BufferedReader(new FileReader("alumnos.csv"));
                FileWriter fw = new FileWriter("salida.csv")
        ) {

            br.readLine();
            fw.write("Matricula,Asignatura,Calificacion\n");

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");
                fw.write(datos[0] + ",Diseño de software," + calificaciones[i] + "\n");
                i++;
            }

            System.out.println("Archivo CSV generado");

        } catch (IOException e) {
            System.out.println("Error al generar archivo");
        }
    }
}
