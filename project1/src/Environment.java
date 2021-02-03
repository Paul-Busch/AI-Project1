import java.util.Collection;

public class Environment {



	protected int sizeX;
	protected int sizeY;
	protected State currentState;

	public Environment(Collection<String> percepts) {
		initFromPercepts(percepts);
	}

	public void initFromPercepts(Collection<String> percepts) {
		currentState = new State();
		
	}

	/**
	 * 
	 * @return the current state of the environment
	 */
	public State getCurrentState() {
		return currentState;
	}

	/**
	 * updates the current state of the environment based on the given action
	 * 
	 * @param a
	 */
	public void doAction(Action a) {
		currentState = getNextState(currentState, a);
	}

	/**
	 * 
	 * @param state
	 * @return a list of actions that are possible in the given state
	 */
	public  List<Action> legalMoves(State state) {
		List<Action> moves = new LinkedList<Action>();
		if (!state.turned_on) {
			moves.add(Action.TURN_ON);
		} else {
			if (state.position.equals(home)) {
				moves.add(Action.TURN_OFF);
			}
			if (state.dirt.contains(state.position)) {
				moves.add(Action.SUCK);
			}
			Coordinates facingPosition = state.facingPosition();
			if (facingPosition.x > 0 && facingPosition.y > 0 && facingPosition.x <= sizeX && facingPosition.y <= sizeY
					&& !obstacles.contains(facingPosition)) {
				moves.add(Action.GO);
			}
				
			moves.add(Action.TURN_RIGHT);	
			moves.add(Action.TURN_LEFT);
			
		}
		return moves;
	}

	/**
	 * 
	 * @param s state
	 * @param a action
	 * @return the state resulting from doing a in s
	 */
	public  State getNextState(State s, Action a) {
		State succState = s.clone();
		// TODO: fill out this function
		if(a.equals(Action.GO)) {
            switch(succState.orientation) {
                case 0: //north
                    succState.position.y +=1;
                    break;
                case 1: //east
                    succState.position.x +=1;
                    break;
                case 2: //south
                    succState.position.y -=1;
                    break;
                case 3: //west
                    succState.position.x -=1;
                    break;
                default:
                    System.out.println("Orientation is not 0, 1, 2 or 3 inside Environment: getNextState");
                    break;
            }

        }

		else if(a.equals(Action.SUCK)) {
            if (succState.dirt.contains(succState.position)){
				succState.dirt.remove(succState.position);
			}
        }
        else if(a.equals(Action.TURN_LEFT)) {
            switch(succState.orientation) {
                case 0: //north
                    succState.orientation = 3;
                    break;
                case 1,2,3:
					succState.orientation -=1;
                    break;
                default:
                    System.out.println("Orientation is not 0, 1, 2 or 3 inside Environment: getNextState");
                    break;
            }

        }
        else if(a.equals(Action.TURN_RIGHT)) {
			switch(succState.orientation) {
                case 3: //west
                    succState.orientation = 0;
                    break;
                case 0,1,2: 
					succState.orientation +=1;
                    break;
                default:
                    System.out.println("Orientation is not 0, 1, 2 or 3 inside Environment: getNextState");
                    break;
            }
        }
        else if(a.equals(Action.TURN_ON)) {
			succState.turned_on = true;
        }
        else if(a.equals(Action.TURN_OFF)) {
			succState.turned_on = false;
        }
		//System.out.println("state:" + s + "move: " + a + " -> next state: " + succState);
		return succState;
	}

	/**
	 * 
	 * @param s
	 * @param a
	 * @return the cost of doing action a in state s
	 */
	public int getCost(State s, Action a) {
		switch (a) {
			case TURN_ON:
				return 1;
			case TURN_OFF: // this requires that we only turn the agent off at home
				return 1 + 50*s.dirt.size();
			case TURN_RIGHT:
				return 1;
			case TURN_LEFT:
				return 1;
			case GO: // this requires that we never do go if there is an obstacle
				return 1;
			case SUCK: // this requires that we never suck if there is no dirt
				return 1;
			default:
				return 0;
		}
	}

}
