package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Menu extends JMenuBar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenu file;
	private JMenu help;
	private Window window;
	
	public Menu(Window window){
		this.window=window;
		file=new JMenu("File");
		help=new JMenu("Help");
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Menu.this.window.getGame().newGame();
				Menu.this.window.repaint();
			}

			
			
		});
		JMenuItem howToPlay = new JMenuItem("How to play");
		howToPlay.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(Menu.this.window.getContentPane(), "You can put a chip only next to a chip that belongs to the other player\n" +
						" and if you can get one or more of the other player's chips between a chip\n of yours and the currently added.\n " +
						"Then, all the chips that are between now are yours.\n\n " +
						"The winner is the player that has more chips at the end of the game");
				
			}
		});
		JMenuItem about = new JMenuItem("About");
		about.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(Menu.this.window.getContentPane(), "Programmed by:\n-Conrado Mader Blanco\n-Tomy Mehdi\n-Federico Ramundo");
				
			}
		});
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		file.add(newGame);
		file.add(exit);
		help.add(howToPlay);
		help.add(about);
		add(file);
		add(help);
	}
}
