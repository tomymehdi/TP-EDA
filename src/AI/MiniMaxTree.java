package AI;

import game.Board;
import game.Position;

import java.io.FileWriter;
import java.io.IOException;

public class MiniMaxTree {

	private Node root;
	int maxLevel;
	boolean prune;

	public MiniMaxTree(int level, Board board, boolean prune){
		this.maxLevel=level;
		root=new MaxNode(board, null);
		this.prune=prune;
	}

	public Position getNextMove(boolean DOT){

		Position pos=root.nextMove(maxLevel, 0, prune, Integer.MIN_VALUE);
		if(pos!=null){
			if(DOT){
				generateDOT();
			}
			return pos;
		}
		return null;
	}

	public void generateDOT(){
		try{
			FileWriter fr=new FileWriter("./tree.dot");
			fr.append("digraph {\n");
			root.toDOT(fr, true);
			fr.append("}");
			fr.close();
		}catch(IOException e){
			//*TODO mensaje de error
		}
	}
	
	public static void main(String[] args) {
		MiniMaxTree t = new MiniMaxTree(1, new Board(), false);
		getNextMove(boolean);
	}
}
