package game;

public class Board implements Cloneable{
	public final int SIZE=8;
	private Tile [][] field = new Tile[SIZE][SIZE];
	
	public Board(){
		for(int row = 0; row<SIZE; row++){
			for(int col =0; col<SIZE; col++){
				if((row==(SIZE/2)-1 && col==(SIZE/2)-1)|| (row==SIZE/2 && col==SIZE/2)) {
					field[row][col]=Tile.PLAYER1;
				}else if((row==(SIZE/2)-1 && col==(SIZE/2))|| (row==SIZE/2 && col==(SIZE/2)-1)) {
					field[row][col]=Tile.PLAYER2;
				}else{
					field[row][col]=Tile.EMPTY;
				}
			}	
		}
	}
	
	public Board putTile(int row, int col, Tile tile){
		Board changed = this.clone();
		int count=0;
		if(changed.field[row][col]!=Tile.EMPTY){
			changed.field[row][col]=tile;
			for(Direction dir : Direction.values()){
				count+=spread(row+dir.getRow(), col+dir.getCol(), tile, dir);
			}
			if(count==0){
				return changed;
			}
		}
		//TODO* pincha
		return null;
		
	}
	
	public Set<Position> getPossiblePositions(Tile tile){
		for(int row = 0; row<SIZE; row++){
			for(int col =0; col<SIZE; col++){
				if(field[row][col]==tile.getOpposite()){
					
				}
			}
		}
	}
		
	private boolean possibleChange(int row, int col)
		
	
	//*TODO revisarr
	public int spread(int row, int col, Tile tile, Direction dir){
		Tile thisTile=field[row][col];
		if((row<0 || row>=SIZE || col<0 || col>=SIZE) || thisTile==Tile.EMPTY){
			return 0;
		}
		int rta;
		if(thisTile==tile.getOpposite()){
			rta=spread(row+dir.getRow(), col+dir.getCol(), tile, dir);
			if(rta>0||rta==-1){
				field[row][col]=thisTile.getOpposite();
				if(rta>0){
					return rta+1;
				}
				return 1;
			}
			return -1;
		}
		return 0;
	}
	
	public Board clone(){
		Board cloned = new Board();
		cloned.field=this.field.clone();
		return cloned;
	}
	
	
}
