package lab.gui.windows.allerts;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Allert extends JFrame
{
	final private int SCREEN_SIZE = 300;
	
	public Allert(String info) 
	{
		setSize(SCREEN_SIZE, SCREEN_SIZE/2);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
		JPanel panel = new JPanel(new BorderLayout());
		
		JLabel textInfo = new JLabel(info);
		Font font = new Font("Arial", Font.PLAIN, 18);
		textInfo.setFont(font);
		JButton okButton = new JButton("OK");
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
        	{
        		dispose();
            }
		});
		
		panel.add(textInfo, BorderLayout.CENTER);
		panel.add(okButton, BorderLayout.SOUTH);
		add(panel);
	}
}
