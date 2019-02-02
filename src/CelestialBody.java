

/**
 * Celestial Body class for NBody
 * @author ola
 *
 */
public class CelestialBody {

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */

	// instance variables / internal data

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		// TODO: complete constructor
		this.myXPos = xp;
		this.myYPos = yp;
		this.myXVel = xv;
		this.myYVel = yv;
		this.myMass = mass;
		this.myFileName = filename;
	}

	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public CelestialBody(CelestialBody b){
		// TODO: complete constructor
		this.myXPos = b.getX();
		this.myYPos = b.getY();
		this.myXVel = b.getXVel();
		this.myYVel = b.getYVel();
		this.myMass = b.getMass();
		this.myFileName = b.getName();
	}

	public double getX() {
		// TODO: complete method
		return this.myXPos;
	}
	public double getY() {
		// TODO: complete method
		return this.myYPos;
	}
	public double getXVel() {
		// TODO: complete method
		return this.myXVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		// TODO: complete method
		return this.myYVel;
	}
	
	public double getMass() {
		// TODO: complete method
		return this.myMass;
	}
	public String getName() {
		// TODO: complete method
		return this.myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		// TODO: complete method

		double xone = this.myXPos;
		double yone = this.myYPos;
		double xtwo = b.getX();
		double ytwo = b.getY();

		double dx = xone - xtwo;
		double dy = yone - ytwo;

		double rsquared = Math.pow(dx, 2) + Math.pow(dy, 2);
		return Math.sqrt(rsquared);
	}

	/**
	 * Return the force exerted on this body by the body specified as the parameter
	 * @param p the other body used to calculate the force exerted on this body
	 * @return force exerted on this body by p
	 */
	public double calcForceExertedBy(CelestialBody p) {
		// TODO: complete method

		double m1 = this.myMass;
		double m2 = p.getMass();
		double productOfMass = m1 * m2;
		double r = this.calcDistance(p);
		double rsquared = Math.pow(r, 2);

		double gVar = 6.67 * 1e-11;
		return gVar * (productOfMass / rsquared);

	}

	/**
	 * Return the force exerted in the X direction
	 * @param p the other body used with this body in the calculation
	 * @return the force exerted in the X direction
	 */
	public double calcForceExertedByX(CelestialBody p) {
		// TODO: complete method

		double totalForce = this.calcForceExertedBy(p);
		double r = this.calcDistance(p);
		double xone = this.myXPos;
		double xtwo = p.getX();
		double dx = xtwo - xone;
		double forceExertedByX = (totalForce * dx) / r;

		return forceExertedByX;

	}

	/**
	 * Return the force exerted in the Y direction
	 * @param p the other body used with this body in the calculation
	 * @return the force exerted in the Y direction
	 */
	public double calcForceExertedByY(CelestialBody p) {
		// TODO: complete method

		double totalForce = this.calcForceExertedBy(p);
		double r = this.calcDistance(p);
		double yone = this.myYPos;
		double ytwo = p.getY();
		double dy = ytwo - yone;
		double forceExertedByY = (totalForce * dy) / r;

		return forceExertedByY;

	}

	/**
	 * Return the net force exerted on this body by all the bodies in the array parameter in the X direction
	 * @param bodies the CelestialBody object array containing the body objects
	 * @return the net force exerted on this body by all the bodies in the array parameter in the Y direction
	 */
	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		// TODO: complete method

		double sumPairwise = 0.0;

		for(int i = 0; i < bodies.length; i++) {
			if (! bodies[i].equals(this)){
				double xForce = this.calcForceExertedByX(bodies[i]);
				sumPairwise += xForce;
			}
		}
		return sumPairwise;
	}


	/**
	 * Return the net force exerted on this body by all the bodies in the array parameter in the Y direction
	 * @param bodies the CelestialBody object array containing the body objects
	 * @return the net force exerted on this body by all the bodies in the array parameter in the Y direction
	 */
	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		// TODO: complete method
		double sumPairwise = 0.0;

		for(int i = 0; i < bodies.length; i++) {
			if (! bodies[i].equals(this)){
				double yForce = this.calcForceExertedByY(bodies[i]);
				sumPairwise += yForce;
			}
		}
		return sumPairwise;
	}


	/**
	 * Update the instance variables of the CelestialBody object on which it's called
	 * @param deltaT time-step value
	 * @param xforce net force in the x-direction exerted on this body by all other bodies in the simulation
	 * @param yforce net force in the y-direction exerted on this body by all other bodies in the simulation
	 */

	public void update(double deltaT, 
			           double xforce, double yforce) {
		// TODO: complete method

		double ax = xforce / this.myMass;
		double ay = yforce / this.myMass;

		double nvx = this.myXVel + deltaT * ax;
		double nvy = this.myYVel + deltaT * ay;

		double nx = this.myXPos + deltaT * nvx;
		double ny = this.myYPos + deltaT * nvy;

		this.myXPos = nx;
		this.myYPos = ny;
		this.myXVel = nvx;
		this.myYVel = nvy;

	}


	/**
	 * Draw a body using instance variables myXPos, myYPos, myFileName
	 */
	public void draw() {
		// TODO: complete method
		StdDraw.picture(myXPos, myYPos, "images/" + myFileName);
	}
}
