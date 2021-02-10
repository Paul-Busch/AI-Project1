import java.util.Random;

public class MyAgent implements Agent {


	//4debug
	private Random random = new Random();

    
	private String role; // the name of this agent's role (white or black)
	private int playclock; // this is how much time (in seconds) we have before nextAction needs to return a move
	private boolean myTurn; // whether it is this agent's turn or not
	private int sizeX, sizeY; // dimensions of the board
	private Environment env;
	Search search; 
	/*
		init(String role, int playclock) is called once before you have to select the first action. Use it to initialize the agent. role is either "white" or "black" and playclock is the number of seconds after which nextAction must return.
	*/
    public void init(String role, int sizeX, int sizeY, int playclock) {
		this.role = role;
		this.playclock = playclock;
		myTurn = !role.equals("white");
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		
		
        env = new Environment(role, sizeX, sizeY);
		this.search = new Search(env, playclock);
    }

	// lastMove is null the first time nextAction gets called (in the initial state)
    // otherwise it contains the coordinates x1,y1,x2,y2 of the move that the last player did
    public String nextAction(int[] lastMove) {
    	if (lastMove != null) {
    		int x1 = lastMove[0], y1 = lastMove[1], x2 = lastMove[2], y2 = lastMove[3];
    		String roleOfLastPlayer;
    		if (myTurn && role.equals("white") || !myTurn && role.equals("black")) {
    			roleOfLastPlayer = "white";
    		} else {
    			roleOfLastPlayer = "black";
    		}
			System.out.println(roleOfLastPlayer + " moved from " + x1 + "," + y1 + " to " + x2 + "," + y2);
			
			// TODO: (Done:Linus) 1. update your internal world model according to the action that was just executed
			env.currentState = env.getNextState(env.getCurrentState(), lastMove);
    	}
		
    	// update turn (above that line it myTurn is still for the previous state)
		myTurn = !myTurn;
		if (myTurn) {
			// TODO: (Later) 2. run alpha-beta search to determine the best move

			// Here we just construct a random move (that will most likely not even be possible),
			// this needs to be replaced with the actual best move.
			int x1,y1,x2,y2;
			//int num = random.nextInt(env.legalMoves(env.getCurrentState()).size());
			//int[] move = env.legalMoves(env.getCurrentState()).get(num);

			
			int[] move = search.miniMaxRoot(env.getCurrentState(),1);



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
