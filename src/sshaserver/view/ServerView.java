package sshaserver.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import sshaserver.controller.constants;

public class ServerView extends JFrame {
	
	private boolean isRunning = false;
	private int wWidth = 640;
	private int wHeight = 480;
	private int settingsWidth = 200;
	private int mainWidth = wWidth-settingsWidth;
	
	JPanel mainPanel;
	TitledBorder mainTitle;
	JPanel settingsPanel;
	TitledBorder settingsTitle;
	Border blackline;
	
	private JScrollPane activityPanel;
	private JTextArea activityField;
	private JLabel activityLabel;
	private JScrollPane clientsPanel;
	private JTextArea connectedClients;
	private JLabel clientsLabel;
	private JButton startServer;
	private JButton saveLog;
	private JButton clearLog;
	
	private JLabel privIPLabel;
	private JLabel pubIPLabel;
	private JLabel portLabel;
	private JTextField openPort;
	private JButton changePort;
	private JButton defaultPort;
	private String info = "<html><body> Note! <br> Port must be an integer withing the range 1025-9999.<br>Server will have to be restarted for changes to take effect.<br>New port might have to be opened on tour router for Clients outside local network to access Server.</body></html>";
	private JLabel infoText;
	
	private int activityFieldY = 230;
	private int connectedClientsY = 95;
	private int activityHeight = 190;
	private int clientsHeight = 110;
	
	public ServerView() {
		setSize(wWidth,wHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		
		blackline = BorderFactory.createLineBorder(Color.black);
		
		// Server
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setSize(mainWidth, wHeight-25);
		mainPanel.setLocation(2, 0);
		mainTitle = BorderFactory.createTitledBorder(blackline, "Server");
		mainPanel.setBorder(mainTitle);
		add(mainPanel);
		
		// Create Activity-textarea
		activityField = new JTextArea();
		activityPanel = new JScrollPane(activityField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		activityLabel = new JLabel("Activity:");
		connectedClients = new JTextArea();
		clientsPanel = new JScrollPane(connectedClients, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		clientsLabel = new JLabel("Connected clients:");
		startServer = new JButton("Start server");
		
		// Create Activity-label
		activityPanel.setLocation(7, activityFieldY);
		activityPanel.setSize(mainWidth-14, activityHeight);
		activityField.setEditable(false);
		mainPanel.add(activityPanel);
		activityLabel.setLocation(7, activityFieldY-20);
		activityLabel.setSize(60, 15);
		mainPanel.add(activityLabel);
		
		// Create ConnectedClients-textarea
		clientsPanel.setLocation(7, connectedClientsY);
		clientsPanel.setSize(mainWidth-14, clientsHeight);
		connectedClients.setEditable(false);
		mainPanel.add(clientsPanel);
		clientsLabel.setLocation(7, connectedClientsY-20);
		clientsLabel.setSize(117, 15);
		mainPanel.add(clientsLabel);
		
		// Create Connect-button
		startServer.setLocation(7, 18);
		startServer.setSize(mainWidth-14, 50);
		startServer.setFont(new Font("Italic", Font.BOLD, 30));
		startServer.setActionCommand("connect");
		startServer.setToolTipText("Starts the server");
		mainPanel.add(startServer);
		
		// Create Save log-button
		saveLog = new JButton("Save log");
		saveLog.setLocation(2, wHeight-55);
		saveLog.setSize(90, 25);
		saveLog.setActionCommand("save");
		saveLog.setToolTipText("Saves the Activity-log to the harddrive");
		mainPanel.add(saveLog);
		
		// Create Clear log-button
		clearLog = new JButton("Clear log");
		clearLog.setLocation(mainWidth-90-2, wHeight-55);
		clearLog.setSize(90,25);
		clearLog.setActionCommand("clear");
		clearLog.setToolTipText("Clears the Activity-log");
		mainPanel.add(clearLog);
		
		
		// Settings
		
		// Create Settings-panel
		settingsPanel = new JPanel();
		settingsPanel.setLayout(null);
		settingsPanel.setSize(settingsWidth-4, wHeight-25);
		settingsPanel.setLocation(wWidth-settingsWidth+2, 0);
		settingsTitle = BorderFactory.createTitledBorder(blackline, "Settings");
		settingsPanel.setBorder(settingsTitle);
		add(settingsPanel);
		
		// Create Private IP-label
		privIPLabel = new JLabel("Private IP:");
		privIPLabel.setLocation(7, 25);
		privIPLabel.setSize(173, 15);
		settingsPanel.add(privIPLabel);
		
		// Create Public IP-label
		pubIPLabel = new JLabel("Public IP:");
		pubIPLabel.setLocation(7, 45);
		pubIPLabel.setSize(173, 15);
		settingsPanel.add(pubIPLabel);
		
		// Create Port-label and textfield
		portLabel = new JLabel("Port:");
		portLabel.setLocation(7,75);
		portLabel.setSize(100, 15);
		settingsPanel.add(portLabel);
		openPort = new JTextField("" + constants.port);
		openPort.setLocation(65, 70);
		openPort.setSize(47, 25);
		openPort.setColumns(4);
		settingsPanel.add(openPort);
		
		// Create change-button
		changePort = new JButton("Change");
		changePort.setLocation(2, 100);
		changePort.setSize(90, 25);
		changePort.setActionCommand("change");
		settingsPanel.add(changePort);
		
		// Create default-button
		defaultPort = new JButton("Default");
		defaultPort.setLocation(settingsWidth-90-6, 100);
		defaultPort.setSize(90, 25);
		defaultPort.setActionCommand("default");
		settingsPanel.add(defaultPort);
		
		// Create info-text
		infoText = new JLabel(info);
		infoText.setLocation(6, 100);
		infoText.setSize(settingsWidth-15, 160);
		infoText.setFont(new Font("Italic", Font.ITALIC, 10));
		settingsPanel.add(infoText);
		
		setVisible(true);
	}
	
	public void clearActivityField() {
		activityField.setText("");
	}
	
	public void addToActivityField(String str) {
		activityField.append(str+(char)10);
	}
	
	public void clearClientsField() {
		connectedClients.setText("");
	}
	
	public void addClient(String str) {
		connectedClients.append(str + (char)10);
	}
	
	public void addActionListener(ActionListener al) {
		startServer.addActionListener(al);
		saveLog.addActionListener(al);
		clearLog.addActionListener(al);
		changePort.addActionListener(al);
		defaultPort.addActionListener(al);
	}
	
	public void changeButtonText() {
		if(startServer.getText().equals("Start server")) {
			startServer.setText("Stop server");
			startServer.setToolTipText("Stops the Server");
			isRunning = true;
		} else {
			startServer.setText("Start server");
			startServer.setToolTipText("Starts the Server");
			isRunning = false;
		}
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public void setPrivIPLabel(String ip) {
		privIPLabel.setText("Private IP: " + ip);
	}
	
	public void setPubIPLabel(String ip) {
		pubIPLabel.setText("Public IP:  " + ip);
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
		}
		
		return returnStr;
	}

}
