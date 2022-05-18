package lighting;
import primitives.*;
import static java.awt.Color.*;


public class AmbientLight extends Light {

	/**
	 * default constructor that sets the intensity to black
	 * 
	 */
	public AmbientLight(){
		super(new Color(BLACK));
	}

	

	/**
	 * constructor that calculates Intensity by scaling IA by kA calling the light constructor 
	 * 
	 * @param iA  A parameter of type Color – It will be the original color of the light 
	 * @param kA  A parameter of type Double3 which will contain the attenuation factor of the original light 
	 */
	public AmbientLight(Color iA, Double3 kA) {
		super( iA.scale(kA));
		
	}



}
