import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class StartPage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartPage window = new StartPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1920, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel MuteUnMuteButton = new JLabel("");
		MuteUnMuteButton.setBounds(1751, 884, 127, 133);
		frame.getContentPane().add(MuteUnMuteButton);
		
		JLabel EXITBUTTON = new JLabel("");
		EXITBUTTON.setBounds(984, 665, 391, 147);
		frame.getContentPane().add(EXITBUTTON);
		
		JLabel STARTBUTTON = new JLabel("");
		STARTBUTTON.setBounds(544, 665, 391, 147);
		frame.getContentPane().add(STARTBUTTON);
		
		JLabel STARTPAGEBG = new JLabel("");
		STARTPAGEBG.setIcon(new ImageIcon(StartPage.class.getResource("/pics/BakeItSweetBg.png")));
		STARTPAGEBG.setBounds(0, 0, 1904, 1041);
		frame.getContentPane().add(STARTPAGEBG);
	}

}
