package studio4harkka2;

import java.util.Random;

import processing.core.PApplet;

/**
 * This placer creates a bunch of bouncy balls when the mouse is pressed down
 *
 * @author Gandalf.
 *         Created 30.10.2012.
 */
public class BallPlacer extends Placer
{
	// ATTRIBUTES	------------------------------------------------------
	
	private static Random rand = new Random();
	
	private int tillCreation;
	
	
	// CONSTRUCTOR	------------------------------------------------------

	/**
	 * This creates a BallPlacer that will be ready to create balls when needed
	 *
	 * @param parentApplet
	 */
	public BallPlacer(PApplet parentApplet)
	{
		super(parentApplet);
		
		this.tillCreation = 1;
	}
	
	
	// IMPLEMENTED METHODS	----------------------------------------------

	@Override
	public Particle generateParticle()
	{
		// Creates a new ball with some variation
		// All balls will be positioned to the mouse's position
		double scl = 0.2 + rand.nextDouble() * 2.8;
		double grdir = rand.nextDouble()*2*Math.PI;
		double grfrc = 0.1 + rand.nextDouble()*0.4;
		int dur = rand.nextInt(1000);
		
		BallParticle newball = new BallParticle(getApplet().mouseX,
				getApplet().mouseY, scl, grdir, grfrc, dur, this, getApplet());
		
		// Also adds some velocity to start with
		newball.addDirectionalVelocity(rand.nextDouble()*2*Math.PI,
				rand.nextDouble()*5);
		
		return newball;
	}

	@Override
	public void onStep()
	{
		// If the mouse is pressed, creates new balls
		if (!mouseDown())
			return;
		
		this.tillCreation --;
		
		if (this.tillCreation <= 0)
		{
			addParticle();
			this.tillCreation = 10;
		}
	}
}
