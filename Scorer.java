import java.io.*;
import java.util.*;

public class Scorer {

	public Scorer(Sim sim, Thrower thrower, Talker talker) {
		mySim = sim;
		myThrower = thrower;
		myTalker = talker;
	}
	
	public void sendRoundResults() {
		myTalker.speakResults(thisRoundResultText);
	}
	
	public void sendGameResults() {
		String gameResults = getGameResults();
		myTalker.speakResults(gameResults);
	}
	
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
	
	private double percentage(int count) {
		double decimal = ( (1.0 * count) / myRecorder.getTotalTimesPlayed() );
		double percentage = Math.floor(decimal * 10000) / 100;
		
		return percentage;
	}
	
	public void computePlay(String userPlay, String computerPlay) {
		int userPlayIndex = playOptions.indexOf(userPlay);
		int computerPlayIndex = playOptions.indexOf(computerPlay);
		
		thisRoundResultText = roundTextOutputOptions[userPlayIndex][computerPlayIndex];
		int thisRoundResultNumber = userNumberResultOptions[userPlayIndex][computerPlayIndex];
		
		updateRecorder(thisRoundResultNumber);
		mySim.updateSim(computerPlay);
	}
	
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
	
	private String playOptions = "rpslc";
	
	private String thisRoundResultText = "";
	
	// -1 -- loss, 0 -- tie, 1 -- win
	private int[][] userNumberResultOptions = {{0, -1, 1, 1, -1}, 
												{1, 0, -1, -1, 1}, 
												{-1, 1, 0, 1, -1},
												{-1, 1, -1, 0, 1},
												{1, -1, 1, -1, 0}};
	private String[][] roundTextOutputOptions = {{"We tied with rocks!", "I won! My paper covers your rock.", "You won! Your rock crushes my scissors.", "You won! Your rock crushes my lizard.", "I won! My Spock vaporizes your rock."},
												{"You won! Your paper covers my rock.", "We tied with paper!", "I won! My scissors cut your paper.", "I won! My lizard eats your paper.", "You won! Your paper disproves my Spock."},
												{"I won! My rock crushes your scissors.", "You won! Your scissors cut my paper.", "We tied with scissors!", "You won! Your scissors decapitate my lizard.", "I won! My Spock smashes your scissors."},
												{"I won! My rock crushes your lizard.", "You won! Your lizard eats my paper.", "I won! My scissors decapitate your lizard.", "We tied with lizards!", "You won! Your lizard poisons my Spock."},
												{"You won! Your Spock vaporizes my rock.", "I won! My paper disproves your Spock.", "You won! Your Spock smashes my scissors.", "I won! My lizard poisons your Spock.", "We tied with Spocks!"}};
}
