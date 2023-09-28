package lab.gui.windows;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import lab.droids.Droid;
import lab.gui.windows.battle.ChoosePlayersDroid;
import lab.player.Player;

public class ModeSelection extends JFrame
{
	 private static final int MODE_1VS1 = 1;
	 private static final int MODE_3VS3 = 3;
	 private static final int BUTTON_WIDTH = 100;
	 private static final int BUTTON_HEIGHT = 30;
	 private static final int SCREEN_SIZE = 200;
	
	public ModeSelection(final Player p1, final Player p2) 
	{
		setTitle("Mode");
        setSize(SCREEN_SIZE, SCREEN_SIZE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new FlowLayout());

        JButton mode1vs1Button = new JButton("1 vs 1");
        mode1vs1Button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        addButtonListener(mode1vs1Button, MODE_1VS1, p1, p2);        
        panel.add(mode1vs1Button);

        JButton mode3vs3Button = new JButton("3 vs 3");
        mode3vs3Button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        addButtonListener(mode3vs3Button, MODE_3VS3, p1, p2);
        panel.add(mode3vs3Button);

        add(panel);
        setVisible(true);
	}
	
	private void addButtonListener(JButton button, int MODE, Player p1, Player p2) {
		button.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) 
	        {
	        	setVisible(false);
        		JFrame a = new ChooseDroids(MODE, p1);
        		a.addWindowListener(new WindowAdapter() {
        		    public void windowClosed(WindowEvent e) {
        		    	a.setVisible(false);
        		    	JFrame b = new ChooseDroids(MODE, p2);
        		    	b.addWindowListener(new WindowAdapter() {
        		    		public void windowClosed(WindowEvent e) {
        		    			b.dispose();
        		    			a.dispose();
        		    			dispose();
        		    		}
        		    	});
        		    }
        		});
	        }
	    });
	}
}
