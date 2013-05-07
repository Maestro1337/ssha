package sshaclient;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TestView extends JFrame {

	private int wWidth = 550;
	private int wHeight = 340;
	
	private JButton connectBtn;
	private JButton lobbyBtn;
	private JButton arenaBtn;
	//private JButton shopBtn;
	
	private JLabel nameLabel;
	private JLabel coordinatesLabel;
	private JLabel angleLabel;
	private JLabel modeLabel;
	private JLabel connectedLabel;
	private JLabel miscLabel;
	
	private JLabel p2NameLabel;
	private JLabel p2CoordinatesLabel;
	private JLabel p2AngleLabel;
	private JLabel p2MiscLabel;
	
	
	public TestView() {
		setSize(wWidth,wHeight);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		
		connectBtn = new JButton("Connect");
		connectBtn.setLocation(5, 5);
		connectBtn.setSize(100, 25);
		connectBtn.setFocusable(false);
		connectBtn.setActionCommand("connect");
		add(connectBtn);
		
		lobbyBtn = new JButton("Lobby");
		lobbyBtn.setLocation(195, 5);
		lobbyBtn.setSize(100, 25);
		lobbyBtn.setFocusable(false);
		lobbyBtn.setActionCommand("lobby");
		add(lobbyBtn);
		
		arenaBtn = new JButton("Arena");
		arenaBtn.setLocation(300, 5);
		arenaBtn.setSize(100, 25);
		arenaBtn.setFocusable(false);
		arenaBtn.setActionCommand("arena");
		add(arenaBtn);
		
		/*
		shopBtn = new JButton("Shop");
		shopBtn.setLocation(405, 5);
		shopBtn.setSize(100, 25);
		shopBtn.setFocusable(false);
		shopBtn.setActionCommand("shop");
		add(shopBtn);
		*/
		
		connectedLabel = new JLabel("Connected: false");
		connectedLabel.setLocation(10, 50);
		connectedLabel.setSize(130, 15);
		add(connectedLabel);
		
		modeLabel = new JLabel("Mode: lobby");
		modeLabel.setLocation(150, 50);
		modeLabel.setSize(110, 15);
		add(modeLabel);
		
		nameLabel = new JLabel("Name: Sebbe");
		nameLabel.setLocation(10, 80);
		nameLabel.setSize(160,15);
		add(nameLabel);
		
		coordinatesLabel = new JLabel("X,Y: 0,0");
		coordinatesLabel.setLocation(200, 80);
		coordinatesLabel.setSize(100, 15);
		add(coordinatesLabel);
		
		angleLabel = new JLabel("Angle: 0");
		angleLabel.setLocation(340, 80);
		angleLabel.setSize(130, 15);
		add(angleLabel);
		
		miscLabel = new JLabel("Misc: 13 , 37");
		miscLabel.setLocation(440, 80);
		miscLabel.setSize(110, 15);
		add(miscLabel);
		
		p2NameLabel = new JLabel("Name: null");
		p2NameLabel.setLocation(10,120);
		p2NameLabel.setSize(160, 15);
		add(p2NameLabel);
		
		p2CoordinatesLabel = new JLabel("X,Y: null");
		p2CoordinatesLabel.setLocation(200, 120);
		p2CoordinatesLabel.setSize(100, 15);
		add(p2CoordinatesLabel);
		
		p2AngleLabel = new JLabel("Angle: null");
		p2AngleLabel.setLocation(340, 120);
		p2AngleLabel.setSize(130, 15);
		add(p2AngleLabel);
		
		p2MiscLabel = new JLabel("Misc: null");
		p2MiscLabel.setLocation(440, 120);
		p2MiscLabel.setSize(110,15);
		add(p2MiscLabel);
		
		setVisible(true);
	}
	
	public void addListeners(TestController tc) {
		connectBtn.addActionListener(tc);
		lobbyBtn.addActionListener(tc);
		arenaBtn.addActionListener(tc);
		//shopBtn.addActionListener(tc);
		
		addKeyListener(tc);
	}
	
	public void setNameLabel(String name) {
		nameLabel.setText("Name: " + name);
	}
	
	public void setConnectedLabel(boolean connected) {
		connectedLabel.setText("Connected: " + connected);
	}
	
	public void setModeLabel(String mode) {
		modeLabel.setText("Mode: " + mode);
	}
	
	public void setXyLabel(int x, int y) {
		coordinatesLabel.setText("X,Y: " + x + "," + y);
	}
	
	public void setAngleLabel(int angle) {
		angleLabel.setText("Angle: " + angle);
	}
	
	public String getConnectedBtnText() {
		return connectBtn.getText();
	}
	
	public void setConnectedBtnText(String txt) {
		connectBtn.setText(txt);
	}
	
	public void setOtherNameLabel(String name) {
		p2NameLabel.setText("Name: " + name);
	}
	
	public void setOtherXyLabel(int x, int y) {
		p2CoordinatesLabel.setText("X,Y: " + x + "," + y);
	}
	
	public void setOtherAngleLabel(int angle) {
		p2AngleLabel.setText("Angle: " + angle);
	}
}
