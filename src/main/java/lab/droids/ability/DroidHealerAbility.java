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

public class DroidHealerAbility
{
	static String fileName = "health.png";
	final static public int NUMBERS_OF_ABILITY = 2;
	final static public String ABILITY1_DESCR = "<html>Відновлює здоров'я одному дроїду<br> від 20% максимального здоров'я</html>";
	final static public String ABILITY2_DESCR = "<html>Відновлює здоров'я усій команді від 20% здоров'я кожного дроїда<br>"
			+ "Потребує 4 енергії</html>";
	final static public String PASSIVE = "З шансом 1% може повністю вилікувати дроїда";
	
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
			image = ImageIO.read(new File(System.getProperty("user.dir") + "\\img\\"+ fileName));
			img_icon = new ImageIcon(image.getScaledInstance(IMG_SIZE, IMG_SIZE, Image.SCALE_SMOOTH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img_icon;
	}

	public static boolean simpleAbility(Droid taker, Player p_taker) {
		int CHANCE = 1;
		double PERCNET = 0.2;
		double heal;
		if(isPassiveAbility(CHANCE)) 
		{
			heal = taker.getMaxHp();
		}
		else 
		{
			heal = taker.getMaxHp() * PERCNET;
		}
		taker.Heal(heal, taker.getMaxHp());
		Logger.addInfoInLog(p_taker.getName() + ": Дрон: " + taker.toString() + " отримав " + heal + " здоров'я");
		return true;
	}

	public static boolean superAbility(Droid damager, CopyOnWriteArrayList<Droid> droids, Player p_taker) {
		int ENERGY_REQ = 4;
		if(damager.isEnoughEnergy(ENERGY_REQ)) 
		{
			damager.useEnergy(ENERGY_REQ);
			for(Droid droid : droids) 
			{
				simpleAbility(droid, p_taker);
			}
			return true;
		}
		return false;
	}
}
