package studio4harkka2;

import java.util.ArrayList;

import processing.core.PApplet;

/**
 * This abstract class creates and draws some particles to the screen. Usually
 * the particles are of a same type. This class also handles the movement and
 * events of its particles.
 * 
 * Each subclass creates its own particles and may react differently
 * to user input
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
	 * @param parentApplet the applet with which the particles are drawn
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
	 * Generates a new particle somehow. Each subclass decides how and what
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
	
	/**
	 * @return Is a mouse button pressed
	 */
	public boolean mouseDown()
	{
		return this.mouseDown;
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
		
		if(p != null){
			addParticle(p);
		}
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
		for (int i = 0; i < this.particles.size(); i++)
		{
			Particle p = this.particles.get(i);
			
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
			// and rotates it depending on it's angle
			this.applet.rotate(PApplet.radians(-p.getAngle()));
			// scales it depending on it's xscale and yscale
			this.applet.scale((float) p.getXScale(), (float) p.getYScale());
			
			//System.out.println("Piirtaa partikkelin");
			p.drawSelf();
			
			/*
			getApplet().fill(0);
			getApplet().stroke(0);
			getApplet().point(0, 0);
			*/
			
			// Loads the previous translation & rotation
			this.applet.popMatrix();
		}
	}
}
