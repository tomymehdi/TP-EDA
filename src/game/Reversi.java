package game;

import java.awt.event.ActionListener;
import java.io.File;

import parser.Parser;

import AI.MiniMaxTree;

public class Reversi {
	Board board;
	private boolean pass;
	private GameListener listener;
	private boolean playerCanMove=true;
	
	public Reversi(File boardFile, GameListener listener){
		Parser p = new Parser(boardFile);
		Board board;
		try {
			board = p.parseFile();
		} catch (Exception e) {
			throw new RuntimeException("Invalid File");
		}
		board = new Board();
		this.listener=listener;
		this.board=board;
	}
	
	public Reversi(GameListener listener){
		this.listener=listener;
		this.board=new Board();
	}
	
	private void endOfGame() {
		//*TODO chequear quien gano
		listener.endOfGame();
	}
	
	public void computerTurn() {
		MiniMaxTree tree = new MiniMaxTree(2, board, false);
		Position pos = tree.getNextMove(false);
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
