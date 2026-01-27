import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Registro registro = new Registro();
        Login login = new Login();

        int opcion = 0;

        while (opcion != 3) {
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir del menu");
            System.out.print("Escriba su opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                registro.registrar();
            } else if (opcion == 2) {
                System.out.print("Usuario: ");
                String usuario = scanner.nextLine();

                System.out.print("Contraseña: ");
                String contraseña = scanner.nextLine();

                if (!login.validar(usuario, contraseña)) {
                    System.out.println("Acceso denegado");
                    return;
                }
                break;
            } else if (opcion == 3) {
                return;
            }
        }

        String linea;
        LeerArchivo lector = new LeerArchivo();

        int totalAlumnos = lector.contarAlumnos("alumnos.csv");

        if (totalAlumnos == 0) {
            System.out.println("No hay alumnos");
            return;
        }

        EscribirCalificaciones ec = new EscribirCalificaciones();
        int[] calificaciones = ec.capturarCalificaciones();

        if (calificaciones == null) {
            System.out.println("Proceso cancelado");
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
