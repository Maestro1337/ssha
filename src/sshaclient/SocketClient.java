package sshaclient;

import java.net.*;
import java.io.*;

public class SocketClient implements Runnable {
	
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
		
		//findConnection();
	}
	
	@Override
	public void run() {
		
		while(true) {
			
			//System.out.println(tp.isConnected());
			//System.out.println(connected);
			//System.out.println("SocketClient");
			
			if(connected) {
				
				if(tp.getMode().equals("lobby")) {
					
					
				} else if(tp.getMode().equals("arena")) {
					
					
				} else if(tp.getMode().equals("shop")) {
					
					
				}
				
				//Process nollstalls inte?
				process = process + "Sebbe " + 10 + " " + 11 + (char)13;
				sendData(process); //Server far inte heller nagot
				recieveData(); //Den fastnar har for att server inte skickar nagonting!
				
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void findConnection() {
		while(!tp.isConnected()) {
			//System.out.println("LOL");
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
			System.out.println("Connected: " + connected);
		}
		catch(IOException ioe) {
			connected = false;
			tp.setConnected(false);
			System.out.println("No contact with server");
		}
	}
	
	public synchronized void sendData(String process) {
		//System.out.println("Skickar detta: " + process);
		
		try {
			osw.write(process);
			osw.flush();
			System.out.println("FUNKA?");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public synchronized void recieveData() {
		int c = 0;
		
		try {
			System.out.println("1");
			//System.out.println(isr);
			//System.out.println(isr.read());
			System.out.println("2");
			while((c = isr.read()) != 13) {
				instr.append((char)c);
			}
			inData = instr.toString();
			System.out.println(inData);
			
			c = 0;
			instr.delete(0,instr.length());
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//System.out.println(connection.isClosed());
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
	
	public String genStatString() {
		//return name + 0 + " " + x + " " + y + "  &" + (char)13;
		return "test" + 5 + 1 + (char)13;
	}
				
}
