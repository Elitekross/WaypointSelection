package classes;

import java.util.ArrayList;

public class WaypointList {
	ArrayList<Waypoint> waypointList;
	//creates a WaypointList of type "type" from a given list
	public WaypointList pullType(int type) {
		WaypointList result = new WaypointList();
		if (type < 3 && type >= 0) {
			for (int i = 0; i < this.waypointList.size(); i++){
				if (this.waypointList.get(i).type == type) {
					result.add(this.get(i));
				}
			}
		}
		return result;
	}
	//create a WaypointList of points within a certain radius from a given list
	public WaypointList pullRadius(double radius, Waypoint a){
		WaypointList result = new WaypointList();
		for (int i = 0; i < this.size(); i++){
			if (Waypoint.distance(a, this.get(i)) <= radius){
				result.add(this.get(i));
			}
		}
		return result;
	}
	//outputs the element of the highest weight, and removes it from the list
	public Waypoint getPriority() {
		int index = 0;
		double maxPriority = 0;
		Waypoint result = new Waypoint();
		if (this.size() > 0) {
			for (int i = 0; i < this.size(); i++){
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
	
	//outputs the nearest element to input, and removes it from the list
	public Waypoint getNearest(Waypoint input){
		Waypoint result = input.findNearest(this);
		int index = this.indexOf(result);
		this.remove(index);
		return result;
	}
	//outputs the nearest element with the highest priority, and removes it from the list. (margin of  5%)
	public Waypoint getNearest(Waypoint input, boolean flag){
		Waypoint result = input;
		WaypointList field = this;
		if(!flag){
			result = this.getNearest(input);
		}
		else {
			result = input.findNearest(this);
			double radius = (Waypoint.distance(input, result) * .05);
			if (this.pullRadius(radius, result).size() == 0){
				result = this.getNearest(input);
			} else {
				result = result.findPriority(radius, this);
				int index = this.indexOf(result);
				this.remove(index);
			}
		}
		return result;
	}
	
	//the following replicate ArrayList functions for ease
	public int indexOf(Waypoint a){
		return this.waypointList.indexOf(a);
	}
	public void add(Waypoint a){
		this.waypointList.add(a);
	}
	public void add(int index, Waypoint a){
		this.waypointList.add(index, a);
	}
	public Waypoint get(int index){
		return this.waypointList.get(index);
	}
	public int size(){
		return this.waypointList.size();
	}
	public void set(int index, Waypoint a){
		this.waypointList.set(index, a);
	}
	public void remove(int index){
		this.waypointList.remove(index);
	}
	
	//find the length along the points in a WaypointList
	public double lengthAlong(){
		double length = 0.0;
		for (int i = 1; i < this.waypointList.size(); i++) {
			length += Waypoint.distance(this.waypointList.get(i), this.waypointList.get(i - 1));
		}
		return length;
	}
	//display all waypoints in the WaypointList
	public void display(){
		for(int i = 0; i < this.waypointList.size(); i++){
			this.waypointList.get(i).display();
		}
	}
	
	public WaypointList(ArrayList<Waypoint> input) {
		waypointList = input;
	}
	public WaypointList() {
		waypointList = new ArrayList<Waypoint>();
	}
}
