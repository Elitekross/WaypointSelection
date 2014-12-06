package classes;

import javax.swing.JFrame;

public class ProjectMain {

	/**
	 * @param args
	 */
	// Version 0.3
	static WaypointList local = new WaypointList();

	public static void setPoints(double a, double b, double c, double d) {


		local = new WaypointList("Random", 30);
		WaypointList route = createRoute(local, 2, new Waypoint(a, b, 0, 0),
				new Waypoint(c, d, 0, 1));
		System.out.println("Distance: " + route.lengthAlong());
		System.out.println("Weighted Distance: " + route.lengthAlong(true));
		WaypointList route2 = new WaypointList();
		// make deep copy or else yuck
		for (int i = 0; i < route.size(); i++) {
			route2.add(i, route.get(i));
		}
		PaintGraph.ref = route2; // long route

		route.display();

		System.out.println();
		shortenRoute(route);
		System.out.println("Distance: " + route.lengthAlong());
		System.out.println("Weighted Distance: " + route.lengthAlong(true));
		PaintGraph.ref2 = route; // short route
		route.display();

		JFrame frame = new JFrame();
		frame.getContentPane().add(new PaintGraph());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(601, 601);
		frame.setVisible(true);

		

	}

	public static WaypointList createRoute(WaypointList field, int type,
			Waypoint start, Waypoint end) {
		WaypointList result = new WaypointList();
		WaypointList LocalType = field.pullType(type);
		LocalType.subtract(start);
		LocalType.subtract(end);
		LocalType.add(end);
		result.add(start);
		boolean routeFinished = false;
		while (!routeFinished) {
			result.add(LocalType.getNearest(result.get(result.size() - 1), true));
			if (result.get(result.size() - 1) == end) {
				routeFinished = true;
			}
			if (LocalType.size() == 0) {
				break;
			}
		}

		return result;
	}

	public static WaypointList createRouteThrough(WaypointList field, int type,
			Waypoint start, Waypoint end, Waypoint... list) {
		WaypointList LocalType = field.pullType(type);
		LocalType.subtract(start);
		LocalType.subtract(end);
		for (Waypoint each : list) {
			LocalType.subtract(each);
		}
		WaypointList result = createRoute(LocalType, type, start, list[0]);
		result.remove(result.size() - 1);
		for (int i = 1; i < list.length; i++) {
			result.add(createRoute(LocalType, type, list[i - 1], list[i]));
			result.remove(result.size() - 1);
		}
		result.add(createRoute(LocalType, type, list[list.length - 1], end));
		return result;
	}

	public static WaypointList createShortRouteThrough(WaypointList field,
			int type, Waypoint start, Waypoint end, Waypoint... list) {
		WaypointList LocalType = field.pullType(type);
		LocalType.subtract(start);
		LocalType.subtract(end);
		for (Waypoint each : list) {
			LocalType.subtract(each);
		}
		WaypointList result = createRoute(LocalType, type, start, list[0]);
		result.remove(result.size() - 1);
		shortenRoute(result);
		for (int i = 1; i < list.length; i++) {
			WaypointList cycle = createRoute(LocalType, type, list[i - 1],
					list[i]);
			shortenRoute(cycle);
			result.add(cycle);
			result.remove(result.size() - 1);
		}
		WaypointList cycle = createRoute(LocalType, type,
				list[list.length - 1], end);
		shortenRoute(cycle);
		result.add(cycle);
		return result;
	}

	public static void shortenRoute(WaypointList input) {
		for (int i = 2; i < input.size(); i++) {
			double distance1 = Waypoint
					.distance(input.get(i - 2), input.get(i));
			distance1 = distance1 / input.get(i).weight;
			double distance2 = Waypoint.distance(input.get(i - 2),
					input.get(i - 1));
			distance2 = distance2 / input.get(i - 1).weight;
			double distance3 = Waypoint
					.distance(input.get(i - 1), input.get(i));
			distance3 = distance3 / input.get(i).weight;
			if (distance1 < (distance2 + distance3)) {
				int index = input.indexOf(input.get(i - 1));
				input.remove(index);
			}
		}
	}

	public static void shortenRouteThrough(WaypointList input, Waypoint... list) {
		WaypointList result = new WaypointList();
		result.add(input.sublist(0, input.indexOf(list[0])));
		shortenRoute(result);
		for (int i = 1; i < list.length; i++) {
			WaypointList route = new WaypointList();
			int index1 = input.indexOf(list[i - 1]);
			route = input.sublist(index1, input.size() - 1);
			int index2 = route.indexOf(list[i]);
			route = route.sublist(0, index2);
			shortenRoute(route);
			result.add(route);
		}
		WaypointList route = new WaypointList();
		int index1 = input.indexOf(list[list.length - 1]);
		route = input.sublist(index1, input.size() - 1);
		shortenRoute(route);
		result.add(route);
		input = result;
	}

}
