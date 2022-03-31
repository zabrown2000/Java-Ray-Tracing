/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

//import org.junit.jupiter.api.Test;

/**
 * @author gabibondi
 *
 */
class PointTests {

	/**
     * Test method for {@link primitives.Point#add#subtract(primitives.Point)}.
     */
    @Test
    public void testPointAndVector() {
    	// ============ Equivalence Partitions Tests ==============
    	Point p1 = new Point(1, 2, 3);
 		Vector v1 = new Vector(-1, -2, -3);
 		Point p2 = new Point(0, 0, 0);
 		//TC01: test that you can add Points and Vectors 
 		assertEquals((p1.add(v1)), p2, "ERROR: Point + Vector does not work correctly");
 		
 		Vector v2 = new Vector(1, 1, 1);
 		Point p3 = new Point(2, 3, 4);
 		//TC01: test that you can subtract Points and Vectors
 		assertEquals(v2, p3.subtract(p1), "ERROR: Point - Point does not work correctly");

    }
}
