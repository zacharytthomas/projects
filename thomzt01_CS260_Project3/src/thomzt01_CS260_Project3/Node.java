package thomzt01_CS260_Project3;

public class Node<E> {
	public Node<E> link;
	public E data;
	public int counter;
	
	/**
	 * Constructor for node obj
	 * @param data
	 * @param link
	 */
	public Node(E data, Node<E> link) {
		this.data = data;
		this.link = link;
		this.counter = 0;
	}
	
	/**
	 * Recursive toString method that prints the data of the given node. As well prints the link till null while the counter is less than 20 (0 start) 
	 */
	public String toString() {
		
		if(this.counter <= 20) {
			counter++;
			return "";
		}
		String fromLink = null, fromData = null;
		
		if (this.link != null) {
			fromLink = link.toString();
		}
		
		if(this.data != null) {
			fromData = data.toString();
		}
		
		String concat = String.format("\nData: %s\n" + "Link: %s", fromData, fromLink);
		return concat;
	}
	
	/**
	 * Finds the bottom of a linked list
	 * @param head
	 * @return
	 */
	public static<E> Node<E> findBottom(Node<E> head){
		Node<E> cursor = head;
		while(cursor.link != null) {
			cursor = cursor.link;
		}
		
		return cursor;
	}
}
