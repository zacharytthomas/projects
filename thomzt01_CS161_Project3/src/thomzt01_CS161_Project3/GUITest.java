package thomzt01_CS161_Project3;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.awt.*;
public class GUITest  extends JFrame{
	public GUITest() {
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setHgap(10);
		getContentPane().setBackground(Color.BLACK);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.EAST);
		
		JSlider slider = new JSlider();
		slider.setValue(100);
		slider.setMaximum(1000);
		slider.setBackground(Color.CYAN);
		slider.setSnapToTicks(true);
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		panel.add(slider);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
	}

}
