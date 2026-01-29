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


        boolean condicion = false;
        for (int c : calificaciones) {
            if (c == 0) {
                condicion = true;
                break;
            }
        }

        PDF pdf = new PDF();

        if (!condicion) {

            try (
                    BufferedReader br = new BufferedReader(new FileReader("alumnos.csv"));
                    FileWriter fw = new FileWriter("salida.csv")
            ) {

                br.readLine();
                fw.write("Matricula,Diseño de software,Calificación\n");

                String linea;
                int i = 0;

                while ((linea = br.readLine()) != null) {
                    if (linea.trim().isEmpty()) continue;

                    String[] datos = linea.split(",");
                    fw.write(datos[0] + ",Diseño de software," + calificaciones[i] + "\n");
                    i++;
                }

                System.out.println("Archivo CSV generado");

                pdf.generarPDFSinCSV("alumnos.csv", calificaciones, "salida.pdf");

            } catch (IOException e) {
                System.out.println("Error al generar el CSV");
            }

        }
        else {
            pdf.generarPDFSinCSV("alumnos.csv", calificaciones, "salida.pdf"
            );

            System.out.println("El CSV no ha sido generado (hay calificaciones en 0)");
        }
    }
}
