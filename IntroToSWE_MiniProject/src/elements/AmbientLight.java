package elements;
import primitives.*;


public class AmbientLight {

	private
	Color intensity;
	
	
	public 
	
	/**
	 * constructor that calculates Intensity by scaling IA by kA
	 * 
	 * @param IA  A parameter of type Color – It will be the original color of the light 
	 * @param kA  A parameter of type Double3 which will contain the attenuation factor of the original light 
	 */
	AmbientLight(Color IA, Double3 kA){
		intensity = IA.scale(kA);
		
	}
	
	/**
	 * default constructor that sets the intensity to black
	 */
	AmbientLight(){
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
