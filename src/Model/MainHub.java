package Model;

import sshaclient.SocketClient;

import Control.PlayerControl;
import Model.Arenas.*;

public class MainHub {

	private int defaultPort = 6666;
	//public static final String hostName = "127.0.0.1";
	private String hostName = "83.248.107.3";
	//public static final String hostName = "10.0.1.8";
	
	// The maximum number of players allowed
	public static final int nbrOfPlayers = 4;
	public static final int noActionLimit = 400;
	public static final int syncFrames = 50;
	
	// Used to correct transmission errors when moving online-player
	public static final int syncMargin = 10;
	
	private boolean isMultiplayer = false;
	private static MainHub myControl = null;
	private Player[] players = new Player[nbrOfPlayers];
	private PlayerControl[] playerControllers = new PlayerControl[nbrOfPlayers];
	private Thread[] controllerThreads = new Thread[nbrOfPlayers];
	private SocketClient socketClient;
	private Thread socketThread;
	private int activePlayer = 0;
	private int mapSelected;
	private int difficultySelected;
	private Arena[] maps;
	private String playerName = "vVv-ito";


	//Singleton
	public static MainHub getController() {
	if (myControl == null) {
	   myControl = new MainHub();
	   // prepare myController here or use setter() methods or a parameterized constructor
	   }
	   return myControl;
	}

	// make constructor private so no one except the getController() can call it
	private MainHub() {
		//players[0] = new ClassWizard(getActivePlayerName(), "player", 120, 100, 0);
	//	players[0] = new Player("name", "player", "No Class", 400, 300, 1600, 0.7, 0.35, 0);
		maps = new Arena[2];
		maps[0] = new MapHazardCross();
		maps[1] = new MapSlaughterField();
		
		socketClient = new SocketClient(hostName, defaultPort);
		socketThread = new Thread(socketClient);
		socketThread.start();
	}
	
	public synchronized Player[] getPlayers(){
		return players;
	}
	public synchronized Player getPlayer(int index) {
		return players[index];
	}
	public String getActivePlayerName(){
		return playerName;
	}
	public int getActivePlayerIndex(){
		return activePlayer;
	}
	public synchronized PlayerControl[] getPlayerControllers() {
		return playerControllers;
	}
	public synchronized PlayerControl getPlayerControl(int index) {
		return playerControllers[index];
	}
	public synchronized Thread[] getControllerThreads() {
		return controllerThreads;
	}
	public synchronized Thread getControllerThread( int index) {
		return controllerThreads[index];
	}
	public SocketClient getSocketClient() {
		return socketClient;
	}
	public Arena getMapSelected(){
		return maps[mapSelected];
	}
	public int getMapIndex(){
		return mapSelected;
	}
	public int getDiffcultySelected(){
		return difficultySelected;
	}
	public boolean getMulti(){
		return isMultiplayer;
	}
	
	public synchronized void addPlayer(Player player, int index){
		System.out.println("Added player: " + player.getName() + " and he is a " + player.getType() + " with ID: " + index);
		players[index] = player;
	}
	public void setActivePlayerName(String name){
		playerName = name;
	}
	public void setActivePlayerIndex(int index){
		activePlayer = index;
	}
	public synchronized void removePlayer(int index) {
		players[index] = null;
	}
	public synchronized void resetPlayers(){
		players = new Player[nbrOfPlayers];
	}
	public synchronized void addPlayerController(PlayerControl pc, int index) {
		playerControllers[index] = pc;
	}
	public synchronized void removePlayerController(int index) {
		playerControllers[index] = null;
	}
	public synchronized void resetPlayerControllers() {
		playerControllers = new PlayerControl[nbrOfPlayers];
	}
	public synchronized void addControllerThread(Thread thread, int index) {
		controllerThreads[index] = thread;
	}
	public synchronized void removeControllerThread(int index) {
		controllerThreads[index] = null;
	}
	public synchronized void resetControllerThreads() {
		controllerThreads = new Thread[nbrOfPlayers];
	}
	public void setMapIndex(int mapIndex){
		mapSelected = mapIndex;
	}
	public void setDifficultySelected(int dif){
		difficultySelected= dif;
	}
	public void setMulti(boolean multi){
		isMultiplayer = multi;
	}
	
	public static String getItem(String str, int pos) {
		
		String[] splitString = str.split(" ");
		
		if(pos < splitString.length) {
			return splitString[pos];
		} else {
			return "nada";
		}
	}
}
