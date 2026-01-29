import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

public class PDF {

    public void generarPDF(String archivoAlumnos, int[] calificaciones, String archivoPDF) {

        try (
                BufferedReader br = new BufferedReader(new FileReader(archivoAlumnos))
        ) {

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(archivoPDF));
            document.open();

            document.add(new Paragraph("Reporte de Calificaciones\n\n"));

            PdfPTable tabla = new PdfPTable(3);
            tabla.addCell("Matrícula");
            tabla.addCell("Diseño de software");
            tabla.addCell("Calificación");

            br.readLine();

            String linea;
            int i = 0;

            while ((linea = br.readLine()) != null && i < calificaciones.length) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",");

                tabla.addCell(datos[0]);
                tabla.addCell("Diseño de software");

                if (calificaciones[i] == 0) {
                    tabla.addCell("S/C");
                } else {
                    tabla.addCell(String.valueOf(calificaciones[i]));
                }

                i++;
            }

            document.add(tabla);
            document.close();

            System.out.println("PDF generado");

        } catch (Exception e) {
            System.out.println("Error al generar PDF");
        }
    }
}

