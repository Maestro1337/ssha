package Model.Skills;

import Model.StatusEffect;

public class SkillSlash extends Skill{
	
	public SkillSlash(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("Slash", 5000, 200, 0.2, 3, 0, 10, null);
	}

}
