package co.com.jaro.decisionMaking.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.border.LineBorder;

import co.com.jaro.decisionMaking.controller.InternalFrameController;

import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

/**
 * @author Jaime Andres Rojas Ocampo
 *
 */
public class Home extends JFrame {

	private static final long serialVersionUID = -7082751080300493031L;
	
	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private JMenuBar menuBar;
	private JMenu mnArchivo;
	private JMenuItem mntmDesordenABaseDeDatos;
	private JMenu mnTablas;
	private JMenuItem mntmSimple;
	private JMenuItem mntmAgrupada;
	
	public Home() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/co/com/jaro/decisionMaking/resources/img/DecisionMaking_icon.png")));
		setTitle("DecisionMaking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 497);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnArchivo = new JMenu("Archivo");
		mnArchivo.setIcon(new ImageIcon(Home.class.getResource("/co/com/jaro/decisionMaking/resources/img/file.png")));
		menuBar.add(mnArchivo);
		
		mntmDesordenABaseDeDatos = new JMenuItem("Desorden a base de datos");
		mntmDesordenABaseDeDatos.setIcon(new ImageIcon(Home.class.getResource("/co/com/jaro/decisionMaking/resources/img/ToDB.png")));
		mnArchivo.add(mntmDesordenABaseDeDatos);
		
		mnTablas = new JMenu("Tablas");
		mnTablas.setIcon(new ImageIcon(Home.class.getResource("/co/com/jaro/decisionMaking/resources/img/table.png")));
		menuBar.add(mnTablas);
		
		mntmSimple = new JMenuItem("Simple");
		mntmSimple.setIcon(new ImageIcon(Home.class.getResource("/co/com/jaro/decisionMaking/resources/img/table_simple.png")));
		mnTablas.add(mntmSimple);
		
		mntmAgrupada = new JMenuItem("Agrupada");
		
		mntmAgrupada.setIcon(new ImageIcon(Home.class.getResource("/co/com/jaro/decisionMaking/resources/img/table_group.png")));
		mnTablas.add(mntmAgrupada);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		desktopPane.setBackground(SystemColor.desktop);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
		// Eventos - click
		clicks();
	}
	
	public void clicks() {
		mntmDesordenABaseDeDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JIToDB jiToDB = new JIToDB();
				desktopPane.add(jiToDB);
				InternalFrameController.agregarBotonesVentanas(jiToDB);
			}
		});
		
		mntmAgrupada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JITablaFrecuenciaAgrupada jiTablaFrecuenciaAgrupada = new JITablaFrecuenciaAgrupada();
				desktopPane.add(jiTablaFrecuenciaAgrupada);
				InternalFrameController.agregarBotonesVentanas(jiTablaFrecuenciaAgrupada);
			}
		});
		
		mntmSimple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JITablaFrecuenciaSimple jiTablaFrecuenciaSimple = new JITablaFrecuenciaSimple();
				desktopPane.add(jiTablaFrecuenciaSimple);
				InternalFrameController.agregarBotonesVentanas(jiTablaFrecuenciaSimple);
			}
		});
	}
}
