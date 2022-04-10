package geometries;

import java.util.List;
import java.util.Objects;

import primitives.*;

public abstract class Intersectable {
	
	/**
	 * helper inner class to know which shape a given point is on
	 * @author zbrow and gbondi
	 *
	 */
	public static class GeoPoint {
	    public Geometry geometry;
	    public Point point;
	    
	    /**
	     * constructor for inner class GeoPoint
	     * @param geo geometry parameter
	     * @param p point parameter
	     */
	    public GeoPoint(Geometry geo, Point p) {
	    	this.geometry = geo;
	    	this.point = p;
	    }


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof GeoPoint))
				return false;
			GeoPoint other = (GeoPoint) obj;
			return (geometry == other.geometry) && (point == other.point);
		}


		@Override
		public String toString() {
			return "GeoPoint [geometry=" + geometry + ", point=" + point + "]";
		}
	    
	}

	
	public abstract List<Point> findIntsersections(Ray ray);
}
