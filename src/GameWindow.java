import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;


public class GameWindow extends JFrame implements WindowListener {
	private static final long serialVersionUID = 1L;

	public static GamePanel game_panel;
	
	public GameWindow() {
		setFrame();
		addComponents();
		
		setVisible(true);
	}
	
	public void setFrame() {
		setSize(400, 500);
		setTitle("Charlie the apple-nigger");
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		addWindowListener(this);
	}
	
	public void addComponents() {
		game_panel = new GamePanel();
		game_panel.setBackground(Color.CYAN);
		add(game_panel);
	}
	
	// ---------------- WINDOW LISTENER -------------------- //	
	public void windowClosing(WindowEvent arg0) {
		Main.menu_gui.setVisible(true);
		game_panel.windowClose();
		System.out.println("Window was closed");
	}

	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
