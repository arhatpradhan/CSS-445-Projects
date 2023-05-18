import java.util.*;
import java.io.*;

public class Potus
{
	public static void main( String[] args )  throws Exception
	{
		BufferedReader state2PresidentsFile = new BufferedReader( new FileReader("state2Presidents.txt") );
		TreeMap<String,TreeSet<String>> state2Presidents= new TreeMap<String,TreeSet<String>>();

		BufferedReader allPresidentsFile = new BufferedReader( new FileReader("allPresidents.txt") );
		TreeSet<String> allPresidents = new TreeSet<String>();

		BufferedReader allStatesFile = new BufferedReader( new FileReader("allStates.txt") );
		TreeSet<String> allStates = new TreeSet<String>();

		System.out.println( "THESE STATES HAD THESE POTUS BORN IN THEM:\n"); //STEP 1

		while (state2PresidentsFile.ready()) {
			TreeSet<String> Presidents = new TreeSet<String>();
				String line = state2PresidentsFile.readLine();
				String[] tokens = line.split("\\s+");
				String state = tokens[0];
				for (int i = 1; i < tokens.length; ++i) {
					Presidents.add(tokens[i]);
				}
				state2Presidents.put(state, Presidents);
		}
		state2PresidentsFile.close();

		for (String state : state2Presidents.keySet()) {
				System.out.print(state);
				for (String president : state2Presidents.get(state))
						System.out.print(" " + president);
				System.out.println();
		}

	System.out.println( "\nLIST OF POTUS AND STATE THEY WERE BORN IN:\n");
	TreeMap<String, String> presidents2state = new TreeMap<String, String>();

	for(String state : state2Presidents.keySet()){
		for(String president : state2Presidents.get(state)){
			presidents2state.put(president, state);
		}
	}
	for(String president : presidents2state.keySet()){
		System.out.println(president + " " + presidents2state.get(president));
	}


	System.out.println( "\nTHESE POTUS BORN BEFORE STATES WERE FORMED:\n");
	TreeSet<String> thePresidents = new TreeSet<String>();
	while(allPresidentsFile.ready()){
		thePresidents.add(allPresidentsFile.readLine());
	}
	TreeSet<String> presWithStates = new TreeSet<String>();
	for(String president : presidents2state.keySet()){
		presWithStates.add(president);
	}
	for(String president : diff(thePresidents, presWithStates)){
		System.out.println(president);
	}


	System.out.println( "\nTHESE STATES HAD NO POTUS BORN IN THEM:\n");
	TreeSet<String> theStates = new TreeSet<String>();
	while(allStatesFile.ready()){
		theStates.add(allStatesFile.readLine());
	}
	TreeSet<String> PresWithoutStates = new TreeSet<String>();
	for(String state : state2Presidents.keySet()){
		PresWithoutStates.add(state);
	}
	for(String state : diff(theStates, PresWithoutStates)){
		System.out.println(state);
		}
	} // END MAIN

	//       - - - - - - - - - - -  H E L P E R    M E T H O D S - - - - - - - -


		static TreeSet<String> diff( TreeSet<String> set1, TreeSet<String> set2 )
		{
			TreeSet<String> set = new TreeSet<String>();
			set.addAll(set1);
			set.removeAll(set2);
			return set;
		}

	}
