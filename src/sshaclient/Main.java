package sshaclient;

import Model.Player;
import Model.Classes.*;

public class Main {
	
	public static void main(String[] args) {
		
		TestView tv = new TestView();
		Player tp = new ClassWizard(Constants.name, "player", 100, 100, -1);
		SocketClient sc = new SocketClient(Constants.hostName, Constants.defaultPort, tp);
		Thread t2 = new Thread(sc);
		t2.start();
		TestController tc = new TestController(tv, tp, sc); 
		Thread t = new Thread(tc);
		t.start();
		
	}

}
