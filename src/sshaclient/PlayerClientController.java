package sshaclient;

import Control.PlayerControl;
import Model.MainHub;
import Model.Player;
import Model.PlayerModel;
import Model.Obstacles.Obstacle;
import Model.Skills.Skill;
import Model.Skills.Hunter.*;
import Model.Skills.Warrior.*;
import Model.Skills.Wizard.*;

public class PlayerClientController implements PlayerControl {

	private SocketClient sc;
	private Player tp; 
	private PlayerModel model;
	
	private boolean isAlive = false;
	
	public PlayerClientController(SocketClient sc, Player tp) {
		this.sc = sc;
		this.tp = tp;
		
		model = new PlayerModel(tp);
		isAlive = true;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String tempStats;
		Skill[] tempSkills = new Skill[5];
		Skill[] realSkills;
		String currentSkill;
		int currentSkillLvl;
		
	//	boolean[] canActivateEndState = new boolean[5];
	//	boolean[] canActivateSkill = new boolean[5];
	//	for(int i=0; i<canActivateSkill.length; i++){
	//		canActivateEndState[i] = true;
	//		canActivateSkill[i] = true;
	//	}
		boolean canActivateAttack = true;
		boolean canActivateMovement = true;
		
		while(isAlive) {
			
		//	System.out.println("I " + tp.getName() + " is alive!!!!! and is a " + tp.getType());
			
			
			tempStats = sc.getPlayerStats(tp.getPlayerListIndex());
			
			// Set Player stats in lobby with info from Server
			if(MainHub.getItem(tempStats, 2).equals("lobby")) {
				tp.setTotalKills(Integer.parseInt(MainHub.getItem(tempStats, 4)));
				tp.setGold(Integer.parseInt(MainHub.getItem(tempStats, 5)));
				tp.setArmor(Double.parseDouble(MainHub.getItem(tempStats, 19)));
				tp.setEvasion(Integer.parseInt(MainHub.getItem(tempStats, 20)));
				tp.setMovementSpeed(Double.parseDouble(MainHub.getItem(tempStats, 21)));
				
				tp.setReadyState(Boolean.parseBoolean(MainHub.getItem(tempStats, 23)));
				tp.setHasClickedStartGame(Boolean.parseBoolean(MainHub.getItem(tempStats, 24)));
				
				if(tp.getPlayerListIndex() != 0) {
					MainHub.getController().setMapIndex(Integer.parseInt(MainHub.getItem(tempStats, 22)));
				}
				
				for(int i = 0; i < tempSkills.length; i++) {
					currentSkill = MainHub.getItem(tempStats, (2*(i+1)+5));
					currentSkillLvl = Integer.parseInt(MainHub.getItem(tempStats, (2*(i+1)+6)));
					switch(currentSkill) {
					case "arrowflurry":
						tempSkills[i] = new SkillArrowFlurry();
						break;
					case "barrelroll":
						tempSkills[i] = new SkillBarrelRoll();
						break;
					case "cripplingshot":
						tempSkills[i] = new SkillCripplingTrap();
						break;
					case "flamingarrow":
						tempSkills[i] = new SkillFlamingArrow();
						break;
					case "guidedarrow":
						tempSkills[i] = new SkillGuidedArrow();
						break;
					case "lifestealingarrows":
						tempSkills[i] = new SkillLifestealingArrows();
						break;
					case "passivedodge":
						tempSkills[i] = new SkillPassiveDodge();
						break;
					case "sprint":
						tempSkills[i] = new SkillSprint();
						break;
					case "stealth":
						tempSkills[i] = new SkillStealth();
						break;
					case "arrow":
						tempSkills[i] = new SkillArrow();
						break;
					case "adrenaline":
						tempSkills[i] = new SkillAdrenaline();
						break;
					case "firstaid":
						tempSkills[i] = new SkillFirstAid();
						break;
					case "grapplinghook":
						tempSkills[i] = new SkillGrapplingHook();
						break;
					case "improvedarmor":
						tempSkills[i] = new SkillImprovedArmor();
						break;
					case "increasedmovement":
						tempSkills[i] = new SkillIncreasedMovement();
						break;
					case "leapattack":
						tempSkills[i] = new SkillLeapAttack();
						break;
					case "shieldstance":
						tempSkills[i] = new SkillShieldStance();
						break;
					case "slash":
						tempSkills[i] = new SkillSlash();
						break;
					case "throwingaxe":
						tempSkills[i] = new SkillThrowingAxe();
						break;
					case "warstomp":
						tempSkills[i] = new SkillWarstomp();
						break;
					case "absorb":
						tempSkills[i] = new SkillAbsorb();
						break;
					case "blizzard":
						tempSkills[i] = new SkillBlizzard();
						break;
					case "fireball":
						tempSkills[i] = new SkillFireball();
						break;
					case "firestorm":
						tempSkills[i] = new SkillFirestorm();
						break;
					case "flamewave":
						tempSkills[i] = new SkillFlamewave();
						break;
					case "iceblock":
						tempSkills[i] = new SkillIceblock();
						break;
					case "ironcloak":
						tempSkills[i] = new SkillIroncloak();
						break;
					case "teleport":
						tempSkills[i] = new SkillTeleport();
						break;
					case "unstablemagic":
						tempSkills[i] = new SkillUnstableMagic();
						break;
					case "wandattack":
						tempSkills[i] = new SkillWandattack();
						break;
					case "noskill":
						tempSkills[i] = null;
						break;
					}
					if(tempSkills[i] != null) {
						tempSkills[i].setCurrentLvl(currentSkillLvl);
					}
				}
				
				tp.setSkillList(tempSkills);
				
			} else {
				
				tp.setHP(Integer.parseInt(MainHub.getItem(tempStats, 5)));
				tp.setCurrentActiveSkillIndex(Integer.parseInt(MainHub.getItem(tempStats, 11)));
				
				float x = Float.parseFloat(MainHub.getItem(tempStats, 3));
				float y = Float.parseFloat(MainHub.getItem(tempStats, 4));
				float checkX = Math.abs(x - tp.getX());
				float checkY = Math.abs(y - tp.getY());
				//tp.setX(checkX);
				//tp.setY(checkY);
				realSkills = tp.getSkillList();
				int skillIndex = tp.getCurrentActiveSkillIndex();
				boolean isRunning = Boolean.valueOf(MainHub.getItem(tempStats, 7));
				
				boolean isAttacking = Boolean.valueOf(MainHub.getItem(tempStats, 12));
				
				if(checkX > MainHub.syncMargin || checkY > MainHub.syncMargin) {
					tp.setX(x);
					tp.setY(y);
				}
				
				if(isRunning) {
					model.move((int)Double.parseDouble(MainHub.getItem(tempStats, 8)), (int)Double.parseDouble(MainHub.getItem(tempStats, 9)));
				}
				
				
				if(canActivateAttack && isAttacking){
					model.attack((int)Double.parseDouble(MainHub.getItem(tempStats, 13)), (int)Double.parseDouble(MainHub.getItem(tempStats, 14)));
					canActivateAttack = false;
				}else if(!isAttacking || tp.getSkillList()[skillIndex].getAttackingState()){
					canActivateAttack = true;
				}
				
				if(Integer.parseInt(MainHub.getItem(tempStats, 15)) != 0) {
					for(int i = 0; i < Integer.parseInt(MainHub.getItem(tempStats, 15)); i++) {
						MainHub.getController().getMapSelected().destroyObstacle(Integer.parseInt(MainHub.getItem(tempStats, 16+i)));
					}
				}
				
				/*
				tp.setX(Float.parseFloat(Constants.getItem(tempStats, 3)));
				tp.setY(Float.parseFloat(Constants.getItem(tempStats, 4)));
				tp.setRotation(Float.parseFloat(Constants.getItem(tempStats, 5)));
				tp.setRunningState(Boolean.valueOf(Constants.getItem(tempStats, 6)));
				tp.setStunState(Boolean.valueOf(Constants.getItem(tempStats, 7)));
				tp.setMovementSpeed(Double.parseDouble(Constants.getItem(tempStats, 8)));
				tp.setHP(Integer.parseInt(Constants.getItem(tempStats, 9)));
				
				realSkills = tp.getSkillList();
				
				//PlayerModel model = new PlayerModel(tp, new Obstacle[0]);
				for(int j = 0; j < realSkills.length; j++) {
					if(realSkills[j] != null) {
						
						boolean isAttacking = Boolean.valueOf(Constants.getItem(tempStats, (j+1)*8+7));
						
						
						if(canActivateSkill[j] && isAttacking){
							realSkills[j].setMouseXPos(Float.parseFloat(Constants.getItem(tempStats, (j+1)*8+4)));
							realSkills[j].setMouseYPos(Float.parseFloat(Constants.getItem(tempStats, (j+1)*8+5)));
						}else if(!isAttacking){
							canActivateSkill[j] = false;
						}
						
						
						realSkills[j].setAttX(Float.parseFloat(Constants.getItem(tempStats, (j+1)*8+4)));
						realSkills[j].setAttY(Float.parseFloat(Constants.getItem(tempStats, (j+1)*8+5)));
						realSkills[j].setRotation(Float.parseFloat(Constants.getItem(tempStats, (j+1)*8+6)));
						realSkills[j].setAttackingState(Boolean.valueOf(Constants.getItem(tempStats, (j+1)*8+7)));
						boolean isEndState = Boolean.valueOf(Constants.getItem(tempStats, (j+1)*8+8));
						if(canActivateEndState[j] && isEndState){
							realSkills[j].setEndstate(true);
							canActivateEndState[j] = false;
						}else if(!isEndState){
							canActivateEndState[j] = true;
						}
						realSkills[j].setXDirAtt(Float.parseFloat(Constants.getItem(tempStats, (j+1)*8+9)));
						realSkills[j].setYDirAtt(Float.parseFloat(Constants.getItem(tempStats, (j+1)*8+10)));
					}
				}
				*/
			}
			/*
			try {
				Thread.sleep(Constants.globalSleep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
		}
	}


	@Override
	public void killItWithFire() {
		System.out.println("Will terminate");
		isAlive = false;
	}


	@Override
	public void changePlayer(Player player) {
		this.tp = player;
		
	}
	
}
