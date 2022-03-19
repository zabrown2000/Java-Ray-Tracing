package geometries;

import java.util.List;
import java.util.ArrayList;


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
	

	public Vector getNormal(Point point) {
		
		/*double distance = point.distance(this.point);  
		if( distance != this.radius){
			throw new IllegalArgumentException("Error: point is not on the boundry");
		}*/
		
		Vector normal;
		normal = (point.subtract(this.point)).normalize();
		return normal;
	}
	


	@Override
	public List<Point> findIntsersections(Ray ray) {
		Vector u = this.point.subtract(ray.getP0());
		double tm = ray.getDir().dotProduct(u);
		double distance = u.distance(ray.getDir());
		if (distance>= this.radius) return null;
		double th = Math.sqrt(this.radius*this.radius - distance*distance);
		double t1 = tm + th;
		double t2 = tm - th;
		
		List<Point> Intersection = new ArrayList<>();
		
		if (t1>0) {
			Point P1 = ray.getP0().add(ray.getDir().scale(t1));
			Intersection.add(P1);
        }
		if(t2>0) {
			Point P2 = ray.getP0().add(ray.getDir().scale(t1));
			Intersection.add(P2);
		}
		return Intersection;
	}
}
