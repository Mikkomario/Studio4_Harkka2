package studio4harkka2;

import processing.core.PApplet;

/**
 * Particle repesents a single visual image drawn on the screen.
 * The class is abstract so it may be a square or a flower or a dot.
 * Each particle knows its position, has a velocity and can draw itself
 * onto the screen. Each particle may also react to the mouse somehow.
 *
 * @author Gandalf.
 *         Created 22.10.2012.
 */

// TODO: Do offsets

public abstract class Particle
{
	// ATTRIBUTES --------------------------------------------------------
	private double[] position; // [x, y]
	private double[] velocity; // [hspeed (+ = right), vspeed (+ = down)]
	private double maxVelocity;
	private double friction;
	
	private double angle;
	private double rotation; // Positive is Counter clockwise because I say so!
	private double maxRotation;
	private double rotationFriction;
	
	private double xscale;
	private double yscale;
	
	// How long (how many steps) the particle lives (Negative if indefinitely)
	private int duration;
	
	private Placer placer;
	private PApplet applet;
	
	
	// CONSTRUCTOR ------------------------------------------------------
	/**
	 * 
	 * Creates a new particle into the given coordinates. The particle's
	 * velocity is 0 when created and it doesn't spin.
	 *
	 * @param newx new position's x coodrinate
	 * @param newy new position's y coordinate
	 * @param maxVelocity how fast the particle can travel
	 * @param friction how much the particle slows down each step
	 * @param maxRotation how fast the particle can rotate
	 * @param rotationFriction how much the particle's rotation slows at each 
	 * step
	 * @param xscale How much the particle is scaled horizontally
	 * @param yscale How much the particle is scaled vertically
	 * (from the left top corner of the particle)
	 * @param duration How many steps the particle will live? (Negative means
	 * infinite)
	 * @param parentPlacer The placer who created this particle
	 * @param parentApplet the applet with wich the particle is drawn
	 */
	public Particle(int newx, int newy, double maxVelocity, double friction, 
			double maxRotation, double rotationFriction, double xscale,
			double yscale, int duration,
			Placer parentPlacer, PApplet parentApplet)
	{
		// Sets up the variables
		this.position = new double[2];
		this.velocity = new double[2];
		
		setPosition(newx, newy);
		setFriction(friction);
		setMaxVelocity(maxVelocity);
		setVelocity(0, 0);
		
		this.angle = 0;
		setRotationFriction(rotationFriction);
		setMaxRotation(maxRotation);
		setRotation(0);
		
		setScale(xscale, yscale);
		
		this.duration = duration;
		
		this.placer = parentPlacer;
		this.applet = parentApplet;
		
		//System.out.println("Current speeds: " + getHspeed() + ", " + getVspeed());
	}
	
	
	// ABSTRACT METHODS -------------------------------------------------
	
	/**
	 * 
	 * This method draws the particle to its position.
	 * Each subclass desides how to draw itself separately.
	 * 
	 * It is recommended to use giveApplet() -method in the drawing method
	 * 
	 * The particle is put to it's position automatically so the subclasses
	 * should NOT use position in this method
	 *
	 */
	public abstract void drawSelf();
	
	/**
	 * 
	 * This method tells whether the asked position is within the particle
	 * (it collides with the particle). Each particle is shaped differently
	 * so this each subclass defines this by themselves.
	 * 
	 * Also, the tested area should include the nearby area the particle is
	 * interested in.
	 *
	 * @param x the tested position's x coordinate
	 * @param y the tested position's y coordinate
	 * @return is the tested position within the particle
	 * or it's area of interest
	 */
	public abstract boolean positionIsOver(int x, int y);
	
	/**
	 * 
	 * This method is called each step when the mouse is over the particle
	 * Each subclass must define how it reacts to the mouse.
	 *
	 */
	public abstract void onMouseOver();
	
	/**
	 * 
	 * This method is called once each time the particle is clicked.
	 * Each subclass must define how it reacts to the mouse.
	 *
	 */
	public abstract void onMousePressed();
	
	/**
	 * 
	 * This method is called each step the mouse is pressed and over the
	 * particle. Each subclass must define how it reacts to the mouse.
	 *
	 */
	public abstract void onMouseDown();
	
	
	// GETTERS & SETTERS ------------------------------------------------
	
	/**
	 * @return the particle's current x coordinate
	 */
	public int getX()
	{
		return (int) this.position[0];
	}
	
	/**
	 * @return the particle's current y coordinate
	 */
	public int getY()
	{
		return (int) this.position[1];
	}
	
	/**
	 * @return the particle's current horizontal speed
	 */
	public double getHspeed()
	{
		return this.velocity[0];
	}
	
	/**
	 * @return the partcle's current vertical speed
	 */
	public double getVspeed()
	{
		return this.velocity[1];
	}
	
	/**
	 * 
	 * Changes the particle's position on screen
	 *
	 * @param newx the new x coordinate
	 * @param newy the new y coordinate
	 */
	public void setPosition(int newx, int newy)
	{
		this.position[0] = newx;
		this.position[1] = newy;
	}
	
	/**
	 * 
	 * Changes the particle's velocity. The velocity can't get higher than
	 * maxVelocity
	 *
	 * @param hspeed the new horisontal velocity
	 * @param vspeed the new vertical velocity
	 * 
	 * @return the new velocity didn't go over maxVelocity
	 */
	public boolean setVelocity(double hspeed, double vspeed)
	{	
		this.velocity[0] = hspeed;
		this.velocity[1] = vspeed;
		
		//System.out.println("Given speeds: " + hspeed + ", " + vspeed);
		//System.out.println("Current speeds: " + getHspeed() + ", " + getVspeed());
		
		return !checkMaxVelocity();
	}
	
	/**
	 * @return the particle's current velocity [hspeed, vspeed]
	 */
	public double getMaxVelocity()
	{
		return this.maxVelocity;
	}
	
	/**
	 * 
	 * Changes the particle's maximum speed
	 *
	 * @param newMaxVelocity new maximum speed
	 */
	public void setMaxVelocity(double newMaxVelocity)
	{
		if (newMaxVelocity < 0)
			this.maxVelocity = 0;
		else
			this.maxVelocity = newMaxVelocity;
	}
	
	/**
	 * @return how much the particle is slowed at each step
	 */
	public double getFriction()
	{
		return this.friction;
	}
	
	/**
	 * 
	 * Sets how much the particle is slowed at each step
	 *
	 * @param newFriction the new friction
	 */
	public void setFriction(double newFriction)
	{
		this.friction = newFriction;
	}
	
	/**
	 * @return the applet within which the particle is drawn
	 */
	public PApplet getApplet()
	{
		return this.applet;
	}
	
	/**
	 * 
	 * Sets the particle to rotate with the given rotation speed
	 *
	 * @param newRotation new rotation speed
	 * @return whether the rotation ended up same as the given rotation
	 */
	public boolean setRotation(double newRotation)
	{
		this.rotation = newRotation;
		
		return !checkMaxRotation();
	}
	
	/**
	 * 
	 * Adjusts how quickly the rotation will end
	 *
	 * @param newFriction the amount which rotation is slowed each step
	 */
	public void setRotationFriction(double newFriction)
	{
		this.rotationFriction = newFriction;
	}
	
	/**
	 * 
	 * Sets how fast the particle can rotate
	 *
	 * @param newMaxRotation how fast the particle can rotate
	 */
	public void setMaxRotation(double newMaxRotation)
	{
		if (newMaxRotation < 0)
			this.maxRotation = 0;
		else
			this.maxRotation = newMaxRotation;
	}
	
	/**
	 * @return Particle's current rotation speed
	 */
	public double getRotation()
	{
		return this.rotation;
	}
	
	/**
	 * @return Particle's current angle
	 */
	public int getAngle()
	{
		return (int) this.angle;
	}
	
	/**
	 * 
	 * Changes the particle's angle
	 *
	 * @param newAngle The particle's new angle
	 */
	public void setAngle(int newAngle)
	{
		this.angle = newAngle;
	}
	
	/**
	 * 
	 * Scales the particle with the given values
	 *
	 * @param xscale particle's horizontal scaling (1 = normal)
	 * @param yscale particle's vertical scaling (1 = normal)
	 */
	public void setScale(double xscale, double yscale)
	{
		this.xscale = xscale;
		this.yscale = yscale;
	}
	
	/**
	 * @return how much the particle is scaled horizontally
	 */
	public double getXScale()
	{
		return this.xscale;
	}
	
	/**
	 * @return how much the particle is scaled vertivally
	 */
	public double getYScale()
	{
		return this.yscale;
	}
	
	/**
	 * 
	 * Changes the particle's remaining life time
	 *
	 * @param newDuration How many steps will the particle live? (Negative if
	 * infinite)
	 */
	public void setDuration(int newDuration)
	{
		this.duration = newDuration;
	}
	
	/**
	 * @return How many steps the particle will still live
	 * Negative means infinite
	 */
	public int getDuration()
	{
		return this.duration;
	}
	
	
	// OTHER METHODS ----------------------------------------------------
	
	// Checks if the speed is too large (or small) and reduces it
	// Returns whether the speed was reduced
	private boolean checkMaxVelocity()
	{
		double speed = Math.abs(getHspeed()) + Math.abs(getVspeed());
		
		// If the speed is too large, reduces it to maximum level
		if (speed > this.maxVelocity)
		{
			this.velocity[0] *= this.maxVelocity / speed;
			this.velocity[1] *= this.maxVelocity / speed;
			
			return true;
		}
		return false;
	}
	
	// Checks if the rotation speed is too large and reduces it
	// Returns whether the rotation speed was reduced
	private boolean checkMaxRotation()
	{
		if (getRotation() > this.maxRotation)
		{
			this.rotation = this.maxRotation;
			return true;
		}
		
		return false;
	}
	
	// Slows the speed the amount of given friction
	private void AddFriction()
	{
		// Calculates the old speed
		double lastSpeed = Math.abs(getHspeed()) + Math.abs(getVspeed());
		double newSpeed = lastSpeed;
		
		// Calculates the new speed
		if (lastSpeed < getFriction())
		{
			// Changes the velocity
			this.velocity[0] = 0;
			this.velocity[1] = 0;
		}
		else
		{
			newSpeed -= getFriction();
			// Changes the velocity
			this.velocity[0] *= newSpeed / lastSpeed;
			this.velocity[1] *= newSpeed / lastSpeed;
		}
	}
	
	// Slows the rotation speed the amoutn of given friction
	private void addRotationFriction()
	{
		//double testRot = getRotation();
		
		if (Math.abs(getRotation()) <= this.rotationFriction)
			this.rotation = 0;
		else if (getRotation() > 0)
		{
			this.rotation -= this.rotationFriction;
			//System.out.println((testRot - this.rotation) - this.rotationFriction);
		}
		else
		{
			this.rotation += this.rotationFriction;
			//System.out.println((this.rotation - testRot) - this.rotationFriction);
		}
	}
	
	/**
	 * 
	 * This method handles the movement of the particle and must be called
	 * each time the particle is drawn. The method handles the friction
	 * and changing the position of the particle.
	 * 
	 * This method also handles the rotation of the particle.
	 * 
	 * And even the duration and death of the particle
	 *
	 */
	public void handleParticle()
	{
		// Checks the friction and the maximum speed
		//System.out.println("Speed1: " + getHspeed() + ", " + getVspeed());
		
		AddFriction();
		//System.out.println("Speed2: " + getHspeed() + ", " + getVspeed());
		checkMaxVelocity();
		
		//System.out.println("Position old: " + getX() + ", " + getY());
		//System.out.println("Speed3: " + getHspeed() + ", " + getVspeed());
		
		// Changes the position of the particle
		this.position[0] += getHspeed();
		this.position[1] += getVspeed();
		
		//System.out.println("Position new: " + getX() + ", " + getY());
		
		// Checks same for rotation
		addRotationFriction();
		checkMaxRotation();
		
		// Changes the angle of the particle
		this.angle += getRotation();
		if (getAngle() < 0)
			this.angle += 360;
		else if (getAngle() > 360)
			this.angle -= 360;
		
		// Changes particle's duration
		handleDuration();
	}
	
	/**
	 * 
	 * This method changes the particle's speed by the given values
	 *
	 * @param haccelration particle's horzontal accelration
	 * @param vaccelration particle's vertical accelration
	 * @return whether the speed didn't go over the maxVelocity
	 */
	public boolean addVelocity(double haccelration, double vaccelration)
	{
		this.velocity[0] += haccelration;
		this.velocity[1] += vaccelration;
		
		return !checkMaxVelocity();
	}
	
	/**
	 * 
	 * The method increases the particle's rotation speed by the given value
	 *
	 * @param momentum particle's rotation speeds change
	 * @return whether the rotation speed didn't go over the limits
	 */
	public boolean addRotation(double momentum)
	{
		this.rotation += momentum;
		
		return !checkMaxRotation();
	}
	
	/**
	 * 
	 * Kills the particle so it no longer does anything and isn't drawn
	 *
	 */
	protected void kill()
	{
		this.placer.removeParticle(this);
	}
	
	// This depletes the duration and kills the particle if neccessary
	private void handleDuration()
	{
		this.duration--;
		
		if (this.duration == 0)
			kill();
	}
}
