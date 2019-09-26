
/**
 * @author tf2478
 *
 * The class computes the results of who won and lost in the previous round. It updates
 * the Thrower and Sim instances of the last move made by the opposing player.
 */
public class Scorer {

	/**
	 * @param sim Sim instance
	 * @param thrower Thrower instance
	 * @param talker Talker instance
	 * 
	 * Instantiates the appropriate values with references to the Sim, Thrower, and Talker
	 * instances for reference.
	 */
	public Scorer(Sim sim, Thrower thrower, Talker talker) {
		mySim = sim;
		myThrower = thrower;
		myTalker = talker;
	}
	
	/**
	 * @param userPlay the play made by the user (sim or human) this round
	 * @param computerPlay the play made by the computer this round
	 * 
	 * Finds the index of each move made. Uses these indices to obtain the results
	 * of this round. Updates the Recorder and Sim instances of plays made in this 
	 * round.
	 */
	public void computePlay(String userPlay, String computerPlay) {
		int userPlayIndex = playOptions.indexOf(userPlay);
		int computerPlayIndex = playOptions.indexOf(computerPlay);
		
		thisRoundResultText = roundTextOutputOptions[userPlayIndex][computerPlayIndex];
		int thisRoundResultNumber = userNumberResultOptions[userPlayIndex][computerPlayIndex];
		
		updateRecorder(thisRoundResultNumber);
		myThrower.addUserPlay(userPlay);
		mySim.addComputerPlay(computerPlay);
	}
	
	/**
	 * Tells the Talker instance to print this round's results to the console.
	 */
	public void sendRoundResults() {
		myTalker.speakResults(thisRoundResultText);
	}
	
	/**
	 * Tells the Talker instance to print this game's results to the console.
	 */
	public void sendGameResults() {
		String gameResults = getGameResults();
		myTalker.speakResults(gameResults);
	}
	
	/**
	 * @return the text output of the results of the game
	 * 
	 * Collects counts for wins and ties from the Recorder instance and computes the
	 * percentages for each. Concatenates all the counts and percentages into a human-readable
	 * String and returns it.
	 */
	private String getGameResults() {
		int userWinCount = myRecorder.getUserWinCount();
		double userWinPercentage = percentage(userWinCount);
		
		int computerWinCount = myRecorder.getComputerWinCount();
		double computerWinPercentage = percentage(computerWinCount);
		
		int tieCount = myRecorder.getTieCount();
		double tiePercentage = percentage(tieCount);
		
		String gameResults = "Game ended!\n";
		gameResults += "User Win Count: " + userWinCount + "\n";
		gameResults += "User Win Percentage: " + userWinPercentage + "\n";
		gameResults += "Computer Win Count: " + computerWinCount + "\n";
		gameResults += "Computer Win Percentage: " + computerWinPercentage + "\n";
		gameResults += "Tie Count: " + tieCount + "\n";
		gameResults += "Tie Percentage: " + tiePercentage + "\n";
		
		return gameResults;
	}
	
	/**
	 * @param count the count that you want to find out the percentage of
	 * @return the percentage computed
	 * 
	 * Finds the fraction of the count over the total rounds played and turns
	 * it into it's percent form.
	 */
	private double percentage(int count) {
		double decimal = ( (1.0 * count) / myRecorder.getTotalTimesPlayed() );
		double percentage = Math.floor(decimal * 10000) / 100;
		
		return percentage;
	}
	
	/**
	 * @param thisRoundResultNumber the number that represents if the user's outcome this round
	 * 
	 * Increases the appropriate counts in the Recorder instance.
	 */
	private void updateRecorder(int thisRoundResultNumber) {
		int userWin = 1;
		int userLoss = -1;
		
		if (thisRoundResultNumber == userWin) {
			myRecorder.increaseUserWinCount();
		}
		else if (thisRoundResultNumber == userLoss) {
			myRecorder.increaseComputerWinCount();
		}
		else {
			myRecorder.increaseTieCount();
		}
		
		myRecorder.increaseTotalTimesPlayed();
	}
	
	private Sim mySim;
	private Thrower myThrower;
	private Talker myTalker;
	private Recorder myRecorder = new Recorder();
	
	private String playOptions = "rpslk";
	
	private String thisRoundResultText = "";
	
	/**
	 * The 2d array is used to store the info on who wins or loses given all
	 * possible combinations of user and computer plays. The rows going down represent
	 * the user's play in the sequence 'rpslk'. The columns going across represent the
	 * computer's play in the sequence 'rpslk'. 
	 * 
	 * -1 	--> loss for the user
	 * 0 	--> tie
	 * 1 	--> win for the user
	 * 
	 * To find the result of any particular combination:
	 * 		userNumberResultOption[userPlay][computerPlay]
	 */
	private int[][] userNumberResultOptions = {{0, -1, 1, 1, -1}, 
												{1, 0, -1, -1, 1}, 
												{-1, 1, 0, 1, -1},
												{-1, 1, -1, 0, 1},
												{1, -1, 1, -1, 0}
												};
	
	/**
	 * The 2d array is used to store the info on the text results for all
	 * possible combinations of user and computer plays. The rows going down represent
	 * the user's play in the sequence 'rpslk'. The columns going across represent the
	 * computer's play in the sequence 'rpslk'. 
	 * 
	 * To find the text outputs of any particular combination:
	 * 		roundTextOutputOptions[userPlay][computerPlay]
	 */
	private String[][] roundTextOutputOptions = {{"We tied with rocks!", "I won! My paper covers your rock.", "You won! Your rock crushes my scissors.", "You won! Your rock crushes my lizard.", "I won! My Spock vaporizes your rock."},
												{"You won! Your paper covers my rock.", "We tied with paper!", "I won! My scissors cut your paper.", "I won! My lizard eats your paper.", "You won! Your paper disproves my Spock."},
												{"I won! My rock crushes your scissors.", "You won! Your scissors cut my paper.", "We tied with scissors!", "You won! Your scissors decapitate my lizard.", "I won! My Spock smashes your scissors."},
												{"I won! My rock crushes your lizard.", "You won! Your lizard eats my paper.", "I won! My scissors decapitate your lizard.", "We tied with lizards!", "You won! Your lizard poisons my Spock."},
												{"You won! Your Spock vaporizes my rock.", "I won! My paper disproves your Spock.", "You won! Your Spock smashes my scissors.", "I won! My lizard poisons your Spock.", "We tied with Spocks!"}
												};
	
}
