package co.com.jaro.decisionMaking.controller;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import co.com.jaro.decisionMaking.model.NodoAgrupado;
import co.com.jaro.decisionMaking.model.NodoSimple;

/**
 * @author Jaime Andres Rojas Ocampo
 *
 */
public class LogicaFrecuencias {

	public static ArrayList<NodoSimple> tablaFrecSimple(ArrayList<String> valores, DefaultTableModel modelo) {
		ArrayList<NodoSimple> nodoSimples = new ArrayList<NodoSimple>();
		String aux = valores.get(1);
		int contador = 0;
		int totalDatos = (valores.size() - 1);
		
		
		// Mediana
		if(totalDatos % 2 == 0) {
			float mediana = 0;
			mediana =
					(
					Float.parseFloat(valores.get(valores.size()/2))
					+
					Float.parseFloat(valores.get((valores.size()/2) + 1))
					) / 2;
			System.out.println("mediana: " + mediana);
		} else {
			int mediana = 0;
			
			mediana = Integer.parseInt(valores.get(valores.size() / 2));
			
			System.out.println("mediana: " + mediana);
		}
		
		// Promedio
		float sumador = 0;
		for (int i = 1; i < valores.size(); i++) {
			sumador = sumador + Float.parseFloat(valores.get(i));
		}
		float promedio = sumador / Float.parseFloat("" + totalDatos);
		
		// Generar el valor y fi
		for (int i = 1; i < valores.size(); i++) {
			if(valores.get(i).equals(aux)) {
				contador++;
			} else {
				NodoSimple nodo = new NodoSimple();
				nodo.setFi(contador);
				nodo.setValor(aux);
				
				nodoSimples.add(nodo);
				
				aux = valores.get(i);
				contador = 1;
			}
			
		}
		NodoSimple nodo = new NodoSimple();
		nodo.setFi(contador);
		nodo.setValor(aux);
		
		nodoSimples.add(nodo);
		
		// Generar el Fi, hi, Hi
		NodoSimple nodoAuxiliar = new NodoSimple();
		for (int i = 0; i < nodoSimples.size(); i++) {
			if(i == 0) {
				nodoSimples.get(i).setFfi(nodoSimples.get(i).getFi());
				// hi (Del primer registro)
				nodoSimples.get(i).setHi(
						Float.parseFloat("" + nodoSimples.get(i).getFi())
						/
						Float.parseFloat("" + totalDatos)
						);
				// Hi (Del primer registro)
				nodoSimples.get(i).setHhi(
						Float.parseFloat("" + nodoSimples.get(i).getFfi())
						/
						Float.parseFloat("" + totalDatos)
						);
				nodoAuxiliar = nodoSimples.get(i);
				// Varianza
				nodoSimples.get(i).setVarianza(
						(Math.pow((Float.parseFloat(nodoSimples.get(i).getValor())-promedio), 2)
								*
								Float.parseFloat("" + nodoSimples.get(i).getFi())));
			} else {
				// Fi
				nodoSimples.get(i).setFfi(
						nodoAuxiliar.getFfi() 
						+
						nodoSimples.get(i).getFi());
				
				// hi
				nodoSimples.get(i).setHi(
						Float.parseFloat("" + nodoSimples.get(i).getFi())
						/
						Float.parseFloat("" + totalDatos)
						);
				// Hi
				nodoSimples.get(i).setHhi(
						Float.parseFloat("" + nodoSimples.get(i).getFfi())
						/
						Float.parseFloat("" + totalDatos)
						);
				
				nodoAuxiliar = nodoSimples.get(i);
				
				// Varianza
				nodoSimples.get(i).setVarianza(
						(Math.pow((Float.parseFloat(nodoSimples.get(i).getValor())-promedio), 2)
								*
								Float.parseFloat("" + nodoSimples.get(i).getFi())));
			}
		}
		
		// Moda
		int modaEvaluador = nodoSimples.get(0).getFi();
		int moda = Integer.parseInt(nodoSimples.get(0).getValor());
		for (int i = 0; i < nodoSimples.size(); i++) {
			if(nodoSimples.get(i).getFi() > modaEvaluador) {
				modaEvaluador = nodoSimples.get(i).getFi();
				moda = Integer.parseInt(nodoSimples.get(i).getValor());
			}
		}
		System.out.println("Moda: " + moda);
		
		return nodoSimples;
	}
	
	
	public static ArrayList<NodoAgrupado> tablaFrecAgrupada(ArrayList<String> valores, DefaultTableModel modelo) {
		ArrayList<NodoAgrupado> nodoAgrupados = new ArrayList<NodoAgrupado>();
		int totalDatos = (valores.size() - 1);
		
		// k
		float k = 10;
		// rango
		float rango = 
				// Xmin
				Float.parseFloat("" + Integer.parseInt(valores.get(valores.size() - 1))) 
				- 
				// Xmax
				Float.parseFloat("" + Integer.parseInt(valores.get(1)));

		// amplitud
		float amplitud = rango / k;
		
		/* **************************************************** *
		 * Asignacion del valor y fi	(Inicio)				*
		 * **************************************************** */
		
		// Asignar los rangos de valores
		float valorActualIni = Float.parseFloat(valores.get(1));
		float valorActualFin = Float.parseFloat(valores.get(1)) + amplitud;
		for (int i = 1; i < valores.size(); i++) {
			NodoAgrupado nodo = new NodoAgrupado();
			if(i == 1) {
				nodo.setValorIni("" + (valorActualIni));
				nodo.setValorFin("" + (valorActualFin));
				nodo.setFi(0);
			} else {
				valorActualIni = (valorActualIni + amplitud);
				valorActualFin = (valorActualFin + amplitud);
				nodo.setValorIni("" + valorActualIni);
				nodo.setValorFin("" + valorActualFin);
				nodo.setFi(0);
			}
			nodoAgrupados.add(nodo);
		}
		
		// Asignar los valores a los rangos antes creados
		for (int i = 1; i < valores.size(); i++) {
			for (NodoAgrupado nodoAgrupado : nodoAgrupados) {
				if(Float.parseFloat(valores.get(i)) >= Float.parseFloat(nodoAgrupado.getValorIni()) 
						&&
						Float.parseFloat(valores.get(i)) < Float.parseFloat(nodoAgrupado.getValorFin())) {
					nodoAgrupado.setFi(nodoAgrupado.getFi() + 1);
				}
			}
		}

		// Eliminar los rangos sin valores
		ArrayList<NodoAgrupado> datosTabFrecAgrupada = new ArrayList<NodoAgrupado>();
		for (NodoAgrupado nodoAgrupado : nodoAgrupados) {
			if(nodoAgrupado.getFi() != 0) {
				datosTabFrecAgrupada.add(nodoAgrupado);
			}
		}
		/* **************************************************** *
		 * Asignacion de Fi, hi, Hi, Xi		(Inicio)			*
		 * **************************************************** */
		
		// Generar el Fi, hi, Hi
		NodoAgrupado nodoAuxiliar = new NodoAgrupado();
		for (int i = 0; i < datosTabFrecAgrupada.size(); i++) {
			if(i == 0) {
				datosTabFrecAgrupada.get(i).setFfi(datosTabFrecAgrupada.get(i).getFi());
				// hi (Del primer registro)
				datosTabFrecAgrupada.get(i).setHi(
						Float.parseFloat("" + datosTabFrecAgrupada.get(i).getFi())
						/
						Float.parseFloat("" + totalDatos)
						);
				// Hi (Del primer registro)
				datosTabFrecAgrupada.get(i).setHhi(
						Float.parseFloat("" + datosTabFrecAgrupada.get(i).getFfi())
						/
						Float.parseFloat("" + totalDatos)
						);
				nodoAuxiliar = datosTabFrecAgrupada.get(i);
				// Xi
				datosTabFrecAgrupada.get(i).setXi(
						(Float.parseFloat(datosTabFrecAgrupada.get(i).getValorIni())
						+
						Float.parseFloat(datosTabFrecAgrupada.get(i).getValorFin())
						) / 2);
			} else {
				// Fi
				datosTabFrecAgrupada.get(i).setFfi(
						nodoAuxiliar.getFfi() 
						+
						datosTabFrecAgrupada.get(i).getFi());
				
				// hi
				datosTabFrecAgrupada.get(i).setHi(
						Float.parseFloat("" + datosTabFrecAgrupada.get(i).getFi())
						/
						Float.parseFloat("" + totalDatos)
						);
				// Hi
				datosTabFrecAgrupada.get(i).setHhi(
						Float.parseFloat("" + datosTabFrecAgrupada.get(i).getFfi())
						/
						Float.parseFloat("" + totalDatos)
						);
				
				nodoAuxiliar = datosTabFrecAgrupada.get(i);
				
				// Xi
				datosTabFrecAgrupada.get(i).setXi(
						(Float.parseFloat(datosTabFrecAgrupada.get(i).getValorIni())
						+
						Float.parseFloat(datosTabFrecAgrupada.get(i).getValorFin())
						) / 2);
			}
		}
		return datosTabFrecAgrupada;
	}
}
