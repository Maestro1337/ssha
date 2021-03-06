package sshaclient;

import java.net.*;
import java.io.*;

import main.MainHub;

import Model.Player;
import Model.Skill;
import Model.Classes.*;


public class SocketClient implements Runnable {
	
	private String[] playerStats;
	private boolean[] playerChanged;
	private Player tp;
	private boolean allAreReady;
	
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
	
	public SocketClient(String host, int port) {
		
		this.allAreReady = false;
		
		this.host = host;
		this.port = port;
		
		playerStats = new String[MainHub.nbrOfPlayers];
		playerChanged = new boolean[MainHub.nbrOfPlayers];
		
		instr = new StringBuffer();
	}
	
	@Override
	public void run() {
		Skill[] tempSkills;
		
		while(true) {
			if(connected) {
				tempSkills = tp.getSkillList();
				if(tp.getMode().equals("lobby")) {
					process = tp.getName() + " " + tp.getPlayerListIndex() + " " + tp.getMode() + " " + tp.getType() + " " + tp.getTotalKills() + " " + tp.getGold() + " skills";
					for(int i = 0; i < tempSkills.length; i++) {
						if(tempSkills[i] != null) {
							process = process + " " + tempSkills[i].getSmallName() + " " + tempSkills[i].getCurrentLvl();
						} else {
							process = process + " noskill 0";
						}
					}
					process = process + " " + tp.getX() + " " + tp.getY() + " " + tp.getArmor() + " " + tp.getEvasion() + " " + tp.getMovementSpeed() + " " + MainHub.getController().getMapIndex() + " " + tp.getReadyState();
					process = process + " " + tp.getHasClickedStartGame();
				} else {
					
					int skillIndex = tp.getCurrentActiveSkillIndex();
					
					process = tp.getName() + " " + tp.getPlayerListIndex() + " " + tp.getMode() + " " + tp.getX() + " " + tp.getY() + " ";
					process = process + tp.getHP() + " " + tp.getCanWalkState() + " " + tp.getRunningState() + " " + tp.getMouseXPosMove() + " " + tp.getMouseYPosMove() + " ";
					process = process + tp.getCanAttackState() + " " + skillIndex + " " + tp.getSkillList()[skillIndex].getAttackingState() + " ";
					process = process + tp.getSkillList()[skillIndex].getMouseXPos() + " " + tp.getSkillList()[skillIndex].getMouseYPos();
					
					int[] tempObs = MainHub.getController().getMapSelected().getDestroyedObstacles();
					
					if(tempObs != null) {
						process = process + " " + tempObs.length;
						for(int i = 0; i < tempObs.length; i++) {
							process = process + " " + tempObs[i];
						}
					} else {
						process = process + " " + 0;
					}
				}
				
				process = process + (char)13;
				sendData(process);
				recieveData();
				
			}
			
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void findConnection() {
		while(!tp.getConnected()) {
			try {
				Thread.sleep(1);
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
			
			connected = true;
		}
		catch(IOException ioe) {
			connected = false;
			tp.setConnected(false);
			System.out.println("No contact with server");
		}
	}
	
	public synchronized void sendData(String process) {
		
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
			while((c = isr.read()) != 13 && instr.length() < 5000) {
				instr.append((char)c);
			}
			inData = instr.toString();
			
			if(inData.length() > 10 && inData.length() < 4999) {
				if(inData.substring(0, inData.indexOf(32)).equals("Connected")) {
					tp.setPlayerListIndex(Integer.parseInt(inData.substring(inData.indexOf(32) + 1, inData.length())));
					MainHub.getController().addPlayer(tp, tp.getPlayerListIndex());
					MainHub.getController().setActivePlayerIndex(tp.getPlayerListIndex());
					
					tp.setNameTaken(false);
				} else if(inData.substring(0, inData.indexOf(32)).equals("NameTaken")) { 
					System.out.println("Name is already taken");
					tp.setNameTaken(true);
					//connected = false;
					closeConnection();
				}else {
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
			
			for(int i = 0; i < MainHub.getController().getPlayerControllers().length; i++) {
				if(MainHub.getController().getPlayerControllers()[i] != null) {
					MainHub.getController().getPlayerControllers()[i].killItWithFire();
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
			
			if(!tempStats.equals("null") && !tempStats.equals("")) {
				arrPos = Integer.parseInt(MainHub.getItem(tempStats, 1));
				
				playerStats[arrPos] = tempStats;
				playerChanged[arrPos] = true; 
				
				// Check if the slot is empty and it's not your own name
				// If it's a new Player then a new Player with Controller and Thread is created.
				if(MainHub.getController().getPlayer(arrPos) == null && !tempStats.substring(0, tempStats.indexOf(32)).equals(tp.getName())) {
					// Add method for checking class, string after id maybe? Instead of just adding "TestClass"
					
					//Create new player from the playerclass
					if(MainHub.getItem(tempStats, 3).equals("Hunter")) {
						MainHub.getController().addPlayer(new ClassHunter(MainHub.getItem(tempStats, 0), "server", Float.parseFloat(MainHub.getItem(tempStats, 17)), Float.parseFloat(MainHub.getItem(tempStats, 18)), arrPos), arrPos);
					} else if(MainHub.getItem(tempStats, 3).equals("Warrior")) {
						MainHub.getController().addPlayer(new ClassWarrior(MainHub.getItem(tempStats, 0), "server", Float.parseFloat(MainHub.getItem(tempStats, 17)), Float.parseFloat(MainHub.getItem(tempStats, 18)), arrPos), arrPos);
					} else if(MainHub.getItem(tempStats, 3).equals("Wizard")) {
						MainHub.getController().addPlayer(new ClassWizard(MainHub.getItem(tempStats, 0), "server", Float.parseFloat(MainHub.getItem(tempStats, 17)), Float.parseFloat(MainHub.getItem(tempStats, 18)), arrPos), arrPos);
					}
					
					MainHub.getController().getPlayer(arrPos).setPlayerListIndex(arrPos);
					MainHub.getController().addPlayerController(new PlayerClientController(this, MainHub.getController().getPlayer(arrPos)), arrPos);
					MainHub.getController().addControllerThread(new Thread(MainHub.getController().getPlayerControl(arrPos)), arrPos);
					MainHub.getController().getControllerThread(arrPos).start();
				}
			}
			data = data.substring(data.indexOf(47)+1, data.length());
		}
		
		// Checks which connections haven't been updated and kills their Threads and deletes their Players
		for(int k = 0; k < playerChanged.length; k++) {
			if(!playerChanged[k]) {
				playerStats[k] = null;
				if(MainHub.getController().getPlayer(k) != null && MainHub.getController().getPlayerControl(k) != null) {
					MainHub.getController().getPlayerControl(k).killItWithFire();
					MainHub.getController().removeControllerThread(k);
					MainHub.getController().removePlayerController(k);
					MainHub.getController().removePlayer(k);
				}
			}
		}
		
		// Checking if players in the lobby are ready.
		boolean tempReady = true;
		for(int i = 0; i < MainHub.getController().getPlayers().length; i++) {
			if(MainHub.getController().getPlayer(i) != null) {
				if(!MainHub.getController().getPlayer(i).getReadyState()) {
					tempReady = false;
				}
			}
		}
		allAreReady = tempReady;
	}
	
	
	public synchronized String getPlayerStats(int pos) {
		return playerStats[pos];
	}
	
	public boolean allAreReady() {
		return allAreReady;
	}
	
	public String[] getPlayerNames() {
		String[] names = new String[MainHub.nbrOfPlayers];
		
		for(int i = 0; i < MainHub.getController().getPlayers().length; i++) {
			names[i] = "";
			if(MainHub.getController().getPlayer(i) != null) {
				names[i] = MainHub.getController().getPlayer(i).getName();
			}
		}
		return names;
	}
	
	public void changePlayer(Player player) {
		this.tp = player;
	}
	
	public Player getPlayer() {
		return this.tp;
	}
}
