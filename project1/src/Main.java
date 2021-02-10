
public class Main {
	/**
	 * starts the game player and waits for messages from the game master <br>
	 * Command line options: [port]
	 */
	public static void main(String[] args){
		try{
			
			/* 			
			Debug
			Environment debugEnv = new Environment("black", 5, 5);
			State debugState = debugEnv.getCurrentState();

			State clonedState = debugState.clone();
			Coordinates testCoordinates = new Coordinates(3,3); 
			//clonedState.myPawns.add(testCoordinates);
			int debugMove[] = {1,4,1,3};
			State nextState = debugEnv.getNextState(debugState,debugMove);

			System.out.println(debugState.equals(nextState));

			
			int de =0; 
			
			
			
			State debugStateBefore = new State();
			debugStateBefore = debugEnv.getCurrentState();

			State debugStateClones = new State();
			debugStateBefore = debugStateBefore.clone();
			
			State debugStateAfter = new State();
			debugStateAfter = debugEnv.getNextState(debugStateBefore, debugMove );
			
			boolean debugEqualsClonedState = debugStateBefore.equals(debugStateClones);
			boolean debugEqualsNextState = debugStateBefore.equals(debugStateAfter); 
			
			*/

 			// TODO: Later put in your agent here
			Agent agent = new MyAgent();

			int port=4001;
			if(args.length>=1){
				port=Integer.parseInt(args[0]);
			}
			GamePlayer gp=new GamePlayer(port, agent);
			gp.waitForExit(); 
		}catch(Exception ex){
			ex.printStackTrace();
			System.exit(-1);
		}
	}
}
