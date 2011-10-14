package AI;

import game.Board;
import game.Position;

public class MiniMaxTree {

	private Node root;
	int maxLevel;
	boolean prune;

	public MiniMaxTree(int level, Board board, boolean prune){
		this.maxLevel=level;
		root=new MaxNode(board, null);
		this.prune=prune;
	}

	public Position getNextMove(){

		Position pos=root.nextMove(maxLevel, 0, prune, Integer.MIN_VALUE);
		if(pos!=null){
			return pos;
		}
		return null;
	}



}
