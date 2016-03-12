package co.com.jaro.decisionMaking.controller;

import java.io.File;
import java.util.ArrayList;

import co.com.jaro.decisionMaking.model.Constantes;
import co.com.jaro.decisionMaking.model.NodoAgrupado;
import co.com.jaro.decisionMaking.model.NodoSimple;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ManejoArchivoController {

	public static ArrayList<String> leerArchivoExcel(String archivoOringen) {

        ArrayList<String> datos = new ArrayList<String>();
        try {
            Workbook archivoExcel;
            if(System.getProperty("os.name").trim().toLowerCase().equals("linux"))
                archivoExcel = Workbook.getWorkbook(new File(archivoOringen));
            else
                archivoExcel = Workbook.getWorkbook(new File(archivoOringen.replace("\\", "\\\\")));

            int columna = 0;
            
            for (int i = 0; i < archivoExcel.getNumberOfSheets(); i++) {
            	
                Sheet hoja = archivoExcel.getSheet(i);
                int numFilas = hoja.getRows();
                
                for (int fila = 0; fila < numFilas; fila++) {
                	datos.add(hoja.getCell(columna, fila).getContents());
                }
            }
            return datos;
        } catch (Exception eGeneral) {
            return null;
        }
    }
	
	public static void archivoSalidaSimple(ArrayList<NodoSimple> listaDatos, String archivoDestino, String tipoDeTabla, String nombreTabla) {
        try {
            WritableWorkbook workbook;
            if(System.getProperty("os.name").trim().toLowerCase().equals("linux"))
                workbook = Workbook.createWorkbook(new File(archivoDestino + ".xls"));
            else
                workbook = Workbook.createWorkbook(new File(archivoDestino.replace("\\", "\\\\") + ".xls"));
            WritableSheet sheet = workbook.createSheet(tipoDeTabla, 0);

            int countRow = 0;
            
            
            for (int i = 0; i < Constantes.columnas(nombreTabla).size(); i++) {
				Label label = new Label(i, countRow, Constantes.columnas(nombreTabla).get(i));
				sheet.addCell(label);
			}
            
            countRow++;
            
            for (NodoSimple dato : listaDatos) {
            	Label label;
                label = new Label(0, countRow, dato.getValor());
                sheet.addCell(label);
                label = new Label(1, countRow, "" + dato.getFi());
                sheet.addCell(label);
                label = new Label(2, countRow, "" + dato.getFfi());
                sheet.addCell(label);
                label = new Label(3, countRow, "" + dato.getHi());
                sheet.addCell(label);
                label = new Label(4, countRow, "" + dato.getHhi());
                sheet.addCell(label);
                label = new Label(5, countRow, "" + dato.getVarianza());
                sheet.addCell(label);

                countRow++;
            }

            workbook.write();
            workbook.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }
	
	public static void archivoSalidaAgrupada(ArrayList<NodoAgrupado> listaDatos, String archivoDestino, String tipoDeTabla, String nombreTabla) {
		try {
			WritableWorkbook workbook;
			if(System.getProperty("os.name").trim().toLowerCase().equals("linux"))
				workbook = Workbook.createWorkbook(new File(archivoDestino + ".xls"));
			else
				workbook = Workbook.createWorkbook(new File(archivoDestino.replace("\\", "\\\\") + ".xls"));
			WritableSheet sheet = workbook.createSheet(tipoDeTabla, 0);
			
			int countRow = 0;
			
			
			for (int i = 0; i < Constantes.columnas(nombreTabla).size(); i++) {
				Label label = new Label(i, countRow, Constantes.columnas(nombreTabla).get(i));
				sheet.addCell(label);
			}
			
			countRow++;
			
			for (NodoAgrupado dato : listaDatos) {
				Label label;
				label = new Label(0, countRow, "De " + dato.getValorIni() + " a menos " + dato.getValorFin());
				sheet.addCell(label);
				label = new Label(1, countRow, "" + dato.getFi());
				sheet.addCell(label);
				label = new Label(2, countRow, "" + dato.getFfi());
				sheet.addCell(label);
				label = new Label(3, countRow, "" + dato.getHi());
				sheet.addCell(label);
				label = new Label(4, countRow, "" + dato.getHhi());
				sheet.addCell(label);
				label = new Label(5, countRow, "" + dato.getXi());
				sheet.addCell(label);
				
				countRow++;
			}
			
			workbook.write();
			workbook.close();
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
