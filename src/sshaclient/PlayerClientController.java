package sshaclient;

public class PlayerClientController implements PlayerControl {

	private SocketClient sc;
	private TestPlayer tp; 

	private int playerID;
	private boolean isAlive = false;
	
	public PlayerClientController(SocketClient sc, TestPlayer tp) {
		this.sc = sc;
		this.tp = tp;
		
		isAlive = true;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(isAlive) {
			
			sc.getPlayerStats(playerID);
			
			System.out.println(tp.getName() + " is alive!");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	@Override
	public void killItWithFire() {
		isAlive = false;
	}
	
}
