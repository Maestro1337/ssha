package sshaclient;

import java.net.*;
import java.io.*;

import Model.Classes.*;


public class SocketClient implements Runnable {
	
	private String[] playerStats;
	private boolean[] playerChanged;
	private Player tp;
	
	private Player[] tpa;
	private PlayerControl[] tpac;
	private Thread[] tpat;
	
	private String host;
	private int port;

	private InetAddress address;
	private Socket connection;
	
	private StringBuffer instr;
	private String inData = "";
	private String process = "";
	private boolean connected = false;
	
	private BufferedOutputStream bos;
	private OutputStreamWriter osw;
	private BufferedInputStream bis;
	private InputStreamReader isr;
	
	public SocketClient(String host, int port, Player tp, Player[] tpa, PlayerControl[] tpac, Thread[] tpat) {
		this.tp = tp;
		
		this.host = host;
		this.port = port;
		
		this.tpa = tpa;
		this.tpac = tpac;
		this.tpat = tpat;
		
		playerStats = new String[Constants.nbrOfPlayer];
		playerChanged = new boolean[Constants.nbrOfPlayer];
		
		instr = new StringBuffer();
	}
	
	@Override
	public void run() {
		
		Skill[] tempSkills;
		
		while(true) {
			
			if(connected) {
				tempSkills = tp.getSkillList();
				if(tp.getMode().equals("lobby")) {
					process = tp.getName() + " " + tp.getID() + " " + tp.getMode() + " " + tp.getType() + " " + tp.getKills() + " " + tp.getGold() + " skills";
					for(int i = 0; i < tempSkills.length; i++) {
						if(tempSkills[i] != null) {
							process = process + " " + tempSkills[i].getSmallName() + " " + tempSkills[i].getCurrentLvl();
						} else {
							process = process + " noskill 0";
						}
					}
					process = process + " " + tp.getX() + " " + tp.getY();
				} else {
					process = tp.getName() + " " + tp.getID() + " " + tp.getMode() + " " + tp.getX() + " " + tp.getY() + " " + tp.getRotation() + " ";
					process = process + tp.isRunning() + " " + tp.isStunned() + " " + tp.getMoveSpeed() + " skills";
					for(int j = 0; j < tempSkills.length; j++) {
						if(tempSkills[j] != null) {
							process = process + " " + tempSkills[j].getSmallName() + " " + tempSkills[j].getAttX() + " " + tempSkills[j].getAttY() + " ";
							process = process + tempSkills[j].getRotation() + " " + tempSkills[j].isAttacking();
						} else {
							process = process + " noskill 0 0 0 0 false";
						}
					}
				}
				
				process = process + (char)13;
				sendData(process);
				recieveData();
				
			}
			
			try {
				Thread.sleep(Constants.globalSleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void findConnection() {
		while(!tp.isConnected()) {
			try {
				Thread.sleep(Constants.globalSleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("SocketClient initialized");
		
		try {
			address = InetAddress.getByName(host);
			connection = new Socket(address, port);
			
			bos = new BufferedOutputStream(connection.getOutputStream());
			osw = new OutputStreamWriter(bos, "US-ASCII");
			bis = new BufferedInputStream(connection.getInputStream());
			isr = new InputStreamReader(bis, "US-ASCII");
			
			//System.out.println("lol");
			connected = true;
		}
		catch(IOException ioe) {
			connected = false;
			tp.setConnected(false);
			System.out.println("No contact with server");
		}
	}
	
	public synchronized void sendData(String process) {
		
		//System.out.println(process.substring(beginIndex, endIndex));
		
		//System.out.println("Din klass aer: " + Constants.getItem(process, 3));
		
		try {
			osw.write(process);
			osw.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public synchronized void recieveData() {
		int c = 0;
		
		try {
			while((c = isr.read()) != 13 && instr.length() < 500) {
				instr.append((char)c);
			}
			inData = instr.toString();
			System.out.println(inData);
			
			if(inData.length() < 499) {
				if(inData.substring(0, inData.indexOf(32)).equals("Connected")) {
					tp.setId(Integer.parseInt(inData.substring(inData.indexOf(32) + 1, inData.length())));
					System.out.println(tp.getName() + "'s ID is: " + tp.getID());
				} else {
					splitData(inData);
				}
			} else {
				connected = false;
			}
			
			c = 0;
			instr.delete(0,instr.length());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void closeConnection() {
		if(connection != null) {
			
			for(int i = 0; i < tpac.length; i++) {
				if(tpac[i] != null) {
					tpac[i].killItWithFire();
				}
			}
			
			try {
				connection.close();
				connected = false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isConnected() {
		return connected;
	}
	
	/**
	 * A method for splitting up playerData according to their ID.
	 * @param String Contains data of 1 or more players including this client's.
	 */
	public synchronized void splitData(String data) {
		String tempStats = "";
		int arrPos = 0;
		
		for(int j = 0; j < playerChanged.length; j++) {
			playerChanged[j] = false;
		}
		
		
		if(data.substring(0, 1).equals("/")) {
			data = data.substring(1, data.length()-1);
		}
		
		while(data.length() > 1) {
			tempStats = data.substring(0, data.indexOf(47));
			
			//System.out.println("Stat: " + tempStats);
			//System.out.println("Name: " + Constants.getItem(tempStats, 0) + " Class: " + Constants.getItem(tempStats, 3));
			
			if(!tempStats.equals("null") && !tempStats.equals("")) {
				arrPos = Integer.parseInt(Constants.getItem(tempStats, 1));
				
				//arrPos = Integer.parseInt(tempStats.substring(tempStats.indexOf(32)+1, tempStats.indexOf(32, tempStats.indexOf(32)+1)));
				playerStats[arrPos] = tempStats;
				playerChanged[arrPos] = true;
				
				//System.out.println(tempStats.substring(0, tempStats.indexOf(32)));
				
				// Check if the slot is empty and it's not your own name
				// If it's a new Player then a new Player with Controller and Thread is created.
				if(tpa[arrPos] == null && !tempStats.substring(0, tempStats.indexOf(32)).equals(tp.getName())) {
					// Add method for checking class, string after id maybe? Instead of just adding "TestClass"
					
					//Create new player from the playerclass
					
					if(Constants.getItem(tempStats, 3).equals("Hunter")) {
						tpa[arrPos] = new ClassHunter(Constants.getItem(tempStats, 0), "server", Float.parseFloat(Constants.getItem(tempStats, 17)), Float.parseFloat(Constants.getItem(tempStats, 18)), Integer.parseInt(Constants.getItem(tempStats, 1)));
					} else if(Constants.getItem(tempStats, 3).equals("Warrior")) {
						tpa[arrPos] = new ClassWarrior(Constants.getItem(tempStats, 0), "server", Float.parseFloat(Constants.getItem(tempStats, 17)), Float.parseFloat(Constants.getItem(tempStats, 18)), Integer.parseInt(Constants.getItem(tempStats, 1)));
					} else if(Constants.getItem(tempStats, 3).equals("Wizard")) {
						tpa[arrPos] = new ClassWizard(Constants.getItem(tempStats, 0), "server", Float.parseFloat(Constants.getItem(tempStats, 17)), Float.parseFloat(Constants.getItem(tempStats, 18)), Integer.parseInt(Constants.getItem(tempStats, 1)));
					}
					
					//tpa[arrPos] = new Player(Constants.getItem(tempStats, 0), "server", Constants.getItem(tempStats, 3));
					
					
					tpa[arrPos].setId(arrPos);
					tpac[arrPos] = new PlayerClientController(this, tpa[arrPos]);
					tpat[arrPos] = new Thread(tpac[arrPos]);
					tpat[arrPos].start();
				}
			}
			data = data.substring(data.indexOf(47)+1, data.length());
		}
		
		// Checks which connections haven't been updated and kills their Threads and deletes their Players
		for(int k = 0; k < playerChanged.length; k++) {
			if(!playerChanged[k]) {
				playerStats[k] = null;
				if(tpa[k] != null) {
					tpac[k].killItWithFire();
					tpat[k] = null;
					tpac[k] = null;
					tpa[k] = null;
				}
			}
		}
		
		// Just a test-method for checking which stats the Socket is getting.
		for(int i = 0; i < playerStats.length; i++) {
			//System.out.println("Stats: " + playerStats[i]);
		}
		
	}
	
	
	public synchronized String getPlayerStats(int pos) {
		
		return playerStats[pos];
	}
				
}
