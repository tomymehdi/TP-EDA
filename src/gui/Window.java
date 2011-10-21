package gui;

import game.Board;
import game.GameListener;
import game.Menu;
import game.Reversi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Window extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GamePanel gPanel;
	private Reversi game;
	private JButton passButton;
	private Menu menu;
	private JLabel status;

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
				Window.this.passButton.setEnabled(false);
				Window.this.repaint();
			}
			
			public void enablePass() {
				passButton.setEnabled(true);
				passButton.repaint();
			}
		};
		game=new Reversi(listener, level, pruned, timed);
		
		
		//Swing
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		menu = new Menu(this);
		add(menu, BorderLayout.NORTH);
		gPanel = new GamePanel(game, this);
		add(gPanel, BorderLayout.CENTER);
		
		status=new JLabel("Your turn!");
		
		passButton=new JButton("pass");
		passButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Window.this.passButton.setEnabled(false);
				Window.this.game.computerTurn();
				repaint();
			}
		});
		add(passButton, BorderLayout.EAST);
		add(status,BorderLayout.SOUTH);
		passButton.setEnabled(false);
		pack();
		setVisible(true);
	}
	
	public void play(int row, int col){
		if(game.PlayerTurn(row, col)){
			status.setText("The computer is thinking...");
			status.paintImmediately(getBounds());
			gPanel.paintImmediately(getBounds());
			//repaint();
			game.computerTurn();
			status.setText("Your turn");
			status.paintImmediately(getBounds());
			gPanel.paintImmediately(getBounds());
			//repaint();
		}
	}
	
	public Reversi getGame() {
		return game;
	}
}
