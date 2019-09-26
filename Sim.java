
/**
 * @author Tabassum Fabiha -- tf2478
 *
 * The class simulates the moves a human would make, either playing the opponent's last play (reflector strategy)
 * or going through the plays 'rpslk' in a sequential order (rotator strategy). The sim starts off with the 
 * rotator strategy of play then switches over to the reflector strategy after N rounds. Every N rounds
 * the switch between strategies is made again.
 */
public class Sim {
	
	/**
	 * Instantiates N with a value of 20 if no value is given for the number of rounds
	 * that should be played with each method before switching over.
	 */
	public Sim () {
		N = 20;
	}
	
	/**
	 * @param switchnum the number of rounds that should be played with each method before
	 * 					switching to the other method
	 * 
	 * Instantiates N with the value of switchNum.
	 */
	public Sim(int switchNum) {
		N = switchNum;
	}
	
	
	/**
	 * @return sim's play this round
	 * 
	 * Every N turns the sim switches strategies. It first checks to see which strategy it
	 * should follow and calls the appropriate function to find the move it will play this
	 * round. It prints to the console the move that it's chosen.
	 */
	public String getSimInput() {
		String simInput;

		int NsPassed = roundsPlayed / N;
				
		if ( NsPassed % 2 == 0 ) {
			simInput = rotatorStrategy();
		}
		else {
			simInput = reflectorStrategy();
		}
		
		System.out.print("Please type in 'r', 'p', 's', 'l' or 'k'.\nYour Input: ");
		System.out.println(simInput);
		
		return simInput;
	}
	
	
	/**
	 * @return sim's play for this round
	 * 
	 * Plays the next sequential move in the list of moves 'rpscl'.
	 */
	private String rotatorStrategy() {
		String simInput = playOptions.charAt(rotatorPlayIterator % playOptions.length()) + "";
		rotatorPlayIterator += 1;
		roundsPlayed += 1;
		return simInput;
	}
	
	/**
	 * @return sim's play for this round
	 * 
	 * If this is the first round that we are playing in then the sim uses the rotator 
	 * strategy. Else it play's the opponent's last move. 
	 */
	private String reflectorStrategy() {
		if (computerLastPlay.isEmpty()) {
			return rotatorStrategy();
		}
			
		roundsPlayed += 1;
		return computerLastPlay;
	}
	
	/**
	 * @param computerPlay the play made by the computer the last round
	 * 
	 * Updates the variable computerLastPlay to contain the latest play made by the computer.
	 */
	public void addComputerPlay(String computerPlay) {
		computerLastPlay = computerPlay;
	}
	
	private int N;
	private int roundsPlayed = 0;
	
	private String playOptions = "rpslk";
	private int rotatorPlayIterator = 0;
	private String computerLastPlay = "";
}
