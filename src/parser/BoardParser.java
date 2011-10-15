package parser;

import game.Board;
import game.Tile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class BoardParser {

	private File file;
	public BoardParser(File file) {
		this.file = file;
		try {
			new BufferedReader(new FileReader(file));
		} catch (Exception e) {
			System.out.println("An error occurred while opening the requested file");
		}
	}

	public Board parseFile(){
		Tile [][] matrix= new Tile[Board.SIZE][Board.SIZE];
		int i=0;
		String line;
		char[] charLine;
		try{
		BufferedReader inputFile = new BufferedReader(new FileReader(file));
		while ((line = inputFile.readLine()) != null) {
			if (line.length() != Board.SIZE || i > Board.SIZE) {
				System.out.println("Invalid board size");
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
					System.out.println("Invalid board");
				}
			}
			i++;
		}
		}catch(IOException e){
			System.out.println("Invalid board");
		}
		if(i!=Board.SIZE){
			System.out.println("Invalid board size");
		}
		Board board=new Board(matrix);
		return board;
	}

}
