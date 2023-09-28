package lab.droids;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;

import lab.droids.ability.DroidTankAbility;
import lab.player.Player;

public class DroidTank extends Droid
{
	public DroidTank() 
	{
		MAX_HP = 200.0;
		this.setHealth(MAX_HP);
		this.setName("Droid Tank");
		this.setEnergy(MAX_ENERGY);
		this.setFileName("tank.png");
	}
	
	public ImageIcon getScaledImageSkill(int IMG_SIZE) 
	{
		return DroidTankAbility.getScaledImageSkill(IMG_SIZE);
	}
	
	@Override
	public void takeDamage(double damage) 
	{
		if(!DroidTankAbility.isDamageAbsorbed()) {
			super.takeDamage(damage);
		}
	}
	
	@Override
	public String getDescriptionSimpleSkill() {
		return DroidTankAbility.ABILITY1_DESCR;
	}

	@Override
	public String getDescriptionSuperSkill() {
		return DroidTankAbility.ABILITY2_DESCR;
	}

	@Override
	public boolean simpleAbility(Droid taker, Player p_taker) {
		return DroidTankAbility.simpleAbility(taker, p_taker);
	}

	@Override
	public boolean superAbility(Droid damager, CopyOnWriteArrayList<Droid> droids, Player p_taker) {
		return DroidTankAbility.superAbility(damager, droids, p_taker);
	}
}
