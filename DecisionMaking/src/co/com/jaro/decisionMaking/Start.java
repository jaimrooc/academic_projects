package co.com.jaro.decisionMaking;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import co.com.jaro.decisionMaking.view.Home;

public class Start {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		Home home = new Home();
		home.setLocationRelativeTo(null);
		home.show();
	}

}
