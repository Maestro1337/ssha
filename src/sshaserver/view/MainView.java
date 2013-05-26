package sshaserver.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import sshaserver.model.MainHub;

public class MainView extends JFrame {
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -2523267189210887244L;
	private int wWidth = 640;
	private int wHeight = 480;

	TitledBorder mainTitle;
	JPanel settingsPanel;
	TitledBorder settingsTitle;
	
	public MainView() {
		setSize(wWidth,wHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);

		// Server
		add(MainHub.getController().getActivityView());
		
		// Settings
		add(MainHub.getController().getSettingsView());
		
		setVisible(true);
	}
	
	public String showDialogBox(String type) {
		String returnStr = "";
		
		if(type.equals("change")) {
			String[] options = {"Cancel", "Restart now"};
			int n = JOptionPane.showOptionDialog(this, "Server must be restarted for changes to take effect.", "Restart Server?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if(n != -1) {
				returnStr = options[n];
			} else {
				returnStr = options[0];
			}
		} else if(type.equals("error")) {
			JOptionPane.showMessageDialog(this, "Invalid port", "Invalid port", JOptionPane.ERROR_MESSAGE);
		} else if(type.equals("emptyactivity")) {
			JOptionPane.showMessageDialog(this, "There is nothing to save.", "Activity log empty", JOptionPane.ERROR_MESSAGE);
		} else if(type.equals("clearactivity")) {
			String[] options = {"Cancel", "Clear log"};
			int n = JOptionPane.showOptionDialog(this, "Are you sure you want to clear the activity log", "Clear activity log?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if(n != -1) {
				returnStr = options[n];
			} else {
				returnStr = options[0];
			}
		}
		
		return returnStr;
	}

}
