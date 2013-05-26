package sshaserver.controller;

import sshaserver.model.MainHub;
import sshaserver.view.MainView;

public class Main {
	
	private static ClientSupervisor CS;
	private static Thread t;
	
	public static void main(String[] args) {
		MainHub.getController().setMainView(new MainView());
		CS = new ClientSupervisor();
		t = new Thread(CS);
		t.start();
	}
	
	
}
