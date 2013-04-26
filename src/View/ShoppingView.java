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


public class ShoppingView extends BasicGameState {
	
	Image playButton;
	Image absorbSkill;
	Image adrenalineSkill;
	Image arrowFlurrySkill;
	Image barrelRollSkill;
	Image blizzardSkill;
	Image cripplingShotSkill;
	Image dodgeSkill;
	Image flameWaveSkill;
	Image flamingArrowSkill;
	Image guidedArrowSkill;
	Image healingArrowSkill;
	Image iceBlockSkill;
	Image ironCloakSkill;
	Image leapAttackSkill;
	Image passiveMovementSkill;
	Image shieldStanceSkill;
	Image sprintSkill;
	Image stealthSkill;
	Image teleportSkill;
	Image throwingAxeSkill;
	Image unstableMagicSkill;
	Image wandAttackSkill;
	Image warStompSkill;
	


	public ShoppingView (int state){
		
	}
	
	public int getID(){
		return 4;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)throws SlickException {
		
		playButton = new Image("res/playButtons.png");
		absorbSkill = new Image("res/skillIcons/Absorb2.jpg");
		adrenalineSkill = new Image("res/skillIcons/Adrenalin.jpg");
		arrowFlurrySkill = new Image("res/skillIcons/ArrowFlurry.jpg");
		barrelRollSkill = new Image("res/skillIcons/BarrelRoll.jpg");
		blizzardSkill = new Image("res/skillIcons/Blizzard.jpg");
		cripplingShotSkill = new Image("res/skillIcons/CripplingShot2.jpg");
		dodgeSkill = new Image("res/skillIcons/Dodge.png");
		flameWaveSkill = new Image("res/skillIcons/FlameWave.jpg");
		flamingArrowSkill = new Image("res/skillIcons/FlamingArrow.jpg");
		guidedArrowSkill = new Image("res/skillIcons/GuidedArrow.jpg");
		healingArrowSkill = new Image("res/skillIcons/HealingArrows.jpg");
		iceBlockSkill = new Image("res/skillIcons/IceBlock.jpg");
		ironCloakSkill = new Image("res/skillIcons/IronCloak.jpg");
		leapAttackSkill = new Image("res/skillIcons/LeapAttack.jpg");
		passiveMovementSkill = new Image("res/skillIcons/PassiveMovement.jpg");
		shieldStanceSkill = new Image("res/skillIcons/ShieldStance.jpg");
		sprintSkill = new Image("res/skillIcons/Sprint.jpg");
		stealthSkill = new Image("res/skillIcons/Stealth.jpg");
		teleportSkill = new Image("res/skillIcons/Teleport.jpg");
		throwingAxeSkill = new Image("res/skillIcons/TrowingAxe.jpg");
		unstableMagicSkill = new Image("res/skillIcons/UnstableMagic.jpg");
		wandAttackSkill = new Image("res/skillIcons/WandAttack.jpg");
		warStompSkill = new Image("res/skillIcons/WarStomp.png");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException {
		g.setColor(Color.black);
		g.drawImage(playButton, 500, 300);
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
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		
		int xPos = Mouse.getX();
		int yPos = 720 - Mouse.getY();
		
		Input input = gc.getInput();
		
	
		if((500<xPos && xPos<750) && (300<yPos && yPos<354)){
			playButton = new Image("res/playButton_hover.png");
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				playButton = new Image("res/playButton_pressed.png");
				sbg.enterState(1);
			}
		
		
		}
	}
}

