package AI;

import game.Board;
import game.Direction;
import game.Position;
import game.Tile;

public class MiniNode extends Node{
	
	public MiniNode(Board board, Position pos){
		super.board=board;
		value=Integer.MAX_VALUE;
		super.pos=pos;
	}
	
	public void setChilds(){
		int myRow, myCol;
		for (int row = 0; row < Board.SIZE; row++) {
			for (int col = 0; col < Board.SIZE; col++) {
				if (board.getTile(row, col) == Tile.PLAYER2) {
					for (Direction dir : Direction.values()) {
						myRow = row + dir.getRow();
						myCol = col + dir.getCol();
						if (!(myRow < 0 || myCol < 0 || myRow >= Board.SIZE || myCol >= Board.SIZE)
						&& board.getTile(myRow, myCol) == Tile.EMPTY) {
							if (board.possibleChange(myRow, myCol, Tile.PLAYER1, dir.getOpposite())) {
								childs.add(new MaxNode(board.putTile(myRow, myCol, Tile.PLAYER1), new Position(myRow, myCol)));
							}

						}
					}
				}
			}
		}
	}
	
	@Override
	public Position nextMove(int maxLevel, int level, boolean prune, int rootVal){
		if(maxLevel==level){
			getHeuristicalValue();
			return pos;
		}
		int current;
		Position nextPos=pos;
		for(Node child:childs){
			child.nextMove(maxLevel, level+1, prune, value);
			current=child.value;
			if(prune && current<=rootVal){
				return null;
			}
			if(value<current){
				nextPos=child.pos;
				value=current;
			}
		}
		return nextPos;
	}
}