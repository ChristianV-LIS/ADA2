import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login {

    public boolean validar(String usuarioIngresado, String contraseñaIngresada) {

        String linea;
        String contraseñaEncriptada = Encriptamiento.encriptar(contraseñaIngresada);

        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.csv"))) {

            br.readLine();

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");
                String usuarioArchivo = datos[0];
                String contrasenaArchivo = datos[1];

                if (usuarioArchivo.equals(usuarioIngresado) && contrasenaArchivo.equals(contraseñaEncriptada)) {
                    return true;
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer usuarios");
        }

        return false;
    }
}