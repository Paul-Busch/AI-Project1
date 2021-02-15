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
		currentState.myTurn = role.equals("white");
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
	}

	/**
	 * 
	 * @return the current state of the environment
	 */
	public State getCurrentState() {
		return currentState.clone();
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
	 * This function finds which moves for a given state are legal and returns them so that we can use
	 * them later and make sure that all the moves done by the agent are not illigal
	 * 
	 * @param state
	 * @return a list of moves that are possible in the given state
	 */
	public  List<int[]> legalMoves(State state) {
		
		List<int[]> legalMoves = new LinkedList<int[]>();

		Coordinates currentCoordinates = new Coordinates(0,0);
		Coordinates goForwardCoordinates = new Coordinates(0,0);
		Coordinates hitLeftCoordinates = new Coordinates(0,0);
		Coordinates hitRightCoordinates = new Coordinates(0,0);

		if(!state.myPawns.isEmpty() && !state.opponentPawns.isEmpty()){
			if(state.myTurn){
				//go through all element of myPawns and check what they can do
				for(int i=0; i<state.myPawns.size(); i++){
					
					//TODO initialize every time

					if(state.myPawns.get(i) != null){
						
						//take the x and y current coordinates from the given state 
						currentCoordinates.x = (int) state.myPawns.get(i).x; currentCoordinates.y = (int) state.myPawns.get(i).y;

						//calculate the next position depending on the role
						if (role.equals("white")){
							//we take the same x coordinate as tha one we are in and we take the next y coordinate to move forward
							goForwardCoordinates.x = currentCoordinates.x; goForwardCoordinates.y = currentCoordinates.y + 1;
							//we take the previous x coordinate than the one we are in to go to the left and we take the next y coordinate to move forward
							hitLeftCoordinates.x = currentCoordinates.x - 1; hitLeftCoordinates.y = currentCoordinates.y + 1;
							//we take the next x coordinate than the one we are in to go to the right and we take the next y coordinate to move forward
							hitRightCoordinates.x = currentCoordinates.x + 1; hitRightCoordinates.y = currentCoordinates.y + 1;
						} else if(role.equals("black")){
							//now we are in the other side of the board so instead to move forward we will move backwards from our point of view
							//we take the same x coordinate as tha one we are in and we take the previous y coordinate to move backwards
							goForwardCoordinates.x = currentCoordinates.x; goForwardCoordinates.y = currentCoordinates.y - 1;
							//we take the previous x coordinate than the one we are in to go to the left and we take the previous y coordinate to move backwards
							hitLeftCoordinates.x = currentCoordinates.x - 1; hitLeftCoordinates.y = currentCoordinates.y - 1;
							//we take the next x coordinate than the one we are in to go to the right and we take the previous y coordinate to move backwards
							hitRightCoordinates.x = currentCoordinates.x + 1; hitRightCoordinates.y = currentCoordinates.y - 1;
						}

					
						
						//check the constrains for a legal move 
						//go forward
						//the pawn will only move forward if in the next coordinate there's no other pawns 
						if(!state.myPawns.contains(goForwardCoordinates) && !state.opponentPawns.contains(goForwardCoordinates)){
							// the pawn will only move if the next coordinate is inside the board
							if (goForwardCoordinates.x >= 1 && goForwardCoordinates.x <= sizeX && goForwardCoordinates.y >= 1 && goForwardCoordinates.y <= sizeY){
								//legal move is an integer array of the form [x1, y1, x2, y2]
								int[] legalMove = new int[4];
								// x1 and y1 will indicate the current position
								legalMove[0] = currentCoordinates.x;
								legalMove[1] = currentCoordinates.y;
								//x2 and y2 will indicate the next position, which we checked that is legal to move to 
								legalMove[2] = goForwardCoordinates.x;
								legalMove[3] = goForwardCoordinates.y;
								
								//since the coordinates fullfill all the constraints we can add them as a legal move in the integer array of legal moves
								legalMoves.add(legalMove);
							}
						}
						//hit left
						// the pawn will only move left if in the next coordinate there's an opponent pawn
						if(!state.myPawns.contains(hitLeftCoordinates) && state.opponentPawns.contains(hitLeftCoordinates)){
							// the pawn will only move if the next coordinate is inside the board
							if (hitLeftCoordinates.x >= 1 && hitLeftCoordinates.x <= sizeX && hitLeftCoordinates.y >= 1 && hitLeftCoordinates.y <= sizeY){
								//same as before, since the coordinates fullfill all the constraints we can add them as a legal move in the integer array of legal moves
								int[] legalMove = new int[4];
								legalMove[0] = currentCoordinates.x;
								legalMove[1] = currentCoordinates.y;
								legalMove[2] = hitLeftCoordinates.x;
								legalMove[3] = hitLeftCoordinates.y;
								legalMoves.add(legalMove);
							}
						}
						//hit right
						// the pawn will only move right if in the next coordinate there's an opponent pawn
						if(!state.myPawns.contains(hitRightCoordinates) && state.opponentPawns.contains(hitRightCoordinates)){
							// the pawn will only move if the next coordinate is inside the board
							if (hitRightCoordinates.x >= 1 && hitRightCoordinates.x <= sizeX && hitRightCoordinates.y >= 1 && hitRightCoordinates.y <= sizeY){
								//same as before, since the coordinates fullfill all the constraints we can add them as a legal move in the integer array of legal moves
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
				//we follow the same procedure to find the legal moves our opponent can do in this state

				for(int i=0; i<state.opponentPawns.size(); i++){
					//go through all element of myPawns and check what they can do
					

					if(state.opponentPawns.get(i) != null){
						
						currentCoordinates.x = state.opponentPawns.get(i).x; currentCoordinates.y = state.opponentPawns.get(i).y;
						
						//now the point of view is the opposite than before (white will move backwards and black will move forward)
						if (role.equals("white")){
							goForwardCoordinates.x = currentCoordinates.x; goForwardCoordinates.y = currentCoordinates.y - 1;
							hitLeftCoordinates.x = currentCoordinates.x - 1; hitLeftCoordinates.y = currentCoordinates.y - 1;
							hitRightCoordinates.x = currentCoordinates.x + 1; hitRightCoordinates.y = currentCoordinates.y - 1;
						} else if(role.equals("black")){
							goForwardCoordinates.x = currentCoordinates.x; goForwardCoordinates.y = currentCoordinates.y + 1;
							hitLeftCoordinates.x = currentCoordinates.x - 1; hitLeftCoordinates.y = currentCoordinates.y + 1;
							hitRightCoordinates.x = currentCoordinates.x + 1; hitRightCoordinates.y = currentCoordinates.y + 1;
						}
						
						//to find the legal moves we do it the same way as before
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
		}
		return legalMoves;
		
	}

	/**
	 * This function gets the move we want to do and returns the new state with the move already done
	 * 
	 * @param s state
	 * @param move int[] (x1, y1, x2, y2), where x1 and y1 are the current position and x2 and y2 the next position
	 * @return the state resulting from doing moves in s
	 */
	public  State getNextState(State s, int[] move) {
		State succState = new State();
		succState = s.clone();
		Coordinates currentCoordinates = new Coordinates(move[0], move[1]);
		Coordinates nextCoordinates = new Coordinates(move[2], move[3]);
		if(!succState.myPawns.isEmpty() && !succState.opponentPawns.isEmpty()){
			//in order to see if it is our turn or the opponent's one
			if (succState.myPawns.contains(currentCoordinates)){
				//iterate over the my pawns list of coordinates 
				for (Coordinates element : succState.myPawns){
					//for each element if it has the same coordinates as the first two positions of move
					if (element.x == move[0] && element.y == move[1]){
						//set element to the new coordinates (which are the last two positions of move array)
						element.x = move[2];
						element.y = move[3];
					}
					// If a player is hit it have to be removed from the board (from the coordinates list)
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
					// If a player is hit it have to be removed from the board (from the coordinates list)
					if (succState.myPawns.contains(nextCoordinates)){
						succState.myPawns.remove(nextCoordinates);
					}
				}
			}
			//we have to change the turn after moving a pawn
			succState.myTurn = !s.myTurn;
			return succState;
		}else {
			System.out.println("something wrong with getNextState");
			return succState;
		}
	}
	
	/**
	 * This function evaluates the score of choosing an input state
	 * 
	 * @param s state
	 * @return the score (int) of choosing that state: 100 (win), 0 (draw), -100(lose)
	 */
	public int eval(State s) {
		State state = s.clone();
		/* 
		First search for most advanced pawn
		Then calculate the distance from the most advanced pawn to line 1 or to sizeY (depending on the color of the player)
		With the minDistance for each player calculate the evalScore
		return evalScore
		*/

		if(!state.myPawns.isEmpty() && !state.opponentPawns.isEmpty()){
			//initialize tempVar with the first element so that it is safe to return a list element and not the initialization 
			int myTempVar = state.myPawns.get(0).y;
			int opponentTempVar = state.opponentPawns.get(0).y;
			int myMinDistance = 0;
			int opponentMinDistance = 0;
			

		
			// check which color the agent is currently playing to see whether the goal line is line 1 or sizeY
			if (role.equals("black")) {
				// search for most advanced element in myPawns and opponentPawns
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
				//since we are from the point of view of black the distance left for the furthest pawn is its own y coordinate minus 1 (since the first row is row 1) 
				myMinDistance = myTempVar - 1;
				//in the case of the opponent the distance will be the height of the board minus its own y coordinate
				opponentMinDistance = sizeY - opponentTempVar;



				// calculating the evalScore for role = black, it is the same statements for role = white because the variables that are used do not depend on the color
				if (!(myMinDistance == 0) && !(opponentMinDistance == 0) && !(legalMoves(state).size() == 0) )  { 
					evalScore = (opponentMinDistance - myMinDistance) * 2 + (state.myPawns.size()- state.opponentPawns.size()); //take hit players into account the y position is valued higher
				// this means that the pawn reached the goal row, hence we win
				} else if (myMinDistance == 0) {
					evalScore = 100;
				// this means that an opponent pawn reached its goal row, hence we lose
				} else if (opponentMinDistance == 0) {
					evalScore = -100;
				// if there are no legal moves left it means that we cannot do anything 
				} else if(legalMoves(state).size() == 0){
					evalScore = 0;
				} else {
					//TODO: Delete debug
					System.out.println("there is a problem in eval()"); 
				}
			
			//same procedure but taking into account 'white' role instead of 'black' one
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

				if (!(myMinDistance == 0) && !(opponentMinDistance == 0) && !(legalMoves(state).size() == 0))  { 
					evalScore = (opponentMinDistance - myMinDistance ) * 2 + (state.myPawns.size()- state.opponentPawns.size());;
				} else if (myMinDistance == 0) {
					evalScore = 100;
				} else if (opponentMinDistance == 0) {
					evalScore = -100;
				} else if(legalMoves(state).size() == 0){
					evalScore = 0;
				} else {
					//TODO: Delete debug
					System.out.println("there is a problem in eval()"); 
				}
			}	
			return evalScore;
		}else if(state.myPawns.isEmpty()){
			return -100; 
		}else if(state.opponentPawns.isEmpty()){
			return 100; 
		}
		return 0; //TODO only for debug reasons
	}
}
