package studio4harkka2;

import processing.core.PApplet;

/**
 * This particle represents a single flower petal. It simply stays in place
 * for a while and dies away
 *
 * @author Gandalf.
 *         Created 29.10.2012.
 */
public class PetalParticle extends Particle
{
	
	// CONSTRUCTOR --------------------------------------------------------

	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 * @param newx
	 * @param newy
	 * @param maxVelocity
	 * @param friction
	 * @param maxRotation
	 * @param rotationFriction
	 * @param xscale
	 * @param yscale
	 * @param duration
	 * @param parentPlacer
	 * @param parentApplet
	 */
	public PetalParticle(int newx, int newy, double xscale, double yscale,
			int duration, Placer parentPlacer, PApplet parentApplet)
	{
		super(newx, newy, 20, 0.2, 30, 0.2, xscale,
				yscale, duration, parentPlacer, parentApplet);
		// TODO Auto-generated constructor stub.
	}
	
	
	// IMPLEMENTED METHODS -------------------------------------------------

	@Override
	public void drawSelf()
	{
		// TODO Auto-generated method stub.
		
	}

	@Override
	public boolean positionIsOver(int x, int y)
	{
		// TODO Auto-generated method stub.
		return false;
	}

	@Override
	public void onMouseOver()
	{
		// TODO Auto-generated method stub.
		
	}

	@Override
	public void onMousePressed()
	{
		// TODO Auto-generated method stub.
		
	}

	@Override
	public void onMouseDown()
	{
		// TODO Auto-generated method stub.
		
	}
	
}
