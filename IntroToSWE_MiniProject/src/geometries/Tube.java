package geometries;

/**
 * Class Tube is the basic class representing a tube of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Zahava Brown and Gabriella Bondi
 *
 */

import primitives.*;

public class Tube implements Geometry {
	
	private double radius;
	private Ray ray;
	

	/**
	 * Constructor of Tue using fields
	 * 
	 * @param radius double
	 * @param ray Ray
	 */
	public Tube(double radius, Ray ray) {
		this.radius = radius;
		this.ray = ray;
	}

	public Vector getNormal() {
		return null;
	}
	
	/**
	 * 
	 * @return the radius 
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * 
	 * @return the ray
	 */
	public Ray getRay() {
		return ray;
	}

	@Override
	public String toString() {
		return "Tube [radius=" + radius + "]";
	}
}
