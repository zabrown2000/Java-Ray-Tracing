package geometries;

import java.util.List;
import java.util.ArrayList;
/**
 * Class Plane is the basic class representing a plane of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Zahava Brown and Gabriella Bondi
 *
 */

import primitives.Point;
import primitives.Ray;
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
		this.q0 = p1;
		Vector v1 = p2.subtract(p1);
		Vector v2 = p3.subtract(p1);
		Vector n = v1.crossProduct(v2);
		this.normal = n.normalize();
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
	public Vector getNormal(Point point) { //calls below function
		return null;
	}
	
	public Vector getNormal() {
		return normal;
	}
	
	public Point getQ0() {
		return q0;
	}
	
	@Override
	public String toString() {
		return "Plane [q0=" + q0.toString() + ", normal=" + normal.toString() + "]";
	}
	
	/**
     * Method to calculate the intersection points between the ray shot and the plane
     * 
     * @param ray the Ray shot by the camera
     * @return the intersection Point to the list of intersections
     */
	@Override
	public List<Point> findIntsersections(Ray ray) {
		// TODO Auto-generated method stub
		double t = (normal.dotProduct(q0.subtract(ray.getP0())))/(normal.dotProduct(ray.getDir()));
		if (t<0) {
			return null;
		} else {
			Point P = ray.getP0().add( ray.getDir().scale(t));
		    List<Point> intersectable = new ArrayList<>();
		    intersectable.add(P);
		    return intersectable;
		}
	}
	
	
}

