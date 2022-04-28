package renderer;
import java.awt.Color;
import java.util.List;


import lighting.AmbientLight;
import geometries.*;
import lighting.AmbientLight;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.*;

public class RayTracerBasic extends RayTraceBase {
	
	/**
	 * constructor 
	 * @param scene Scene
	 */
	public RayTracerBasic(Scene scene) { super(scene);}
	
	
	@Override
	/**
	 * Function to trace a ray to get its color
	 * @param ray the ray to be traced
	 * @return the color
	 */
	public primitives.Color traceRay(Ray ray) {
		List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(ray);
		/*Current issue: at this point in the class the scene's triangles have color, but once
		 * go down into the findgeointersectionshelper functions, the emission defaults to black,
		 * but not sure why or how it happens*/
		if (intersections == null) {
			return this.scene.background;
		} else {
			GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
			return this.calcColor(closestPoint);
		}
	}

	/**
	 * Function to get the color of a specific point
	 * @param point point to get color of
	 * @return the color of the point
	 */
	private primitives.Color calcColor(GeoPoint gp) {
		//primitives.Color amb = this.scene.ambientLight.getIntensity();
		//primitives.Color em = gp.geometry.getEmission();
		//return em.add(amb);
		//return gp.geometry.getEmission().add(this.scene.ambientLight.getIntensity());
		return this.scene.ambientLight.getIntensity().add(gp.geometry.getEmission()); //problem: not adding emission to ambient
	}
	//to add the object’s color to the point’s color
}
