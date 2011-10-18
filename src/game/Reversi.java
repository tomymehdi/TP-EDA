package game;

import java.awt.event.ActionListener;
import java.io.File;

import parsing.BoardParser;

import AI.MiniMaxTree;

public class Reversi {
	private Board board;
	private boolean pruned, timed;
	private GameListener listener;
	private boolean playerCanMove;
	private int level;
	private boolean playerTurn;
	private int tileCount;
	
	public Reversi(GameListener listener,int level, boolean pruned, boolean timed){
		this.listener=listener;
		this.timed=timed;
		this.pruned=pruned;
		this.level=level;
		newGame();
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
		MiniMaxTree tree = new MiniMaxTree(level, board, pruned, timed, false, MiniMaxTree.CPUTURN);
		Position pos = tree.getNextMove();
		boolean cpuCanMove=false;
		if (pos != null) {
			board=board.putTile(pos.getRow(), pos.getCol(), Tile.PLAYER2);
			tileCount++;
			cpuCanMove=true;
		}
		else{
			if(!playerCanMove){
				endOfGame();
				return;
			}
		}
		playerTurn=true;
		if(tileCount>=Board.SIZE*Board.SIZE){
			endOfGame();
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
		if(playerTurn&& playerCanMove){
				Board auxBoard=board.putTile(row, col, Tile.PLAYER1);
				if(auxBoard==board){
					//Toco un lugar incorrecto
					return false;
				}
				tileCount++;
				board=auxBoard;
				playerTurn=false;
				return true;
		}
		return false;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void newGame(){
		board=new Board(FieldFactory.DEFAULT_FIELD);
		tileCount=4;
		playerTurn=true;
		playerCanMove=true;
	}
}
