import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class HighscoreGUI extends JFrame implements WindowListener {
	private static final long serialVersionUID = 1L;
	
	public boolean fromMenu; 
	
	public HighscoreGUI() {
		setFrame();
		add(new HighscorePanel());
	}
	
	public void setFrame() {
		setTitle("Charlie the apple-nigger");
		setSize(400, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		addWindowListener(this);
	}

	public void windowClosing(WindowEvent arg0) {
		if(fromMenu) {
			Main.menu_gui.setVisible(true);
		}
		
		System.out.println("Window was closed");
	}
	
	public void windowOpened(WindowEvent arg0) {
		//Main.highscores.ReadFile();
		//repaint();
	}
	
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
}
