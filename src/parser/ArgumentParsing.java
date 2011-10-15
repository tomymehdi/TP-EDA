package parser;

import java.io.File;

public class ArgumentParsing {

	private String[] args;
	boolean isVisual, timed, pruned, DOT;
	String fileName;
	int player, level;

	public ArgumentParsing(String[] args){
		this.args=args;
	}

	public void parse(){
		int i=0;
		i=setGameMode(i);
		i=setAIProcessing(i);
		i=setOptionals(i);
		if(i!=args.length){
			throw new ParsingException();
		}

	}

	private int setGameMode(int i){
		if(args[i].equals("-visual")){
			isVisual=true;
			return i+1;
		}else if(args[i].equals("-file")){
			i++;
			fileName=args[i];
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
			level=Integer.valueOf(args[i]);
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
	
//	private void print(){
//		System.out.println("visual " + isVisual +"\ntiempo " + timed + "\npoda " + pruned + "\ndot " + DOT +"\nnivel " + level);
//		if(!isVisual){
//			System.out.println("\nempieza " + player + "\narchivo " + fileName);
//		}
//	}
//	
//	public static void main(String[] args) {
//		String[] s={"-visual",  "2", "-depth", "6", };
//		ArgumentParsing p=new ArgumentParsing(s);
//		p.parse();
//		p.print();
//	}

}
