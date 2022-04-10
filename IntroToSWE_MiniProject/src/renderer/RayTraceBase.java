package renderer;
import java.awt.Color;

import geometries.*;
import lighting.AmbientLight;
import primitives.*;
import scene.*;

public abstract class RayTraceBase {
	
	protected Scene scene;
	
	/**
	 * a constructor that receives a scene as a parameter 
	 * @param scene Scene
	 */
	public RayTraceBase(Scene scene) {
		this.scene = scene;
	}
	
	/**
	 * 
	 * @param ray Ray being shot
	 * @return the Color on the point that the ray hit
	 */
	public abstract primitives.Color traceRay(Ray ray);
		
	

}
