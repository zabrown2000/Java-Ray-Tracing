package scene;

import java.awt.Color;
import elements.*;
import geometries.*;
import primitives.*;

public class Scene {
	
	public String name;
	public Color background = Color.BLACK;
	public AmbientLight ambientLight;
	public Geometries geometries;
	
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
	public Scene setAmbientLight(Color IA, Double3 kA) {
		AmbientLight ambientLight = new AmbientLight(IA, kA); //check that the double3 should be a parameter
		this.ambientLight = ambientLight;
		return this;
	}
	
	/**
	 * sets the background color 
	 * 
	 * @param color Color of the background
	 * @return the scene 
	 */
	public Scene setBackground(Color color) {
		background = color;
		return this;
	}
	
	/**
	 * adds a geometry to the list 
	 * 
	 * @param geometry the Geometry you want to add to the scene
	 * @return the scene 
	 */
	public Scene setGeometries(Geometries geometry) {
		geometries.add(geometry);
		return this;
	}
	
	

}
