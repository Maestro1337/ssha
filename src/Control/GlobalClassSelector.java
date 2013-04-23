package Control;

import Model.Player;
import Model.Classes.ClassWarrior;

public class GlobalClassSelector {

	private static GlobalClassSelector myControl = null;
	private Player player = null;

	public static GlobalClassSelector getController() {
	if (myControl == null) {
	   myControl = new GlobalClassSelector();
	   // prepare myParty here or use setter() methods or a parameterized constructor
	   }
	   return myControl;
	}

	// make constructor private so no one except the getParty() can call it
	private GlobalClassSelector() {
		
	}
	
	public void activatePlayer(Player player, String type){
		this.player = player;
	}
	
	public String getPlayer(){
		return player;
	}

	
}