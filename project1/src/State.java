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
		State cloned = new State();
		try {
			cloned = (State)super.clone();

			
			List <Coordinates> tempMyPawns = new ArrayList<Coordinates>();
			for(Coordinates i : this.myPawns){
				Coordinates clone = (Coordinates) i.clone();
				tempMyPawns.add(clone);
			}
		
			List <Coordinates> tempOpponentPawns = new ArrayList<Coordinates>();
			for(Coordinates i : this.opponentPawns){
				Coordinates clone = (Coordinates) i.clone();
				tempOpponentPawns.add(clone);
			} 

			cloned.myPawns = tempMyPawns;
			cloned.opponentPawns = tempOpponentPawns;

			boolean myTurnTmp = this.myTurn;
			cloned.myTurn = myTurnTmp;

		//	cloned.myPawns = (List<Coordinates>)myPawns.clone();	
		//	cloned.opponentPawns = (List<Coordinates>)opponentPawns.clone();
		//do I need to clone the bool too?
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
	// TODO (done by Paul)
	public boolean equals(Object o) {
		if (!(o instanceof State)) {
			return false;
		}
		State s = (State) o;
		boolean myPawnsEquals = (s.myPawns.size() == myPawns.size() && s.myPawns.containsAll(myPawns) && myPawns.containsAll(s.myPawns));
		boolean opponentPawnsEquals = (s.opponentPawns.size() == opponentPawns.size() && s.opponentPawns.containsAll(opponentPawns) && opponentPawns.containsAll(s.opponentPawns));
		return  s.myTurn == myTurn && myPawnsEquals && opponentPawnsEquals;
	}
}
	