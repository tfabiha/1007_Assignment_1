import java.io.*;
import java.util.*;

public class Talker {

	public Talker(Sim sim) {
		reader = new Scanner(System.in);
		mySim = sim;
	}
	
	//TODO INCLUDE WELCOME TEXT WITH ALL THE RULES OF THE GAME
	public void speakWelcomeText() {
		System.out.println("hi");
	}
	
	public void speakResults(String results) {
		System.out.println(results);
		System.out.print("\n");
	}
	
	public String getUserPlay() {
		System.out.println("New round has begun.");
		//String userPlay = getUserInput();
		String userPlay = mySim.getSimInput();
		return userPlay;
	}
	
	private String getUserInput() {
		boolean incorrectInput = true;
		String userInput = "";
		
		while (incorrectInput) {
			System.out.print("Please type in 'r', 'p', or 's'.\nYour Input: ");
			userInput = reader.nextLine();
			
			incorrectInput = isInvalidInput(userInput);
		}
		
		return userInput;
	}
	
	private boolean isInvalidInput(String userInput) {
		boolean invalid = true;

		for (int i = 0; i < userChoices.length; i++) {
			if (userInput.equals( userChoices[i] )) {
				return !invalid;
			}
		}
		
		return invalid;
	}
	
	private Scanner reader;
	private Sim mySim;
	private String[] userChoices = {"r", "p", "s", "l", "c"};
}
