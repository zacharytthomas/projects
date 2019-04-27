package thomzt01_CS161_Project2;
/*
 * Zachary Thomas
 * Oct. 2018
 * Prof. Petruska
 * Project 2
 */
// This class represents the properties of a single race horse
public class Horse {
	//name of Horse
public String Name;
//curent position of horse down the field
public int position;
//placement the horse achieved post game
public int placement;
//false while horse is running, true when done
public boolean finished;
//keep counting the number of strides the horse made on the track
public int strides;
//text displayed on the track of the horse.
public String runX = "";


/**
 * Constructor for the horse, requires name, initializes it
 */
public Horse (String name) {
	this.Name=name;
}

/**
 * Getter for Name field
 * @return
 */
public String getName() {
	return Name;
}

/**
 * setter for name
 * @param name
 */
public void setName(String name) {
	Name = name;
}

/**
 * getter for position
 * @return
 */
public int getPosition() {
	return position;
}

/**
 * setter for position
 * @param position
 */
public void setPosition(int position) {
	this.position = position;
}

/**
 * getter for placement
 * @return
 */
public int getPlacement() {
	return placement;
}

/**
 * setter for placement
 * @param placement
 */
public  void setPlacement(int placement) {
	this.placement = placement;
}

/**
 * getter for finished boolean
 * @return
 */
public boolean isFinished() {
	return finished;
}

/**
 * setter for finished
 * @param finished
 */
public void setFinished(boolean finished) {
	this.finished = finished;
}

/**
 * getter for strides
 * @return
 */
public int getStrides() {
	return strides;
}

/**
 * setter for strides
 * @param strides
 */
public void setStrides(int strides) {
	this.strides = strides;
}

/**
 * getter for runX
 * @return
 */
public String getRunX() {
	return runX;
}

//instance methods
/**
 * Updates the current position by adding num; negative
updated position must be replaced by 0;
 */

public void updatePosition(int num) {
	 this.position = Math.max(position, 0) + num;
}
/**
 * Runs a for loop to build runX such that it contains
position many ‘X’ characters
 */
public void updateRunX() {
	this.runX="";
	for (int i = 0; i < this.getPosition(); i++) {
		this.runX +=("X");
	}
}
/**
 * Setter for RunX field.
 * @param runX
 */
public void setRunX(String runX) {
	this.runX = runX;
}
}
