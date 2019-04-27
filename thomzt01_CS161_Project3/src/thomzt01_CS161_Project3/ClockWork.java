package thomzt01_CS161_Project3;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Zachary Thomas
 * 11 Nov 18
 * Project 3
 * CS161
 */
public class ClockWork{
	//Clock Param Fields
	private int longArmIndex;
	private int shortArmIndex;
	private int secondsArmIndex;
	
	private int radius;
	private int centerX;
	private int centerY;
	
	private int[][] x1y1 = new int[60][60];
	private int[][] x2y2 = new int[60][60];
	
	private int tickTime;
	
	//functional and communication fields
	private Timer timer;
	private ActionListener listener;
	private ActionEvent event;
	
	//constructor
	public ClockWork() {
		this.event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "tick");
	}
	
	//instance methods
	
	//getters and setters, to be cleaned up and optimized
	
	public int[][] getFirstCoordinates() {
		return x1y1;
	}
	
	public int[][] getSecondCoordinates(){
		return x2y2;
	}
	
	
	public int getLongArmIndex() {
		return longArmIndex;
	}

	public void setLongArmIndex(int longArmIndex) {
		this.longArmIndex = longArmIndex;
	}

	public int getShortArmIndex() {
		return shortArmIndex;
	}

	public void setShortArmIndex(int shortArmIndex) {
		this.shortArmIndex = shortArmIndex;
	}

	public int getSecondsArmIndex() {
		return secondsArmIndex;
	}

	public void setSecondsArmIndex(int secondsArmIndex) {
		this.secondsArmIndex = secondsArmIndex;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setTickTime(int tick) {
		try {
		 timer.restart();
		}catch(NullPointerException n) {
			String error = n.getMessage();
			System.out.println("This slider has the big gay.");
		}
		finally {
			this.tickTime = tick;
		}
	}
	public void addActionListener(ActionListener watch) {
		watch = listener;
	}
	
	/**
	 * Boolean instant method to check if a number is divisible by 5 up to 60
	 */
	private boolean divBy5(int num) {
		int temp = num/5;
		boolean result = false;
		for (int i = 1; i < 13 ; i++) {
			if (temp == i) {
				result = true; 
				break;
			}
		}
		return result;
	}
	
	/**
	 * Runs a for loop to populate the four end-point coordinate-arrays for the tacks;
	 *  the points are on circles, Math.cos and Math.sin needed for the calculations
	 */
	public void loadTacks() {
		for(int i = 0; i<60; i++) {
			x1y1[i][0] = (int) (centerX + radius*Math.sin(2*i*Math.PI/60));
			x1y1[0][i] = (int) (centerY - radius*Math.cos(2*i*Math.PI/60)); 
			if (divBy5(i)) {
				x2y2[i][0] = (int) (centerX + (radius + 15) *Math.sin(2*i*Math.PI/60));
				x2y2[0][i] = (int) (centerY - (radius + 15) *Math.cos(2*i*Math.PI/60)); 
			}else {
				x2y2[i][0] = (int) (centerX + (radius + 10) *Math.sin(2*i*Math.PI/60));
				x2y2[0][i] = (int) (centerY - (radius + 10) *Math.cos(2*i*Math.PI/60)); 
			}
		}
	}
	
	/**
	 * Instantiates  a new Timer object for timer; constructor parameters are tickTime for delay and a new TimerListener 
	 * (an anonymous object from the inner class, see below); the method calls the start( ) method of the Timer class
	 */
	public void makeTimer() {
		this.timer = new Timer(tickTime, new TimerListener());
		this.timer.start();
		
	}
	
	/**
	 * calls the stop( ) method of the Timer class 
	 */
	public void stop() {
		this.timer.stop();
	}
	
	/**
	 *Stops the timer; resets the arm index values to 0; calls connect( )  
	 */
	public void reset() {
		this.timer.stop();
		setLongArmIndex(0);
		setShortArmIndex(0);
		setSecondsArmIndex(0);
	}
	/**
	 * private helper method;
	 *  validates inp if it is correct to set the time as required on the control GUI 
	 * @param inp
	 * @return
	 */
	private boolean timeFormat(String inp) {
		if (inp.matches("\\d{2}:\\d{2}")){
			return true;
		}else {
		return false;
		}
	}
	/**
	 * Calls timeFormat( ) to validate timeToSet; if not valid, the method returns;
	 * if valid, assigns shortArmIndex and longArmIndex according to the input and secondsArmIndex to 0;
	 * calls connect( ) 
	 * @param timeToSet
	 */
	public void setClock(String timeToSet) {
		//if true
		if (timeFormat(timeToSet)) {
			String[] temp = timeToSet.split(";");
			shortArmIndex = Integer.parseInt(temp[0]);
			longArmIndex = Integer.parseInt(temp[1]);
			secondsArmIndex = 0;
			connect();
		}else {
			System.out.println("Error Occured");
		}
		
	}
	/**
	 * Private helper; calls actionPerformed( ) with respect to listener and event in the parameter 
	 */
	private void connect() {
		listener.actionPerformed(event);
	}
	
		private class TimerListener implements ActionListener{
			public TimerListener() {
				
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				secondsArmIndex = secondsArmIndex % 60;
				if (secondsArmIndex == 0) {
					longArmIndex = (longArmIndex + 1) % 60;
				}
				if (divBy12(longArmIndex)) {
					shortArmIndex = (shortArmIndex + 1) % 60;
				}
				connect();
			}
			
			private boolean divBy12(int num) {
				int temp = num/12;
				boolean result = false;
				for (int i = 1; i < 6 ; i++) {
					if (temp == i) {
						result = true; 
						break;
					}
				}
				return result;
			}
			
		}
}
