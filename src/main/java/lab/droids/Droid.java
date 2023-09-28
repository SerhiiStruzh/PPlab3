package lab.droids;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import lab.player.Player;

public abstract class Droid 
{
	private String fileName;
	private String name;
	private double health;
	private int energy;
	final private double MIN_HP = 0;
	final static public int MAX_ENERGY = 10;
	protected double MAX_HP;
	
	public abstract String getDescriptionSimpleSkill();
	public abstract String getDescriptionSuperSkill();
	public abstract ImageIcon getScaledImageSkill(int IMG_SIZE);
	public abstract boolean simpleAbility(Droid taker, Player p_taker);
	public abstract boolean superAbility(Droid damager, CopyOnWriteArrayList<Droid> droids, Player p_taker);
	
	public String toString() 
	{
		return name + " HP: " + health + " ENG: " + energy;
	}
	
	public void Heal(double health, double max_hp) 
	{
		takeHealth(health);
		if(getHealth() >= max_hp) {
			setHealth(max_hp);
		}
	}
	
	public ImageIcon getScaledImageDroid(int IMG_SIZE) 
	{
		Image image;
		ImageIcon img_icon = null;
		try {
			image = ImageIO.read(new File(System.getProperty("user.dir") + "\\img\\" + fileName));
			img_icon = new ImageIcon(image.getScaledInstance(IMG_SIZE, IMG_SIZE, Image.SCALE_SMOOTH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img_icon;
	}
	
	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}
	
	public String getFileName() 
	{
		return fileName;
	}
	
	public double getMaxHp() 
	{
		return MAX_HP;
	}
	
	public boolean isAlive() 
	{
		return health >= MIN_HP;
	}
	
	public void takeDamage(double damage) 
	{
		health -= damage;
	}
	
	public void takeHealth(double health) 
	{
		this.health += health;
	}
	
	public void setHealth(double health) 
	{
		this.health = health;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public int getEnergy() 
	{
		return this.energy;
	}
	
	public void setEnergy(int energy) 
	{
		this.energy = energy;
	}
	
	public boolean isEnoughEnergy(int required_energy) 
	{
		return energy >= required_energy;
	}
	
	public void useEnergy(int used_energy) 
	{
		energy -= used_energy;
	}
	
	public boolean isFullEnergy() 
	{
		return energy == MAX_ENERGY;
	}
	
	public void increaseEnergy()
	{
		if(!this.isFullEnergy()) {
			energy++;
		}
	}
	
	public String getName() 
	{
		return name;
	}
	public double getHealth() 
	{
		return health;
	}
	
}
