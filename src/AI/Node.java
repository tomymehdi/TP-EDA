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



	public Position nextMove(int maxLevel, int level, boolean prune, int rootVal){
		if(maxLevel==level){
			getHeuristicalValue();
			return pos;
		}
		Position nextPos=pos;
		setChilds();
		for(Node child:childs){
			child.nextMove(maxLevel, level+1, prune, value);
			if(prune && pruneBranch(rootVal)){
				child.pruned=true;
				return null;
			}
			if(child.chooseMove(value)){
				nextPos=child.pos;
				value=child.value;
			}
		}
		return nextPos;
	}
	public int toDOT(FileWriter fr, boolean red, int i) throws IOException{

		String s, p;
		int me = i,aux;
		if(red){
			s="color=red, style=filled, ";
		}else if(pruned){
			s="color=blue, style=filled, ";
		}else{
			s="";
		}
		if(this instanceof MaxNode){//*TODO turbioo
			p="shape=box, ";
		}else{
			p="";
		}
		fr.append(me + " ["+s+p+"label=\""+pos.toString() + " " + value +"\"];\n");
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
		Tile tile;
		boolean IAmMini = this instanceof MiniNode;
		if(IAmMini){
			tile=Tile.PLAYER2;
		}else{
			tile=Tile.PLAYER1;
		}
		for (int row = 0; row < Board.SIZE; row++) {
			for (int col = 0; col < Board.SIZE; col++) {
				if (board.getTile(row, col) == tile) {
					for (Direction dir : Direction.values()) {
						myRow = row + dir.getRow();
						myCol = col + dir.getCol();
						if (!(myRow < 0 || myCol < 0 || myRow >= Board.SIZE || myCol >= Board.SIZE)
								&& board.getTile(myRow, myCol) == Tile.EMPTY) {
							if (board.possibleChange(myRow, myCol, tile.getOpposite(), dir.getOpposite())) {
								if(IAmMini){
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
	public abstract boolean pruneBranch(int val);
}
