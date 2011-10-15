package AI;

import game.Board;
import game.Position;


public class MaxNode extends Node{

	public MaxNode(Board board, Position pos){
		super.board=board;
		value=Integer.MIN_VALUE;
		super.pos=pos;
	}

	@Override
	public boolean chooseMove(int val) {
		return value<val;
	}

	@Override
	public boolean pruneBranch(int val) {
		return value>=val;
	}

	@Override
	public String getDOTFormat() {
		return "shape=box,";
	}
}
