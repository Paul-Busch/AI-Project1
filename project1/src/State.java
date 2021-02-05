import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class holds all information about the state of the environment and the
 * robot
 *
 */
public class State implements Cloneable {

	public List<Coordinates> myPawns;
	public List<Coordinates> opponentPawns;
	public boolean myTurn;

	/**
	 * 
	 */
	public State() {
		myPawns = new ArrayList<Coordinates>();
		opponentPawns = new ArrayList<Coordinates>();
		myTurn = false; //TODO (Later) false or true?
		//TODO (Done:Linus) look where this is called or needed
	}

	// TODO (Laura) rewrite clone
	@SuppressWarnings("unchecked")
	public State clone() {
		State cloned;
		try {
			cloned = (State)super.clone();
			cloned.myPawns =  new ArrayList<Coordinates>(this.myPawns); 
			cloned.opponentPawns =  new ArrayList<Coordinates>(this.opponentPawns); 

		//	cloned.myPawns = (List<Coordinates>)myPawns.clone();	
		//	cloned.opponentPawns = (List<Coordinates>)opponentPawns.clone();
		//do I need to clone the bool too?
		} catch (CloneNotSupportedException e) { e.printStackTrace(); System.exit(-1); cloned=null; }
		return cloned;
	}
	

	public String toString() {
		return "FILL ME";
	}

	public boolean equals(Object o){
		State s = (State) o;
		boolean myPawnsEquals = (s.myPawns.size() == myPawns.size() && s.myPawns.containsAll(myPawns) && myPawns.containsAll(s.myPawns));
		boolean opponentPawnsEquals = (s.opponentPawns.size() == opponentPawns.size() && s.opponentPawns.containsAll(opponentPawns) && opponentPawns.containsAll(s.opponentPawns));
		return  s.myTurn == myTurn && myPawnsEquals && opponentPawnsEquals;
	}
}	

	