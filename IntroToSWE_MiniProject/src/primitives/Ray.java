package primitives;

import java.util.List;
import geometries.Intersectable.GeoPoint;

/**
 * Class Ray is the basic class representing a ray of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Zahava Brown and Gabriella Bondi
 *
 */

public class Ray {
	
	private static final double DELTA = 0.1;
	
	public Point p0;
	public Vector dir;
	
	/**
	 * constructor to create a Ray
	 * @param p point field
	 * @param v vector field
	 */
	public Ray(Point p, Vector v) {
		this.p0 = p;
		this.dir = v.normalize();
	}
	
	/**
	 * 
	 * @param p point
	 * @param dir Vector 
	 * @param normal  Vector
	 * 
	 * sets ray to start at point and delta
	 */
	public Ray(Point p, Vector dir, Vector normal) {
		Vector deltaV = normal.scale(normal.dotProduct(dir) > 0 ? DELTA : - DELTA).normalize();  //?????????
		this.p0 = p.add(deltaV);
		this.dir = dir;
	}

	/**
	 * getter method for p0 field
	 * @return Ray's point field
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * getter method for dir field
	 * @return Ray's vector field
	 */
	public Vector getDir() {
		return dir;
	}
	
	/**
	 * Helper function to create a ray specifically for the use of adaptive super sampling
	 * @param cp center point, the basis
	 * @param xVal x-coord for point
	 * @param yVal y-coord for point
	 * @return new ray
	 */
	public Ray createAdaptiveRay(Point cp, double xVal, double yVal) {
		
		Point newCorner = cp.add(new Point(xVal, yVal, 0)); //creating corner point
		Vector cornerVec = newCorner.subtract(this.p0); //creating vector through point from initial ray
		return new Ray(this.p0, cornerVec);
	}
	
	/**
	 * Function to find the point closest to the ray's head
	 * @param points list of points to check
	 * @return the point closest to ray head
	 */
	public Point findClosestPoint(List<Point> points) {
		    return points == null || points.isEmpty() ? null
		           : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
	}
	
	
	/**
	 * refactored getter method for point on ray
	 * @param t scalar for ray vector
	 * @return new point
	 */
	public Point getPoint(double t) {
		return (this.p0.add(dir.scale(t))); //p0 + t*v
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Ray)) return false;
		Ray other = (Ray)obj;
		return this.p0.equals(other.p0) && this.dir.equals(other.dir); 
	}
	
	
	@Override
	public String toString() {return p0.toString() + "->" + dir.toString(); }
	
	
	/**
	 * Function to find the GeoPoint closest to the ray's head
	 * @param list of GeoPoints to check
	 * @return the GeoPoint closest to ray head
	 */
	public GeoPoint findClosestGeoPoint(List<GeoPoint> intersection) {
        if ((intersection == null) || (intersection.isEmpty())) return null;
		
		double minDistance = Double.MAX_VALUE;
		GeoPoint closest = null;
		
		for (GeoPoint p : intersection) {
			double result  = this.p0.distance(p.point);
			if (result < minDistance) {
				minDistance = result;
				closest = p;
			}
		}
		return closest;
		
	}
}
