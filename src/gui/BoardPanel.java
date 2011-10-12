package gui;

import game.Board;

import java.awt.Dimension;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	
	private Board board;
	private static final int TILE_SIZE=30;
	
	public BoardPanel(Board board){
		this.board=board;
		setPreferredSize(new Dimension(Board.SIZE*TILE_SIZE,Board.SIZE*TILE_SIZE));
	}
}
