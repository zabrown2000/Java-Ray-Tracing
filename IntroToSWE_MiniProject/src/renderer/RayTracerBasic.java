package renderer;
import java.awt.Color;
import java.util.List;

import geometries.*;
import lighting.AmbientLight;
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
		List<Point> intersections = this.scene.geometries.findIntsersections(ray);
		if (intersections == null) {
			return this.scene.background;
		} else {
			Point closestPoint = ray.findClosestPoint(intersections);
			return this.calcColor(closestPoint);
		}
	}

	/**
	 * Function to get the color of a specific point
	 * @param point point to get color of
	 * @return the color of the point
	 */
	public primitives.Color calcColor(Point point) {
		return scene.ambientLight.getIntensity();
	}
	
}
