//import jdk.jfr.internal.test.WhiteBox;

public class Main {
	/**
	 * starts the game player and waits for messages from the game master <br>
	 * Command line options: [port]
	 */
	public static void main(String[] args){
		
		
/* 		Environment debugEnv = new Environment("black" , 2, 4);
		System.out.println("Evaluation: " + debugEnv.eval(debugEnv.getCurrentState()));
		int[] move = {1,2,2,3};
		State nextState = debugEnv.getNextState(debugEnv.getCurrentState(), move );
		System.out.println("Evaluation: " + debugEnv.eval(nextState));
		int[] move2 = {1,3,1,2};
		nextState = debugEnv.getNextState(nextState, move2 );
	
		System.out.println("Evaluation: " + debugEnv.eval(nextState));

		int[] move3 = {2,3,1,4};
		nextState = debugEnv.getNextState(nextState, move3 );
		System.out.println("Evaluation: " + debugEnv.eval(nextState));
 */

		
		try{
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
