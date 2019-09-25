import java.io.*;
import java.util.*;

public class Sim {
	
	public Sim () {
		N = 20;
	}
	
	public Sim(int switchnum) {
		N = switchnum;
	}
	
	public String getSimInput() {
		String simInput;
		
		int NsPassed = roundsPlayed / N;
				
		if ( NsPassed % 2 == 0 ) {
			simInput = rotatorStrategy();
		}
		else {
			simInput = reflectorStrategy();
		}
		
		System.out.print("Please type in 'r', 'p', 's', 'l' or 'c'.\nYour Input: ");
		System.out.println(simInput);
		
		return simInput;
	}
	
	private String rotatorStrategy() {
		String simInput = playOptions.charAt(rotatorPlayIterator % playOptions.length()) + "";
		rotatorPlayIterator += 1;
		roundsPlayed += 1;
		return simInput;
	}
	
	private String reflectorStrategy() {
		if (computerLastPlay.isEmpty()) {
			return rotatorStrategy();
		}
			
		roundsPlayed += 1;
		return computerLastPlay;
	}
	
	public void updateSim(String computerPlay) {
		computerLastPlay = computerPlay;
	}
	
	private int N;
	private int roundsPlayed = 0;
	
	private String playOptions = "rpslc";
	private int rotatorPlayIterator = 0;
	private String computerLastPlay = "";
}
