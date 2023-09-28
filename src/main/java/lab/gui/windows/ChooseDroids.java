package lab.gui.windows;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import lab.droids.Droid;
import lab.droids.DroidDamager;
import lab.droids.DroidHealer;
import lab.droids.DroidTank;
import lab.logger.Logger;
import lab.player.Player;

public class ChooseDroids extends JFrame
{
	private int QUANTITY;
	final private int IMG_SIZE = 200;
	final private int DROID_QTY = 3;
	
	public ChooseDroids(int quantity, Player p) 
	{		
		setTitle("Choose Droids");
		setResizable(false);
		QUANTITY = quantity;
		
		JPanel panel = new JPanel();
        SpringLayout layout = new SpringLayout(); 
        panel.setLayout(layout);
		
        Droid[] droids = {new DroidTank(), new DroidHealer(), new DroidDamager()};
        JButton[] buttons = new JButton[DROID_QTY];
        for (int i = 0; i < buttons.length; i++) 
        {
            buttons[i] = new JButton(droids[i].getScaledImageDroid(IMG_SIZE));
        }
        
        for(int i = 0; i < buttons.length; i++) 
        {
        	panel.add(buttons[i]);
        	layout.putConstraint(SpringLayout.WEST, buttons[i], 250 * i + 5, SpringLayout.WEST, panel);
        }
        
        JLabel info = new JLabel("Гравець " + p.getName() + " повинен вибрати " + QUANTITY + " дроїдів(-а)");
		Font font = new Font("Arial", Font.PLAIN, 18);
		info.setFont(font);
        info.setPreferredSize(new Dimension(500, 30));
        layout.putConstraint(SpringLayout.WEST, info, 5, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.SOUTH, info, -20, SpringLayout.SOUTH, panel);
        panel.add(info);
        
        addButtonListener(buttons[0], 0, p);
        addButtonListener(buttons[1], 1, p);
        addButtonListener(buttons[2], 2, p);
        
        add(panel);
		setSize(765, 400);
	    setLocationRelativeTo(null);
	    setVisible(true);
	}
	
	private void writeInLogPlayerDroids(Player player)
	{
		Logger.addInfoInLog("Дроїди гривця " + player.getName() + ":");
		for(Droid droid : player.getDroids()) {
			Logger.addInfoInLog(droid.toString());
		}
	}
	
	private void addButtonListener(JButton button, int buttonIndx, Player p) {
	    button.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            QUANTITY--;
	            switch(buttonIndx) 
	            {
	            case 0:
	            	p.addDroid(new DroidTank());
	            	break;
	            case 1:
	            	p.addDroid(new DroidHealer());
	            	break;
	            case 2:
	            	p.addDroid(new DroidDamager());
	            	break;
	            }
	            if (QUANTITY == 0) {
	            	writeInLogPlayerDroids(p);
	                dispose();
	            }
	        }
	    });
	}
}
