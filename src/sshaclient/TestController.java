package sshaclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TestController implements KeyListener, ActionListener {

	TestView tv;
	TestPlayer tp;
	
	public TestController(TestView tv, TestPlayer tp) {
		this.tv = tv;
		this.tp = tp;
		
		tv.addListeners(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		if(action.equals("connect")) {
			tp.setConnected(!tp.isConnected());
			
			//connectedLabel.setText("Connected: " + connected);
		}
		if(action.equals("lobby") || action.equals("arena") || action.equals("shop")) {
			//modeLabel.setText("Mode: " + action);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int theKey = e.getKeyCode();
		
		if(theKey == KeyEvent.VK_LEFT && tp.getX() > 0) {
			//x -= 1;
		}
		if(theKey == KeyEvent.VK_RIGHT && tp.getX() < 9999) {
			//x += 1;
		}
		if(theKey == KeyEvent.VK_UP && tp.getY() > 0) {
			//y -= 1;
		}
		if(theKey == KeyEvent.VK_DOWN && tp.getY() < 9999) {
			//y += 1;
		}
		if(theKey == KeyEvent.VK_A && tp.getAngle() > 0 ) {
			//angle -= 1;
		}
		if(theKey == KeyEvent.VK_S && tp.getAngle() < 360) {
			//angle += 1;
		}
		
		//coordinatesLabel.setText("X,Y: " + x + "," + y);
		//angleLabel.setText("Angle: " + angle);
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}
