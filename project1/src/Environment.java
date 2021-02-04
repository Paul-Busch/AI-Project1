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
		// TODO (Paul) test function with printout

		List<Coordinates> fillMyPawns = new ArrayList<Coordinates>();
		for (int y = 1; y <= 2; y++) {
			for (int x = 1; x <= sizeX; x++) {
				Coordinates fillInCoordinates = new Coordinates(x, y);
				fillMyPawns.add(fillInCoordinates);
			  }
		  }
		  // TODO (Paul) test function with printout
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
		List<int[]> legalMoves = new LinkedList<int[]>();
		
		//TODO (Done:Linus) legalMoves
		for(int i=0; i<state.myPawns.size(); i++){
			//go through all element of myPawns and check what they can do
			if(state.myPawns.get(i) != null){
				Coordinates currentCoordinates = new Coordinates(state.myPawns.get(i).x, state.myPawns.get(i).y);
				Coordinates goForwardCoordinates = new Coordinates(state.myPawns.get(i).x, state.myPawns.get(i).y + 1);
				Coordinates hitLeftCoordinates = new Coordinates(state.myPawns.get(i).x - 1, state.myPawns.get(i).y + 1);
				Coordinates hitRightCoordinates = new Coordinates(state.myPawns.get(i).x + 1, state.myPawns.get(i).y + 1);
				int[] legalMove = {currentCoordinates.x,currentCoordinates.y,0,0};
				//go forward
				if(!state.myPawns.contains(goForwardCoordinates) && !state.opponentPawns.contains(goForwardCoordinates)){
					legalMove[2] = goForwardCoordinates.x;
					legalMove[3] = goForwardCoordinates.y;
					legalMoves.add(legalMove);
				}
				//hit left
				if(!state.myPawns.contains(hitLeftCoordinates) && state.opponentPawns.contains(hitLeftCoordinates)){
					legalMove[2] = hitLeftCoordinates.x;
					legalMove[3] = hitLeftCoordinates.y;
					legalMoves.add(legalMove);
				}
				//hit right
				if(!state.myPawns.contains(hitRightCoordinates) && state.opponentPawns.contains(hitRightCoordinates)){
					legalMove[2] = hitRightCoordinates.x;
					legalMove[3] = hitRightCoordinates.y;
					legalMoves.add(legalMove);
				}

			} 
		}
		return legalMoves;
	}

	/**
	 * 
	 * @param s state
	 * @param move int[]
	 * @return the state resulting from doing moves in s
	 */
	public  State getNextState(State s, int[] move) {
		State succState = s.clone();
		// TODO: (Laura) getNextState fill out this function
		return succState;
	}
}
