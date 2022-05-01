package lighting;
import primitives.*;

public class PointLight extends Light implements LightSource {
	
	protected Point position;
	protected double kC, kL, kQ;
	
	/**
	 * constructor
	 * 
	 * @param color the color of the light 
	 * @param pos the position point of the light
	 */
	public PointLight(Color color, Point pos) {
		super(color);
		this.position = pos;
		kC = 1; kL = 0; kQ = 0;
	}
	
	/**
	 * 
	 * @param kc the attenuation coefficient 
	 * @return the point light 
	 */
	public PointLight setkC(double kc) {
		this.kC = kc;
		return this;
	}
	
	/**
	 * 
	 * @param kl attenuation coefficient 
	 * @return the point light
	 */
	public PointLight setkL(double kl) {
		this.kL = kl;
		return this;
	}
	
	/**
	 * 
	 * @param kq attenuation coefficient 
	 * @return the pointLight
	 */
	public PointLight setkQ(double kq) {
		this.kQ = kq;
		return this;
	}
	
	/**
	 * the light intensity at a point
	 */
	public Color getIntensity(Point p){
		double d = position.distance(p);
		return getIntensity().scale(1/(kC+kL*d+kQ*d*d));
	}
	
	/**
	 * 
	 * @return vector from my light to the point 
	 */
	public Vector getL(Point p){
		return position.subtract(p);
	}
		

}
