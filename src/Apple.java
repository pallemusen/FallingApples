import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * @author Pallemusen
 * @description This class is used to create an object to the apple in the game. 
 * The apple will have two coordinates, x and y, and will be able to
 * draw itself, reset its coordinates and random an integer to be assigned as apple.x
 */

public class Apple {
	
	public static int x; 
	public static int y;
	
	private Random rand = new Random();
	
	private ImageIcon apple_img = new ImageIcon(getClass().getClassLoader().getResource("res/apple.png"));
	
	// coordinates for new apple
	public Apple() {
		resetApple();
	}
	
	public void resetApple() {
		y = -50; //starting coordinates
		x = setRandX()*50; 
	}
	
	public int setRandX() {
		int r = rand.nextInt(7); 
		System.out.println("Random num is: " +r);
		return r;
	}
	
	public void draw(Graphics g) {
		g.drawImage(apple_img.getImage(), x, y, 50, 50, null);
	}
}
