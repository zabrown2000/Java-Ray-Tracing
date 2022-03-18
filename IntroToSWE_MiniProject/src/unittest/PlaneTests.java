package unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;
import java.util.List;
import java.util.ArrayList;

class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		Plane p1 = new Plane(new Point(1,0,2), new Point(5,2,1), new Point(0,0,1));
		assertEquals(1d, p1.normal.length(), "Error: Plane get normal");
	}

	/**
	 * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
	 */
	@Test
	void testPlane() {
		// =============== Boundary Values Tests ==================
		//TC01: The first and second points are equal.
		assertThrows(IllegalArgumentException.class,()->new Plane(new Point(1,0,2), new Point(1,0,2), new Point(0,0,1)),
				"ERROR: Plane with a zero normal vector doesn't throw an exception");
		
		//TC11: The points are all on same line
		assertThrows(IllegalArgumentException.class,()->new Plane(new Point(1,2,3), new Point(2,4,6), new Point(3,6,9)),
				"ERROR: Plane with a zero normal vector doesn't throw an exception");
	}
	
	
	@Test
	void testFindIntersection() {
		
		
		// ============ Equivalence Partitions Tests ==============
		
		//TC01: Ray intersects the plane 
		Plane plane1 = new Plane(new Point(1,1,0), new Vector(1,1,1));
		Ray ray1 = new Ray(new Point (0,0,1), new Vector (1,1,0));
		Point p1 = new Point(0.5,0.5,1);
		List<Point> list1 = new ArrayList<>();
		list1.add(p1);
		assertEquals(list1, plane1.findIntsersections(ray1), "Ray does not interset the plane");
		
		//TC02: Ray does not intersect the plane
		Plane plane2 = new Plane(new Point(0,0,0), new Vector(0,0,1));
		Ray ray2 = new Ray(new Point (0,0,1), new Vector (0,2,2));
		assertNull( plane2.findIntsersections(ray2),"Ray's line out of sphere");

		
		// =============== Boundary Values Tests ==================
		
		//TC11: Ray is parallel to the plane including the plane
		Plane plane3 = new Plane(new Point(1,1,0), new Vector(1,1,1));
		Ray ray3 = new Ray(new Point (1,0,0), new Vector (2,2,0));
		Point p3 = new Point(1.5,0.5,0);
		List<Point> list3 = new ArrayList<>();
		list3.add(p3);
		assertEquals(list3, plane3.findIntsersections(ray3), "Ray does not interset the plane");
		
		
		//TC12: Ray parallel to the plane excluding the plane
		Ray ray4 = new Ray(new Point (0,0,4), new Vector (0,4,4));
	    assertNull( plane1.findIntsersections(ray4),"Ray's parallel to the plane excluding the plane ");

		
		//TC13: orthogonal to the plane before the plane
		//interpreting before below the plane as the plane is infinitly long and wide 
		Ray ray5 = new Ray(new Point(1,1,-1), new Vector(1,1,0));
		Point p5 = new Point (1.5,1.5,-1);
		List<Point> list5 = new ArrayList<>();
		list5.add(p5);
		assertEquals(list5, plane3.findIntsersections(ray5), "Ray orthogonal to the plane before the plane");
		
		//TC14: orthogonal to the plane in the plan
		//ray starts on the plane 
		assertEquals(list5, plane3.findIntsersections(ray5), "Ray orthogonal to the plane in the plane");
				
		//TC15: orthogonal to the plane after the plan
		// ray starts below after the plane and therefore should not intersect 
		Ray ray6 = new Ray (new Point (1,1,4), new Vector (1,1, 6));
		assertNull(plane3.findIntsersections(ray6), "Ray orthogonal to the plane after the plan");
	
		
		//TC16: Ray begins at the plane
		Ray ray8 = new Ray(new Point (0.5,0.5,0), new Vector (0.5,0.5,0.5));
		Point p8 = new Point(0.8333333333333334,0.8333333333333334,0.33333333333333337);
		List<Point> list8 = new ArrayList<>();
		list8.add(p8);
		assertEquals(list8, plane3.findIntsersections(ray8), "Ray start on the plane");
		
		//TC17: Ray begins at the same point with is a reference point in the plane 
		Ray ray9 = new Ray(new Point (1,1,0), new Vector (6,6,1));
		 assertThrows(IllegalArgumentException.class, () -> plane3.findIntsersections(ray9),"Ray begins at the same point with is a reference point in the plane ");
		
	}
}

