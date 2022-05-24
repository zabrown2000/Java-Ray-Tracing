package renderer;
import java.awt.Color;
import java.util.List;


import lighting.AmbientLight;
import lighting.LightSource;
import geometries.*;
import lighting.AmbientLight;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.*;
import geometries.*;

public class RayTracerSuperSampling extends RayTraceBase {
	
	/**
	 * constructor 
	 * @param scene Scene
	 */
	public RayTracerSuperSampling(Scene scene) { super(scene);}

	
	@Override
	public primitives.Color traceRay(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private List<Ray> shootMultipleReflectiveRays(Ray ray) {
		Ray relective = constructReflectedRay(ray);
		List<Ray> multipleRays = calcVectors(relective);
		return multipleRays;
		
	}
	
	private List<Ray> shootMultipleRefractoredRays(Ray ray) {
		Ray relective = constructRefractoredRay(ray);
		List<Ray> multipleRays = calcRayVectors(relective);
		return multipleRays;
		
	}
	
	private List<Ray> calcRayVectors(Ray ray){
		List<Ray> multipleRays =  new List<Ray>;
		for( int i = 0; i < 80; i++) {
			
		}
	}
}
