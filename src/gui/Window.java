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
	GamePanel gPanel;
	Reversi game;
	JButton passButton;

	public Window(){
		GameListener listener=new GameListener(){
			public void endOfGame() {
				JOptionPane.showMessageDialog(Window.this, "Game Over","End", JOptionPane.INFORMATION_MESSAGE);
			}
			
			public void enablePass() {
				passButton.setEnabled(true);
			}
		};
		game=new Reversi(listener);
		
		
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

	public static void main(String[] args) {
		new Window();
	}
}
