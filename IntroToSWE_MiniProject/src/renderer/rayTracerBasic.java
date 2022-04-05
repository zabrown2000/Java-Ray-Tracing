package renderer;
import java.awt.Color;
import elements.AmbientLight;
import geometries.*;
import primitives.*;
import scene.*;

public class rayTracerBasic extends RayTraceBase {
	
	/**
	 * constructor 
	 * @param scene Scene
	 */
	public rayTracerBasic(Scene scene) { super(scene);}
	
	/**
	 * override traceRay
	 */
	@Override
	public Color traceRay(Ray ray) {
		return null;
	}

}
