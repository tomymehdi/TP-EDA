package AI;

import game.Board;
import game.Position;
import game.Tile;


public class MiniNode extends Node{

	public MiniNode(Board board, Position pos){
		super.board=board;
		value=Integer.MAX_VALUE;
		super.pos=pos;
	}

	@Override
	public boolean chooseMove(int val) {
		return value>val;
	}

	@Override
	public boolean pruneBranch(Integer val) {
		if(val!=null){
			return value<=val;
		}
		return false;
	}

	@Override
	public String getDOTFormat() {
		return "";
	}
	
	@Override
	public Tile getOpositeTile(){
		return Tile.PLAYER2;
	}
}