package highscore;

public class HighscoreHandling {
	
	public ReadFile readFile; 
	private String fileURL = getClass().getClassLoader().getResource("res/highscores.txt").getPath();
	
	public void ReadFile() {
		readFile = new ReadFile(fileURL);
	}
	
	public void WriteToFile(String[][] scores) {
		new WriteToFile(fileURL, scores);
	}
	
}
