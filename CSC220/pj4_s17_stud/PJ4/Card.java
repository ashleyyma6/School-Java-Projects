 import java.util.*;
//=================================================================================
/** class Card : for creating playing card objects
 *  it is an immutable class.
 *  Rank - valid values are 1 to 13
 *  Suit - valid values are 0 to 4
 *  Do not modify this class!
 */
class Card {

    /* constant suits and ranks */
    static final String[] Suit = {"Joker","Clubs", "Diamonds", "Hearts", "Spades" };
    static final String[] Rank = {"","A","2","3","4","5","6","7","8","9","10","J","Q","K"};

    /* Data field of a card: rank and suit */
    private int cardRank;  /* values: 1-13 (see Rank[] above) */
    private int cardSuit;  /* values: 0-4  (see Suit[] above) */

    /* Constructor to create a card */
    /* throw PlayingCardException if rank or suit is invalid */
    public Card(int suit, int rank) throws PlayingCardException {

        // suit =0 is joker, rank must be 1 or 2
	if (suit==0) {
	    if ((rank <1) || (rank >2))
	       throw new PlayingCardException("Invalid rank for Joker:"+rank);
	    cardRank=rank;
	    cardSuit=0;
        } else {

	    if ((rank < 1) || (rank > 13))
		throw new PlayingCardException("Invalid rank:"+rank);
	    else
        	cardRank = rank;

	    if ((suit < 1) || (suit > 4))
		throw new PlayingCardException("Invalid suit:"+suit);
	    else
        	cardSuit = suit;
   	}
    }

    /* Accessor and toString */
    /* You may impelemnt equals(), but it will not be used */
    public int getRank() { return cardRank; }
    public int getSuit() { return cardSuit; }
    public String toString() {
	if (cardSuit == 0) return Suit[cardSuit]+" #"+cardRank;
        else return Rank[cardRank] + " " + Suit[cardSuit];
    }


    /* Few quick tests here */
    public static void main(String args[])
    {
	try {
	    Card c1 = new Card(4,1);    // A Spades
	    System.out.println(c1);
	    c1 = new Card(1,10);	// 10 Clubs
	    System.out.println(c1);
	    c1 = new Card(0,2);		// Joker #2
	    System.out.println(c1);
	    c1 = new Card(5,10);        // generate exception here
	}
	catch (PlayingCardException e)
	{
	    System.out.println("PlayingCardException: "+e.getMessage());
	}
    }
}
