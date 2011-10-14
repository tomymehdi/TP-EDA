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
	JPanel boardPanel;

	public Window(Board board) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		boardPanel = new BoardPanel(board);
		add(boardPanel);
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
