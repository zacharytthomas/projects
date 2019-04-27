package thomzt01_CS260_Project2;

import java.util.*;
/**
 * GyP, Khalifa
 * 23 Mar 2019
 * Project 2
 * @author zachary Thomas
 *
 */
public class Stack<T> implements Cloneable{
	//fields 
	private Node<T> top;
	
	public Stack(){
		this.top = null;
	}
	
	

//	stack when the method returns; otherwise pop the stack, save the popped value
//	in a local variable, call display( ) , print the saved value
	
	/**
	 * checks if the given stack is empty
	 * @return true or false depending on the top node being null or not
	 */
	public boolean isEmpty() {
		return (top == null);
	}
	/**
	 * @return top's data
	 */
	public T peek() {
		return top.data;
	}
	/**
	 * Makes the top of the stack the next thing in the stack, basically a delete
	 * @return
	 */
	public T pop() {
		T result;
		if (top == null) {
			throw new EmptyStackException();
		}
		result = top.data;
		top = top.link;
		return result;
	}
	/**
	 * Makes a new node at the top of the list and pushes the other down.
	 * @param item
	 */
	public void push (T item) {
		top = new Node<T>(item,top);
	}
	/**
	 * Traverses the stack and counts the items in it
	 * @return size of stack
	 */
	public int size() {
		Node<?> cursor = this.top;
		int counter = 0;
		while (cursor != null) {
			cursor = cursor.link;
			counter++;
		}
		return counter;
	}
	
	/**
	 * Displays the content of the stack bottom to top.
	 */
	public void display() {
		if(this.isEmpty()) {
			System.out.printf("");
			return;
		}
		
		T data = this.pop();
		this.display();
		System.out.println(data);
	}
	
	
	@SuppressWarnings("hiding")
	private class Node<T>{
		//fields
		private T data;
		private Node<T> link;
		
		//constructor
		public Node(T data, Node<T> link) {
			this.data = data;
			this.link = link;
		}
		
	}
}
