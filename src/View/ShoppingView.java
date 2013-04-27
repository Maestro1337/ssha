package View;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Control.GlobalClassSelector;
import Control.PlayerController;
import Model.Player;


public class ShoppingView extends BasicGameState {
	
	Image playButton;
	//Wizard skillicons
	Image fireBallSkill;
	Image fireStormSkill;
	Image absorbSkill; 
	Image blizzardSkill;
	Image flameWaveSkill;
	Image iceBlockSkill;
	Image teleportSkill;
	Image ironCloakSkill;
	Image unstableMagicSkill;
	Image wandAttackSkill;
	//Hunter skillicons
	Image arrowFlurrySkill;
	Image barrelRollSkill;
	Image cripplingShotSkill;
	Image dodgeSkill;
	Image flamingArrowSkill;
	Image guidedArrowSkill;
	Image healingArrowSkill;
	Image sprintSkill;
	Image stealthSkill;
	//Warrior skillicons
	Image adrenalineSkill;
	Image leapAttackSkill;
	Image passiveMovementSkill;
	Image shieldStanceSkill;
	Image throwingAxeSkill;
	Image warStompSkill;

	public ShoppingView (int state){
		
	}
	
	public int getID(){
		return 4;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException {
		
		playButton = new Image("res/playButtons.png");
		//Init wizard skillicons
		fireBallSkill = new Image("res/fireball.png");
		fireStormSkill = new Image("res/Firestorm.png");
		absorbSkill = new Image("res/skillIcons/Absorb2.jpg");
		blizzardSkill = new Image("res/skillIcons/Blizzard.jpg");
		flameWaveSkill = new Image("res/skillIcons/FlameWave.jpg");
		iceBlockSkill = new Image("res/skillIcons/IceBlock.jpg");
		ironCloakSkill = new Image("res/skillIcons/IronCloak.jpg");
		teleportSkill = new Image("res/skillIcons/Teleport.jpg");
		unstableMagicSkill = new Image("res/skillIcons/UnstableMagic.jpg");
		wandAttackSkill = new Image("res/skillIcons/WandAttack.jpg");
		//Init hunter skillicons
		arrowFlurrySkill = new Image("res/skillIcons/ArrowFlurry.jpg");
		barrelRollSkill = new Image("res/skillIcons/BarrelRoll.jpg");
		cripplingShotSkill = new Image("res/skillIcons/CripplingShot2.jpg");
		dodgeSkill = new Image("res/skillIcons/Dodge.png");
		flamingArrowSkill = new Image("res/skillIcons/FlamingArrow.jpg");
		guidedArrowSkill = new Image("res/skillIcons/GuidedArrow.jpg");
		healingArrowSkill = new Image("res/skillIcons/HealingArrows.jpg");
		sprintSkill = new Image("res/skillIcons/Sprint.jpg");
		stealthSkill = new Image("res/skillIcons/Stealth.jpg");
		//Init warrior skillicons
		adrenalineSkill = new Image("res/skillIcons/Adrenalin.jpg");
		leapAttackSkill = new Image("res/skillIcons/LeapAttack.jpg");
		passiveMovementSkill = new Image("res/skillIcons/PassiveMovement.jpg");
		shieldStanceSkill = new Image("res/skillIcons/ShieldStance.jpg");
		throwingAxeSkill = new Image("res/skillIcons/TrowingAxe.jpg");
		warStompSkill = new Image("res/skillIcons/WarStomp.png");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException {
		g.setColor(Color.black);
		g.drawImage(playButton, 500, 650);
		// TODO Auto-generated method stub
		
		g.setColor(Color.white);

		g.drawString("1", 70, 200);
		g.drawImage(absorbSkill, 50, 225);
		g.drawString("2", 140, 200);
		g.drawImage(adrenalineSkill, 119, 225);
		g.drawString("3", 215, 200);
		g.drawImage(arrowFlurrySkill, 188, 225);
		g.drawString("4", 280, 200);
		g.drawImage(barrelRollSkill, 257, 225);
		g.drawString("5", 350, 200);
		g.drawImage(blizzardSkill, 326, 225);
		
		
		g.drawString("Off", 50, 375);
		g.drawRect(50, 400, 50, 50);
		g.drawRect(150, 400, 50, 50);
		g.drawRect(250, 400, 50, 50);
		g.drawString("Def", 150, 375);
		g.drawRect(50, 475, 50, 50);
		g.drawRect(150, 475, 50, 50);
		g.drawRect(250, 475, 50, 50);
		g.drawString("Mob", 250, 375);
		g.drawRect(50, 550, 50, 50);
		g.drawRect(150, 550, 50, 50);
		g.drawRect(250, 550, 50, 50);
		
		switch(GlobalClassSelector.getController().getPlayer().getType()){
			case "Wizard": 
				g.drawImage(fireBallSkill,500, 200);
				g.drawImage(fireStormSkill,600, 200);
				g.drawImage(flameWaveSkill,700, 200);
				g.drawImage(ironCloakSkill,500, 275);
				g.drawImage(absorbSkill,600, 275);
				g.drawImage(iceBlockSkill,700, 275);
				g.drawImage(unstableMagicSkill,500, 350);
				g.drawImage(blizzardSkill,600, 350);
				g.drawImage(teleportSkill,700, 350);
			break;
			case "Hunter":
				g.drawImage(flamingArrowSkill,500, 200);
				g.drawImage(guidedArrowSkill,600, 200);
				g.drawImage(arrowFlurrySkill,700, 200);
				g.drawImage(dodgeSkill,500, 275);
				g.drawImage(healingArrowSkill,600, 275);
				g.drawImage(stealthSkill,700, 275);
				g.drawImage(sprintSkill,500, 350);
				g.drawImage(cripplingShotSkill,600, 350);
				g.drawImage(barrelRollSkill,700, 350);
			break;
			case "Warrior":
				g.drawImage(throwingAxeSkill,500, 200);
				g.drawImage(warStompSkill,600, 200);
				g.drawImage(adrenalineSkill,700, 200);
				g.drawRect(500, 275, 64, 64); //Improved armor icon is missing
				g.drawRect(600, 275, 64, 64); //First aid icon is missing
				g.drawImage(shieldStanceSkill,700, 275);
				g.drawImage(passiveMovementSkill,500, 350);
				g.drawRect(600, 350, 64, 64); //Grappling hook icon is missing
				g.drawImage(leapAttackSkill,700, 350);
			break;
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		
		int xPos = Mouse.getX();
		int yPos = 720 - Mouse.getY();
		
		Input input = gc.getInput();
		

		if((500<xPos && xPos<750) && (650<yPos && yPos<704)){
			playButton = new Image("res/playButton_hover.png");
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				playButton = new Image("res/playButton_pressed.png");
				sbg.enterState(1);
			}
		}else{
			playButton = new Image("res/playButtons.png");
		}
	}
}

