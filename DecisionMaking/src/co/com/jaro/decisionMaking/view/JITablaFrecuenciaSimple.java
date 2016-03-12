package co.com.jaro.decisionMaking.view;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTable;

import co.com.jaro.decisionMaking.controller.ManejoArchivoController;
import co.com.jaro.decisionMaking.controller.TableController;
import co.com.jaro.decisionMaking.model.Constantes;
import co.com.jaro.decisionMaking.model.GraficoHistograma;
import co.com.jaro.decisionMaking.model.GraficoTorta;
import co.com.jaro.decisionMaking.model.NodoSimple;

import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.SwingConstants;

/**
 * @author Jaime Andres Rojas Ocampo
 *
 */
public class JITablaFrecuenciaSimple extends JInternalFrame {

	private static final long serialVersionUID = 3055860393756951030L;
	private JTextField txtCargarArchivo;
	private JScrollPane scrollPane;
	private JTable tabla;
	private DefaultTableModel modelo;
	private JButton btnBuscarArchivo;
	private JButton btnGenerarArchivo;
	private JLabel lblMsg;
	private JPanel pnlMsg;
	private ArrayList<NodoSimple> nodoSimples = new ArrayList<NodoSimple>();
	private ArrayList<String> tablaFrecSimple = new ArrayList<String>();
	
	
	public JITablaFrecuenciaSimple() {
		setTitle("Tabla de frecuencia simple");
		setFrameIcon(new ImageIcon(JITablaFrecuenciaSimple.class.getResource("/co/com/jaro/decisionMaking/resources/img/table_simple.png")));
setBounds(100, 100, 636, 408);
		
		JLabel lblCargarArchivo = new JLabel("Cargar archivo:");
		
		txtCargarArchivo = new JTextField();
		txtCargarArchivo.setEditable(false);
		txtCargarArchivo.setColumns(10);
		
		btnBuscarArchivo = new JButton("Buscar archivo:");
		btnBuscarArchivo.setIcon(new ImageIcon(JITablaFrecuenciaAgrupada.class.getResource("/co/com/jaro/decisionMaking/resources/img/find.png")));
		
		modelo = new  DefaultTableModel();
	  	tabla = new JTable(modelo);
		scrollPane = new JScrollPane();
		scrollPane = new JScrollPane(tabla);
		tabla.setEnabled(false);
		
		btnGenerarArchivo = new JButton("Generar archivo");
		btnGenerarArchivo.setIcon(new ImageIcon(JITablaFrecuenciaAgrupada.class.getResource("/co/com/jaro/decisionMaking/resources/img/build.png")));
		btnGenerarArchivo.setVisible(false);
		
		pnlMsg = new JPanel();
		pnlMsg.setVisible(false);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addComponent(pnlMsg, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addComponent(txtCargarArchivo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addComponent(lblCargarArchivo, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnBuscarArchivo, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnGenerarArchivo, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCargarArchivo, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtCargarArchivo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBuscarArchivo)
						.addComponent(btnGenerarArchivo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnlMsg, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		lblMsg = new JLabel("msg");
		lblMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsg.setFont(new Font("Arial", Font.BOLD, 12));
		GroupLayout gl_pnlMsg = new GroupLayout(pnlMsg);
		gl_pnlMsg.setHorizontalGroup(
			gl_pnlMsg.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlMsg.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMsg, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_pnlMsg.setVerticalGroup(
			gl_pnlMsg.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlMsg.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblMsg, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
					.addContainerGap())
		);
		pnlMsg.setLayout(gl_pnlMsg);
		getContentPane().setLayout(groupLayout);
		
		eventosClick();
	}
	
	public void eventosClick() {
		btnBuscarArchivo.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
	            int opcion = chooser.showOpenDialog(btnBuscarArchivo);
	            if (opcion == JFileChooser.APPROVE_OPTION) {
	                txtCargarArchivo.setText(chooser.getSelectedFile().getPath());
	                
	                pnlMsg.setVisible(false);
	                btnGenerarArchivo.setVisible(false);
	                
	                if(!txtCargarArchivo.getText().substring((txtCargarArchivo.getText().length() - 3), txtCargarArchivo.getText().length())
	                		.toLowerCase().equals("xls")) {
	                	pnlMsg.setVisible(true);
	                	lblMsg.setText("El archivo debe tener una extension 'XLS'");
	                	pnlMsg.setBorder(new LineBorder(Color.RED));
	                } else {
	                	try {
	                		tablaFrecSimple = ManejoArchivoController.leerArchivoExcel(txtCargarArchivo.getText());
							

							nodoSimples = (ArrayList<NodoSimple>) TableController.mostrarDatos(
						  			tabla, 
						  			modelo, 
						  			Constantes.columnas(tablaFrecSimple.get(0)),
						  			tablaFrecSimple, 
						  			"simple");
							
							pnlMsg.setVisible(true);
		                	lblMsg.setText("Puede proceder a generar el archivo");
		                	pnlMsg.setBorder(new LineBorder(Color.GREEN));
		                	btnGenerarArchivo.setVisible(true);
						} catch (Exception e2) {
							pnlMsg.setVisible(true);
							lblMsg.setText("El archivo ingresado no esta debidamente constituido");
							pnlMsg.setBorder(new LineBorder(Color.RED));
		                	btnGenerarArchivo.setVisible(false);
						}
	                }
	                
	                pnlMsg.setVisible(true);
	            }
			}
		});
		btnGenerarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCargarArchivo.getText().toString().trim().equals("")) {
					lblMsg.setText("Debe existir un archivo de origen");
					pnlMsg.setBorder(new LineBorder(Color.BLUE));
				} else {
					String rutaDestino = txtCargarArchivo.getText().toString().substring(0, txtCargarArchivo.getText().toString().length() - 4);
					rutaDestino += "_(Out)";
					 
					ManejoArchivoController.archivoSalidaSimple(nodoSimples, rutaDestino, "Tabla simple", tablaFrecSimple.get(0));
					 
					lblMsg.setText("Archivo generado exitosamente!!");
					pnlMsg.setBorder(new LineBorder(Color.GREEN));
				}
				
				GraficoTorta.generarTorta(nodoSimples);
				GraficoHistograma.generarHistograma(nodoSimples);
			}
		});
	}
}