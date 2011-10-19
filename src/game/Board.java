package game;

import java.util.HashSet;
import java.util.Set;


public class Board implements Cloneable {

	public static final int SIZE = 8;
	private Tile[][] field;
	
	public Board(Tile[][] field) {
		this.field = field;
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
				int rta;
				rta = clone.spread(row + dir.getRow(), col + dir.getCol(),
						tile, dir);
				if (rta > 0) {
					count += rta;
				}
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
							if (possibleChange(actualRow, actualCol, tile,
									dir.getOpposite())) {
								set.add(new Position(actualRow, actualCol));
							}

						}
					}
				}
			}
		}
		return set;
	}

	public boolean possibleChange(int row, int col, Tile tile, Direction dir) {
		if (field[row][col] != Tile.EMPTY) {
			return false;
		}
		return possibleChangeR(row + dir.getRow(), col + dir.getCol(), tile,
				dir) > 0;
	}

	private int possibleChangeR(int row, int col, Tile tile, Direction dir) {
		Tile thisTile;
		;
		if ((row < 0 || row >= SIZE || col < 0 || col >= SIZE)
				|| (thisTile = field[row][col]) == Tile.EMPTY) {
			return -1;
		}
		if (thisTile == tile.getOpposite()) {
			int rta;
			rta = possibleChangeR(row + dir.getRow(), col + dir.getCol(), tile,
					dir);
			if (rta >= 0) {
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
		if (thisTile == tile.getOpposite()) {
			int rta;
			rta = spread(row + dir.getRow(), col + dir.getCol(), tile, dir);
			if (rta >= 0) {
				field[row][col] = thisTile.getOpposite();
				return ++rta;
			}
			return rta;
		}
		return 0;
	}

	public Board clone() {
		Tile[][] clonedField = new Tile[SIZE][SIZE];
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				clonedField[row][col] = field[row][col];
			}
		}
		return new Board(clonedField);
	}

	public Tile[][] getField() {
		return field;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				s += field[i][j];
			}
			s += "\n";
		}
		return s;
	}

	public boolean playerHasMoves() {
		int myRow, myCol, possibleMoves = 0;
		for (int row = 0; row < Board.SIZE; row++) {
			for (int col = 0; col < Board.SIZE; col++) {
				if (getTile(row, col) == Tile.PLAYER2) {
					for (Direction dir : Direction.values()) {
						myRow = row + dir.getRow();
						myCol = col + dir.getCol();
						if (!(myRow < 0 || myCol < 0 || myRow >= Board.SIZE || myCol >= Board.SIZE)
								&& getTile(myRow, myCol) == Tile.EMPTY) {
							if (possibleChange(myRow, myCol, Tile.PLAYER1,
									dir.getOpposite())) {
								possibleMoves++;
							}

						}
					}
				}
			}
		}
		return possibleMoves >= 1;
	}

}
