package thomzt01_CS161_Project3;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.awt.*;

/*
 * Zachary Thomas
 * 11 Nov 18
 * Project 3
 * CS161
 */

public class ControlDevicess extends JFrame implements ActionListener, ChangeListener{

	//fields
	private ClockWork work;
	
	private JTextField inp = new JTextField();
	private JTextField inpMessage = new JTextField("Enter time, use format 15:42");
	private JLabel slideLabel = new JLabel("Select tick time");
	private JLabel runLabel = new JLabel("Click to start the clock");
	private JLabel stopLabel = new JLabel("Click to stop the clock");
	private JLabel resetLabel = new JLabel("Click to reset the clock");
	private JButton run = new JButton("Run the Clock");
	private JButton stop = new JButton("Stop the Clock");
	private JButton reset = new JButton("Reset the Clock");
	private JButton set = new JButton("Set the Clock");
	private JSlider timeSlide = new JSlider();
	private Container pane = getContentPane();
	
	//constructor
	public ControlDevicess(ClockWork cw) {
		super("Control of the Clockwork");
		this.work = cw;
		makeSlider();
		buildControlGUI();
		this.setVisible(true);
		this.setSize(600, 500);
		this.setLocation(250, 250);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
	}
	
	//instance methods
	
	/**
	 * creates the finer bits of the slider that would be messy to look at in the building of the GUI
	 */
	public void makeSlider() {
		timeSlide.setPaintLabels(true);
	    timeSlide.setPaintTicks(true);
	    timeSlide.setMajorTickSpacing(100);
	    timeSlide.setOrientation(JSlider.VERTICAL);
	    timeSlide.setMinimum(0);
	    timeSlide.setMaximum(1000);
	    timeSlide.setMinorTickSpacing(10);
	    timeSlide.setBackground(Color.cyan);
	    timeSlide.addChangeListener(this);
	    timeSlide.setSize(getSize());
	}
	/**
	 * Constructs the support gui
	 */
	public void buildControlGUI() {
		
		pane.setBackground(Color.black);
		pane.setLayout(new BorderLayout(10,10));
		
		//action listeners
		
		run.addActionListener(this);
		stop.addActionListener(this);
		reset.addActionListener(this);
		set.addActionListener(this);
		
		//center bois
		JPanel center = new JPanel();
		JPanel runBox = new JPanel();
		JPanel stopBox = new JPanel();
		JPanel resetBox = new JPanel();
		
		runBox.add(run);
		runBox.add(runLabel);
		center.add(runBox);
		
		stopBox.add(stop);
		stopBox.add(stopLabel);
		center.add(stopBox);
		
		resetBox.add(reset);
		resetBox.add(resetLabel);
		center.add(resetBox);
		
		// subSouth panel in center
		JPanel subSouth = new JPanel();
		subSouth.setBackground(Color.PINK);
//		subSouth.setLayout(new GridLayout(2,1));
		inpMessage.setBackground(Color.PINK);
		inpMessage.setEditable(false);
		inpMessage.setBorder(BorderFactory.createLineBorder(Color.black)); 
		inp.setBorder(BorderFactory.createLineBorder(Color.black));
//		inp.setPreferredSize();
		subSouth.add(inpMessage);
		subSouth.add(inp);
		subSouth.add(set);
		
		center.add(subSouth, BorderLayout.SOUTH);
		pane.add(center, BorderLayout.CENTER);
		
		
		//east bois
		JPanel east = new JPanel();
		JPanel subEast = new JPanel();
		subEast.add(timeSlide);
		subEast.add(slideLabel);
		east.add(subEast);
		pane.add(east, BorderLayout.EAST);
		
	}
	/**
	 * Handles the buttons clicks on the GUI functions accordingly
	 * 
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == stop) {
			work.stop();
			run.setEnabled(true);
		}else if(e.getSource() == run) {
			run.setEnabled(false);
			work.makeTimer();
		}else if(e.getSource() == reset) {
			run.setEnabled(true);
			work.reset();
		}else if(e.getSource() == set) {
			work.setClock(inp.getText());
			inp.setText("");
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		work.setTickTime(timeSlide.getValue());
	}

}
