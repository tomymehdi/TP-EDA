package game;

import gui.Window;

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
	private Window window;
	
	public Menu(Window window){
		this.window=window;
		file=new JMenu("File");
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
		file.add(newGame);
		file.add(howToPlay);
		add(file);
	}
}
