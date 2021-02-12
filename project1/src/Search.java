import java.util.ArrayList;
import java.util.List;

public class Search {

	int playclock;
	Environment env;
	int startingMillis;
	public int stateExpansions;
	public int depthLimit;

	public Search(Environment env, int playclock) {
		this.playclock = playclock;
		this.env = env;
	}

	public int[] iterativeDeepening(State state, int startMillis){
		this.startingMillis = startMillis;
		int[] bestReturnMove = new int[4]; 
		int previousIterationMillis = 0;
		try {
			int depth = 1;
			while((int) System.currentTimeMillis() - startMillis < (playclock -1) * 1000){
				previousIterationMillis = (int) System.currentTimeMillis() - startMillis - previousIterationMillis;
				System.out.println("Depth: " + depth + "    Time of previous iteration: " + previousIterationMillis + "ms");
				bestReturnMove = miniMaxRoot(state, depth);
				stateExpansions++;
				depthLimit = depth;
				depth++;

			}
		} catch (RuntimeException e) {
			return bestReturnMove;
		}
		//Debug:
		System.out.println("problem with iterative deepening");
		return bestReturnMove; 

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
				
				if((int) System.currentTimeMillis() - startingMillis < (playclock -1) * 1000){
					State child = new State();
					child =	env.getNextState(state, legalMove);
					int childEval = miniMax(child, depth -1);
					stateExpansions++;
					System.out.println("Move: from (" + legalMove[0] + "," + legalMove[1] + ") to (" + legalMove[2] + "," + legalMove[3] + ") Evaluation: " + childEval);			
					
					if(childEval > maxEval){
						maxEval = childEval;
						bestMove = legalMove;
					}
				} else {
					throw new RuntimeException();
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
			
		}
		if(legalMoves.isEmpty()){
			return 0; //gleichstand
		} else {
			//maximizing player
			if(state.myTurn){
				int maxEval = -101;
				for(int[] legalMove : legalMoves){
					if((int) System.currentTimeMillis() - startingMillis < (playclock -1) * 1000){
					
						State child = new State();
						child = env.getNextState(state, legalMove);
						int childEval = miniMax(child, depth -1);
						stateExpansions++;
						if(childEval > maxEval){
							maxEval = childEval;
						}
					} else {
						throw new RuntimeException();
					}
				} 
				return maxEval;

			} else {
				//minimizing player
				int minEval = 101;
				for(int[] legalMove : legalMoves){
					if((int) System.currentTimeMillis() - startingMillis < (playclock -1) * 1000){
						State child = new State();
						child = env.getNextState(state, legalMove);
						int childEval = miniMax(child, depth -1);
						stateExpansions++;
						if(childEval < minEval){
							minEval = childEval;
						}
					} else {
						throw new RuntimeException();
					}
				}

				return minEval;

			}
		}
	}
}
