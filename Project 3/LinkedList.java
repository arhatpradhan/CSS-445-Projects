import java.io.*;
import java.util.*;

// NOTICE THE "<T extends Comparable<T>>"
// using <T extends Comparable<T>> in here means compiler wont let the code in main send in any T type
// that does not implement Comparable.  Now we do not have to cast the incoming key to a Comparable
// in our insertInOrder() method. Compiler now lets us call .compareTo off the dot on the incoming key
// without throwing an error.

public class LinkedList<T extends Comparable<T>>
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FROM INCOMING FILE
	@SuppressWarnings("unchecked")
	public LinkedList( String fileName, boolean orderedFlag )
	{	head = null;
		try
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{
				if (orderedFlag)
					insertInOrder( (T)infile.readLine() );  // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail( (T)infile.readLine() );  // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER PRESERVED
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
	}

	//-------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place

	public void insertAtFront(T data)
	{
		//creates new node head, puts data in the head, and sets last head
		//as the new next
		head = new Node<T>(data,head);
	}

	// we use toString as our print

	public String toString()
	{
		String toString = "";

		for (Node<T> curr = head; curr != null; curr = curr.next )
		{
			toString += curr.data;		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.next != null)
				toString += " ";
		}

		return toString;
	}

	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################


	public int size() // OF COURSE MORE EFFICIENT to KEEP COUNTER BUT YOU  MUST WRITE LOOP
	{
		// int count = 1;
		// // Node<T> curr = head;
		// // if(head == null){
		// // 	count = 0;
		// // 	return count;
		// // }
		// // while(curr.next != null){
		// // 		curr = curr.next;
		// // 		count++;
		// // }
		return 0;
	}

	public boolean empty()
	{
		if(size() == 0) return true;

		return false ;  // YOUR CODE HERE
	}

	public boolean contains( T key )
	{
		return (search(key) != null);  // YOUR CODE HERE
	}

	public Node<T> search( T key )
	{
		Node<T> curr = head;
		while(curr != null){
			if(curr.data.equals(key)){
				return curr;
			}
			curr = curr.next;
		}
		return curr; // YOUR CODE HERE
	}

	// TACK A NEW NODE (CABOOSE) ONTO THE END OF THE LIST
	public void insertAtTail(T data)
	{
		// YOUR CODE HERE
		//basecase
		if(size() == 0){
	 		insertAtFront(data);
			return;
		}

		Node<T> curr = head;

		while(curr.next != null){
			curr = curr.next;
		}
		Node<T> last = new Node<T>(data, null);
		curr.next = last;
	}

	// IF YOU DEFINE <T> at the top of this class as <T implements Comparable>
	// YOU DO NOT HAVE TO CAST TO COMPARABLE AND YOU DO NOT NEED TO SUPPRESS
	public void insertInOrder(T  data)
	{
		//base case 1
		if(head == null){
			 insertAtFront(data);
			 return;
		 }
		 //base case 2
		 //if the incoming data is less than the one already in
		 if(data.compareTo(head.data) < 0){
			 insertAtFront(data);
			 return;
		 }
		 	Node<T> curr = head;
			// Node<T> previous = null;
		 //base case 3
		 //^ the above case tells us that if data < head.data it would put it
		 //in the front, if that isn't true you can just put it in the back
		 if(head.next == null){
			 insertAtTail(data);
			 return;
		 }
		//curr stops at the last element that is still less than data
		//and inserts
		while(curr.next != null && data.compareTo(curr.next.data) > 0){
			// previous = curr;
			curr = curr.next;
		}
		Node<T> insert = new Node<T>(data, curr.next);
		curr.next = insert;
	}

	public boolean remove(T key)
	{
		//find previous node
		// Change the next of the previous node.

		//node for the current
		Node<T> curr = head;
		//node for the previous
		Node<T> previous = null;

		//if key is at head change head to the next node
		if(curr != null && curr.data.equals(key)){
			head = curr.next;
			return true;
		}
		//loop through the linked list to find key
		//set the previous to current and the current to the next
		while(curr != null && !curr.data.equals(key)){
			previous = curr;
			curr = curr.next;
		}
		//if no key just return false
		if(curr == null) return false;

		if(curr.data.equals(key)){
			//sets the previous node to point at the next node after the one
			//that was deleted
			previous.next = curr.next;
			return true;
		}
		return false; //  REPLACE WITH YOUR CODE
	}

	public boolean removeAtTail()	// RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if(head == null) return false;

		Node<T> curr = head;
		Node<T> previous = null;

		//while loop for setting curr as the last node
		while(curr.next != null){
			previous = curr;
			curr = curr.next;
		}
		if(previous == null) head = null;
		else previous.next = null;
		return true; // YOUR CODE HERE
	}

	public boolean removeAtFront() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if(head == null) return false;

		Node<T> curr = head;
		if(curr.next == null){
			head = null;
			return true;
		}
		if(curr.next != null){
				head = curr.next;
				return true;
		}
		return false; // YOUR CODE HERE
	}

	public LinkedList<T> union( LinkedList<T> other )
	{
		LinkedList<T> union = new LinkedList<T>();
		Node<T> curr = this.head;
		Node<T> curr2 = other.head;
		//looping through list1
		while(curr != null){
			//putting everything from list 1 into union
			union.insertInOrder(curr.data);
			curr = curr.next;
		}
		//looping through list 2
		while(curr2 != null){
			//if whatever item from list 2 is not in list 1 add that
			if(!union.contains(curr2.data)) union.insertInOrder(curr2.data);
			curr2 = curr2.next;
		}
		// YOUR CODE HERE

		return union;
	}
	public LinkedList<T> inter( LinkedList<T> other )
	{
		LinkedList<T> inter = new LinkedList<T>();
		Node<T> curr = this.head;
		while(curr != null){
		if(other.contains(curr.data)) inter.insertInOrder(curr.data);
		curr = curr.next;
	}
		// YOUR CODE HERE

		return inter;
	}
	public LinkedList<T> diff( LinkedList<T> other )
	{
		LinkedList<T> diff = new LinkedList<T>();
		Node<T> curr = this.head;

		while(curr != null){
			if(!other.contains(curr.data)) diff.insertInOrder(curr.data);
			curr = curr.next;
		}
		// YOUR CODE HERE

		return diff;
	}
	public LinkedList<T> xor( LinkedList<T> other )
	{
		return  this.union(other).diff(this.inter(other));  // REPLACE WITH YOUR CODE

	}

} //END LINKEDLIST CLASS

// A D D   N O D E   C L A S S  D O W N   H E R E
// R E M O V E  A L L  P U B L I C  &  P R I V A T E (except toString)
// R E M O V E  S E T T E R S  &  G E T T E R S
// M A K E  T O  S T R I N G  P U B L I C

 class Node<T extends Comparable<T>> // tells compiler our incoming T type implements Comparable

{
   T data;
   Node<T> next;

   Node()
  {
    this( null, null );
  }

   Node(T data)
  {
    this( data, null );
  }

   Node(T data, Node<T> next)
  {
    this.data = data;
    this.next = next;
  }

  public String toString()
  {
	  return ""+ this.data;
  }

} //EOF
