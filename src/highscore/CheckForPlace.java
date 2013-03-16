package highscore;

public class CheckForPlace {
	
	private String[][] scores; 
	private String name; 
	
	private int score; 
	private int time; 
	private int place;
	
	public int findPlace(String name, int score, int time, String[][] scores) {
		this.scores = scores; 
		this.score = score; 
		this.time = time;
		this.name = name; 
		place = 10; 
		
		for(int i = 9; i >= 0; i--) {
			if(score > Integer.parseInt(scores[i][1])) {
				System.out.println(score+" is higher than " + Integer.parseInt(scores[i][1]) + " on place "+i);
				place--; 
			} else {
				System.out.println(score+" is not higher than " + Integer.parseInt(scores[i][1]) + " on place "+i);
				
				if(score == Integer.parseInt(scores[i][1])) {
					System.out.println("EQUALS ON PLACE: "+i);
					if(time < Integer.parseInt(scores[i][2])) {
						place--;
					}
				}
			}
		}
		System.out.println(place);
		return place; 
	}
	
	public String[][] putIntoPlace(int place/*, String name, int score, int time*/) {
		System.out.println("Method called...");
		
		for(int n = 9; n >= place; n--) {
			for(int i = 0; i < 3; i++) {
				if(n != 0) {
					scores[n][i] = scores[n-1][i];
				} 
				
				System.out.println("Moving from ["+n+"]["+i+"] and 1 up...");
			}
		}
		
		scores[place][0] = this.name;
		scores[place][1] = Integer.toString(this.score);
		scores[place][2] = Integer.toString(this.time);
		
		for(int i = 0; i < scores.length; i++) {
			System.out.println(i+": "+scores[i][0]);
			System.out.println(i+": "+scores[i][1]);
			System.out.println(i+": "+scores[i][2]);
		}
		
		System.out.println("Returning scores...");
		return scores; 
	}
}
