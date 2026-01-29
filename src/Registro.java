import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Registro {

    public void registrar() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String contraseña = scanner.nextLine();

        String contraseñaEncriptada = Encriptamiento.encriptar(contraseña);

        try (FileWriter fw = new FileWriter("usuarios.csv", true)) {

            fw.write(usuario + "," + contraseñaEncriptada + "\n");
            System.out.println("Usuario registrado correctamente");

        } catch (IOException e) {
            System.out.println("Error al registrar usuario");
        }
    }
}