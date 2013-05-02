package sshaserver.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import sshaserver.view.ServerView;

public class ClientSupervisor implements Runnable, ActionListener {

	private String statString;
	private ServerView SV;
	private SocketFinder SF;
	private Thread sft;
	
	private MultiSocketServer[] theSockets = new MultiSocketServer[constants.nbrOfClients];
	private Thread[] theThreads = new Thread[constants.nbrOfClients];
	
	//private int Socket
	
	private String[] clientsStats = new String[constants.nbrOfClients];
	
	public ClientSupervisor(ServerView SV) {
		this.SV = SV;
		
		SF = new SocketFinder(constants.port, this, SV);
		sft = new Thread(SF);
		
		SV.addActionListener(this);
	}
	
	
	
	public synchronized void addSocket(Socket connection) {
		
		for(int j = 0; j < constants.nbrOfClients; j++) {
			if(theSockets[j] == null) {
				theSockets[j] = new MultiSocketServer(connection, j);
				theThreads[j] = new Thread(theSockets[j]);
				theThreads[j].start();
				
				while(theSockets[j].getPlayerName() == null) {
					try {
						Thread.sleep(1000);
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
				Thread.sleep(1000);
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
		
		if(!SV.isRunning()) {
			if(sft.getState() == Thread.State.NEW) {
				sft.start();
			} else {
				SF.startSearching();
				sft = null;
				sft = new Thread(SF);
				sft.start();
			}
		
		} else {
			SF.stopSearching();
			for(int i = 0; i < constants.nbrOfClients; i++) {
				if(theSockets[i] != null) {
					theSockets[i].closeConnection();
				}
			}
			//System.out.println(sft.isInterrupted());
		}

		SV.changeButtonText();
		
	}

}
