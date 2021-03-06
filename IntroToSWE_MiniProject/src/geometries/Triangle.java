package geometries;

import java.util.ArrayList;
import java.util.List;

import geometries.Intersectable.GeoPoint;

/**
 * Class Triangle is the basic class representing a triangle of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 * @author Zahava Brown and Gabriella Bondi
 *
 */

import primitives.*;

public class Triangle extends Polygon {

	/**
	 * Constructor for triangle
	 * @param p1 point 1
	 * @param p2 point 2
	 * @param p3 point 3
	 */
	public Triangle(Point p1, Point p2, Point p3) { 
		super(p1, p2, p3); //calls polygon
	}

	public Point getP1() {
		return vertices.get(0);
	}
	
	public Point getP2() {
		return vertices.get(1);
	}
	
	public Point getP3() {
		return vertices.get(2);
	}
	
	@Override
	public String toString() {
		return "Triangle [vertices=" + vertices + ", plane=" + plane + "]"; //need to iterate through list?
	}
	
	
	/**
     * Method to calculate the intersection points between the ray shot and the triangle
     * 
     * @param ray the Ray shot by the camera
     * @return list of GeoPoints that intersected the geometry 
     */
	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
		if(this.plane.findGeoIntersections(ray) == null) {
		     return null;
		     
		}else {
			 Vector v1 = this.getP1().subtract(ray.getP0());
			 Vector v2 = this.getP2().subtract(ray.getP0());
			 Vector v3 = this.getP3().subtract(ray.getP0());
			 
			 v1 = v1.normalize();
			 v2 = v2.normalize();
			 v3 = v3.normalize();
			 
			 Vector n1 = v1.crossProduct(v2).normalize();
			 Vector n2 = v2.crossProduct(v3).normalize();
			 Vector n3 = v3.crossProduct(v1).normalize();
			 
			 double ans1 = ray.getDir().dotProduct(n1);
			 double ans2 = ray.getDir().dotProduct(n2);
			 double ans3 = ray.getDir().dotProduct(n3);
			 
			 if((ans1>0 && ans2>0 && ans3>0) || (ans1<0 && ans2<0 && ans3<0)) {
				 //return this.plane.findGeoIntersectionsHelper(ray);
				 
				 //reassigning geo of geoPoint to be the  triangle, because it calls the funcitom 
				 //of the plane, which does not have an emission color
				 List<GeoPoint> result = this.plane.findGeoIntersectionsHelper(ray);
				 for (GeoPoint gp : result) {
					 gp.geometry = this;
				 }
				 return result;
			 }
			 else {
				 return null;
			 }
		}
	}
	
	
}

