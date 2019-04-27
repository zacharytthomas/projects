package thomzt01_CS161_Project2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*
 * Zachary Thomas
 * Oct. 2018
 * Prof. Petruska
 * Project 2
 */
public class NameSelector extends JFrame implements ActionListener, ItemListener {
	//fields
	private String[] names = new String[12];
	private RaceOrganizer model;
	private JCheckBox[] boxes = new JCheckBox[12];
	private boolean selectionFinished = false;
	private JButton selectionCompleted;
	private JButton start;
	/**
	 * Constructor for the Name Selector Class
	 * @param names
	 * @param model
	 */
	public NameSelector(String[] names, RaceOrganizer model) {
		super("A Day at the Races");
		this.names = names;
		this.model = model;
		setSize(350,550);
		setLocation(750, 350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	/**
	 * getter for selectionFinished
	 * @return
	 */
	public boolean getSelectionFinished() {
		return this.selectionFinished;
	}
	/**
	 * Resets the selectionFinished field to false
	 */
	public void resetSelectionFinished(){
		this.selectionFinished = false;
	}
	/**
	 * setter for the model field
	 * @param mod
	 */
	public void setModel(RaceOrganizer model) {
		this.model = model;
	}
	/**
	 * Builds the window for name selection
	 */
	public void buildFrame() {
		//all components of the north panel
		JPanel northPanel = new JPanel();
		getContentPane().add(northPanel, BorderLayout.NORTH);
		JLabel lblSelectTheRacing = new JLabel("Select the racing field!");
		lblSelectTheRacing.setFont(new Font("Dialog", Font.BOLD, 24));
		northPanel.add(lblSelectTheRacing);
		//all components of the south panel
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.PINK);
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		selectionCompleted = new JButton("Selection Complete");
		southPanel.add(selectionCompleted);
		selectionCompleted.addActionListener(this);
		
		start = new JButton("Start!");
		southPanel.add(start);
		start.addActionListener(this);
		
		//center panel components
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.YELLOW);
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(12, 1, 0, 2));
		
		for(int k = 0; k<12 ; k++) {
            boxes[k] = new JCheckBox(names[k]);
            boxes[k].setBackground(Color.CYAN);
            boxes[k].addItemListener(this);
            boxes[k].setFont(new Font("Dialog", Font.BOLD, 24));
            centerPanel.add(boxes[k]);
        }
		setVisible(true);
		
	}
	/**
	 * Interface method, executes when one of the
check boxes is checked or unchecked

	 */
	@Override
	/**
	 * called when a JCheckBox is selected or deselected, checks the array of JCheckBoxes for the source of the selection
	 * adds or removes horses from the array relative to the call of selected or deselected.
	 */
	public void itemStateChanged(ItemEvent e) {
	for (int k = 0; k < 12; k++) {
        if(e.getSource() == boxes[k]) {
            if(boxes[k].isSelected()) {
            	model.getRunners().add(new Horse(names[k]));
            }else {
            for(int i = 0; i < model.getRunners().size(); i++) {
                if(model.getRunners().get(i).getName().equals(names[k])) {
                    model.getRunners().remove(i);
                    break;
                }
                }
            }
        }
    }
}
		// TODO Auto-generated method stub
		
	@Override
	/**
	 * called when an actionListener is called to listen.
	 * determines the source of the action call and directs the call accordingly
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == selectionCompleted) {
			for (int k = 0; k < model.getRunners().size(); k++) {
				System.out.println(model.getRunners().get(k).getName());
			}
			for (int i = 0; i<boxes.length;i++ ) {
					boxes[i].setEnabled(false);
			}
				selectionFinished=true;
		}else {
			selectionFinished=true;
			this.dispose();
		}
	}
}