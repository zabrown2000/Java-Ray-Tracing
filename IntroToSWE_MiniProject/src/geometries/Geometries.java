package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Geometries implements Intersectable { //look up composite design patterns, testing number of points
	
	private List<Intersectable> pointsList;

	/**
	 * default constructor for pointsList
	 */
	public Geometries() {
		//choosing arrayList because we'll be doing a lot of accessing in the list
		//and arrayList has O(1) access time
		pointsList = new ArrayList<Intersectable>();
	}
	
	/**
	 * constructor to fill in the empty pointsList arrayList
	 * @param geometries array of intersectable points
	 */
	public Geometries(Intersectable... geometries) {
		
		for (int i = 0; i < geometries.length; i++) {
			pointsList.add(geometries[i]);
		}
	}

	/**
	 * function to add a number of objects to the pointsList collection
	 * @param geometries array of intersectable points
	 */
	public void add(Intersectable... geometries) {
		
		for (int i = 0; i < geometries.length; i++) {
			pointsList.add(geometries[i]);
		}
	}
	
	public List<Point> findIntsersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
		//will use iterator behavioral design pattern
	}

}
