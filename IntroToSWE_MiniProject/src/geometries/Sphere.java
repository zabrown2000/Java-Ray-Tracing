package geometries;

/**
 * Class Sphere is the basic class representing a sphere of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Zahava Brown and Gabriella Bondi
 *
 */

import primitives.*;

public class Sphere implements Geometry {

	private Point point;
	private double radius;
	

	/**
	 * Sphere constructor of the parameters
	 * 
	 * @param point a Point
	 * @param radius a double
	 */
	public Sphere(Point point, double radius) {
		this.point = point;
		this.radius = radius;
	}


	/**
	 * 
	 * @return the point field
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * 
	 * @return the radius field
	 */
	public double getRadius() {
		return radius;
	}
	

	public Vector getNormal() {
		return null;
	}
	

	@Override
	public String toString() {
		return "Sphere [radius=" + radius + "]";
	}
}
