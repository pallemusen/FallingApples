import highscore.ReadFile;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener, Runnable {
	private static final long serialVersionUID = 1L;
	
	public static CharlieHandler charlie = new CharlieHandler();
	public static Random rand = new Random();
	public static Apple my_apple;
	public static GameWindow frame;
	public Thread apple_thread = new Thread(this);	// a thread instance of this very class
	public HealthHandler health_handler = new HealthHandler();
	
	public Counting count = new Counting();
	public Thread count_thread = new Thread(count); 
	
	private int sleep_time = 5; 
	private int score = 0; 
	private int health = 3;
	private int deltaY;
	
	private ImageIcon background_img = new ImageIcon(getClass().getClassLoader().getResource("res/background_img.png"));
	
	private boolean isRunning = false; 
	private boolean isPaused = false; 
	public static boolean gameEnded = false; 
	
	public GamePanel() {
		setFocusable(true);
		addKeyListener(this);
	}

	public void define() {
		 my_apple = new Apple();
		 count.resetCount(); 
	}
		 
	// is used to change difficulty by lowering game-Thread's sleep time
	public void setDifficulty(int score) {
		if(score == 10) {
			setQuality(2);
		} else if(score == 25) {
			setQuality(3);
		} else if(score == 50) {
			setQuality(4); 
		} else if(score == 80) {
			setQuality(5); 
		}
	}
	
	@SuppressWarnings("deprecation")
	public void windowClose() {
		apple_thread.stop();
		count_thread.stop();
		isRunning = false; 
	}
	
	public void startGame() {
		apple_thread.start();
		count_thread.start();
		isRunning = true; 
		gameEnded = false; 
	}
	
	@SuppressWarnings("deprecation")
	public void endGame() {
		System.out.println("endGame() loaded");
		count_thread.stop();
		isRunning = false; 
		my_apple.resetApple();
		count.isPaused();
		charlie.isPaused();
		gameEnded = true;
		System.out.println("End of endGame()");
	}
	
	public void paintComponent(Graphics g) {
		//background image
		g.drawImage(background_img.getImage(), 0, 0, null);
		
		// score and time data in big, white letters
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString(""+score, 120, 425);
		g.drawString(""+count.getSeconds(), 120, 465);
		
		charlie.draw(g);
		
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("Press 'P' to pause!", 200, 450); 
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		
		// if game isn't running, draw instructional string. Else, draw the apple. 
		if(!isRunning) {
			g.drawString("Press space to start the game!", 40, 60);
		} else {
			my_apple.draw(g);
		}
		
		health_handler.draw(g, health);
		
		if(isPaused && !gameEnded) {
			g.drawString("Press 'P' to resume!", 40, 100);
			g.drawString("Press 'R' to restart!", 40, 120);
			g.drawString("Press 'Q' to exit to main menu", 40, 140);
			g.drawString("Press 'W' to Exit game", 40, 160);
			
		}
		
		if(gameEnded) {
			g.drawString("Press 'R' to restart!", 40, 120);
			g.drawString("Press 'Q' to exit to main menu", 40, 140);
			g.drawString("Press 'W' to Exit game", 40, 160);
			
			g.drawString("Game has ended.", 40, 200);
			g.drawString("You scored: " + score + " points", 40, 225);
			g.drawString("On time: " + count.getSeconds() + " seconds", 40, 250); 
		}
	}

	//------------- APPLE HANDLER ------------//
	// loop to manage the apple falling from the sky
	@SuppressWarnings({ "static-access", "deprecation" })
	@Override
	public void run() {
		// if the player has HP left, the loop will be running 
		while(health > 0) {
			// a loop that moves the apple down 400px, where it dies
			for(int i = 1; i < 400/deltaY; i++) {
				repaint();
				my_apple.y += deltaY; // moves apple 1px at a time
				try {
					Thread.sleep(sleep_time);	// moves apple 1px every 5 ms = 200 times/min --> 200px/min
				} catch (InterruptedException e) {
					System.out.println("Thread error. ");
				}
				
				// checks if the apple is collected by charlie
				if(my_apple.y > 260) {
					if(my_apple.x == charlie.x) {
						System.out.println("Apple is collected.");
						score++; 
						while(i < 400) {
							i += 400;	// makes i > 400, as the loop will break 
							health++; // gets subtracted later
						}
					}
				}
			}
			// when the loop ends, the apple is considered either dead or collected, meaning that it has to be reset
			System.out.println("Apple is to be terminated");
			// decrease 1 from health - if apple is collected, 1 is added to health so that this gets evened out
			health--; 
			my_apple.resetApple();
			
			System.out.println("Health: "+health);
			
			setDifficulty(score);
		}
		
		// player has lost all HP, and game will end and pause-menu will be brought up
		System.out.println("-------------------YOU LOST---------------------");
		count_thread.stop();
		isPaused = true; 
		gameEnded = true; 
		repaint();
		
		if(Main.check.findPlace("Pallemuzz", score, count.getSeconds(), ReadFile.getScoreArray()) < 10) {
			System.out.println("Highscore achieved. Waiting for input: ");
			
			String name = (String)JOptionPane.showInputDialog("Congratulations! \nYou made it for the top-10s! \nPlease Enter your name: ");
			
			ReadFile.setScoreArray(Main.check.putIntoPlace(Main.check.findPlace(name, score, count.getSeconds(), ReadFile.getScoreArray())));
			
			Main.highscores.WriteToFile(ReadFile.getScoreArray());
			
			Main.highscore_gui.fromMenu = false; 
			Main.highscore_gui.setVisible(true);
		}
	}
	
	public void setQuality(int level) {
		if(Menu.quality.equals("High")) {
			System.out.println("### HIGH QUALITY ENABLED ###");
			deltaY = 1; //1px
			if(level == 1) {
				sleep_time = 5; //5 ms
			} else if(level == 2) {
				sleep_time = 4; 
			} else if(level == 3) {
				sleep_time = 3;
			} else if(level == 4) {
				sleep_time = 2;
			} else if(level == 5) {
				sleep_time = 1; 
			}
		} else if(Menu.quality.equals("Medium")) {
			System.out.println("### MEDIUM QUALITY ENABLED ###");
			deltaY = 5; //5px at a time (apple Y-movage)
			if(level == 1) {
				sleep_time = 25; //5 ms
			} else if(level == 2) {
				sleep_time = 20; 
			} else if(level == 3) {
				sleep_time = 15;
			} else if(level == 4) {
				sleep_time = 10;
			} else if(level == 5) {
				sleep_time = 5; 
			}
		} else if(Menu.quality.equals("Low")) {
			System.out.println("### LOW QUALITY ENABLED ###");
			deltaY = 20; //5px at a time (apple Y-movage)
			if(level == 1) {
				sleep_time = 100; //5 ms
			} else if(level == 2) {
				sleep_time = 80; 
			} else if(level == 3) {
				sleep_time = 60;
			} else if(level == 4) {
				sleep_time = 40;
			} else if(level == 5) {
				sleep_time = 20; 
			}
		}
	}
	
	// resets the game by disposing frame and starting it again
	public static void resetGame() {
		gameEnded = false; 
		frame.dispose();
		charlie.isUnpaused();
		Main.menu_gui.startNewGame();
		System.out.println("-------------------Game has ben restarted");
	}
	
	@SuppressWarnings("deprecation")
	public void pauseGame() {
		count_thread.suspend();
		apple_thread.suspend(); 
		count.isPaused();
		charlie.isPaused();
		isPaused = true; 
	}
	
	@SuppressWarnings("deprecation")
	public void resumeGame() {
		count_thread.resume();
		apple_thread.resume(); 
		count.isUnpaused();
		charlie.isUnpaused();
		isPaused = false; 
	}
	
	// keylistener to control the movement of Charlie, pausing and debug-buttons
	@SuppressWarnings("static-access")
	@Override
	public void keyPressed(KeyEvent e) {
		// if charlie isn't all the way to the left, move him and repaint 
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			if(charlie.x > 0) {
				charlie.moveLeft();
				repaint();
			}
		// same thing - just right
		} else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			if(charlie.x < 350) {
				charlie.moveRight();
				repaint();
			}
		} else if(e.getKeyCode() == KeyEvent.VK_SPACE && !isRunning) {
			define();
			startGame();
			count.isUnpaused();
			repaint();
			
			// unpause if paused
			resumeGame();
			
		} else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			// a debugging function that displays the current coordinates of both charlie and the apple
			System.out.println("Apple_x:" + my_apple.x);
			System.out.println("Apple_y:" + my_apple.y);
			System.out.println("Charlie_x:" + charlie.x);
			System.out.println("Charlie_y:" + charlie.y);
		} else if(e.getKeyCode() == KeyEvent.VK_P) {
			if(!gameEnded) {
				if(isPaused) {
					resumeGame();
				} else if(!isPaused) {
					pauseGame();
				}
				repaint();
			}
		} else if(e.getKeyCode() == KeyEvent.VK_Q) {
			if(isPaused) {
				frame.dispose();
				Main.menu_gui.setVisible(true);
				charlie.isUnpaused();
				gameEnded = false; 
			}
		} else if(e.getKeyCode() == KeyEvent.VK_W) {
			if(isPaused) {
				System.exit(0);
			}
		} else if(e.getKeyCode() == KeyEvent.VK_R) {
			if(isPaused) {
				resetGame(); 
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {	
	}
	
}
