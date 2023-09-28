package lab.droids;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;

import lab.droids.ability.DroidDamagerAbility;
import lab.player.Player;

public class DroidDamager extends Droid
{
	public DroidDamager() 
	{
		MAX_HP = 100;
		this.setHealth(MAX_HP);
		this.setName("Droid Damager");
		this.setEnergy(MAX_ENERGY);
		this.setFileName("damage.png");
	}
	
	public ImageIcon getScaledImageSkill(int IMG_SIZE) 
	{
		return DroidDamagerAbility.getScaledImageSkill(IMG_SIZE);
	}
	
	@Override
	public boolean simpleAbility(Droid taker, Player p_taker) {
		return DroidDamagerAbility.simpleAbility(taker, p_taker);
	}

	@Override
	public boolean superAbility(Droid damager, CopyOnWriteArrayList<Droid> droids, Player p_taker) {
		return DroidDamagerAbility.superAbility(damager, droids, p_taker);
	}

	@Override
	public String getDescriptionSimpleSkill() {
		return DroidDamagerAbility.ABILITY1_DESCR;
	}

	@Override
	public String getDescriptionSuperSkill() {
		return DroidDamagerAbility.ABILITY2_DESCR;
	}
}
