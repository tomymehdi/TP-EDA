package parser;

import game.Board;
import game.Tile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import sun.applet.Main;

public class Parser {

	private final int LENGTH = 8;
	private File file;
	private BufferedReader inputFile;

	public Parser(File file) {
		this.file = file;
		try {
			this.inputFile = new BufferedReader(new FileReader(file));
		} catch (Exception e) {
			// TODO
		}
	}

	public Board parseFile() throws Exception {
		int countRows = 0;
		Board board = new Board(1);
		String line;
		char[] charLine;
		BufferedReader inputFile = new BufferedReader(new FileReader(file));
		while ((line = inputFile.readLine()) != null) {
			if (line.length() != 8 || countRows > 8) {
				throw new Exception();
			}
			charLine = line.toCharArray();
			for (int i = 0; i < 8; i++) {
				switch (charLine[i]) {
				case ' ':
					board.put(countRows, i, Tile.EMPTY);
					break;
				case '1':
					board.put(countRows, i, Tile.PLAYER1);
					break;
				case '2':
					board.put(countRows, i, Tile.PLAYER2);
					break;
				default:
					throw new Exception();
				}
			}
			countRows++;
		}
		if(countRows!=8){
			throw new Exception();
		}

		return board;
	}

	public static void main(String[] args) throws Exception {
		File file = new File("./src/parser/lala.txt");
		Parser test = new Parser(file);
		Board boardd = test.parseFile();
		System.out.println(boardd);

	}

}
