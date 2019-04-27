package thomzt01_CS161_Project3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Zachary Thomas
 * 11 Nov 18
 * Project 3
 * CS161
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class FaceGUI extends JFrame implements ActionListener {

	//fields 
	private ClockWork work;
	private Face face;
	private int radius;
	private int centerX;
	private int centerY;
	
	/**
	 * Initializes the data field work;
	 * registers this as the ActionListener with work; calls buildClock() 
	 */
	public FaceGUI (ClockWork cw) {
		this.work = cw;
		this.work.addActionListener(this);
		buildClock();
	}
	/**
	 * Makes the fundamental calls and parameter choices to build the GUI. 
	 * Initializes the circle data, the center is about the center of the frame, vertical adjustment may be desirable;
	 * the radius must be less than the vertical half size to give room for the numbers The method calls the setter in ClockWork to pass the circle data to that class;
	 * calls loadTacks of ClockWork; instantiates face 
	 */
	private void buildClock() {
		
		// this gui needs to be finished, so does the private inner class.
		this.centerX = 375;
		this.centerY = 500;
		this.radius =250;
		JPanel center = new JPanel();
		center.setBackground(Color.GREEN);
		getContentPane().add(center, BorderLayout.CENTER);
		face = new Face();
		this.setSize(750,500);
		this.setLocation(750,440);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		face.repaint();
	}
	
	private class Face extends JPanel{
		
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
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int paintLong = work.getLongArmIndex();
			int paintShort = work.getShortArmIndex();
			int paintSeconds = work.getSecondsArmIndex();
			int[][] firstCoordinates = work.getFirstCoordinates();
			int[][] secondCoordinates = work.getSecondCoordinates();
			
			
			//draws the three layers of circles
			g.setColor(Color.WHITE);
			g.drawArc(250,250,125,125,0,360);
			g.setColor(Color.CYAN);
			g.drawArc(250,250,120,120,0,360);
			g.setColor(Color.BLACK);
			g.drawArc(250,250,115,115,0,360);
			
			//creates the ticks all the way around the 
			String num;
			for (int i = 0; i < 60; i++) {
				for (int k = 0; k < 60; k++) {
					g.drawLine(firstCoordinates[i][0],firstCoordinates[0][k] , secondCoordinates[i][0], secondCoordinates[0][k]);
					if (divBy5(i)) {
						num = Integer.toString(i);
						g.drawString(num,secondCoordinates[i][0] , secondCoordinates[0][k]);
					}
				}
			}
			
			
			//set numbers to clock (needs finished)
			g.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
			
		}
	}
	
}
