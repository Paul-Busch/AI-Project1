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
		int maxEval = -101;
		
		List<int[]> legalMoves = new ArrayList<int[]>();
		legalMoves = env.legalMoves(state);

		if(legalMoves.isEmpty()){
			int[] illegalMove = {0,0,0,0};
			System.out.println("problem with miniMaxRoot");
			return illegalMove; //this makes no sense //gelichstand
		} else {
			for(int[] legalMove : legalMoves){	//TODO what happens when there are no legal moves?
				
				State child = new State();
				child =	env.getNextState(state, legalMove);
				int childEval = miniMax(child, depth -1);

				System.out.println("Move: from (" + legalMove[0] + "," + legalMove[1] + ") to (" + legalMove[2] + "," + legalMove[3] + ") Evaluation: " + childEval);			
				
				if(childEval > maxEval){
					maxEval = childEval;
					bestMove = legalMove;
					
				}
			}
		}
		return bestMove;
	}

	public int miniMax(State s, int depth){

		State state = s.clone();
		List<int[]> legalMoves = new ArrayList<int[]>();
		legalMoves = env.legalMoves(state);

		if(depth == 0 || env.eval(state) == 100 || env.eval(state) == -100){
			//System.out.println(state.toString() + "    Evaluation: " + env.eval(state));	
			return env.eval(state);
			//Debug
			
		}
		if(legalMoves.isEmpty()){
			return 0; //gleichstand
		} else {
			//maximizing player
			if(state.myTurn){
				int maxEval = -101;
				for(int[] legalMove : legalMoves){
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
				int minEval = 101;
				for(int[] legalMove : legalMoves){
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
}
