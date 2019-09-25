import java.io.*;
import java.util.*;

public class Runner {

	public static void main(String[] args) {

		Sim mySim = new Sim(5);
		Talker myTalker = new Talker(mySim);
		Thrower myThrower = new Thrower();
		Scorer myScorer = new Scorer(mySim, myThrower, myTalker);
		
		myTalker.speakWelcomeText();

		for (int i = 0; i < 10000; i++) {
			String userPlay = myTalker.getUserPlay();
			String computerPlay = myThrower.getComputerPlay();
			
			//TODO REMOVE WHEN FINISHED DEBUGGING
			System.out.println(computerPlay);
			
			myScorer.computePlay(userPlay, computerPlay);
			myScorer.sendRoundResults();
		}
		
		myScorer.sendGameResults();
	}

}
