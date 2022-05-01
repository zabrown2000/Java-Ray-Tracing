package lighting;
import primitives.*;
import static java.awt.Color.*;

public abstract class Light{
	
	private Color intensity;
	
	/**
	 * light constructor 
	 * 
	 * @param color returns the intensity of the light 
	 */
	protected Light(Color color) {
		this.intensity = color;
	}

	/**
	 * 
	 * @return the intensity 
	 */
	public Color getIntensity() {
		return intensity;
	}


	
	

}
