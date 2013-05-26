package sshaserver.controller;

import sshaserver.model.MainHub;
import sshaserver.view.ActivityView;
import sshaserver.view.MainView;
import sshaserver.view.SettingsView;

public class Main {
	
	private static ClientSupervisor CS;
	private static Thread t;
	
	public static void main(String[] args) {
		MainHub.getController().setActivityView(new ActivityView());
		MainHub.getController().setSettingsView(new SettingsView());
		MainHub.getController().setMainView(new MainView());
		CS = new ClientSupervisor();
		t = new Thread(CS);
		t.start();
	}
	
	
}
