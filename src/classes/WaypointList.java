package classes;

import java.util.ArrayList;

public class WaypointList {
	ArrayList<Waypoint> waypointList;

	// creates a WaypointList of type "type" from a given list
	public WaypointList pullType(int type) {
		WaypointList result = new WaypointList();
		if (type < 3 && type >= 0) {
			for (int i = 0; i < this.waypointList.size(); i++) {
				if (this.waypointList.get(i).type == type) {
					result.add(this.get(i));
				}
			}
		}
		return result;
	}

	// create a WaypointList of points within a certain radius from a given list
	public WaypointList pullRadius(double radius, Waypoint a) {
		WaypointList result = new WaypointList();
		for (int i = 0; i < this.size(); i++) {
			if (Waypoint.distance(a, this.get(i)) <= radius) {
				result.add(this.get(i));
			}
		}
		return result;
	}

	public void subtract(WaypointList input) {
		for (int i = 0; i < this.size(); i++) {
			for (int j = 0; i < input.size(); j++)
				if (this.get(i) == input.get(j)) {
					this.remove(i);
				}
		}
	}

	public void subtract(Waypoint input) {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i) == input) {
				this.remove(i);
			}
		}
	}

	// outputs the element of the highest weight, and removes it from the list
	public Waypoint getPriority() {
		int index = 0;
		double maxPriority = 0;
		Waypoint result = new Waypoint();
		if (this.size() > 0) {
			for (int i = 0; i < this.size(); i++) {
				if (maxPriority < this.get(i).weight) {
					index = i;
					maxPriority = this.get(i).weight;
				}
			}
			result = this.waypointList.get(index);
			this.waypointList.remove(index);
		}
		return result;
	}

	// outputs the nearest element to input, and removes it from the list
	public Waypoint getNearest(Waypoint input) {
		Waypoint result = input.findNearest(this);
		int index = this.indexOf(result);
		this.remove(index);
		return result;
	}

	// outputs the nearest element with the highest priority, and removes it
	// from the list. (margin of 5%)
	public Waypoint getNearest(Waypoint input, boolean flag) {
		Waypoint result = input;
		if (!flag) {
			result = this.getNearest(input);
		} else {
			result = input.findNearest(this);
			double radius = Waypoint.distance(input, result);
			double radiusMargin = (Waypoint.distance(input, result) * .05);
			WaypointList Margin = this.pullRadius(radius + radiusMargin, input);
			Margin.subtract(this.pullRadius(radiusMargin - radiusMargin, input));
			result = Margin.getPriority();
			int index = this.indexOf(result);
			this.remove(index);
		}
		return result;
	}

	// the following replicate ArrayList functions for ease
	public int indexOf(Waypoint a) {
		return this.waypointList.indexOf(a);
	}

	public void add(Waypoint a) {
		this.waypointList.add(a);
	}

	public void add(int index, Waypoint a) {
		this.waypointList.add(index, a);
	}

	public void add(WaypointList input) {
		for (int i = 0; i < input.size(); i++) {
			this.add(input.get(i));
		}
	}

	public Waypoint get(int index) {
		return this.waypointList.get(index);
	}

	public int size() {
		return this.waypointList.size();
	}

	public void set(int index, Waypoint a) {
		this.waypointList.set(index, a);
	}

	public void remove(int index) {
		this.waypointList.remove(index);
	}

	// find the length along the points in a WaypointList
	public double lengthAlong() {
		double length = 0.0;
		for (int i = 1; i < this.size(); i++) {
			length += Waypoint.distance(this.get(i), this.get(i - 1));
		}
		return length;
	}

	public double lengthAlong(boolean flag) {
		double length = 0.0;
		if (flag) {
			for (int i = 1; i < this.size(); i++) {
				double distance = Waypoint.distance(this.get(i),
						this.get(i - 1));
				distance = distance / this.get(i).weight;
				length += distance;
			}
		} else {
			this.lengthAlong();
		}
		return length;
	}

	public WaypointList sublist(int start, int end) {
		WaypointList result = new WaypointList();
		for (int i = start; i <= end; i++) {
			result.add(this.get(i));
		}
		return result;
	}
	public void reverse(){
		WaypointList cycle = new WaypointList();
		while(this.size() != 0){
			cycle.add(0, this.get(0));
			this.remove(0);
		}
		for(int i = 0; i < cycle.size(); i ++){
			this.add(cycle.get(i));
		}
	}

	// display all waypoints in the WaypointList
	public void display() {
		for (int i = 0; i < this.waypointList.size(); i++) {
			this.waypointList.get(i).display();
		}
	}
	
	public WaypointList clone(){
		WaypointList result = new WaypointList();
		for(int i = 0; i < this.size(); i++){
			result.add(this.get(i).clone());
		}
		return result;
	}

	public WaypointList(ArrayList<Waypoint> input) {
		waypointList = input;
	}

	public WaypointList() {
		waypointList = new ArrayList<Waypoint>();
	}

	public WaypointList(Waypoint... list) {
		for (Waypoint each : list) {
			waypointList.add(each);
		}
	}

	public WaypointList(String a, int size) {
		waypointList = new ArrayList<Waypoint>();
		if (a.equals("Random")) {
			for (int i = 0; i < size; i++) {
				waypointList.add(new Waypoint("Random"));
			}
		}
	}
}
