package sshaclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TestController implements Runnable, KeyListener, ActionListener {

	private TestView tv;
	private TestPlayer tp;
	private String mode;
	private SocketClient sc;
	
	public TestController(TestView tv, TestPlayer tp, SocketClient sc) {
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
			tv.setConnectedLabel(tp.isConnected());
			if(tv.getConnectedBtnText().equals("Connect")) {
				tv.setConnectedBtnText("Disconnect");
				
				tp.setConnected(true);
				sc.findConnection(); // kanske fixa denna istallet sa att SC kollar om tp ar connected bara... sa behovs ingen sc har
			} else {
				tv.setConnectedBtnText("Connect");
				tp.setConnected(false);
				sc.closeConnection();
			}
			
		}
		if(action.equals("lobby") || action.equals("arena") || action.equals("shop")) {
			tv.setModeLabel(action);
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
		if(theKey == KeyEvent.VK_A && tp.getAngle() > 0 ) {
			tp.setAngle(tp.getAngle()-1);
		}
		if(theKey == KeyEvent.VK_S && tp.getAngle() < 360) {
			tp.setAngle(tp.getAngle()+1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}


	@Override
	public void run() {
		//testConnection();
		
		while(true) {
			tv.setXyLabel(tp.getX(), tp.getY());
			tv.setAngleLabel(tp.getAngle());
			
			//System.out.println("TestView: " + tv);
			//System.out.println("TestPlayer : " + tp);
			//System.out.println("SocketClient : " + sc);
			//System.out.println("TestController");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Alla setters till vyn ska goras har.
		//Controller kommer alltid koras, men inte alltid anvandas av anvandaren.
		//Alltsa socketclient hamtar data och uppdaterar testplayer
		//testcontroller hamtar sen data darifran och skickar till testview
	}
	
	public void testConnection() {
		tp.setConnected(true);
		sc.findConnection();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tp.setConnected(false);
		sc.closeConnection();
	}

}
