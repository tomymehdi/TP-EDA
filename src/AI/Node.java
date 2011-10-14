package AI;

import game.Board;
import game.Position;
import game.Tile;

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
	
	public abstract void setChilds();
	
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
	
	
	public abstract Position nextMove(int maxLevel, int level, boolean prune, int rootVal);
	
	public int toDOT(FileWriter fr, boolean red, boolean shape, int i) throws IOException{
		String s, p;
		int me = i,aux;
		if(red){
			s="color=red, style=filled, ";
		}else if(pruned){
			s="color=blue, style=filled, ";
		}else{
			s="";
		}
		if(shape){
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
			i=son.toDOT(fr, flag, son instanceof MaxNode, ++i); //*TODO turbioo
			fr.append(me +" -> " + aux +";\n");
		}
		return i;
	}
}
