package sshaclient;

import java.net.*;
import java.io.*;

public class SocketClient implements Runnable {
	
	private String playerStats;
	private TestPlayer tp;
	
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
	
	public SocketClient(String host, int port, String name, TestPlayer tp) {
		this.tp = tp;
		
		this.host = host;
		this.port = port;
		
		instr = new StringBuffer();
	}
	
	@Override
	public void run() {
		
		while(true) {
			
			if(connected) {
				
				process = tp.getName() + " " + tp.getID() + " " + tp.getMode();
				
				if(tp.getMode().equals("lobby")) {
					process = process + " " + tp.getMisc1() + " " + tp.getMisc2();
				} else {
					process = process + " " + tp.getX() + " " + tp.getY() + " " + tp.getAngle();	
				}
				
				process = process + " " + (char)13;
				sendData(process);
				recieveData();
				
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			//System.out.println(tp.isConnected());
		}
	}
	
	public void findConnection() {
		while(!tp.isConnected()) {
			//System.out.println("LOL");
			try {
				Thread.sleep(1000);
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
			while((c = isr.read()) != 13 && instr.length() < 500) {
				instr.append((char)c);
			}
			inData = instr.toString();
			System.out.println(inData);
			System.out.println(inData.substring(0, inData.indexOf(32)));
			
			if(inData.substring(0, inData.indexOf(32)).equals("Connected")) {
				//System.out.println(inData.substring(inData.indexOf(32) + 1, inData.length()));
				tp.setId(Integer.parseInt(inData.substring(inData.indexOf(32) + 1, inData.length())));
				System.out.println(tp.getName() + "'s ID is: " + tp.getID());
			} else {
				splitData(inData);
			}
			
			c = 0;
			instr.delete(0,instr.length());
		
			if(inData.length() >= 499) {
				connected = false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
				connected = false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	/**
	 * A method for splitting up playerData according to their ID.
	 * @param String Containts data of 1 or more players including this client's.
	 */
	public void splitData(String data) {
		
	}
	
	public String getPlayerStats() {
		
		
		return "test";
	}
				
}
