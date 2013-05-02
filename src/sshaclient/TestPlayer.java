package sshaclient;

public class TestPlayer {

	private String[] stats;
	private String name;
	private int id = 0;
	private String ctrlType;
	private int x = 0;
	private int y = 0;
	private int angle = 0;
	private String mode = "lobby";
	private boolean connected = false;
	private int miscData = 13;
	private int miscData2 = 37;
	
	public TestPlayer(String name, String ctrlType) {
		this.name = name;
		this.ctrlType = ctrlType;
		stats = new String[7];
	}
	
	public String[] getStats() {
		if(mode.equals("lobby")) {
			
		} else if(mode.equals("arena")) {
			
		} else {
			
		}
		
		
		return stats;
	}
	
	public void setStats(String[] stats) {
		
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return id;
	}
	
	public String getControlType() {
		return ctrlType;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public String getMode() {
		return mode;
	}
	
	public int getMisc1() {
		return miscData;
	}

	public int getMisc2() {
		return miscData2;
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setControlType(String ctrlType) {
		this.ctrlType = ctrlType;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setAngle(int angle) {
		this.angle = angle;
	}
	
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public void setMisc1(int misc) {
		this.miscData = misc;
	}
	
	public void setMisc2(int misc) {
		this.miscData2 = misc;
	}
	
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
}
