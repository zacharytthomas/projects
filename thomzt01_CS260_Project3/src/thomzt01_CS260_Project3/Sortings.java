package thomzt01_CS260_Project3;

public class Sortings {
	
	/**
	 * Utility method to add sort an array of ints
	 * @param data (an array of ints)
	 * @param first (first element to sort from
	 */
	public static void insertionSort(int[] data, int first) {
		//TODO fix this
		if(data == null || data.length == 0) {
			System.out.println("That is one empty array.");
			return;
		}
		
		if(data.length == 2) {
			if(data[0] > data[1]) {
				int temp = data[0];
				data[0] = data[1];
				data[1] = temp;
				return;
			}
		}
		
		int i = 0, j = 0, entry = 0;
		
		for (i = 1; i < data.length; i++) {
			entry = data[first + i];
			for(j = first + i; ((j > first) && (data[j-1] > entry)); j--) {
				data[j] = data[j-1];
			}
			data[j] = entry;
		}
		
	}
	
	/**
	 * Overloaded utility method that sorts and inserts a node into a linked list.
	 * @param head
	 * @return
	 */
	public static<E> Node<E> insertionSort(Node<E> head) {
		//TODO fix this
		if(head == null || head.link == null) {
			return head;
		}
		if(getLength(head) == 2) {
			if (((int) head.data > (int) head.link.data)){
				E temp = head.data;
				head.data = head.link.data;
				head.link.data = temp;
			}
		}
		E current, temp;
		Node<E> cursor = head;
		while(cursor.link != null) {
			current =  cursor.data;
			temp =  cursor.link.data;
				if((int) current > (int) temp) {
					cursor.data = (E) temp;
					cursor.link.data = current;
				}
			cursor = cursor.link;
		}
		return head;
	}
	/**
	 * Utility sorting method that follows the merge sorting pattern
	 * accepts an array of ints, an initial point and an ending point
	 * @param data
	 * @param first
	 * @param n
	 */
	public static void mergeSort(int[] data, int first, int n) {
		//TODO fix thiso.
		if(data == null || data.length == 0) {
			System.out.println("That is one empty list.");
			return;
		}
		
		if(data.length == 1) {
			System.out.println("Data is sorted already as array length is 1");
			return;
		}
		
		if(data.length == 2) {
			if(data[0] > data[1]) {
				int temp = data[0];
				data[0] = data[1];
				data[1] = temp;
				return;
			}else {
				return;
			}
		}
		int n1; // Size of the first half of the array
		int n2; // Size of the second half of the array
		
		if(n > 1) {
			// Compute sizes of the two halves: 
			n1 = n / 2; 
			n2 = n - n1;
			
			mergeSort(data, first, n1);
			mergeSort(data, (first + n1), n2);
			
			merge(data, first, n1, n2);
		}

	}
	
	/**
	 * Overloaded mergeSort method that takes a node and sorts as necessary
	 * @param head
	 * @return
	 */
	public static<E> Node<E> mergeSort(Node<E> head) {
		if(head == null || head.link == null) {
			return head;
		}
		int middle = getLength(head)/2 ; 
		Node<E> oldHead = head;
		
		while (middle - 1 > 0) {
			oldHead = oldHead.link;
			middle--;
		}
		
		Node<E> newHead = oldHead.link;
		
		oldHead.link = null;
		oldHead = head;
		
		Node<E> t1 = mergeSort(oldHead);
		Node<E> t2 = mergeSort(newHead);
		return mergeLists(t1,t2);
	}
	
	/**
	 * Sorts the data in the param array based on location in the heap.
	 * @param data
	 */
	public static void heapSort(int[] data) {
		if(data == null || data.length == 0) {
			System.out.println("That is one empty list.");
			return;
		}
		int unsorted; // Size of the unsorted part of the array 
		int temp; // Used during the swapping of two array locations
		makeHeap(data, data.length);
		unsorted = data.length;
		while (unsorted > 1) { 
			unsorted--;
		// Swap the largest element (data[0]) with the final element of unsorted part 
			temp = data[0]; 
			data[0] = data[unsorted]; 
			data[unsorted] = temp;
			reheapifyDown(data, unsorted);
		}

	}
	/**
	 * Finds the length of a linked list
	 * @param head
	 * @return
	 */
	private static<E> int getLength(Node<E> head){
		if(head == null) {
			return 0;
		}
		int count = 0;
		Node<E> cursor = head;
		while(cursor != null) {
			count++;
			cursor = cursor.link;
		}
		return count;
	}
	
	/**
	 * Merges 2 linked lists (sorted)
	 * @param t1
	 * @param t2
	 * @return
	 */
	private static<E> Node<E> mergeLists(Node<E> t1, Node<E> t2){
		if(t1.counter < 20)
			return t1;
		Node<E> result = null;
		if(t1 == null) {
			return t2;
		}
		if(t2 == null) {
			return t1;
		}
		
		if((int)t1.data > (int)t2.data) {
			result = t2;
			result.link = mergeLists(t1, t2.link);
		}else {
			result = t1;
			result.link = mergeLists(t1.link, t2);
		}
		
		return result;
	}
	
	/**
	 * merges given elements of an array sorted
	 * @param data
	 * @param first
	 * @param n1
	 * @param n2
	 */
	private static void merge(int[] data, int first, int n1, int n2) {
		int[ ] temp = new int[n1+n2]; // Allocate the temporary array
		int copied = 0; // Number of elements copied from data to temp 
		int copied1 = 0; // Number copied from the first half of data 
		int copied2 = 0; // Number copied from the second half of data 
		int i; // Array index to copy from temp back into data
		
		while((copied1 < n1) && (copied2 < n2)) {
			if(data[first+copied1] >= data[first+n1+copied2]) {
				temp[copied++] = data[first + n1 + (copied1++)];
			}else {
				temp[copied++] = data[first + n1 + (copied2++)];
			}
		}
		
		// Copy any remaining entries in the left subarray (but not from the right subarray, 
		// because those entries are already in the correct spot of the data array). 
		while (copied1 < n1) {
			temp[copied++] = data[first + (copied1++)];// Copy from temp back to the data array. 
		}
			
		for (i = 0; i < copied; i++) 
			data[first + i] = temp[i];
	}
	
	/**
	 * Makes a heap that is not balanced yet.
	 * @param data
	 * @param n
	 */
	private static void makeHeap(int[] data, int n) {
		int i, k;
		
		for(i = 1; i < n; i++) {
			k = i;
			while(data[k] != data[0] && data[k] > data[parent(k)]) {
				int temp = data[k];
				data[k] = data[parent(k)];
				data[parent(k)] = temp;
				k = parent(k);
			}
		}
	}
	
	/**
	 * Minimum order heapification occurs here and causes the heap to become one step more sorted.
	 * @param data
	 * @param n
	 */
	private static void reheapifyDown(int[] data, int n) {
		// Reheapification downward (for a heap where the root is out of place) 
		int current; // Index of the node that’s moving down 
		int bigChildIndex = 0; // Index of current’s larger child 
		boolean heapOkay; // Will become true when heap is correct
		current = 0; 
		heapOkay = false;
		
		if(n == 1) {
			return;
		}
		
		while ((!heapOkay) && (leftChild(current) < n)) { 
			//Set bigChildIndex to be the index of the larger child of the current node. (If there is only one child, then bigChildIndex will be set to the index of this one child.)
			if(data[leftChild(current)] > data[rightChild(current)]) {
				bigChildIndex = leftChild(current);
			}else {
				bigChildIndex = rightChild(current);
			}
			
			if (data[current] < data[bigChildIndex]) { 
				int temp = data[current];
				data[current] = data[bigChildIndex];
				data[bigChildIndex] = temp;
				current = bigChildIndex; 
			} else {
				heapOkay = true;
			}
		}
	}

	
	/**
	 * Returns the parent element of k
	 * @param k
	 * @return
	 */
	private static int parent(int k) {
		return (k-1) / 2;
	}
	/**
	 * Returns left child of element k
	 * @param k
	 * @return
	 */
	private static int leftChild(int k) {
		return (2*k + 1);
	}
	/**
	 * Returns right chiild of element k
	 * @param k
	 * @return
	 */
	private static int rightChild(int k) {
		return (2*k + 2);
	}
}
