package classes;

import java.util.ArrayList;

public class Graph extends WaypointList{

	ArrayList<ConnectedWaypoint> waypointList;
	
	public Graph() {
		this.waypointList = new ArrayList<ConnectedWaypoint>();
	}
	public ConnectedWaypoint get(int index){
		return this.waypointList.get(index);
	}
	public int indexOf(ConnectedWaypoint a){
		return this.waypointList.indexOf(a);
	}
	public void remove(int index){
		this.waypointList.remove(index);
	}
	public ConnectedWaypoint getNeighborOf(ConnectedWaypoint a, int index){
		ConnectedWaypoint result = new ConnectedWaypoint();
		for (int i = 0; i < this.size(); i ++){
			if (this.waypointList.get(i).equals(a.getNeighbor(index))){
				result = this.waypointList.get(i);
			}
		}
		return result;
	}
	public void add(ConnectedWaypoint a){
		this.waypointList.add(a);
	}
	public int size(){
		return this.waypointList.size();
	}
	public int typedSize(int type){
		ArrayList<ConnectedWaypoint> cycle = new ArrayList<ConnectedWaypoint>();
		for(int i = 0; i < this.size(); i ++){
			if (this.get(i).type == type){
				cycle.add(this.get(i));
			}
		}
		return cycle.size();
	}
	public void removeEdge(ConnectedWaypoint a, ConnectedWaypoint b){
		if(this.indexOf(a) != -1 && this.indexOf(b) != -1 && a.neighbors(b)){
			a.removeNeighbor(b);
			b.removeNeighbor(a);
		}
	}
	public Graph typedGraph(int type){
		Graph result = (Graph)this.clone();
		for(int i = 0; i < result.size(); i++){
			if(result.get(i).type != type){
				result.get(i).type = 0;
			}
		}
		return result;
	}
	public Graph clone(){
		Graph result = new Graph();
		for(int i = 0; i < this.size(); i ++){
			result.add(this.get(i).clone());
		}
		return result;
	}
	public boolean lessThan(double a, double b){
		if(a <= b){
			if(a == -1){
				return false;
			} else {
				return true;
			}
		} else {
			if(b == -1){
				return true;
			} else{
				return false;
			}
		}
	}
	public double add(double a, double b){
		if(a == -1 || b == -1){
			return -1;
		} else {
			return a + b;
		}
	}
	public double dijkstras(ConnectedWaypoint a, ConnectedWaypoint b){
		Graph field = this.clone();
		ConnectedWaypoint current = new ConnectedWaypoint();
		ConnectedWaypoint destination = new ConnectedWaypoint();
		for(int i = 0; i < field.waypointList.size(); i++){
				field.waypointList.get(i).distance = -1;
				field.waypointList.get(i).visited = false;
			if(field.waypointList.get(i).equals(a)){
				field.waypointList.get(i).distance = 0;
				current =  field.waypointList.get(i);
			}
			if(field.waypointList.get(i).equals(b)){
				destination = field.waypointList.get(i);
			}
		}
		while(!destination.visited){
			for(int i = 0; i < current.neighbors.size(); i = i + 1){
				ConnectedWaypoint target = field.getNeighborOf(current, i);
				if (!target.visited){
					double distance = Waypoint.distance(current, target);
					if (lessThan(add(current.distance, distance), target.distance)){
						target.distance = add(distance, current.distance);
						target.parent = current;
					}
				}
			}

			current.visited = true;
			if(destination.visited){
				break;
			}
			for(int i = 0; i < field.size(); i ++){
				if(!field.waypointList.get(i).visited){
					current = field.waypointList.get(i);
					break;
				}
			}
			for(int i = 0; i < field.size(); i ++){
				if(!field.waypointList.get(i).visited && lessThan(field.waypointList.get(i).distance, current.distance)){
					current = field.waypointList.get(i);
				}
			}
		}
		return destination.distance;
	}
	
	
	public WaypointList dijkstrasPath(ConnectedWaypoint a, ConnectedWaypoint b){
		Graph field = this.clone();
		WaypointList path = new WaypointList();
		ConnectedWaypoint start = new ConnectedWaypoint();
		ConnectedWaypoint current = new ConnectedWaypoint();
		ConnectedWaypoint destination = new ConnectedWaypoint();
		for(int i = 0; i < field.waypointList.size(); i++){
				field.waypointList.get(i).distance = -1;
				field.waypointList.get(i).visited = false;
			if(field.waypointList.get(i).equals(a)){
				field.waypointList.get(i).distance = 0;
				current =  field.waypointList.get(i);
				start =  field.waypointList.get(i);
			}
			if(field.waypointList.get(i).equals(b)){
				destination = field.waypointList.get(i);
			}
		}
		while(!destination.visited){
			for(int i = 0; i < current.neighbors.size(); i = i + 1){
				ConnectedWaypoint target = field.getNeighborOf(current, i);
				if (!target.visited){
					double distance = Waypoint.distance(current, target);
					if (lessThan(add(current.distance, distance), target.distance)){
						target.distance = add(distance, current.distance);
						target.parent = current;
					}
				}
			}

			current.visited = true;
			if(destination.visited){
				break;
			}
			for(int i = 0; i < field.size(); i ++){
				if(!field.waypointList.get(i).visited){
					current = field.waypointList.get(i);
					break;
				}
			}
			for(int i = 0; i < field.size(); i ++){
				if(!field.waypointList.get(i).visited && lessThan(field.waypointList.get(i).distance, current.distance)){
					current = field.waypointList.get(i);
				}
			}
		}
		current = destination;
		while(path.indexOf(start) == -1){
			path.add(current);
			current = current.parent;
		}
		path.reverse();
		return path;
	}
	public ConnectedWaypoint getNearestTyped(ConnectedWaypoint a, int type){
		ConnectedWaypoint result = new ConnectedWaypoint();
		for(int i = 0; i < this.size(); i ++){
			if (this.get(i).type == type && !this.get(i).equals(a) && !this.get(i).visited){
				result = this.get(i);
				break;
			}
		}
		for(int i = 0; i < this.size(); i ++){
			if (this.get(i).type == type && !this.get(i).equals(a) && !this.get(i).visited 
					&& this.dijkstras(a, result) >= this.dijkstras(a, this.get(i))){
				if(this.dijkstras(a, result) == this.dijkstras(a, this.get(i))){
					if(result.weight < this.get(i).weight){
						result = this.get(i);
					}
				} else {
					result = this.get(i);
				}
			}
		}
		result.visited = true;
		return result;
	}
}
