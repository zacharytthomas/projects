package thomzt01_CS161_Project2;
/*
 * Zachary Thomas
 * Oct. 2018
 * Prof. Petruska
 * Project 2
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PanelFactory extends JFrame {
	/*
	 * Panel factory fields
	 */
	private int dimension;
	private JPanel north = new JPanel();
	private JPanel south = new JPanel();
	private JPanel center = new JPanel();
	private JPanel east = new JPanel();
	private JPanel west = new JPanel();
	private ArrayList<JTextField> horses = new ArrayList<JTextField>();
	private Container pane = getContentPane();
	
	/**
	 * panelFactory constructor
	 * sets basic componets of a gui up
	 * @param model
	 */
	public PanelFactory(RaceOrganizer model) {
		super("A Day at the Races!");
		this.setLocation(500, 350);
		this.dimension = model.getRunners().size();
		this.setSize(860,dimension*30+370);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pane.setLayout((new BorderLayout(10,10)));
		
}
	/**
	 * Builds the north panel
	 */
public JPanel buildNorth() {
		pane.add(north, BorderLayout.NORTH);
		north.setLayout(new GridLayout(0, 3, 0, 0));
		JTextField nameBox = new JTextField("The Horse");
		nameBox.setBackground(Color.DARK_GRAY);
		nameBox.setForeground(Color.RED);
		nameBox.setHorizontalAlignment(SwingConstants.CENTER);
		nameBox.setEditable(false);
		north.add(nameBox);
		
		JTextField trackText = new JTextField("The Tracks");
		trackText.setHorizontalAlignment(SwingConstants.CENTER);
		trackText.setBackground(Color.DARK_GRAY);
		trackText.setForeground(Color.PINK);
		trackText.setEditable(false);
		trackText.setBounds(5,2,3,10);
		north.add(trackText);
		
		JTextField placeStrideBox = new JTextField("Place - Stride");
		placeStrideBox.setForeground(Color.YELLOW);
		placeStrideBox.setBackground(Color.DARK_GRAY);
		placeStrideBox.setHorizontalAlignment(SwingConstants.CENTER);
		placeStrideBox.setEditable(false);
		north.add(placeStrideBox);
		return north;
}
/**
 * builds the south panel
 * @return
 * returns the panel
 */
public JPanel buildSouth() {
	
		pane.add(south, BorderLayout.SOUTH);
		JLabel raceGifLabel = new JLabel("");
		raceGifLabel.setIcon(new ImageIcon(PanelFactory.class.getResource("/thomzt01_CS161_Project2/racehorseicon.gif")));
		south.add(raceGifLabel);
		return south;
		
}
/**
 * builds the west panel
 * @return
 * returns the panel
 */
public JPanel buildWest(RaceOrganizer RO) {
    pane.add(west, BorderLayout.WEST);
    west.setLayout(new GridLayout(RO.getRunners().size(), 1, 75, 15));
    for (int i = 0; i < RO.getRunners().size(); i++) {
        horses.add(new JTextField());
        horses.get(i).setText(RO.getRunners().get(i).getName());
        horses.get(i).setEditable(false);
        horses.get(i).setBackground(Color.CYAN);
        horses.get(i).setFont(new Font("DIALOG", Font.BOLD, 24));
        west.add(horses.get(i));
    }
    return west;

}

/**
 * builds the center panel
 * @param runXLines
 * @return
 * returns the center panel
 */
public JPanel buildCenter(JTextField[] runXLines) {
    pane.add(center, BorderLayout.CENTER);
    center.setLayout(new GridLayout(runXLines.length, 1, 65, 15));
    for (int i = 0; i < runXLines.length; i++) {
        JTextField temp = new JTextField();
        temp.setPreferredSize(new Dimension(100, 20));
        runXLines[i].setEditable(false);
        runXLines[i].setBackground(Color.GREEN);
        runXLines[i].setOpaque(true);
        center.add(runXLines[i]);
    }
    return center;
}

/**
 * Creates the east panel of the Race GUI, takes two JTextField[].
 * @param placement, stride
 * @return
 * returns the created panel
 */
public JPanel buildEast(JTextField[] placement, JTextField[] stride) {
	 pane.add(east, BorderLayout.EAST);
	    east.setLayout(new GridLayout(placement.length, 2, 5, 15));
	    for (int i = 0; i < placement.length; i++) {
	        JTextField temp = new JTextField();
	        temp.setPreferredSize(new Dimension(50, 60));
	        stride[i].setEditable(false);
	        placement[i].setEditable(false);
	        stride[i].setSize(100,100);
	        placement[i].setSize(100,100);
	        east.add(stride[i]);
	        east.add(placement[i]);

	    }
	    return east;
	}
}





