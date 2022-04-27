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
		return scene.ambientLight.getIntensity()
				.add(gp.geometry.getEmission());
	}
	
}
