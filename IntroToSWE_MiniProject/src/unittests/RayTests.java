package unittests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import primitives.*;

class RayTests {

	/**
     * Test method for {@link primitives.ray#findClosestPoint(java.util.List<Point>)}.
     */
	@Test
	void testFindClosestPoint() {
		//initializing to arrayList because will do a lot of iterating, even though the adding would be faster with a linkedList
		
		Ray r = new Ray(new Point(1,-1,3), new Vector(-7.28, 3.48,-3));
		List<Point> pointList = new ArrayList<Point>();
		Point p1 = new Point(0,-2,5); //distance: 2.45
		Point p2 = new Point(1,2,2); //distance: 3.16
		Point p3 = new Point(1,-1,-2); //distance: 5
		Point p4 = new Point(4,1,1); //distance: 4.12
		
		
		// ============ Equivalence Partitions Tests ==============
		//TC01: the closest point to the ray’s head is found somewhere in the middle of the list
		pointList.add(p4);
		pointList.add(p1); //closest
		pointList.add(p3);
		pointList.add(p2);
		assertEquals(p1, r.findClosestPoint(pointList), "Error: closest point middle of list");
		
		// =============== Boundary Values Tests ==================
		//TC11: an empty list
		pointList.clear();
		assertEquals(null, r.findClosestPoint(pointList), "Error: empty list");
		
		//TC12: a list where the closest point is the first point in the list
		pointList.add(p1); //closest
		pointList.add(p4);
		pointList.add(p3);
		pointList.add(p2);
		assertEquals(p1, r.findClosestPoint(pointList), "Error: closest point beginning of list");
		
		//TC13: a list where the closest point is the last point in the list
		pointList.clear();
		pointList.add(p4);
		pointList.add(p3);
		pointList.add(p2);
		pointList.add(p1); //closest
		assertEquals(p1, r.findClosestPoint(pointList), "Error: closest point end of list");
		
	}

}

