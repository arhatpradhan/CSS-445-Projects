/*
	Deck class (for TopCardPlacer class of project #1
*/

import java.util.*;
import java.io.*;

public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 30;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE )
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
		deck = new int[numCards];
		for ( int i=0 ; i<numCards ; i++ ) deck[i] = i;
	}

	public String toString()
	{
		String deckStr = "";
		for ( int i=0 ; i < deck.length ; ++i )
			deckStr += deck[i] + " ";
		return deckStr;
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void inShuffle()
	{

		//new array to stored shuffled values
		int[] array = new int[deck.length];
		//count of values put into new array
		int count = 0;
		//deck's top
		int top = 0;
		//middle of deck
		int mid = deck.length/2;

		for(int i = 0; i < mid; i++){
		    array[count] = deck[mid + i];
		    count ++;
		    array[count] = deck[top + i];
		    count++;
		}
		//gives the shuffled values to member array deck
		deck = array;
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void outShuffle()
	{
		//new array to store shuffled values
		int[] array = new int[deck.length];
		//count of values put into new array
		int count = 0;
		//deck's top
		int top = 0;
		//middle of deck
		int mid = deck.length/2;

		for(int i = 0; i < mid; i++){
			  //loops through deck and adds values starting from top before mid
		    array[count] = deck[top + i];
		    count ++;
				//loops through deck and adds values starting from mid til end of deck
		    array[count] = deck[mid + i];
		    count++;
		}
		deck = array;
	}

	public String toBitString( int n ){
		if(n == 0){
			return ""; // REPLACE WITH YOUR CODE
		}
		//length of char array
		int length = (int)((Math.log(n)/Math.log(2)) + 1);
		//initalize new char arrary
		char[] bits = new char[length];
		//sets every value in char to 0
		for(int i=0; i < bits.length; i++) {
			bits[i] = '0';
		}
		//loop for converting into binary
		while (n != 0) {

				int val = (int)(Math.log(n)/Math.log(2));

				bits[(length-1) - val] = '1';

				n = n - ((int)(Math.pow(2,val)));
		}
		String str = new String(bits);
		return str;

	}
}	// END DECK CLASS
