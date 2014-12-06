package classes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PaintGraph extends JPanel {

	static WaypointList ref;
	static WaypointList ref2;

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.GRAY);

		g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_BEVEL, 0, new float[] { 1, 2 }, 0));

		for (int i = 50; i <= 550; i += 50) {
			g2d.drawLine(i, 0, i, getSize().height);
		}

		for (int i = 50; i <= 550; i += 50) {
			g2d.drawLine(0, i, getSize().width, i);
		}

		g2d.setStroke(new BasicStroke(1.5f));
		// for (int i = 0; i <= getSize().width; i += 300)
		// {
		// g2.drawLine(i, 0, i, getSize().height);
		// }
		//
		// for (int i = 0; i <= getSize().height; i += 300)
		// {
		// g2.drawLine(0,i, getSize().width, i);
		// }

		// For Long Path
		int[] xs = { 100, 200, 300, 400, 500, 400, 200 };
		int[] ys = { 333, 422, 54, 64, 74, 54, 34 };
		int[] ws = { 15, 9, 12, 6, 18, 13, 5 };

		for (int i = 0; i < ref.size(); i++) {
			xs[i] = (int) ref.get(i).lat * 50;
		}

		for (int i = 0; i < ref.size(); i++) {
			ys[i] = (int) ref.get(i).lng * 50;
		}

		// for (int i = 0; i < ref.size(); i++) {
		// ws[i] = (int) ref.get(i).weight * 13;
		// }

		g.setColor(Color.BLACK);
		g.fillOval(xs[0] - (ws[0] / 2), ys[0] - (ws[0] / 2), ws[0], ws[0]);
		g.setColor(Color.WHITE);
		g.fillOval(xs[ref.size() - 1] - (ws[ref.size() - 1] / 2),
				ys[ref.size() - 1] - (ws[ref.size() - 1] / 2),
				ws[ref.size() - 1], ws[ref.size() - 1]);
		g.setColor(Color.YELLOW);
		for (int i = 1; i < ref.size() - 1; i++) {
			g.fillOval(xs[i] - (ws[i] / 2), ys[i] - (ws[i] / 2), ws[i], ws[i]);
		}
		g.setColor(Color.RED);
		g.drawPolyline(xs, ys, ref.size());

		// For Short Path
		int[] xs2 = { 100, 200, 300, 400, 500, 400, 200 };
		int[] ys2 = { 333, 422, 54, 64, 74, 54, 34 };
		int[] ws2 = { 10, 9, 12, 6, 18, 13, 5 };

		for (int i = 0; i < ref2.size(); i++) {
			xs2[i] = (int) ref2.get(i).lat * 50;
		}

		for (int i = 0; i < ref2.size(); i++) {
			ys2[i] = (int) ref2.get(i).lng * 50;
		}

		// for (int i = 0; i < ref2.size(); i ++){
		// ws2[i] = (int) ref2.get(i).weight*13;
		// }

		g.setColor(Color.ORANGE);
		for (int i = 1; i < ref2.size() - 1; i++) {
			g.fillOval(xs2[i] - (ws2[i] / 2), ys2[i] - (ws2[i] / 2), ws2[i],
					ws2[i]);
		}
		g.setColor(Color.BLUE);
		g.drawPolyline(xs2, ys2, ref2.size());

		// for (int i = 0; i < ref2.size(); i ++){
		// for (int j = 0; j<ref.size(); j ++){
		// if (xs[j] == xs2[i] && ys[j] == ys2[i]){
		// g.setColor(Color.MAGENTA);
		// g.fillOval(xs2[i]-3, ys2[i]-3, 8, 8);
		// }
		// }
		// }

		// PutImageOnJFrame c = new PutImageOnJFrame();
		//
		// this.add(c);
		// Image a = Toolkit.getDefaultToolkit().getImage("yosemite.jpg");
		// g.drawImage(a,0,0,getSize().width,getSize().height, this);
		// super.paint(g);

	}
	
//	 public static void main(String[] args) {
//	 JFrame frame = new JFrame();
//	 frame.getContentPane().add(new PaintGraph());
//	
//	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	 frame.setSize(600,600);
//	 frame.setVisible(true);
//	 }
}