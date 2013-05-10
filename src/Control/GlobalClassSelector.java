package Control;

import java.util.ArrayList;

import Model.Player;
import Model.Classes.ClassHunter;
import Model.Classes.ClassWarrior;
import Model.Classes.ClassWizard;

public class GlobalClassSelector {

	private static GlobalClassSelector myControl = null;
	private Player[] players = new Player[4];
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
		players[0] = new ClassHunter("Tester", 120, 100);
	}
	
	public void addPlayer(Player player, int index){
		players[index] = player;
	}
	public void resetPlayers(){
		players = new Player[4];
	}
	
	public Player[] getPlayers(){
		return players;
	}
	public int getActivePlayerIndex(){
		return activePlayer;
	}
	public void setActivePlayerIndex(int index){
		activePlayer = index;
	}
}