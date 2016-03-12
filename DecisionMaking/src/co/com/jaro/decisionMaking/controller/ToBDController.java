package co.com.jaro.decisionMaking.controller;

import java.io.File;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * @author Jaime Andres Rojas Ocampo
 *
 */
public class ToBDController {

	public static boolean leerArchivoExcel(String archivoOringen, String archivoDestino) {

        ArrayList<String> row = new ArrayList<String>();
        try {
            Workbook archivoExcel;
            if(System.getProperty("os.name").trim().toLowerCase().equals("linux"))
                archivoExcel = Workbook.getWorkbook(new File(archivoOringen));
            else
                archivoExcel = Workbook.getWorkbook(new File(archivoOringen.replace("\\", "\\\\")));
            // Ciclo para recorrer las hojas existentes
            for (int sheetNo = 0; sheetNo < archivoExcel.getNumberOfSheets(); sheetNo++) {
                Sheet hoja = archivoExcel.getSheet(sheetNo);
                int numColumnas = hoja.getColumns();
                int numFilas = hoja.getRows();
                String data = "";
                // Ciclo para recorrer en CONTENIDO de las hojas existentes (FILAS)
                for (int fila = 0; fila < numFilas; fila++) {
                    // Ciclo para recorrer en CONTENIDO de las hojas existentes (COLUMNAS)
                    boolean rowContent = false;
                    for (int columna = 0; columna < numColumnas; columna++) {
                        data = hoja.getCell(columna, fila).getContents();
                        if (!data.trim().toLowerCase().equals("")) {
                            row.add(data);
                            rowContent = true;
                        }
                    }
                    if (!rowContent) {
                        row.add(null);
                    }
                }
            }
            createCalculationSheetTwo(row, archivoDestino);
        } catch (Exception eGeneral) {
            return false;
        }
        return true;
    }

    public static void createCalculationSheetTwo(ArrayList<String> datos, String archivoDestino) {
        try {
            WritableWorkbook workbook;
            if(System.getProperty("os.name").trim().toLowerCase().equals("linux"))
                workbook = Workbook.createWorkbook(new File(archivoDestino + ".xls"));
            else
                workbook = Workbook.createWorkbook(new File(archivoDestino.replace("\\", "\\\\") + ".xls"));
            WritableSheet sheet = workbook.createSheet("Data base", 0);

            int countRow = -1;
            int countColum = 0;
            for (String string : datos) {
                if (string == null || string.trim().toLowerCase().equals("null")) {
                    countRow = -1;
                    countColum++;
                    continue;
                }
                countRow++;

                Label label = new Label(countColum, countRow, string);
                sheet.addCell(label);

            }

            workbook.write();
            workbook.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
