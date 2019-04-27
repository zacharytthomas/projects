package thomzt01_CS260_Project1;
/**
 * Zachary Thomas
 * 13.02.19
 * CS260
 * GyP
 * @author Zachary Thomas
 *
 */

import java.util.*;
public class Applications {
	//the following will soley be used to test all componenets of the sequence class.
	public static void main(String[] args) {
		Sequence<Integer> series = new Sequence<Integer>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		series.setList(list);
		
		System.out.println("Size of series: " + series.size()
							+ ". Current: " + series.getCurrent());
		
		Random rng = new Random();
		for(int i = 0; i < 5 ; i++) {
			series.addAfter(rng.nextInt(101));
			series.setCurrent(list.get(i));
			System.out.println("Size of series: " + series.size()
					+ ". Current: " + series.getCurrent());
		}
		
		System.out.println("Printing current Sequence: (displaySequence() called)");
		series.displaySequence();
		
		System.out.println("Setting current to: list.get(1)");
		series.setCurrent(list.get(1));
		series.addAfter(rng.nextInt(101));
		System.out.println("Size of series: " + series.size()
		+ ". Current: " + series.getCurrent());
		
		series.displaySequence();
		
		System.out.println("Added Element: " + series.addBefore(rng.nextInt(101)) + "\n" 
				+ "Size of series: " + series.size());
		
		System.out.println("Added Element: " + series.addBefore(rng.nextInt(101)) + "\n" 
				+ "Size of series: " + series.size());
		
		series.displaySequence();
		
		System.out.println("Setting current to: 6");
		series.setCurrent(list.get(5));
		
		System.out.println("Removed Element: " + series.getList().get(5) + "\n" 
				+ "Size of series: " + series.size());
		series.removeCurrent();
		
		Sequence<Integer> clone = series.clone();
		
		clone.displaySequence();
		
		System.out.println("Checking if clone equals series: " + clone.equals(series));
		System.out.println("Checking if clone.list.get(0) equals series.list.get(0): " + (clone.getList().get(0).equals(series.getList().get(0))));
		
		System.out.println("Testing the concatenate methods:");
		Sequence<Integer> temp = series;
		Sequence<Integer> concTemp = Sequence.concatenate(temp, clone);
		
		System.out.println("Sequences combined (displaying concTemp)");
		concTemp.displaySequence();
		
		Sequence<Integer> sequenceForAA = new Sequence<Integer>();
		Sequence<Integer> oSequenceForAA = new Sequence<Integer>();
		
		for(int i = 0; i < 5; i++) {
			sequenceForAA.addAfter(rng.nextInt(101));
			oSequenceForAA.addAfter(rng.nextInt(101));
		}
		
		System.out.println("Add All Method testing: (randomly generated sequence)");
		sequenceForAA.addAll(oSequenceForAA);
		
		sequenceForAA.displaySequence();
		
		System.out.println("Testing with the book's for loop");
		for (series.start( ); series.isCurrent( ); series.advance( )) 
			System.out.println(series.getCurrent( ) + "\n");
	}
}
