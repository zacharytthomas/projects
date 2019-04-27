package thomzt01_CS260_Project2;
/**
 * GyP, Khalifa
 * 23 Mar 2019
 * Project 2
 * @author zachary Thomas
 *
 */
public class Applications {

	public static void main(String[] args) {
		Cell[][] random = MazeBuilder.loadMazeRandom();
		Maze randomMaze = new Maze(random);
		randomMaze.findPath();
		
		Cell[][] file = MazeBuilder.loadMazeFromFiles("eastWest.txt", "northSouth.txt");
		Maze fileMaze = new Maze(file);
		fileMaze.findPath();
	}

}

/* For the directed maze, in the testing section of this project (all cases tested 10 times) :
 * For the base case of (p = .5, r = 5. c = 5), the search failed 7 times.
 * For case (p = .8, r = 5, c = 5), the search failed: 2 times.
 * For case (p = .8, r = 20, c = 20), the search failed: 3 times.
 * For case ("    ", r = 25, c = 25), the search failed: 3 times.
 * For case ("    ", r = 30, c = 30), the search failed: 1 time.
 * For case ("    ", r = 50, c = 50), the search failed: 1 time.
 * For case ("    ", r = 505, c = 505), no data, stack overflow.
 * For case ("    ", r = 100, c = 100), the search failed: 1 time.
 * For case ("    ", r = 200, c = 200), the search failed: 1 time.
 * For case ("    ", r = 300, c = 300), the search completes but data cannot print.
 * 
 */
