package Model;


import Model.Items.Item;

public /*abstract */class StatusEffectShell implements IStatusEffectShell{
	
	
	private Player player;
	private Skill skill;
	private String name;
	
	private int dmgEff;
	private float moveXEff;
	private float moveYEff;
	private double armEff;
	private int atkSpeedEff;
	private int rangeEff;
	private double moveSpeedEff;
	private int evasionEff;
	private int maxCounts;
	private boolean hasStun;
	private boolean isChanneling;
	private boolean hasStealth;
	private String[] playersGivenTo;
	private int delay;
	
	private boolean changeModel = false;
	
	protected StatusEffectShell(Player player, Skill skill, String name,int damage, float moveX, float moveY, double moveSpeed, double arm, int attackSpeed, int range, int evasion, boolean isStealth, boolean isStun, boolean isChanneling, int counts, int delay){
		this.player = player;
		this.skill = skill;
		this.name = name;
		
		dmgEff = damage;
		moveXEff = moveX;
		moveYEff = moveY;
		armEff = arm;
		atkSpeedEff = attackSpeed;
		rangeEff = range;
		moveSpeedEff = moveSpeed;
		maxCounts = counts;
		this.delay = delay;
		hasStun = isStun;
		this.isChanneling = isChanneling;
		evasionEff = evasion;
		hasStealth = isStealth;
		
		playersGivenTo = new String[MainHub.nbrOfPlayers];
		
	}
	
	protected StatusEffectShell(Player player, Item item, String name,int damage, double moveSpeed, double arm, int counts, int delay){
		this.player = player;
		this.name = name;
		
		dmgEff = damage;
		armEff = arm;
		moveSpeedEff = moveSpeed;
		maxCounts = counts;
		this.delay = delay;
		
		playersGivenTo = new String[3];
		
	}
	
	// Getters
	protected boolean getChannel(){
		return isChanneling;
	}
	protected Player getPlayer(){
		return player;
	}
	protected Skill getSkill(){
		return skill;
	}
	protected String getName(){
		return name;
	}
	protected int getDmgEff(){
		return dmgEff;
	}
	protected float getMoveXEff(){
		return moveXEff;
	}
	protected float getMoveYEff(){
		return moveYEff;
	}
	protected double getArmEff(){
		return armEff;
	}
	protected int getAttackSpeedEff(){
		return atkSpeedEff;
	}
	protected int getRangeEff(){
		return rangeEff;
	}
	protected int getEvasionEff(){
		return evasionEff;
	}
	protected double getMoveSpeedEff(){
		return moveSpeedEff;
	}
	protected boolean hasStun(){
		return hasStun;
	}
	protected boolean hasStealth(){
		return hasStealth;
	}
	protected void setChangeModel(){
		changeModel = true;
	}
	protected boolean getChangeModel(){
		return changeModel;
	}
	protected int getMaxCounts(){
		return maxCounts;
	}
	protected int getDelay(){
		return delay;
	}
	
	// Setters
	protected void setArmor(double armor){
		armEff = armor;
	}
	
	//Misc Methods
	protected void addPlayerGivenTo(String name){
		for(int i=0; i<playersGivenTo.length; i++){
			if(playersGivenTo[i] == null){
				playersGivenTo[i] = name;
				break;
			}
		}
	}
	public boolean hasBeenGivenTo(String name){
		for(int i=0; i<playersGivenTo.length; i++){
			// Changed == to .equals
			if(playersGivenTo[i] != null && playersGivenTo[i].equals(name)){
				return true;
			}
		}
		return false;
	}
	public void resetCloning(){
		System.out.println("RESET CLONING");
		playersGivenTo = new String[MainHub.nbrOfPlayers];
	}

	@Override
	public StatusEffect createStatusEffectTo(Player toPlayer, Player fromPlayer) {
		return null;
	}
}
