package com.sf.location.geofence;

/**
*
* @author Charles
*/
public class Geofence 
{

	/**
	 * Mean radius.
	 */
	private static double EARTH_RADIUS = 6371;

	/**
	 * Returns the distance between two sets of latitudes and longitudes in meters.
	 * <p/>
	 * Based from the following JavaScript SO answer:
	 * http://stackoverflow.com/questions/27928/calculate-distance-between-two-latitude-longitude-points-haversine-formula,
	 * which is based on https://en.wikipedia.org/wiki/Haversine_formula (error rate: ~0.55%).
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return 
	 */
	public static double getDistanceBetween(double lat1, double lon1, double lat2, double lon2) {
	    double dLat = toRadians(lat2 - lat1);
	    double dLon = toRadians(lon2 - lon1);

	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
	            Math.cos(toRadians(lat1)) * Math.cos(toRadians(lat2)) *
	                    Math.sin(dLon / 2) * Math.sin(dLon / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double d = EARTH_RADIUS * c * 1000; //multiply by 1000 to convert to meters

	    return d;
	}

	public static double toRadians(double degrees) {
	    return degrees * (Math.PI / 180);
	}
	
	public boolean isWithinCircleBoundary(double lat, double lng, double radius, double outletLat, double outletLng) {
		if(lat != 0 && lng != 0 && outletLat != 0 && outletLng != 0) {
			if(getDistanceBetween(lat, lng, outletLat, outletLng) > radius) {
				return false;
			}
		}
		
		return true;
	}
	
}
