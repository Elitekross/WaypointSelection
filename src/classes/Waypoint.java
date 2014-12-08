package classes;

public class Waypoint {
	double lat;
	double lng;
	int type;
	double weight;


	
	
	public Waypoint clone(){
		Waypoint result = new Waypoint();
		result.lat = this.lat;
		result.lng = this.lng;
		result.type = this.type;
		result.weight = this.weight;
		return result;
	}
	public boolean equals(Waypoint a){
		if(this.lat == a.lat && this.lng == a.lng){
			return true;
		} else {
			return false;
		}
	}
	// standard euclidean distance
	public static double distance(Waypoint a, Waypoint b) {
		double distance = Math.sqrt(((a.lat - b.lat) * (a.lat - b.lat))
				+ ((a.lng - b.lng) * (a.lng - b.lng)));
		return distance;
	}

	public Waypoint(double lat1, double lng1, int type1, double weight1) {
		lat = lat1;
		lng = lng1;
		type = type1;
		weight = weight1;
	}

	public Waypoint() {
		lat = 0;
		lng = 0;
		type = 0;
		weight = 1;
	}

	public Waypoint(String a) {
		if (a.equals("Random")) {
			lat = (int) (Math.random() * 10);
			lng = (int) (Math.random() * 10);
			type = (int) (Math.random() * 4);
			weight = Math.random();
			if (weight == 0) {
				type = 0;
			}
		}
	}

	// displays the information in a waypoint
	public void display() {
		System.out.println("(" + lat + ", " + lng + "), Type: " + type
				+ ", Weight: " + weight);
	}

	// finds the nearest Waypoint
	public Waypoint findNearest(WaypointList field) {
		Waypoint result = field.get(0);
		for (int i = 0; i < field.size(); i++) {
			if (Waypoint.distance(this, field.get(i)) < Waypoint.distance(this,
					result)) {
				if (field.get(i) != this) {
					result = field.get(i);
				}
			}
		}
		return result;
	}

	// finds the Waypoint of highest priority in radius r around given point in
	// a field
	public Waypoint findPriority(double r, WaypointList field) {
		WaypointList localList = field.pullRadius(r, this);
		Waypoint result = this;
		if (localList.size() > 0) {
			result = localList.getPriority();
		}
		return result;
	}
}
