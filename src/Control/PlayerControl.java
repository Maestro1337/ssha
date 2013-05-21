package Control;

import Model.Player;

/**
 * Interface used by the different controllers; User, AI and Server
 * @author bellevik
 *
 */
public interface PlayerControl extends Runnable {

	public void killItWithFire();
	public void changePlayer(Player player);
}
