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
			return this.calcColor(closestPoint, ray);
		}
	}
	
	/**
	 * 
	 * @param intersection GeoPoints intersected by the ray 
	 * @param ray Ray 
	 * @return calculated light contribution from all the light sources
	 */
	private primitives.Color calcLocalEffects(GeoPoint intersection, Ray ray) {
		Vector v = ray.getDir();
		Vector n = intersection.geometry.getNormal(intersection.point); //not sure if that should be v or not
		double nv = Util.alignZero(n.dotProduct(v)); //not working not sure why 
		int nShininess = intersection.geometry.getMaterial().getNshininess();
		Double3 kd = intersection.geometry.getMaterial().kD;
		Double3 ks = intersection.geometry.getMaterial().kS;
		
		primitives.Color color = new primitives.Color(Color.BLACK);
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(intersection.point);
			double nl = Util.alignZero(n.dotProduct(l));
			if(nl*nv > 0 ) {
				primitives.Color lightIntensity = lightSource.getIntensity(intersection.point);
				color = color.add(calcDiffusive(kd,l,n,lightIntensity), calcSpecular(ks,l,n,v,nShininess,lightIntensity));
			}
		}
		return color;
	}
	
	/**
	 * calculates the diffuse 
	 * 
	 * @param kd Double3 
	 * @param l Vector 
	 * @param n Vector 
	 * @param lightIntensity primitives.Color
	 * @return the diffuse color primitives.Color 
	 */
	private primitives.Color calcDiffusive(Double3 kd, Vector l, Vector n, primitives.Color lightIntensity){
		return lightIntensity.scale(kd.scale(l.dotProduct(n)));
	}
	
	/**
	 * calculates specular light 
	 * 
	 * @param ks Double3 constant 
	 * @param l Vector from light 
	 * @param n Vector normal off surface 
	 * @param v Vector from camera
	 * @param nShininess
	 * @param lightIntensity
	 * @return specular light primitives.Color
	 */
	private primitives.Color calcSpecular(Double3 ks, Vector l, Vector n, Vector v, int nShininess, primitives.Color lightIntensity){
		Vector normal = n.scale(-2*(l.dotProduct(n)));
		Vector r = l.add(l,normal);
		Double3 bracket = ks.scale(Math.max(0,-v.dotProduct(r)));
		for( int i = 1; i < nShininess; i++) {
			bracket = bracket.product(bracket);
		}
		return lightIntensity.scale(bracket);
	}

	/**
	 * Function to get the color of a specific point
	 * @param point point to get color of
	 * @return the color of the point
	 */
	private primitives.Color calcColor(GeoPoint gp, Ray ray) {
		//primitives.Color amb = this.scene.ambientLight.getIntensity();
		//primitives.Color em = gp.geometry.getEmission();
		//return em.add(amb);
		//return gp.geometry.getEmission().add(this.scene.ambientLight.getIntensity());
		return this.scene.ambientLight.getIntensity()
				.add(gp.geometry.getEmission())
				.add(calcLocalEffects(gp,ray));
	}
	//to add the object’s color to the point’s color
}
