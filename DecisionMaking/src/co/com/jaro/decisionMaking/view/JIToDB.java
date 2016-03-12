package co.com.jaro.decisionMaking.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import co.com.jaro.decisionMaking.controller.ToBDController;

import java.awt.Font;

/**
 * @author Jaime Andres Rojas Ocampo
 *
 */
public class JIToDB extends JInternalFrame implements ActionListener{

	private static final long serialVersionUID = 5740084450504808138L;
	private JTextField txtArchivoDesorganizado;
	private JTextField txtArchivoSalida;
	private JButton btnBuscarArchivo;
	private JButton btnBuscarArchivo_1;
	private JButton btnGenerarArchivo;
	private JLabel lblMensaje;
	

	public JIToDB() {
		setFrameIcon(new ImageIcon(JIToDB.class.getResource("/co/com/jaro/decisionMaking/resources/img/ToDB.png")));
		setTitle("Desorden a base de datos");
		setBounds(100, 100, 450, 293);
		
		JLabel lblArchivoDesorganizado = new JLabel("Archivo desorganizado:");
		
		txtArchivoDesorganizado = new JTextField();
		txtArchivoDesorganizado.setEditable(false);
		txtArchivoDesorganizado.setColumns(10);
		
		btnBuscarArchivo = new JButton("Buscar archivo");
		btnBuscarArchivo.setIcon(new ImageIcon(JIToDB.class.getResource("/co/com/jaro/decisionMaking/resources/img/find.png")));
		
		JLabel lblArchivoSalida = new JLabel("Archivo salida:");
		
		txtArchivoSalida = new JTextField();
		txtArchivoSalida.setEditable(false);
		txtArchivoSalida.setColumns(10);
		
		btnBuscarArchivo_1 = new JButton("Buscar archivo");
		btnBuscarArchivo_1.setIcon(new ImageIcon(JIToDB.class.getResource("/co/com/jaro/decisionMaking/resources/img/find.png")));
		
		btnGenerarArchivo = new JButton("Generar archivo");
		btnGenerarArchivo.setIcon(new ImageIcon(JIToDB.class.getResource("/co/com/jaro/decisionMaking/resources/img/Excel.png")));
		
		lblMensaje = new JLabel("");
		lblMensaje.setFont(new Font("Arial", Font.BOLD, 12));
		lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblMensaje, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtArchivoDesorganizado, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
								.addComponent(lblArchivoDesorganizado, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
								.addComponent(btnBuscarArchivo, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
								.addComponent(lblArchivoSalida, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
								.addComponent(txtArchivoSalida, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
								.addComponent(btnBuscarArchivo_1, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(155)
							.addComponent(btnGenerarArchivo)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblArchivoDesorganizado)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtArchivoDesorganizado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBuscarArchivo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblArchivoSalida)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtArchivoSalida, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBuscarArchivo_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMensaje, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnGenerarArchivo)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
		loadActionListener();
	}

    public void loadActionListener() {
        btnBuscarArchivo_1.addActionListener(this);
        btnBuscarArchivo.addActionListener(this);
        btnGenerarArchivo.addActionListener(this);
    }
	
	@Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(btnBuscarArchivo_1)) {
            JFileChooser chooser = new JFileChooser();
            int opcion = chooser.showOpenDialog(btnBuscarArchivo_1);
            if (opcion == JFileChooser.APPROVE_OPTION) {
                txtArchivoSalida.setText(chooser.getSelectedFile().getPath());
            }
        } else if(e.getSource().equals(btnBuscarArchivo)) {
            JFileChooser chooser = new JFileChooser();
            int opcion = chooser.showOpenDialog(btnBuscarArchivo);
            if (opcion == JFileChooser.APPROVE_OPTION) {
                txtArchivoDesorganizado.setText(chooser.getSelectedFile().getPath());
            }
        } else if(e.getSource().equals(btnGenerarArchivo)) {
            if(txtArchivoDesorganizado.getText().toString().trim().equals("")) {
                lblMensaje.setForeground(Color.BLUE);
                lblMensaje.setText("Debe existir un archivo de origen");
                return;
            } else if(txtArchivoSalida.getText().toString().trim().equals("")) {
                lblMensaje.setForeground(Color.BLUE);
                lblMensaje.setText("Debe existir un archivo de destino");
                return;
            } else {
                if(ToBDController.leerArchivoExcel(txtArchivoDesorganizado.getText(), txtArchivoSalida.getText())) {
                    lblMensaje.setForeground(Color.green);
                    lblMensaje.setText("Archivo creado exitosamente");
                    txtArchivoSalida.setText("");
                    txtArchivoDesorganizado.setText("");
                    return;
                } else {
                    lblMensaje.setForeground(Color.red);
                    lblMensaje.setText("Error al generar el archivo");
                    return;
                }
            }
        }
    }
}
