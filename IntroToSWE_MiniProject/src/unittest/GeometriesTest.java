package unittest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;

class GeometriesTest {

	/**
	 * Test method for {@link geometries.Geometries#findIntsersections(primitives.Ray)}.
	 */
	@Test
	void testFindIntsersections() {
		//List<Intersectable> geos = null;
		//geos.add(new Plane(new Point(0,1,-2), new Point(1,-1,2), new Point(-1,-1,2)));
		//geos.add(new Triangle(new Point(0,1,-2), new Point(1,-1,2), new Point(-1,-1,2)));
		//geos.add(new Sphere(new Point(0,0,1), 1)); 
		
		Plane p = new Plane(new Point(0,1,-2), new Point(1,-1,2), new Point(-1,-1,2));
		Triangle t = new Triangle(new Point(0,1,-2), new Point(1,-1,2), new Point(-1,-1,2));
		Sphere s = new Sphere(new Point(0,0,1), 1);
		
		Geometries group = new Geometries(p, t, s); 
		
		// ============ Equivalence Partitions Tests ==============
		//TC01: Some shapes but not all intersect
		//ray will intersect triangle and plane only -> 2 points
		Ray r1 = new Ray(new Point(0.5,1,-1), new Vector(-0.5,-3,1));
		assertEquals(2d, group.findIntsersections(r1).size(), "ERROR with some shapes intersecting");
		
		// =============== Boundary Values Tests ==================
		//TC11: Empty collection of shapes
		//will return null so length is 0
		//the geos list is empty - create new Geometry with default ctor and just use r1
		Geometries emptyGroup = new Geometries();
		assertEquals(0, emptyGroup.findIntsersections(r1).size(), "ERROR with empty collection of shapes");
		
		
		
		//TC12: No shape intersects with a body
		//will return null so length is 0
		Ray r2 = new Ray(new Point(0.5,1,-1), new Vector(-0.5,1.17,1));
		assertEquals(0, group.findIntsersections(r2).size(), "ERROR with no shapes intersecting");
		
		//TC13: Only 1 shape intersects
		//all but 1 geo returns null
		//ray intersects with sphere only -> 2 points
		Ray r3 = new Ray(new Point(0.5,1,-1), new Vector(-0.5,-1,5));
		assertEquals(2d, group.findIntsersections(r3).size(), "ERROR with one shape intersecting");
		
		
		//TC14: All shapes intersect
		//no geos return null -> 4 points
		Ray r4 = new Ray(new Point(0.5,1,-1), new Vector(-1.3,-2.35,3.7));
		assertEquals(4d, group.findIntsersections(r4).size(), "ERROR with all shapes intersecting");
	}

}

//model for tests: https://www.geogebra.org/3d/pzyj4su3