package AI;

import game.Board;
import game.Position;


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
	public boolean pruneBranch(int val) {
		return value<=val;
	}

	@Override
	public String getDOTFormat() {
		return "";
	}
}