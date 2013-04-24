package sshaserver.controller;

import sshaserver.view.ServerView;

public class Main {
	
	private static ClientSupervisor CS;
	private static Thread t;
	private static ServerView SV;
	
	public static void main(String[] args) {
		
		SV = new ServerView();
		
		CS = new ClientSupervisor(SV);
		t = new Thread(CS);
		t.start();
		
		//new SocketFinder(constants.port, CS);
		
	}
	
	
}
