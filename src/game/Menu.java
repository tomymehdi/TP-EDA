package game;

import gui.Window;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar{
	
	JMenu file;
	Window window;
	
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
		setVisible(true);
	}
}
