package sshaclient;

import Model.Classes.*;

public class Main {
	
	public static void main(String[] args) {
		Player[] tpa = new Player[Constants.nbrOfPlayer];
		PlayerControl[] tpac = new PlayerControl[Constants.nbrOfPlayer];
		Thread[] tpat = new Thread[Constants.nbrOfPlayer];
		
		TestView tv = new TestView();
		Player tp = new ClassWizard(Constants.name, "player", 100, 100, -1);
		SocketClient sc = new SocketClient(Constants.hostName, Constants.port, tp, tpa, tpac, tpat);
		Thread t2 = new Thread(sc);
		t2.start();
		TestController tc = new TestController(tv, tp, sc, tpa, tpat); 
		Thread t = new Thread(tc);
		t.start();
	}

}
