package lab.battle;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import lab.droids.Droid;
import lab.gui.windows.ModeSelection;
import lab.gui.windows.PlayersNames;
import lab.gui.windows.battle.ChoosePlayersDroid;
import lab.logger.Logger;
import lab.player.Player;

public class BattleGame 
{
	static Player p1 = new Player(""), p2 = new Player("");
	static PlayersNames windowPlayerNamesSelect;
	static ModeSelection windowGameModeSelect;
	static ChoosePlayersDroid windowWithPlayersDroid;
	static Droid active = null;
	
	public static void main(String args[]) 
	{
		Logger.openBufferWrite();
		windowPlayerNamesSelect = new PlayersNames(p1, p2);
		openGameModeWindow();
	}
	
	private static void startGame() 
	{
		windowWithPlayersDroid = new ChoosePlayersDroid(p1, p2, "Виберіть дроїда, яким будете ходити");
	}
	
	private static void openPlayersDroidsWindow() 
	{
		windowGameModeSelect.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				startGame();
			}
		});
	}
	
	private static void openGameModeWindow() 
	{
		windowPlayerNamesSelect.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				windowGameModeSelect = new ModeSelection(p1, p2);
				openPlayersDroidsWindow();
			}
		});
	}
	
}
