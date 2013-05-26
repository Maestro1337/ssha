package sshaserver.controller;

import java.net.*;
import java.io.*;

import sshaserver.model.MainHub;

public class MultiSocketServer implements Runnable {

	private Socket connection;
	private int ID;
	private String[] names;
	
	public String playerName;
	private String statString;
	private boolean isDead = false;
	private boolean nameIsFree;
	
	private String otherStatsString;
	
	public MultiSocketServer(Socket s, int i, String[] names) {
		this.connection = s;
		this.ID = i;
		this.names = names;
	}
	
	@Override
	public void run() {
		
		try {
			//Create input and output readers
			BufferedInputStream is = new BufferedInputStream(connection.getInputStream());
			InputStreamReader isr = new InputStreamReader(is);
			BufferedOutputStream os = new BufferedOutputStream(connection.getOutputStream());
			OutputStreamWriter osw = new OutputStreamWriter(os, "US-ASCII");
			
			int character;
			StringBuffer process = new StringBuffer();
			
			while( (character = isr.read()) != 13) {
				process.append((char)character);
			}
			playerName = process.toString();
			playerName = playerName.substring(0, playerName.indexOf(32));
			
			character = 0;
			process.delete(0, process.length());
			
			nameIsFree = true;
			for(int i = 0; i < names.length; i++) {
				if(names[i].equals(playerName)) {
					nameIsFree = false;
				}
			}

			if(nameIsFree) {
				// Just let the Client know it's connected and send their ID.
				otherStatsString = "Connected " + this.ID + (char)13;
				osw.write(otherStatsString);
				osw.flush();
				
				System.out.println("Player " + playerName + " is connected.");
			} else {
				
				//It still looks like the player connects and then disconnects. FIX IT!
				otherStatsString = "NameTaken 0" + (char)13;
				osw.write(otherStatsString);
				osw.flush();
			}
			
			
			while(true) {
				//Read from client
				while( (character = isr.read()) != 13 && character >= 0) {
					process.append((char)character);
				}
				
				//Convert StringBuffer to String
				this.statString = "" + process.toString();
				System.out.println(statString);
				
				//Clear the StringBuffer
				character = 0;
				process.delete(0,process.length());
			
				try {
					Thread.sleep(MainHub.globalSleep);
				}
				catch(Exception e) {}
				
				//Send information to the Client
				osw.write(otherStatsString);
				osw.flush();
			
			}
		}
		catch(Exception e) {
			System.out.println(e);
			isDead = true;
		}
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public int getPlayerID() {
		return this.ID;
	}
	
	public synchronized String getPlayerStats() {
		return this.statString;
	}
	
	public synchronized void setPlayerStats(String stats) {
		this.otherStatsString = stats + "/" + (char)13;
	}
	
	public void closeConnection() {
		try {
			connection.close();
		}
		catch(IOException e) {}
	}
	
	public boolean isDead() {
		return isDead;
	}

	public boolean isNameFree() {
		return nameIsFree;
	}
}
