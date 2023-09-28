package lab.gui.windows.battle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SpringLayout;

import lab.droids.Droid;
import lab.player.Player;

public class ChoosePlayersDroid extends JFrame
{
	final private int SCREEN_SIZE = 1000;
	final private int IMG_SIZE = 200;
	Player p_a, p_d;
	String message;
	
	public ChoosePlayersDroid(Player p_attacker, Player p_defender, String message) 
	{
		this.p_a = p_attacker;
		this.p_d = p_defender;
		this.message = message;
		setSize(SCREEN_SIZE, SCREEN_SIZE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		JPanel droidsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
		addDroidButton(buttons, droidsPanel);
		
		JPanel textPanel = new JPanel();
		addTextInPanel(textPanel, mainPanel);
		
		JPanel droidStatsPanel = new JPanel();
		BoxLayout layout = new BoxLayout(droidStatsPanel, BoxLayout.Y_AXIS);
		droidStatsPanel.setLayout(layout);
		addDroidStats(droidStatsPanel);
		
		addButtonListener(buttons);
		
		droidsPanel.setPreferredSize(new Dimension(0, SCREEN_SIZE/3));
		textPanel.setPreferredSize(new Dimension(0, SCREEN_SIZE/4));
		droidStatsPanel.setPreferredSize(new Dimension(0, SCREEN_SIZE/3));
		
		mainPanel.add(textPanel, BorderLayout.SOUTH);
		mainPanel.add(droidsPanel, BorderLayout.NORTH);
		mainPanel.add(droidStatsPanel, BorderLayout.CENTER);
		add(mainPanel);
	}
	
	private void addDroidStats(JPanel droidStatsPanel) 
	{
		for(Droid droid : p_a.getDroids())
		{
			JPanel oneDroidStats = new JPanel();
			BoxLayout layout = new BoxLayout(oneDroidStats, BoxLayout.X_AXIS);
			oneDroidStats.setLayout(layout);
			addDroidIMG(droid, oneDroidStats);
			addDroidHPandENG(droid, oneDroidStats);
			droidStatsPanel.add(oneDroidStats);
		}
	}
	
	private void addDroidHPandENG(Droid droid, JPanel droidStats) 
	{
		JPanel oneDroidStats = new JPanel();
		BoxLayout layout = new BoxLayout(oneDroidStats, BoxLayout.Y_AXIS);
		oneDroidStats.setLayout(layout);
		JProgressBar ENGprogressBar = new JProgressBar(0, Droid.MAX_ENERGY);
		JProgressBar HPprogressBar = new JProgressBar(0, (int) droid.getMaxHp());
		ENGprogressBar.setForeground(Color.BLUE);
		HPprogressBar.setForeground(Color.RED);
		ENGprogressBar.setValue(droid.getEnergy());
		HPprogressBar.setValue((int) droid.getHealth());
		ENGprogressBar.setMaximumSize(new Dimension(200, 20)); 
		HPprogressBar.setMaximumSize(new Dimension(200, 20));
		ENGprogressBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		HPprogressBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		oneDroidStats.add(HPprogressBar);
		oneDroidStats.add(ENGprogressBar);
		droidStats.add(oneDroidStats);
	}
	
	private void addDroidIMG(Droid droid, JPanel droidStats) 
	{
		JPanel droidStatsIMG = new JPanel();
		BoxLayout layout = new BoxLayout(droidStatsIMG, BoxLayout.Y_AXIS);
		droidStatsIMG.setLayout(layout);
		droidStatsIMG.add(new JLabel(droid.getScaledImageDroid(IMG_SIZE/2)));
		droidStats.add(droidStatsIMG);
	}
	
	private void addButtonListener(ArrayList<JButton> buttons) 
	{
		
		for(int i = 0; i < buttons.size(); i++) {
			final int index = i;
			buttons.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
	        	{
					dispose();
	        		new ChooseDroidAbility(p_a.getDroid(index), p_a, p_d);
	            }
			});
		}
	}
	
	private void addTextInPanel(JPanel textPanel, JPanel mainPanel) 
	{
		SpringLayout layout = new SpringLayout(); 
		textPanel.setLayout(layout);
		JLabel text = new JLabel(p_a.getName()  + ", " + message);
		text.setMaximumSize(new Dimension(200,200));
		Font font = new Font("Arial", Font.PLAIN, 18);
		text.setFont(font);
		textPanel.add(text);
		layout.putConstraint(SpringLayout.WEST, text, 100, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.NORTH, text, 110, SpringLayout.NORTH, mainPanel);
	}
	
	private void addDroidButton(ArrayList<JButton> buttons, JPanel droidsPanel) 
	{
		for(Droid players_droid : p_a.getDroids()) 
		{
			buttons.add(new JButton(players_droid.getScaledImageDroid(IMG_SIZE)));
		}
		for(JButton button : buttons) 
		{
			droidsPanel.add(button);
		}
	}
}
