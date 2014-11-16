package classes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PaintGraph extends JPanel {

static WaypointList ref;
static WaypointList ref2;
	
public void paint(Graphics g) {
	
	//For Long Path
      int[] xs = {100,200,300,400,500,400,200};
      int[] ys = {333,422,54,64,74,54,34};
      
      for (int i = 0; i < ref.size(); i ++){
    	  xs[i] = (int) ref.get(i).lat * 50;
      }
      
      for (int i = 0; i < ref.size(); i ++){
    	  ys[i] = (int) ref.get(i).lng * 50;
      }
      
      g.setColor(Color.RED);
      g.drawPolyline(xs, ys, ref.size());
      
     
    //For Short Path
  int[] xs2 = {100,200,300,400,500,400,200};
     int[] ys2 = {333,422,54,64,74,54,34};
      
      for (int i = 0; i < ref2.size(); i ++){
    	  xs2[i] = (int) ref2.get(i).lat * 50;
      }
      
      for (int i = 0; i < ref2.size(); i ++){
    	  ys2[i] = (int) ref2.get(i).lng * 50;
      }
      
      
      g.setColor(Color.BLUE);
      g.drawPolyline(xs2, ys2, ref2.size());
      
      
     


  
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