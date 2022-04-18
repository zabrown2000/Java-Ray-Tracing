package lighting;
import primitives.*;


public class AmbientLight {

	private primitives.Color intensity;
	
	
	/**
	 * default constructor that sets the intensity to black
	 * @param double3 
	 * @param color 
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
	public AmbientLight(Color iA, Double3 kA) {
		this.intensity =  iA.scale(kA);
	}

	/**
	 * 
	 * @return the intensity field
	 */
	public Color getIntensity() {
		return intensity;
	}



}
