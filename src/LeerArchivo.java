import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeerArchivo {

    public int contarAlumnos(String nombreArchivo) {

        int totalAlumnos = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            br.readLine();

            while (br.readLine() != null) {
                totalAlumnos++;
            }

        } catch (IOException e) {
            System.out.println("No se ha leido el archivo");
        }

        return totalAlumnos;
    }
}
