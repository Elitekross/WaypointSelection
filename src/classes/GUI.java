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

	private JLabel oneLabel, twoLabel, threeLabel;
	private JTextField oneText, twoText, threeText;
	private JButton calculateButton, exitButton;
	private static final int HEIGHT = 600;
	private static final int WIDTH = 300;

	private double option1, option2, option3;

	private CalculateButtonHandler calculateButtonHandler;
	private ExitButtonHandler exitButtonHandler;

	public GUI()

	{
		oneLabel = new JLabel("Option 1: ", SwingConstants.CENTER);
		twoLabel = new JLabel("Option 2: ", SwingConstants.CENTER);
		threeLabel = new JLabel("Option 3: ", SwingConstants.CENTER);
		oneText = new JTextField(12);
		twoText = new JTextField(12);
		threeText = new JTextField(12);

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
		pane.setLayout(new GridLayout(4, 2));

		// Grid layout requires that you add components to the content pane in
		// the order they should appear

		pane.add(oneLabel);
		pane.add(oneText);
		pane.add(twoLabel);
		pane.add(twoText);
		pane.add(threeLabel);
		pane.add(threeText);
		pane.add(calculateButton);
		pane.add(exitButton);

		oneText.setText("1-10");
		twoText.setText("1-10");
		threeText.setText("1-10");

		setSize(HEIGHT, WIDTH);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private class CalculateButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			option1 = Double.parseDouble(oneText.getText()) / 10;
			option2 = Double.parseDouble(twoText.getText()) / 10;
			option3 = Double.parseDouble(threeText.getText()) / 10;

			ProjectMain.setPoints();
			
			
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
