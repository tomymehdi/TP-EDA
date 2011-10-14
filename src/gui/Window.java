package gui;

import game.Board;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
	private Board board;
	JPanel boardPanel;

	public Window(Board board) {
		this.board = board;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		boardPanel = new BoardPanel(board);
		add(boardPanel);
		pack();
		setVisible(true);
	}
	
	public Window() {
		new Window(new Board());
	}


	 public static void main(String[] args) {
		 new Window();
	 }
}
