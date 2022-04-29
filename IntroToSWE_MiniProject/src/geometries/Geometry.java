package geometries;

import primitives.*;

public abstract class Geometry extends Intersectable {
	
	protected primitives.Color emission = Color.BLACK;
	protected Material material = new Material();


	public abstract Vector getNormal(Point point);

	/**
	 * Function to get the emission color of a geometry
	 * @return the emission color
	 */
	public primitives.Color getEmission() {
		return this.emission;
	}
	
	/**
	 * Function to set the emission color of a geometry
	 * @return the geometry
	 */
	public Geometry setEmission(primitives.Color c) {
		this.emission = c;
		return this;
	}
	
	/**
	 * getter for material
	 * @return the material
	 */
	public Material getMaterial() {
		return this.material;
	}
	
	/**
	 * setter for material
	 * @param m the material
	 * @return new geometry
	 */
	public Geometry setMaterial(Material m) {
		this.material = m;
		return this;
	}
}
