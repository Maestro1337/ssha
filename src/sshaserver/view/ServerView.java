package sshaserver.view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class ServerView extends JFrame {
	
	private boolean isRunning = false;
	private int wWidth = 640;
	private int wHeight = 480;
	
	private JScrollPane activityPanel;
	private JTextArea activityField;
	private JLabel activityLabel;
	private JScrollPane clientsPanel;
	private JTextArea connectedClients;
	private JLabel clientsLabel;
	private JButton startServer;
	
	private int activityFieldY = 250;
	private int connectedClientsY = 100;
	
	public ServerView() {
		setSize(wWidth,wHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		// Create Activity-textarea
		activityField = new JTextArea();
		activityPanel = new JScrollPane(activityField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		activityLabel = new JLabel("Activity:");
		connectedClients = new JTextArea();
		clientsPanel = new JScrollPane(connectedClients, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		clientsLabel = new JLabel("Connected clients:");
		startServer = new JButton("Start server");
		
		// Create Activity-label
		activityPanel.setLocation(5, activityFieldY);
		activityPanel.setSize(wWidth-10, wHeight-activityFieldY-27);
		activityField.setEditable(false);
		add(activityPanel);
		activityLabel.setLocation(5, activityFieldY-20);
		activityLabel.setSize(60, 15);
		add(activityLabel);
		
		// Create ConnectedClients-textarea
		clientsPanel.setLocation(5, connectedClientsY);
		clientsPanel.setSize(wWidth-10, wHeight-connectedClientsY-(int)activityPanel.getSize().getHeight()-55);
		connectedClients.setEditable(false);
		add(clientsPanel);
		clientsLabel.setLocation(5, connectedClientsY-20);
		clientsLabel.setSize(117, 15);
		add(clientsLabel);
		
		// Create ConnectedClients-label
		startServer.setLocation(5, 5);
		startServer.setSize(wWidth-10, wHeight-(int)activityPanel.getSize().getHeight()-(int)clientsPanel.getSize().getHeight()-85);
		startServer.setFont(new Font("Italic", Font.BOLD, 30));
		add(startServer);

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
		connectedClients.append(str + (char)9 + "1" + (char)10);
	}
	
	public void addActionListener(ActionListener al) {
		startServer.addActionListener(al);
	}
	
	public void changeButtonText() {
		if(startServer.getText().equals("Start server")) {
			startServer.setText("Stop server");
			isRunning = true;
		} else {
			startServer.setText("Start server");
			isRunning = false;
		}
	}
	
	public boolean isRunning() {
		return isRunning;
	}

}
