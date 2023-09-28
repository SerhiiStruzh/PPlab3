package lab.droids.ability;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import lab.droids.Droid;
import lab.logger.Logger;
import lab.player.Player;

public class DroidTankAbility
{
	final static private String fileName = "shield.png";
	final static public int NUMBERS_OF_ABILITY = 2;
	final static private double MAX_DMG = 20.0;
	final static private double MIN_DMG = 10.0;
	static Random random_dmg = new Random();	
	final static public String ABILITY2_DESCR = "<html>Наносить кожному дроїду противника від 10 до 20 урону<br>"
			+ "На 10% від нанесеного урону віднослює собі здоров'я</html>";
	final static public String ABILITY1_DESCR = "<html>Наносить ворожому дроїду від 10 до 20 одиниць урону</html>";
	final static public String PASSIVE = "З шансом 5% може поглину весь урон";
	
	public static ImageIcon getScaledImageSkill(int IMG_SIZE) 
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
	
	public static boolean isDamageAbsorbed() 
	{
		int CHANCE = 5;
		int MAX_CHANCE = 100;
		Random random = new Random();
		return random.nextInt(MAX_CHANCE) <= CHANCE;
	}

	public static boolean simpleAbility(Droid taker, Player p_taker) {
		double damage = random_dmg.nextDouble() * (MAX_DMG - MIN_DMG) + MIN_DMG;
		taker.takeDamage(damage);
		Logger.addInfoInLog(p_taker.getName() + ": Дрон: " + taker.toString() + " отримав " + damage + " урону");
		if(!taker.isAlive()) {
			Logger.addInfoInLog("Дрон: " + taker.toString() + " був знищений");
			p_taker.removeDroid(taker);
		}
		return true;
	}

	public static boolean superAbility(Droid damager, CopyOnWriteArrayList<Droid> droids, Player p_taker) {
		int ENERGY_REQ = 4;
		if(damager.isEnoughEnergy(ENERGY_REQ)) {
			damager.useEnergy(ENERGY_REQ);
			for(Droid droid_iter : droids) 
			{
				double damage = random_dmg.nextDouble() * (MAX_DMG - MIN_DMG) + MIN_DMG;
				droid_iter.takeDamage(damage);
				Logger.addInfoInLog(p_taker.getName() + ": Дрон: " + droid_iter.toString() + " отримав " + damage + " урону");
				if(!droid_iter.isAlive()) {
					Logger.addInfoInLog("Дрон: " + droid_iter.toString() + " був знищений");
					p_taker.removeDroid(droid_iter);
				}
				damager.Heal(damage*0.1, damager.getMaxHp());
				Logger.addInfoInLog("Дрон: " + damager.toString() + " отримав " + damage*0.1 + " здоров'я");
			}
			return true;
		}
		return false;
	}
}
