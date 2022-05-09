package scene;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import lighting.AmbientLight;
import lighting.LightSource;
import geometries.*;
import lighting.AmbientLight;
import primitives.*;

public class Scene {
	
	public String name;
	public primitives.Color background = primitives.Color.BLACK;
	public AmbientLight ambientLight = new AmbientLight(primitives.Color.BLACK, new Double3(1.0));
	public Geometries geometries;
	public List<LightSource> lights = new LinkedList<LightSource>();
	
	/**
	 * Constructs a scene with this name and creates an empty list of Geometries
	 * 
	 * @param scene name 
	 */
	public Scene(String name){
		this.name = name;
		this.geometries = new Geometries();
	}
	
	/**
	 * Sets the scene ambient light
	 * 
	 * @param IA color
	 * @param kA the Doubel3 scale factor
	 * @return the scene 
	 */
	public Scene setAmbientLight(AmbientLight ambient) {
		this.ambientLight = ambient;
		return this;
	}
	
	/**
	 * sets the background color 
	 * 
	 * @param color Color of the background
	 * @return the scene 
	 */
	public Scene setBackground(primitives.Color color) {
		background = color;
		return this;
	}
	
	/**
	 * adds a geometry to the list 
	 * 
	 * @param geometry the Geometry you want to add to the scene
	 * @return the scene 
	 */
	public Scene setGeometries(Geometries... geometries) {
		for (Intersectable g : geometries) {
			this.geometries.add(g);
		}
		
		//geometries.add(geometry);
		return this;
	}

	/**
	 * setter for light sources
	 * @param lightSources lights for scene class
	 * @return new scene
	 */
	public Scene setLights(List<LightSource> lightSources) {
		this.lights = lightSources;
		return this;
	}
	

}
