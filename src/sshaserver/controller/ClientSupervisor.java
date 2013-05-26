package sshaserver.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import sshaserver.model.MainHub;
import sshaserver.view.MainView;

public class ClientSupervisor implements Runnable, ActionListener {

	private String statString;
	private MainView mainView;
	private SocketFinder socketFinder;
	private Thread socketFinderThread;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	private MultiSocket[] theSockets = new MultiSocket[MainHub.nbrOfClients];
	private Thread[] theThreads = new Thread[MainHub.nbrOfClients];
	
	private boolean isRunning;
	
	public ClientSupervisor() {
		isRunning = false;
		
		mainView = MainHub.getController().getMainView();
		MainHub.getController().getSettingsView().setPrivIPLabel(getLocalIP());
		MainHub.getController().getSettingsView().setPubIPLabel(getPublicIP());
		
		socketFinder = new SocketFinder(MainHub.port, this, mainView);
		socketFinderThread = new Thread(socketFinder);
		
		MainHub.getController().getActivityView().addActionListener(this);
		MainHub.getController().getSettingsView().addActionListener(this);
	}
	
	public String getLocalIP() {
		String tempIP = "";
		try {
			Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
			while(e.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) e.nextElement();
				Enumeration<InetAddress> e2 = ni.getInetAddresses();
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
	
	// Adds a socket-connection it gets from SocketFinder
	public void addSocket(Socket connection) {
		String[] names;
		
		for(int j = 0; j < MainHub.nbrOfClients; j++) {
			if(theSockets[j] == null) {
				names = new String[MainHub.nbrOfClients];
				for(int k = 0; k < MainHub.nbrOfClients; k++) {
					if(theSockets[k] != null) {
						names[k] = theSockets[k].getPlayerName();
					} else {
						names[k] = "";
					}
				}
				
				// Initiates new Thread for each connection
				theSockets[j] = new MultiSocket(connection, j, names);
				theThreads[j] = new Thread(theSockets[j]);
				theThreads[j].start();
				
				while(theSockets[j].getPlayerName() == null) {
					try {
						Thread.sleep(MainHub.globalSleep);
					} catch (InterruptedException e) {}
				}
				// Checks if the username is already taken
				if(theSockets[j].isNameFree()) {
					MainHub.getController().getActivityView().addToActivityField(theSockets[j].getPlayerName() + " is now connected");
				}
				break;
			}
		}
		
		MainHub.getController().getActivityView().clearClientsField();
		for(int i = 0; i < MainHub.nbrOfClients; i++) {
			if(theSockets[i] != null) {
				if(theSockets[i].isNameFree()) {
					MainHub.getController().getActivityView().addClient(theSockets[i].getPlayerName() + (char)9 + theSockets[i].getPlayerID());
				}
			}
		}
	}

	@Override
	public void run() {
		
		while(true) {
			// Joins all players statstrings
			statString = "";
			for(int i = 0; i < MainHub.nbrOfClients; i++) {
				if(theThreads[i] != null) {
					statString = statString + theSockets[i].getPlayerStats() + "/";
					
					// Check if it loses a connection and kills then kills the controller and thread
					if(theSockets[i].isDead()) {
						if(theSockets[i].isNameFree()) {
							MainHub.getController().getActivityView().addToActivityField(theSockets[i].getPlayerName() + " has disconnected");
						}
						theSockets[i].closeConnection();
						theSockets[i] = null;
						theThreads[i] = null;
						MainHub.getController().getActivityView().clearClientsField();
						for(int j = 0; j < MainHub.nbrOfClients; j++) {
							if(theSockets[j] != null) {
								MainHub.getController().getActivityView().addClient(theSockets[j].getPlayerName() + (char)9 + theSockets[j].getPlayerID());
							}
						}
					}		
				}	
			}
			
			for(int j = 0; j < MainHub.nbrOfClients; j++) {
				if(theThreads[j] != null) {
					theSockets[j].setPlayerStats(statString);
				}
			}
			
			try {
				Thread.sleep(MainHub.globalSleep);
			} catch (InterruptedException e) {}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("connect")) {
			if(!MainHub.getController().getActivityView().isRunning()) {
				startServer();
			} else {
				stopServer();
			}
			MainHub.getController().getActivityView().changeButtonText();
		} else if(e.getActionCommand().equals("save")) {
			saveActivityLog();
		} else if(e.getActionCommand().equals("clear")) {
			if(!MainHub.getController().getActivityView().getActivity().equals("")) {
				String clearLog = mainView.showDialogBox("clearactivity");
				if(clearLog.equals("Clear log")) {
					MainHub.getController().getActivityView().clearActivityField();
				}
			}
		} else if(e.getActionCommand().equals("change")) {
			String tempPort = MainHub.getController().getSettingsView().getPortField();
			int port = -1;
			String whatChange;
			
			try {
				port = Integer.parseInt(tempPort);
			}
			catch(NumberFormatException nfe) {
				tempPort = "" + MainHub.port;
			}
			
			// Checks that it's a valid port-number
			if(port < 1024 || port > 9999 || tempPort.length() > 4) {
				whatChange = mainView.showDialogBox("error");
				MainHub.getController().getSettingsView().setPortField("" + MainHub.port);
				tempPort = "" + MainHub.port;
			} else {
				if(socketFinder.getCurrentPort() != port) {
					// Have to have a check if the server is running
					if(isRunning) {
						whatChange = mainView.showDialogBox("change");
						if(whatChange.equals("Restart now")) {
							restartServer(Integer.parseInt(tempPort));
						}
					} else {
						socketFinder.changePort(Integer.parseInt(tempPort));
						MainHub.getController().getActivityView().addToActivityField("Port changed to " + tempPort);
					}
				}
				
			}
			
		} else if(e.getActionCommand().equals("default")) {
			MainHub.getController().getSettingsView().setPortField("" + MainHub.port);
		}
	}
	
	public void startServer() {
		if(socketFinderThread.getState() == Thread.State.NEW) {
			socketFinderThread.start();
		} else {
			socketFinder.startSearching();
			socketFinderThread = null;
			socketFinderThread = new Thread(socketFinder);
			socketFinderThread.start();
		}
		isRunning = true;
	}
	
	public void stopServer() {
		socketFinder.stopSearching();
		for(int i = 0; i < MainHub.nbrOfClients; i++) {
			if(theSockets[i] != null) {
				theSockets[i].closeConnection();
			}
		}
		
		isRunning = false;
	}
	
	public void restartServer(int newPort) {
		isRunning = false;
		stopServer();
		socketFinder.changePort(newPort);
		MainHub.getController().getActivityView().changeButtonText();
		try {
			Thread.sleep(MainHub.globalSleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		startServer();
		MainHub.getController().getActivityView().changeButtonText();
		isRunning = true;
	}
	
	public void saveActivityLog() {
		String activityLog = MainHub.getController().getActivityView().getActivity();
		
		if(!activityLog.equals("")) {
			Date date = new Date();
			String logFile = MainHub.macSavepath + "/" + dateFormat.format(date).replaceAll("/", "").replaceAll(":", "") + ".txt";
			if(MainHub.osName.contains("macosx")) {
				new File(MainHub.macSavepath).mkdirs();
				try {
					FileWriter fstream = new FileWriter(logFile);
					BufferedWriter out = new BufferedWriter(fstream);
					out.write(activityLog);
					out.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
			}
			
		} else {
			mainView.showDialogBox("emptyactivity");
		}
		
	}

}
