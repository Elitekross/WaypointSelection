package classes;

public class ConnectedWaypoint extends Waypoint{
	WaypointList neighbors;
	ConnectedWaypoint parent;
	double distance;
	boolean visited;
	
	public ConnectedWaypoint(double i, double j, int k, double d) {
		this.lat=i;
		this.lng=j;
		this.type=k;
		this.weight=d;
		this.neighbors = new WaypointList();
		distance = -1;
		visited = false;
	}
	public ConnectedWaypoint() {
		// TODO Auto-generated constructor stub
	}
	public Waypoint toWaypoint(){
		return new Waypoint(this.lat, this.lng, this.type, this.weight);
	}
	public void addNeighbor(Waypoint a){
		this.neighbors.add(a);
		
	}
	public void addNeighbor(ConnectedWaypoint a){
		this.addNeighbor(a.toWaypoint());
		if (a.neighbors.indexOf(this.toWaypoint()) != -1){
			a.addNeighbor(this.toWaypoint());
		}
	}
	public void removeNeighbor(Waypoint a){
		this.neighbors.subtract(a);
	}
	public Waypoint getNeighbor(int index){
		return this.neighbors.get(index);
	}
	public boolean neighbors(ConnectedWaypoint a){
		for(int i = 0; i < this.neighbors.size(); i++){
			if(this.getNeighbor(i).equals(a)){
				return true;
			}
		}
		for(int i = 0; i < a.neighbors.size(); i++){
			if(a.getNeighbor(i).equals(this)){
				return true;
			}
		}
		return false;
	}
	public ConnectedWaypoint pullNeighbors(int type){
		ConnectedWaypoint result = new ConnectedWaypoint();
		result.lat = this.lat;
		result.lng = this.lng;
		result.type = this.type;
		result.weight = this.weight;
		for(int i = 0; i < this.neighbors.size(); i++){
			if(this.neighbors.get(i).type == type){
				result.addNeighbor(this.neighbors.get(i));
			}
		}
		return result;
	}
	public ConnectedWaypoint clone(){
		ConnectedWaypoint result = new ConnectedWaypoint();
		result.lat = this.lat;
		result.lng = this.lng;
		result.weight = this.weight;
		result.type = this.type;
		result.neighbors = this.neighbors.clone();
		return result;
	}
	public void display(){
		System.out.println("(" + lat + ", " + lng + "), Type: " + type
				+ ", Weight: " + weight +", Distance: " + distance + ", Visited: " + visited);
	}
}
