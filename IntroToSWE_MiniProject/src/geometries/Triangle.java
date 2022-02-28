package geometries;

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

	@Override
	public String toString() {
		return "Triangle [vertices=" + vertices + ", plane=" + plane + "]"; //need to iterate through list?
	}
	
	
	
}

