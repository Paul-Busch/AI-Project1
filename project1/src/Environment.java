import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Environment {



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
		// TODO Paul test function with printout

		List<Coordinates> fillMyPawns = new ArrayList<Coordinates>();
		for (int y = 1; y <= 2; y++) {
			for (int x = 1; x <= sizeX; x++) {
				Coordinates fillInCoordinates = new Coordinates(x, y);
				fillMyPawns.add(fillInCoordinates);
			  }
		  }
		  // TODO Paul test function with printout
		  List<Coordinates> fillOpponentPawns = new ArrayList<Coordinates>();
		  for (int y = sizeY-1; y <= sizeY; y++) {
			  for (int x = 1; x <= sizeX; x++) {
				  Coordinates fillInCoordinates = new Coordinates(x, y);
				  fillOpponentPawns.add(fillInCoordinates);
				}
			}
		currentState.myPawns = fillMyPawns;
		currentState.opponentPawns = fillOpponentPawns;
		currentState.myTurn = true; //TODO (Later) true or false? --> check with color	
	}

	/**
	 * 
	 * @return the current state of the environment
	 */
	public State getCurrentState() {
		return currentState;
	}

	/**
	 * updates the current state of the environment based on the given move
	 * 
	 * @param move
	 */
	public void doMove(int[] move) {
		currentState = getNextState(currentState, move);
	}

	/**
	 * 
	 * @param state
	 * @return a list of moves that are possible in the given state
	 */
	public  List<int[]> legalMoves(State state) {
		List<int[]> moves = new LinkedList<int[]>();
		//TODO (soon) legalMoves
		return moves;
	}

	/**
	 * 
	 * @param s state
	 * @param move int[]
	 * @return the state resulting from doing moves in s
	 */
	public  State getNextState(State s, int[] move) {
		State succState = s.clone();
		// TODO: Laura fill out this function
		return succState;
	}
}
