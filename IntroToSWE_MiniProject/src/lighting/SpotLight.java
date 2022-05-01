package lighting;
import primitives.*;

public class SpotLight extends PointLight {
	
	Vector direction;
	
	/**
	 * a constructor
	 * 
	 * @param color the Color of the light 
	 * @param dir the direction Vector the light is pointing in 
	 */
	public SpotLight(Color color, Vector dir) {
		super(color, dir);  //not sure how this works 
	}
	
	/**
	 * the light intensity at a point
	 */
	public Color getIntensity(Point p){
		double d = position.distance(p);
		double max = Math.max(0, direction.dotProduct(getL(p)));
		return getIntensity().scale(max/(kC+kL*d+kQ*d*d));
	}

}
