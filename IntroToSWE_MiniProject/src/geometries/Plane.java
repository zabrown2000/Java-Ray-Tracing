package geometries;

/**
 * Class Plane is the basic class representing a plane of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Zahava Brown and Gabriella Bondi
 *
 */

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
	
	public Point q0;
	public Vector normal;
	
	/**
	 * Constructor for Plane that takes 3 points
	 * @param p1 point 1
	 * @param p2 point 2
	 * @param p3 point 3
	 */
	public Plane(Point p1, Point p2, Point p3) {
		this.normal = null;
		this.q0 = p1;
	}
	
	/**
	 * Constructor for Plane that takes a point and a vector
	 * @param p point for plane
	 * @param v normal vector to plane
	 */
	public Plane(Point p, Vector v) {
		this.q0 = p;
		this.normal = v.normalize();
	}
	
	/**
	 * Method to get vector normal to the plane
	 * @return the normal vector
	 */
	public Vector getNormal() {
		return null;
	}
	
	public Point getQ0() {
		return q0;
	}
	
	@Override
	public String toString() {
		return "Plane [q0=" + q0.toString() + ", normal=" + normal.toString() + "]";
	}
	
	
}

