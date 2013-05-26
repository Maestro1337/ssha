package sshaclient;

import main.MainHub;
import Model.Player;
import Model.PlayerControl;
import Model.PlayerModel;
import Model.Skill;
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
		String tempStats;
		Skill[] tempSkills = new Skill[5];
		//Skill[] realSkills;
		String currentSkill;
		int currentSkillLvl;

		boolean canActivateAttack = true;
		
		while(isAlive) {
			
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
				//realSkills = tp.getSkillList();
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
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {}
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
