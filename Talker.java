import java.io.*;
import java.util.*;

public class Talker {

	public Talker() {
		reader = new Scanner(System.in);
	}
	
	//TODO INCLUDE WELCOME TEXT WITH ALL THE RULES OF THE GAME
	public void speakWelcomeText() {
		System.out.println("hi");
	}
	
	public void speakResults(String results) {
		System.out.println(results);
	}
	
	public String getUserPlay() {
		System.out.println("New round has begun.");
		String userPlay = getUserInput();
		return userPlay;
	}
	
	private String getUserInput() {
		boolean incorrectInput = true;
		String userInput = "";
		
		while (incorrectInput) {
			System.out.println("Please type in 'r', 'p', or 's'.");
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
	private String[] userChoices = {"r", "p", "s"};
}
