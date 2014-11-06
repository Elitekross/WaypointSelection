package classes;

import java.util.ArrayList;

public class WaypointList {
	ArrayList<Waypoint> waypointList;
	
	public ArrayList<Waypoint> pullList(int type) {
		ArrayList<Waypoint> result = new ArrayList<Waypoint>();
		if (type < 3 && type >= 0) {
			for (int i = 0; i < this.waypointList.size(); i++){
				if (this.waypointList.get(i).type == type) {
					result.add(this.waypointList.get(i));
				}
			}
		}
		return result;
	}
	public Waypoint getPriority() {
		int index = 0;
		double maxPriority = 0;
		Waypoint result;
		for (int i = 0; i < this.waypointList.size(); i++){
			if (maxPriority < this.waypointList.get(i).weight) {
				index = i;
				maxPriority = this.waypointList.get(i).weight;
			}
		}
		result = this.waypointList.get(index);
		this.waypointList.remove(index);
		return result;
	}
	
	public double lengthAlong(){
		double length = 0.0;
		for (int i = 1; i < this.waypointList.size(); i++) {
			length = Waypoint.distance(this.waypointList.get(i), this.waypointList.get(i - 1));
		}
		return length;
	}
	
	public WaypointList(ArrayList<Waypoint> input) {
		waypointList = input;
	}
	public WaypointList() {
		waypointList = new ArrayList<Waypoint>();
	}
}
