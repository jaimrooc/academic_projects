package co.com.jaro.decisionMaking.model;

import java.util.ArrayList;

/**
 * @author Jaime Andres Rojas Ocampo
 *
 */
public class Constantes {

	public static ArrayList<String> columnas(String nombreDatos) {
		ArrayList<String> nombreColumnas = new ArrayList<String>();
		
		nombreColumnas.add(nombreDatos);
		nombreColumnas.add("fi");
		nombreColumnas.add("Fi");
		nombreColumnas.add("hi");
		nombreColumnas.add("Hi");
		nombreColumnas.add("Varianza");
		
		return nombreColumnas;
	}
	
	public static ArrayList<String> columnasAgrupada(String nombreDatos) {
		ArrayList<String> nombreColumnas = new ArrayList<String>();
		
		nombreColumnas.add(nombreDatos);
		nombreColumnas.add("fi");
		nombreColumnas.add("Fi");
		nombreColumnas.add("hi");
		nombreColumnas.add("Hi");
		nombreColumnas.add("Xi");
		
		return nombreColumnas;
	}
}
