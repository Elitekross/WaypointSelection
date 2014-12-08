package classes;

import java.util.ArrayList;

public class ProjectMain {

	/**
	 * @param args
	 */
	// Version 0.3

	static ConnectedWaypoint one = new ConnectedWaypoint(1, 1, 1, .5);
	static ConnectedWaypoint two = new ConnectedWaypoint(1, 2, 2, .25);
	static ConnectedWaypoint three = new ConnectedWaypoint(2, 1, 0, 1);
	static ConnectedWaypoint four = new ConnectedWaypoint(1.75, 1.25, 1, .6);
	static ConnectedWaypoint five = new ConnectedWaypoint(2, 2, 2, .35);
	static ConnectedWaypoint six = new ConnectedWaypoint(3, 1.5, 1, .5);
	static ConnectedWaypoint seven = new ConnectedWaypoint(2.75, 2.75, 3, .8);
	static ConnectedWaypoint eight = new ConnectedWaypoint(3.5, 2.25, 1, .7);
	static ConnectedWaypoint nine = new ConnectedWaypoint(4, 4, 1, .5);
	static ConnectedWaypoint ten = new ConnectedWaypoint(2, 4, 2, .1);
	
	
	static Graph field = new Graph();
	
	public static void setPoints(double a, double b, double c, double d, int type) {
		
		ConnectedWaypoint start = new ConnectedWaypoint();
		start.lat = a;
		start.lng = b;
		ConnectedWaypoint end = new ConnectedWaypoint();
		end.lat = c;
		end.lng = d;

		for (int i = 0; i < field.size(); i++){
			field.get(i).display();
			if (field.get(i).equals(start)){
				start = field.get(i);
			}
			if (field.get(i).equals(end)){
				end = field.get(i);
			}
		}
		System.out.println();
		start.display();
		System.out.println();
		end.display();
		
		
		PaintGraph.ref = completeLongRoute(field, type, start, end);
		System.out.println("Distance: " + PaintGraph.ref.lengthAlong());
		System.out.println("Weighted Distance: " + PaintGraph.ref.lengthAlong(true));
		PaintGraph.ref.display();
		PaintGraph.ref2 = completeRoute(field, type, start, end);
		System.out.println("Distance: " + PaintGraph.ref2.lengthAlong());
		System.out.println("Weighted Distance: " + PaintGraph.ref2.lengthAlong(true));
		PaintGraph.ref2.display();

		WaypointViewer.graph.revalidate();
		WaypointViewer.graph.repaint();
	}

	public static void init(){
		one = new ConnectedWaypoint(100, 100, 1, .5);
		two = new ConnectedWaypoint(100, 200, 2, .25);
		three = new ConnectedWaypoint(200, 100, 0, 1);
		four = new ConnectedWaypoint(175, 125, 1, .6);
		five = new ConnectedWaypoint(200, 200, 2, .35);
		six = new ConnectedWaypoint(300, 150, 1, .5);
		seven = new ConnectedWaypoint(275, 275, 3, .8);
		eight = new ConnectedWaypoint(350, 225, 1, .7);
		nine = new ConnectedWaypoint(400, 400, 1, .5);
		ten = new ConnectedWaypoint(200, 400, 2, .1);
		
		
		one.addNeighbor(two);
		two.addNeighbor(one);
		one.addNeighbor(three);
		three.addNeighbor(one);
		two.addNeighbor(four);
		four.addNeighbor(two);
		two.addNeighbor(ten);
		ten.addNeighbor(two);
		three.addNeighbor(four);
		four.addNeighbor(three);
		three.addNeighbor(six);
		six.addNeighbor(three);
		four.addNeighbor(five);
		five.addNeighbor(four);
		five.addNeighbor(six);
		six.addNeighbor(five);
		five.addNeighbor(seven);
		seven.addNeighbor(five);
		five.addNeighbor(eight);
		eight.addNeighbor(five);
		seven.addNeighbor(nine);
		nine.addNeighbor(seven);
		eight.addNeighbor(nine);
		nine.addNeighbor(eight);
		nine.addNeighbor(ten);
		ten.addNeighbor(nine);
		
		field.add(one);
		field.add(two);
		field.add(three);
		field.add(four);
		field.add(five);
		field.add(six);
		field.add(seven);
		field.add(eight);
		field.add(nine);
		field.add(ten);
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
	public static ArrayList<ConnectedWaypoint> createRoute(Graph field, int type,
			ConnectedWaypoint start, ConnectedWaypoint end){
		Graph typed = field.clone().typedGraph(type);
		for (int i = 0; i < typed.size(); i++){
			if(typed.get(i).equals(start) || typed.get(i).equals(end)){
				if(typed.get(i).equals(start)){
					typed.get(i).visited = true;
				}
				typed.get(i).type = type;
			}
		}
		ArrayList<ConnectedWaypoint> cycle = new ArrayList<ConnectedWaypoint>();
		boolean routeFinished = false;
		cycle.add(start);
		while (!routeFinished){
			cycle.add(typed.getNearestTyped(cycle.get(cycle.size() - 1), type));
			if (cycle.get(cycle.size() - 1).equals(end)) {
				routeFinished = true;
			}
			if (typed.typedSize(type) == 0) {
				break;
			}
		}
		return cycle;
	}
	public static ArrayList<ConnectedWaypoint> shortenRoute(Graph field, ArrayList<ConnectedWaypoint> route){
		//ArrayList<ConnectedWaypoint> cycle = new ArrayList<ConnectedWaypoint>();
		for(int i = 2; i < route.size(); i ++){
			double distance1 = field.dijkstras(route.get(i - 2), route.get(i - 1));
			distance1 = distance1 / route.get(i - 1).weight;
			double distance2 = field.dijkstras(route.get(i - 1), route.get(i));
			distance2 = distance2 / route.get(i).weight;
			double distance3 = field.dijkstras(route.get(i - 2), route.get(i));
			distance3 = distance3 / route.get(i).weight;
			if (distance1 < (distance2 + distance3)) {
				int index = route.indexOf(route.get(i - 1));
				route.remove(index);
			}
		}
		return route;
	}
	public static WaypointList expandRoute(Graph field, ArrayList<ConnectedWaypoint> route){
		WaypointList result = new WaypointList();
		for (int i = 1; i < route.size(); i++){
			result.add(field.dijkstrasPath(route.get(i - 1), route.get(i)));
			if(!result.get(result.size() - 1).equals(route.get(route.size() - 1))){
				result.remove(result.size() - 1);
			}
		}
		return result;
	}
	public static WaypointList completeLongRoute(Graph field, int type,
			ConnectedWaypoint start, ConnectedWaypoint end){
		return expandRoute(field, createRoute(field, type, start, end));
	}
	public static WaypointList completeRoute(Graph field, int type,
			ConnectedWaypoint start, ConnectedWaypoint end){
		return expandRoute(field, shortenRoute(field, createRoute(field, type, start, end)));
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
