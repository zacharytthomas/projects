package thomzt01_CS260_Project2;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
 * GyP, Khalifa
 * 23 Mar 2019
 * Project 2
 * @author Zachary Thomas
 *
 */
public class MazeBuilder {

	/**
	 * loadRandomMaze solicits number information from the user, uses try catch to verify input, instantiates a grid of cells to later be returned,
	 *  assigns the cells inside of the grid neighbor cells (randomly generated).
	 * 
	 * @return the grid
	 */
	public static Cell[][] loadMazeRandom(){
		
		double p = 0.0;
		int r = 0, c = 0;
		try {
			p = Double.parseDouble(JOptionPane.showInputDialog("Enter Passage Probability (between 0 and 1): "));
			r = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount of rows in the maze: (Must be greater than 0 and positive)"));
			c = Integer.parseInt(JOptionPane.showInputDialog("Enter the amount of columns in the maze: (Must be greater than 0 and positive)"));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "You failed to follow instrucitons! Restart the program.");
			System.exit(-1);
		}
		Cell[][] grid = new Cell[r][c];
		
		for (int i = 0; i < r; ++i) {
			for (int j = 0; j < c; ++j) {
				//all cells must exist before they can be a neighbor
				grid[i][j] = new Cell(i , j);	
			}
		}
		
	for(int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			if(i == 0) {
				grid[i][j].neighbor[0] = null;
			} 
			if(j == 0) {
				grid[i][j].neighbor[3] = null;
			} 
			if(i == (r - 1)) {
				grid[i][j].neighbor[2] = null;
			} 
			if(j == (c - 1)) {
				grid[i][j].neighbor[1] = null;
			}
			for (int k = 0; k < 4; k++) {
				if (k == 0 && (i - 1) >= 0)
					grid[i][j].neighbor[k] = (Math.random() < p) ? grid[i-1][j]: null;
				if (k == 1 && (j + 1) < c)
					grid[i][j].neighbor[k] = (Math.random() < p) ? grid[i][j+1]: null;
				if (k == 2 && (i + 1) < r)
					grid[i][j].neighbor[k] = (Math.random() < p) ? grid[i+1][j]: null;
				if (k == 3 && (j - 1) >= 0) 
					grid[i][j].neighbor[k] = (Math.random() < p) ? grid[i][j-1]: null;
			}
		}
	}
	//handles corner cases
	grid[0][0].neighbor[2] = grid[1][0];
	grid[0][c-1].neighbor[2] = grid[1][c-1];
	grid[r-1][0].neighbor[0] = grid[r-2][0];
	grid[r-1][c-1].neighbor[0] = grid[r-2][c-1];

		
		return grid;
	}
	
	/**
	 * Creates two file objects with the given string params, and passes those files to two scanners, ensures the files exist in a try catch system.
	 * Creates a grid from determined by the first two numbers in the first files and then assigns neighbor values depending on the placement of
	 * a 0 or 1 in the files scanned. 0 is pass-able, 1 is not.
	 * @param file
	 * @param file2
	 * @return
	 */
	public static Cell[][] loadMazeFromFiles(String file, String file2){
		
		Cell[][] grid;
		try {
			int r,c;
			File f = new File(file);
			File f1 = new File(file2);
			Scanner fSc1 = new Scanner(f);
			Scanner fSc2 = new Scanner(f1);
			
			r = fSc1.nextInt();
			c = fSc1.nextInt();
			
			grid = new Cell[r][c];
			
			for (int i = 0; i < r; i++) {
				for(int j = 0; j < c; j++) {
					grid[i][j] = new Cell(i ,j);
				}
			}	
			
			for(int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if(fSc1.hasNextInt()) {
						if(fSc1.nextInt() == 0) {
							grid[i][j].neighbor[1] = grid[i][j+1];
						}else {
							grid[i][j].neighbor[1] = null;
						}
					}		
					
					if(fSc2.hasNextInt()) {
						if(fSc2.nextInt() == 0) {
							grid[i][j].neighbor[2] = grid[i+1][j];
						} else {
							grid[i][j].neighbor[2] = grid[i+1][j];
						}
							
					}
				}
			}
			
			
			fSc1.close();
			fSc2.close();
		}catch(FileNotFoundException e) {
			System.out.println("File not found, generating a random maze.");
			return grid = MazeBuilder.loadMazeRandom();
		}
		
		
		return grid;
	}
}
