/**
 * 
 */
package unittests;

import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import renderer.ImageWriter;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 */
public class ReflectionRefractionTests {
	private Scene scene = new Scene("Test scene");

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(150, 150).setVPDistance(1000);

		scene.geometries.add( //
				new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setKD(0.4).setKS(0.3).setShininess(100).setkT(0.3)),
				new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setShininess(100)));
		scene.lights.add( //
				new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setKL(0.0004).setKQ(0.0000006));

		camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene)); //
		camera.renderImage();
		camera.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

		scene.geometries.add( //
				new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setKD(0.25).setKS(0.25).setShininess(20).setkT(0.5)),
				new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setKD(0.25).setKS(0.25).setShininess(20)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(1.0)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
						new Point(-1500, -1500, -2000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(0.5)));

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setKL(0.00001).setKQ(0.000005));

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)); //
		        //.setRayTracer(new RayTracerSuperSampling(scene));
				camera.renderImage(); //
				camera.writeToImage();

	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200, 200).setVPDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3 (0.15)));

		scene.geometries.add( //
				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setShininess(60)), //
				new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setShininess(60)), //
				new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setKD(0.2).setKS(0.2).setShininess(30).setkT(0.6)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
				.setKL(4E-5).setKQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)); //
				camera.renderImage(); //
				camera.writeToImage();
	}
	
	@Test
	public void fourShapes() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200,200).setVPDistance(1000);
		scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3 (0.15)));
		
		scene.geometries.add(
				new Triangle(new Point(-14.5823,8.8826,10), new Point(0,20,0), new Point(15.379,-13.9439,-10))
					.setEmission(new Color(PINK)).setMaterial(new Material().setKD(0.25).setKS(0.25).setShininess(20)),
				new Triangle(new Point(-89.216,22.7966,-50), new Point(66.6284,69.7684,-50), new Point(41.3255,-106.1116,-50))
					.setEmission(new Color(50,100,100)).setMaterial(new Material().setKD(0.25).setKS(0.25).setkR(0.9)),
				new Sphere(new Point(25,0,30), 30).setEmission(new Color(CYAN))
					.setMaterial(new Material().setKD(0.30).setKS(0.25).setShininess(30).setkT(0.8)),
				new Sphere(new Point(15,-20,20), 20).setEmission(new Color(50,10,20))
					.setMaterial(new Material().setKD(0.2).setKS(0.2).setShininess(30).setkT(0.6)));
		
		scene.lights.add(new SpotLight(new Color(1020, 200, 200), new Point(160, 150, 850), new Vector(-1, 1, -4)) //
				.setKL(0.0001).setKQ(0.000005));
		
		ImageWriter imageWriter = new ImageWriter("4Shapes", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)); //
		camera.renderImage(); // 
		camera.writeToImage();
	}
	
	@Test
	public void shapesBonus() {      

		Camera camera = new Camera(new Point(0,0,1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200,200).setVPDistance(1000);
		scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3 (0.15))).setBackground(new Color(51,204,255));
		
		scene.geometries.add(
				//base
				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //base right
					.setEmission(new Color(41, 135, 67)).setMaterial(new Material().setKD(0.6).setKS(0.7).setShininess(30)), //
				new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //base left
					.setEmission(new Color(41, 135, 67)).setMaterial(new Material().setKD(0.6).setKS(0.7).setShininess(30)),
				//flower
				new Sphere(new Point(-2.8,-37.7,-100),20).setEmission(new Color(14, 80, 11)) //flower center
					.setMaterial(new Material().setKD(0.3).setkT(0.4)),
				new Sphere(new Point(0,-70,-100),20).setEmission(new Color(168, 71, 161)) //bottom petal
					.setMaterial(new Material().setKD(0.3).setkT(0.5)),
				new Sphere(new Point(-27.5,-55,-100),20).setEmission(new Color(168, 71, 161)) //bottom left petal
					.setMaterial(new Material().setKD(0.3).setkT(0.5)),
				new Sphere(new Point(25,-50,-100),20).setEmission(new Color(168, 71, 161)) //bottom right petal
					.setMaterial(new Material().setKD(0.3).setkT(0.5)),
				new Sphere(new Point(22.5,-17.5,-100),20).setEmission(new Color(168, 71, 161)) //top right petal
					.setMaterial(new Material().setKD(0.3).setkT(0.5)),
				new Sphere(new Point(-30,-22.5,-100),20).setEmission(new Color(168, 71, 161)) //top left petal
					.setMaterial(new Material().setKD(0.3).setkT(0.5)),
				new Sphere(new Point(-5,-5,-100),20).setEmission(new Color(168, 71, 161)) //top petal
					.setMaterial(new Material().setKD(0.3).setkT(0.5)),
				//middle tree
				new Triangle(new Point(12.3,40.0433,-130), new Point(-15.1,38.8867,-130), new Point(0,70,-40)).setEmission(new Color(133, 101, 16)) //tree base middle
					.setMaterial(new Material().setKS(0.3).setkT(0.1)),
				new Triangle(new Point(-20,40.3,0), new Point(20,40.3,0), new Point(0,70.3,100)).setEmission(new Color(48, 184, 85)) //1st level tree middle
					.setMaterial(new Material().setKS(0.3).setKD(0.7)),
				new Triangle(new Point(-20,40.3,140), new Point(20,40.3,140), new Point(0,70.3,220)).setEmission(new Color(48, 184, 85)) //2nd level tree middle
					.setMaterial(new Material().setKS(0.3).setKD(0.7)),
				//right tree
				new Triangle(new Point(62.3,40.0433,-130), new Point(35.1,38.8867,-130), new Point(50,70,-40)).setEmission(new Color(133, 101, 16)) //tree base right
					.setMaterial(new Material().setKS(0.3).setkT(0.1)),
				new Triangle(new Point(25,40.3,0), new Point(65,40.3,0), new Point(45,70.3,100)).setEmission(new Color(48, 184, 85)) //1st level tree right
					.setMaterial(new Material().setKS(0.3).setKD(0.7)),
				new Triangle(new Point(20,40.3,140), new Point(60,40.3,140), new Point(40,70.3,220)).setEmission(new Color(48, 184, 85)) //2nd level tree right
					.setMaterial(new Material().setKS(0.3).setKD(0.7)),
				//left tree
				new Triangle(new Point(-38.3,40.0433,-130), new Point(-65.1,38.8867,-130), new Point(-50,70,-40)).setEmission(new Color(133, 101, 16)) //tree base left
					.setMaterial(new Material().setKS(0.3).setkT(0.1)),
				new Triangle(new Point(-65,40.3,0), new Point(-25,40.3,0), new Point(-45,70.3,100)).setEmission(new Color(48, 184, 85)) //1st level tree left
					.setMaterial(new Material().setKS(0.3).setKD(0.7)),
				new Triangle(new Point(-60,40.3,140), new Point(-20,40.3,140), new Point(-40,70.3,220)).setEmission(new Color(48, 184, 85)) //2nd level tree left
					.setMaterial(new Material().setKS(0.3).setKD(0.7)),
				//sun
				new Sphere(new Point(-70,70,200), 10).setEmission(new Color(RED)) //inner sphere
					.setMaterial(new Material().setKS(0.3).setkT(0.2).setkR(0.5)),
				new Sphere(new Point(-70,70,200), 20).setEmission(new Color(YELLOW)) //outer sphere
					.setMaterial(new Material().setKS(0.5).setkT(0.5))
				);
	
		scene.lights.add(new SpotLight(new Color(700, 700, 300), new Point(-150,150,700), new Vector(15, -25, -80)) //
				.setKL(0.001).setKQ(0.000005));
		
		
		ImageWriter imageWriter = new ImageWriter("BonusPicture", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)); //
		camera.renderImage(); //
		camera.writeToImage();
	}
}
