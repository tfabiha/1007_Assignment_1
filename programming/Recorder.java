
/**
 * @author Tabassum Fabiha -- tf2478
 * 
 * This class records all the counts throughout the game, for wins, ties, and total rounds played
 * thus far.
 */
public class Recorder {

	/**
	 * @return user's win count thus far
	 */
	public int getUserWinCount() {
		return userWinCount;
	}

	/**
	 * increases the user's win count by one
	 */
	public void increaseUserWinCount() {
		userWinCount += 1;
	}
	
	/**
	 * @return computer's win count thus far
	 */
	public int getComputerWinCount() {
		return computerWinCount;
	}

	/**
	 * increase the computer's win count by one
	 */
	public void increaseComputerWinCount() {
		computerWinCount += 1;
	}
	
	/**
	 * @return tie count for the user and computer thus far
	 */
	public int getTieCount() {
		return tieCount;
	}

	/**
	 * increases the tie count by one
	 */
	public void increaseTieCount() {
		tieCount += 1;
	}
	
	/**
	 * @return the total number of rounds played thus far
	 */
	public int getTotalTimesPlayed() {
		return totalTimesPlayed;
	}

	/**
	 * increases the total number of rounds by one
	 */
	public void increaseTotalTimesPlayed() {
		totalTimesPlayed += 1;
	}
	
	private int userWinCount = 0;
	private int computerWinCount = 0;
	private int tieCount = 0;
	private int totalTimesPlayed = 0;
}
