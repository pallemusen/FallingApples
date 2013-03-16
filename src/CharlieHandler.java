import java.awt.Graphics;
import javax.swing.ImageIcon;


public class CharlieHandler {
	
	public static int x; 
	public static int y = 305;
	private int width = 50; 
	private int height = 80; 
	
	private boolean isPaused = false; 
	
	private ImageIcon charlie_img = new ImageIcon(getClass().getClassLoader().getResource("res/charlie.png"));
	
	public static GamePanel game = new GamePanel();
	
	public CharlieHandler() {
		x = 200; 
	}
	
	public void draw(Graphics g) {
		g.drawImage(charlie_img.getImage(), x, y, width, height, null);
	}
	
	public void isPaused() {
		isPaused = true; 
	}
	
	public void isUnpaused() {
		isPaused = false; 
	}
	
	public void moveRight() {
		if(!isPaused) {
			x += 50; 
		}
	}
	
	public void moveLeft() {
		if(!isPaused) {
			x -= 50; 
		}
	}
}
