package Model.StatusEffects;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Model.MainHub;
import Model.Player;
import Model.StatusEffect;
import Model.StatusEffectShell;
import Model.Items.Item;

public class StatusEffectItems extends StatusEffectShell {

	private int newDamage;
	public StatusEffectItems(Item item, int damage, double movement, double armor, int seconds) {
		//Player player, Item item, String name,int damage, double moveSpeed, double arm,  int counts, int delay
		super(null, item, "Armor", damage, movement, armor, seconds+1, 0);
		this.newDamage = damage;
	}

	@Override
	public StatusEffect createStatusEffectTo(Player newPlayer) {
		//Finding the next free space in list to add player to
				super.addPlayerGivenTo(newPlayer.getName());
				
				StatusEffect newSE;
				newSE = new StatusEffect(newPlayer, super.getSkill(), super.getName(), super.getDmgEff(), 
						super.getMoveXEff(), super.getMoveYEff(), super.getMoveSpeedEff(), 
						super.getArmEff(), super.getAttackSpeedEff(), super.getRangeEff(), super.getEvasionEff(), 
						super.hasStealth(), super.hasStun(), super.getChannel(), super.getMaxCounts(), super.getDelay());
				
				newSE.setChangeOfMainSkill(getImagesOfCurrentLevel(), newDamage);

				return newSE;
	}
	private Image[] getImagesOfCurrentLevel(){
		Image[] images = new Image[4];
		try {
			switch(MainHub.getController().getPlayers()[MainHub.getController().getActivePlayerIndex()].getType()){
			case("Hunter"):
				images[0] = new Image("res/animations/arrow/arrow.png");
				images[1] = new Image("res/animations/arrow/arrow.png");
				images[2] = new Image("res/animations/arrow/arrow.png");
				images[3] = new Image("res/animations/arrow/arrow.png");
				break;
			case("Warrior"):
				images[0] = new Image("res/animations/slash/slash1.png");
				images[1] = new Image("res/animations/slash/slash2.png");
				images[2] = new Image("res/animations/slash/slash3.png");
				images[3] = new Image("res/animations/slash/slash4.png");
				break;
			case("Wizard"):
				images[0] = new Image("res/animations/WandAttack/wandattack.png");
				images[1] = new Image("res/animations/WandAttack/wandattack.png");
				images[2] = new Image("res/animations/WandAttack/wandattack.png");
				images[3] = new Image("res/animations/WandAttack/wandattack.png");
				break;
			}
			images[0] = new Image("res/animations/slash/adrenaline_slash1.png");
			images[1] = new Image("res/animations/slash/adrenaline_slash2.png");
			images[2] = new Image("res/animations/slash/adrenaline_slash3.png");
			images[3] = new Image("res/animations/slash/adrenaline_slash4.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return images;
	}

}
