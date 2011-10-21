package game;


import AI.MiniMaxTree;

public class Reversi {
	private Board board;
	private boolean pruned, timed;
	private GameListener listener;
	private boolean playerCanMove;
	private int level;
	private boolean playerTurn;
	private int tileCount;
	
	public Reversi(GameListener listener,int level, boolean pruned, boolean timed){
		this.listener=listener;
		this.timed=timed;
		this.pruned=pruned;
		this.level=level;
		newGame();
	}
	
	private void endOfGame() {
		int playerCount=board.cantTiles(Tile.PLAYER1);
		int computerCount=board.cantTiles(Tile.PLAYER2);
		listener.endOfGame(playerCount-computerCount);
	}
	
	public void computerTurn() {
		MiniMaxTree tree = new MiniMaxTree(level, board, pruned, timed, false, MiniMaxTree.CPUTURN);
		Position pos = tree.getNextMove();
		boolean cpuCanMove=false;
		if (pos != null) {
			board=board.putTile(pos.getRow(), pos.getCol(), Tile.PLAYER2);
			tileCount++;
			cpuCanMove=true;
		}
		
		if(!cpuCanMove&&!playerCanMove || tileCount>=Board.SIZE*Board.SIZE){
			endOfGame();
			return;
		}
		
		playerTurn=true;
		playerCanMove=board.playerHasMoves();
		if(!playerCanMove){
			if(cpuCanMove){
				listener.enablePass();
			}
			else{
				endOfGame();
			}
		}
	}
	
	public boolean PlayerTurn(int row, int col){
		if(playerTurn&& playerCanMove){
				Board auxBoard=board.putTile(row, col, Tile.PLAYER1);
				if(auxBoard==board){
					//Toco un lugar incorrecto
					return false;
				}
				tileCount++;
				board=auxBoard;
				playerTurn=false;
				return true;
		}
		return false;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void newGame(){
		board=new Board(Board.DEFAULT_FIELD);
		tileCount=4;
		playerTurn=true;
		playerCanMove=true;
	}
}
