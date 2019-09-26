import java.util.*;

/**
 * @author Tabassum Fabiha -- tf2478
 * 
 * The class speaks with the user to relay information in the console. It is also used to obtain
 * information from the user (human or sim).
 */
public class Talker {

	public Talker(Sim sim, int numRounds) {
		reader = new Scanner(System.in);
		mySim = sim;
		NUMROUNDS = numRounds;
	}
	
	/**
	 * Prints out the instructions to the console.
	 */
	public void speakWelcomeText() {
		String output = "";
		output += "Welcome to RSPLK, the game of Rock Paper Scissors Lizard Spock.\n";
		output += "\n";
		
		output += "Rules:\n";
		output += "   - Rock beats scissors and lizard.\n";
		output += "   - Paper beats rock and Spock.\n";
		output += "   - Scissors beat paper and lizard.\n";
		output += "   - Lizard beats paper and Spock.\n";
		output += "   - Spock beats rock and scissors.\n";
		output += "\n";
		
		output += "The following letters correspond to each option:\n";
		output += "   - Rock --> r\n";
		output += "   - Paper --> p\n";
		output += "   - Scissors --> s\n";
		output += "   - Lizard --> l\n";
		output += "   - Spock --> k\n";
		
		System.out.println(output);
	}
	
	/**
	 * @param results text that holds the results of the round/game
	 * 
	 * Prints out the results of the round/game to the console.
	 */
	public void speakResults(String results) {
		System.out.println(results);
		System.out.print("\n");
	}
	
	/**
	 * @return user's play this round
	 * 
	 * Asks the user (sim or human) what move they will play for the current round.
	 */
	public String getUserPlay() {
		System.out.println("Round " + currentRound + " has begun.");
		String userPlay = mySim.getSimInput();
		
		currentRound += 1;
		return userPlay;
	}
	
	/**
	 * @return user's play this round
	 * 
	 * Asks the human user to input a play for this round.
	 */
	private String getUserInput() {
		boolean incorrectInput = true;
		String userInput = "";
		
		while (incorrectInput) {
			System.out.print("Please type in 'r', 'p', 's', 'l', or 'k'.\nYour Input: ");
			userInput = reader.nextLine();
			
			incorrectInput = isInvalidInput(userInput);
		}
		
		return userInput;
	}
	
	/**
	 * @param userInput user's play this round
	 * @return if the given string is a valid play or not
	 * 
	 * Checks to make sure that the given string is a valid play by
	 * comparing it to all the possible plays.
	 */
	private boolean isInvalidInput(String userInput) {
		boolean invalid = true;

		for (int i = 0; i < userPlayOptions.length; i++) {
			if (userInput.equals( userPlayOptions[i] )) {
				return !invalid;
			}
		}
		
		return invalid;
	}
	
	private Scanner reader;
	private Sim mySim;
	
	private int NUMROUNDS;
	private int currentRound = 0;
	private String[] userPlayOptions = {"r", "p", "s", "l", "k"};
}
