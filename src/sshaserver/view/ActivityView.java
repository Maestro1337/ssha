package sshaserver.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;

public class ActivityView extends JPanel{

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 6717916816151175849L;
	
	TitledBorder mainTitle;
	Border blackline;
	
	private int wWidth = 640;
	private int wHeight = 480;
	private int settingsWidth = 200;
	private int mainWidth = wWidth-settingsWidth;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private boolean isRunning = false;
	
	Date date;
	
	private DefaultCaret caretActivity;
	private DefaultCaret caretClients;
	private JScrollPane activityPanel;
	private JTextArea activityField;
	private JLabel activityLabel;
	private JScrollPane clientsPanel;
	private JTextArea connectedClients;
	private JLabel clientsLabel;
	private JButton startServer;
	private JButton saveLog;
	private JButton clearLog;
	
	private int activityFieldY = 230;
	private int connectedClientsY = 95;
	private int activityHeight = 190;
	private int clientsHeight = 110;
	
	public ActivityView() {
		setLayout(null);
		setSize(mainWidth, wHeight-25);
		setLocation(2, 0);
		
		blackline = BorderFactory.createLineBorder(Color.black);
		
		mainTitle = BorderFactory.createTitledBorder(blackline, "Server");
		setBorder(mainTitle);
		
		// Create Activity-textarea
		activityField = new JTextArea();
		activityPanel = new JScrollPane(activityField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		activityLabel = new JLabel("Activity:");
		connectedClients = new JTextArea();
		clientsPanel = new JScrollPane(connectedClients, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		clientsLabel = new JLabel("Connected clients:");
		startServer = new JButton("Start server");
		caretActivity = (DefaultCaret) activityField.getCaret();
		caretActivity.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
				
				
		// Create Activity-label
		activityPanel.setLocation(7, activityFieldY);
		activityPanel.setSize(mainWidth-14, activityHeight);
		activityField.setEditable(false);
		add(activityPanel);
		activityLabel.setLocation(7, activityFieldY-20);
		activityLabel.setSize(60, 15);
		add(activityLabel);
				
		// Create ConnectedClients-textarea
		clientsPanel.setLocation(7, connectedClientsY);
		clientsPanel.setSize(mainWidth-14, clientsHeight);
		connectedClients.setEditable(false);
		add(clientsPanel);
		clientsLabel.setLocation(7, connectedClientsY-20);
		clientsLabel.setSize(117, 15);
		add(clientsLabel);
		caretClients = (DefaultCaret) connectedClients.getCaret();
		caretClients.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
				
		// Create Connect-button
		startServer.setLocation(7, 18);
		startServer.setSize(mainWidth-14, 50);
		startServer.setFont(new Font("Italic", Font.BOLD, 30));
		startServer.setActionCommand("connect");
		startServer.setToolTipText("Starts the server");
		add(startServer);
			
		// Create Save log-button
		saveLog = new JButton("Save log");
		saveLog.setLocation(2, wHeight-55);
		saveLog.setSize(90, 25);
		saveLog.setActionCommand("save");
		saveLog.setToolTipText("Saves the Activity-log to the harddrive");
		add(saveLog);
				
		// Create Clear log-button
		clearLog = new JButton("Clear log");
		clearLog.setLocation(mainWidth-90-2, wHeight-55);
		clearLog.setSize(90,25);
		clearLog.setActionCommand("clear");
		clearLog.setToolTipText("Clears the Activity-log");
		add(clearLog);
	}
	
	public String getActivity() {
		return activityField.getText();
	}
	
	public String getStartButtonText() {
		return startServer.getText();
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public void addToActivityField(String str) {
		date = new Date();
		activityField.append(dateFormat.format(date) + "   " + str + (char)10);
	}
	
	public void clearActivityField() {
		activityField.setText("");
	}
	
	public void addClient(String str) {
		connectedClients.append(str + (char)10);
	}
	
	public void clearClientsField() {
		connectedClients.setText("");
	}
	
	public void addActionListener(ActionListener al) {
		startServer.addActionListener(al);
		saveLog.addActionListener(al);
		clearLog.addActionListener(al);
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
}
