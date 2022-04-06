package renderer;
import java.awt.Color;
import elements.AmbientLight;
import geometries.*;
import primitives.*;
import scene.*;

public class RayTracerBasic extends RayTraceBase {
	
	/**
	 * constructor 
	 * @param scene Scene
	 */
	public RayTracerBasic(Scene scene) { super(scene);}
	
	/**
	 * override traceRay
	 */
	@Override
	public primitives.Color traceRay(Ray ray) {
		return null;
	}

}
