public class Search{

	int depth;
	int playclock;
	Environment env;

	public Search(Environment env, int playclock) {
		this.playclock = playclock;
		this.env = env;
	}
	
	public int miniMax(State state, int depth){
		this.depth = depth;
		if(depth == 0 || env.eval(state) == 100 || env.eval(state) == -100){
			return env.eval(state);
		}

		//maximizing player
		if((env.role.equals("white") && state.myTurn) || (env.role.equals("black") && !state.myTurn)){
			int maxEval = -100;
			for(int[] legalMove : env.legalMoves(state)){
				State child = new State();
				child = env.getNextState(state, legalMove);
				int childEval = miniMax(child, depth -1);
				if(childEval > maxEval){
					maxEval = childEval;
				}
				return maxEval;
			} 

		} else {
			//minimizing player
			int minEval = 100;
			for(int[] legalMove : env.legalMoves(state)){
				State child = new State();
				child = env.getNextState(state, legalMove);
				int childEval = miniMax(child, depth -1);
				if(childEval < minEval){
					minEval = childEval;
				}
				return minEval;
			}
		}
	}
}
