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
		//TODO (Linus) legalMoves
		List<int[]> legalMoves = new LinkedList<int[]>();
		
		Coordinates currentCoordinates = new Coordinates(0,0);
		Coordinates goForwardCoordinates = new Coordinates(0,0);
		Coordinates hitLeftCoordinates = new Coordinates(0,0);
		Coordinates hitRightCoordinates = new Coordinates(0,0);
		int[] legalMove = {0,0,0,0};


		if(state.myTurn){
			//go through all element of myPawns and check what they can do
			for(int i=0; i<state.myPawns.size(); i++){
				
				if(state.myPawns.get(i) != null){
					
					currentCoordinates.x = state.myPawns.get(i).x; currentCoordinates.y = state.myPawns.get(i).y;

					//calculate the next position
					if (role.equals("white")){
						goForwardCoordinates.x = currentCoordinates.x; goForwardCoordinates.y = currentCoordinates.y + 1;
						hitLeftCoordinates.x = currentCoordinates.x - 1; hitLeftCoordinates.y = currentCoordinates.y + 1;
						hitRightCoordinates.x = currentCoordinates.x + 1; hitRightCoordinates.y = currentCoordinates.y + 1;
					} else if(role.equals("black")){
						goForwardCoordinates.x = currentCoordinates.x; goForwardCoordinates.y = currentCoordinates.y - 1;
						hitLeftCoordinates.x = currentCoordinates.x - 1; hitLeftCoordinates.y = currentCoordinates.y - 1;
						hitRightCoordinates.x = currentCoordinates.x + 1; hitRightCoordinates.y = currentCoordinates.y - 1;
					}

					//write current position into the first two parameters of a legal move
					legalMove[0] = currentCoordinates.x;
					legalMove[1] = currentCoordinates.y;
					
					//check the constrains for a legal move 
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
		}  else if(!state.myTurn){
			//legal moves for own white

			//TODO (Linus) legalMoves
			for(int i=0; i<state.opponentPawns.size(); i++){
				//go through all element of myPawns and check what they can do
				if(state.opponentPawns.get(i) != null){
					
					currentCoordinates.x = state.opponentPawns.get(i).x; currentCoordinates.y = state.opponentPawns.get(i).y;

					if (role.equals("white")){
						goForwardCoordinates.x = currentCoordinates.x; goForwardCoordinates.y = currentCoordinates.y + 1;
						hitLeftCoordinates.x = currentCoordinates.x - 1; hitLeftCoordinates.y = currentCoordinates.y + 1;
						hitRightCoordinates.x = currentCoordinates.x + 1; hitRightCoordinates.y = currentCoordinates.y + 1;
					} else if(role.equals("black")){
						goForwardCoordinates.x = currentCoordinates.x; goForwardCoordinates.y = currentCoordinates.y - 1;
						hitLeftCoordinates.x = currentCoordinates.x - 1; hitLeftCoordinates.y = currentCoordinates.y - 1;
						hitRightCoordinates.x = currentCoordinates.x + 1; hitRightCoordinates.y = currentCoordinates.y - 1;
					}

					legalMove[0] = currentCoordinates.x;
					legalMove[1] = currentCoordinates.y;
					//go forward
					if(!state.opponentPawns.contains(goForwardCoordinates) && !state.myPawns.contains(goForwardCoordinates)){
						if (goForwardCoordinates.x >= 1 && goForwardCoordinates.x <= sizeX && goForwardCoordinates.y >= 1 && goForwardCoordinates.y <= sizeY){
							legalMove[2] = goForwardCoordinates.x;
							legalMove[3] = goForwardCoordinates.y;
							legalMoves.add(legalMove);
						}

					}
					//hit left
					if(!state.opponentPawns.contains(hitLeftCoordinates) && state.myPawns.contains(hitLeftCoordinates)){
						if (hitLeftCoordinates.x >= 1 && hitLeftCoordinates.x <= sizeX && hitLeftCoordinates.y >= 1 && hitLeftCoordinates.y <= sizeY){
							legalMove[2] = hitLeftCoordinates.x;
							legalMove[3] = hitLeftCoordinates.y;
							legalMoves.add(legalMove);
						}
					}
					//hit right
					if(!state.opponentPawns.contains(hitRightCoordinates) && state.myPawns.contains(hitRightCoordinates)){
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
		Coordinates nextCoordinates = new Coordinates(move[2], move[3]);
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
				//TODO (Laura) Linus added this since hit plavers must be removed
				if (succState.opponentPawns.contains(nextCoordinates)){
					succState.opponentPawns.remove(nextCoordinates);
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
				//TODO (Laura) Linus added this since hit plavers must be removed
				if (succState.myPawns.contains(nextCoordinates)){
					succState.myPawns.remove(nextCoordinates);
				}
			}
		}
		return succState;
	}
}
