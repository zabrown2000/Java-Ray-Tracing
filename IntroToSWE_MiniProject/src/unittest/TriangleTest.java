package unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import geometries.*;
import primitives.*;

class TriangleTest {

	/**
	 * Test method for {@link geometries.Triangle#findIntsersections(primitives.Ray)}.
	 */
	@Test
	void testFindIntersection() {
		
		Point p1 = new Point(1,0,0);
		Point p2 = new Point(3,0,0);
		Point p3 = new Point(2,1,0);
		
		
		Triangle t1 = new Triangle(p1,p2,p3);
		
		// ============ Equivalence Partitions Tests ==============
		//TC01: outside opposite a side of the triangle
		Ray r1 = new Ray(new Point(1.53,0.53,2), new Vector(0.41,-1.16,-2));
		Point p4 = new Point(1.94,-0.63,0); //point opposite side
		assertEquals(null, t1.findIntsersections(r1), "ERROR: Not intersect inside the triangle ");
		
		
		//TC02: outside opposite a corner of a triangle 
		Point p5 = new Point(2,1.41,0); //point opposite corner
		Ray r6 = new Ray(new Point(1.53,0.53,2), new Vector(0.47,0.88,-2));
		assertEquals(null, t1.findIntsersections(r6), "ERROR: Not intersect inside the triangle ");
		
		
		//TC03: inside a triangle
		Point p6 = new Point(2,0.5,0);
		Ray r5 = new Ray(new Point(1.53,0.53,2), new Vector(0.47,-0.03,-2));
		assertEquals(p6, t1.findIntsersections(r5).get(0), "ERROR: Not intersect inside the triangle ");
				
		
		// =============== Boundary Values Tests ==================
		//TC11: On the side of a triangle
		Point p7 = new Point(1.53,0.53,0);
		Ray r2 = new Ray(new Point(1.53,0.53,2), new Vector(0,0,-2));
		//assertEquals(p7, t1.findIntsersections(r2).get(0), "ERROR: Not intersect side of triangle");
		
		//TC12: On the corner of a triangle
		Ray r3 = new Ray(new Point(1.53,0.53,2), new Vector(0.47,0.47,-2));
		//assertEquals(p3, t1.findIntsersections(r3).get(0), "ERROR: Not intersect corner of triangle");
		
		//TC13: Extension of the line of a triangle
		Point p8 = new Point(3.55,0,0); //point on line of triangle
		Ray r4 = new Ray(new Point(1.53,0.53,2), new Vector(2.02, -0.53,-2));
		assertEquals(null, t1.findIntsersections(r4), "ERROR: Not intersect line of triangle");
		

	}

}
