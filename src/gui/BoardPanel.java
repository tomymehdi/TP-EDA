package gui;

import game.Board;
import game.Tile;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	
	private Board board;
	private static final int TILE_SIZE=30;
	
	public BoardPanel(Board board){
		this.board=board;
		setPreferredSize(new Dimension(Board.SIZE*TILE_SIZE,Board.SIZE*TILE_SIZE));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawGrid(g);
		drawTiles(g);
	}
	
	private void drawGrid(Graphics g){
		g.setColor(Color.GRAY);
		for(int col=0; col<Board.SIZE; col++){
			g.drawLine(col*TILE_SIZE, 0, col*TILE_SIZE, Board.SIZE*TILE_SIZE);
		}
		for(int row=0; row<Board.SIZE; row++){
			g.drawLine(0, row*TILE_SIZE, Board.SIZE*TILE_SIZE, row*TILE_SIZE);
		}
	}
	
	private void drawTiles(Graphics g){
		for(int row=0; row<Board.SIZE; row++){
			for(int col=0; col<Board.SIZE; col++){
				Tile tile=board.getTile(row,col);
				if(tile==Tile.PLAYER1){
					g.setColor(Color.BLACK);
					g.fillOval(row*TILE_SIZE, col*TILE_SIZE, TILE_SIZE, TILE_SIZE);
				}
				else if(tile==Tile.PLAYER2){
					g.setColor(Color.WHITE);
					g.fillOval(row*TILE_SIZE, col*TILE_SIZE, TILE_SIZE, TILE_SIZE);
				}
			}
		}
	}
}
