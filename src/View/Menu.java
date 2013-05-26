package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.state.*;
import org.newdawn.slick.util.ResourceLoader;

import Model.MainHub;
import Model.Classes.*;
import Model.Skills.Hunter.*;
import Model.Skills.Warrior.*;
import Model.Skills.Wizard.*;





public class Menu extends BasicGameState implements ActionListener{
	/** The wav sound effect */
	private Audio wavEffect;
	
	private boolean startMusic = true;
	
	private Image backgroundImage;
	private Image startGameButton;
	private Image exitButton;
	private Image titleText1;
	private Image titleText2;
	private Image midBannerImage;
	
	public Menu (int state){
		
	}
	
	// Getters
	public int getID(){
		return 0;
	}
	
	public int getWidth(Image image){
		return image.getWidth();
	}
	
	public int getHeight(Image image){
		return image.getHeight();
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
		backgroundImage = new Image("res/miscImages/bg-gates.png");
		midBannerImage = new Image("res/miscImages/midBanner.png");
		startGameButton = new Image("res/buttons/startgame.png");
		exitButton = new Image("res/buttons/exit.png");
		titleText1 = new Image("res/miscImages/SSH.png");
		titleText2 = new Image("res/miscImages/arena.png");
		
		try {
			wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/sounds/bg-music.wav"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		openGLInitializer();
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		gc.setFullscreen(false);
		g.setColor(Color.black);
		g.drawImage(backgroundImage, 0, 0);
		g.drawImage(midBannerImage, gc.getWidth()/2 - midBannerImage.getWidth()/2, 0);

		if(startMusic){
			wavEffect.playAsSoundEffect(1.0f, 1.0f, true);
			startMusic = false;
		}
		
		g.drawImage(titleText1, gc.getWidth()/2 - titleText1.getWidth()/2, 150);
		g.drawImage(titleText2, gc.getWidth()/2 - titleText2.getWidth()/2, 200);
		g.drawImage(startGameButton, gc.getWidth()/2 - startGameButton.getWidth()/2, 325);
		g.drawImage(exitButton, gc.getWidth()/2 - exitButton.getWidth()/2, 425);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
		int xPos = Mouse.getX();
		int yPos = 720 - Mouse.getY();
		
		Input input = gc.getInput();  
		// Escape key quits the game
        if(input.isKeyDown(Input.KEY_ESCAPE)) gc.exit();
        
		
		if((580<xPos && xPos<700) && (325<yPos && yPos<370)){
			startGameButton = new Image("res/buttons/startgame_p.png");
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				sbg.enterState(3);
			}
		} else if((580<xPos && xPos<700) && (425<yPos && yPos<470)){
			exitButton = new Image("res/buttons/exit_p.png");
			if(input.isMousePressed(0)){ // 0 = leftclick, 1 = rightclick
				gc.exit();
			}
		}else{
			startGameButton = new Image("res/buttons/startgame.png");
			exitButton = new Image("res/buttons/exit.png");
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	private void openGLInitializer(){
		new ClassHunter(MainHub.getController().getActivePlayerName(), "player", 120, 100, 0);
		new ClassWarrior(MainHub.getController().getActivePlayerName(), "player", 120, 100, 0);
		new ClassWizard(MainHub.getController().getActivePlayerName(), "player", 160, 300, 0);
		
		new SkillArrow();
		new SkillArrowFlurry();
		new SkillBarrelRoll();
		new SkillCripplingTrap();
		new SkillFlamingArrow();
		new SkillGuidedArrow();
		new SkillLifestealingArrows();
		new SkillPassiveDodge();
		new SkillSprint();
		new SkillStealth();
		
		new SkillAdrenaline();
		new SkillFirstAid();
		new SkillGrapplingHook();
		new SkillImprovedArmor();
		new SkillIncreasedMovement();
		new SkillLeapAttack();
		new SkillShieldStance();
		new SkillSlash();
		new SkillThrowingAxe();
		new SkillWarstomp();
		
		new SkillAbsorb();
		new SkillBlizzard();
		new SkillFireball();
		new SkillFirestorm();
		new SkillFlamewave();
		new SkillIceblock();
		new SkillIroncloak();
		new SkillTeleport();
		new SkillUnstableMagic();
		new SkillWandattack();
	}
}
