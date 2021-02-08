import java.util.LinkedList;
import java.util.List;

public class Node {
	public State state;
	public Node parent;
	public int[] move;
	public int depth;
	public int evaluation;

	/**
	 * create the root node of the search tree
	 * @param state the state belonging to this node
	 * @param val the evaluation of this node
	 */
	public Node(State state, int val) {
		this.parent = null;
		this.state = state;
		this.move = null;
		this.depth = 0;
		this.evaluation = val;
	}
	/**
	 * create a new node
	 * @param parent the parent of the node
	 * @param state the state belonging to this node
	 * @param action the action that was executed to get to this node
	 * @param eval the evaluation of this node
	 */
	public Node(Node parent, State state, int[] move, int eval) {
		this.parent = parent;
		this.state = state;
		this.move = move;
		this.depth = parent.depth + 1;
		this.evaluation = eval;
	}
	


	public String toString() {
		return "Node{depth: " + depth + ", value: " + evaluation + ", state: " + state + ", path: " + getPlan() + "}";
	}
}