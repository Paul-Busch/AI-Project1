import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Environment {


	protected List<Coordinates> myPawns;
	protected List<Coordinates> opponentPawns;
	protected int sizeX;
	protected int sizeY;
	protected State currentState;

	public Environment(String role, int sizeX, int sizeY) {
		init(role, sizeX, sizeY);
	}

	public void init(String role, int sizeX, int sizeY) {
		currentState = new State();
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		// TODO Paul Initialize and fill pawns_white <List(Coordinates)> and for black
		List<Coordinates> fillMyPawns = new ArrayList<Coordinates>();
		// Coordinates coordinates1 = new Coordinates(1, 1);
		// Coordinates coordinates_last1 = new Coordinates(sizeX, 1);
		

		
	}

	/**
	 * 
	 * @return the current state of the environment
	 */
	public State getCurrentState() {
		return currentState;
	}

	/**
	 * updates the current state of the environment based on the given action
	 * 
	 * @param a
	 */
	public void doAction(Action a) {
		currentState = getNextState(currentState, a);
	}

	/**
	 * 
	 * @param state
	 * @return a list of actions that are possible in the given state
	 */
	public  List<Action> legalMoves(State state) {
		List<Action> moves = new LinkedList<Action>();
		return moves;
	}

	/**
	 * 
	 * @param s state
	 * @param a action
	 * @return the state resulting from doing a in s
	 */
	public  State getNextState(State s, Action a) {
		State succState = s.clone();
		// TODO: Laura fill out this function
		return succState;
	}
}
