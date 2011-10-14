package parser;

import game.Board;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class Parser {

	private File file;
	private BufferedReader inputFile;

	public Parser(File file) {
		this.file = file;
		try {
			this.inputFile = new BufferedReader(new FileReader(file));
		} catch (Exception e) {
			System.out.println("error");
		}
	}

	public Board parseFile() throws Exception {
		int [][] matrix= new int[Board.SIZE][Board.SIZE];
		int i=0;
		String line;
		char[] charLine;
		BufferedReader inputFile = new BufferedReader(new FileReader(file));
		while ((line = inputFile.readLine()) != null) {
			if (line.length() != Board.SIZE || i > Board.SIZE) {
				throw new Exception();
			}
			charLine = line.toCharArray();
			for (int j = 0; j < Board.SIZE; j++) {
				switch (charLine[j]) {
				case ' ':
					matrix[i][j]=0;
					break;
				case '1':
					matrix[i][j]=1;
					break;
				case '2':
					matrix[i][j]=2;
					break;
				default:
					throw new Exception();
				}
			}
			i++;
		}
		if(i!=Board.SIZE){
			throw new Exception();
		}
		Board board=new Board(matrix);
		return board;
	}

	public static void main(String[] args) throws Exception {
		File file = new File("./src/parser/lala.txt");
		Parser test = new Parser(file);
		Board boardd = test.parseFile();
		System.out.println(boardd);
	}

}
