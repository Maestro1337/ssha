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

		//System.out.println(System.getProperty("user.dir"));
		
		//System.out.println(CS.getLocalIP());
		//System.out.println(CS.getPublicIP());
		//new SocketFinder(constants.port, CS);
		
	}
	
	
}
