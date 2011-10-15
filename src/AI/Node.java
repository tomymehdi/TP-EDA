package AI;

import game.*;


import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class Node{

	protected List<Node> childs=new LinkedList<Node>();
	Board board;
	Position pos;
	int value;
	boolean pruned;

	public int getHeuristicalValue(){
		int val=0;
		for(Tile[] row: board.getField()){
			for(Tile tile: row){
				val+=getHeuristicalValue(tile);
			}
		}
		value=val;
		return val;
	}

	private int getHeuristicalValue(Tile tile){
		switch(tile){
		case PLAYER1:
			return -1;
		case PLAYER2:
			return 1;
		default:
			return 0;
		}
	}


	//*TODO timee!!
	public Position nextMove(int maxLevel, long limit, boolean prune, boolean timed, Integer parentVal){
		long time= System.currentTimeMillis();
		if((!timed && maxLevel==limit) || (timed && limit<=0)){
			getHeuristicalValue();
			return pos;
		}
		Position nextPos=pos;
		setChilds();
		if(timed){
			limit=time-System.currentTimeMillis();
		}else{
			limit++;
		}
		for(Node child:childs){
			if(prune && pruneBranch(parentVal)){
				child.pruned=true;
				return null;
			}
			if(child.chooseMove(value)){
			child.nextMove(maxLevel, limit, prune, timed, value);
				nextPos=child.pos;
				value=child.value;
			}
		}
		return nextPos;
	}
	public int toDOT(FileWriter fr, boolean red, int i) throws IOException{

		String s, p, v;
		int me = i,aux;
		if(red){
			s="color=red, style=filled, ";
		}else if(pruned){
			s="color=blue, style=filled, ";
		}else{
			s="";
		}
		if(value==Integer.MAX_VALUE||value==Integer.MIN_VALUE){
			v="";
		}else{
			v=""+value;
		}
		p=getDOTFormat();
		fr.append(me + " ["+s+p+"label=\""+pos.toString() + " " + v +"\"];\n");
		boolean flag;
		for(Node son: childs){
			if(son.value==value){
				flag=true;
			}else{
				flag=false;
			}
			aux=i+1;
			i=son.toDOT(fr, flag, ++i); 
			fr.append(me +" -> " + aux +";\n");
		}
		return i;
	}

	public void setChilds(){
		int myRow, myCol;
		Tile tile= getOpositeTile();
		for (int row = 0; row < Board.SIZE; row++) {
			for (int col = 0; col < Board.SIZE; col++) {
				if (board.getTile(row, col) == tile) {
					for (Direction dir : Direction.values()) {
						myRow = row + dir.getRow();
						myCol = col + dir.getCol();
						if (!(myRow < 0 || myCol < 0 || myRow >= Board.SIZE || myCol >= Board.SIZE)
								&& board.getTile(myRow, myCol) == Tile.EMPTY) {
							if (board.possibleChange(myRow, myCol, tile.getOpposite(), dir.getOpposite())) {
								if(tile==Tile.PLAYER2){
									childs.add(new MaxNode(board.putTile(myRow, myCol, Tile.PLAYER1), new Position(myRow, myCol)));									
								}else{
									childs.add(new MiniNode(board.putTile(myRow, myCol, Tile.PLAYER2), new Position(myRow, myCol)));									
								}
							}

						}
					}
				}
			}
		}
	}
	
	public abstract boolean chooseMove(int val);
	public abstract boolean pruneBranch(Integer val);
	public abstract String getDOTFormat();
	public abstract Tile getOpositeTile();
}
