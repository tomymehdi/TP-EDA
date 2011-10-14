package gui;

import game.Board;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import parser.Parser;

public class Window extends JFrame {
	BoardPanel boardPanel;

	public Window(Board board) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		boardPanel = new BoardPanel(board);
		add(boardPanel);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				Board b=Window.this.boardPanel.getBoard();
				if(e.getKeyCode()==KeyEvent.VK_ENTER && !b.isPlayerTurn()){
					Window.this.boardPanel.setBoard(b.computerTurn());
					Window.this.repaint();
				}
			}
		});
		pack();
		setVisible(true);
	}

	public Window() {
		this(new Board());
	}


	public static void main(String[] args) {
		Parser p=new Parser(new File("./boards/lala.txt"));
		Board b;
		try{
			b=p.parseFile();
		} catch(Exception e){
			throw new RuntimeException("Invalid File");
		}
		b=new Board();
		new Window(b);
	}
}
