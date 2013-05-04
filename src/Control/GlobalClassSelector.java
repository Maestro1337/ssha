package Control;

import Model.Player;
import Model.Classes.ClassHunter;
import Model.Classes.ClassWarrior;
import Model.Classes.ClassWizard;

public class GlobalClassSelector {

	private static GlobalClassSelector myControl = null;
	private Player player = null;
	private boolean changedPlayer = false;

	public static GlobalClassSelector getController() {
	if (myControl == null) {
	   myControl = new GlobalClassSelector();
	   // prepare myController here or use setter() methods or a parameterized constructor
	   }
	   return myControl;
	}

	// make constructor private so no one except the getController() can call it
	private GlobalClassSelector() {
		player = new ClassHunter("Tester", 120, 100);
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