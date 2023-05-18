import java.io.*;
import java.util.*;

public class Pacs
{	public static void main( String args[] ) throws Exception
	{	BufferedReader memberToPacsFile = new BufferedReader(new FileReader( "member2Pacs.txt"));
		BufferedReader AllPacsFile= new BufferedReader(new FileReader("allPacs.txt"));
		TreeSet<String> allPacs= new TreeSet<String>();
		while( AllPacsFile.ready())
			allPacs.add(AllPacsFile.readLine());

						//key 	//value
		TreeMap<String, TreeSet<String>> pacToMembers = new TreeMap<String, TreeSet<String>>(); // THE MAP THAT GETS PRINTED

		//creates an empty treeset for all the keys in the map
		for(String pac: allPacs){
			pacToMembers.put(pac, new TreeSet<String>());
		}

		while(memberToPacsFile.ready()){
				String line = memberToPacsFile.readLine(); //bgates BFPR AFCTC
				String tokens[] = line.split("\\s+");
				//token 0 will be the value, everything after is a key
				for(int i = 1; i< tokens.length; i++ ){
					//set new treeset
					TreeSet<String> pacs = pacToMembers.get(tokens[i]);
						if(!pacs.contains(tokens[0])){
							pacs.add(tokens[0]);
						}
					}
				}

	for(String member: pacToMembers.keySet()){
			 System.out.print(member);

				for(String pac: pacToMembers.get(member)){
				System.out.print( " " + pac);
			}
			System.out.println();
		}

	} // END MAIN
} // CLASS
