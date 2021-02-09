import java.lang.management.MemoryPoolMXBean;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Environment {



	protected int sizeX;
	protected int sizeY;
	protected State currentState;
	protected String role;
	protected int evalScore;

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


		if(state.myTurn){
			//go through all element of myPawns and check what they can do
			for(int i=0; i<state.myPawns.size(); i++){
				
				//TODO initialize every time


				if(state.myPawns.get(i) != null){
					
					currentCoordinates.x = (int) state.myPawns.get(i).x; currentCoordinates.y = (int) state.myPawns.get(i).y;

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

				
					
					//check the constrains for a legal move 
					//go forward
					if(!state.myPawns.contains(goForwardCoordinates) && !state.opponentPawns.contains(goForwardCoordinates)){
						if (goForwardCoordinates.x >= 1 && goForwardCoordinates.x <= sizeX && goForwardCoordinates.y >= 1 && goForwardCoordinates.y <= sizeY){
							
							int[] legalMove = new int[4];
							legalMove[0] = currentCoordinates.x;
							legalMove[1] = currentCoordinates.y;
							legalMove[2] = goForwardCoordinates.x;
							legalMove[3] = goForwardCoordinates.y;
							legalMoves.add(legalMove);
						}
					}
					//hit left
					if(!state.myPawns.contains(hitLeftCoordinates) && state.opponentPawns.contains(hitLeftCoordinates)){
						if (hitLeftCoordinates.x >= 1 && hitLeftCoordinates.x <= sizeX && hitLeftCoordinates.y >= 1 && hitLeftCoordinates.y <= sizeY){
							int[] legalMove = new int[4];
							legalMove[0] = currentCoordinates.x;
							legalMove[1] = currentCoordinates.y;
							legalMove[2] = hitLeftCoordinates.x;
							legalMove[3] = hitLeftCoordinates.y;
							legalMoves.add(legalMove);
						}
					}
					//hit right
					if(!state.myPawns.contains(hitRightCoordinates) && state.opponentPawns.contains(hitRightCoordinates)){
						if (hitRightCoordinates.x >= 1 && hitRightCoordinates.x <= sizeX && hitRightCoordinates.y >= 1 && hitRightCoordinates.y <= sizeY){
							int[] legalMove = new int[4];
							legalMove[0] = currentCoordinates.x;
							legalMove[1] = currentCoordinates.y;
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
					
					//go forward
					if(!state.opponentPawns.contains(goForwardCoordinates) && !state.myPawns.contains(goForwardCoordinates)){
						if (goForwardCoordinates.x >= 1 && goForwardCoordinates.x <= sizeX && goForwardCoordinates.y >= 1 && goForwardCoordinates.y <= sizeY){
							int[] legalMove = new int[4];
							legalMove[0] = currentCoordinates.x;
							legalMove[1] = currentCoordinates.y;
							legalMove[2] = goForwardCoordinates.x;
							legalMove[3] = goForwardCoordinates.y;
							legalMoves.add(legalMove);
						}

					}
					//hit left
					if(!state.opponentPawns.contains(hitLeftCoordinates) && state.myPawns.contains(hitLeftCoordinates)){
						if (hitLeftCoordinates.x >= 1 && hitLeftCoordinates.x <= sizeX && hitLeftCoordinates.y >= 1 && hitLeftCoordinates.y <= sizeY){
							int[] legalMove = new int[4];
							legalMove[0] = currentCoordinates.x;
							legalMove[1] = currentCoordinates.y;
							legalMove[2] = hitLeftCoordinates.x;
							legalMove[3] = hitLeftCoordinates.y;
							legalMoves.add(legalMove);
						}
					}
					//hit right
					if(!state.opponentPawns.contains(hitRightCoordinates) && state.myPawns.contains(hitRightCoordinates)){
						if (hitRightCoordinates.x >= 1 && hitRightCoordinates.x <= sizeX && hitRightCoordinates.y >= 1 && hitRightCoordinates.y <= sizeY){
							int[] legalMove = new int[4];
							legalMove[0] = currentCoordinates.x;
							legalMove[1] = currentCoordinates.y;
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
		Coordinates currentCoordinates = new Coordinates(move[0], move[1]);
		Coordinates nextCoordinates = new Coordinates(move[2], move[3]);
		// TODO: (Done) getNextState fill out this function
		// TODO: (Afterwards) check if this is possible: if (succState.myTurn==true){
		// TODO: cange myTurn?
		if (succState.myPawns.contains(currentCoordinates)){
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
		succState.myTurn = !s.myTurn;
		return succState;
	}
	// TODO (Paul) implement the evalFunc 
	public int eval(State state) {
		// First search for most advanced pawn
		// Then calculate the distance from the most advanced pawn to line 1 or to sizeY (depending on the color of the player)
		// With the minDistance for each player calculate the evalScore (see pdf for description of the evalFunction)
		// return evalScore


		//initialize tempVar with the first element so that it is safe to return a list element and not the initialization 
		int myTempVar = state.myPawns.get(0).y;
		int opponentTempVar = state.opponentPawns.get(0).y;
		int myMinDistance = 0;
		int opponentMinDistance = 0;
		


		// check which color the agent is currently playing to see whether the goalline is line 1 or sizeY
		if (role.equals("black")) {
			// search for max element in myPawns and opponentPawns
			// For loop is starting with 1 because TempVar is initialized with the zero element of the list
			for (int i = 1; i < state.myPawns.size(); i++) {
				if (state.myPawns.get(i).y < myTempVar) {
					myTempVar = state.myPawns.get(i).y;
				}
			}
			for (int i = 1; i < state.opponentPawns.size(); i++) {
				if (state.opponentPawns.get(i).y > opponentTempVar) {
					opponentTempVar = state.opponentPawns.get(i).y;
				}
			}
			myMinDistance = myTempVar - 1;
			opponentMinDistance = sizeY - opponentTempVar;

			// calculating the evalScore for role = black, it is the same statements for role = white because the variables that are used do not depend on the color
			if (!(myMinDistance == 0) && !(legalMoves(state).size() == 0))  {
				evalScore = opponentMinDistance - myMinDistance;
			} else if (myMinDistance == 0) {
				evalScore = 100;
			} else if (opponentMinDistance == 0) {
				evalScore = -100;
			} else {
				evalScore = 0;
			}

		} else {
			for (int i = 1; i < state.myPawns.size(); i++) {
				if (state.myPawns.get(i).y > myTempVar) {
					myTempVar = state.myPawns.get(i).y;
				}
			}
			for (int i = 1; i < state.opponentPawns.size(); i++) {
				if (state.opponentPawns.get(i).y < opponentTempVar) {
					opponentTempVar = state.opponentPawns.get(i).y;
				}
			}
				
			myMinDistance = sizeY - myTempVar;
			opponentMinDistance = opponentTempVar - 1;
			 
			// calculating the evalScore for role = white
			if (!(myMinDistance == 0) && !(legalMoves(state).size() == 0))  {
				evalScore = opponentMinDistance - myMinDistance;
			} else if (myMinDistance == 0) {
				evalScore = 100;
			} else if (opponentMinDistance == 0) {
				evalScore = -100;
			} else {
				evalScore = 0;
			}
		}	
		return evalScore;
	}
}
