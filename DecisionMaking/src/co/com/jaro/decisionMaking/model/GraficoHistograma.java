package co.com.jaro.decisionMaking.model;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author Jaime Andres Rojas Ocampo
 *
 */
public class GraficoHistograma {

	public static void generarHistograma(ArrayList<NodoSimple> datos) {
        String fi = "fi";

        // Creamos y rellenamos el modelo de datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Visitas del datos
        for (NodoSimple nodoSimple : datos) {
        	dataset.setValue(nodoSimple.getFi(), fi, "" + nodoSimple.getValor());
		}

        JFreeChart chart = ChartFactory.createBarChart3D("Histograma", "Valores", "Cantidad valores", dataset, PlotOrientation.VERTICAL, true, true, false);
        
        // Creación del panel con el gráfico
        ChartPanel panel = new ChartPanel(chart);

        JFrame ventana = new JFrame("Histograma");
        ventana.getContentPane().add(panel);
        ventana.pack();
        ventana.setVisible(true);
    }
	
	public static void generarHistogramaAgrupado(ArrayList<NodoAgrupado> datos) {
		String fi = "fi";
		
		// Creamos y rellenamos el modelo de datos
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		// Visitas del datos
		for (NodoAgrupado nodoAgrupado : datos) {
			dataset.setValue(nodoAgrupado.getFi(), fi, "De " + nodoAgrupado.getValorIni() + " a menos " + nodoAgrupado.getValorFin());
		}
		
		JFreeChart chart = ChartFactory.createBarChart3D("Histograma", "Valores", "Cantidad valores", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		// Creación del panel con el gráfico
		ChartPanel panel = new ChartPanel(chart);
		
		JFrame ventana = new JFrame("Histograma");
		ventana.getContentPane().add(panel);
		ventana.pack();
		ventana.setVisible(true);
	}
}
