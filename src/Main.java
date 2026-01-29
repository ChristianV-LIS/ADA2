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
            System.out.println("Proceso finalizado");
            return;
        }

        PDF pdf = new PDF("salida.pdf");
        pdf.generarPDF("alumnos.csv", calificaciones);
    }
}
