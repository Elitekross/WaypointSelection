package classes;

public class Waypoint {
	double lat;
	double lng;
	int type;
	double weight;
	
	public static double distance(Waypoint a, Waypoint b){
		double distance = Math.sqrt((a.lat - b.lat)*(a.lat-b.lat) + (a.lng - b.lng)*(a.lng-b.lng));
		return distance;
	}
}
