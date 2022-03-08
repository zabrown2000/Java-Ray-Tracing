package primitives;

/**
 * Class Point is the basic class representing a point of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Zahava Brown and Gabriella Bondi
 *
 */

public class Point {

	final Double3 xyz;

	/**
	 * Constructor to initialize Point based on a Double3.
	 * 
	 * @param xyz Double3 value
	 */
	public Point(Double3 xyz) {
		this.xyz = xyz;
	}
	
	/**
	 * Constructor to initialize point based object with its three number values
	 * 
	 * @param d1 first number value
	 * @param d2 second number value
	 * @param d3 third number value
	 */
	public Point(double d1, double d2, double d3) {
		this.xyz = new Double3(d1,d2,d3);
	}

	/**
	 * Subtracts current point from the point given in the parameter
	 * 
	 * @param p Point value
	 * @return vector from second point to the point which called the method
	 */
	public Vector subtract(Point p){
	    Point newXYZ = new Point(this.xyz.subtract(p.xyz));  //subtract is in double 3 returns dounle and then vector constructor turns it into a double 
	    Vector vector = new Vector(newXYZ.xyz);
	    return vector;
	}
	

	/**
	 * Adds a vector to my current point
	 * 
	 * @param v a Vector
	 * @return the sum of the vector and my current Point3
	 */
	public Point add(Vector v) {
		//this.xyz.add(v.Point.xyz);
		//return new Point(xyz);
		
		 Double3 result = this.xyz.add(v.xyz);
		 return new Point(result);
	}
	
	
	

	/**
	 * the distance of two points squared
	 * 
	 * @param p1 first Point value
	 * @param p2 second Point value
	 * @return the distance between these two points squared 
	 */
	public double distanceSquared(Point p1, Point p2) {
		double distanceSqr = (p1.xyz.d1-p2.xyz.d1)*(p1.xyz.d1-p2.xyz.d1) + (p1.xyz.d2 -p2.xyz.d2)*(p1.xyz.d1-p2.xyz.d1) 
				+ (p1.xyz.d3 - p2.xyz.d3)*(p1.xyz.d1-p2.xyz.d1);
		return distanceSqr;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Point)) return false;
		Point other = (Point)obj;
		return xyz.equals(other.xyz);
	}


	@Override
	public String toString() {
	return this.xyz.toString();
	}
	
	/**
	 * the distance of two points
	 * 
	 * @param p1 first Point value
	 * @param p2 second Point value
	 * @return the distance between these two points squared 
	 */
	public double distance(Point point) {
		double distance = distanceSquared(this,point);
		distance = Math.sqrt(distance);
		return distance;
	}
	
}
