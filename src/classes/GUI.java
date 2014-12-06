package classes;

import java.awt.Container;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI extends JFrame

{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel oneLabel, twoLabel, threeLabel, fourLabel;
	private JTextField oneText, twoText, threeText, fourText;
	private JButton calculateButton, exitButton;
	private static final int HEIGHT = 600;
	private static final int WIDTH = 300;

	private double option1, option2, option3, option4;
	
	

	private CalculateButtonHandler calculateButtonHandler;
	private ExitButtonHandler exitButtonHandler;

	public GUI()

	{
		oneLabel = new JLabel("X1: ", SwingConstants.CENTER);
		twoLabel = new JLabel("Y1: ", SwingConstants.CENTER);
		threeLabel = new JLabel("X2: ", SwingConstants.CENTER);
		fourLabel = new JLabel("Y2: ", SwingConstants.CENTER);
		oneText = new JTextField(12);
		twoText = new JTextField(12);
		threeText = new JTextField(12);
		fourText = new JTextField(12);

		// This section specifies the handlers for the buttons and adds an
		// ActionListener.

		calculateButton = new JButton("Generate");
		calculateButtonHandler = new CalculateButtonHandler();
		calculateButton.addActionListener(calculateButtonHandler);
		exitButton = new JButton("Close");
		exitButtonHandler = new ExitButtonHandler();
		exitButton.addActionListener(exitButtonHandler);

		setTitle("MappLife");
		Container pane = getContentPane();
		pane.setLayout(new GridLayout(5, 2));

		// Grid layout requires that you add components to the content pane in
		// the order they should appear

		pane.add(oneLabel);
		pane.add(oneText);
		pane.add(twoLabel);
		pane.add(twoText);
		pane.add(threeLabel);
		pane.add(threeText);
		pane.add(fourLabel);
		pane.add(fourText);
		pane.add(calculateButton);
		pane.add(exitButton);



		setSize(HEIGHT, WIDTH);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	private class CalculateButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			option1 = Double.parseDouble(oneText.getText());
			option2 = Double.parseDouble(twoText.getText());
			option3 = Double.parseDouble(threeText.getText());
			option4 = Double.parseDouble(fourText.getText());

			ProjectMain.setPoints(option1, option2, option3, option4);
			
			
		}

	}

	public class ExitButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}


	public double get1() {
		return option1;
	}

	public double get2() {
		return option2;
	}

	public double get3() {
		return option3;
	}

}
