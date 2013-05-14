package sshaserver.controller;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;

import sshaserver.view.ServerView;

public class SocketFinder implements Runnable {

	private int port;
	
	private ClientSupervisor CS;
	private ServerView SV;
	private ServerSocket socket1;
	private boolean isActive;
	
	public SocketFinder(int port, ClientSupervisor CS, ServerView SV) {
		this.port = port;
		this.CS = CS;
		this.SV = SV;
		
		
		startSearching();
	}

	@Override
	public void run() {
		while(!isActive) {
			try {
				System.out.println("lol");
				Thread.sleep(10);
			} catch (InterruptedException e) {}
		}
		
		try {
			socket1 = new ServerSocket(port);
			SV.addToActivityField("Server started on port " + port);
			
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
		SV.addToActivityField("Server stopped");
	}
	
	// Closes the socket and thereby stops the search
	public void stopSearching() {
		try {
			socket1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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