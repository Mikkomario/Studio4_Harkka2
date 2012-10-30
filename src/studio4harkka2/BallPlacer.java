package studio4harkka2;

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
		
		
		return new BallParticle(getApplet().mouseX, getApplet().mouseY,
				scl, grdir, grfrc, dur, this, getApplet());
	}

	@Override
	public void onStep()
	{
		// TODO Auto-generated method stub.
		
	}

}
