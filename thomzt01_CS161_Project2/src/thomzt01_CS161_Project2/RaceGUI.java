package thomzt01_CS161_Project2;
import javax.management.modelmbean.ModelMBean;
import javax.swing.*;
/*
 * Zachary Thomas
 * Oct. 2018
 * Prof. Petruska
 * Project 2
 */
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RaceGUI extends JFrame implements ActionListener {
	//Fields for the RaceGUI class
	private RaceOrganizer model;
	private int dimension;
	private JTextField[] strides;
	private JTextField[] placement;	
	private JTextField[] runXTrax;
	private Font font = new Font("Dialog", Font.BOLD,24);
	
	/**
	 * Constructor for the RaceGUI class,
	 * creates the race GUI and assigns values to all the fields
	 * fills field arrays with objects
	 * 
	 * @param model
	 * @param title
	 */
	public RaceGUI(RaceOrganizer model, String title) {
        super(title);
        setModel(model);
        PanelFactory PF = new PanelFactory(model);
        this.dimension = model.getRunners().size();
        this.runXTrax = new JTextField[dimension];
        this.placement = new JTextField[dimension];
        this.strides = new JTextField[dimension];
        this.setSize(860, this.dimension*30+370);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10,10));
        for (int i =0;i<dimension;i++)
        {
            runXTrax[i]=new JTextField(dimension);
            placement[i]= new JTextField(dimension);
            strides[i]= new JTextField(dimension);
        }
        PF.buildNorth();
        PF.buildWest(model);
        PF.buildCenter(runXTrax);
        PF.buildEast(placement,strides);
        PF.buildSouth();
        PF.setVisible(true);
    }
/**
 * setter for the model field
 * @param model
 */
	public void setModel(RaceOrganizer model) {
		this.model=model;
		model.addActionListener(this);
		
	}

	@Override
	/**
	 * this method activates when the runRace method is called in the RaceOrganizer class
	 * it is connected via the connect() method
	 * @param e
	 */
	public void actionPerformed(ActionEvent e) {
	        ArrayList<Horse> temp = model.getRunners();
	        int index;
	        for (Horse horses : temp) {
	            index = temp.indexOf(horses);
	            runXTrax[index].setText(horses.getRunX());
		        runXTrax[index].setFont(font);
	            placement[index].setText(Integer.toString(horses.getPlacement()));
	            placement[index].setFont(font);
	            strides[index].setText(Integer.toString(horses.getStrides()));
	            strides[index].setFont(font);
	        }
	}
}
