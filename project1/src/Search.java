import java.util.ArrayList;
import java.util.List;

public class Search {

	int playclock;
	Environment env;
	int startingMillis;
	public int stateExpansions;
	public int depthLimit;

	//inizialization of the search
	public Search(Environment env, int playclock) {
		this.playclock = playclock;
		this.env = env;
	}

	//Iterative deepening 
	public int[] iterativeDeepening(State state, int startMillis){
		//since the iterativeDeepening function is called in the agent, the fuction is used to get the start time
		this.startingMillis = startMillis;
		int[] bestReturnMove = new int[4]; 
		int previousIterationMillis = 0;
		try {
			//while loop that checks whether time is left for another depth
			int depth = 1;
			while((int) System.currentTimeMillis() - startMillis < (playclock* 1000)-100){
				//calculate time of iteration
				previousIterationMillis = (int) System.currentTimeMillis() - startMillis - previousIterationMillis;
				System.out.println("Depth: " + depth + "    Time of previous iteration: " + previousIterationMillis + "ms");
				//call the miniMaxRoot function to get the best move for the depth
				bestReturnMove = miniMaxRoot(state, depth);
				stateExpansions++;
				depthLimit = depth;
				depth++;
				
			}
		} catch (RuntimeException e) {
			//catch the runtime error if the time left is to small return the best move from the last full iteration
			return bestReturnMove;
		}
		//Print error message if the program gets here (it's not supposed to be here)
		System.out.println("problem with iterative deepening");
		return bestReturnMove; 

	}



	//MiniMaxRoot function
	public int[] miniMaxRoot(State state, int depth){
		int[] bestMove = new int[4];
		//Set maxEval to smallest possible int 
		int maxEval = Integer.MIN_VALUE;
		
		List<int[]> legalMoves = new ArrayList<int[]>();
		legalMoves = env.legalMoves(state);

		if(legalMoves.isEmpty()){
			//if legal moves is empty the game should be over
			int[] illegalMove = {0,0,0,0};
			System.out.println("problem with miniMaxRoot");
			return illegalMove; 
		} else {
			// iterate through all the possible moves
			for(int[] legalMove : legalMoves){	
				//if there is time left, expande nodes
				if((int) System.currentTimeMillis() - startingMillis < (playclock* 1000)-100){
					State child = new State();
					child =	env.getNextState(state, legalMove);
					int childEval = miniMax(child, depth -1, Integer.MIN_VALUE, Integer.MAX_VALUE);
					stateExpansions++;
					System.out.println("Move: from (" + legalMove[0] + "," + legalMove[1] + ") to (" + legalMove[2] + "," + legalMove[3] + ") Evaluation: " + childEval);			
					//check wether the evaluation of the current move is larger than the current best eval
					if(childEval > maxEval){
						//set the better evaluation to the new current best
						maxEval = childEval;
						bestMove = legalMove;
					}
				} else {
					//leave the iteration if there is no time left
					throw new RuntimeException();
				}
			}
		}
		//return best move if ther depth is reached
		return bestMove;
	}

	//miniMax workes similar to miniMaxRoot except that it doesnt return an move insted it returnes a evaluation and there is also a minimizing player
	public int miniMax(State s, int depth, int alpha, int beta){

		State state = s.clone();
		List<int[]> legalMoves = new ArrayList<int[]>();
		legalMoves = env.legalMoves(state);

		//if the desired depth or a terminal state is reached, it doesnt call itself anymore
		if(depth == 0 || env.eval(state) == 100 || env.eval(state) == -100){
			//System.out.println(state.toString() + "    Evaluation: " + env.eval(state));	
			return env.eval(state);
			
		}
		if(legalMoves.isEmpty()){
			return 0; 
		} else {
			//maximizing player (similar to miniMaxRoot but with pruning)
			if(state.myTurn){
				int maxEval = Integer.MIN_VALUE;
				for(int[] legalMove : legalMoves){
					if((int) System.currentTimeMillis() - startingMillis < (playclock* 1000)-100){
						
						State child = new State();
						child = env.getNextState(state, legalMove);
						int childEval = miniMax(child, depth -1, alpha, beta);
						stateExpansions++;
						if(childEval > maxEval){
							maxEval = childEval;
						}
						//pruning:
						if(alpha > childEval){
							alpha = childEval;
						}
						// if beta is smaller than alpha, its not worth expolring the part of the tree
						if(beta <= alpha){
							break;
						}
					} else {
						throw new RuntimeException();
					}
				} 
				return maxEval;

			} else {
				//minimizing player (similar to the maximizing player but not the value gets minimized)
				int minEval = Integer.MAX_VALUE;
				for(int[] legalMove : legalMoves){
					if((int) System.currentTimeMillis() - startingMillis < (playclock* 1000)-100){
						State child = new State();
						child = env.getNextState(state, legalMove);
						int childEval = miniMax(child, depth -1, alpha, beta);
						stateExpansions++;
						if(childEval < minEval){
							minEval = childEval;
						}

						if(beta < childEval){
							beta = childEval;
						}
						if(beta <= alpha){
							break;
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
