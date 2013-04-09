package Model;
import java.awt.*;

import javax.swing.JFrame;

public class Screen {
	
	private GraphicsDevice vc;
	
	public Screen(){
		
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = env.getDefaultScreenDevice();
	}
	
	public void setFullScreen(DisplayMode dm, JFrame window){
		window.setUndecorated(true);
		window.setResizable(false);
		vc.setFullScreenWindow(window);
		
		if(dm != null && vc.isDisplayChangeSupported()){
			try{
				vc.setDisplayMode(dm);
			}catch(Exception ex){}
		}
	}
	
	public Window getFullScreenWindow(){
		return vc.getFullScreenWindow();
	}
	
	public void restoreScreen(){
		Window w = vc.getFullScreenWindow();
		
		if(w != null){
			w.dispose();
		}
		vc.setFullScreenWindow(null);
	}
	
	
}
