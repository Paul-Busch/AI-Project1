public class MyAgent implements Agent {


    
	private String role; // the name of this agent's role (white or black)
	private int playclock; // this is how much time (in seconds) we have before nextAction needs to return a move
	private boolean myTurnAgent; // whether it is this agent's turn or not
	private int sizeX, sizeY; // dimensions of the board
	private Environment env;

	Search search; 
	/*
		init(String role, int playclock) is called once before you have to select the first action. Use it to initialize the agent. role is either "white" or "black" and playclock is the number of seconds after which nextAction must return.
	*/
	//
    public void init(String role, int sizeX, int sizeY, int playclock) {
		env = new Environment(role, sizeX, sizeY);
		this.role = role;
		this.playclock = playclock;
		myTurnAgent = !role.equals("white");
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.search = new Search(env, playclock);
    }

	// lastMove is null the first time nextAction gets called (in the initial state)
	// otherwise it contains the coordinates x1,y1,x2,y2 of the move that the last player did
	/**
	 * 
	 * @param int[] lastMove 
	 * @return String with x1, x2, y1, y2 when its the agents turn, String "noop" when its not the agents turn
	 * This method returns the nextAction that is the bestMove found in miniMaxRoot
	 */
    public String nextAction(int[] lastMove) {
    	if (lastMove != null) {
			//we take the coordin
    		int x1 = lastMove[0], y1 = lastMove[1], x2 = lastMove[2], y2 = lastMove[3];
    		String roleOfLastPlayer;
    		if (myTurnAgent && role.equals("white") || !myTurnAgent && role.equals("black")) {
    			roleOfLastPlayer = "white";
    		} else {
    			roleOfLastPlayer = "black";
    		}
			System.out.println(roleOfLastPlayer + " moved from " + x1 + "," + y1 + " to " + x2 + "," + y2);
			
			// TODO: (Done:Linus) 1. update your internal world model according to the action that was just executed
			env.currentState = env.getNextState(env.getCurrentState(), lastMove);
    	}
		
    	// update turn (above that line it myTurn is still for the previous state)
		myTurnAgent = !myTurnAgent;
		if (myTurnAgent) {
			// TODO: (Later) 2. run alpha-beta search to determine the best move


			int x1,y1,x2,y2;
			int startMillis = (int) System.currentTimeMillis();
			int[] move = search.iterativeDeepening(env.getCurrentState(), startMillis);
			int totalRuntime = (int) System.currentTimeMillis() - startMillis;
			System.out.println("State expansions: " + search.stateExpansions + ", Current depth limit: " + search.depthLimit + ", Total runtime: " + totalRuntime + "ms");

			x1 = move[0];
			y1 = move[1];
			x2 = move[2];
			y2 = move[3];
			
			return "(move " + x1 + " " + y1 + " " + x2 + " " + y2 + ")";
			
		} else {
			return "noop";
		}
	}

	// is called when the game is over or the match is aborted
	//TODO Later @Override
	public void cleanup() {
		// TODO: Later cleanup so that the agent is ready for the next match
	}

}
