package gui;

import game.Board;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
	private Board board;
	JPanel boardPanel;

	public Window() {
		board = new Board();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		boardPanel = new BoardPanel(board);
		add(boardPanel);
		pack();
		setVisible(true);
	}

	// TODO esto es para ver q el parsing funciona bien, no deberia estar por q
	// el entorno grafico
	// nunca va a levantar un archivo supuestamente
	public Window(Board board) {
		this.board = board;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		boardPanel = new BoardPanel(board);
		add(boardPanel);
		pack();
		setVisible(true);
	}

	// public static void main(String[] args) {
	// new Window();
	// }
}
