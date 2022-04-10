package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Geometries extends Intersectable { //look up composite design patterns, testing number of points
	
	private List<Intersectable> groupGeometries;

	/**
	 * default constructor for groupGeometries
	 */
	public Geometries() {
		//choosing arrayList because we'll be doing a lot of accessing in the list
		//and arrayList has O(1) access time
		groupGeometries = new ArrayList<Intersectable>();
	}
	
	/**
	 * constructor to fill in the empty groupGeometries arrayList
	 * @param geometries array of intersectable points
	 */
	public Geometries(Intersectable... geometries) {
		groupGeometries = new ArrayList<Intersectable>();

		for (int i = 0; i < geometries.length; i++) {
			groupGeometries.add(geometries[i]);
		}
	}

	/**
	 * function to add a number of objects to the groupGeometries collection
	 * @param geometries array of intersectable points
	 */
	public void add(Intersectable... geometries) {
		
		for (int i = 0; i < geometries.length; i++) {
			groupGeometries.add(geometries[i]);
		}
	}
	
	@Override
	public List<Point> findIntsersections(Ray ray) {
		//no shapes in collection
		if (this.groupGeometries.size() == 0) {
			return null;
		}
		
		List<Point> p = new ArrayList<Point>(); //array list because need to index
		
		for (Intersectable shape : this.groupGeometries) {
			List<Point> temp = shape.findIntsersections(ray); //will call each shape's own function
			if (temp == null) continue;
			if (temp.size() == 1) { //1 intersection point for shape
				p.add(temp.get(0));
			} else if (temp.size() == 2) { //2 intersection points for shape
				p.add(temp.get(0));
				p.add(temp.get(1));
			}
			
		}
		//return (p.size() == 0) ? null : p; //returns null if no intersection points
		if (p.size() == 0) {
			return null;
		} else {
			return p;
		}
	}

}
