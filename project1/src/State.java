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
		return null;
	}
	

	public String toString() {
		return "FILL ME";
	}

	// TODO (later) better performance when checking if s.myPawns.size() == myPawns.size() so that not whole list is compared before knowing its size
	public boolean equals(Object o) {
		if (!(o instanceof State)) {
			return false;
		}
		State s = (State) o;
		Boolean myPawnsEquals = (s.myPawns.size() == myPawns.size() && s.myPawns.containsAll(myPawns) && myPawns.containsAll(s.myPawns));
		Boolean opponentPawnsEquals = (s.opponentPawns.size() == opponentPawns.size() && s.opponentPawns.containsAll(opponentPawns) && opponentPawns.containsAll(s.opponentPawns));
		return  s.myTurn == myTurn && myPawnsEquals && opponentPawnsEquals;

	}
}	
	

/* if (list1.size() == list2.size() && list1.containsAll(list2) && list1.containsAll(list2)        ) {
	System.out.println("EQUAL"); */
	