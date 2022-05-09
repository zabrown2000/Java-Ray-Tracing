package lighting;
import primitives.*;
import static java.awt.Color.*;

public abstract class Light{
	
	private primitives.Color intensity;
	
	/**
	 * light constructor 
	 * 
	 * @param color returns the intensity of the light 
	 */
	protected Light(primitives.Color color) {
		this.intensity = color;
	}

	/**
	 * 
	 * @return the intensity 
	 */
	public primitives.Color getIntensity() {
		return intensity;
	}


	
	

}
