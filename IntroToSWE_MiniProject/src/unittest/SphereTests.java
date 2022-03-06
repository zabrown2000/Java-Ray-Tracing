package unittest;

import static org.junit.jupiter.api.Assertions.*;
import geometries.*;
import primitives.*;


import org.junit.jupiter.api.Test;

class SphereTests {

	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============

		// TC01: check the getNormal function for a sphere;
		Point p0 = new Point(0,0,0);
		Sphere s1 = new Sphere(p0, 2);
		Vector v1 = new Vector(2,2,2);
		Vector v2 = new Vector(Math.sqrt(3),Math.sqrt(3),Math.sqrt(3));
		
		assertEquals(s1.getNormal(v1), v2, "Error: sphere getNoraml");
		
		// =============== Boundary Values Tests ==================
		// TC02: test that the bound lies on the boundry of the shpere
		assertThrows(IllegalArgumentException.class, ()->s1.getNormal(p0), "ERROR: vector is not on the boundry");
	}
	

}
