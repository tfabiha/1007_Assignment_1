import java.io.*;
import java.util.*;

public class Scorer {

	public Scorer(Thrower thrower, Talker talker) {
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
		gameResults += "Tie Count: " + tiePercentage + "\n";
		
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
	
	private Thrower myThrower;
	private Talker myTalker;
	private Recorder myRecorder = new Recorder();
	
	private String playOptions = "rps";
	
	private String thisRoundResultText = "";
	
	// -1 -- loss, 0 -- tie, 1 -- win
	private int[][] userNumberResultOptions = {{0, -1, 1}, 
												{1, 0, -1}, 
												{-1, 1, 0}};
	private String[][] roundTextOutputOptions = {{"We tied with rocks!", "I won! My paper covers your rock.", "You won! Your rock crushes my scissors."},
												{"You won! Your paper covers my rock.", "We tied with paper!", "I won! My scissors cut your paper."},
												{"I won! My rock crushes your scissors.", "You won! Your scissors cut my paper.", "We tied with paper!"}};
}
