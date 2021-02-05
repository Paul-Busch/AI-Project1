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

		// TODO (later) better performance when checking if s.myPawns.size() == myPawns.size() so that not whole list is compared before knowing its size
		public boolean equals(Object o) {
			if (!(o instanceof State)) {
				return false;
			}
			State s = (State) o;
			return  s.myTurn == myTurn && s.myPawns.equals(myPawns) && s.opponentPawns.equals(opponentPawns);

		}
	}	
		
	