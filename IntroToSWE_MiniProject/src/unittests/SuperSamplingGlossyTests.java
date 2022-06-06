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
	
	private int on_off = 1;
	
	@Test
	void glossyAndDiffuseTest() {
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

		scene.geometries.add( //
				//new Sphere(new Point(-550, -500, -1000), 400d).setEmission(new Color(0, 0, 100)) //blue sphere
				//		.setMaterial(new Material().setKD(0.25).setKS(0.25).setShininess(20).setkT(0.5)),
				new Sphere(new Point(-550, -500, -1200), 200d).setEmission(new Color(255, 47, 154)) //pink sphere
						.setMaterial(new Material().setKD(0.6)),
				new Sphere(new Point(-1300,-100,-300), 200d).setEmission(new Color(23,236,255)) //green sphere, further away
						.setMaterial(new Material().setKD(0.6)),
				//new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //tall triangle, mirror
						//.setEmission(new Color(20, 20, 20)) //
						//.setMaterial(new Material().setkR(1.0)));
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
						new Point(-1500, -1500, -2000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(0.5)));
						

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setKL(0.00001).setKQ(0.000005));

		ImageWriter imageWriter = new ImageWriter("superSamplingImprovement", 500, 500);
		camera.setImageWriter(imageWriter); //
		
		//adding choice to use feature or not
		if (this.on_off == 1) {
			camera.setRayTracer(new SuperSampling(scene).setColorLevel(4).setHalfDistance(0.05).setSamplingRays(80));
		} else if (this.on_off == 0) {
			camera.setRayTracer(new RayTracerBasic(scene));
		}
		
		        
		camera.renderImage(); //
		camera.writeToImage();
	}

}
