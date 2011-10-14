package parser;

import java.util.HashMap;
import java.util.Map;

public class ConsoleArguments {

	private String[] args;
	private Map<String,Boolean> arguments = new HashMap<String,Boolean>();

	public ConsoleArguments(String[] args) {
		this.args = args;
		arguments.put("-visual", false);
		arguments.put("-file", false);
		arguments.put("-player", false);
		arguments.put("-maxtime", false);
		arguments.put("-depth", false);
		arguments.put("-prune", false);
		arguments.put("-tree", false);
	}

	public static void main(String[] args) {
		for (String s : args) {
			System.out.println(s);
		}
		ConsoleArguments aux = new ConsoleArguments(args);
		aux.verifyArguments();
		System.out.println(aux.arguments.get("-visual"));
		System.out.println(aux.arguments.get("-file"));
		System.out.println(aux.arguments.get("-player"));
		System.out.println(aux.arguments.get("-maxtime"));
		System.out.println(aux.arguments.get("-depth"));
		System.out.println(aux.arguments.get("-prune"));
		System.out.println(aux.arguments.get("-tree"));
	}

	public void verifyArguments() {
		String sArgs = "";
		for (String s : args) {
			sArgs += s;
		}
		if (sArgs.contains("-visual")) {
			arguments.put("-visual", true);
		}
		if (sArgs.contains("-file")) {
			arguments.put("-file", true);
		}
		if (sArgs.contains("-player")) {
			arguments.put("-player", true);
		}
		if (sArgs.contains("-maxtime")) {
			arguments.put("-maxtime", true);
		}
		if (sArgs.contains("-depth")) {
			arguments.put("-depth", true);
		}
		if (sArgs.contains("-prune")) {
			arguments.put("-prune", true);
		}
		if (sArgs.contains("-tree")) {
			arguments.put("-tree", true);
		}

		if (!((visual & !file & !player) || (!visual & file & player))) {
			throw new RuntimeException();
		}

		if (!((maxtime & !depth) || (!maxtime & depth))) {
			throw new RuntimeException();
		}
		
		

	}

}
