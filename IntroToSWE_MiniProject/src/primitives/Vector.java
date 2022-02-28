package primitives; 

/**
 * Class Vector is the basic class representing a vector of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Zahava Brown and Gabriella Bondi
 *
 */

public class Vector extends Point { //need equals to string
	
	/**
	 * Vector constructor that receives 3 doubles
	 * @param d1 first number value
	 * @param d2 second number value
	 * @param d3 third number value
	 */
	public Vector(double d1, double d2, double d3) {
		
		if ((d1 == 0) && (d2 == 0) && (d3 == 0)) {
			throw new IllegalArgumentException("Error: Zero Vector");
		}
		Point(d1, d2, d3);
		
	}
	
	
	/**
	 * Vector constructor that receives an object of type Double3
	 * @param d 3d coordinate
	 */
	Vector(Double3 d) {
		
		if ((d.d1 == 0) && (d.d2 == 0) && (d.d3 == 0)) {
			throw new IllegalArgumentException("Error: Zero Vector");
		}
		Point(d);
	}
	
	/**
	 * Method to scale a vector by a scalar
	 * @param v the vector
	 * @param scalar the scalar to multiply
	 * @return
	 */
	public Vector scale(double scalar) {
		if (scalar == 0) {
			throw new IllegalArgumentException("Error: Zero Vector");
		}
		return new Vector(this.xyz.scale(scalar)); 
	}
	
	/**
	 * Method to calculate the length of a vector squared
	 * @return vector's length squared
	 */
	public double lengthSquared() {
		return this.dotProduct(this);
	}
	
	/**
	 * Method to calculate the length of a vector
	 * @return vector's length
	 */
	public double length() {
		return Math.sqrt(this.lengthSquared()); //need to check length is 0?
	}
	
	/**
	 * Method to normalize a vector
	 * @return new vector, normalized
	 */
	public Vector normalize() {
		double lengthFactor = (1/ this.length());
		if (lengthFactor == 0) {
			throw new IllegalArgumentException("Error: Zero Vector");
		}
		return this.scale(lengthFactor);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Point)) return false;
		Point other = (Point)obj;
		return super.equals(other);
	}
	
	@Override
	public String toString() {return "->" + super.toString(); }
}
