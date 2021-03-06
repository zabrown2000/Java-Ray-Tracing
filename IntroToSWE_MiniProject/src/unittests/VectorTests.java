package unittests;

import primitives.*;

import static java.lang.System.out;
//import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static primitives.Util.*;

class VectorTests {
	
	 /**
     * Test method for {@link primitives.Vector#zeroVector(primitives.Vector)}.
     */
   @Test
    public void testZeroVector() {
     //=============== Boundary Values Tests ==================
     // TC01: test that an error messages in thrown when you use a zero vector
		assertThrows(IllegalArgumentException.class,()->new Vector(0,0,0),"ERROR: zero vector does not throw an exception" );
		//try {
	    //  v0;
		//	fail{"ERROR: zero vector does not throw an exception");
		//	}
		//	catch (Exception e) {}
		//}
    }

	/**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand");
        assertTrue( isZero(vr.dotProduct(v2)),"crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),"crossProduct() for parallel vectors does not throw an exception");
        // try {
        //     v1.crossProduct(v2);
        //     fail("crossProduct() for parallel vectors does not throw an exception");
        // } catch (Exception e) {}
    }
    
    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}
     */
    @Test
    public void testDotProduct() {
    	
    	Vector v1 = new Vector(1, 2, 3);
    	Vector v2 = new Vector(-2, -4, -6);
		Vector v3 = new Vector(0, 3, -2);
		
    	// ============ Equivalence Partitions Tests ==============
    	//TC01: Test dot product of orthogonal vectors returns 0
    	assertTrue(isZero(v1.dotProduct(v3)), "ERROR: dotProduct() for orthogonal vectors is not zero");
    	
    	//TC02: Test for general functionality of dot product
    	assertTrue(isZero(v1.dotProduct(v2) + 28), "ERROR: dotProduct() wrong value");
    }
    
    /**
     * Test method for {@link primitives.Vector#length()}
     */
    @Test
    public void testLength() {
    	
    	// ============ Equivalence Partitions Tests ==============
    	//TC01: Testing general functionality of length
    	assertTrue(isZero(new Vector(0, 3, 4).length() - 5), "ERROR: length() wrong value");
    }
    
    /**
     * Test method for {@link primitives.Vector#lengthSquared()}
     */
    @Test
    public void testLengthSquared() {
    	
    	Vector v1 = new Vector(1, 2, 3);
    	
    	// ============ Equivalence Partitions Tests ==============
    	//TC01: Testing general functionality of length squared
    	assertTrue(isZero(v1.lengthSquared() - 14), "ERROR: lengthSquared() wrong value");
    }
    
    /**
     * Test method for {@link primitives.Vector#normalize()}
     */
    @Test
    public void testNormalize() {
    	Vector v = new Vector(1, 2, 3);
    	Vector u = v.normalize(); 
    	
    	// ============ Equivalence Partitions Tests ==============
    	//TC01: Simple test
    	assertEquals(1d, u.lengthSquared(), 0.00001, "ERROR: the normalized vector is not a unit vector");
    	assertThrows(IllegalArgumentException.class, ()->v.crossProduct(u), "ERROR: the normalized vector is not parallel to the original one");
    	assertEquals(new Vector(0.2672612419124244, 0.5345224838248488, 0.8017837257372732), u, "ERROR: wrong normalized vector");
    	assertTrue(v.dotProduct(u) > 0, "ERROR: the normalized vector is opposite to the original one");
    }
    
   
    
    
}
