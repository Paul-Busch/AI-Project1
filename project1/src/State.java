import java.util.List;

/**
 * 
 * This class holds all information about the state of the environment and the
 * robot
 *
 */
public class State implements Cloneable {
	protected List<Coordinates> myPawns;
	protected List<Coordinates> opponentPawns;
	protected boolean myTurn;

		public State() {
			//TODO Linus look where this is called or needed
		}

		// TODO Laura rewrite clone
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