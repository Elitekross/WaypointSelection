package classes;

import java.util.ArrayList;

public class ProjectMain {

	/**
	 * @param args
	 */
	static WaypointList local = new WaypointList();
	
	public static void main(String[] args) {
		Waypoint a = new Waypoint(0.0, 0.0, 0, 0);
		Waypoint b = new Waypoint(1.0, 0.0, 0, 0);
		
		Waypoint a1 = new Waypoint(2.0, 1.5, 1, .5);
		Waypoint b1 = new Waypoint(1.5, 1.5, 1, .7);
		Waypoint c1 = new Waypoint(1.0, 1.0, 1, .6);
		
		Waypoint a2 = new Waypoint(1.9, 1.6, 2, .7);
		Waypoint b2 = new Waypoint(1.9, 1.6, 2, .6);
		
		local.waypointList.add(a1);
		local.waypointList.add(a2);
		local.waypointList.add(b1);
		local.waypointList.add(b2);
		local.waypointList.add(c1);
		
		local.display();
		System.out.println();
		local.getNearest(a, true).display();
		System.out.println();
		local.display();
		//WaypointList route = createRoute(3, 1, a, b);
		//System.out.println("Length of route: " + route.lengthAlong());
		//route.display();

	}

		public static WaypointList createRoute(double dist, int type, Waypoint start, Waypoint end){
		WaypointList result = new WaypointList();
		WaypointList LocalType = local.pullType(1);
		result.add(start);
		result.add(end);
		double resultLength = result.lengthAlong();
		while (resultLength < (0.8 * dist)) {
			result.add(result.size() - 1, LocalType.getPriority());
			resultLength = result.lengthAlong();
			if (LocalType.size()== 0) {
				break;
			}
		}
		while (result.lengthAlong() > (1.2* dist)) {
			result.remove(result.size() - 2);
			resultLength = result.lengthAlong();
		}
		return result;
	}
	
}
