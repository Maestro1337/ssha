package Model.Skills;

public class SkillSuperSlowTestSkill extends Skill{
	
	public SkillSuperSlowTestSkill(){
		//String name, int cd, int range, double speed, int aoe, int cost, int damage, StatusEffect SE
		super("SuperSlowTestSkill", 1000, 800, 0.1, 3, 0, 1, null);
	}

}
