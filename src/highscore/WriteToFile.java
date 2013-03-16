package highscore;

import java.io.File;
import java.io.FileWriter;

public class WriteToFile {
	
	/* Default highscorelist
	Pallemusen,87,96
	Palemau5,86,94
	Birdiez0r,84,3
	Asgersword,83,92
	Trucker1,78,95
	Trucker2,70,95
	Trucker3,50,95
	Trucker4,45,95
	Trucker5,43,95
	Birdiemau5,20,280
	*/
	
	private FileWriter writer; 
	
	private String outStr;
	
	public WriteToFile(String fileURL, String[][] scores) { 
		outStr = "";
		
		try {
			File highscores = new File(fileURL);
			writer = new FileWriter(highscores);
			
			for(int i = 0; i < 10; i++) {
				outStr += scores[i][0] + "," + scores[i][1] + "," + scores[i][2] + "\n";
			}
			
			System.out.println(outStr);
			writer.write(outStr);
			
			writer.close();
			
		} catch (Exception ex) {
			System.out.println("Couldn't find file...");
		}
	}
	
	
}
