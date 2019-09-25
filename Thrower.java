import java.io.*;
import java.util.*;

public class Thrower {

	private String randomStrategy() {
		int i = (int) (Math.random() * playOptions.length());
		return playOptions.substring(i,i+1);
	}
	
	private String recorderStrategy() {
		int chanceToGoRandom = (int) (Math.random() * 2);
		if (chanceToGoRandom == 0) {
			return randomStrategy();
		}
		
		int indexOfLargestCount = getIndexOfLargestCount();
		int[] computerPlayOptions = numberResultOptions[indexOfLargestCount];
		
		int computerWin = -1;
		String result = "";
		
		for (int i = 0; i < computerPlayOptions.length; i++) {
			if (computerPlayOptions[i] == computerWin) {
				result = playOptions.substring(i,i+1);
			}
		}
		
		return result;
	}
	
	private int getIndexOfLargestCount() {
		int indexOfLargestCount = userPlaysRecord[0];
		
		for (int i = 1; i < userPlaysRecord.length; i++) {
			if (userPlaysRecord[i] > indexOfLargestCount) {
				indexOfLargestCount = i;
			}
		}
		
		return indexOfLargestCount;
	}
	
	public void addUserPlay(String userPlay) {
		int userPlayIndex = playOptions.indexOf(userPlay);
		userPlaysRecord[userPlayIndex] += 1;
	}
	
	public String getComputerPlay() {
		return randomStrategy();
		//return recorderStrategy();
	}
	
	private String playOptions = "rpslc";
	private int[] userPlaysRecord = {0, 0, 0, 0, 0};
	
	private int[][] numberResultOptions = {{0, -1, 1, 1, -1}, 
											{1, 0, -1, -1, 1}, 
											{-1, 1, 0, 1, -1}, 
											{-1, 1, -1, 0, 1},
											{1, -1, 1, -1, 0}};
	
}
