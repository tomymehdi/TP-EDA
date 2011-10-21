package parsing;

import java.io.File;

public class ArgumentParsing {

	private String[] args;
	private boolean isVisual, timed, pruned, DOT;
	private File file;
	private int player, level;

	public ArgumentParsing(String[] args){
		this.args=args;
		parse();
	}

	private void parse(){
		int i=0;
		if(args.length==0){
			throw new ParsingException();
		}
		i=setGameMode(i);
		i=setAIProcessing(i);
		i=setOptionals(i);
		if(i!=args.length){
			throw new ParsingException();
		}

	}

	private int setGameMode(int i){
		if(args[i].equals("-visual")){
			if(args.length<3){
				throw new ParsingException();
			}
			isVisual=true;
			return i+1;
		}else if(args[i].equals("-file")){
			if(args.length<6){
				throw new ParsingException();
			}
			i++;
			String name = "./"+args[i];
			file = new File(name);
			i++;
			if(!args[i].equals("-player")){
				throw new ParsingException();
			}else{
				i++;
				player=Integer.valueOf(args[i]);
				if(!(player==1||player==2)){
					throw new ParsingException();
				}
				return i+1;
			}

		}else{
			throw new ParsingException();
		}
	}

	private int setAIProcessing(int i){
		if(args[i].equals("-maxtime") || args[i].equals("-depth")){
			if(args[i].equals("-maxtime")){
				timed=true;
			}
			i++;
			try{
				level=Integer.valueOf(args[i]);
			}catch(NumberFormatException e){
				throw new ParsingException();
			}
			return i+1;
		}else{
			throw new ParsingException();
		}
	}

	private int setOptionals(int i){
		if(args.length==i+1){ //hay uno de los dos args
			if(args[i].equals("-prune")){
				pruned=true;
			}else if(args[i].equals("-tree")){
				if(isVisual){
					throw new ParsingException();
				}
				DOT=true;
			}else{
				throw new ParsingException();
			}
			return i+1;
		}
		if(args.length==i+2){ //estan ambos
			if(args[i].equals("-prune")){
				pruned=true;
				i++;
			}else{
				throw new ParsingException();
			}
			if(args[i].equals("-tree")){
				if(isVisual){
					throw new ParsingException();
				}
				DOT=true;
			}else{
				throw new ParsingException();
			}
			return i+1;
		}
		return i;
	}
	
	public File getFile() {
		return file;
	}
	public int getLevel() {
		return level;
	}
	public int getStartingPlayer() {
		return player;
	}
	public boolean isDOT() {
		return DOT;
	}
	public boolean isPruned() {
		return pruned;
	}
	public boolean isTimed() {
		return timed;
	}
	public boolean isVisual() {
		return isVisual;
	}
}
