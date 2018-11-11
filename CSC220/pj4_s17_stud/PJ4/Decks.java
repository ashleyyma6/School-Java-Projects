import java.util.*;
//=================================================================================
/** class Decks represents : n decks of 52 (or 54) playing cards
 *  Use class Card to construct n * 52 (or 54) playing cards!
 *
 *  Do not add new data fields!
 *  Do not modify any methods
 *  You may add private methods
 */

class Decks {

    /* this is used to keep track of original n*52 or n*54 cards */
    private List<Card> originalDecks;

    /* this starts with copying cards from originalDecks */
    /* it is used to play the card game                  */
    /* see reset(): resets gameDecks to originalDecks    */
    private List<Card> gameDecks;

    /* number of decks in this object */
    private int numberDecks;
    private boolean withJokers;


    /**
     * Constructor: Creates one deck of 52 or 54  (withJokers = false or true)
     *              playing cards in originalDecks and copy them to gameDecks.
     *              initialize numberDecks=1
     * Note: You need to catch PlayingCardException from Card constructor
     *	     Use ArrayList for both originalDecks & gameDecks
     */
    public Decks(boolean withJokers) {
        numberDecks = 1;
        originalDecks = new ArrayList<Card>();
        gameDecks = new ArrayList<Card>();
        this.withJokers = true;
        try {
            if (withJokers) {
                int suit = 0;
                for (int rank = 1; rank <= 2; rank++) {
                    originalDecks.add(new Card(suit, rank));
                    gameDecks.add(new Card(suit, rank));
                }
            }
            for (int suit = 1; suit <= 4; suit++) {
                for (int rank = 1; rank <= 13; rank++) {
                    originalDecks.add(new Card(suit, rank));
                    gameDecks.add(new Card(suit, rank));
                }
            }
        } catch (PlayingCardException e) {
            System.out.println("PlayingCardException: " + e.getMessage());
        }
        // implement this method!
    }


    /**
     * Constructor: Creates n decks (54 or 52 cards each deck - with or without Jokers)
     *              of playing cards in originalDecks and copy them to gameDecks.
     *              initialize numberDecks=n
     * Note: You need to catch PlayingCardException from Card constructor
     *	     Use ArrayList for both originalDecks & gameDecks
     */
    public Decks(int n, boolean withJokers) {
        numberDecks = n;
        originalDecks = new ArrayList<Card>();
        gameDecks = new ArrayList<Card>();
        this.withJokers = withJokers;
        try {
            for (int decknum = 0; decknum < numberDecks; decknum++) {
                if (withJokers) {
                    int suit = 0;
                    for (int rank = 1; rank <= 2; rank++) {
                        originalDecks.add(new Card(suit, rank));
                        gameDecks.add(new Card(suit, rank));
                    }
                }

                for (int suit = 1; suit <= 4; suit++) {
                    for (int rank = 1; rank <= 13; rank++) {
                        originalDecks.add(new Card(suit, rank));
                        gameDecks.add(new Card(suit, rank));

                    }
                }
            }
        } catch (PlayingCardException e) {
            System.out.println("PlayingCardException: " + e.getMessage());
        }
        // implement this method!
    }


    /**
     * Task: Shuffles cards in gameDecks.
     * Hint: Look at java.util.Collections
     */
    public void shuffle()
    {
        // implement this method!
        Collections.shuffle(gameDecks);

    }

    /**
     * Task: Deals cards from the gameDecks.
     *
     * @param numberCards number of cards to deal
     * @return a list containing cards that were dealt
     * @throw PlayingCardException if numberCard > number of remaining cards
     *
     * Note: You need to create ArrayList to stored dealt cards
     *       and should removed dealt cards from gameDecks
     *
     */
    public List<Card> deal(int numberCards) throws PlayingCardException {
        // implement this method!
            if (numberCards > remainSize()) {
                throw new PlayingCardException("Not enough cards to deal!");
            }
            List<Card> dealCards = new ArrayList<Card>();
            for (int i = numberCards - 1; i >= 0; i--) {
                dealCards.add(gameDecks.remove(i));
            }
        return dealCards;
    }

    /**
     * Task: Resets gameDecks by getting all cards from the originalDecks.
     */
    public void reset()
    {
        // implement this method!
        gameDecks=originalDecks;
    }

    /**
     * Task: Return number of decks.
     */
    public int getNumberDecks()
    {
	return numberDecks;
    }

    /**
     * Task: Return withJokers.
     */
    public boolean getWithJokers()
    {
	return withJokers;
    }

    /**
     * Task: Return number of remaining cards in gameDecks.
     */
    public int remainSize()
    {
	return gameDecks.size();
    }

    /**
     * Task: Returns a string representing cards in the gameDecks
     */
    public String toString()
    {
	return ""+gameDecks;
    }


    /* Quick test                    */
    /*                               */
    /* Do not modify these tests     */
    /* Generate 2 decks of 54 cards  */
    /* Loop 2 times:                 */
    /*   Deal 27 cards for 5 times   */
    /*   Expect exception at 5th time*/
    /*   reset()                     */

    public static void main(String args[]) {

        System.out.println("*******    Create 2 decks of cards      ********\n");
        Decks decks  = new Decks(2, true);
        System.out.println("getNumberDecks:" + decks.getNumberDecks());
        System.out.println("getWithJokers:" + decks.getWithJokers());

	for (int j=0; j < 2; j++)
	{
        	System.out.println("\n************************************************\n");
        	System.out.println("Loop # " + j + "\n");
		System.out.println("Before shuffle:"+decks.remainSize()+" cards");
		System.out.println("\n\t"+decks);
        	System.out.println("\n==============================================\n");

                int numHands = 5;
                int cardsPerHand = 27;

        	for (int i=0; i < numHands; i++)
	 	{
	    		decks.shuffle();
		        System.out.println("After shuffle:"+decks.remainSize()+" cards");
		        System.out.println("\n\t"+decks);
			try {
            		    System.out.println("\n\nHand "+i+":"+cardsPerHand+" cards");
            		    System.out.println("\n\t"+decks.deal(cardsPerHand));
            		    System.out.println("\n\nRemain:"+decks.remainSize()+" cards");
		            System.out.println("\n\t"+decks);
        	            System.out.println("\n==============================================\n");
			}
			catch (PlayingCardException e)
			{
		 	        System.out.println("*** In catch block:PlayingCardException:Error Msg: "+e.getMessage());
			}
		}


		decks.reset();
	}
    }

}
