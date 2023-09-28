package lab.gui.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import lab.logger.Logger;
import lab.player.Player;

public class LogBattle extends JFrame
{
	final public int SCREEN_SIZE = 500; 
	
	public LogBattle()
	{
		Logger.closeBufferWrite();
		
		setResizable(false);
		setLocationRelativeTo(null);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		String info = getInfoFromLogger();
		addScrollInfo(mainPanel, info);
		
		add(mainPanel);
		
		setSize((3*SCREEN_SIZE)/2, SCREEN_SIZE);
		setVisible(true);
	}
	
	private String getInfoFromLogger() 
	{
		Logger.openBufferRead();
		String text = Logger.readInfoFromLog();
		Logger.closeBufferRead();
		return text;
	}
	
	private void addScrollInfo(JPanel panel, String info) 
	{	
		JTextArea textArea = new JTextArea(info);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		Font font = new Font("Arial", Font.PLAIN, 16);
		textArea.setFont(font);
		JScrollPane scrollPane = new JScrollPane(textArea);
     	panel.add(scrollPane);
	}
}
