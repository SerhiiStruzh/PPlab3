package lab.droids;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;

import lab.droids.ability.DroidHealerAbility;
import lab.player.Player;

public class DroidHealer extends Droid
{	
	public DroidHealer() 
	{
		MAX_HP = 130;
		this.setHealth(MAX_HP);
		this.setName("Droid Healer");
		this.setEnergy(MAX_ENERGY);
		this.setFileName("heal.png");
	}
	
	public ImageIcon getScaledImageSkill(int IMG_SIZE) 
	{
		return DroidHealerAbility.getScaledImageSkill(IMG_SIZE);
	}
	
	@Override
	public boolean simpleAbility(Droid taker, Player p_taker) {
		return DroidHealerAbility.simpleAbility(taker, p_taker);
	}

	@Override
	public boolean superAbility(Droid damager, CopyOnWriteArrayList<Droid> droids, Player p_taker) {
		return DroidHealerAbility.superAbility(damager, droids, p_taker);
	}
	
	@Override
	public String getDescriptionSimpleSkill() {
		return DroidHealerAbility.ABILITY1_DESCR;
	}

	@Override
	public String getDescriptionSuperSkill() {
		return DroidHealerAbility.ABILITY2_DESCR;
	}
}
