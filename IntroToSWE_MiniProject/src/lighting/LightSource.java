package lighting;
import primitives.*;
import static java.awt.Color.*;

public interface LightSource {
	
	/**
	 * Calculates and returns the Color of the object at the point p that the light hits 
	 * 
	 * @param p Point that the light hits 
	 * @return the Color of the object 
	 */
	public Color getIntensity(Point p);
	
	/**
	 * Calculates and returns the Vector from the light to the object 
	 * 
	 * @param p Point that the light hits 
	 * @return Vector from the light to the point on the object 
	 */
	public  Vector getL(Point p);
	
	/**
	 * Function to get the distance between a given point and the light source
	 * @param point
	 * @return
	 */
	public double getDistance(Point point);
}
