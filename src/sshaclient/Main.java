package sshaclient;


public class Main {

	
	public static void main(String[] args) {
		TestView tv = new TestView();
		TestPlayer tp = new TestPlayer();
		TestController tc = new TestController(tv, tp); 
		SocketClient sc = new SocketClient("localhost", 6666, "Sebbe", tp);
		Thread t = new Thread(sc);
		t.start();
	}

}
