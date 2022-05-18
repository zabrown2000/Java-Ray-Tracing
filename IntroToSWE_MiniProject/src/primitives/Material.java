package primitives;

public class Material {

	public Double3 kS = Double3.ZERO;
	public Double3 kD = Double3.ZERO;
	/**
	 * transparency coefficient
	 */
	public Double3 kT = Double3.ZERO;
	/**
	 * reflection coefficient
	 */
	public Double3 kR = Double3.ZERO;
	
	public int nShininess = 0;
	
	
	/**
	 * 
	 * @param kT transparency coeff
	 * @return the material
	 */
	public Material setkT(Double3 kT) {
		this.kT = kT;
		return this;
	}

	/**
	 * 
	 * @param kR reflection coeff
	 * @return the material
	 */
	public Material setkR(Double3 kR) {
		this.kR = kR;
		return this;
	}

	
	/**
	 * getter function
	 * @return Double3 kD
	 */
	public Double3 getkD() {
		return kD;
	}
	
	/**
	 * getter function
	 * @return int nShininess
	 */
	public int getNshininess() {
		return nShininess;
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
