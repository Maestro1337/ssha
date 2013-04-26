package sshaserver.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import sshaserver.view.ServerView;

public class ClientSupervisor implements Runnable, ActionListener {

	private ServerView SV;
	private SocketFinder SF;
	private Thread sft;
	
	private MultiSocketServer[] theSockets = new MultiSocketServer[constants.nbrOfClients];
	private Thread[] theThreads = new Thread[constants.nbrOfClients];
	int count = 0;
	
	//private int Socket
	
	private String[] clientsStats = new String[constants.nbrOfClients];
	
	public ClientSupervisor(ServerView SV) {
		this.SV = SV;
		
		SF = new SocketFinder(constants.port, this, SV);
		sft = new Thread(SF);
		
		SV.addActionListener(this);
	}
	
	
	
	public void addSocket(Socket connection) {
		System.out.println("lolOLOLOLOlolol1ol1o1!!!1111one");
		theSockets[count] = new MultiSocketServer(connection, count);
		theThreads[count] = new Thread(theSockets[count]);
		theThreads[count].start();
		
		while(theSockets[count].getPlayerName() == null) {
			System.out.println("Searching");
		}
		SV.addToActivityField(theSockets[count].getPlayerName() + " is now connected");
		SV.addClient(theSockets[count].getPlayerName());
		count += 1;
	}

	@Override
	public void run() {
		
		while(true) {
			for(int i = 0; i < 4; i++) {
				System.out.println(theSockets[0]);
				if(theSockets[0] != null) {
					System.out.println("The socket is dead:" + theSockets[0].isDead());
				}
				if(theThreads[i] != null) {
					/*
					//System.out.println(theThreads[i].getState());
					if(theThreads[i].getState() == Thread.State.TERMINATED) {
						theSockets[i] = null;
						theThreads[i] = null;
						System.out.println("Avslutad inkorrekt");
					}
					*/
					
					if(theSockets[i].isDead()) {
						theSockets[i] = null;
						theThreads[i] = null;
					}
					
				}
					
					
			}
			try {
				Thread.sleep(10);
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
		
		//System.out.println("Server is running: " + SV.isRunning());
		//System.out.println("Thread has started: " + sft.isAlive());
		//System.out.println("Thread is: " + sft.getState());
		
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
			//System.out.println(sft.isInterrupted());
		}

		SV.changeButtonText();
		
	}

}
