public class Node<T extends Comparable<T>> // tells compiler our incoming T type implements Comparable

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
