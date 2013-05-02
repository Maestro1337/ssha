package sshaclient;


public class Main {

	TestPlayer[] tpa = new TestPlayer[3];
	
	
	public static void main(String[] args) {
		TestPlayer[] tpa = new TestPlayer[3];
		
		TestView tv = new TestView();
		TestPlayer tp = new TestPlayer("Sebbe", "player");
		SocketClient sc = new SocketClient("localhost", 6666, "Sebbe", tp);
		Thread t2 = new Thread(sc);
		t2.start();
		TestController tc = new TestController(tv, tp, sc); 
		Thread t = new Thread(tc);
		t.start();
		
	}

}
