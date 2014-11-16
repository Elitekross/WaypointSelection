package classes;

import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PaintGraph extends JPanel {

static WaypointList ref;
	
public void paint(Graphics g) {
      int[] xs = {100,200,300,400,500,400,200};
      int[] ys = {333,422,54,64,74,54,34};
      
      for (int i = 0; i < ref.size(); i ++){
    	  xs[i] = (int) ref.get(i).lat * 50;
      }
      
      for (int i = 0; i < ref.size(); i ++){
    	  ys[i] = (int) ref.get(i).lng * 50;
      }
      
      g.drawPolyline(xs, ys, ref.size());

  
  }
//
//  public static void main(String[] args) {
//    JFrame frame = new JFrame();
//    frame.getContentPane().add(new PaintGraph());
//
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    frame.setSize(600,600);
//    frame.setVisible(true);
//  }
}