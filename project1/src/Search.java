import java.util.ArrayList;
import java.util.List;

public class Search {

	int playclock;
	Environment env;

	public Search(Environment env, int playclock) {
		this.playclock = playclock;
		this.env = env;
	}






	public int[] miniMaxRoot(State state, int depth){
		int[] bestMove = new int[4];
		int maxEval = -100;
		
		List<int[]> legalMoves = new ArrayList<int[]>();
		legalMoves = env.legalMoves(state);

		for(int i=0;i<legalMoves.size(); i++){	
			int[] move = legalMoves.get(i);
			
			State child = new State();
			child =	env.getNextState(state, move);
			int childEval = miniMax(child, depth -1);
			if(childEval > maxEval){
				maxEval = childEval;
				bestMove = move;
			}
		}
		return bestMove;
	}

	public int miniMax(State s, int depth){
		State state = s.clone();
		if(depth == 0 || env.eval(state) == 100 || env.eval(state) == -100){
			return env.eval(state);
		}
		
		//maximizing player
		if(state.myTurn){
			int maxEval = -100;
			for(int[] legalMove : env.legalMoves(state)){
				State child = new State();
				child = env.getNextState(state, legalMove);
				int childEval = miniMax(child, depth -1);
				if(childEval > maxEval){
					maxEval = childEval;
				}
			} 
			return maxEval;

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
			}
			return minEval;
		}
	}
}
