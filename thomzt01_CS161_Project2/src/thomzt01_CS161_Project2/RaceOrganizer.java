package thomzt01_CS161_Project2;
import java.awt.event.*;
/*
 * Zachary Thomas
 * Oct. 2018
 * Prof. Petruska
 * Project 2
 */
import java.util.*;
public class RaceOrganizer {
	//fields for the RaceOrganizer Class
public int length;
public int delay;
public ArrayList<Horse> runners = new ArrayList<Horse>();
public ActionListener listener;
public ActionEvent event;
public double FORWARD_STRIDE=0.56;


/**
 * Constructor for the RaceOrganizer. 
 * Initializes the data fields length and delay
 *Instantiates and initializes the data field event:
 *@param length
 *@param delay
 */

public RaceOrganizer (int length, int delay) {
	this.length=length;
	this.delay=delay;
	event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "stride");
}
/**
 * Getter for the field length.
 * @return
 * returns length
 */
public int getLength() {
	return length;
}
/**
 * Setter for the field length.
 * @param length
 */
public void setLength(int length) {
	this.length = length;
}
/**
 *Getter for the field delay.
 * @return
 * Returns value for delay.
 */
public int getDelay() {
	return delay;
}
/**
 * Setter for delay.
 * @param delay
 */
public void setDelay(int delay) {
	this.delay = delay;
}
/**
 * Getter for the ActionListener.
 * @return
 * returns the listener
 */
public ActionListener getListener() {
	return listener;
}
/**
 * Getter for the ArrayList<Horse> runners.
 * @return
 * returns the array list runners.
 */
public ArrayList<Horse> getRunners() {
	return runners;
}
/**
 * Setter for the size of the ArrayList<Horse> runners.
 * @param d
 */
public void setRunnerSize(int d) {
	this.runners= new ArrayList<Horse>(d);
}
/**
 * Setter (or adder?) for actionListener class.
 * @param listener
 */
public void addActionListener(ActionListener listener) {
	this.listener = listener;
}
/**
 * connects RaceGUI to this class.
 */
public void connect() {
	listener.actionPerformed(event);
	
}
/**
 * Resets the data fields position, strides, placement, finished
 * to 0, 0, 0, false for every horse in the list runners 
 */
public void reset() {
	for(int i = 0; i<runners.size();i++) {
	runners.get(i).setPosition(0);
	runners.get(i).setPlacement(0);
	runners.get(i).setStrides(0);
	runners.get(i).setFinished(false);
	}
}
/**
 * onHold method is a standard delay loop, only delays for (param value) milliseconds, but it works.
 * @param delay
 */
public void onHold(int delay) {
	long now = System.currentTimeMillis();
	long stop = now + delay;
	while(System.currentTimeMillis()<stop) {
		System.out.println("");
	}
}
/**
 * resets the fields and begins the race
 * sets elements for the for of the field arrays, and loads them with text loops until all horses have finished
 */
public void runRace() {
    reset();
    int counter = 0;
    int place = 1;
    int direction = 0;
    int numberOfFinished = 0;

    while (numberOfFinished < runners.size()) {

        for (int i = 0; i < runners.size(); i++) {

            if (runners.get(i).getPosition() < length) {
                Random RNG = new Random();
                double chance = RNG.nextDouble();
                if (chance <= FORWARD_STRIDE) {
                    direction = 1;

                } else {
                    direction = -1;
                }
                runners.get(i).updatePosition(direction);
                runners.get(i).updateRunX();
                runners.get(i).setStrides(counter);
            } else if(runners.get(i).isFinished() == false) {

                runners.get(i).setFinished(true);
                runners.get(i).setPlacement(place);
                runners.get(i).setStrides(counter);
                numberOfFinished++;
                place++;
            }

        }
        counter++;
        onHold(delay);
        connect();
    }
}

}

