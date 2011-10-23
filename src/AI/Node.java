package AI;

import game.*;


import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class Node{

	private final static int[][] HEURISTICAL_VALUES= 
		{{99,-8,8,6,6,8,-8,99},
		{-8,-24,-4,-3,-3,-4,-24,-8},
		{8,-4,7,4,4,7,-4,8},
		{6,-3,4,0,0,4,-3,6},
		{6,-3,4,0,0,4,-3,6},
		{8,-4,7,4,4,7,-4,8},
		{-8,-24,-4,-3,-3,-4,-24,-8},
		{99,-8,8,6,6,8,-8,99}};
	protected List<Node> childs=new LinkedList<Node>();
	protected Board board;
	protected Position pos;
	protected int value;
	protected Tile myTile;

	public int getHeuristicalValue(){
		int val=0;
		for(int i=0; i<Board.SIZE; i++){
			for(int j=0; j<Board.SIZE;j++){
				val+=getHeuristicalValue(i,j);
			}
		}
		value=val;
		return val;
	}

	private int getHeuristicalValue(int row, int col){
		Tile tile = board.getTile(row, col);
		switch(tile){
		case PLAYER1:
			return -1*HEURISTICAL_VALUES[row][col];
		case PLAYER2:
			return HEURISTICAL_VALUES[row][col];
		default:
			return 0;
		}
	}


	public Position nextMove(int maxLevel, long level, boolean prune, Integer parentVal, TimeInfo timeInfo){
		if(timeInfo!=null){
			timeInfo.update();
		}
		if(maxLevel==level){ 
			getHeuristicalValue();
			return pos;
		}
		Position nextPos=pos;
		setChilds();
		level++;
		for(Node child:childs){
			if(prune && pruneBranch(parentVal)){
				return null;
			}
			if(child.chooseMove(value)){
				child.nextMove(maxLevel, level, prune, value, timeInfo);
				nextPos=child.pos;
				value=child.value;
			}
		}
		return nextPos;
	}
	public int toDOT(FileWriter fr, boolean redColored, int i) throws IOException{

		String red, format, val, blue;
		int me = i,aux;
		if(redColored){
			red="color=red, style=filled, ";
		}else{
			red="";
		}
		if(value==Integer.MAX_VALUE||value==Integer.MIN_VALUE){
			val="";
			blue="color=blue, style=filled, ";
		}else{
			val=""+value;
			blue="";
		}
		format=getDOTFormat();
		fr.append(me + " ["+red+blue+format+"label=\""+pos.toString() + " " + val +"\"];\n");
		boolean redSon, selected=true;
		for(Node son: childs){
			if(selected &&son.value==value){
				selected=false;
				redSon=true;
			}else{
				redSon=false;
			}
			aux=i+1;
			i=son.toDOT(fr, redSon, ++i); 
			fr.append(me +" -> " + aux +";\n");
		}
		return i;
	}

	public void setChilds(){
		int myRow, myCol;
		for (int row = 0; row < Board.SIZE; row++) {
			for (int col = 0; col < Board.SIZE; col++) {
				if (board.getTile(row, col) == myTile.getOpposite()) {
					for (Direction dir : Direction.values()) {
						myRow = row + dir.getRow();
						myCol = col + dir.getCol();
						if (!(myRow < 0 || myCol < 0 || myRow >= Board.SIZE || myCol >= Board.SIZE)
								&& board.getTile(myRow, myCol) == Tile.EMPTY) {
							if (board.possibleChange(myRow, myCol, myTile, dir.getOpposite())) {
								childs.add(getNewChild(board.putTile(myRow, myCol, myTile.getOpposite()), new Position(myRow, myCol), myTile.getOpposite()));
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
	public abstract Node getNewChild(Board board, Position pos, Tile tile);

//*TODO mirar heuri

	public int extraHV(){
		int heurVal=board.getPossiblePositions(myTile).size()*3;
		if(heurVal==0 && board.getPossiblePositions(myTile.getOpposite()).isEmpty()){
			int myCount=0,otherCount=0;
			for(int row=0; row<Board.SIZE; row++){
				for(int col=0; col<Board.SIZE; col++){
					if(board.getField()[row][col]==myTile){
						myCount++;
					}
					else if(board.getField()[row][col]==myTile.getOpposite()){
						otherCount++;				
					}
				}
				if(myCount-otherCount<0){
					return -10000;
				}else if(myCount-otherCount>0){
					return 10000;
				}
			}
		}
		heurVal+=controlCorners(heurVal)+controlBorders(heurVal);
		return heurVal;
	}




	private int controlBorders(int hv){
		int heurVal=hv;
		for(int row=2;row<Board.SIZE-3; row++){
			if(board.getField()[row][0]==myTile){
				heurVal+=5;
			}
			if(board.getField()[row][Board.SIZE-1]==myTile){
				heurVal+=5;
			}    
		}
		for(int col=2;col<Board.SIZE-3; col++){
			if(board.getField()[0][col]==myTile){
				heurVal+=5;
			}
			if(board.getField()[Board.SIZE-1][col]==myTile){
				heurVal+=5;
			}    
		}
		return heurVal;
	}

	private int controlCorners(int hv){
		int heurVal=hv;
		if(board.getField()[0][0]==Tile.EMPTY){
			if(board.getField()[0][1]==myTile){
				heurVal-=10;
			}
			if(board.getField()[1][1]==myTile){
				heurVal-=10;
			}
			if(board.getField()[1][0]==myTile){
				heurVal-=10;
			}
		}else if(board.getField()[0][0]==myTile){
			heurVal+=50;
		}
		if(board.getField()[Board.SIZE-1][0]==Tile.EMPTY){
			if(board.getField()[Board.SIZE-1][1]==myTile){
				heurVal-=10;
			}
			if(board.getField()[Board.SIZE-2][1]==myTile){
				heurVal-=10;
			}
			if(board.getField()[Board.SIZE-2][0]==myTile){
				heurVal-=10;
			}
		}else if(board.getField()[Board.SIZE-1][0]==myTile){
			heurVal+=50;
		}
		if(board.getField()[0][Board.SIZE-1]==Tile.EMPTY){
			if(board.getField()[1][Board.SIZE-1]==myTile){
				heurVal-=10;
			}
			if(board.getField()[0][Board.SIZE-2]==myTile){
				heurVal-=10;
			}
			if(board.getField()[1][Board.SIZE-2]==myTile){
				heurVal-=10;
			}
		}else if(board.getField()[0][Board.SIZE-1]==myTile){
			heurVal+=50;
		}
		if(board.getField()[Board.SIZE-1][Board.SIZE-1]==Tile.EMPTY){
			if(board.getField()[Board.SIZE-2][Board.SIZE-1]==myTile){
				heurVal-=10;
			}
			if(board.getField()[Board.SIZE-1][Board.SIZE-2]==myTile){
				heurVal-=10;
			}
			if(board.getField()[Board.SIZE-2][Board.SIZE-2]==myTile){
				heurVal-=10;
			}
		}else if(board.getField()[Board.SIZE-1][Board.SIZE-1]==myTile){
			heurVal+=50;
		}
		return heurVal;
	}
}

