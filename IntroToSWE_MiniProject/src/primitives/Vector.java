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
		super(d1,d2,d3);
		if ((d1 == 0) && (d2 == 0) && (d3 == 0)) {
			throw new IllegalArgumentException("Error: Zero Vector");
		}
	}

	/**
	 * Vector constructor that receives an object of type Double3
	 * @param d 3d coordinate
	 */
	public Vector(Double3 d) {
		super(d);
		if ((d.d1 == 0) && (d.d2 == 0) && (d.d3 == 0)) {
			throw new IllegalArgumentException("Error: Zero Vector");
		}
		
	}
	
	/**
	 * Sums up two vectors 
	 * 
	 * @param vector1 the first vector
	 * @param vector2 the second vector
	 * @return the triads of the sums of the two vectors 
	 */
	public Vector add(Vector vector1, Vector vector2) {
		Point sum = vector1.add(vector2);
		Vector added = new Vector(sum.xyz);
		return added;
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
	 * the dot product of vectors
	 * @param the vector you want to dot product you current vector with 
	 * @return the double value of the dot product 
	 */
	public double dotProduct(Vector vector) {
		Point newXYZ = new Point(this.xyz.product(vector.xyz));
		double dotproduct = newXYZ.xyz.d1+newXYZ.xyz.d2+newXYZ.xyz.d3;
		return dotproduct;
	}
	
	

	/**
	 * the cross product of the two vectors 
	 * 
	 * @param v1 the first vector
	 * @param v2 the second vector
	 * @return the cross product vector 
	 */
	public Vector crossProduct(Vector v) {
		double x = (this.xyz.d2*v.xyz.d3) - (this.xyz.d3*v.xyz.d2);
	    double y = (this.xyz.d3*v.xyz.d1) - (this.xyz.d1*v.xyz.d3);
	    double z = (this.xyz.d1*v.xyz.d2) - (this.xyz.d2*v.xyz.d1);
	    

	    if ((x == 0) && (y == 0) && (z == 0)) {
			throw new IllegalArgumentException("Error: Zero Vector");
		}
	    return new Vector(x,y,z);
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
	
	/**
	 * Function to calculate a normal to the calling vector
	 * @return the normal vector
	 */
	public Vector calcNormal() {
		int min = 1;
		double x = this.getX(), y = this.getY(), z = this.getZ();
		double minCoor = x > 0 ? x : -x;
		if (Math.abs(y) < minCoor) {
			minCoor = y > 0 ? y : -y;
			min = 2;
		}
		if (Math.abs(z) < minCoor) {
			min = 3;
		}
		switch (min) {
		case 1: {
			return new Vector(0, -z, y).normalize();
		}
		case 2: {
			return new Vector(-z, 0, x).normalize();
		}
		case 3: {
			return new Vector(y, -x, 0).normalize();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + min);
		}
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
