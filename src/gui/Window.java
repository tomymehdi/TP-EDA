package gui;

import game.GameListener;
import game.Reversi;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Window extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GamePanel gPanel;
	Reversi game;
	JButton passButton;

	public Window(int level, boolean pruned, boolean timed){
		GameListener listener=new GameListener(){
			public void endOfGame(int score) {
				String endMessage;
				if(score>0){
					endMessage="You have won!";
				}
				else if(score<0){
					endMessage="You have lost!";
				}
				else{
					endMessage="It's a tie!";
				}
				JOptionPane.showMessageDialog(Window.this, endMessage,"End", JOptionPane.INFORMATION_MESSAGE);
			}
			
			public void enablePass() {
				passButton.setEnabled(true);
			}
		};
		game=new Reversi(listener, level, pruned, timed);
		
		
		//Swing
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		gPanel = new GamePanel(game);
		add(gPanel);
		
		passButton=new JButton("pass");
		passButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Window.this.game.computerTurn();
				Window.this.passButton.setEnabled(false);
			}
		});
		passButton.setEnabled(false);
		add(passButton);
		pack();
		setVisible(true);
	}

}
