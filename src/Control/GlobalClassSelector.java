package Control;

import Model.Player;
import Model.Classes.ClassWarrior;

public class GlobalClassSelector {

	private static GlobalClassSelector myControl = null;
	private Player player = null;
	private boolean changedPlayer = false;

	public static GlobalClassSelector getController() {
	if (myControl == null) {
	   myControl = new GlobalClassSelector();
	   // prepare myParty here or use setter() methods or a parameterized constructor
	   }
	   return myControl;
	}

	// make constructor private so no one except the getParty() can call it
	private GlobalClassSelector() {
		player = new ClassWarrior("Tester", 120, 100);
	}
	
	public void addPlayer(Player player){
		this.player = player;
		changedPlayer = true;
	}
	
	public boolean checkPlayerAddition(){
		if(changedPlayer){
			changedPlayer = false;
			return true;
		}
		return false;
	}
	
	public Player getPlayer(){
		return player;
	}

	
}