package primitives;

/**
 * Class Ray is the basic class representing a ray of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Zahava Brown and Gabriella Bondi
 *
 */

public class Ray {
	
	public Point p0;
	public Vector dir;
	
	//need 1 ctor (noralize), getters, equals and tostring
	
	/**
	 * constructor to create a Ray
	 * @param p point field
	 * @param v vector field
	 */
	public Ray(Point p, Vector v) {
		this.p0 = p;
		this.dir = v.normalize();
	}

	/**
	 * getter method for p0 field
	 * @return Ray's point field
	 */
	public Point getP0() {
		return p0;
	}

	/**
	 * getter method for dir field
	 * @return Ray's vector field
	 */
	public Vector getDir() {
		return dir;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Ray)) return false;
		Ray other = (Ray)obj;
		return this.p0.equals(other.p0) && this.dir.equals(other.dir); 
	}
	
	@Override
	public String toString() {return p0.toString() + "->" + dir.toString(); }
	
}
