package renderer;

import primitives.*;
import geometries.*;

public class Camera {
	private Point p0;
	private Vector Vto;
	private Vector Vup;
	private Vector Vright;
	
	//veiw plane fields
	double width;
	double height;
	Vector direction;
	
	
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

	public Camera(Point p0, Vector Vup, Vector Vto) {}
	
	public Camera setVPSize(double width, double height) {}
	
	public Camera setVPDistance(double distance) {}
	
	public Ray constructRay(int nX, int nY, int j, int i)
	

}
