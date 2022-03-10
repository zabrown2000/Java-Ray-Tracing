package geometries;

import java.util.List;

import primitives.*;

public interface Intersectable extends Geometry {
	
	List<Point> findIntsersections(Ray ray);
}
