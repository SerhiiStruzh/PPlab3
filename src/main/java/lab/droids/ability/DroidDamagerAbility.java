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

public class DroidDamagerAbility
{
	static String fileName = "bullet.png";
	final static public int NUMBERS_OF_ABILITY = 2;
	final static private double MAX_DMG = 40.0;
	final static private double MIN_DMG = 25.0;
	static Random random_dmg = new Random();
	final static public String ABILITY1_DESCR = "<html>Наносить супернику від 25 до 40 одиниць урону<br>"
			+ "З 5% шансом може знищити дрона з 1 пострілу</html>";
	final static public String ABILITY2_DESCR = "<html>Наносить усім противникам від 25 до 40 одиниць урону<br>"
			+ "Вимагає 4 енергії</html>";
	final static public String PASSIVE = "З шансом 5% може знищити противника з 1 пострілу";	
	
	public static boolean isPassiveAbility(int CHANCE) 
	{
		int MAX_CHANCE = 100;
		Random random = new Random();
		return random.nextInt(MAX_CHANCE) <= CHANCE;
	}
	
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
	
	public static boolean simpleAbility(Droid taker, Player p_taker) {
		int CHANCE = 5;
		double damage;
		if(isPassiveAbility(CHANCE)) 
		{
			damage = taker.getHealth() + 1;
		}else 
		{
			damage = random_dmg.nextDouble() * (MAX_DMG - MIN_DMG) + MIN_DMG;
		}
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
		if(damager.isEnoughEnergy(ENERGY_REQ)) 
		{
			damager.useEnergy(ENERGY_REQ);
			for(Droid droid_iter : droids) 
			{
				simpleAbility(droid_iter, p_taker);
			}
			return true;
		}
		return false;
	}
}
