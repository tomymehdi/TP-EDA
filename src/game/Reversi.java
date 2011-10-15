package game;

import java.awt.event.ActionListener;
import java.io.File;

import parser.BoardParser;

import AI.MiniMaxTree;

public class Reversi {
	private Board board;
	private boolean pass, pruned, timed;
	private GameListener listener;
	private boolean playerCanMove=true;
	private int level;
	
	public Reversi(GameListener listener,int level, boolean pruned, boolean timed){
		this.listener=listener;
		this.board=new Board();
		this.timed=timed;
		this.pruned=pruned;
		this.level=level;
		
	}
	
	private void endOfGame() {
		int playerCount=0;
		int computerCount=0;
		Tile[][] field=board.getField();
		for(int row=0; row<Board.SIZE; row++){
			for(int col=0; col<Board.SIZE; col++){
				if(field[row][col]==Tile.PLAYER1){
					playerCount++;
				}
				else if(field[row][col]==Tile.PLAYER2){
					computerCount++;
				}
			}
		}
		listener.endOfGame(playerCount-computerCount);
	}
	
	public void computerTurn() {
		MiniMaxTree tree = new MiniMaxTree(level, board, pruned, timed, false);
		Position pos = tree.getNextMove();
		boolean cpuCanMove=false;
		if (pos != null) {
			board=board.putTile(pos.getRow(), pos.getCol(), Tile.PLAYER2);
			cpuCanMove=true;
		}
		playerCanMove=board.playerHasMoves();
		if(!playerCanMove){
			if(cpuCanMove){
				listener.enablePass();
			}
			else{
				endOfGame();
			}
			
		}
	}
	
	public boolean PlayerTurn(int row, int col){
		if(board.isPlayerTurn()&& playerCanMove){
				Board auxBoard=board.putTile(row, col, Tile.PLAYER1);
				if(auxBoard==board){
					//Toco un lugar incorrecto
					return false;
				}
				board=auxBoard;
				return true;
		}
		return false;
	}
	
	public Board getBoard() {
		return board;
	}
}
