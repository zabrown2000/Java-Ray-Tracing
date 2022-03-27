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
	
	
	public Point getP0() {
		return p0;
	}

	public Vector getVto() {
		return Vto;
	}

	public Vector getVup() {
		return Vup;
	}

	public Vector getVright() {
		return Vright;
	}

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
	
	public Camera setVPSize(double width, double height) {
		this.width = width;
		this.height = height;
		return this;
	}
	
	public Camera setVPDistance(double distance) {
		this.distance = distance;
		return this;
	}
	
	
	public Ray constructRay(int nX, int nY, int j, int i) {
		return null;
	}
	

}
