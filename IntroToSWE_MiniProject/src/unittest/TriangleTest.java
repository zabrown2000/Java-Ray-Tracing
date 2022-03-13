package unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import geometries.*;
import primitives.*;

class TriangleTest {

	@Test
	void testFindIntersection() {
		
		Point p1 = new Point(1,0,0);
		Point p2 = new Point(3,0,0);
		Point p3 = new Point(2,1,0);
		
		
		Triangle t1 = new Triangle(p1,p2,p3);
		
		// ============ Equivalence Partitions Tests ==============
		//TC01: outside opposite a side of the triangle
		Ray r1 = new Ray(new Point(0,0,0), new Vector(1,1,0));
		Point p4 = new Point(2,0.5,0);
		assertEquals(p4, t1.findIntersection(r1), "Not intersect inside the triangle "),
		
		
		//TC02: outside opposite a corner of a triangle 
		Point p5 = new Point(2,0.5,0);
		assertEquals(p5, t1.findIntersection(r1), "Not intersect inside the triangle "),
		
		
		//TC03: inside a triangle
		Point p6 = new Point(2,0.5,0);
		assertEquals(p6, t1.findIntersection(r1), "Not intersect inside the triangle "),
				
		
		// =============== Boundary Values Tests ==================
		//TC11: On the side of a triangle
		//TC12: On the corner of a triangle
		//TC13: Extension of the line of a triangle

	}

}
