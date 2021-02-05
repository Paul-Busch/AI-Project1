import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.*; 

public class Environment {



	protected int sizeX;
	protected int sizeY;
	protected State currentState;
	protected String role;

	public Environment(String role, int sizeX, int sizeY) {
		init(role, sizeX, sizeY);
	}

	public void init(String role, int sizeX, int sizeY) {
		currentState = new State();
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.role = role;
		// TODO (Paul) test function with printout

		if (role.equals("white")){
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


		} else if(role.equals("black")){
			List<Coordinates> fillOpponentPawns = new ArrayList<Coordinates>();
			for (int y = 1; y <= 2; y++) {
				for (int x = 1; x <= sizeX; x++) {
					Coordinates fillInCoordinates = new Coordinates(x, y);
					fillOpponentPawns.add(fillInCoordinates);
				}
			}
			
			// TODO (Paul) test function with printout
			List<Coordinates> fillMyPawns = new ArrayList<Coordinates>();
			for (int y = sizeY-1; y <= sizeY; y++) {
				for (int x = 1; x <= sizeX; x++) {
					Coordinates fillInCoordinates = new Coordinates(x, y);
					fillMyPawns.add(fillInCoordinates);
				}
			}
			currentState.myPawns = fillMyPawns;
			currentState.opponentPawns = fillOpponentPawns;
		}
		

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
		
		if((role.equals("white") && state.myTurn) || (role.equals("black") && !state.myTurn)){
			//legal moves for white

			//TODO (Linus) legalMoves
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
						if (goForwardCoordinates.x >= 1 && goForwardCoordinates.x <= sizeX && goForwardCoordinates.y >= 1 && goForwardCoordinates.y <= sizeY){
							legalMove[2] = goForwardCoordinates.x;
							legalMove[3] = goForwardCoordinates.y;
							legalMoves.add(legalMove);
						}

					}
					//hit left
					if(!state.myPawns.contains(hitLeftCoordinates) && state.opponentPawns.contains(hitLeftCoordinates)){
						if (hitLeftCoordinates.x >= 1 && hitLeftCoordinates.x <= sizeX && hitLeftCoordinates.y >= 1 && hitLeftCoordinates.y <= sizeY){
							legalMove[2] = hitLeftCoordinates.x;
							legalMove[3] = hitLeftCoordinates.y;
							legalMoves.add(legalMove);
						}
					}
					//hit right
					if(!state.myPawns.contains(hitRightCoordinates) && state.opponentPawns.contains(hitRightCoordinates)){
						if (hitRightCoordinates.x >= 1 && hitRightCoordinates.x <= sizeX && hitRightCoordinates.y >= 1 && hitRightCoordinates.y <= sizeY){
							legalMove[2] = hitRightCoordinates.x;
							legalMove[3] = hitRightCoordinates.y;
							legalMoves.add(legalMove);
						}
					}
				} 
			}
			
		} else if((role.equals("black") && state.myTurn) || (role.equals("white") && !state.myTurn)){
			//legal moves for black

			//TODO (Linus) legalMoves
			for(int i=0; i<state.myPawns.size(); i++){
				//go through all element of myPawns and check what they can do
				if(state.myPawns.get(i) != null){
					Coordinates currentCoordinates = new Coordinates(state.myPawns.get(i).x, state.myPawns.get(i).y);
					Coordinates goForwardCoordinates = new Coordinates(state.myPawns.get(i).x, state.myPawns.get(i).y - 1);
					Coordinates hitLeftCoordinates = new Coordinates(state.myPawns.get(i).x - 1, state.myPawns.get(i).y - 1);
					Coordinates hitRightCoordinates = new Coordinates(state.myPawns.get(i).x + 1, state.myPawns.get(i).y - 1);
					int[] legalMove = {currentCoordinates.x,currentCoordinates.y,0,0};
					//go forward
					if(!state.myPawns.contains(goForwardCoordinates) && !state.opponentPawns.contains(goForwardCoordinates)){
						if (goForwardCoordinates.x >= 1 && goForwardCoordinates.x <= sizeX && goForwardCoordinates.y >= 1 && goForwardCoordinates.y <= sizeY){
							legalMove[2] = goForwardCoordinates.x;
							legalMove[3] = goForwardCoordinates.y;
							legalMoves.add(legalMove);
						}

					}
					//hit left
					if(!state.myPawns.contains(hitLeftCoordinates) && state.opponentPawns.contains(hitLeftCoordinates)){
						if (hitLeftCoordinates.x >= 1 && hitLeftCoordinates.x <= sizeX && hitLeftCoordinates.y >= 1 && hitLeftCoordinates.y <= sizeY){
							legalMove[2] = hitLeftCoordinates.x;
							legalMove[3] = hitLeftCoordinates.y;
							legalMoves.add(legalMove);
						}
					}
					//hit right
					if(!state.myPawns.contains(hitRightCoordinates) && state.opponentPawns.contains(hitRightCoordinates)){
						if (hitRightCoordinates.x >= 1 && hitRightCoordinates.x <= sizeX && hitRightCoordinates.y >= 1 && hitRightCoordinates.y <= sizeY){
							legalMove[2] = hitRightCoordinates.x;
							legalMove[3] = hitRightCoordinates.y;
							legalMoves.add(legalMove);
						}
					}
				} 
			}
		}

		return legalMoves;
		
	}

	/**
	 * 
	 * @param s state
	 * @param move int[] (x1, y1, x2, y2)
	 * @return the state resulting from doing moves in s
	 */
	public  State getNextState(State s, int[] move) {
		State succState = s.clone();
		// TODO: (Done) getNextState fill out this function
		if (succState.myTurn==true){
			//iterate over the my pawns list of coordinates 
			for (Coordinates element : succState.myPawns){
				//for each element if it has the same coordinates as the first two positions of move
				if (element.x == move[0] && element.y == move[1]){
					//set element to the new coordinates (which are the last two positions of move array)
					element.x = move[2];
					element.y = move[3];
				}
			}
		}
		else{
			//iterate over the opponent pawns list of coordinates 
			for (Coordinates element : succState.opponentPawns){
				//for each element if it has the same coordinates as the first two positions of move array
				if (element.x == move[0] && element.y == move[1]){
					//set element to the new coordinates (which are the last two positions of move array)
					element.x = move[2];
					element.y = move[3];
				}
			}
		}
		return succState;
	}
}
