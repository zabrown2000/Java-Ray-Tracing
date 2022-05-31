package renderer;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
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
	 * sets our target range 
	 */
	private double TARGET_ANGLE_RANGE_LIMIT = 1;
	
	/**
	 * constructor 
	 * @param scene Scene
	 */
	public RayTracerSuperSampling(Scene scene) { super(scene);}

	
	@Override
	public primitives.Color traceRay(Ray ray) {
		GeoPoint closestPoint = findClosestIntersection(ray);
		return closestPoint == null ?  scene.background : calcColor(closestPoint, ray); 
	}
	
	
	private List<Ray> shootMultipleReflectiveRays(Ray ray, Point point, Vector n) {
		
		Ray relective = constructReflectedRay(ray,point,  n);  // the center ray 
		List<Ray> multipleRays = calcRayVectors(relective, TARGET_ANGLE_RANGE_LIMIT);
		return multipleRays;
		
		//primitives.Color addedColor = calMultipleRayColor(multipleRays);
		//return addedColor;
		
	}
	
	private List<Ray> shootMultipleRefractoredRays( Ray ray, Point point, Vector n) {
		
		Ray refractored = constructRefractedRay(point, ray, n);
		List<Ray> multipleRays = calcRayVectors(refractored, TARGET_ANGLE_RANGE_LIMIT);
		return multipleRays;
		
		//primitives.Color addedColor = calMultipleRayColor(multipleRays);
		//return addedColor;
		
	}
	
	//angle generator 
	private List<Ray> calcRayVectors(Ray ray, double d ){
        
	
		List<Ray> multipleRays = new LinkedList<Ray>(); //choosing linked list as we are constantly adding to the list 
		multipleRays.add(ray); //adding our center ray 
		
		for( int i = 0; i < 80 ; i++) {
			
			
			/*idea:
				calculate the vector after add 45 degrees 
				//maybe look at culc and look at the form8ula of a cone 
				 * newVectpr 
				// another idea is create a square or circle as a target area and only generate vectors that hit it 
				
				randomX = Util.random( ray.dir.getX(), newVector.getX());
				randomY = Util.random( ray.dir.getY(), newVector.getY());
				randomZ = Util.random( ray.dir.getZ(), newVector.getZ());
			
			new Ray = new Ray( ray.p0, newVector(randomX, randomY, randomZ);
			*/
			
			
			
			
			
			 //******new idea:********
			  
			 // we will use our reflection and refraction ray as the center of a circle which will be our target area :
			 
			 // need to find the point at the top of your ray vector 
			 // have a few ideas: 
			 // 1) is using the length(function we have)
			 // 2) or using the add from point like this..... 
			 Point centerPoint = ray.p0.add(ray.dir); // return point at the top of you vector and treate as the center of the circle 
			 
			 //pick random radius :
			 double radius = 0.9; // maybe set ontop 
			 
			 //generate random xyz points: //can maybe do in another function 
			 double randomX = Util.random(centerPoint.getX() + radius, centerPoint.getX() - radius);
			 double randomY = Util.random(centerPoint.getY() + radius, centerPoint.getY() - radius);
			 double randomZ = Util.random(centerPoint.getZ() + radius, centerPoint.getZ() - radius);
			 
			 //create points from the random x y z:
			 Point randomPoint = new Point(randomX, randomY, randomZ);
			 
			 //create the vector part of the ray from star point to random point:
			 Vector newVector = randomPoint.subtract(ray.p0); //might need to be the other way around
			 
			 //create the ray:
			 Ray newRay = new Ray(ray.p0, newVector);
			 
			 //add to the list:
			 multipleRays.add(newRay);
			 
			 
				
			
			
			
			
			// we chose our rage angle not to exceed a hard coded value.
			//double xLimit = Math.random()*((ray.dir.getX() + d)-(ray.dir.getX() - d)) + (ray.dir.getX() - d);
			//double yLimit = Math.random()*((ray.dir.getY() + d)-(ray.dir.getY() - d)) + (ray.dir.getY() - d);
			//double zLimit = Math.random()*((ray.dir.getZ() + d)-(ray.dir.getZ() - d)) + (ray.dir.getZ() - d);
			
			//double zLimit = ray.dir.getZ();
			//double yLimit = ray.dir.getY();
			//double xLimit = 
			//double 		
			
			//Ray newRay = new Ray(ray.p0, new Vector(xLimit, yLimit, zLimit));
			//double randomNum = (Math.random()*(1-(-1)))+(-1);
			
			//Ray newRay = new Ray(ray.p0, ray.dir.scale(randomNum)); //this thethod does not change things much 
			
			
			
			//multipleRays.add(newRay);	
		}
		
		return multipleRays;
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
			color = color.add(calcGlobalEffects(shootMultipleReflectiveRays(v, gp.point,n), level, material.kR, kkr));
		
		if(!(kkt.lowerThan(MIN_CALC_COLOR_K)))
			color = color.add( calcGlobalEffects(shootMultipleRefractoredRays(v,gp.point,n), level, material.kT, kkt));
		return color;
	}
	
	/////////////////////////////
	
	/**
	 * helps global effect recursion by decreasing level and rescaleing the k
	 * @param ray Ray
	 * @param level int (stops when level = 
	 * @param kx Double3
	 * @param kkx Double3
	 * @return primitives.Color
	 */
	private primitives.Color calcGlobalEffects(List<Ray> rays, int level, Double3 kx, Double3 kkx ) {  //now accepst a list of rays and not only one ray 
		/*CHANGES:
		 * 1) function now receives list of rays to work with
		 * 2) have color variable set default to black and will use to calc color from each ray
		 * 3) loop through list of rays to find closest geopoint and then using recursive calcColor to find color at that point
		 * 4) add the return of calcColor to the global color variable
		 * 5) check after loop if color still black return background, otherwise return the global color*/
		
		//primitives.Color globalColor = primitives.Color.BLACK; //set default to black and not null so don't get null exceptions later
		//primitives.Color globalColor = primitives.Color.BLACK;
		/*primitives.Color globalColor = new primitives.Color(Color.BLACK);
		for(Ray r : rays) {
			GeoPoint gp = findClosestIntersection(r);
			//if (gp == null) continue; //if non is found go to the next ray
			//globalColor = globalColor.add(gp.geometry.getEmission());
			if(gp != null){
				globalColor.add(calcColor(gp, r, level-1, kkx)).scale(kx);
			}
		}*/
		//return (globalColor == primitives.Color.BLACK) ? scene.background : globalColor;	
		
		
		//return (globalColor == primitives.Color.BLACK) ? scene.background : globalColor; //.add(calcColor(gp, r, level-1, kkx)).scale(kx);
		
		//return(gp == null ? scene.background : calcColor(gp, ray, level-1, kkx).scale(kx));
		
		List<primitives.Color> globalColor = new LinkedList<primitives.Color>(); //bc we are adding to the list 
		
		for(Ray r : rays) {
			GeoPoint gp = findClosestIntersection(r);
			if (gp != null) { //if non is found go to the next ray
			    globalColor.add(calcColor(gp, r, level-1, kkx).scale(kx));
			    
			}	
		}
		
		return (addColorList(globalColor) == primitives.Color.BLACK) ? scene.background : addColorList(globalColor);
	}
		
	
		
	private primitives.Color addColorList(List<primitives.Color> colorList ){
		
		if(colorList.isEmpty()) return primitives.Color.BLACK ;
		
		primitives.Color firstColor = colorList.get(0);
		
		for (int i = 1; i < colorList.size(); i++) {
			firstColor.add(colorList.get(i));
		}
		return firstColor;

	}
		
		
		//return (totalColor == primitives.Color.BLACK) ? scene.background : totalColor; 
		
		
		
		
	//}
	
	/*itterateThroughRays(List<Ray> ray){
		for( rays r : rays ) {
			GeoPoint gp = findClosestIntersection(r);
			if gp = null
			clor = calclGlobaleffects (gp,ray );
		}
	}*/
	
	
	private static final int MAX_CALC_COLOR_LEVEL = 4; 
	private static final double MIN_CALC_COLOR_K = 0.001; 
	private static final Double3 INITIAL_K = new Double3(1.0);
	
	/**
	 * 
	 * @param ray Ray
	 * @return the geopoint closest to the head of the ray 
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		
		List<GeoPoint> intersectableList = scene.geometries.findGeoIntersections(ray); 
		return ray.findClosestGeoPoint(intersectableList);
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
	 * Function to check if a geo is blocking light source to current geo
	 * @param geoPoint GeoPoint
	 * @param ls LightSource
	 * @param l Vector
	 * @param n Vector
	 * @return Double3
	 */
	private Double3 transparency(GeoPoint geoPoint, LightSource ls, Vector l, Vector n){
		
		Vector lightDirection = l.scale(-1); // from point to light source
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
	 * 
	 * @param gp Geopoint
	 * @param ray Ray
	 * @return calculates the color of the GeoPoint intersected by that ray 
	 */
	private primitives.Color calcColor(GeoPoint gp, Ray ray) {
		return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K )
				.add(scene.ambientLight.getIntensity());
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
	 * adds global and local effects color 
	 * @param gp GeoPoint
	 * @param ray Ray
	 * @param level int
	 * @param k Double3
	 * @return primitives.Color
	 */
	private primitives.Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
		primitives.Color color = gp.geometry.getEmission().add(calcLocalEffects(gp,ray,k));
		return level == 1 ? color : color.add(calcGlobalEffects(gp,ray,level,k));
	}
	
	
	
	
}
