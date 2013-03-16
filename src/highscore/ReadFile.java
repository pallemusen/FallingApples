package highscore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
	
	private String line; 
	private static  String[][] scoreArray = new String[10][3]; 
	private String[] temp; 
	
	private File highscorelist; 

	private Scanner scanner;
	
	private int n = 0; 
	
	public ReadFile(String fileURL) {
		highscorelist = new File(fileURL);
		try {
			scanner = new Scanner(highscorelist);
			try {
				while(scanner.hasNextLine()) {
					line = scanner.nextLine();
					
					temp = line.split(",");
					
					scoreArray[n] = temp;
					n++; 
					
				}
				
				for(int i = 0; i < scoreArray.length; i++) {
					for(int b = 0; b < scoreArray[0].length; b++) {
						System.out.println((i+1)+","+(b+1)+":"+scoreArray[i][b]);
					}
				}
			} catch(Exception ex) {
				System.out.println("Couldn't read from file");
			}
		} catch (FileNotFoundException e) {
			System.out.println("File: \"highscores.txt\" wasnt found...");
		}
		
		scanner.close();
	}
	
	public static String[][] getScoreArray() {
		return scoreArray;
	}
	
	public static void setScoreArray(String[][] scores) {
		scoreArray = scores; 
	}
	
	
}
