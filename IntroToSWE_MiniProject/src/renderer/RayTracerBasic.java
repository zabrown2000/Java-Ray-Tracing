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
	
	private static final double DELTA = 0.1;
	
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
		Vector n = intersection.geometry.getNormal(intersection.point); 
		double nv = Util.alignZero(n.dotProduct(v)); 
		int nShininess = intersection.geometry.getMaterial().getNshininess();
		Double3 kd = intersection.geometry.getMaterial().kD;
		Double3 ks = intersection.geometry.getMaterial().kS;
		
		primitives.Color color = new primitives.Color(Color.BLACK);
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(intersection.point);
			double nl = Util.alignZero(n.dotProduct(l));
			if(nl*nv > 0 ) {
				if (unshaded(intersection, lightSource, l, n)) { //only getting light of point if not blocked by light
					primitives.Color lightIntensity = lightSource.getIntensity(intersection.point);
					color = color.add(calcDiffusive(kd,l,n,lightIntensity), calcSpecular(ks,l,n,v,nShininess,lightIntensity));
				}
			}
		}
		return color;
	}
	
	/**
	 * Function to check if a geo is blocking light source to current geo
	 * @param v vector to make shadow ray
	 * @param gp point on geometry
	 * @return if intersection list is empty
	 */
	private boolean unshaded(GeoPoint gp, LightSource ls, Vector l, Vector n) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Vector deltaV = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
		Point p = gp.point.add(deltaV);

		Ray lightRay = new Ray(p, lightDirection);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
		
		if (intersections == null) return true;
		
		double rayLightDistance = ls.getDistance(lightRay.p0);
		for (GeoPoint geopoint : intersections) {
			double rayIntersectionDistance = lightRay.p0.distance(geopoint.point);
			if (rayIntersectionDistance < rayLightDistance) return false;
		}
		return true;  //nothing in between geo and lightsource
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
		//return lightIntensity.scale(kd.scale(l.dotProduct(n)));
		double ln = Math.abs(l.dotProduct(n));
		return lightIntensity.scale(kd.scale(ln));
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
		//Vector normal = n.scale(-2*(l.dotProduct(n)));
		//Vector r = l.add(l,normal);
		//Double3 bracket = ks.scale(Math.max(0,-v.dotProduct(r)));
		//for( int i = 1; i < nShininess; i++) {
		//	bracket = bracket.product(bracket);
		//}
		//return lightIntensity.scale(bracket);
		
		Vector r = l.subtract(n.scale(2*(l.dotProduct(n))));
		double vrMinus = Math.max(0, v.scale(-1).dotProduct(r));
		double vrn =Math.pow(vrMinus,nShininess);
        return lightIntensity.scale(ks.scale(vrn));
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
		//primitives.Color em = gp.geometry.getEmission();	
		//primitives.Color emANDamb = scene.ambientLight.getIntensity().add(em);
		//return emANDamb.add(calcLocalEffects(gp, ray));
	}
}












