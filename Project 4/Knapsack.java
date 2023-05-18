import java.io.*;
import java.util.*;

public class Knapsack
{
	public static void main( String args[] ) throws Exception
	{
		if(args.length < 1) {
			System.out.print("Error: No File!");
			System.exit(0);
		}
		int result[] = {};
		int total = 0;
		try{
			Scanner file = new Scanner(new File(args[0]));
			result = new int[16];
			for(int i = 0; i < result.length; ++i){
				result[i] = file.nextInt();
			}
				total = file.nextInt();
			} catch(Exception e) {
				System.out.println("Error:" + e);
				System.exit(0);
			}
		for ( int i=0 ; i < 65535 ; ++i ){
			String string = "";
			int sum = 0;
			String bmap = toBitString( i, result.length );
			for ( int j = 0 ; j < result.length ; j++ ){
				if ( bmap.charAt(j)=='1' ){
					sum = sum + result[j];
					string = string + result[j] + " ";
			}
		}
		if (sum == total){
			System.out.println(string);
	}

}
	}

	static String toBitString( int number, int width )
	{
	String bitstring = "";
	while (number > 0)
	{	if (number % 2 == 0)
			bitstring = "0" + bitstring;
		else
			bitstring = "1" + bitstring;
		number /= 2 ;
	}
	while ( bitstring.length() < width )
			bitstring = "0" + bitstring;
	return bitstring;
	}
}
