import java.io.*;
import java.util.*;

public class GraphLL
{
	private final int NO_EDGE = -1; // all real edges are positive
	private  Edge[] G;              // every G[i] is the head of a linked list, i.e ref to an Edge

	private int numEdges;
	public GraphLL( String graphFileName ) throws Exception  // since readFild doesn't handle them either
	{
		loadGraphFile( graphFileName );
	}

	///////////////////////////////////// LOAD GRAPH FILE //////////////////////////////////////////

	private void loadGraphFile( String graphFileName ) throws Exception
	{
		Scanner graphFile = new Scanner( new File( graphFileName ) );
		int numNodes = graphFile.nextInt();
		G = new Edge[numNodes];
		numEdges=0;

		// NOW LOOP THRU THE FILE READING EACH TRIPLET row col weight FROM THE LINE
		// DO an insertAtFront using g[SRC] as the head

		while ( graphFile.hasNextInt() )
		{  // read in the src, dest, weight
			// call addEdge
      int sourceNode = graphFile.nextInt();
			int destNode= graphFile.nextInt();
			int weight = graphFile.nextInt();

			addEdge(sourceNode, destNode, weight);
		}
	} // END readGraphFile

	// GO TO G[src] AND DO INSERTATFRONT ON THAT 'head' POINTER I.E. G[src]
	private void addEdge( int src, int dest, int weight )
	{
    insertAtFront(src, dest, weight);
	}

  public void insertAtFront( int s, int d, int w)
  {
    G[s] = new Edge(d, w, G[s]);
  }

	private boolean hasEdge(int src, int dest)
	{
		// GOTO G[src] AND WALK THAT LINKED LIST LOOKING FOR A NODE (EDGE) WITH DEST
    return false;
	}

	private int inDegree(int dest) // # of roads(edges) entering this city (node)
	{	// THE HARDER ONE
		int inDeg = 0;
		// WALK ALL THE LISTS COUNTING THE NODE HAVING THIS DEST
    for(int i = 0; i < G.length; ++i){
			Edge curr = G[i];
			while(curr != null) {
				if(curr.dest == dest){
					inDeg ++;
				}
				curr = curr.next;
		}
	}
		return inDeg;
	}

	private int outDegree(int src) // # of roads(edges) leaving this city (src node #)
	{	// THE EASIER ONE
		int outDeg=0;
		// JUST RETURN THE LENGTH OF THIS LIST AT G[src]
    Edge curr = G[src];
    while(curr != null){
      outDeg ++;
      curr = curr.next;
    }
		return outDeg;
	}

	private int degree(int node) // indegree + outdegree of this node #
	{
		return inDegree(node) + outDegree(node);
	}

	// PUBLIC METHODS. THIS CODE COPIED FROM THE GRAPH2D LAB AND USED AS IS. SINCE IT IS NOT
	// DEPENDENT ON UNDERLYING DATA STRUCTURE AND RELIES ONLY ON CALLING THE 3 DEGREE FUNCTIONS

	public int maxOutDegree()
	{
		int maxOutDegree = outDegree(0); // ASSUM 1STNODE HAS LARGEST OUTDEG
		// LOOP OVER ALL NODES CALLING THE OUTDEG OF THAT NODE- RMEMBER THE LARGEST OUTDEG
    for (int node = 0 ; node < G.length ; node++ ) {
      if(outDegree(node) > maxOutDegree  ){
      maxOutDegree = outDegree(node);
      }
    }
		return maxOutDegree;
	}

	public int maxInDegree()
	{
		int maxInDegree = inDegree(0); // ASSUM 1STNODE HAS LARGEST OUTDEG
		// SAME AS ABOVE BUT CALLING ALL INS AND REMEMBERING  LARGEST INDEG
    for (int node = 0 ; node < G.length ; node++ ) {
      if(inDegree(node) > maxInDegree  ){
      maxInDegree = inDegree(node);
      }
    }
		return maxInDegree;
	}

	public int minOutDegree()
	{
		int minOutDegree = outDegree(0); // ASSUM 1STNODE HAS SMALLES OUTDEG
		// SAME PATTERN AS ABOVE
    for (int node = 0 ; node < G.length ; node++ ) {
      if(outDegree(node) < minOutDegree  ){
      minOutDegree = outDegree(node);
      }
    }
		return minOutDegree;
	}

	public int minInDegree()
	{
		int minInDegree = inDegree(0); // ASSUM 1STNODE HAS LARGEST OUTDEG
		// SAME PATTERN
    for (int node = 0 ; node < G.length ; node++ ) {
      if(inDegree(node) < minInDegree  ){
      minInDegree = inDegree(node);
      }
    }
		return minInDegree;
	}

	public int maxDegree()
	{
		int maxDegree = degree(0);
		// SAME PATTERN
    for (int node = 0 ; node < G.length ; node++ ) {
      if(degree(node) > maxDegree  ){
      maxDegree = degree(node);
      }
    }
		return maxDegree;
	}

	public int minDegree()
	{
		int minDegree = degree(0);
		// SAME PATTERN
    for (int node = 0 ; node < G.length ; node++ ) {
      if(degree(node) < minDegree  ){
      minDegree = degree(node);
      }
    }
		return minDegree;
	}

	public void removeEdge(int src, int dest)
	{	// ITS AN OLD FASHIONED FIND & REMOVE NODE ON A 1 WAY LINKED LIST
		// IF THE LIST AT G[src] IS NULL -OR-  SRC OR DEST OUT OF BOUNDS
		// 		THROW AND CATCH AN EXCEPTION - SEE OUTPUT
		// USE A BASE CASE TEST FOR 1ST NODE BEGIN THE ONE
		// WALK A CURR UP TO THE PRED OF THE DEADNODE
		// REMOVE THE NODE (IF ITS THERE)
		// ITS NOT THERE THROW AND CATCH AN EXCEPTION (SEE OUTPUT)
    try {
			remove(src, dest);
		} catch (Exception e) {
			System.out.print("java.lang.Exception: Non Existent Edge Exception: removeEdge(" + src + "," + dest + ")" );
			System.exit(0);
		}

	} // E N D  R E M O V E D G E
  public void remove (int fromNode, int toNode)
	{
		if(G[fromNode].dest == toNode){
			G[fromNode] = G[fromNode].next;
			return;
		}

		Edge curr = G[fromNode];
		while(curr.next != null){
			if(curr.next.dest == toNode){
				 G[fromNode] = curr;
				 G[fromNode].next = G[fromNode].next.next;
				 return;
			}
			curr = curr.next;
		}
	}
	// TOSTRING
	public String toString()
	{
    String toString = "";
		for(int i = 0; i < G.length; ++i){
			if(G[i] != null){
				toString += i + ":" + " -> [" + G[i].dest + "," + G[i].weight + "]";
				Edge curr = G[i];
				while(curr.next != null) {
					toString += " -> [" + curr.next.dest + "," + curr.next.weight + "]";
					curr = curr.next;
			}
		} else {
			toString += i + ":";
		}
		toString += "\n";


	}
	return toString;
	} // END TOSTRING
} // E N D    G R A P H L L    C L A S S

// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

class Edge
{
	// DEFINE dest,weight,nextInt
	// DEFINE FULL CONSTRUCTOR
  int dest, weight;
	Edge next;

	Edge( int dest, int weight, Edge next){
		this.dest = dest;
		this.weight = weight;
		this.next = next;
  }
}
