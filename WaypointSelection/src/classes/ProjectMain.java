package classes;

import java.util.ArrayList;

public class ProjectMain {

	/**
	 * @param args
	 */
	WaypointList local = new WaypointList();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public WaypointList createRoute(double dist, int type, Waypoint start, Waypoint end){
		WaypointList result = new WaypointList();
		WaypointList LocalType = new WaypointList(local.pullList(type));
		result.waypointList.add(start);
		result.waypointList.add(end);
		while (result.lengthAlong() < (0.95 * dist)) {
			result.waypointList.add(result.waypointList.size() - 1, LocalType.getPriority());
		}
		while (result.lengthAlong() > (1.05 * dist)) {
			result.waypointList.remove(result.waypointList.size() - 2);
		}
		return result;
	}
	
}
