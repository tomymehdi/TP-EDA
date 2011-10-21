package game;

import gui.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
		file.add(newGame);
		add(file);
	}
}
