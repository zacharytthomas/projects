package thomzt01_CS260_Project3;

import java.util.Random;

public class Test {
	static int[] data;
	static Node<Integer> head;
	static int TOP = 12000000;
	
	public static void main(String[] args) {
		System.out.println("Testing sort functions: ");
		for (int i = 0; i < 3; i++) {
			randomData(i);
			testSort(data, head);
		}
		System.out.println("Testing on 10: ");
		randomData(10);
		
		head.counter = 0;
		testSort(data, head);
		
		runTests(20);
		runTests(5000);
		runTests(10000);
		runTests(20000);
		runTests(40000);
		runTests(80000);
		runTests(160000);
		runTests(320000);
		runTests(640000);
		runTests(1280000);
		runTests(2560000);
		runTests(5120000);
		runTests(10240000);
		
		
		
	}
	
	/**
	 * helper method to compute runtime and test all sorting algorithms 
	 * @param n
	 */
	public static void runTests(int n) {
		long before = System.currentTimeMillis();
		System.out.printf("\nTesting on %d:\n" , n);
		randomData(n);
		head.counter = 0;
		testSort(data,head);
		long after = System.currentTimeMillis();
		long total = after - before;
		System.out.println("Total run time in miliseconds: " + total);
	}
	public static void randomData(int length) {
		if (length == 0) {
			return;
		}
		data = new int[length];
		Random rng = new Random();
		int currentData;
		for(int i = 0; i < length; i++) {
			
			currentData = rng.nextInt(TOP);
			data[i] = currentData;
			if(head == null) {
				head = new Node<Integer>(currentData, null);
			}else {
				Node.findBottom(head).link = new Node<Integer>(currentData, null);
			}
			
		}
		
	}
		
	/**
	 * Helper method that calls all the tests and prints for the given params
	 * @param data
	 * @param head
	 */
	public static void testSort(int[] data, Node<Integer> head) {
		System.out.println("Heap Sorting:");
		Sortings.heapSort(data);
		displayArray(data);
				
		System.out.println("Insertion sorting: (Linked List then Array)");
		assignZeroes(head);
		Sortings.insertionSort(head);
		displayList(head);
		
		Sortings.insertionSort(data, 0);
		displayArray(data);
		
		System.out.println("Merge Sorting: (Linked List then Array)");
		assignZeroes(head);
		Sortings.mergeSort(head);
		displayList(head);
		
		if(data != null) {
			Sortings.mergeSort(data, 0, data.length);
			displayArray(data);
		}else {
			System.out.println("Merge Sort Failed");
		}
		
	}
	
	/**
	 * loops through linked list and assigns the counter field to 0
	 * @param head
	 */
	public static void assignZeroes(Node<Integer> head) {
		Node<Integer> cursor = head;
		while(cursor != null) {
			head.counter = 0;
			cursor = cursor.link;
		}
	}
	
	/**
	 * Displays list contents
	 * @param head
	 */
	public static void displayList(Node<Integer> head) {
		if (head == null) {
			System.out.println("Empty List");
			return;
		}
		
		System.out.println(head.toString());
	}
	
	/**
	 * displays array content
	 * @param data
	 */
	public static void displayArray(int[] data) {
		if(data == null) {
			System.out.println("No data");
			return;
		}
		if(data.length >= 200) {
			int printLength = data.length/50;
			for(int i = 0; i < data.length;) {
				System.out.println("Data [" + i + "]\t" + data[i]);
				i += printLength;
			}
		}else {
			for(int i = 0; i < data.length; i++) {
					System.out.println("Data [" + i + "]\t" + data[i]);	
			}
		}
	}
	
}
