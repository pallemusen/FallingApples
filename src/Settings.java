import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class Settings extends JFrame implements WindowListener {
	private static final long serialVersionUID = 1L;

	private JLabel heading; 
	private JLabel qualityLabel; 
	
	private JButton accept;
	  
	public static JComboBox qualityDropdown;  
	
	public Settings() {
		setFrame();
		setComponents();
		addComponents();
		
	}
	
	public void setFrame() {
		setTitle("Settings");
		setSize(400,400);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(this);
	}
	
	public void setComponents() {
		heading = new JLabel("Settings");
		heading.setFont(new Font("Arial", Font.BOLD, 40));
		heading.setBounds(10, 5, 300, 50);
		
		qualityLabel = new JLabel("Quality");
		qualityLabel.setBounds(12,60,100,25);
		
		qualityDropdown = new JComboBox(Menu.qualityOptions);
		qualityDropdown.setBounds(10,85,100,25);
		qualityDropdown.setSelectedIndex(2);
		
		
		
		accept = new JButton("Apply settings");
		accept.setBounds(10, 320, this.getWidth()-20, 50);
	}
	
	public void addComponents() {
		add(heading);
		add(qualityLabel);
		add(qualityDropdown);
		add(accept);
	}
	
	@Override
	public void windowClosing(WindowEvent arg0) {
		System.out.println("LULS");
		Main.menu_gui.setVisible(true);
		
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowDeactivated(WindowEvent arg0) {}
	@Override
	public void windowDeiconified(WindowEvent arg0) {}
	@Override
	public void windowIconified(WindowEvent arg0) {}
	@Override
	public void windowOpened(WindowEvent arg0) {}
	
}
