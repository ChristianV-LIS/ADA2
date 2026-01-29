import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

public class PDF {

    private String archivoPDF;

    public PDF(String archivoPDF) {
        this.archivoPDF = archivoPDF;
    }

    public void generarPDF(String archivoAlumnos, int[] calificaciones) {

        String linea;
        int i = 0;

        try (
                BufferedReader br = new BufferedReader(new FileReader(archivoAlumnos));
                FileOutputStream fos = new FileOutputStream(archivoPDF)
        ) {

            Document document = new Document();
            PdfWriter.getInstance(document, fos);
            document.open();

            document.add(new Paragraph("Reporte de Calificaciones\n\n"));

            PdfPTable tabla = new PdfPTable(3);
            tabla.addCell("Matricula");
            tabla.addCell("Asignatura");
            tabla.addCell("Calificacion");

            br.readLine();

            while ((linea = br.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",");
                String calif = (calificaciones[i] == 0) ? "S/C" : String.valueOf(calificaciones[i]);

                tabla.addCell(datos[0]);
                tabla.addCell("Diseño de software");
                tabla.addCell(calif);

                i++;
            }

            document.add(tabla);
            document.close();

            System.out.println("PDF generado correctamente");

        } catch (Exception e) {
            System.out.println("Error al generar PDF");
        }
    }
}
