package classes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

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

		for (int i = 0; i < ProjectMain.field.size(); i++) {

			for (int j = 0; j < ProjectMain.field.get(i).neighbors.size(); j++) {
				g.setColor(Color.GRAY);
				g.drawLine((int) ProjectMain.field.get(i).lat,
						(int) ProjectMain.field.get(i).lng,
						(int) ProjectMain.field.get(i).neighbors.get(j).lat,
						(int) ProjectMain.field.get(i).neighbors.get(j).lng);

			}

			if (ProjectMain.field.get(i).type == 0)
				g.setColor(Color.PINK);
			if (ProjectMain.field.get(i).type == 1)
				g.setColor(Color.CYAN);
			if (ProjectMain.field.get(i).type == 2)
				g.setColor(Color.ORANGE);
			if (ProjectMain.field.get(i).type == 3)
				g.setColor(Color.GREEN);

			g.fillOval((int)ProjectMain.field.get(i).lat - (int)(8 * ProjectMain.field.get(i).weight) - 3, (int)ProjectMain.field.get(i).lng - (int)(8 * ProjectMain.field.get(i).weight) - 3, (int)(16 * ProjectMain.field.get(i).weight) + 6, (int)(16 * ProjectMain.field.get(i).weight) + 6);
		}

		
		
		if (ref != null) {
		
			// For Long Path

			int[] xs = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
			int[] ys = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
			int[] ws = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };

			for (int i = 0; i < ref.size(); i++) {
				xs[i] = (int) ref.get(i).lat;
			}

			for (int i = 0; i < ref.size(); i++) {
				ys[i] = (int) ref.get(i).lng;
			}

			for (int i = 0; i < ref.size(); i++) {
				ws[i] = (int) ref.get(i).weight;
			}

			g.setColor(Color.BLACK);
			g.fillOval((int)ref2.get(0).lat - (int)(8*ref2.get(0).weight) - 3, (int)ref2.get(0).lng - (int)(8*ref2.get(0).weight) - 3, (int)(16*ref2.get(0).weight) + 6,
					(int)(16*ref2.get(0).weight) + 6);
			g.setColor(Color.WHITE);
			g.fillOval((int)ref2.get(ref2.size() - 1).lat - (int)(8*ref2.get(ref2.size() - 1).weight) - 3,
					(int)ref2.get(ref2.size() - 1).lng - (int)(8*ref2.get(ref2.size() - 1).weight) - 3, (int)(16*ref2.get(ref2.size() - 1).weight) + 6,
					(int)(16*ref2.get(ref2.size() - 1).weight) + 6);
			g.setColor(Color.GREEN);
			for (int i = 1; i < ref.size() - 1; i++) {
				// g.fillOval(xs[i] - (ws[i] / 2), ys[i] - (ws[i] / 2), ws[i],
				// ws[i]);
			}
			g2d.setStroke(new BasicStroke(3));
			g.setColor(Color.RED);
			g.drawPolyline(xs, ys, ref.size());

			// For Short Path
			int[] xs2 = { 100, 200, 300, 400, 500, 400, 200, 8, 9, 10 };
			int[] ys2 = { 333, 422, 54, 64, 74, 54, 34, 8, 9, 10 };
			int[] ws2 = { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 };

			for (int i = 0; i < ref2.size(); i++) {
				xs2[i] = (int) ref2.get(i).lat;
			}

			for (int i = 0; i < ref2.size(); i++) {
				ys2[i] = (int) ref2.get(i).lng;
			}

			for (int i = 0; i < ref2.size(); i++) {
				ws[i] = (int) ref2.get(i).weight * 20;
			}

			g.setColor(Color.ORANGE);
			for (int i = 1; i < ref2.size() - 1; i++) {
				// g.fillOval(xs2[i] - (ws2[i] / 2), ys2[i] - (ws2[i] / 2),
				// ws2[i], ws2[i]);
			}
			
			

			g.setColor(Color.BLUE);
			g.drawPolyline(xs2, ys2, ref2.size());
			
			g2d.dispose();
		}
	}

}