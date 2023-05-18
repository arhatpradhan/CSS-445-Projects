import java.io.*;
import java.util.*;

public class CDLL_JosephusList<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count = 0;
	// private Scanner kbd = new Scanner(System.in); // FOR DEBUGGING. See executeRitual() method
	private Scanner kbd = new Scanner(System.in);
	public CDLL_JosephusList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE

	public CDLL_JosephusList( String infileName ) throws Exception
	{
		BufferedReader infile = new BufferedReader( new FileReader( infileName ) );
		while ( infile.ready() )
		{	@SuppressWarnings("unchecked")
			T data = (T) infile.readLine(); // CAST CUASES WARNING (WHICH WE CONVENIENTLY SUPPRESS)
			insertAtTail( data );
		}
		infile.close();
	}



	// ########################## Y O U   W R I T E / F I L L   I N   T H E S E   M E T H O D S ########################

	// TACK ON NEW NODE AT END OF LIST
	public void insertAtFront(T data)
	{
		// BASE CASE WRITTEN FOR YOU
		CDLL_Node<T> newNode = new CDLL_Node<T>( data, null, null);;
		if (head==null)
		{
			newNode.next = newNode;
			newNode.prev = newNode;
			head = newNode;
			return;
		}
		// CAN YOU WRITE THE CODE BELOW IN LESS LINES OF CODE ?
																//pointer to the tail node
		CDLL_Node<T> old1st = head, oldlast = head.prev;
		//sets head to new node
		head = newNode;
		//sets the new node next to the old node
		newNode.next = old1st;
		//set new node previous to old node
		newNode.prev = oldlast;
		//set old1st previous to new node
		old1st.prev = newNode;
		//set oldlast next to new node
		oldlast.next = newNode;

	}
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data)
	{
			insertAtFront(data);
			head = head.next;
	}

	public int size()
	{
		CDLL_Node<T> curr = head;
		//if head is null there is nothing in the list
		count = 0;
    if(head == null)return 0;

		//while curr != head keep incrementing
    do{
			curr = curr.next;
			count ++;
    }while(curr != head);

		return count;
	}

	// RETURN REF TO THE FIRST NODE CONTAINING  KEY. ELSE RETURN NULL
	public CDLL_Node<T> search( T key )
	{
		CDLL_Node<T> curr = head;
    do{
       if(curr.data.equals(key)) return curr;
       curr = curr.next;
    }while(curr!= head);

		return null;
	}

	// RETURNS CONATENATION OF CLOCKWISE TRAVERSAL
	@SuppressWarnings("unchecked")
	public String toString()
	{
		String toString = " ";
		CDLL_Node<T> curr = head;

		do{
		 	toString += curr.data;
		 	if(curr.next != head) toString += "<=>";
		 	curr = curr.next;
				} 	while(curr != head);

		 return toString;
	}

	void removeNode( CDLL_Node<T> deadNode )
	{
		CDLL_Node<T> deadNodeNext = deadNode.next;
		CDLL_Node<T> deadNodePrev = deadNode.prev;

		//node after deadNode prev should be set to deadNode's prevous
		deadNodeNext.prev = deadNodePrev;
		deadNodePrev.next = deadNode.next;
	}

	public void executeRitual( T first2Bdeleted, int skipCount )
	{
		if (size() <= 1 ) return;
		CDLL_Node<T> curr = search( first2Bdeleted );
		if ( curr==null ) return;

		// OK THERE ARE AT LEAST 2 NODES AND CURR IS SITING ON first2Bdeleted
		do
		{
			CDLL_Node<T> deadNode = curr;
			T deadName = deadNode.data;
			System.out.println( "stopping on " + deadName + " to delete " + deadName);

			// BEFORE DOING ACTUAL DELETE DO THESE TWO THINGS
			// 1: you gotta move that curr off of the deadNode.
			//    if skipCount poitive do curr=curr.next  else do  curr=curr.prev
			if(skipCount > 0) curr = curr.next;
			else curr = curr.prev;
			// 2: check to see if HEAD is pointing to the deadnode.
			if(head == deadNode) head = curr;
			//    If so make head=curr

			// NOW DELETE THE DEADNODE
			removeNode(deadNode);
			System.out.println("deleted. list now: " + toString());

			// if the list size has reached 1 return YOU ARE DONE.  RETURN RIGHT HERE
			if(size() == 1) return;

			if(skipCount > 0)	System.out.println("resuming at " + curr.data + "," + " skipping " + curr.data  + " + " + (skipCount - 1) + " nodes CLOCKWISE after");
			else System.out.println("resuming at " + curr.data + "," + " skipping " + curr.data  + " " +  Math.abs(skipCount + 1) + " nodes COUNTER_CLOCKWISE after");

			// write loop that advances curr pointer skipCount times (be sure of CLOCKWISE or COUNTER)
			int counter = 0;
			while(counter != skipCount){
				if(skipCount > 0){
				counter ++;
				curr = curr.next;
				}else{
					counter --;
					curr = curr.prev;

					}

			}
			// OPTIONAL HERE FOR DEBUGGING TO MAKE IT STOP AT BOTTOM OF LOOP
		}
		while (size() > 1 );  // ACTUALLY COULD BE WHILE (TRUE) SINCE WE RETURN AS SOON AS SIZE READES 1

	}

} // END CDLL_LIST CLASS

class CDLL_Node<T>
{
  T data;
  CDLL_Node<T> prev, next; // EACH CDLL_Node PTS TO ITS PREV  & NEXT

  CDLL_Node()
  {
    this( null, null, null );  // 3 FIELDS TO INIT
  }

   CDLL_Node(T data)
  {
    this( data, null, null);
  }

   CDLL_Node(T data, CDLL_Node<T> prev, CDLL_Node<T> next)
  {
    this.data = data;
	  this.prev = prev;
    this.next = next;
  }

  public String toString()
  {
	  return ""+ this.data;
  }

} //EOF
