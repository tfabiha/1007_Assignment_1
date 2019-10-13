import java.util.*;

/**
 * @author Tabassum Fabiha -- tf2478
 *
 * The class computes the moves made by the computer. 
 * 
 * If there's been less than three rounds played thus far then it chooses between either 
 * randomly choosing a play (random strategy) or choosing a play that would win against 
 * the user's most picked move (recorder strategy). The Thrower has a 50-50 chance of choosing 
 * either strategy.
 * 
 * If there's been three or more rounds played then the computer tries to figure out what
 * move the user will play next by analyzing the last 6 moves played thus far and picks a
 * move that will win against that (revenge strategy).
 */
public class Thrower {
	
	/**
	 * @return computer's play for this round
	 * 
	 * There's a fifty-fifty chance that the computer will choose to play the 
	 * random strategy or the recorder strategy.
	 */
	public String getComputerPlay() {
		String computerPlay;
		int chanceToGoRandom;
		
		if (last6Moves.size() < 6) {
			chanceToGoRandom = (int) (Math.random() * 2);
			
			if (chanceToGoRandom == 0) {
				computerPlay = randomStrategy();
			}
			else {
				computerPlay = recorderStrategy();
			}
		}
		else {
			computerPlay = revengeStrategy();
		}
		
		if (last6Moves.size() >= 6) {
			last6Moves.removeFirst();
		}
		last6Moves.addLast(computerPlay);
		
		return computerPlay;
	}
	
	/**
	 * @return computer's play this round
	 * 
	 * Randomly chooses a play from 'rsplc' and gives each option 
	 * an equal chance of being chosen. 
	 */
	private String randomStrategy() {
		int i = (int) (Math.random() * playOptions.length());
		return playOptions.substring(i,i+1);
	}
	
	/**
	 * @return computer's play this round
	 * 
	 * Finds the option that the user chooses most often and picks a play
	 * that will win against that option. 
	 */
	private String recorderStrategy() {
		int indexOfLargestCount = getIndexOfLargestCount();
		String computerPlay = playToWinAgainst(indexOfLargestCount);
		
		return computerPlay;
	}
	
	/**
	 * @return computer's play this round
	 * 
	 * Assumes we have played 3 rounds of the game.
	 */
	private String revengeStrategy() {
		String computerPlay = "s";
		
		if (userUsesRotatorStrategy()) {
			computerPlay = counterRotatorStrategy();
		}
		else {
			computerPlay = counterReflectorStrategy();
		}
		
		return computerPlay;
	}
	
	/**
	 * @return computer's play this round
	 * 
	 * Finds the move the computer should play to win against the user next
	 * round if the user is using the rotator strategy.
	 */
	private String counterRotatorStrategy() {
		String computerPlay;
		String userPlayOptionsTwice = playOptions + playOptions;
		String userLastPlay = last6Moves.getLast();
		
		int userNextPlayIndex = (1 + userPlayOptionsTwice.indexOf(userLastPlay)) % playOptions.length();
	
		computerPlay = playToWinAgainst(userNextPlayIndex);
		return computerPlay;
	}
	
	/**
	 * @return computer's play this round
	 * 
	 * Finds the move the computer should play to win against the user next 
	 * round if the user is using the reflector strategy
	 */
	private String counterReflectorStrategy() {
		String computerPlay = "s";
		String computerLastPlay = last6Moves.get(4);
		int userNextPlayIndex = playOptions.indexOf(computerLastPlay);
		
		computerPlay = playToWinAgainst(userNextPlayIndex);
		return computerPlay;
	}
	
	/**
	 * @return if the user has been using the rotator strategy the past three rounds
	 * 
	 * Looks at the user's plays for the last three rounds and sees if they're in the
	 * pattern of the rotator strategy
	 */
	private boolean userUsesRotatorStrategy() {
		String userPlayOptionsTwice = playOptions + playOptions;
		String lastUserPlays = "";
		
		lastUserPlays += last6Moves.get(1);
		lastUserPlays += last6Moves.get(3);
		lastUserPlays += last6Moves.get(5);
		
		return userPlayOptionsTwice.contains(lastUserPlays);
	}
	
	/**
	 * @param userPlayIndex the index of the move that the user will next play
	 * @return computer's play this round
	 * 
	 * Picks a play that will win against the userPlayIndex.
	 */
	private String playToWinAgainst(int userPlayIndex) {
		int[] computerPlayOptions = numberResultOptions[userPlayIndex];
		
		int computerWin = -1;
		String computerPlay = "";
		
		for (int i = 0; i < computerPlayOptions.length; i++) {
			if (computerPlayOptions[i] == computerWin) {
				computerPlay = playOptions.substring(i,i+1);
			}
		}
		
		return computerPlay;
	}	
	
	/**
	 * @return the index of the option that the user chooses most often
	 * 
	 * Iterates through the counts of each option that the user has chosen over the
	 * course of the game thus far and finds the one with the highest count. 
	 */
	private int getIndexOfLargestCount() {
		int indexOfLargestCount = 0;
		
		for (int i = 1; i < userPlaysRecord.length; i++) {
			if (userPlaysRecord[i] > indexOfLargestCount) {
				indexOfLargestCount = i;
			}
		}
		
		return indexOfLargestCount;
	}
	
	/**
	 * @param userPlay user's play last round
	 * 
	 * Adds the user's play last round to the record of all of the user's
	 * plays over the course of the game thus far.
	 */
	public void addUserPlay(String userPlay) {
		int userPlayIndex = playOptions.indexOf(userPlay);
		userPlaysRecord[userPlayIndex] += 1;
		
		if (last6Moves.size() >= 6) {
			last6Moves.removeFirst();
		}
		last6Moves.addLast(userPlay);
	}
	
	private String playOptions = "rpslk";
	private int[] userPlaysRecord = {0, 0, 0, 0, 0};
	private LinkedList<String> last6Moves = new LinkedList<String>();
	
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
	private int[][] numberResultOptions = {{0, -1, 1, 1, -1}, 
											{1, 0, -1, -1, 1}, 
											{-1, 1, 0, 1, -1}, 
											{-1, 1, -1, 0, 1},
											{1, -1, 1, -1, 0}
											};
	
}
