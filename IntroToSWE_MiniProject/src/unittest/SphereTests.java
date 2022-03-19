package unittest;

import static org.junit.jupiter.api.Assertions.*;

import geometries.*;
import primitives.*;
import java.util.List;

import org.junit.jupiter.api.Test;

class SphereTests {

	/**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}
     */
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
	
    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point(1, 0, 0),1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),"Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntsersections(new Ray(new Point(-1, 0, 0),new Vector(3, 1, 0)));
        assertEquals( 2, result.size(),"Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Wrong number of points");

        // TC03: Ray starts inside the sphere (1 point)
        assertEquals(new Point(1,0,1), sphere.findIntsersections(new Ray(new Point(1, 0, 0),new Vector(1, 0, 1))),"ray starts inside the sphere"); 
       
        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntsersections(new Ray (new Point(6,6,6), new Vector(7,7,7))), "ray starts after the sphere");
       

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        assertEquals(new Point(1,0,1), sphere.findIntsersections(new Ray(new Point (2,0,0), new Vector(1,0,1))), "Ray starts at sphere and goes inside");
        
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Point(2,0,0), new Vector(2,0,1) )),"Ray starts at sphere and goes outside"); //double check might not work

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        // TC14: Ray starts at sphere and goes inside (1 points)
        // TC15: Ray starts inside (1 points)
        // TC16: Ray starts at the center (1 points)
        
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Point(1,1,0), new Vector(0,1,0) )), "Ray starts at sphere and goes outside");
        
        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Point(2,5,0), new Vector(1,5,0)))," Ray starts at sphere and goes outside");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        // TC20: Ray starts at the tangent point
        // TC21: Ray starts after the tangent point

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line

    }

	

}
