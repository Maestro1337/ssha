package sshaserver.controller;

import java.io.IOException;
import java.net.*;

import sshaserver.model.MainHub;
import sshaserver.view.MainView;

public class SocketFinder implements Runnable {

	private int port;
	
	private ClientSupervisor CS;
	private ServerSocket socket1;
	private boolean isActive;
	
	public SocketFinder(int port, ClientSupervisor CS, MainView SV) {
		this.port = port;
		this.CS = CS;
		
		startSearching();
	}

	@Override
	public void run() {
		while(!isActive) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
		}
		
		try {
			socket1 = new ServerSocket(port);
			MainHub.getController().getActivityView().addToActivityField("Server started on port " + port);
			
			// Listens for new connections
			while(isActive) {
				Socket connection = socket1.accept();
				if(connection != null) {
					CS.addSocket(connection);
				}
			}
		}
		catch(Exception e) {

		}
		MainHub.getController().getActivityView().addToActivityField("Server stopped");
	}
	
	public void stopSearching() {
		try {
			socket1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		isActive = false;
	}
	
	public void startSearching() {
		isActive = true;
	}
	
	public void changePort(int port) {
		this.port = port;
	}
	
	public int getCurrentPort() {
		return port;
	}
}
