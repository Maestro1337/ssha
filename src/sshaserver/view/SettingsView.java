package sshaserver.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import sshaserver.model.MainHub;

public class SettingsView extends JPanel {
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 4521474886059049258L;
	
	private int wWidth = 640;
	private int wHeight = 480;
	private int settingsWidth = 200;
	
	TitledBorder settingsTitle;
	Border blackline;
	
	private JLabel privIPLabel;
	private JLabel pubIPLabel;
	private JLabel portLabel;
	private JTextField openPort;
	private JButton changePort;
	private JButton defaultPort;
	private String info = "<html><body> Note! <br> Port must be an integer withing the range 1025-9999.<br>Server will have to be restarted for changes to take effect.<br>New port might have to be opened on tour router for Clients outside local network to access Server.</body></html>";
	private JLabel infoText;
	
	public SettingsView() {
		setLayout(null);
		setSize(settingsWidth-4, wHeight-25);
		setLocation(wWidth-settingsWidth+2, 0);
		blackline = BorderFactory.createLineBorder(Color.black);
		settingsTitle = BorderFactory.createTitledBorder(blackline, "Settings");
		setBorder(settingsTitle);
				
		// Create Private IP-label
		privIPLabel = new JLabel("Private IP:");
		privIPLabel.setLocation(7, 25);
		privIPLabel.setSize(173, 15);
		add(privIPLabel);
				
		// Create Public IP-label
		pubIPLabel = new JLabel("Public IP:");
		pubIPLabel.setLocation(7, 45);
		pubIPLabel.setSize(173, 15);
		add(pubIPLabel);
				
		// Create Port-label and textfield
		portLabel = new JLabel("Port:");
		portLabel.setLocation(7,75);
		portLabel.setSize(100, 15);
		add(portLabel);
		openPort = new JTextField("" + MainHub.port);
		openPort.setLocation(65, 70);
		openPort.setSize(47, 25);
		openPort.setColumns(4);
		add(openPort);
				
		// Create change-button
		changePort = new JButton("Change");
		changePort.setLocation(2, 100);
		changePort.setSize(90, 25);
		changePort.setActionCommand("change");
		add(changePort);
				
		// Create default-button
		defaultPort = new JButton("Default");
		defaultPort.setLocation(settingsWidth-90-6, 100);
		defaultPort.setSize(90, 25);
		defaultPort.setActionCommand("default");
		add(defaultPort);
				
		// Create info-text
		infoText = new JLabel(info);
		infoText.setLocation(6, 100);
		infoText.setSize(settingsWidth-15, 160);
		infoText.setFont(new Font("Italic", Font.ITALIC, 10));
		add(infoText);
	}
	
	public String getPortField() {
		return openPort.getText();
	}
	
	public void setPortField(String port) {
		openPort.setText(port);
	}
	
	public void clearPortField() {
		openPort.setText("");
	}
	
	public void setPrivIPLabel(String ip) {
		privIPLabel.setText("Private IP: " + ip);
	}
	
	public void setPubIPLabel(String ip) {
		pubIPLabel.setText("Public IP:  " + ip);
	}
	
	public void addActionListener(ActionListener al) {
		changePort.addActionListener(al);
		defaultPort.addActionListener(al);
	}
	
}
