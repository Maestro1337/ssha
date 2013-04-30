package sshaclient;


public class Main {

	
	
	
	public static void main(String[] args) {
		TestView tv = new TestView();
		TestPlayer tp = new TestPlayer();
		SocketClient sc = new SocketClient("localhost", 6666, "Sebbe", tp);
		Thread t2 = new Thread(sc);
		t2.start();
		TestController tc = new TestController(tv, tp, sc); 
		Thread t = new Thread(tc);
		t.start();
		
	}

}
