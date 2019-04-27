package thomzt01_CS260_Project2;
/**
 * GyP, Khalifa
 * 23 Mar 2019
 * Project 2
 * @author zachary Thomas
 *
 */

import javax.swing.*;
public class Maze {
	
	//fields
	public Cell entrance;
	public Cell exit;
	public Cell[][] grid;
	public int rows;
	public int columns;
	public boolean pathFound = false;	
	/*
	 * constructor; initializes the grid array
	 * assigns grid dimensions
	 * assigns entrance and exit
	 */
	public Maze(Cell[][] grid) {
		this.grid = grid;				
		this.rows = grid.length;
		this.columns = grid[0].length;
		this.entrance = grid[0][0];
		this.exit = grid[rows - 1][columns - 1];
	}

	/**
	 * implements the search (see the pseudo-code);
	 * depending on pathFound creates output messages;
	 * Traverses the grid by checking if the neighbors of the original cell (0,0) and their neighbors from there.
	 * Creates an easy to print stack.
	 * 
	 */
	public void findPath() {
		
		int i;
		//TODO: finish this
		Stack<Cell> stack = new Stack<Cell>();
		stack.push(entrance);
		entrance.visited = true;
		Cell top = null;
		while(!stack.isEmpty() && !(this.pathFound)) {
			top = stack.peek();
			if(top == exit) {
				pathFound = true;
				JOptionPane.showMessageDialog(null, "The maze has a traversing path. \n"
									+ "Cell coordinates are listed on the console.");
				System.out.printf("The length of the path is: %d \nCells on the traversing path:\n", stack.size());
				stack.display();
				return;
			}
			for(i = 0; i < top.neighbor.length; i++) {
				
				if(top.neighbor[i] == null || top.neighbor[i].visited) {
					continue;
				} else {
					top.neighbor[i].visited = true;
					top = top.neighbor[i];
					top.lastOut = i;
					stack.push(top);
					break;
				}
				
			}
			if(i == top.neighbor.length) {
				stack.pop();
			}
			
		}
		
		if(!this.pathFound) {
			JOptionPane.showMessageDialog(null, "No traversing path was found.");
		}
		
	}
	
}
