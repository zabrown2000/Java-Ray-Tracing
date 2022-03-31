package renderer;

import primitives.*;
import geometries.*;

public class Camera {
	private Point p0;
	private Vector Vto;
	private Vector Vup;
	private Vector Vright;
	
	//view plane fields
	double width;
	double height;
	double distance;
	
	/**
	 * 
	 * @return the position p0 of the camera
	 */
	public Point getP0() {
		return p0;
	}
	
	/**
	 * 
	 * @return the toward vector (Vto) of the camera 
	 */
    public Vector getVto() {
		return Vto;
	}

    /**
     * 
     * @return the upwards vector (Vup) of the camera 
     */
	public Vector getVup() {
		return Vup;
	}

	/**
	 * 
	 * @return the right vector (Vright) of the camera 
	 */
	public Vector getVright() {
		return Vright;
	}

	/**
	 * sets p0
	 * checks that the two vectors are orthogonal and sets then to Vto and Vup
	 * Calculates Vright and sets it
	 * 
	 * @param p0 the position point of the camera
	 * @param Vup the upward vector of the camera
	 * @param Vto the forward vector of the camera 
	 */
	public Camera(Point p0, Vector Vup, Vector Vto) {
		this.p0 = p0;
		
		//check perpendicular
		if(Vup.dotProduct(Vto) != 0) {
			throw new IllegalArgumentException("Error: Vto and Vup are not perpedicular");
		}
		
		this.Vto = Vto.normalize();
		this.Vup = Vup.normalize();
		this.Vright = (Vto.crossProduct(Vup)).normalize();	
	}
	
	/**
	 * sets the width and height of the view plane and returns the vector
	 * 
	 * @param width of the view plane
	 * @param height of the view plane
	 * @return the camera 
	 */
	public Camera setVPSize(double width, double height) {
		this.width = width;
		this.height = height;
		return this;
	}
	
	/**
	 * sets the distance from the camera to the view plane and return the camera 
	 * @param distance between the view plane and the camera 
	 * @return the camra 
	 */
	public Camera setVPDistance(double distance) {
		this.distance = distance;
		return this;
	}
	
	/**
	 * shoots a ray from the camera through the view plane 
	 * 
	 * @param nX number of pixels in the horizontal direction
	 * @param nY number of pixels in the vertical direction
	 * @param j position in the column 
	 * @param i position in the row 
	 * @return 
	 */
	public Ray constructRay(int nX, int nY, int j, int i) {
		//image center 
		Point Pc = this.p0.add(Vto.scale(distance));
				
		//ratio
		double Ry = height/nY;
		double Rx = width/nX;
				
		//if xj and yi equal zero we will get in error 
		//therefore we want to work around this as it is not incorrect 
		double yI = -(i-((nY-1)/2))*Ry;
		double xJ = (j-((nX-1)/2))*Rx;
				
		Point Pij = Pc; 
		if (xJ != 0) Pij = Pij.add(Vright.scale(xJ));
		if (yI != 0) Pij = Pij.add(Vup.scale(yI));
				
		Vector Vij = Pij.subtract(p0);
		Ray ray = new Ray(p0,Vij);
			    
		return ray;
		
	}
	

}
