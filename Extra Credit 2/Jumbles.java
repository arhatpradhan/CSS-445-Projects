import java.util.*;
import java.io.*;

public class Jumbles{
    public static void main(String[] args) throws Exception {

      if ( args.length < 2 )
  			die( "FATAL ERROR: must enter two filenames on command line." );

  		BufferedReader inFile0 = new BufferedReader( new FileReader( args[0] ) );
  		while ( infile0.ready() )

  		infile0.close();

      BufferedReader inFile1 = new BufferedReader( new FileReader( args[1] ) );
      while ( infile1.ready() )

      infile1.close();

      
  }
}
