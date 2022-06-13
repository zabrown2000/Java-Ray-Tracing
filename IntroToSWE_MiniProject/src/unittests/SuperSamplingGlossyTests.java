package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.Scene;

class SuperSamplingGlossyTests {

	private Scene scene = new Scene("Test scene");
	
	private int superSampling_on_off = 1;
	private int adaptive_on_off = 1;
	
	@Test
	void glossyAndDiffuseTest() {
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000)
				.setMultithreading(4); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

		scene.geometries.add( 
				//base
				new Triangle(new Point(-1500, -1500, -1150), new Point(1500, -1500, -1350), new Point(750, 750, -1500)) //base right
					.setEmission(new Color(20, 20, 20)).setMaterial(new Material().setkR(1.0)), //
				new Triangle(new Point(-1500, -1500, -1150), new Point(-700, 700, -1400), new Point(750, 750, -1500)) //base left
					.setEmission(new Color(20, 20, 20)).setMaterial(new Material().setkR(1.0)),
					
				//triangle at end
				new Triangle(new Point(-700, 700, -1400), new Point(750, 750, -1500), new Point(100,1000,3000))
					.setEmission(new Color(51,51,153)).setMaterial(new Material().setkT(0.4).setkR(0.05)),
					
				//flag triangles
				new Triangle(new Point(-700,-1300,100), new Point(500,-100,200), new Point(200,-1200,150)) //rightmost triangle in flag
					.setEmission(new Color(244,114,208)).setMaterial(new Material().setKS(0.3).setkT(0.75)),
				new Triangle(new Point(-700,-1300,400), new Point(-136,-1097,450), new Point(-363,34,500)) //middle triangle in flag
					.setEmission(new Color(51,153,102)).setMaterial(new Material().setKD(0.3).setkT(0.5)),
				new Triangle(new Point(-700,-1300,700), new Point(-400,-700,750), new Point(-900,100,800)) //leftmost triangle in flag
					.setEmission(new Color(51,102,255)).setMaterial(new Material().setShininess(60).setkT(0.7)),
				
				//sphere under right triangle
				new Sphere(new Point(50,-900,-500), 200).setEmission(new Color(100,100,10))
					.setMaterial(new Material().setKD(0.3).setKS(0.5).setShininess(30)),
					
				//spheres in front
				new Sphere(new Point(-500,300,-1200), 100).setEmission(new Color(128,0,0)) //lowest sphere
					.setMaterial(new Material().setShininess(70).setkT(0.1)),
				new Sphere(new Point(-100,600,-800), 150).setEmission(new Color(255,153,0)) //middle height
					.setMaterial(new Material().setKD(0.7).setKS(0.4).setkT(0.1)),
				new Sphere(new Point(500,100,-300), 300).setEmission(new Color(51,51,0)) //highest height outer
					.setMaterial(new Material().setKD(0.2).setShininess(50).setkT(0.6)),
					new Sphere(new Point(500,100,-300), 200).setEmission(new Color(128,0,128)) //highest height inner
					.setMaterial(new Material().setKD(0.2).setShininess(30).setkT(0.2))
						);
						

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setKL(0.00001).setKQ(0.000005));
		scene.lights.add(new PointLight(new Color(400,1020,400), new Point(300,-200,2000))
				.setKL(0.001).setKQ(0.000005));
		scene.lights.add(new DirectionalLight(new Color(100,100,200), new Vector(3.75,-5.1,-20.79)));

		ImageWriter imageWriter = new ImageWriter("superSamplingImprovement1", 500, 500);
		camera.setImageWriter(imageWriter); //
		
		//adding choice to use feature or not
		if (this.superSampling_on_off == 1) {
			camera.setRayTracer(new SuperSampling(scene).setColorLevel(4).setHalfDistance(0.05).setSamplingRays(81).setSetting(adaptive_on_off));
		} else if (this.superSampling_on_off == 0) {
			camera.setRayTracer(new RayTracerBasic(scene));
		}
		       
		camera.renderImage(); //
		camera.writeToImage();
	}

}

			/****RUN TIMES****
			 * stage 7 : 2.114
			 * miniproject 1: 24.442
			 * Threading: 13.392
			 * mini project 2 without threading: 11.17
			 * Threading + miniproject 2 = 7.334
			 */
			 
  
 
