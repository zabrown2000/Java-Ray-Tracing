package renderer;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*; 
import scene.Scene;

public class SuperSampling extends RayTraceBase{
	
	private static final double MIN_CALC_COLOR_K = 0.001; 
	private static final Double3 INITIAL_K = new Double3(1.0);
	
	private int maxCalcColorLevel; 
	private double halfDistance;
	private int superSamplingRays;
	public int setting; //1 = regular super sampling   2 = adaptive super sampling 
	
	/**
	 * setter for setting 
	 * @param n int that chooses the setting
	 * @return supersampling
	 */
	public SuperSampling setSetting(int n) {
		this.setting = n;
		return this;
	}
	
	/**
	 * constructor
	 * @param scene
	 */
	public SuperSampling(Scene scene) {
		super(scene);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * setter for max color level
	 * @param level
	 * @return
	 */
	public SuperSampling setColorLevel(int level) {
		this.maxCalcColorLevel = level;
		return this;
	}
	
	/**
	 * setter for half distance for grid
	 * @param hd
	 * @return
	 */
	public SuperSampling setHalfDistance(double hd) {
		this.halfDistance = hd;
		return this;
	}
	
	/**
	 * setter for amount of rays for beam
	 * @param amount
	 * @return
	 */
	public SuperSampling setSamplingRays(int amount) {
		this.superSamplingRays = amount;
		return this;
	}

	/**
	 * 
	 * @param ray Ray
	 * @return the geopoint closest to the head of the ray 
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		
		List<GeoPoint> intersectableList = scene.geometries.findGeoIntersections(ray); 
		return ray.findClosestGeoPoint(intersectableList);
	}
	
	
	@Override
	/**
	 * Function to trace a ray to get its color
	 * @param ray the ray to be traced
	 * @return the color
	 */
	public primitives.Color traceRay(Ray ray) {
		
		GeoPoint closestPoint = findClosestIntersection(ray);
		return closestPoint == null ?  scene.background : calcColor(closestPoint, ray) ;  
	}
	
	private primitives.Color calcColor(GeoPoint gp, Ray ray) {
		return calcColor(gp, ray, maxCalcColorLevel, INITIAL_K )
				.add(scene.ambientLight.getIntensity());
	}
	
	private primitives.Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
		primitives.Color color = gp.geometry.getEmission().add(calcLocalEffects(gp,ray,k));
		return level == 1 ? color : color.add(calcGlobalEffects(gp,ray,level,k));
	}
	
	/**
	 * 
	 * @param intersection GeoPoints intersected by the ray 
	 * @param ray Ray 
	 * @return calculated light contribution from all the light sources
	 */
	private primitives.Color calcLocalEffects(GeoPoint intersection, Ray ray, Double3 k) {
		
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
				Double3 ktr = transparency(intersection, lightSource, l,n);
				if(!(k.product(ktr).lowerThan(MIN_CALC_COLOR_K))){
					primitives.Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
					color = color.add(calcDiffusive(kd,l,n,lightIntensity),
							calcSpecular(ks,l,n,v,nShininess,lightIntensity));
					
				}
			}
		}
		return color;
	}
	
	/**
	 * Function to check if a geo is blocking light source to current geo
	 * @param geoPoint GeoPoint
	 * @param ls LightSource
	 * @param l Vector
	 * @param n Vector
	 * @return Double3
	 */
	private Double3 transparency(GeoPoint geoPoint, LightSource ls, Vector l, Vector n){
		
		Vector lightDirection = l.scale(-1);
		Ray lightRay = new Ray(geoPoint.point, lightDirection,n);
		
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
		if (intersections == null) return new Double3(1.0);
		
		Double3 ktr = new Double3(1.0);
		
		double rayLightDistance = ls.getDistance(lightRay.p0);
		
		for (GeoPoint geopoint : intersections) {
			double rayIntersectionDistance = lightRay.p0.distance(geopoint.point); 
			if (rayIntersectionDistance < rayLightDistance) ktr = geopoint.geometry.getMaterial().kT.product(ktr); 
		}
		return ktr;  
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
		Vector r = l.subtract(n.scale(2*(l.dotProduct(n))));
		double vrMinus = Math.max(0, v.scale(-1).dotProduct(r));
		double vrn =Math.pow(vrMinus,nShininess);
        return lightIntensity.scale(ks.scale(vrn));
	}
	
	/**
	 * recursive function that takes into account all the relection and refraction rays if transparent 
	 * @param gp GeoPoint
	 * @param v Ray
	 * @param level int 
	 * @param k Double3
	 * @return primitive.Color
	 */
	private primitives.Color calcGlobalEffects(GeoPoint gp, Ray v, int level, Double3 k ){
		
		primitives.Color color = new primitives.Color(Color.BLACK);
		Vector n = gp.geometry.getNormal(gp.point).normalize();  // get normal vector 
		Material material = gp.geometry.getMaterial();
		
		Double3 kkr = k.product(material.kR);
		Double3 kkt = k.product(material.kT);
		
		if(!(kkr.lowerThan(MIN_CALC_COLOR_K))) //stop recursion 
			color = color.add(calcGlobalEffects(constructReflectedRay(v, gp.point,n), level, material.kR, kkr));
		
		if(!(kkt.lowerThan(MIN_CALC_COLOR_K)))
			color = color.add( calcGlobalEffects(constructRefractedRay(gp.point,v,n), level, material.kT, kkt));
		return color;
	}
	
	/**
	 * 
	 * @param ray Ray
	 * @param point Point
	 * @param n Vector
	 * @return constructs a reflection ray 
	 */
	private Ray constructReflectedRay(Ray ray, Point point, Vector n) {
		Vector reflectedV =  ray.dir.subtract((n.scale(n.dotProduct(ray.dir))).scale(2));
		return new Ray(point, reflectedV,  n);
	}
	
	/**
	 * 
	 * @param point Point
	 * @param ray Ray
	 * @param n Vector
	 * @return constructs a refraction ray 
	 */
	private Ray constructRefractedRay(Point point, Ray ray, Vector n) {
		return new Ray(point, ray.dir,  n);
	}
	
	
	/**
	 * helps global effect recursion by decreasing level and rescaleing the k
	 * @param ray Ray
	 * @param level int (stops when level = 
	 * @param kx Double3
	 * @param kkx Double3
	 * @return primitives.Color
	 */
	private primitives.Color calcGlobalEffects(Ray ray, int level, Double3 kx, Double3 kkx ) {
		GeoPoint gp = findClosestIntersection(ray);
		
		//***FIRST CHANGE***//
		List<Ray> beam = shootBeam(ray);
		primitives.Color coloredBeam = coloredBeam(beam);
		return(gp == null ? scene.background : add(calcColor(gp, ray, level-1, kkx), coloredBeam).scale(kx));
	}
	
	/**
	 * shoots multiple rays in the shape of a grid
	 * 
	 * @param ray the reflection or refraction ray 
	 * @return a list of rays 
	 */
	private List<Ray> shootBeam(Ray ray){
		
		List<Ray> beam = new LinkedList<Ray> ();
		
		//get the center point of your square by getting the point of the ray 
		Point centerPoint = ray.p0.add(ray.dir);
		
		//regular algorithm 
		if(setting == 1) {
			
		//get the left most corner point of your grid 
		//we will keep the same z axis as our center point but change the x and y axis
		//**** NOTE: added a new function that adds two points****//
		Point cornerPoint = centerPoint.add(new Point(halfDistance, halfDistance, 0)); 
		
		
		// equally shoot rays in the shape of a grid 
		for(int i = 0; i < Math.sqrt(superSamplingRays); i++) {
			for(int j = 0;  j < Math.sqrt(superSamplingRays); j++) {
				
				//get the interval between each point 
			    Point interval = new Point(i*(2*halfDistance/(Math.sqrt(superSamplingRays))), j*(2*halfDistance/(Math.sqrt(superSamplingRays))), 0);
			    
			    //added to the corner
				Point onGrid = cornerPoint.add(interval);
				
				//create the vector part of the ray from star point to random point:
				Vector newVector = onGrid.subtract(ray.p0); 
				 
				//create the ray:
				Ray newRay = new Ray(ray.p0, newVector);
				 
				//add to the list:
			    beam.add(newRay);
				
				}
			}
		}
		
		//adaptive super sampling 
		if(setting == 2) {
			
			
		}

		return beam;
		
	}
	
	/**
	 * calculate the color from a list of rays 
	 * 
	 * @param beam List of rays 
	 * @return color of all these rays added together ans scaled 
	 */
	private primitives.Color coloredBeam(List<Ray> beam){
		
		primitives.Color color = new primitives.Color(Color.BLACK);
		
		for(int i = 0; i < beam.size(); i++) {
			
			//find the closet geo that the point hits
			GeoPoint gp = findClosestIntersection(beam.get(i));
			
			//if it does not hit anything added the background
			if(gp == null) {
				color = color.add(scene.background);
			}
			
			//else add the color of the first object it hits
			else {
				color = color.add(gp.geometry.getEmission());
			}
				
		}
		
		//return the avrage color of all these rays 
		//return color.reduce(superSamplingRays);
		
		//needed to change because woun't nescsicarly have a super sampling amount of beams 
		return color.reduce(beam.size());
		
	}
	
	/**
	 * adds two colors 
	 * 
	 * @param color1 first color 
	 * @param color2 second color 
	 * @return primitive.Color
	 */
	private primitives.Color add(primitives.Color color1, primitives.Color color2){
		
		Double3 firstColor = color1.getRGB();
		Double3 secondColor = color2.getRGB();
		
		return new primitives.Color(firstColor.add(secondColor));
	}

	
}
