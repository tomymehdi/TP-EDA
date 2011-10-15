package game;

import java.io.File;

import parser.ArgumentParsing;

public class Main {
	public static void main(String[] args) {
		ArgumentParsing argPars = new ArgumentParsing(args);
		if(argPars.isVisual()){
			
		}else{
			int player=argPars.getStartingPlayer();
			File file = argPars.getFile();
			boolean timed = argPars.isTimed();
			int level=argPars.getLevel();
		}
	}
}
