package lighting;
import primitives.*;

public class DirectionalLight extends Light implements LightSource {
	
	private Vector direction;
	
	/**
	 * constructor 
	 * 
	 * @param color the color of the light 
	 * @param dir the direction vector the light is pointing in 
	 */
	public DirectionalLight(Color color, Vector dir) {
		super(color);
		this.direction = dir;
	}
	
	/**
	 * 
	 * @return the direction vector
	 */
	public Vector getL(Point p){
		return direction;
	}
	
	/**
	 * @return the color of the light 
	 */
	public Color getIntensity(Point p) {
		return getIntensity();
	}

	@Override
	public double getDistance(Point point) {
		
		return Double.POSITIVE_INFINITY;
	}
	
	
	

}
