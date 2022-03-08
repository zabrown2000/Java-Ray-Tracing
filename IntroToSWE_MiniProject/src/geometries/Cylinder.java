package geometries;

/**
 * Class Cylinder is the basic class representing a cylinder of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Zahava Brown and Gabriella Bondi
 *
 */

import primitives.Ray;
import primitives.*;

public class Cylinder extends Tube {

	private double height;

	/**
	 * Constructor of Cylinder using fields 
	 * 
	 * @param height
	 */
	public Cylinder(double radius, Ray ray, double height) {
		super(radius, ray);
		this.height = height;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}
	
	public Vector getNormal(Point point) {
		
		/*if(point.dotProdcuct(point,this.getRay().p0) == && distance(point,this.getRay().p0) <= this.getRadius()){
				return(this.getRay().dir)	;
	    }
	
		Tube t1 = new Tube(this.getRadius(), this.getRay());
		return t1.getNormal(point);*/
		
		return null;
	}
	
	@Override
	public String toString() {
		return "Cylinder [height=" + height + "]";
	}
}
