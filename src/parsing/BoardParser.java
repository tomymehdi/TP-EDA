package parsing;

import game.Board;
import game.Tile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class BoardParser {

	private BufferedReader buffer;
	
	public BoardParser(File file) throws FileNotFoundException {
		buffer = new BufferedReader(new FileReader(file));
	}

	public Board parseFile() throws IOException{
		Tile [][] matrix= new Tile[Board.SIZE][Board.SIZE];
		int i=0;
		String line;
		char[] charLine;
		while ((line = buffer.readLine()) != null) {
			if (line.length() != Board.SIZE || i > Board.SIZE) {
				throw new ParsingException();
			}
			charLine = line.toCharArray();
			for (int j = 0; j < Board.SIZE; j++) {
				switch (charLine[j]) {
				case ' ':
					matrix[i][j]=Tile.EMPTY;
					break;
				case '1':
					matrix[i][j]=Tile.PLAYER1;
					break;
				case '2':
					matrix[i][j]=Tile.PLAYER2;
					break;
				default:
					throw new ParsingException();
				}
			}
			i++;
		}
		
		if(i!=Board.SIZE){
			throw new ParsingException();
		}
		Board board=new Board(matrix);
		return board;
	}

}
