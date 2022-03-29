package unittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.*;
import renderer.Camera;
import geometries.*;

class GeoCameraIntegrationTests {
	static final Point ZERO_POINT = new Point(0, 0, 0);

	/**
	 * helper function to calculate intersection points for each of 9 rays with a given geometry
	 * @param c camera
	 * @param g geometry
	 * @param intersections expected value for assert function
	 * @param assertMessage error message to send if assert fails
	 */
	private void checkIntersections(Camera c, Geometry g, int intersections, String assertMessage) {
		c.setVPSize(3,3).setVPDistance(1);                 //switch i and j?
		int count = 0;
		for (int i = 0; i < 3; i++) { //y coord
			for (int j = 0; j < 3; j++) { //x coord
				Ray r = c.constructRay(3, 3, j, i);
				if (g.findIntsersections(r) != null) {
					count += g.findIntsersections(r).size();
				}
			}
		}
		assertEquals(intersections, count, assertMessage);
	}
	
	/**
     * integration tests for constructing a ray through a pixel with a sphere
     * {@link renderer.Camera#constructRay(int, int, int, int)}.
     */
	@Test
	void testSphereCamera() {
													
		//TC01: Unit sphere in center of view plane- 2 points                                             
		Camera c1 = new Camera(ZERO_POINT, new Vector(0,-1,0), new Vector(0,0,-1)); 
		//Camera c1 = new Camera(ZERO_POINT, new Vector(0,-1,0), new Vector(0,0,1)));
		Sphere s1 = new Sphere(new Point(0,0,-3), 1);
		checkIntersections(c1, s1, 2, "ERROR Sphere TC01: Expected 2 points");
		
		//TC02: Sphere larger than view plane - 18 points
		Camera c2 = new Camera(new Point(0,0,0.5), new Vector(0,-1,0), new Vector(0,0,-1)); //need to change?
		//Camera c2 = new Camera(new Point(0,0,-0.5), new Vector(0,-1,0), new Vector(0,0,1)));
		Sphere s2 = new Sphere(new Point(0,0,-2.5), 2.5);
		checkIntersections(c2, s2, 18, "ERROR Sphere TC02: Expected 18 points");
		
		//TC03: Sphere slightly intersecting view plane - 10 points
		Sphere s3 = new Sphere(new Point(0,0,-2), 2);
		checkIntersections(c2, s3, 10, "ERROR Sphere TC03: Expected 10 points");
		
		//TC04: Sphere with view plane in midlle of it - 9 points
		Sphere s4 = new Sphere(new Point(0,0,-1.5), 4);
		checkIntersections(c2, s4, 9, "ERROR Sphere TC04: Expected 9 points");
		
		//TC05: Sphere behind view plane - 0 points
		Sphere s5 = new Sphere(new Point(0,0,1), 0.5);
		checkIntersections(c2, s5, 0, "ERROR Sphere TC05: Expected 0 points");
	}
	
	/**
     * integration tests for constructing a ray through a pixel with a triangle
     * {@link renderer.Camera#constructRay(int, int, int, int)}.
     */
	@Test
	void testTrangleCamera() {
		Camera c = new Camera(ZERO_POINT, new Vector(0,-1,0), new Vector(0,0,-1));
		
		//TC01: small triangle in front of view plane - 1 point
		Triangle t1 = new Triangle(new Point(1,1,-2), new Point(-1,1,-2), new Point(0,-1,-2));
		checkIntersections(c, t1, 1, "ERROR Triangle TC01: Expected 1 point");
		
		//TC02: tall triangle - 2 points
		Triangle t2 = new Triangle(new Point(1,1,-2), new Point(-1,1,-2), new Point(0,-20,-2));
		checkIntersections(c, t2, 2, "ERROR Triangle TC02: Expected 2 points");
		
		//TC03: triangle behind view plane - 0 points
		Triangle t3 = new Triangle(new Point(1,1,2), new Point(-1,1,2), new Point(0,-20,2));
		checkIntersections(c, t3, 0, "ERROR Triangle TC03: Expected 0 points");
		
	}

	/**
     * integration tests for constructing a ray through a pixel with a plane
     * {@link renderer.Camera#constructRay(int, int, int, int)}.
     */
	@Test
	void testPlaneCamera() {
		Camera c = new Camera(ZERO_POINT, new Vector(0,-1,0), new Vector(0,0,-1));
		
		//TC01: Plane parallel to view plane - 9 points
		Plane p1 = new Plane(new Point(0,0,-5), new Vector(0,0,1));
		checkIntersections(c, p1, 9, "ERROR Plane TC01: Expected 9 points");
		
		//TC02: Plane slightly slanted against view plane - 9 points
		Plane p2 = new Plane(new Point(0,0,-5), new Vector(0,1,2));
		checkIntersections(c, p2, 9, "ERROR Plane TC02: Expected 9 points");
		
		//TC03: Plane very slanted against view plane - 6 points
		Plane p3 = new Plane(new Point(0,0,-5), new Vector(0,1,1));
		checkIntersections(c, p3, 6, "ERROR Plane TC03: Expected 6 points");
		
		//TC04: Plane behind view plane - 0 points
		Plane p4 = new Plane(new Point(1,1,2), new Point(-1,1,2), new Point(0,-10,2));
		checkIntersections(c, p4, 0, "ERROR Plane TC04: Expected 0 points");
		
	}
	
	
}
