package sshaserver.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Constants {

	/**
	 * Represents the number of allowed clients.
	 */
	public static final int nbrOfClients = 4;
	
	/**
	 * The default port
	 */
	public static final int port = 6666;
	
	/**
	 * The global sleep-time
	 */
	public static final int globalSleep = 1000;
	
	public static final String osName = System.getProperty("os.name").toLowerCase().replaceAll("\\s", "");
	
	public static final String macSavepath = "/Users/bellevik/Documents/sshaserver";
	
	public static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

}
