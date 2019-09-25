import java.io.*;
import java.util.*;

public class Runner {

	public static void main(String[] args) {

		Talker myTalker = new Talker();
		Thrower myThrower = new Thrower();
		Scorer myScorer = new Scorer(myThrower, myTalker);
		
		myTalker.speakWelcomeText();

		for (int i = 0; i < 100; i++) {
			String userPlay = myTalker.getUserPlay();
			String computerPlay = myThrower.getComputerPlay();
			System.out.println(computerPlay);
			
			myScorer.computePlay(userPlay, computerPlay);
			myScorer.sendRoundResults();
		}
		
		myScorer.sendGameResults();
	}

}
