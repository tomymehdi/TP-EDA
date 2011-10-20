package gui;

import game.Board;
import game.Reversi;
import game.Tile;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GamePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Reversi game;
	private static final int TILE_SIZE = 30;
	private Image blackTile, whiteTile, boardIm;
	private Window window;

	public GamePanel(Reversi game, Window window) {
		this.window=window;
		try{
			this.game= game;
			blackTile= ImageUtils.loadImage("./resources/blacktile.png");
			whiteTile=ImageUtils.loadImage("./resources/whitetile.png");
			boardIm=ImageUtils.loadImage("./resources/board.png");
		}catch(IOException e){
			JOptionPane.showMessageDialog(window.getContentPane(), "An error has occurred while uploading the images");
			System.exit(0);
		}
		
		setPreferredSize(new Dimension(Board.SIZE * TILE_SIZE, Board.SIZE*TILE_SIZE));
		setBackground(new Color(255,201,125));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent m) {
				int row=m.getY()/TILE_SIZE;
				int col=m.getX()/TILE_SIZE;
				if(GamePanel.this.game.PlayerTurn(row, col)){
					GamePanel.this.repaint();
					GamePanel.this.game.computerTurn();
					GamePanel.this.repaint();
				}
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGrid(g);
		drawTiles(g);
	}

	private void drawGrid(Graphics g) {
		g.setColor(Color.GRAY);
		g.drawImage(boardIm,0,0,TILE_SIZE*Board.SIZE,TILE_SIZE*Board.SIZE, null);
	}

	private void drawTiles(Graphics g) {
		Board board= game.getBoard();
		for (int row = 0; row < Board.SIZE; row++) {
			for (int col = 0; col < Board.SIZE; col++) {
				Tile tile = board.getTile(row, col);
				if (tile == Tile.PLAYER1) {
					g.drawImage(whiteTile,col*TILE_SIZE,row*TILE_SIZE,TILE_SIZE,TILE_SIZE,null);
				} else if (tile == Tile.PLAYER2) {
					g.drawImage(blackTile,col*TILE_SIZE,row*TILE_SIZE,TILE_SIZE,TILE_SIZE,null);
				}
			}
		}
	}
}
