package game;

import java.util.HashSet;
import java.util.Set;

public class Board implements Cloneable {

	public static final int SIZE = 8;

	private Tile[][] field = new Tile[SIZE][SIZE];
	private boolean pass;

	public Board() {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if ((row == (SIZE / 2) - 1 && col == (SIZE / 2) - 1)
						|| (row == SIZE / 2 && col == SIZE / 2)) {
					field[row][col] = Tile.PLAYER1;
				} else if ((row == (SIZE / 2) - 1 && col == (SIZE / 2))
						|| (row == SIZE / 2 && col == (SIZE / 2) - 1)) {
					field[row][col] = Tile.PLAYER2;
				} else {
					field[row][col] = Tile.EMPTY;
				}
			}
		}
	}
	
	public Board(int[][] matrix){
		for(int i=0; i<SIZE; i++){
			for(int j=0; j<SIZE; j++){
				switch(matrix[i][j]){
				case 1:
					field[i][j]=Tile.PLAYER1;
					break;
				case 2:
					field[i][j]=Tile.PLAYER2;
					break;
				default:
					field[i][j]=Tile.EMPTY;
				}
			}
		}
	}

	public Tile getTile(int row, int col) {
		return field[row][col];
	}

	public Board putTile(int row, int col, Tile tile) {

		Board clone = this.clone();
		int count = 0;
		if (clone.field[row][col] == Tile.EMPTY) {
			clone.field[row][col] = tile;
			for (Direction dir : Direction.values()) {
				count += clone.spread(row + dir.getRow(), col + dir.getCol(), tile, dir);
			}
			if (count > 0) {
				return clone;
			}
		}
		return this;
	}

	public Set<Position> getPossiblePositions(Tile tile) {
		int actualRow, actualCol;
		Set<Position> set = new HashSet<Position>();
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (field[row][col] == tile.getOpposite()) {
					for (Direction dir : Direction.values()) {
						actualRow = row + dir.getRow();
						actualCol = col + dir.getCol();
						if (!(actualRow < 0 || actualCol < 0
								|| actualRow >= SIZE || actualCol >= SIZE)
								&& field[actualRow][actualCol] == Tile.EMPTY) {
							if (possibleChange(actualRow, actualCol, tile, dir.getOpposite())) {
								set.add(new Position(actualRow, actualCol));
							}

						}
					}
				}
			}
		}
		return set;
	}
	
	public boolean possibleChange(int row, int col, Tile tile, Direction dir){
		if(field[row][col]!=Tile.EMPTY){
			return false;
		}
		return possibleChangeR(row+dir.getRow(), col+dir.getCol(), tile, dir)>0;
	}
	
	private int possibleChangeR(int row, int col, Tile tile, Direction dir) {
		Tile thisTile;;
		if ((row < 0 || row >= SIZE || col < 0 || col >= SIZE)
				|| (thisTile = field[row][col]) == Tile.EMPTY) {
			return -1;
		}
		if(thisTile==tile.getOpposite()){
			int rta;
			rta=possibleChangeR(row+dir.getRow(), col+dir.getCol(), tile, dir);
			if(rta>=0){
				return ++rta;
			}
			return rta;
		}
		return 0;
		
	}
	
	private int spread(int row, int col, Tile tile, Direction dir) {
		Tile thisTile;
		if ((row < 0 || row >= SIZE || col < 0 || col >= SIZE)
				|| (thisTile = field[row][col]) == Tile.EMPTY) {
			return -1;
		}
		if(thisTile==tile.getOpposite()){
			int rta;
			rta=spread(row+dir.getRow(), col+dir.getCol(), tile, dir);
			if(rta>=0){
				field[row][col]=thisTile.getOpposite();
				return ++rta;
			}
			return rta;
		}
		return 0;
	}

	public void checkEndGame(Tile tile) {
		if (getPossiblePositions(tile).isEmpty()) {
			if (pass) {
				// *TODO endgame
			} else {
				pass = true;
				// *TODO pass
			}
		}
	}

	public Board clone() {
		Board cloned = new Board();
		cloned.field = this.field.clone();
		return cloned;
	}
	
	public Tile[][] getField(){
		return field;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				s += field[i][j];
			}
			s+="\n";
		}
		return s;
	}
}
