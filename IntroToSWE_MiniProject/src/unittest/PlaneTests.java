package unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Plane;
import primitives.Point;

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
}

