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
	public SpotLight(Color color, Point p, Vector dir) {
		super(color, p);  
		this.direction = dir;
	}
	
	/**
	 * the light intensity at a point
	 */
	public Color getIntensity(Point p){
		double d = position.distance(p); //if sign is wrong, swap these
		double max = Math.max(0, direction.dotProduct(getL(p)));
		return getIntensity().scale(max/(kC+kL*d+kQ*d*d));
	}

	public PointLight setNarrowBeam(int i) { //bonus
		// TODO Auto-generated method stub
		return this;
	}

}
