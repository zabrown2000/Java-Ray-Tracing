package unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;
import renderer.Camera;
import geometries.*;

class GeoCameraIntegrationTests {
	static final Point ZERO_POINT = new Point(0, 0, 0);

	@Test
	void testSphereCamera() {
		
		//TC01: First test case - 2 points                                             
		Camera c1 = new Camera(ZERO_POINT, new Vector(0,-1,0), new Vector(0,0,-1)); 
		Sphere s1 = new Sphere(new Point(0,0,-3), 1);
		int intersections = 0;
		for (int i = -1; i < 2; i++) { //y coord
			//(j,i,-1) - j:-1,0,1 i:-1x3, 0x3, 1x3
			for (int j = -1; j < 2; j++) { //x coord
				Point p = new Point((double)j,(double)i,-1.0);
				Vector v = p.subtract(c1.getP0());
				Ray r = new Ray(c1.getP0(), v);
				if (s1.findIntsersections(r) != null) {
					intersections += s1.findIntsersections(r).size();
				}
			}
		}
		assertEquals(2, intersections, "ERROR: Expected 2 points");
		
		//TC02: Second test case - 18 points
		Camera c2 = new Camera(new Point(0,0,0.5), new Vector(0,-1,0), new Vector(0,0,-1)); //need to change?
		Sphere s2 = new Sphere(new Point(0,0,-2.5), 2.5);
		intersections = 0;
		for (int i = -1; i < 2; i++) { //y coord
			//(j,i,-1.5) - j:-1,0,1 i:-1x3, 0x3, 1x3
			for (int j = -1; j < 2; j++) { //x coord
				Point p = new Point((double)j,(double)i,-1.5);
				Vector v = p.subtract(c2.getP0());
				Ray r = new Ray(c2.getP0(), v);
				if (s2.findIntsersections(r) != null) {
					intersections += s2.findIntsersections(r).size();
				}
			}
		}
		assertEquals(18, intersections, "ERROR: Expected 18 points");
		
		//TC03: Third test case - 10 points
		Sphere s3 = new Sphere(new Point(0,0,-2), 2);
		intersections = 0;
		for (int i = -1; i < 2; i++) { //y coord
			//(j,i,-1.5) - j:-1,0,1 i:-1x3, 0x3, 1x3
			for (int j = -1; j < 2; j++) { //x coord
				Point p = new Point((double)j,(double)i,-1.5);
				Vector v = p.subtract(c2.getP0());
				Ray r = new Ray(c2.getP0(), v);
				if (s3.findIntsersections(r) != null) {
					intersections += s3.findIntsersections(r).size();
				}
			}
		}
		assertEquals(10, intersections, "ERROR: Expected 10 points");
		
		//TC04: Fourth test case - 9 points
		Sphere s4 = new Sphere(new Point(0,0,-1.5), 4);
		intersections = 0;
		for (int i = -1; i < 2; i++) { //y coord
			//(j,i,-1.5) - j:-1,0,1 i:-1x3, 0x3, 1x3
			for (int j = -1; j < 2; j++) { //x coord
				Point p = new Point((double)j,(double)i,-1.5);
				Vector v = p.subtract(c2.getP0());
				Ray r = new Ray(c2.getP0(), v);
				if (s4.findIntsersections(r) != null) {
					intersections += s4.findIntsersections(r).size();
				}
			}
		}
		assertEquals(9, intersections, "ERROR: Expected 9 points");
		
		//TC05: Fifth test case - 0 points
		Sphere s5 = new Sphere(new Point(0,0,1), 0.5);
		intersections = 0;
		for (int i = -1; i < 2; i++) { //y coord
			//(j,i,-1.5) - j:-1,0,1 i:-1x3, 0x3, 1x3
			for (int j = -1; j < 2; j++) { //x coord
				Point p = new Point((double)j,(double)i,-1.5);
				Vector v = p.subtract(c2.getP0());
				Ray r = new Ray(c2.getP0(), v);
				if (s5.findIntsersections(r) != null) {
					intersections += s5.findIntsersections(r).size();
				}
			}
		}
		assertEquals(0, intersections, "ERROR: Expected 0 points");
	}
	
	@Test
	void testTrangleCamera() {
		fail("Not yet implemented");
	}

	@Test
	void testPlaneCamera() {
		fail("Not yet implemented");
	}
}
