package renderer;

import primitives.*;
import geometries.*;
import renderer.*;

public class Camera {
	
	private static int PRINT_INTERVAL = 1;
	//private static int threadsCount = 4;
	
	private int threadsCount;
	private Point p0;
	private Vector Vto;
	private Vector Vup;
	private Vector Vright;
	private ImageWriter imageWriter;
	private RayTraceBase rayTracer;
	
	
	//view plane fields
	double width;
	double height;
	double distance;
	
	
	
	/**
	 * 
	 * @param imageWriter
	 * @return The camera
	 */
	public Camera setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}

	/**
	 * 
	 * @param rayTracer
	 * @return The camera
	 */
	public Camera setRayTracer(RayTraceBase rayTracer) {
		this.rayTracer = rayTracer;
		return this;
	}

	/**
	 * 
	 * @param width
	 * @return The camera
	 */
	public Camera setWidth(double width) {
		this.width = width;
		return this;
	}

	/**
	 * 
	 * @param height
	 * @return The camera
	 */
	public Camera setHeight(double height) {
		this.height = height;
		return this;
	}

	/**
	 * 
	 * @param distance
	 * @return The camera
	 */
	public Camera setDistance(double distance) {
		this.distance = distance;
		return this;
	}

	/**
	 * 
	 * @param p0
	 * @return The camera
	 */
	public Camera setP0(Point p0) {
		this.p0 = p0;
		return this;
	}

	/**
	 * 
	 * @param vto
	 * @return The camera
	 */
	public Camera setVto(Vector vto) {
		Vto = vto.normalize();
		return this;
	}

	/**
	 * 
	 * @param vup
	 * @return The camera
	 */
	public Camera setVup(Vector vup) {
		Vup = vup.normalize();
		return this;
	}

	/**
	 * 
	 * @param vright
	 * @return The camera
	 */
	public Camera setVright(Vector vright) {
		Vright = vright.normalize();
		return this;
	}

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
	public Camera(Point p0, Vector Vto, Vector Vup) {
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
	 * @return the camera 
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
	 * @return the new ray
	 */
	public Ray constructRay(int nX, int nY, int j, int i) {
		//image center 
		Point Pc = this.p0.add(Vto.scale(distance));
				
		//ratio
		double Ry = height/nY;
		double Rx = width/nX;
				
		//if xj and yi equal zero we will get in error 
		//therefore we want to work around this as it is not incorrect 
		double yI = ((nY - 1)/2d - i)*Ry;
		double xJ = (-(nX - 1)/2d + j)*Rx;
				

		Point Pij = Pc; 
		if (xJ != 0) Pij = Pij.add(Vright.scale(xJ));
		if (yI != 0) Pij = Pij.add(Vup.scale(yI));
		
		//else Pij = Pij.add(Vright.scale(xJ).normalize()).add(Vright.scale(yI).normalize());
				
		
				
		Vector Vij = Pij.subtract(p0).normalize();
		Ray ray = new Ray(p0,Vij);
			    
		return ray;
    }
	
	/**
	 * Function to render an image and set everything up
	 */
	public void renderImage() {
		if(p0 == null || Vto == null || Vup == null|| Vright == null || imageWriter == null || rayTracer == null ) {
			throw new IllegalArgumentException("MissingResourcesException");
			//throw new IllegalArgumentException("UnsupportedOperationException");
		}
		
		/*for (int i = 0; i < imageWriter.getNy(); i++) {
			for (int j = 0; j < imageWriter.getNx(); j++) {
				
				Ray r = this.constructRay(imageWriter.getNx(), imageWriter.getNx(), j, i); //construct ray through pixel
				primitives.Color c = this.rayTracer.traceRay(r); //color at point intersected by ray
				this.imageWriter.writePixel(j, i, c); //coloring that pixel
			}
		}*/
		
		/**THREADING**/
		Pixel.initialize(imageWriter.getNy(),imageWriter.getNx(), PRINT_INTERVAL);
		while(threadsCount-- > 0) {
			new Thread( () -> {
				for(Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
					this.imageWriter.writePixel(pixel.col, pixel.row, this.rayTracer.traceRay(this.constructRay(imageWriter.getNy(),imageWriter.getNx(), pixel.col, pixel.row))); 
				    
			}).start();
		}
		Pixel.waitToFinish();
	}
	
	/**
	 * prints a grid in the color 
	 * @param interval how the grid is spaced out 
	 * @param color color of grid lines
	 */
	public void printGrid(int interval, Color color) {
		if(imageWriter == null) {
			throw new IllegalArgumentException("MissingResourcesException");
		}
		for (int i = 0; i < imageWriter.getNy(); i++) {
			for (int j = 0; j < imageWriter.getNx(); j++) {
				if (((i % interval) == 0) || ((j % interval) == 0)) { //grid lines - need to add if so no grid on shape
					//need another check here to not overwrite picture
					imageWriter.writePixel(j, i, color);
				}
			}
		}
	}
	
	/**
	 * Wrapper function for imageWriter's function inside camera
	 */
	public void writeToImage() {
		if(imageWriter == null) {
			throw new IllegalArgumentException("MissingResourcesException");
		}
		imageWriter.writeToImage();
	}

	
	/**
	 * set threat count 
	 * @param n int number of threads 
	 */
	public Camera setMultithreading(int n) {
		this.threadsCount  = n;
		return this;
	}
		
}
	


