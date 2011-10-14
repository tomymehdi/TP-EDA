package parser;

import java.util.HashMap;
import java.util.Map;

public class ConsoleArguments {

	private String[] args;
	private Map<String, Argument> arguments = new HashMap<String, Argument>();

	public ConsoleArguments(String[] args) {
		this.args = args;
		arguments.put("-visual", new Argument());
		arguments.put("-file", new Argument());
		arguments.put("-player", new ArgumentWithNumber());
		arguments.put("-maxtime", new ArgumentWithNumber());
		arguments.put("-depth", new ArgumentWithNumber());
		arguments.put("-prune", new Argument());
		arguments.put("-tree", new Argument());
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
			arguments.get("-visual").setIsOn(true);
		}
		if (sArgs.contains("-file")) {
			arguments.get("-file").setIsOn(true);
		}
		if (sArgs.contains("-player")) {
			arguments.get("-player").setIsOn(true);
			verifyNumberArgument("-player");
		}
		if (sArgs.contains("-maxtime")) {
			arguments.get("-maxtime").setIsOn(true);
			verifyNumberArgument("-maxtime");
		}
		if (sArgs.contains("-depth")) {
			arguments.get("-depth").setIsOn(true);
			verifyNumberArgument("-depth");
		}
		if (sArgs.contains("-prune")) {
			arguments.get("-prune").setIsOn(true);
		}
		if (sArgs.contains("-tree")) {
			arguments.get("-tree").setIsOn(true);
		}

		if (!((arguments.get("-visual").getIsOn()
				& !arguments.get("-file").getIsOn() & !arguments.get("-player")
				.getIsOn()) || (!arguments.get("-player").getIsOn()
				& arguments.get("-file").getIsOn() & arguments.get("-player")
				.getIsOn()))) {
			System.out.println("Incorrect arguments");
		}

		if (!((arguments.get("-maxtime").getIsOn()
				& !arguments.get("-depth").getIsOn() || (!arguments.get(
				"-maxtime").getIsOn() & arguments.get("-depth").getIsOn())))) {
			System.out.println("Incorrect arguments");
		}
	}

	private void verifyNumberArgument(String string) {
		int i = 0;
		while (!args[i].equals(string)) {
			i++;
		}
		i++;
		int value = Integer.parseInt(args[i]);
		if (!(value == 1 || value == 2)) {
			System.out.println("Incorrect arguments");
		}
		((ArgumentWithNumber)arguments.get(string)).setNumber(value);
		
	}
}
