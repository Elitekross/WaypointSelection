package classes;


public class ProjectMain {

	/**
	 * @param args
	 */
	//Version 0.3
	static WaypointList local = new WaypointList();
	
	public static void setPoints(){
		
		Waypoint a = new Waypoint(0.0, 0.0, 0, 0);
		Waypoint b = new Waypoint(2.0, 2.0, 0, 0);
		
		Waypoint a1 = new Waypoint(2.0, 1.5, 1, .5);
		Waypoint b1 = new Waypoint(1.5, 1.5, 1, .7);
		Waypoint c1 = new Waypoint(1.0, 1.0, 1, .6);
		
		Waypoint d1 = new Waypoint(1.9, 1.6, 1, .7);
		Waypoint e1 = new Waypoint(1.9, 1.6, 1, .6);
		
		
		local.add(a1);
		local.add(d1);
		local.add(b1);
		local.add(e1);
		local.add(c1);
		
		WaypointList route = createRoute(1, a, b);
		System.out.println("Length of route: " + route.lengthAlong());
		route.display();

	}

		public static WaypointList createRoute(int type, Waypoint start, Waypoint end){
		WaypointList result = new WaypointList();
		WaypointList LocalType = local.pullType(type);
		LocalType.add(end);
		result.add(start);
		boolean routeFinished = false;
		while (!routeFinished) {
			result.add(LocalType.getNearest(result.get(result.size() - 1), true));
			if (result.get(result.size() - 1) == end){
				routeFinished = true;
			}
			if (LocalType.size()== 0) {
				break;
			}
		}
		
		return result;
	}
	
}
