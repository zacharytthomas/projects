package thomzt01_CS260_Project1;

import java.util.*;

public class Sequence<T> implements Cloneable {
	//fields
	private ArrayList<T> list;
	private T current;
	
	//constructor
	public Sequence(){
		list = new ArrayList<T>();
	}
	
	//getters and setters
	public ArrayList<T> getList() {
		return list;
	}

	public void setList(ArrayList<T> list) {
		this.list = list;
	}

	public T getCurrent() {
		return current;
	}

	public void setCurrent(T current) {
		this.current = current;
	}
	
	//instance methods
	
	/**
	 * Takes a given sequence and data point and
	 * adds more data after said point
	 * @param data
	 * @return
	 */
	public T addAfter(T data) {
		
		ArrayList<T> temp = new ArrayList<T>();
		
		if(list.size() == 0) {
				this.list.add(data);
				this.current = data;
				return data;
			}
		
		for (int i = 0; i < list.size(); i++) {
			if(i == list.indexOf(current)) {	
				list.add((i + 1), data);
				break;
			}
			
		}
		
		current = data;
		return data;
	}

	
	/**
	 * Takes a given sequence and data point and
	 * adds more data before said point
	 * @param data
	 * @return
	 */
	public T addBefore(T data) {

        ArrayList<T> temp = new ArrayList<T>();
        
        for (T element: list) {
            if(list.indexOf(element) == (list.indexOf(current) - 1)) {
                temp.add(data);
            }
            temp.add(element);
        }
        this.list = temp;

        return data;
    }
	
	/**
	 * This method adjoins two lists, first this list then other list.
	 * @param other
	 * @return
	 */
	public Sequence<T> addAll(Sequence<T> other) {
		int index = other.list.size();
		if (other == null) {
			return null;
		}else {
			for (int i = 0; i < index; i++) {
				this.list.add(other.list.get(i));
			}
			return this;
		}
	}
		
	
	/**
	 * takes 2 parameter sequences and concatenates them to create a 3rd sequence.
	 * @param first
	 * @param second
	 * @return
	 */
	public static<T> Sequence<T> concatenate(Sequence<T> first, Sequence<T> second){
		if (first == null || second == null) {
			throw new NullPointerException();
		}
		Sequence<T> temp = new Sequence<T>();
		temp.addAll(first);
		
		temp.addAll(second);
		
		return temp;
		
	}
	
	/**
	 * Removes the current element if it exists, replaces it with the element right after 
	 * @return
	 */
	public Sequence<T> removeCurrent(){
		if(isCurrent()) {
			this.list.remove(list.indexOf(current));
			return this;
		}
		else {
			return null;
		}
	}
	
	/**
	 * advances current one step forward in the sequence
	 * @return
	 */
	public void advance() {
		if(isCurrent()){
            if(list.indexOf(current) == (list.size()-1)) {
                current = null;
            } else {
                current = list.get(list.indexOf(current) + 1);
            }
        } else {
            throw new IllegalStateException("There is no current element.");
        }
    }
	/**
	 * checks to see if current exists, if it does true
	 * if not false
	 * @return
	 */
	public boolean isCurrent() {
		return (current != null);
	}
	
	/**
	 * Starts the sequence walking
	 */
	public void start() {
		if (this == null) {
			//do nothing
		}else {
			this.current = list.get(0);
		}
		
	}
	/**
	 * returns a copy of the sequence this method was called on
	 */
	@SuppressWarnings("unchecked")
	public Sequence<T> clone(){
		Sequence<T> temp;
		try {
			temp = (Sequence<T>) super.clone();
			temp.current = current;
		}catch ( CloneNotSupportedException exc){
			throw new RuntimeException("Couldn't be cloned.");
		}
		return temp;
	}
	
	/**
	 * Calls size on list, and returns the size
	 * @return
	 */
	public int size() {
		return list.size();
	}
	
	/**
	 * utility method to display the data in the sequence.
	 */
	public void displaySequence() {
		for(T element: list) {
			System.out.println(element.toString() + "\n");
		}
	}
}

/*Sequence ADT Invariant.
 * The invariants of this class are the fields, Current & List. Current is a generic variable, and list is a generic ArrayList object. Current holds the current data of the item on the ArrayList List. 
 * With that information, as well as ArrayList method indexOf(Object O) you can manage the data quite easily. I did not define an int to hold current index as the method did that for me.
 * because I had access to the current index at the call of a method. When this method returns null, it indicates that the list is actually empty.*/
