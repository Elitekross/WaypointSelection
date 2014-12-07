package classes;

import javax.swing.JFrame;

public class WaypointViewer {

	/**
	 * @param args
	 * 
	 */
	
	static PaintGraph graph = new PaintGraph();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUI gui = new GUI();
		gui.setVisible(true);



		JFrame frame = new JFrame();
		frame.getContentPane().add(graph);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(601, 601);
		frame.setVisible(true);
	}
}