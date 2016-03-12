package co.com.jaro.decisionMaking.controller;

import javax.swing.JInternalFrame;

/**
 * @author Jaime Andres Rojas Ocampo
 *
 */
public class InternalFrameController {

	public static void agregarBotonesVentanas(JInternalFrame internalFrame) {
		internalFrame.setMaximizable(true);
		internalFrame.setIconifiable(true);
		internalFrame.setClosable(true);
		
		internalFrame.setVisible(true);
	}
	
}
