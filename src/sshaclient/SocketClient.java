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
		
		findConnection();
	}
	
	@Override
	public void run() {
		if(connected) {
			if(tp.getMode().equals("lobby")) {
				
				
			} else if(tp.getMode().equals("arena")) {
				
				
			} else if(tp.getMode().equals("shop")) {
				
				
			}
			
			process = process + "Sebbe" + 10 + 11 + (char)13;
			sendData(process);
			recieveData();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void findConnection() {
		System.out.println("SocketClient initialized");
		
		try {
			address = InetAddress.getByName(host);
			connection = new Socket(address, port);
			
			bos = new BufferedOutputStream(connection.getOutputStream());
			osw = new OutputStreamWriter(bos, "US-ASCII");
			bis = new BufferedInputStream(connection.getInputStream());
			isr = new InputStreamReader(bis, "US-ASCII");
		}
		catch(IOException ioe) {
			System.out.println("LOL");
		}
		connected = true;
	}
	
	public void sendData(String process) {
		try {
			osw.write(process);
			osw.flush();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void recieveData() {
		int c;
		
		try {
			while((c = isr.read()) != 13) {
				instr.append((char)c);
			}
			inData = instr.toString();
			System.out.println(inData);
			
			c = 0;
			instr.delete(0,instr.length());
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String genStatString() {
		//return name + 0 + " " + x + " " + y + "  &" + (char)13;
		return "test" + 5 + 1 + (char)13;
	}
				
}
