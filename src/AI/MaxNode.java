package AI;

import game.Board;
import game.Position;
import game.Tile;


public class MaxNode extends Node{

	public MaxNode(Board board, Position pos, Tile tile){
		super.board=board;
		value=Integer.MIN_VALUE;
		super.pos=pos;
		myTile=tile;
	}

	@Override
	public boolean chooseMove(int val) {
		return value<val;
	}

	@Override
	public boolean pruneBranch(Integer val) {
		if(val!=null){
			return value>=val;
		}
		return false;
	}

	@Override
	public String getDOTFormat() {
		return "shape=box,";
	}
	@Override
	public Tile getOpositeTile(){
		return Tile.PLAYER1;
	}
	
	@Override
	public Node getNewChild(Board board, Position pos, Tile tile) {
		return new MiniNode(board, pos, tile);
	}
}
