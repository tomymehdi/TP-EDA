package game;

public enum Tile {
	PLAYER1,PLAYER2, EMPTY;
	
	public Tile getOpposite(){
		if(this==PLAYER1){
			return Tile.PLAYER2;
		}else if(this==PLAYER2){
			return Tile.PLAYER1;
		}
		return Tile.EMPTY;
	}
}
