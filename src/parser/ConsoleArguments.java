package parser;

public class ConsoleArguments {

	private String[] args;
	private boolean visual = false;
	private boolean file = false;
	private boolean player = false;
	private boolean maxtime = false;
	private boolean depth = false;
	private boolean prune = false;
	private boolean tree = false;

	public ConsoleArguments(String[] args) {
		this.args = args;
	}

	public static void main(String[] args) {
		for (String s : args) {
			System.out.println(s);
		}
		ConsoleArguments aux = new ConsoleArguments(args);
		aux.verifyArguments();
		System.out.println(aux.visual);
		System.out.println(aux.file);
		System.out.println(aux.player);
		System.out.println(aux.maxtime);
		System.out.println(aux.depth);
		System.out.println(aux.prune);
		System.out.println(aux.tree);
	}

	public void verifyArguments() {
		String sArgs = "";
		for (String s : args) {
			sArgs += s;
		}
		if (sArgs.contains("-visual")) {
			visual = true;
		}
		if (sArgs.contains("-file")) {
			file = true;
		}
		if (sArgs.contains("-player")) {
			player = true;
		}
		if (sArgs.contains("-maxtime")) {
			maxtime = true;
		}
		if (sArgs.contains("-depth")) {
			depth = true;
		}
		if (sArgs.contains("-prune")) {
			prune = true;
		}
		if (sArgs.contains("-tree")) {
			tree = true;
		}

		if (!((visual & !file & !player) || (!visual & file & player))) {
			throw new RuntimeException();
		}

		if (!((maxtime & !depth) || (!maxtime & depth))) {
			throw new RuntimeException();
		}

	}

}
