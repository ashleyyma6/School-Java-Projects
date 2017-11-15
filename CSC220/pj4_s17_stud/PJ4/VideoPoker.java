package PJ4;
import java.util.*;

/*
 * Ref: http://en.wikipedia.org/wiki/Video_poker
 *      http://www.freeslots.com/poker.htm
 *
 *
 * Short Description and Poker rules:
 *
 * Video poker is also known as draw poker. 
 * The dealer uses a 52-card deck, which is played fresh after each playerHand. 
 * The player is dealt one five-card poker playerHand. 
 * After the first draw, which is automatic, you may hold any of the cards and draw 
 * again to replace the cards that you haven't chosen to hold. 
 * Your cards are compared to a table of winning combinations. 
 * The object is to get the best possible combination so that you earn the highest 
 * payout on the bet you placed. 
 *
 * Winning Combinations
 *  
 * 1. One Pair: one pair of the same card
 * 2. Two Pair: two sets of pairs of the same card denomination. 
 * 3. Three of a Kind: three cards of the same denomination. 
 * 4. Straight: five consecutive denomination cards of different suit. 
 * 5. Flush: five non-consecutive denomination cards of the same suit. 
 * 6. Full House: a set of three cards of the same denomination plus 
 * 	a set of two cards of the same denomination. 
 * 7. Four of a kind: four cards of the same denomination. 
 * 8. Straight Flush: five consecutive denomination cards of the same suit. 
 * 9. Royal Flush: five consecutive denomination cards of the same suit, 
 * 	starting from 10 and ending with an ace
 *
 */


/* This is the video poker game class.
 * It uses Decks and Card objects to implement video poker game.
 * Please do not modify any data fields or defined methods
 * You may add new data fields and methods
 * Note: You must implement defined methods
 */



public class VideoPoker {

    // default constant values
    private static final int startingBalance=100;
    private static final int numberOfCards=5;

    // default constant payout value and playerHand types
    private static final int[] multipliers={1,2,3,5,6,10,25,50,1000};
    private static final String[] goodHandTypes={ 
	  "One Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	", 
	  "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

    // must use only one deck
    private final Decks oneDeck;

    // holding current poker 5-card hand, balance, bet    
    private List<Card> playerHand;
    private int playerBalance;
    private int playerBet;

    /** default constructor, set balance = startingBalance */
    public VideoPoker()
    {
	this(startingBalance);
    }

    /** constructor, set given balance */
    public VideoPoker(int balance)
    {
	this.playerBalance= balance;
        oneDeck = new Decks(1, false);
    }

    /** This display the payout table based on multipliers and goodHandTypes arrays */
    private void showPayoutTable()
    { 
	System.out.println("\n\n");
	System.out.println("Payout Table   	      Multiplier   ");
	System.out.println("=======================================");
	int size = multipliers.length;
	for (int i=size-1; i >= 0; i--) {
		System.out.println(goodHandTypes[i]+"\t|\t"+multipliers[i]);
	}
	System.out.println("\n\n");
    }

    /** Check current playerHand using multipliers and goodHandTypes arrays
     *  Must print yourHandType (default is "Sorry, you lost") at the end of function.
     *  This can be checked by testCheckHands() and main() method.
     */
    private void checkHands()
    {
        // implement this method!
        int rank=0;
        String result = "";
        //Check current playerHand and update playerBalance
        if (isRoyalFlush()) {
            result = goodHandTypes[8];
            playerBalance += (playerBet * multipliers[8]);
        }else if (isStraightFlush()) {
            result = goodHandTypes[7];
            playerBalance += (playerBet * multipliers[7]);
        }else if (isFlush()) {
            result = goodHandTypes[4];
            playerBalance += (playerBet * multipliers[4]);
        }else if (isStraight()) {
            result = goodHandTypes[3];
            playerBalance += (playerBet * multipliers[3]);
        }else if (isFullHouse()) {
            result = goodHandTypes[5];
            playerBalance += (playerBet * multipliers[5]);
        }else if (isFourofaKind()) {
            result = goodHandTypes[6];
            playerBalance += (playerBet * multipliers[6]);
        }else  if (isThreeofaKind()) {
            result = goodHandTypes[2];
            playerBalance += (playerBet * multipliers[2]);
        }else if (isTwoPairs()) {
            result = goodHandTypes[1];
            playerBalance += (playerBet * multipliers[1]);
        }else if (isOnePair()) {
            result = goodHandTypes[0];
            playerBalance += (playerBet * multipliers[0]);
        } else
        {
           result = "Sorry, you lost!";
        }
        System.out.println(result);

    }

    /*************************************************
     *   add new private methods here ....
     *
     *************************************************/
    private List<Card> ranksortedHand() {
        List<Card> ranksorted = new ArrayList<Card>();
        for (int i = 0; i < playerHand.size(); i++) {
            ranksorted.add(playerHand.get(i));
        }
        Collections.sort(ranksorted, new Comparator<Card>() {
            @Override
            public int compare(Card card1, Card card2) {
                return card1.getRank() - card2.getRank();
            }
        });
        return ranksorted;
    }
    //Royal Flush: five consecutive denomination cards of the same suit,
    //starting from 10 and ending with an ace
    private boolean isRoyalFlush() {
        int counter = 0;
        if ((ranksortedHand().get(0).getRank() == 1) && (ranksortedHand().get(1).getRank() == 10) && (ranksortedHand().get(2).getRank() == 11) && (ranksortedHand().get(3).getRank() == 12) && (ranksortedHand().get(4).getRank() == 13)) {
            for (int i = 0; i < (numberOfCards - 1); i++) {
                if (ranksortedHand().get(i).getSuit() == ranksortedHand().get(i + 1).getSuit()) {
                    counter++;
                }
            }
        }
        return (counter == 4);
    }
    //Straight Flush: five consecutive denomination cards of the same suit.
    private boolean isStraightFlush() {
        int counter = 0;
        for (int i = 0; i < (numberOfCards - 1); i++) {
            if ((ranksortedHand().get(i).getRank() + 1) == ranksortedHand().get(i + 1).getRank()) {
                if (ranksortedHand().get(i).getSuit() == ranksortedHand().get(i + 1).getSuit()) {
                    counter++;
                }
            }
        }
        return (counter == 4);
    }
    
    private boolean isStraight() {
        int Rankcounter = 0;
        int Suitcounter = 0;
        for (int i = 0; i < (numberOfCards - 1); i++) {
            if ((ranksortedHand().get(i).getRank() + 1) == ranksortedHand().get(i + 1).getRank()) {
                Rankcounter++;
                if (ranksortedHand().get(i).getSuit() != ranksortedHand().get(i + 1).getSuit()) {
                    Suitcounter++;
                }
            }
        }
        return (Suitcounter > 0 && Rankcounter == 4);
    }
    //Flush: five non-consecutive denomination cards of the same suit.
    private boolean isFlush() {
        int Suitcount = 0;
        int Rankcounter = 0;
        for (int i = 0; i < (numberOfCards - 1); i++) {
            if (ranksortedHand().get(i).getSuit() == ranksortedHand().get(i + 1).getSuit()) {
                Suitcount++;
                if ((ranksortedHand().get(i).getRank() + 1) != ranksortedHand().get(i + 1).getRank()) {
                    Rankcounter++;
                }
            }
        }
        return (Rankcounter > 0 && Suitcount == 4);
    }
    //Full House: a set of three cards of the same denomination plus
    private boolean isFullHouse() {
        int counter = 0;
        List<Integer> temp = new ArrayList<Integer>();
        for (int i = 0; i < (numberOfCards - 1); i++) {
            if (ranksortedHand().get(i).getRank() == ranksortedHand().get(i + 1).getRank()) {
                temp.add(ranksortedHand().get(i).getRank());
                counter++;
            }
        }
        return (counter == 3 && (temp.get(0) == temp.get(1) && temp.get(1) != temp.get(2)));
    }    
    //Four of a kind: four cards of the same denomination.
    private boolean isFourofaKind() {
        int counter = 0;
        for (int i = 0; i < (numberOfCards - 1); i++) {
            if (ranksortedHand().get(i).getRank() == ranksortedHand().get(i + 1).getRank()) {
                counter++;
            }
        }
        return (isFullHouse() == false && counter == 3);
    }
    
    private boolean isThreeofaKind() {
        int counter = 0;
        for (int i = 0; i < (numberOfCards - 1); i++) {
            if (ranksortedHand().get(i).getRank() == ranksortedHand().get(i + 1).getRank()) {
                counter++;
            }
        }
        return (counter == 2&&isTwoPairs()==false);
    }
    
    private boolean isTwoPairs() {
        int ranka = 0;
        int rankb = 0;
        for (int i = 0; i < (numberOfCards - 1); i++) {
            if (ranksortedHand().get(i).getRank() == ranksortedHand().get(i + 1).getRank()) {
                if (ranka == 0) {
                    ranka = ranksortedHand().get(i).getRank();
                }
                if (rankb == 0 && ranka != ranksortedHand().get(i).getRank()) {
                    rankb = ranksortedHand().get(i).getRank();
                }
            }
        }
        return (ranka != rankb && rankb != 0);
    }

    private boolean isOnePair() {
        int counter = 0;
        for (int i = 0; i < (numberOfCards - 1); i++) {
            if (ranksortedHand().get(i).getRank() == ranksortedHand().get(i + 1).getRank()) {
                if ((ranksortedHand().get(i).getRank() == 11) || (ranksortedHand().get(i).getRank() == 12) || (ranksortedHand().get(i).getRank() == 13) || (ranksortedHand().get(i).getRank() == 1)) {
                    counter++;
                }
            }
        }
        return (counter==1);
    }
   
    public void play() {
        /**
         * The main algorithm for single player poker game
         *
         * Steps: showPayoutTable()
         *
         * ++ show balance, get bet verify bet value, update balance reset deck,
         * shuffle deck, deal cards and display cards ask for positions of cards
         * to replace get positions in one input line update cards check hands,
         * display proper messages update balance if there is a payout if
         * balance = O: end of program else ask if the player wants to play a
         * new game if the answer is "no" : end of program else :
         * showPayoutTable() if user wants to see it goto ++
         */

        // implement this method!
        boolean check = true;
        System.out.println("=====================");
        List<Card> keptCard = new ArrayList<Card>();
        showPayoutTable();
        
        //loop until banlance=0 or user stop game
        while (check && playerBalance > 0) {
            boolean showTable = true;
            int counter = 0;
            int errorcounter = 0;

            //show balance
            System.out.println("--------------------");
            System.out.println("Balance:$" + playerBalance);

            //get, verify bet value, updated balance
            boolean balancecheck = true;
            while (balancecheck) {
                System.out.println("Enter bet:");
                Scanner input = new Scanner(System.in);
                if (input.hasNextInt()) {
                    playerBet = input.nextInt();
                    if (playerBet <= playerBalance && playerBet < 0) {
                        balancecheck = false;
                        playerBalance = playerBalance - playerBet;
                    } else {
                        System.out.println("Wrong input, please enter again.");
                    }
                } else {
                    System.out.println("Wrong input, please enter again.");
                }
            }
            //reset deck, shuffle deck
            oneDeck.reset();
            oneDeck.shuffle();
            //deal display cards
            try {
                playerHand = oneDeck.deal(numberOfCards);     
            } catch (PlayingCardException e) {
                System.out.println("PlayingCardException: " + e.getMessage());
            }
            System.out.println(playerHand);
            //get input position and verify input
            do {
                System.out.print("Enter positions of cards to keep : ");
                Scanner input2 = new Scanner(System.in);
                String position = input2.nextLine();
                input2 = new Scanner(position);
                input2 = input2.useDelimiter("\\s*");
                while (input2.hasNext()) {
                    if (input2.hasNextInt()) {
                        int i = input2.nextInt();
                        if (i > 0 && i <= 5) {
                            counter++;
                            errorcounter = 0;
                            keptCard.add(playerHand.get(i - 1));
                        } else {
                            errorcounter++;
                            keptCard.clear();
                            System.out.println("Try again. You should input at most 5 positions.");
                            break;
                        }
                    } else {
                        errorcounter++;
                        keptCard.clear();
                        System.out.println("Try again.You should input at most 5 positions.");
                        break;
                    }
                }
                if (counter == 0 && errorcounter == 0 && input2.hasNext() == false) {
                    break;
                } else if (counter <= 5 && errorcounter == 0 && input2.hasNext() == false) {
                    break;
                } else {
                    errorcounter++;
                    keptCard.clear();
                    System.out.println("Try again.You should input at most 5 positions.");
                }
            } while (errorcounter > 0);

            //update cards
            playerHand = keptCard;
            try {
                playerHand.addAll(oneDeck.deal(5 - counter));
            } catch (PlayingCardException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Hand:" + playerHand);

            //check hands+display messages
            checkHands();

            //check balance
            System.out.println("Balcance: " + playerBalance);
            if (playerBalance == 0) {
                System.out.println("Your balance is 0");
                System.out.println("Bye!");
                break;
            } else {
                System.out.println("Your balance:$" + playerBalance);
                System.out.println("\nWould you like to bet again?  press [y] for yes, press [n] to end program");
                Scanner input3 = new Scanner(System.in);
                if (input3.hasNext()) {
                    String YN = input3.next();
                    if (YN == "y") {
                        while (showTable) {
                            System.out.println("Want to see payout table (y or n)?");
                            Scanner input4 = new Scanner(System.in);
                            if (input4.hasNext()) {
                                String YorN = input4.next();
                                if (YorN == "y") {
                                    showPayoutTable();
                                    showTable = false;
                                    break;
                                } else if (YorN == "n") {
                                    showTable = false;
                                    break;
                                } else {
                                    System.out.println("Please enter 'y' or 'n'");
                                    break;
                                }
                            } else {
                                System.out.println("Please enter 'y' or 'n'");
                            }
                        }
                        break;

                    } else if (YN == "n") {
                        check = false;
                        showTable = false;
                    } else {
                        System.out.println("Please enter 'y' or 'n'");
                        break;
                    }
                } else {
                    System.out.println("Please enter 'y' or 'n'");
                }
            }
            keptCard.clear();
            playerHand.clear();
        }
    }

    /*************************************************
     *   Do not modify methods below
    /*************************************************

    /** testCheckHands() is used to test checkHands() method 
     *  checkHands() should print your current hand type
     */ 

    public void testCheckHands()
    {
      	try {
    		playerHand = new ArrayList<Card>();

		// set Royal Flush
		playerHand.add(new Card(3,1));
		playerHand.add(new Card(3,10));
		playerHand.add(new Card(3,12));
		playerHand.add(new Card(3,11));
		playerHand.add(new Card(3,13));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight Flush
		playerHand.set(0,new Card(3,9));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight
		playerHand.set(4, new Card(1,8));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Flush 
		playerHand.set(4, new Card(3,5));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	", 
	 	// "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

		// set Four of a Kind
		playerHand.clear();
		playerHand.add(new Card(4,8));
		playerHand.add(new Card(1,8));
		playerHand.add(new Card(4,12));
		playerHand.add(new Card(2,8));
		playerHand.add(new Card(3,8));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Three of a Kind
		playerHand.set(4, new Card(4,11));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Full House
		playerHand.set(2, new Card(2,11));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Two Pairs
		playerHand.set(1, new Card(2,9));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set One Pair
		playerHand.set(0, new Card(2,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set One Pair
		playerHand.set(2, new Card(4,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set no Pair
		playerHand.set(2, new Card(4,6));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");
      	}
      	catch (Exception e)
      	{
		System.out.println(e.getMessage());
      	}
    }

    /* Quick testCheckHands() */
    public static void main(String args[]) 
    {
	VideoPoker pokergame = new VideoPoker();
	pokergame.testCheckHands();
    }
}
