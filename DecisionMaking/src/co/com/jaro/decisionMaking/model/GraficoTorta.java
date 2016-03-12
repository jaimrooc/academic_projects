package co.com.jaro.decisionMaking.model;

import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 * @author Jaime Andres Rojas Ocampo
 *
 */
public class GraficoTorta {

	public static void generarTorta(ArrayList<NodoSimple> datos) {
		// Fuente de Datos
		DefaultPieDataset data = new DefaultPieDataset();
		
		for (NodoSimple nodoSimple : datos) {
			data.setValue("" + nodoSimple.getValor(), nodoSimple.getFi());
		}

		// Creando el Grafico
		JFreeChart chart = ChartFactory.createPieChart(
				"Gráfico de torta", data, true, true,
				false);

		// Mostrar Grafico
		ChartFrame frame = new ChartFrame("Gráfico de torta", chart);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void generarTortaAgrupado(ArrayList<NodoAgrupado> datos) {
		// Fuente de Datos
		DefaultPieDataset data = new DefaultPieDataset();
		
		for (NodoAgrupado nodoAgrupado : datos) {
			data.setValue("De " + nodoAgrupado.getValorIni() + " a menos " + nodoAgrupado.getValorFin(), nodoAgrupado.getFi());
		}
		
		// Creando el Grafico
		JFreeChart chart = ChartFactory.createPieChart(
				"Gráfico de torta", data, true, true,
				false);
		
		// Mostrar Grafico
		ChartFrame frame = new ChartFrame("Gráfico de torta", chart);
		frame.pack();
		frame.setVisible(true);
	}
}
