package geometries;

import java.util.List;

import primitives.*;

public abstract class Intersectable {
	
	public abstract List<Point> findIntsersections(Ray ray);
}
