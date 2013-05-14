package sshaclient;

public interface PlayerControl extends Runnable {
	
	/**
	 * Interface used by the different controllers; User, AI and Server
	 * @author bellevik
	 *
	 */
	public void killItWithFire();
}
