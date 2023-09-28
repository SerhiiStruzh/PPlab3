package lab.gui.windows.battle;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lab.droids.Droid;
import lab.droids.DroidHealer;
import lab.gui.windows.LogBattle;
import lab.gui.windows.allerts.Allert;
import lab.player.Player;

public class ChooseDroidAbility extends JFrame
{
	Droid active;
	Player p_active, p_target;
	final private int SCREEN_SIZE = 1000;
	final private int IMG_SIZE = 200;
	
	public ChooseDroidAbility(Droid active, Player p_active, Player p_target)
	{
		this.active = active;
		this.p_active = p_active;
		this.p_target = p_target;
		
		setSize(SCREEN_SIZE, SCREEN_SIZE/2 + 50);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JPanel droidIMG = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
		addDroidIMG(active, droidIMG);
		
		JPanel skillsPanel = new JPanel();
		addSkillsButton(active, skillsPanel);
		
		mainPanel.add(skillsPanel, BorderLayout.CENTER);
		mainPanel.add(droidIMG, BorderLayout.NORTH);
		add(mainPanel);
		setVisible(true);
	}
	
	private void addSkillsButton(Droid droid, JPanel panel) 
	{
		JPanel skillsPanel = new JPanel(new BorderLayout());
		
		JPanel superSkillPanel = new JPanel();
		BoxLayout layout1 = new BoxLayout(superSkillPanel, BoxLayout.Y_AXIS);
		superSkillPanel.setLayout(layout1);
		superSkillPanel.setPreferredSize(new Dimension(SCREEN_SIZE/4, SCREEN_SIZE/4));
		
		JPanel simpleSkillPanel = new JPanel();
		BoxLayout layout2 = new BoxLayout(simpleSkillPanel, BoxLayout.Y_AXIS);
		simpleSkillPanel.setLayout(layout2);
		simpleSkillPanel.setPreferredSize(new Dimension(SCREEN_SIZE/4, SCREEN_SIZE/4));

		superSkillPanel.setMaximumSize(new Dimension(100, 100));
		simpleSkillPanel.setMaximumSize(new Dimension(100, 100));
		
		JButton simpleSkillButton = new JButton(droid.getScaledImageSkill(IMG_SIZE/2));
		JButton superSkillButton = new JButton(droid.getScaledImageSkill(IMG_SIZE/2));
		simpleSkillButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		superSkillButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		simpleSkillButton.setMaximumSize(new Dimension(100, 100));
		superSkillButton.setMaximumSize(new Dimension(100, 100));
		
		JLabel simpleSkillDsrc = new JLabel(droid.getDescriptionSimpleSkill());
		JLabel superSkillDsrc = new JLabel(droid.getDescriptionSuperSkill());
		simpleSkillDsrc.setAlignmentX(Component.CENTER_ALIGNMENT);
		superSkillDsrc.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Font font = new Font("Arial", Font.PLAIN, 18);
		simpleSkillDsrc.setFont(font);
		superSkillDsrc.setFont(font);
		
		superSkillPanel.add(superSkillButton);
		superSkillPanel.add(superSkillDsrc);
		simpleSkillPanel.add(simpleSkillButton);
		simpleSkillPanel.add(simpleSkillDsrc);
		
		addListenerToSuperSkillButton(superSkillButton);
		addListenerToSimpleSkillButton(simpleSkillButton);
		
		skillsPanel.add(superSkillPanel, BorderLayout.EAST);
		skillsPanel.add(simpleSkillPanel, BorderLayout.WEST);
		
		panel.add(skillsPanel);
	}
	
	private void addListenerToSimpleSkillButton(JButton button) 
	{
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
        	{
				Player for_choice;
				if(active instanceof DroidHealer) 
				{
					for_choice = p_active;
				}else 
				{
					for_choice = p_target;
				}
				JFrame chooseDroidWindow = new ChooseOneDroid(active, for_choice, "Виберіть дроїда до якого застосуєте здібності");
				chooseDroidWindow.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						dispose();
						if(p_target.isHasDroids()) {
							new ChoosePlayersDroid(p_target, p_active, "Виберіть дроїда, яким будете ходити");
						}else {
							EndBattle(p_active);
						}
					}
				});
            }
		});
	}
	
	private void addListenerToSuperSkillButton(JButton button) 
	{
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
        	{
				CopyOnWriteArrayList<Droid> team;
				if(active instanceof DroidHealer) 
				{
					team = p_active.getDroids();
				}else 
				{
					team = p_target.getDroids();
				}
				
        		if(!active.superAbility(active, team, p_target)){
        			new Allert("      Недостатньо енергії!");
        		}else {
        			dispose();
        			if(p_target.isHasDroids()) {
    					new ChoosePlayersDroid(p_target, p_active, "Виберіть дроїда, яким будете ходити");
    				}
        			else {
        				EndBattle(p_active);
        			}
        		}
            }
		});
	}
	
	private void EndBattle(Player player) 
	{
		JFrame allert = new Allert("          Гравець " + player.getName() + " переміг");
		allert.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				new LogBattle();
			}
		});
	}
	
	private void addDroidIMG(Droid droid, JPanel panel) 
	{		
		JPanel droidStatsIMG = new JPanel();
		BoxLayout layout = new BoxLayout(droidStatsIMG, BoxLayout.Y_AXIS);
		droidStatsIMG.setLayout(layout);
		droidStatsIMG.add(new JLabel(droid.getScaledImageDroid(IMG_SIZE)));
		panel.add(droidStatsIMG);
	}
}
