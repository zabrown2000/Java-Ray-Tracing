package unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import geometries.*;
import primitives.*;

class TubeTests {

	/**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}
     */
	@Test
	public void testGetNormal() {
		
	// ============ Equivalence Partitions Tests ==============
	Point p0 = new Point(3,3,0);
	Point p = new Point(1,1,1);
	Vector v = new Vector(2,2,0);
	Ray r1 = new Ray(p0,v);
	Tube t = new Tube(1, r1);
	Vector ans1 = new Vector(1/Math.sqrt(2),1/Math.sqrt(2),0);
	
	assertEquals(ans1,t.getNormal(p), "Error: sphere getNoraml");

	// =============== Boundary Values Tests ==================
	// TC01: check the getNormal function for a Tube when the connection between the point on the body and the ray’s head creates a 90 degrees with the ray ;
	Point p1 = new Point(2,0,0);
	Point p2 = new Point(1,0,1);
	Vector v1 = new Vector(1,0,0);
	Ray r2 = new Ray(p1, v1);
	Tube t1 = new Tube(1, r2);	
	Vector ans2 = new Vector(0,0,1);
	
	/*Point p0 = new Point(3,0,0);
	Point p = new Point(1,1,0);
	Vector v = new Vector(2,0,0);
	Ray r1 = new Ray(p0,v);
	Tube t = new Tube(1, r1);
	Vector ans1 = new Vector(0,1,0);*/
	
	
	assertEquals(ans1,t.getNormal(p), "Error: sphere getNoraml");

	
	assertEquals(ans2,t1.getNormal(p2), "Error: sphere getNoraml");
	
	}
	

}
