
/**
 * @author Tabassum Fabiha -- tf2478
 *
 * This creates instances of the Sim, Thrower, Scorer, and Talker classes. It also
 * starts off the game by asking the Talker to welcome the user and facilitates the
 * major components of each round.
 * 
 * For each round it obtains the plays from both the user and the computer and sends
 * them to the scorer to be computed to find the winner of the round. It also asks the
 * scorer to send the round/game results to the appropriate places when appropriate.
 * 
 * Step 1 Trial Run Summary:
 *    User Win Count: 35
 *    User Win Percentage: 35.0
 *    Computer Win Count: 38
 *    Computer Win Percentage: 38.0
 *    Tie Count: 27
 *    Tie Percentage: 27.0
 *    
 *    Notes:
 *    	I think the reason why the in this trial run I ended up winning against the
 *    computer was that I wasn't really thinking about what move I should play in
 *    each round. I ended up pressing the same move two or three times in a row,
 *    which in the real world humans probably wouldn't do. Still these seem fairly even
 *    with there being almost 33% for each percentage shown above.
 *    
 * Step 2 Trial Run 0 Summary:
 *    User Win Count: 38
 *    User Win Percentage: 38.0
 *    Computer Win Count: 29
 *    Computer Win Percentage: 29.0
 *    Tie Count: 33
 *    Tie Percentage: 33.0
 *    
 *    Notes:
 *    	I went through the same process as before. The results are still pretty evenly
 *    split.
 *    
 * Step 2 Trial Run 1 Summary:
 * 	  User Win Count: 14
 *    User Win Percentage: 14.0
 *    Computer Win Count: 72
 *    Computer Win Percentage: 72.0
 *    Tie Count: 14
 *    Tie Percentage: 14.0
 *    
 *    Notes:
 *    	I used this trial to test that the recorder strategy of the AI worked. I only
 *    played the move "r". As expected the AI figured out that I had a bias and played
 *    paper every time the recorder strategy was called. The reason that the computer's 
 *    win percentage isn't even higher is because it had a 50-50 chance of using the 
 *    recorder strategy each time it played a move.
 *    
 * Step 3 Trial Run Summary:
 * 	  User Win Count: 41
 * 	  User Win Percentage: 41.0
 *    Computer Win Count: 42
 *    Computer Win Percentage: 42.0
 *    Tie Count: 17
 *    Tie Percentage: 17.0
 *    
 * 	  Notes:
 * 		I decided to go through the rotator strategy this time since there were so many
 *    options to choose from. There seems to be fewer ties in this one, but the wins
 *    between the AI and the user seem to be nearly the same.
 *    
 * Step 4 Trial Run 0 Summary:
 *    User Win Count: 43
 *    User Win Percentage: 43.0
 *    Computer Win Count: 37
 *    Computer Win Percentage: 37.0
 *    Tie Count: 20
 *    Tie Percentage: 20.0
 *    
 *    Notes:
 *    	This test run I tested the sim's rotator strategy. I checked each of the moves made
 *    by the sim to make sure it reflected the next move in the sequence 'rsplk'.
 *    
 * Step 4 Trial Run 1 Summary:
 *    User Win Count: 31
 *    User Win Percentage: 31.0
 *    Computer Win Count: 29
 *    Computer Win Percentage: 29.0
 *    Tie Count: 40
 *    Tie Percentage: 40.0
 *    
 *    Note: 
 *    	This test run I tested the sim's reflector strategy. I checked each of the moves made
 *    by the sim to make sure it reflected the last move made by the AI.
 *    
 * Step 4 Trial Run 2 Summary:
 *    User Win Count: 3544
 *    User Win Percentage: 35.44
 *    Computer Win Count: 3543
 *    Computer Win Percentage: 35.43
 *    Tie Count: 2913
 *    Tie Percentage: 29.13
 *    
 *    Note:
 *    	This test run I played 10000 rounds with an N of 15. Regardless of what N I tried out the
 *    percentages of user wins vs computer wins stayed the same. Both had approximately 35% wins 
 *    and 30% ties.
 *    
 * Step 5 Trial Run 0 Summary:
 *    User Win Count: 334
 *    User Win Percentage: 3.34
 *    Computer Win Count: 9332
 *    Computer Win Percentage: 93.32
 *    Tie Count: 334
 *    Tie Percentage: 3.34
 *    
 *    Note:
 *    	This test run was under the same conditions (10000 rounds and N of 15)as the as the previous 
 *    test run. The computer was quite accurately able to guess what the next move to be made would be
 *    the only reason the user was able to win even 3 percent of the time was because of the times in
 *    which the user switched to a new strategy.
 */
public class Runner {
	
	static int NUMROUNDS = 10000;
	static int N = 15;

	public static void main(String[] args) {

		Sim mySim = new Sim(N);
		Talker myTalker = new Talker(mySim, NUMROUNDS);
		Thrower myThrower = new Thrower();
		Scorer myScorer = new Scorer(mySim, myThrower, myTalker);
		
		myTalker.speakWelcomeText();

		for (int i = 0; i < NUMROUNDS; i++) {
			String userPlay = myTalker.getUserPlay();
			String computerPlay = myThrower.getComputerPlay();
			
			myScorer.computePlay(userPlay, computerPlay);
			myScorer.sendRoundResults();
		}
		
		myScorer.sendGameResults();
	}

}
