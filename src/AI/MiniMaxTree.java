package AI;

import game.Board;
import game.FieldFactory;
import game.Position;
import game.Tile;

import java.io.FileWriter;
import java.io.IOException;

public class MiniMaxTree {
//*TODO ASK ANDI
	public static int CPUTURN=2, PLAYERTURN=1;
	private Node root;
	private int limit;
	private boolean prune, timed, DOT;
	Tile tile;
	Board board;

	public MiniMaxTree(int limit, Board board, boolean prune, boolean timed, boolean DOT, int startingPlayer) {
		if(timed){
			this.limit = limit*1000;
		}else{
			this.limit=limit;
		}
		if(startingPlayer==PLAYERTURN){
			tile=Tile.PLAYER1;
		}else{
			tile=Tile.PLAYER2;
		}
		this.board=board;
		this.prune = prune;
		this.DOT=DOT;
		this.timed=timed;
	}
	
	public Position getNextMove(){
		Position pos;
		if(timed){
			pos= getNextMoveByTime();
		}else{
			pos=getNextMoveByLevel(limit);
		}
		if(DOT){
			generateDOT();
		}
		return pos;
	}
	
	private Position getNextMoveByLevel(int limit){
		root = new MaxNode(board, null, tile);
		Position ans=root.nextMove(limit, 0, prune, null);
		return ans;
	}
	
	private Position getNextMoveByTime(){
		long initTime= System.currentTimeMillis();
		Position pos=null;
		int level=1;
		long elapsedTime;
		while( (elapsedTime=System.currentTimeMillis()-initTime) <=limit){
			pos=getNextMoveByLevel(level);
			//System.out.println("Time: "+elapsedTime +" Level: "+ level);
			level++;
		}
		System.out.println("Total time: "+elapsedTime);
		return pos;
	}

	public void generateDOT() {
		try {
			int i = 0, aux;
			FileWriter fr = new FileWriter("./tree.dot");
			fr.append("digraph {\n");
			fr.append("Root [shape=box, color=red, style=filled, label=\"START "
					+ root.value + "\"];\n");
			boolean selected=true, red;
			for (Node son : root.childs) {
				if (selected&&root.value == son.value) {
					selected=false;
					red = true;
				} else {
					red = false;
				}
				aux = i + 1;
				i = son.toDOT(fr, red, ++i);
				fr.append("Root -> " + aux + ";\n");
			}

			fr.append("}");
			fr.close();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	
	
	
	public static void main(String[] args) {
//		Tile[][] tiles = {{ Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY },
//	{ Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.PLAYER1, Tile.EMPTY,Tile.EMPTY, Tile.EMPTY, Tile.EMPTY },
//	{ Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.PLAYER1, Tile.EMPTY,Tile.EMPTY, Tile.EMPTY, Tile.EMPTY },
//	{ Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.PLAYER1, Tile.EMPTY,Tile.EMPTY, Tile.EMPTY, Tile.EMPTY },
//	{ Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.PLAYER1, Tile.PLAYER1,Tile.PLAYER2, Tile.EMPTY, Tile.EMPTY },
//	{ Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY,Tile.EMPTY, Tile.EMPTY, Tile.EMPTY },
//	{ Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY,Tile.EMPTY, Tile.EMPTY, Tile.EMPTY },
//	{ Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY,Tile.EMPTY, Tile.EMPTY, Tile.EMPTY } };
		MiniMaxTree t = new MiniMaxTree(3, new Board(FieldFactory.DEFAULT_FIELD), true, false,true, CPUTURN);
		t.getNextMove();
	}
}
