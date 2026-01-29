import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class CapturaCalificacionesGUI extends JFrame {

    private JTextField[] campos;
    private String[] matriculas;

    public CapturaCalificacionesGUI() {

        setTitle("Captura de Calificaciones");
        setSize(450, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        LeerArchivo lector = new LeerArchivo();
        int totalAlumnos = lector.contarAlumnos("alumnos.csv");

        if (totalAlumnos == 0) {
            JOptionPane.showMessageDialog(this, "No hay alumnos");
            dispose();
            return;
        }

        campos = new JTextField[totalAlumnos];
        matriculas = new String[totalAlumnos];

        try (BufferedReader br = new BufferedReader(new FileReader("alumnos.csv"))) {

            br.readLine();
            String linea;
            int i = 0;

            while ((linea = br.readLine()) != null && i < totalAlumnos) {

                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(",");
                matriculas[i] = datos[0];

                JLabel lbl = new JLabel("Matrícula " + datos[0] + ":");
                lbl.setBounds(30, 30 + (i * 35), 150, 25);
                add(lbl);

                JTextField txt = new JTextField();
                txt.setBounds(190, 30 + (i * 35), 60, 25);
                add(txt);

                campos[i] = txt;
                i++;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al leer alumnos.csv");
            dispose();
            return;
        }

        JButton btnGenerar = new JButton("Generar reporte");
        btnGenerar.setBounds(120, 40 + (totalAlumnos * 35), 200, 30);
        add(btnGenerar);

        btnGenerar.addActionListener(e -> {

            int[] calificaciones = capturarCalificaciones();

            boolean condicion = false;
            for (int c : calificaciones) {
                if (c == 0) {
                    condicion = true;
                    break;
                }
            }

            PDF pdf = new PDF();

            if (!condicion) {

                try (FileWriter fw = new FileWriter("salida.csv")) {

                    fw.write("Matricula,Diseño de software,Calificación\n");

                    for (int i = 0; i < matriculas.length; i++) {
                        fw.write(matriculas[i] + ",Diseño de software," + calificaciones[i] + "\n");
                    }

                    JOptionPane.showMessageDialog(this, "CSV generado");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al generar el CSV");
                    return;
                }

                pdf.generarPDF("alumnos.csv", calificaciones, "salida.pdf");
                JOptionPane.showMessageDialog(this, "PDF generado");

            }
            else {
                pdf.generarPDF("alumnos.csv", calificaciones, "salida.pdf");
                JOptionPane.showMessageDialog(this,
                        "El CSV no ha sido generado (hay calificaciones en 0)\nPDF generado");
            }
        });

        setVisible(true);
    }

    private int[] capturarCalificaciones() {

        int[] calificaciones = new int[campos.length];

        for (int i = 0; i < campos.length; i++) {
            try {
                int cal = Integer.parseInt(campos[i].getText());

                if (cal < 0 || cal > 100) {
                    calificaciones[i] = 0;
                } else {
                    calificaciones[i] = cal;
                }

            } catch (NumberFormatException e) {
                calificaciones[i] = 0;
            }
        }

        return calificaciones;
    }
}