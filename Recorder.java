import java.io.*;
import java.util.*;

public class Recorder {

	public int getUserWinCount() {
		return userWinCount;
	}

	public void increaseUserWinCount() {
		userWinCount += 1;
	}
	
	public int getComputerWinCount() {
		return computerWinCount;
	}

	public void increaseComputerWinCount() {
		computerWinCount += 1;
	}
	
	public int getTieCount() {
		return tieCount;
	}

	public void increaseTieCount() {
		tieCount += 1;
	}
	
	public int getTotalTimesPlayed() {
		return totalTimesPlayed;
	}

	public void increaseTotalTimesPlayed() {
		totalTimesPlayed += 1;
	}
	
	private int userWinCount = 0;
	private int computerWinCount = 0;
	private int tieCount = 0;
	private int totalTimesPlayed = 0;
}
