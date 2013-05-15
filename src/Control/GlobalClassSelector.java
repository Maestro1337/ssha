package Control;

import java.util.ArrayList;

import Model.Player;
import Model.Classes.ClassHunter;
import Model.Classes.ClassWarrior;
import Model.Classes.ClassWizard;

public class GlobalClassSelector {

	private static final int nbrOfPlayers = 4;
	
	private static GlobalClassSelector myControl = null;
	private Player[] players = new Player[nbrOfPlayers];
	private PlayerControl[] playerControllers = new PlayerControl[nbrOfPlayers];
	private Thread[] controllerThreads = new Thread[nbrOfPlayers];
	private boolean changedPlayer = false;
	private int activePlayer = 0;

	//Singleton
	public static GlobalClassSelector getController() {
	if (myControl == null) {
	   myControl = new GlobalClassSelector();
	   // prepare myController here or use setter() methods or a parameterized constructor
	   }
	   return myControl;
	}

	// make constructor private so no one except the getController() can call it
	private GlobalClassSelector() {
	//	players.add(new ClassHunter("Tester", 120, 100));
		players[0] = new ClassHunter("Tester", "player", 120, 100, 0);
	}
	
	public synchronized void addPlayer(Player player, int index){
		players[index] = player;
	}
	public synchronized void removePlayer(int index) {
		players[index] = null;
	}
	public synchronized void resetPlayers(){
		players = new Player[nbrOfPlayers];
	}
	public Player[] getPlayers(){
		return players;
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
	public PlayerControl[] getPlayerControllers() {
		return playerControllers;
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
	public Thread[] getControllerThreads() {
		return controllerThreads;
	}
	
	public int getActivePlayerIndex(){
		return activePlayer;
	}
	public void setActivePlayerIndex(int index){
		activePlayer = index;
	}
}