package sshaserver.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.URL;
import java.util.Enumeration;

import sshaserver.view.ServerView;

public class ClientSupervisor implements Runnable, ActionListener {

	private String statString;
	private ServerView SV;
	private SocketFinder SF;
	private Thread sft;
	
	private MultiSocketServer[] theSockets = new MultiSocketServer[constants.nbrOfClients];
	private Thread[] theThreads = new Thread[constants.nbrOfClients];
	
	//private String[] clientsStats = new String[constants.nbrOfClients];
	
	private boolean isRunning;
	
	public ClientSupervisor(ServerView SV) {
		this.SV = SV;
		
		isRunning = false;
		
		SV.setPrivIPLabel(getLocalIP());
		SV.setPubIPLabel(getPublicIP());
		
		SF = new SocketFinder(constants.port, this, SV);
		sft = new Thread(SF);
		
		SV.addActionListener(this);
	}
	
	// Adds a socket-connection it gets from SocketFinder
	public synchronized void addSocket(Socket connection) {
		
		for(int j = 0; j < constants.nbrOfClients; j++) {
			if(theSockets[j] == null) {
				theSockets[j] = new MultiSocketServer(connection, j);
				theThreads[j] = new Thread(theSockets[j]);
				theThreads[j].start();
				
				while(theSockets[j].getPlayerName() == null) {
					try {
						Thread.sleep(constants.globalSleep);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				SV.addToActivityField(theSockets[j].getPlayerName() + " is now connected");
				break;
			}
		}
		
		SV.clearClientsField();
		for(int i = 0; i < constants.nbrOfClients; i++) {
			if(theSockets[i] != null) {
				SV.addClient(theSockets[i].getPlayerName() + (char)9 + theSockets[i].getPlayerID());
			}
		}
	}

	@Override
	public void run() {
		
		while(true) {
			statString = "";
			
			for(int i = 0; i < constants.nbrOfClients; i++) {
				/*
				System.out.println(theSockets[0]);
				if(theSockets[0] != null) {
					System.out.println("The socket is dead:" + theSockets[0].isDead());
				}
				*/
				if(theThreads[i] != null) {
					/*
					//System.out.println(theThreads[i].getState());
					if(theThreads[i].getState() == Thread.State.TERMINATED) {
						theSockets[i] = null;
						theThreads[i] = null;
						System.out.println("Avslutad inkorrekt");
					}
					*/
					
					statString = statString + theSockets[i].getPlayerStats() + "/";
					
					if(theSockets[i].isDead()) {
						SV.addToActivityField(theSockets[i].getPlayerName() + " has disconnected");
						theSockets[i].closeConnection();
						theSockets[i] = null;
						theThreads[i] = null;
						SV.clearClientsField();
						for(int j = 0; j < constants.nbrOfClients; j++) {
							if(theSockets[j] != null) {
								SV.addClient(theSockets[j].getPlayerName() + (char)9 + theSockets[j].getPlayerID());
							}
						}
					}
					
				}	
			}
			
			//statString = statString + "/";
			//System.out.println(statString + " LOL");
			
			
			for(int j = 0; j < constants.nbrOfClients; j++) {
				if(theThreads[j] != null) {
					theSockets[j].setPlayerStats(statString);
				}
			}
			
			
			try {
				Thread.sleep(constants.globalSleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public synchronized void setAllStats(String stats) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("connect")) {
			if(!SV.isRunning()) {
				startServer();
			} else {
				stopServer();
			}
			SV.changeButtonText();
		} else if(e.getActionCommand().equals("save")) {
			
		} else if(e.getActionCommand().equals("clear")) {
			SV.clearActivityField();
		} else if(e.getActionCommand().equals("change")) {
			String tempPort = SV.getPortField();
			int port = -1;
			String whatChange;
			
			try {
				port = Integer.parseInt(tempPort);
			}
			catch(NumberFormatException nfe) {
				tempPort = "" + constants.port;
			}
				
			if(port < 1024 || port > 9999 || tempPort.length() > 4) {
				whatChange = SV.showDialogBox("error");
				SV.setPortField("" + constants.port);
				tempPort = "" + constants.port;
			} else {
				if(SF.getCurrentPort() != port) {
					// Have to have a check if the server is running
					if(isRunning) {
						whatChange = SV.showDialogBox("change");
						if(whatChange.equals("Restart now")) {
							restartServer(Integer.parseInt(tempPort));
						}
					} else {
						SF.changePort(Integer.parseInt(tempPort));
						SV.addToActivityField("Port changed to " + tempPort);
					}
				}
				
			}
			
		} else if(e.getActionCommand().equals("default")) {
			SV.setPortField("" + constants.port);
		}
	}
	
	public String getLocalIP() {
		String tempIP = "";
		try {
			Enumeration e = NetworkInterface.getNetworkInterfaces();
			while(e.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) e.nextElement();
				Enumeration e2 = ni.getInetAddresses();
				while(e2.hasMoreElements()) {
					InetAddress ip = (InetAddress) e2.nextElement();
					// Netgear-routers (and others)
					if(ip.toString().length() > 9) {
						if(ip.toString().substring(0, 9).equals("/192.168.")) {
							tempIP = ip.toString();
						}
					}
					// Apple-routers
					if(ip.toString().substring(0, 6).equals("/10.0.")) {
						tempIP = ip.toString();
					}
					
				}
			}
			
		}
		catch (Exception e) {
			tempIP = "Not available";
			System.out.println("No network avalible.");
			e.printStackTrace();
		}
		if(!tempIP.equals("")) {
			if(tempIP.substring(0, 1).equals("/")) {
				tempIP = tempIP.substring(1, tempIP.length());
			}
		} else {
			tempIP = "Not available";
		}
		return tempIP;
	}
	
	public String getPublicIP() {
		String tempIP = "";
		try {
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			tempIP = in.readLine();
		}
		catch (Exception e) {
			System.out.println("Not connected to internet.");
			tempIP = "Not available";
		}
		return tempIP;
	}
	
	public void startServer() {
		if(sft.getState() == Thread.State.NEW) {
			sft.start();
		} else {
			SF.startSearching();
			sft = null;
			sft = new Thread(SF);
			sft.start();
		}
		isRunning = true;
	}
	
	public void stopServer() {
		SF.stopSearching();
		for(int i = 0; i < constants.nbrOfClients; i++) {
			if(theSockets[i] != null) {
				theSockets[i].closeConnection();
			}
		}
		
		isRunning = false;
	}
	
	public void restartServer(int newPort) {
		isRunning = false;
		stopServer();
		SF.changePort(newPort);
		SV.changeButtonText();
		try {
			Thread.sleep(constants.globalSleep);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startServer();
		SV.changeButtonText();
		isRunning = true;
	}

}
