package sshaserver.controller;

import java.net.*;
import java.io.*;
import java.util.*;

public class MultiSocketServer implements Runnable {

	private Socket connection;
	//private String TimeStamp;
	private String returnCode;
	private int ID;
	private enum mode {LOBBY, ARENA, SHOP};
	
	private boolean isClosing;
	public String playerName;
	private int playerX;
	private int playerY;
	private String statString;
	private boolean isDead = false;
	
	private String otherStatsString;
	
	public MultiSocketServer(Socket s, int i) {
		this.connection = s;
		this.ID = i;
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
			
			System.out.println("Player " + playerName + " is connected.");

			// Just let the Client know it's connected and send their ID.
			otherStatsString = "Connected " + this.ID + (char)13;
			osw.write(otherStatsString);
			osw.flush();
			
			while(true) {
				//Read from client
				while( (character = isr.read()) != 13 && character >= 0) {
					process.append((char)character);
				}
				//Convert StringBuffer to String
				this.statString = "" + process.toString();
				System.out.println(statString);
				//System.out.println(statString.indexOf(32));
				
				//Just test this method
				//getPlayerStats();
				
				//Generate the code to return to the Client
				//returnCode = process.toString() + (char)13;
				//returnCode = "Test" + (char)13;
				
				//Clear the StringBuffer
				character = 0;
				process.delete(0,process.length());
			
				try {
					Thread.sleep(1000);
				}
				catch(Exception e) {}
			
			
				//TimeStamp = new java.util.Date().toString();
				//returnCode = "MultipleSocketServer repsonded at "+ TimeStamp + (char) 13;
				//returnCode = "test" + (char)13;
				
				//Send information to the Client
				osw.write(otherStatsString);
				osw.flush();
			
			}
		}
		catch(Exception e) {
			//System.out.println(e);
			isDead = true;
		}
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public int getPlayerID() {
		return this.ID;
	}
	
	public String getPlayerStats() {
		return this.statString;
	}
	
	public synchronized void setPlayerStats(String stats) {
		this.otherStatsString = stats + (char)13;
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

}
