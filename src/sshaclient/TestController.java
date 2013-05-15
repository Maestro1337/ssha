package sshaclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.Player;

public class TestController implements Runnable, KeyListener, ActionListener {

	private TestView tv;
	private Player tp;
	private String mode;
	private SocketClient sc;
	
	public TestController(TestView tv, Player tp, SocketClient sc) {
		this.tv = tv;
		this.tp = tp;
		this.sc = sc;
		
		tv.addListeners(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		if(action.equals("connect")) {
			tp.setConnected(!tp.isConnected());
			if(tv.getConnectedBtnText().equals("Connect")) {
				//tv.setConnectedBtnText("Disconnect");
				
				tp.setConnected(true);
				sc.findConnection(); // kanske fixa denna istallet sa att SC kollar om tp ar connected bara... sa behovs ingen sc har
			} else {
				//tv.setConnectedBtnText("Connect");
				tp.setConnected(false);
				sc.closeConnection();
			}
		}
		if(action.equals("lobby") || action.equals("arena")) {
			tv.setModeLabel(action);
			tp.setMode(action);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int theKey = e.getKeyCode();
		
		if(theKey == KeyEvent.VK_LEFT && tp.getX() > 0) {
			tp.setX(tp.getX()-1);
		}
		if(theKey == KeyEvent.VK_RIGHT && tp.getX() < 9999) {
			tp.setX(tp.getX()+1);
		}
		if(theKey == KeyEvent.VK_UP && tp.getY() > 0) {
			tp.setY(tp.getY()-1);
		}
		if(theKey == KeyEvent.VK_DOWN && tp.getY() < 9999) {
			tp.setY(tp.getY()+1);
		}
		if(theKey == KeyEvent.VK_A && tp.getRotation() > 0 ) {
			tp.setRotation(tp.getRotation()-1);
		}
		if(theKey == KeyEvent.VK_S && tp.getRotation() < 360) {
			tp.setRotation(tp.getRotation()+1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}


	@Override
	public void run() {
		while(true) {
			tv.setXyLabel(tp.getX(), tp.getY());
			tv.setAngleLabel(tp.getRotation());
			
			if(sc.isConnected()) {
				tv.setConnectedBtnText("Disconnect");
				tv.setConnectedLabel(true);
			} else {
				tv.setConnectedBtnText("Connect");
				tv.setConnectedLabel(false);
			}
			
			try {
				Thread.sleep(Constants.globalSleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
