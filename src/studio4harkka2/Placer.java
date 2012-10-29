package studio4harkka2;

import java.util.ArrayList;

import processing.core.PApplet;

/**
 * TODO Put here a description of what this class does.
 *
 * @author Gandalf.
 *         Created 22.10.2012.
 */
public abstract class Placer
{
	// ATTRIBUTES --------------------------------------------------------
	
	private PApplet applet;
	private ArrayList<Particle> particles;
	private boolean mouseDown;
	
	
	// CONSTRUCTOR -------------------------------------------------------
	
	/**
	 * 
	 * Creates a new placer with no data
	 *
	 * @param parentApplet the applet with wich the particles are drawn
	 */
	public Placer(PApplet parentApplet)
	{
		this.applet = parentApplet;
		this.particles = new ArrayList<Particle>();
		this.mouseDown = false;
	}
	
	
	// ABSTRACT METHODS --------------------------------------------------
	
	/**
	 * 
	 * Generates a new particle somehow. Each subclass desides how and what
	 * kind of particles it generates.
	 *
	 * @return the particle generated
	 */
	public abstract Particle generateParticle();
	
	/**
	 * 
	 * This method is called each time the screen is drawn. Here subclasses
	 * should handle all actions that depend on time. This method can also
	 * be left empty if the placer doesn't change anyhow.
	 *
	 */
	public abstract void onStep();
	
	
	// GETTERS & SETTERS -------------------------------------------------
	
	/**
	 * @return The window / applet to which the particles are drawn
	 */
	public PApplet getApplet()
	{
		return this.applet;
	}
	
	/**
	 * @return how many particles are placed on the screen
	 */
	public int getSize()
	{
		return this.particles.size();
	}
	
	/**
	 * 
	 * Returns a single particle from the particles of the screen
	 * Returns null if no such index exists
	 *
	 * @param index the index of the particle
	 * @return particle with the given index or null
	 */
	public Particle getParticle(int index)
	{
		if (index < 0 || index >= getSize())
			return null;
		
		return this.particles.get(index);
	}
	
	// OTHER METHODS -----------------------------------------------------
	
	/**
	 * 
	 * Adds the given particle to the drawn particles
	 *
	 * @param p the particle added
	 * @return whether the particle was successfully added
	 */
	public boolean addParticle(Particle p)
	{
		if (this.particles.contains(p))
			return false;
		
		this.particles.add(p);
		return true;
	}
	
	/**
	 * 
	 * Adds a generated particle to the drawn particles
	 * 
	 * @return the particle added
	 */
	public Particle addParticle()
	{
		Particle p = generateParticle();
		
		addParticle(p);
		
		return p;
	}
	
	/**
	 * 
	 * Removes the given particle from the drawn particles
	 *
	 * @param p the particle to be removed
	 * @return whether the particle was successfully removed 
	 */
	public boolean removeParticle(Particle p)
	{
		if (!this.particles.contains(p))
			return false;
		
		this.particles.remove(p);
		return true;
	}
	
	/**
	 * 
	 * Removes all particles from the drawn particles
	 *
	 */
	public void clearAllParticles()
	{
		this.particles = new ArrayList<Particle>();
	}
	
	/**
	 * 
	 * This method reacts to when the mouse is clicked and should be called
	 * each time the mousebutton is pressed
	 *
	 */
	public void onMousePressed()
	{
		this.mouseDown = true;
		
		// Calls mousepressed for each particle pressed
		for (int i = 0; i < getSize(); i++)
		{
			Particle p = getParticle(i);
			
			if (p.positionIsOver(this.applet.mouseX, this.applet.mouseY))
				p.onMousePressed();
		}
	}
	
	/**
	 * 
	 * This method reacts to when the mouse is released
	 *
	 */
	public void onMouseReleased()
	{
		this.mouseDown = false;
	}
	
	/**
	 * 
	 * Draws each particle and handles their movement. This should be called
	 * each time the particles need to be drawn.
	 * 
	 * Also handles the mouseListening for each particle.
	 *
	 */
	public void handleParticles()
	{	
		for (Particle p : this.particles)
		{
			// Checks whether the mouse is over the particle
			if (p.positionIsOver(this.applet.mouseX, this.applet.mouseY))
			{
				p.onMouseOver();
				
				// Checks whether the mouse is also pressed down
				if (this.mouseDown)
					p.onMouseDown();
			}
			
			p.handleParticle();
			
			// Handles the particle's origin, rotation and scaling and draws it 
			// Saves the previous translation & rotation
			this.applet.pushMatrix();
			
			// Translates the particle to it's position
			this.applet.translate(p.getX(), p.getY());
			// Translates the particle to depending on it's origin
			//this.applet.translate(p.getXOffset(), p.getYOffset());
			// and rotates it depending on it's angle
			this.applet.rotate(PApplet.radians(-p.getAngle()));
			// scales it depending on it's xscale and yscale
			this.applet.scale((float) p.getXScale(), (float) p.getYScale());
			
			p.drawSelf();
			
			// Loads the previous translation & rotation
			this.applet.popMatrix();
		}
	}
}
