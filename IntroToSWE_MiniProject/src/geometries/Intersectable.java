package geometries;

import java.util.List;

import primitives.*;

public interface Intersectable {
	
	List<Point> findIntsersections(Ray ray);
}
