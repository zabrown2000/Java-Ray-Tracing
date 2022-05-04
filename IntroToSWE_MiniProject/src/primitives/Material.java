package primitives;

public class Material {

	public Double3 kS = Double3.ZERO;
	public Double3 kD = Double3.ZERO;
	public int nShininess = 0;
	
	/**
	 * getter function 
	 * @return Double3 kS
	 */
	public Double3 getkS() {
		return kS;
	}
	
	/**
	 * getter function
	 * @return Double3 kD
	 */
	public Double3 getkD() {
		return kD;
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
	 * Setter for kS 
	 * @param ks - Double3
	 * @return new material
	 */
	public Material setKS(Double3 ks) {
		this.kS = ks;
		return this;
	}
	
	/**
	 * Setter for kD
	 * @param kd - double
	 * @return new material
	 */
	public Material setKD(double kd) {
		this.kD = new Double3(kd);
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
	 * setter for nShininess
	 * @param nsh int
	 * @return new material
	 */
	public Material setShininess(int nsh) {
		this.nShininess = nsh;
		return this;
	}
}
