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
		Point p3 = new Point(8,6,5);
		Sphere s1 = new Sphere(p0, Math.sqrt(57));
		Sphere s2 = new Sphere(p0, Math.sqrt(27));
		Vector v1 = new Vector(3,3,3);
		Vector v2 = new Vector(1/Math.sqrt(3),1/Math.sqrt(3),1/Math.sqrt(3));
		Vector v3 = new Vector(4/Math.sqrt(57),4/Math.sqrt(57),5/Math.sqrt(57));
		Vector v4 = new Vector(4,4,5);
		
		assertEquals(v2,s2.getNormal(v1), "Error: sphere getNormal");
		assertEquals(v3,s1.getNormal(v4), "Error: sphere getNormal");
		
		// =============== Boundary Values Tests ==================
		// TC02: test that the bound lies on the boundry of the sphere
		//assertThrows(IllegalArgumentException.class, ()->s1.getNormal(p3), "ERROR: vector is not on the boundry");
	}
	

}
