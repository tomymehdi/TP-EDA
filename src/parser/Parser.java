package parser;

import game.Board;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
	
	private File file;
	private BufferedReader inputFile;
	
	public Parser(File file) {
		this.file = file;
		try {
			this.inputFile = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO
			e.printStackTrace();
		}
	}
	
	public Board parseFile() throws IOException{
			Board board = new Board();
			String line;
			BufferedReader inputFile = new BufferedReader(new FileReader(file));
			while ((line = inputFile.readLine()) != null) {		
				
			}
			
		return board;
	}
	
}
