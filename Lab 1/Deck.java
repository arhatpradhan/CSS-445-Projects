/*
	Deck class
*/

import java.util.*;
import java.io.*;

public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 20;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE )
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}

		// YOU DO THIS => init deck to be exactly numCards long
	  deck = new int[numCards];
		// YOU DO THIS => fill deck with with 0 1 2 3 ... numCards-1 in order
		for(int i = 0; i < deck.length; i++){
			deck[i] = i;
		}
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
	public void inShuffle()	{
		//new array to stored shuffled values
		int[] array = new int[deck.length];
		//count of values put into new array
		int count = 0 ;
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
	public void outShuffle(){
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

	// RETURNS TRUE IF DECK IN ORIGINAL SORTED:  0 1 2 3 ...
	public boolean inSortedOrder(){
		//returns false if new array does not match original array
		for(int i = 0; i < deck.length; i++){
			if(deck[i] != i){
				return false;
			}
		}
		return true; // JUST HERE TO COMPILE
	}
}	// END DECK CLASS
