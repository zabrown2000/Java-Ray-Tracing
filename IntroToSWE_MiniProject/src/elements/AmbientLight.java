package elements;
import primitives.*;


public class AmbientLight {

	private Color intensity;
	
	 
	
	/**
	 * constructor that calculates Intensity by scaling IA by kA
	 * 
	 * @param IA  A parameter of type Color – It will be the original color of the light 
	 * @param kA  A parameter of type Double3 which will contain the attenuation factor of the original light 
	 */
	public AmbientLight(Color iA, Double3 kA){
		intensity =  iA.scale(kA);
	}
	
	/**
	 * default constructor that sets the intensity to black
	 */
	public AmbientLight(){
		intensity = Color.BLACK;
	}


	/**
	 * 
	 * @return the intensity field
	 */
	public Color getIntensity() {
		return intensity;
	}



}
