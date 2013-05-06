package sshaserver.controller;

import java.io.IOException;
import java.net.*;
import java.util.HashMap;

import sshaserver.view.ServerView;

public class SocketFinder implements Runnable {

	//private static MultiSocketServer[] theSockets = new MultiSocketServer[constants.nbrOfClients];
	//private static Thread[] theThreads = new Thread[constants.nbrOfClients];
	private int port;
	//int count = 0;
	
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
		//System.out.println("1");
		while(!isActive) {
			try {
				System.out.println("lol");
				Thread.sleep(10);
			} catch (InterruptedException e) {}
		}
		
		try {
			socket1 = new ServerSocket(port);
			SV.addToActivityField("Server started on port " + port);
			
			while(isActive) {
				//System.out.println("2");
				Socket connection = socket1.accept();
				//theSockets[++count] = new MultiSocketServer(connection, count);
				//theThreads[count] = new Thread(theSockets[count]);
				//theThreads[count].start();
				//System.out.println(count);
				if(connection != null) {
					CS.addSocket(connection);
				}
			}
		}
		catch(Exception e) {
			//System.out.println("3");
		}
		//System.out.println("4");
		SV.addToActivityField("Server stopped");
	}
	
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

}
