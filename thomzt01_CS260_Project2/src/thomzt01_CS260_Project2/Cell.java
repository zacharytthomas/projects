package thomzt01_CS260_Project2;
/**
 * The cell class to be implemented, is a quadly-linked list, or a super node
 * it takes data and uses it to track the location of one such person inside a system.
 * @author Zachary Thomas
 *
 */
public class Cell {

	//fields
	public int row = 0;
	public int column = 0;
	public boolean visited = false;
	public Cell[] neighbor;
	public int lastOut = 0; //just a helper holder
	
	//constructor
	public Cell(int row, int col) {
		this.row = row;
		this.column = col;
		this.neighbor = new Cell[4];
	}
	
	
	/**
	 * Overridden toString method, prints the cell data
	 */
	@Override
	public String toString() {
		return "Cell [row=" + row + ", column=" + column + "]";
	}
	
}
