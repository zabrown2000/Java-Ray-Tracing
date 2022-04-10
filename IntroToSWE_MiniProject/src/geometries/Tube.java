package geometries;

import java.util.List;

/**
 * Class Tube is the basic class representing a tube of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Zahava Brown and Gabriella Bondi
 *
 */

import primitives.*;

public class Tube extends Geometry {
	
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
	
	/**
	 * gets the normal to a tube 
	 * 
	 * @param point Point
	 * @return the normal vector to the point 
	 */
	public Vector getNormal(Point point) {
	
		double distance = this.ray.dir.dotProduct(point.subtract(this.ray.p0));
		Point center = this.ray.p0.add(ray.dir.scale(distance));
		Vector normal;
		normal = (point.subtract(center)).normalize();
		return normal;
			
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

	@Override
	public List<Point> findIntsersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}
