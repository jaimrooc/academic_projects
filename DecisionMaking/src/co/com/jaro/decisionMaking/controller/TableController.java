package co.com.jaro.decisionMaking.controller;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import co.com.jaro.decisionMaking.model.NodoAgrupado;
import co.com.jaro.decisionMaking.model.NodoSimple;

/**
 * @author Jaime Andres Rojas Ocampo
 *
 */
public class TableController {
	
	public static ArrayList<?> mostrarDatos(JTable table, DefaultTableModel modelo, ArrayList<String> nombreColumnas, ArrayList<String> valores, String tipo) {
		
		modelo.setColumnCount(0);
		modelo.setRowCount(0);
				
		for (String columna : nombreColumnas) {
			modelo.addColumn(columna.toString());
		}
		
		ArrayList<NodoSimple> nodoSimples = new ArrayList<NodoSimple>();
		ArrayList<NodoAgrupado> nodoAgrupados = new ArrayList<NodoAgrupado>();
		
		if(tipo.equals("simple")) {
			String datos[] = new String[nombreColumnas.size()];
			
			nodoSimples = LogicaFrecuencias.tablaFrecSimple(valores, modelo);
			for (NodoSimple nodo : nodoSimples) {
				datos[0] = nodo.getValor();
				datos[1] = "" + nodo.getFi();
				datos[2] = "" + nodo.getFfi();
				datos[3] = "" + nodo.getHi();
				datos[4] = "" + nodo.getHhi();
				datos[5] = "" + nodo.getVarianza();
				
				modelo.addRow(datos);
			}
			
		} else if(tipo.equals("agrupada")) {
			String datos[] = new String[nombreColumnas.size()];
			
			nodoAgrupados = LogicaFrecuencias.tablaFrecAgrupada(valores, modelo);
			for (NodoAgrupado nodo : nodoAgrupados) {
				datos[0] = "De " + nodo.getValorIni() + " a menos " + nodo.getValorFin();
				datos[1] = "" + nodo.getFi();
				datos[2] = "" + nodo.getFfi();
				datos[3] = "" + nodo.getHi();
				datos[4] = "" + nodo.getHhi();
				datos[5] = "" + nodo.getXi();
				
				modelo.addRow(datos);
			}
		}
		
		if(!nodoSimples.isEmpty())
			return nodoSimples;
		else
			return nodoAgrupados;
	}
}
