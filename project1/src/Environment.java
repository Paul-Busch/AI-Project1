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
	 * updates the current state of the environment based on the given action
	 * 
	 * @param a
	 */
	public void doAction(Action a) {
		currentState = getNextState(currentState, a);
	}

		/**
	 * updates the current state of the environment based on the opponents action
	 * 
	 * @param int[] lastMove
	 */

	public void updateEnvAfterOpponentAction(int[] lastMove) {
		int x1 = lastMove[0], y1 = lastMove[1], x2 = lastMove[2], y2 = lastMove[3];
		State succState = getCurrentState().clone();
		//TODO Paul? update the two lists with positions
		currentState = succState;
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
