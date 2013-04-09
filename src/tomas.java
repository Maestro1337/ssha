
import java.awt.*;
import javax.swing.JFrame;

import Model.Screen;

public class tomas extends JFrame{
	public static void main(String[] args){

		DisplayMode dm = new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		tomas a = new tomas();
		a.run(dm);
	}
	
	public void run (DisplayMode dm){
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setFont(new Font("Calibri", Font.PLAIN, 24));
		
		Screen s = new Screen();
		try{
			s.setFullScreen(dm, this);
			try{
				Thread.sleep(10000);
			}catch(Exception ex){}
		}finally{
			s.restoreScreen();
		}
	}
	
	public void paint(Graphics g){
		g.drawString("I am awesome!", 325, 300);
		g.draw3DRect(350, 325, 100, 100, false);
		g.drawLine(200, 300, 600, 300);
	}
}