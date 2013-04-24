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
	
	public String playerName;
	private int playerX;
	private int playerY;
	private String statString;
	
	public MultiSocketServer(Socket s, int i) {
		this.connection = s;
		this.ID = i;
	}
	
	@Override
	public void run() {
		
		//System.out.println(MC.getLol());
		
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
			this.playerName = process.toString();
			character = 0;
			process.delete(0, process.length());
			
			System.out.println("Player " + playerName + " is connected.");
			
			while(true) {
				//Read from client
				while( (character = isr.read()) != 13) {
					process.append((char)character);
				}
				//System.out.println(process);
				
				//Convert StringBuffer to String
				this.statString = "" + process.toString();
				//System.out.println(statString.indexOf(32));
				
				//Just test this method
				getPlayerStats();
				
				//Generate the code to return to the Client
				returnCode = process.toString() + (char)13;
				
				//Clear the StringBuffer
				character = 0;
				process.delete(0,process.length());
			
				try {
					Thread.sleep(500);
				}
				catch(Exception e) {}
			
			
				//TimeStamp = new java.util.Date().toString();
				//returnCode = "MultipleSocketServer repsonded at "+ TimeStamp + (char) 13;
				//returnCode = "test" + (char)13;
				
				//Send information to the Client
				osw.write(returnCode);
				osw.flush();
			
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public int getPlayerID() {
		return this.ID;
	}
	
	public int[] getPlayerStats() {
		String tempStats = "" + statString;
		int[] stats = new int[2];
		int counter = 0;
		
		while(tempStats.length() > 1) {
			stats[counter] = Integer.parseInt(tempStats.substring(tempStats.indexOf(32)+1, tempStats.indexOf(32, tempStats.indexOf(32)+1)));
			tempStats = tempStats.substring(tempStats.indexOf(32, tempStats.indexOf(32)+1), tempStats.length()-1);
			counter += 1;
		}
		
		return stats;
	}
	
	public void setPlayerStats() {
		
	}
	
	public void closeConnection() {
		try {
			connection.close();
		}
		catch(IOException e) {}
	}

}
