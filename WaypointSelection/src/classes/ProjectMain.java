package classes;

import java.util.ArrayList;

public class ProjectMain {

	/**
	 * @param args
	 */
	// Version 0.3
	static WaypointList local = new WaypointList();

	public static void setPoints(double a, double b, double c, double d) {
		
		ConnectedWaypoint one = new ConnectedWaypoint(0, 0, 1, .5);
		ConnectedWaypoint two = new ConnectedWaypoint(0, 1, 2, .25);
		ConnectedWaypoint three = new ConnectedWaypoint(1, 0, 0, 1);
		ConnectedWaypoint four = new ConnectedWaypoint(.75, .25, 1, .6);
		ConnectedWaypoint five = new ConnectedWaypoint(1, 1, 2, .35);
		ConnectedWaypoint six = new ConnectedWaypoint(2, .5, 1, .5);
		ConnectedWaypoint seven = new ConnectedWaypoint(1.75, 1.75, 3, .8);
		ConnectedWaypoint eight = new ConnectedWaypoint(2.5, 1.25, 1, .7);
		ConnectedWaypoint nine = new ConnectedWaypoint(3, 3, 1, .5);
		ConnectedWaypoint ten = new ConnectedWaypoint(1, 3, 2, .1);
		
		one.addNeighbor(two);
		one.addNeighbor(three);
		two.addNeighbor(four);
		two.addNeighbor(ten);
		three.addNeighbor(four);
		three.addNeighbor(six);
		six.addNeighbor(three);
		four.addNeighbor(five);
		five.addNeighbor(six);
		six.addNeighbor(five);
		five.addNeighbor(seven);
		five.addNeighbor(eight);
		seven.addNeighbor(nine);
		eight.addNeighbor(nine);
		nine.addNeighbor(ten);
		
		Graph field = new Graph();
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
		
		WaypointList path0 = completeLongRoute(field, 1, one, ten);
		System.out.println("Distance: " + path0.lengthAlong());
		System.out.println("Weighted Distance: " + path0.lengthAlong(true));
		path0.display();
		WaypointList path1 = completeRoute(field, 1, one, ten);
		System.out.println("Distance: " + path1.lengthAlong());
		System.out.println("Weighted Distance: " + path1.lengthAlong(true));
		path1.display();
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
