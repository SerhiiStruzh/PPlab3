package lab.gui.windows;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import lab.player.Player;

public class PlayersNames extends JFrame
{
	Player p1, p2;
	
	public PlayersNames(Player p_1, Player p_2) 
	{
		this.p1 = p_1;
		this.p2 = p_2;
		setTitle("Names");
		JButton nextButton = new JButton("Далі!");
		JLabel name_allert_fst = new JLabel("Введіть ім'я першого гравця");
		JLabel name_allert_sec = new JLabel("Введіть ім'я другого гравця");
		final JTextField fst_player_nick = new JTextField();
        final JTextField sec_player_nick = new JTextField();
        JPanel panel = new JPanel();
        SpringLayout layout = new SpringLayout(); 
        panel.setLayout(layout);

        panel.add(name_allert_fst);
        panel.add(name_allert_sec);
        panel.add(fst_player_nick);
        panel.add(sec_player_nick);
        panel.add(nextButton);

        layout.putConstraint(SpringLayout.WEST, name_allert_fst, 45, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, name_allert_fst, 110, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, fst_player_nick, 45, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, fst_player_nick, 150, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, sec_player_nick, 45, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sec_player_nick, 50, SpringLayout.SOUTH, fst_player_nick);
        
        layout.putConstraint(SpringLayout.WEST, name_allert_sec, 45, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, name_allert_sec, -40, SpringLayout.NORTH, sec_player_nick);
        
        layout.putConstraint(SpringLayout.WEST, nextButton, 105, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.SOUTH, nextButton, -20, SpringLayout.SOUTH, panel);

        name_allert_fst.setPreferredSize(new Dimension(200, 30));
        name_allert_sec.setPreferredSize(new Dimension(200, 30));
        
        fst_player_nick.setPreferredSize(new Dimension(200, 30));
        sec_player_nick.setPreferredSize(new Dimension(200, 30));

        nextButton.addActionListener(new ActionListener() 
        {
			public void actionPerformed(ActionEvent e) 
        	{
        		if(!compareNames(fst_player_nick.getText(), sec_player_nick.getText())) 
        		{
        			p1.setName(fst_player_nick.getText());
        			p2.setName(sec_player_nick.getText());
        			dispose();
        		}
            }
        }
        );
        
        add(panel);
        setSize(300, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    private static boolean compareNames(String name1, String name2) 
    {
    	return name1.toLowerCase().compareTo(name2.toLowerCase()) == 0;
    }
}
