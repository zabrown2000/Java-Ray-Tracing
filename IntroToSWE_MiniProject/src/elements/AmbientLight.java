package elements;
import primitives.*;


public class AmbientLight {

	private Color intensity;
	
	
	/**
	 * default constructor that sets the intensity to black
	 */
	public AmbientLight(){
		this.intensity = Color.BLACK;
	}

	

	/**
	 * constructor that calculates Intensity by scaling IA by kA
	 * 
	 * @param iA  A parameter of type Color – It will be the original color of the light 
	 * @param kA  A parameter of type Double3 which will contain the attenuation factor of the original light 
	 */
	public AmbientLight(java.awt.Color iA, Double3 kA) {
		Color color = new Color(iA);
		this.intensity =  color.scale(kA);
	}

	/**
	 * 
	 * @return the intensity field
	 */
	public Color getIntensity() {
		return intensity;
	}



}
