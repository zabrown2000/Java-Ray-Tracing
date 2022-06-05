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
	
	
	private static final int MAX_CALC_COLOR_LEVEL = 4; 
	private static final double MIN_CALC_COLOR_K = 0.001; 
	private static final Double3 INITIAL_K = new Double3(1.0);
	private static final double RADIUS = 0.1;
<<<<<<< HEAD
	private static final int SUPERSAMPLING_RAYS = 20;
	private static final double DISTANCE = 10;
=======
	private static final int SUPERSAMPLING_RAYS = 80;
>>>>>>> branch 'main' of git@github.com:zabrown2000/IntroToSWE_MiniProject.git
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
	
	/**
	 * recursive function that takes into account all the relection and refraction rays if transparent 
	 * @param gp GeoPoint
	 * @param v Ray
	 * @param level int 
	 * @param k Double3
	 * @return primitive.Color
	 */
	private primitives.Color calcGlobalEffects(GeoPoint gp, Ray r, int level, Double3 k ){
		
		primitives.Color color = new primitives.Color(Color.BLACK);
		Vector n = gp.geometry.getNormal(gp.point).normalize();  // get normal vector 
		Material material = gp.geometry.getMaterial();
		
		Double3 kkr = k.product(material.kR);
		Double3 kkt = k.product(material.kT);
<<<<<<< HEAD
																	//getting color of center ray twice, in global color and calcrayvectors
		/*if(!(kkr.lowerThan(MIN_CALC_COLOR_K))) //stop recursion 
			//color = color.add(calcGlobalEffects(shootMultipleReflectiveRays(r, gp.point,n), level, material.kR, kkr));
=======
		
		if(!(kkr.lowerThan(MIN_CALC_COLOR_K))) //stop recursion 
			//color = color.add(calcGlobalEffects(shootMultipleReflectiveRays(v, gp.point,n), level, material.kR, kkr));
			//color = color.add(shootMultipleReflectiveRays(v, gp.point,n));
			//color = color.add(calcGlobalEffects(constructReflectedRay(v, gp.point,n), level, material.kR, kkr));//.add(shootMultipleReflectiveRays(v, gp.point,n));
<<<<<<< HEAD
=======
		
=======
>>>>>>> branch 'main' of git@github.com:zabrown2000/IntroToSWE_MiniProject.git
>>>>>>> branch 'main' of https://github.com/zabrown2000/IntroToSWE_MiniProject.git
			//color = color.add(shootMultipleReflectiveRays(v, gp.point,n, level, material.kR, kkr).add);
			//color = color.add(calcGlobalEffects(constructReflectedRay(v, gp.point,n), level, material.kR, kkr)).add(shootMultipleReflectiveRays(v, gp.point,n));
<<<<<<< HEAD
			color = color.add(calcGlobalEffects(constructReflectedRay(r, gp.point,n), level, material.kR, kkr), shootMultipleReflectiveRays(r, gp.point,n));
=======
			color = color.add(calcGlobalEffects(constructReflectedRay(v, gp.point,n), level, material.kR, kkr), shootMultipleReflectiveRays(v, gp.point,n));
<<<<<<< HEAD

=======
>>>>>>> branch 'main' of https://github.com/zabrown2000/IntroToSWE_MiniProject.git
>>>>>>> branch 'main' of git@github.com:zabrown2000/IntroToSWE_MiniProject.git
		
>>>>>>> branch 'main' of https://github.com/zabrown2000/IntroToSWE_MiniProject.git
		if(!(kkt.lowerThan(MIN_CALC_COLOR_K)))
<<<<<<< HEAD
			//color = color.add( calcGlobalEffects(shootMultipleRefractoredRays(r,gp.point,n), level, material.kT, kkt));
=======
			//color = color.add( calcGlobalEffects(shootMultipleRefractoredRays(v,gp.point,n), level, material.kT, kkt));

			color = color.add(shootMultipleRefractoredRays(v, gp.point,n));
			//color = color.add(calcGlobalEffects(constructRefractedRay(gp.point,v,n), level, material.kT, kkt)).add(shootMultipleReflectiveRays(v, gp.point,n));
		
<<<<<<< HEAD

=======
=======
>>>>>>> branch 'main' of git@github.com:zabrown2000/IntroToSWE_MiniProject.git
>>>>>>> branch 'main' of https://github.com/zabrown2000/IntroToSWE_MiniProject.git
			//color = color.add(shootMultipleRefractoredRays(v, gp.point,n, level, material.kR, kkr));
			//color = color.add( calcGlobalEffects(constructRefractedRay(gp.point,v,n), level, material.kT, kkt)).add(shootMultipleRefractoredRays(v, gp.point,n));
			color = color.add( calcGlobalEffects(constructRefractedRay(gp.point,r,n), level, material.kT, kkt), shootMultipleRefractoredRays(r, gp.point,n));
			
<<<<<<< HEAD
=======
<<<<<<< HEAD
		return color;*/
		if(!(kkr.lowerThan(MIN_CALC_COLOR_K))) //stop recursion 
			color = color.add(calcGlobalEffects(shootMultipleReflectiveRays(r, gp.point,n), level, material.kR, kkr));
		
		if(!(kkt.lowerThan(MIN_CALC_COLOR_K)))
			color = color.add( calcGlobalEffects(shootMultipleRefractoredRays(r,gp.point,n), level, material.kT, kkt));
=======
>>>>>>> branch 'main' of https://github.com/zabrown2000/IntroToSWE_MiniProject.git
>>>>>>> branch 'main' of git@github.com:zabrown2000/IntroToSWE_MiniProject.git
>>>>>>> branch 'main' of https://github.com/zabrown2000/IntroToSWE_MiniProject.git
		return color;
	}
	
	/**
	 * helps global effect recursion by decreasing level and rescaleing the k
	 * @param ray Ray
	 * @param level int (stops when level = 
	 * @param kx Double3
	 * @param kkx Double3
	 * @return primitives.Color
	 */
	private primitives.Color calcGlobalEffects(List<Ray> rays, int level, Double3 kx, Double3 kkx ) {
		//GeoPoint gp = findClosestIntersection(ray);
		//return(gp == null ? scene.background : calcColor(gp, ray, level-1, kkx).scale(kx));
		List<primitives.Color> globalColor = new LinkedList<primitives.Color>(); //bc we are adding to the list 
		
		for(Ray r : rays) {
			GeoPoint gp = findClosestIntersection(r);
			if (gp != null) { //if non is found go to the next ray
			    globalColor.add(calcColor(gp, r, level-1, kkx).scale(kx));
			    
			} else {
				globalColor.add(scene.background);
			}
		}
		
		return (addColorList(globalColor) == scene.background) ? scene.background : addColorList(globalColor);
	}
	
	
	private List<Ray> shootMultipleReflectiveRays(Ray ray, Point point, Vector n) {
		
		Ray relective = constructReflectedRay(ray,point,  n);  // the center ray 
		List<Ray> multipleRays = calcRayVectors(relective);
		return multipleRays;
		
	}
	
	private List<Ray> shootMultipleRefractoredRays( Ray ray, Point point, Vector n) {
		
		Ray refractored = constructRefractedRay(point, ray, n);
		List<Ray> multipleRays = calcRayVectors(refractored);
		return multipleRays;
		
	}
	
	
	private List<Ray> calcRayVectors(Ray ray){ /*primitives.Color*/
		List<Ray> multipleRays = new LinkedList<Ray>(); //choosing linked list as we are constantly adding to the list 
		multipleRays.add(ray); //adding our center ray
		
		
		/*public List<Ray> generateBeam(Vector n, double radius, double distance, int numOfRays) {
		List<Ray> rays = new LinkedList<Ray>();
		rays.add(this);// Includeing the main ray
		if (numOfRays == 1 || isZero(radius))// The component (glossy surface /diffuse glass) is turned off
			return rays;
		Vector nX = _dir.createNormal();
		Vector nY = _dir.crossProduct(nX);
		Point3D centerCircle = this.getPoint(distance);
		Point3D randomPoint;
		double x, y, d;
		double nv = alignZero(n.dotProduct(_dir));
		for (int i = 1; i < numOfRays; ++i) {
			x = getRandomNumber(-1, 1);
			y = Math.sqrt(1 - x * x);
			d = getRandomNumber(-radius, radius);
			x = alignZero(x * d);
			y = alignZero(y * d);
			randomPoint = centerCircle;
			if (x != 0)
				randomPoint = randomPoint.add(nX.scale(x));
			if (y != 0)
				randomPoint = randomPoint.add(nY.scale(y));
			Vector tPoint = randomPoint.subtract(_p0);
			double nt = alignZero(n.dotProduct(tPoint));
			if (nv * nt > 0) {
				rays.add(new Ray(_p0, tPoint));
			}
		}
		return rays;
	}*/
		
		 
		
		//initalize color list
<<<<<<< HEAD
		 List<primitives.Color> globalColor = new LinkedList<primitives.Color>();
		 
		 //List<Double3> rgb = new LinkedList<Double3>();
=======
		// List<primitives.Color> globalColor = new LinkedList<primitives.Color>();
>>>>>>> branch 'main' of https://github.com/zabrown2000/IntroToSWE_MiniProject.git
		
		for( int i = 0; i < SUPERSAMPLING_RAYS; i++) {
		
			 // we will use our reflection and refraction ray as the center of a circle which will be our target area :
			 
			 // need to find the point at the top of your ray vector 
			 Point centerPoint = ray.p0.add(ray.dir); // return point at the top of you vector and treate as the center of the circle 
			 
			 //pick random radius :
			 //double radius = 0.1; // maybe set ontop 
			 
			 //generate random xyz points: //can maybe do in another function 
			 double randomX = Util.random(centerPoint.getX() + RADIUS, centerPoint.getX() - RADIUS);
			 double randomY = Util.random(centerPoint.getY() + RADIUS, centerPoint.getY() - RADIUS);
			 double randomZ = Util.random(centerPoint.getZ() + RADIUS, centerPoint.getZ() - RADIUS);
			 
			 //create points from the random x y z:
			 Point randomPoint = new Point(randomX, randomY, randomZ);
			 
			 //create the vector part of the ray from star point to random point:
			 Vector newVector = randomPoint.subtract(ray.p0); //might need to be the other way around
			 //Vector newVector = ray.p0.subtract(randomPoint);
			 
			 //create the ray:
			 Ray newRay = new Ray(ray.p0, newVector);
			 
			 //add to the list:
			 multipleRays.add(newRay);
			 
			 //could do this all in a new function 
			 
			 // get intersection point color 
<<<<<<< HEAD
			 GeoPoint gp = findClosestIntersection(newRay);
				if(gp == null) {
					globalColor.add(scene.background);
				}
					if(gp != null) {
					globalColor.add(gp.geometry.getEmission());  //added rgb to color 
				   // globalColor.add(gp.geometry.getEmission());
=======
			 /*GeoPoint gp = findClosestIntersection(newRay);
				if(gp != null) {
				    globalColor.add(gp.geometry.getEmission());
>>>>>>> branch 'main' of https://github.com/zabrown2000/IntroToSWE_MiniProject.git
				    
				} //else {
					//globalColor.add(scene.background);
				//}*/
		}
		
<<<<<<< HEAD
		return (globalColor.isEmpty()) ? scene.background : addColorList(globalColor);
		//return (globalColor.isEmpty()) ? scene.background : addColorList(globalColor);
		//return multipleRays;
=======
		//return (globalColor.isEmpty()) ? scene.background : addColorList(globalColor);
		return multipleRays;
>>>>>>> branch 'main' of https://github.com/zabrown2000/IntroToSWE_MiniProject.git
	}
	
	
	private primitives.Color addColorList(List<primitives.Color> colorList ){
	//private primitives.Color addColorList(List <globalColor> rgb ){
		
		if(colorList.isEmpty()) return primitives.Color.BLACK ;
		//if(rgb.isEmpty()) return primitives.Color.BLACK;
		
		primitives.Color firstColor = colorList.get(0);
		//Double3 firstColor = rgb.get(0);
		
		for (int i = 1; i < colorList.size(); i++) {
			firstColor.add(colorList.get(i));
		}
		
		//firstColor = firstColor.reduce(SUPERSAMPLING_RAYS);
		//return firstColor.scale(1/SUPERSAMPLING_RAYS);
<<<<<<< HEAD
		//primitives.Color total = new primitives.Color(firstColor);
=======
<<<<<<< HEAD
		return firstColor.reduce(SUPERSAMPLING_RAYS);
	    //return firstColor;
=======
>>>>>>> branch 'main' of https://github.com/zabrown2000/IntroToSWE_MiniProject.git
	    return firstColor;
>>>>>>> branch 'main' of git@github.com:zabrown2000/IntroToSWE_MiniProject.git

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
		//return color;
		return level == 1 ? color : color.add(calcGlobalEffects(gp,ray,level,k));
	}
	
	
	
	
}
