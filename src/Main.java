import highscore.CheckForPlace;
import highscore.HighscoreHandling;

public class Main {
	public static Menu menu_gui;
	public static HighscoreHandling highscores; 
	public static HighscoreGUI highscore_gui;
	public static CheckForPlace check;
	
	public static void main(String[] args) {
		menu_gui = new Menu();
		highscores = new HighscoreHandling();
		highscore_gui = new HighscoreGUI();
		check = new CheckForPlace();
		
		highscores.ReadFile();
	}
}
