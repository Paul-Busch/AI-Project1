import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class holds all information about the state of the environment
 *
 */
public class State implements Cloneable {

	//we create two lists to store the coordinates of all the pawns in the board at each state
	public List<Coordinates> myPawns;
	public List<Coordinates> opponentPawns;
	//we create a boolean variable that determinates wether is our turn or not in each state
	public boolean myTurn;

	/**
	 * 
	 */
	public State() {
		//we initialize all the variables
		myPawns = new ArrayList<Coordinates>();
		opponentPawns = new ArrayList<Coordinates>();
		myTurn = false; 
	}

	/**
	 * This function clones a given state and it returns it
	 * @return cloned State
	 */
	@SuppressWarnings("unchecked")
	public State clone() {
		//we create the state we want to return
		State cloned = new State();
		try {
			//we cast and we clone the state
			cloned = (State)super.clone();

			//we clone both arrays of pawns

			List <Coordinates> tempMyPawns = new ArrayList<Coordinates>();
			//we iterate over the whole array, clone each item and store it in a temp variable 
			for(Coordinates i : this.myPawns){
				Coordinates clone = (Coordinates) i.clone();
				tempMyPawns.add(clone);
			}
			
			//same procedure but with opponentPawns array
			List <Coordinates> tempOpponentPawns = new ArrayList<Coordinates>();
			for(Coordinates i : this.opponentPawns){
				Coordinates clone = (Coordinates) i.clone();
				tempOpponentPawns.add(clone);
			} 

			// we assign the temp arrays to the cloned ones
			cloned.myPawns = tempMyPawns;
			cloned.opponentPawns = tempOpponentPawns;

			//we clone the trun of the state
			boolean myTurnTmp = this.myTurn;
			cloned.myTurn = myTurnTmp;

		} catch (CloneNotSupportedException e) { e.printStackTrace(); System.exit(-1); cloned=null; }
		return cloned;
	}
	

	public String toString() {
		String stateToString ="MyPawns:";
		for(Coordinates corr : myPawns){
			stateToString += "(" + corr.x + "," + corr.y + "),";
		}
		stateToString += "    opponentPawns:";
		for(Coordinates corr : opponentPawns){
			stateToString += "(" + corr.x + "," + corr.y + "),";
		}
		return stateToString;
	}
	/**
	 * This function checks if two given states are equal or not
	 * @return true if they are equal, false if they are not
	 */
	public boolean equals(Object o) {
		//make sure that the given object is from State type
		if (!(o instanceof State)) {
			return false;
		}
		// we cast the given object and we assign it to a new variable of type State 's'
		State s = (State) o;
		//check that both lists of pawns have the same size and contain the same elements
		boolean myPawnsEquals = (s.myPawns.size() == myPawns.size() && s.myPawns.containsAll(myPawns) && myPawns.containsAll(s.myPawns));
		boolean opponentPawnsEquals = (s.opponentPawns.size() == opponentPawns.size() && s.opponentPawns.containsAll(opponentPawns) && opponentPawns.containsAll(s.opponentPawns));
		
		//return if the turn is the same and if the lists of pawns are equal
		return  s.myTurn == myTurn && myPawnsEquals && opponentPawnsEquals;
	}
}
	