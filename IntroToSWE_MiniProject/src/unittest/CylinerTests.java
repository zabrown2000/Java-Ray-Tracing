package unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Cylinder;
import geometries.Tube;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

class CylinerTests {

	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		
		// ============ Equivalence Partitions Tests ==============
		//TC01: normal is on the side
		Point p1 = new Point(2,0,0);
		Point p2 = new Point(1,0,1);
		Vector v = new Vector(1,0,0);
		Ray r = new Ray(p1, v);
		Cylinder c1 = new Cylinder(1, r, 1);	
		Vector correctNorm = new Vector(0,0,1);
		assertEquals(correctNorm, c1.getNormal(p2), "Error: Cylinder getNoraml on side");
		
		//TC02: normal is on bottom base    normal at base is in same direction as vector of ray or opposite it
		
		//if p2 is on base (distance from p1 <= radius), need to check parallel
		
		p1 = new Point(2,0,0);
		p2 = new Point(1,0,1);
		v = new Vector(1,0,0);
		r = new Ray(p1, v); //need to make ray on base
		c1 = new Cylinder(1, r, 1);	
		correctNorm = new Vector(0,0,1);
		assertEquals(correctNorm, c1.getNormal(p2), "Error: Cylinder getNoraml on side");
		
		//TC03: normal is on top base
		
		//top, just do like bottom, but 
		
		
		// =============== Boundary Values Tests ==================
		//TC11: normal is on center of bottom base
		
		
		//TC12: normal is on center of top base
	}

}
/*•	For those who create a getNormal for finite cylinder – 
 * there are 3 equivalence tests (on the side and on each one of the bases) and 
 * two boundaries – the center of the bases. (There is no need to check points on 
 * the connection between bases and side of tube, since no points like this will be created in the findIntersections method).*/
