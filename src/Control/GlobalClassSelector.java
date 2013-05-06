package Control;

import java.util.ArrayList;

import Model.Player;
import Model.Classes.ClassHunter;
import Model.Classes.ClassWarrior;
import Model.Classes.ClassWizard;

public class GlobalClassSelector {

	private static GlobalClassSelector myControl = null;
	private ArrayList<Player> players = new ArrayList<Player>();
	private boolean changedPlayer = false;
	private int activePlayer = 0;

	public static GlobalClassSelector getController() {
	if (myControl == null) {
	   myControl = new GlobalClassSelector();
	   // prepare myController here or use setter() methods or a parameterized constructor
	   }
	   return myControl;
	}

	// make constructor private so no one except the getController() can call it
	private GlobalClassSelector() {
		players.add(new ClassHunter("Tester", 120, 100));
	}
	
	public void addPlayer(Player player){
		players.add(player);
	}
	public void resetPlayers(){
		players = new ArrayList<Player>();
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	public int getActivePlayerIndex(){
		return activePlayer;
	}
}