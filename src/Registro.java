import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Registro {

    public void registrar(String usuario, String contraseña) {
        String contraseñaEncriptada = Encriptamiento.encriptar(contraseña);

        try (FileWriter fw = new FileWriter("usuarios.csv", true)) {
            fw.write(usuario + "," + contraseñaEncriptada + "\n");
        } catch (IOException e) {
            System.out.println("Error al registrar usuario");
        }
    }
}