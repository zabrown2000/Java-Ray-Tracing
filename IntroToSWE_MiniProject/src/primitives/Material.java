package primitives;

public class Material {

	public Double3 kS = Double3.ZERO;
	public Double3 kD = Double3.ZERO;
	public int nShininess = 0;
	
	/**
	 * Setter for kS 
	 * @param ks - Double3
	 * @return new material
	 */
	public Material setKS(Double3 ks) {
		this.kS = ks;
		return this;
	}
	
	/**
	 * Setter for kS
	 * @param ks - double
	 * @return new material
	 */
	public Material setKS(double ks) {
		this.kS = new Double3(ks);
		return this;
	}
	
	/**
	 * Setter for kD
	 * @param kd - Double3
	 * @return new material
	 */
	public Material setKD(Double3 kd) {
		this.kD = kd;
		return this;
	}
	
	/**
	 * Setter for kD
	 * @param kd - double
	 * @return new material
	 */
	public Material sedKD(double kd) {
		this.kD = new Double3(kd);
		return this;
	}
}
