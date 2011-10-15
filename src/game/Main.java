package game;

import gui.Window;

import java.io.File;
import java.io.IOException;

import AI.MiniMaxTree;

import parser.ArgumentParsing;
import parser.BoardParser;


public class Main {
	public static void main(String[] args) {
		ArgumentParsing argParser = new ArgumentParsing(args);
		if(argParser.isVisual()){
			Window w = new Window(argParser.getLevel(), argParser.isPruned(), argParser.isTimed());
		}else{
			try{
				BoardParser boardParser = new BoardParser(argParser.getFile());
				Board board= boardParser.parseFile();
				MiniMaxTree tree= new MiniMaxTree(argParser.getLevel(), board, argParser.isPruned(), argParser.isDOT());
				Position pos =tree.getNextMove();
				System.out.println(pos.toString());
			}catch(Exception e){
				System.out.println("An error occurred");

			}
		
		}
	}
}