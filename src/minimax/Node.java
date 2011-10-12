package minimax;

import java.util.LinkedList;
import java.util.List;

public abstract class Node {
	
	protected List<Node> son = new LinkedList<Node>();
	protected int value;
	
	public abstract int get();
}
