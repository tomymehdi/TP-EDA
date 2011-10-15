package AI;

import game.Board;
import game.Position;

import java.io.FileWriter;
import java.io.IOException;

public class MiniMaxTree {

	private Node root;
	int maxLevel;
	boolean prune;
	boolean DOT;

	public MiniMaxTree(int level, Board board, boolean prune, boolean DOT) {
		this.maxLevel = level;
		root = new MaxNode(board, null);
		this.prune = prune;
	}

	public Position getNextMove() {

		Position pos = root.nextMove(maxLevel, 0, prune, Integer.MIN_VALUE);
		if (pos != null) {
			if (DOT) {
				generateDOT();
			}
			return pos;
		}
		return null;
	}

	public void generateDOT() {
		try {
			int i = 0, aux;
			FileWriter fr = new FileWriter("./tree.dot");
			fr.append("digraph {\n");
			fr.append("Root [shape=box, color=red, style=filled, label=\"START "
					+ root.value + "\"];\n");
			boolean b;
			for (Node son : root.childs) {
				if (root.value == son.value) {
					b = true;
				} else {
					b = false;
				}
				aux = i + 1;
				i = son.toDOT(fr, b, ++i);
				fr.append("Root -> " + aux + ";\n");
			}

			fr.append("}");
			fr.close();
		} catch (IOException e) {
			System.out.println("error");
		}
	}

	public static void main(String[] args) {
		MiniMaxTree t = new MiniMaxTree(2, new Board(), false, true);
		t.getNextMove();
	}
}
