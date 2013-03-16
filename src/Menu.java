import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Menu extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private int button_width = 300; 
	private int button_height = 50; 
	
	public static boolean isFirst = true; // a boolean to know wether the game has been runned before
	
	private JLabel title_img; 

	public static GameWindow game; 
	
	// public static String[] difficultyOptions = {"Easy", "Medium", "Hard"};
	public static String[] qualityOptions = {"Low", "Medium", "High"};
	
	public static String quality; 
	
	// settings-gui is initialized upon start of program - it's just invisible
	private Settings settings = new Settings(); 
	
	private JButton start_game;
	private JButton highscores; 
	private JButton settings_but; 
	private JButton exit;
	
	public Menu() {
		define();
		setFrame();
		addComponents();
		setVisible(true);
	}
	
	public void define() {
		// defines title img as JLabel
		title_img = new JLabel();
		title_img.setIcon(new ImageIcon(getClass().getClassLoader().getResource("res/title_image.png"))); 
		
		
		// defines the buttons
		start_game = new JButton("Start Game");
		highscores = new JButton("Highscores");
		settings_but = new JButton("Settings");
		exit = new JButton("Exit");
		
		// adds actionlisteners to the buttons
		start_game.addActionListener(this);
		highscores.addActionListener(this);
		settings_but.addActionListener(this);
		exit.addActionListener(this);
		
		
	}
	
	public void setFrame() {
		setSize(400, 400);
		setTitle("Charlie the apple-nigger");
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		
	}
	
	// sets bounds and adds components
	public void addComponents() {
		// add title pic as jlabel
		title_img.setBounds(50,20,button_width,button_height);
		add(title_img);
		
		// add 'start game' button
		start_game.setBounds(50, 100, button_width,button_height);
		add(start_game);
		
		// add settings button
		settings_but.setBounds(50, 160, button_width,button_height);
		add(settings_but);
		
		// add highscores button
		highscores.setBounds(50, 220, button_width,button_height);
		add(highscores);
		
		// add exit button
		exit.setBounds(50, 280, button_width,button_height); 
		add(exit);
	}

	// action listener for title menu buttons
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start_game) {
			startNewGame();
			setVisible(false);
		} else if(e.getSource() == highscores) {
			System.out.println("Highscores was pressed...");
			
			// Hvorfor virker dette ikke i .jar-filen?
			Main.highscores.ReadFile();
			
			Main.highscore_gui.repaint();
			
			
			setVisible(false);
			Main.highscore_gui.fromMenu = true; 
			Main.highscore_gui.setVisible(true);
		} else if(e.getSource() == settings_but) {
			System.out.println("Settings was pressed...");
			setVisible(false);
			settings.setVisible(true);
		} else if(e.getSource() == exit) {
			System.out.println("Exit was pressed...");
			System.exit(0);
		}
		
	}
	
	public void startNewGame() {
		try {
			game = new GameWindow();
			GamePanel.frame = game; 
			quality = (String)Settings.qualityDropdown.getSelectedItem();
			System.out.println("Quality: " + quality);
			GameWindow.game_panel.setQuality(1);
		} catch(Exception ex) {
			System.out.println("Game GUI couldn't be loaded.");
		}
	}
	
}
