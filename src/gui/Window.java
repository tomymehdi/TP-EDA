package gui;

import game.GameListener;
import game.Reversi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Window extends JFrame {
	
	GamePanel gPanel;
	Reversi game;
	JButton passButton;
	JButton newGameButton;

	public Window(int level, boolean pruned, boolean timed){
		GameListener listener=new GameListener(){
			public void endOfGame(int score) {
				JFrame frame = new JFrame("Game Over");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				ImageFrame panel;
				try {
					if(score<0){
						panel = new ImageFrame(ImageUtils.loadImage("./resources/YOULOOSE.png"));
						panel.setPreferredSize(new Dimension(469,600));
					}else if(score>0){
						panel = new ImageFrame(ImageUtils.loadImage("./resources/youWon.png"));
						panel.setPreferredSize(new Dimension(552,240));
					}else{
						panel = new ImageFrame(ImageUtils.loadImage("./resources/drawgame.png"));
						panel.setPreferredSize(new Dimension(425,352));
					}
					frame.getContentPane().add(panel, BorderLayout.CENTER);	
					frame.setVisible(true);
					frame.pack();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(Window.this, "There's has been an error while loading the images");
				}
			}
			
			public void enablePass() {
				passButton.setEnabled(true);
				passButton.repaint();
			}
		};
		game=new Reversi(listener, level, pruned, timed);
		
		
		//Swing
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		newGameButton=new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Window.this.game.newGame();
				repaint();
			}
		});
		add(newGameButton);
		
		gPanel = new GamePanel(game);
		add(gPanel);
		
		passButton=new JButton("pass");
		passButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Window.this.passButton.setEnabled(false);
				Window.this.game.computerTurn();
				repaint();
			}
		});
		//passButton.setEnabled(false);
		add(passButton);
		pack();
		setVisible(true);
	}

}
