import java.awt.Graphics;
import javax.swing.ImageIcon;


public class HealthHandler {
	
	private ImageIcon heart; 
	private ImageIcon heart_dead; 
	private int heart_width = 35; 
	
	// sets up imageIcons
	public HealthHandler() {
		heart = new ImageIcon(getClass().getClassLoader().getResource("res/heart.png"));
		heart_dead = new ImageIcon(getClass().getClassLoader().getResource("res/heart_dead.png"));
	}
	
	// draws x red hearts and y grey where x+y = 3
	public void draw(Graphics g, int health) {
		if(health == 3) {
			g.drawImage(heart.getImage(), 250, 390, heart_width, heart_width, null);
			g.drawImage(heart.getImage(), 290, 390, heart_width, heart_width, null);
			g.drawImage(heart.getImage(), 330, 390, heart_width, heart_width, null);
		} else if (health == 2) {
			g.drawImage(heart.getImage(), 250, 390, heart_width, heart_width, null);
			g.drawImage(heart.getImage(), 290, 390, heart_width, heart_width, null);
			g.drawImage(heart_dead.getImage(), 330, 390, heart_width, heart_width, null);
		} else if (health == 1) {
			g.drawImage(heart.getImage(), 250, 390, heart_width, heart_width, null);
			g.drawImage(heart_dead.getImage(), 290, 390, heart_width, heart_width, null);
			g.drawImage(heart_dead.getImage(), 330, 390, heart_width, heart_width, null);
		} else if (health == 0) {
			g.drawImage(heart_dead.getImage(), 250, 390, heart_width, heart_width, null);
			g.drawImage(heart_dead.getImage(), 290, 390, heart_width, heart_width, null);
			g.drawImage(heart_dead.getImage(), 330, 390, heart_width, heart_width, null);
		}
		
	}
}
