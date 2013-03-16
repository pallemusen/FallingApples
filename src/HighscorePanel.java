import highscore.ReadFile;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;


public class HighscorePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private int width_name = 180;
	private int width_score = 90; 
	private int width_time = 90; 
	private int height = 35; 
	
	private String[][] scores; 
	
	public HighscorePanel() {
		
	}
	
	public void paintComponent(Graphics g) {
		// title label "Highscores"
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString("Highscores", 90, 50);
		
		// reads highscores.txt and sets it as scores (an array)
		Main.highscores.ReadFile();
		scores = ReadFile.getScoreArray();	// is used to access highscore data from
		
		drawTable(g);
		drawNumbers(g);
		drawNames(g);
		drawScores(g);
		drawTime(g);
	}
	
	private void drawTable(Graphics g) {
		// bold font used for column names
		g.setFont(new Font("Arial", Font.BOLD, 20));
		
		g.drawString("#", 30, 95);
		for(int y = 100; y < (80+height*10); y += height) {
			g.drawRect(20, y, 30, height);
		}
		
		g.drawString("Name", 50, 95);
		for(int y = 100; y < (80+height*10); y += height) {
			g.drawRect(20, y, width_name, height);
		}
		
		g.drawString("Score", 200, 95);
		for(int y = 100; y < (80+height*10); y += height) {
			g.drawRect(200, y, width_score, height);
		}
		
		g.drawString("Time", 290, 95);
		for(int y = 100; y < (80+height*10); y += height) {
			g.drawRect(290, y, width_time, height);
		}
		
		// plain font used for table-data 
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		
	}
	
	private void drawNumbers(Graphics g) {
		int n = 1; 
		for(int y = 100; y < (80+height*10); y += height) {
			g.drawString(""+n, 25, y+height/2+10);
			n++; 
		}
	}
	
	private void drawNames(Graphics g) {
		int n = 0;
		for(int y = 100; y < (80+height*10); y += height) {
				g.drawString(scores[n][0], 55, y+height/2+10);
				n++;
		}
	}
	
	private void drawScores(Graphics g) {
		int n = 0;
		for(int y = 100; y < (80+height*10); y += height) {
				g.drawString(scores[n][1], 210, y+height/2+10);
				n++;
		}
	}
	
	private void drawTime(Graphics g) {
		int n = 0;
		for(int y = 100; y < (80+height*10); y += height) {
				g.drawString(scores[n][2], 300, y+height/2+10);
				n++;
		}
	}
}
