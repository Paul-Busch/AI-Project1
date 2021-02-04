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
		// TODO Paul rewrite equals 
		public boolean equals(Object o) {
			if (!(o instanceof State)) {
				return false;
			}
			State s = (State) o;
			return false;
		}
	}