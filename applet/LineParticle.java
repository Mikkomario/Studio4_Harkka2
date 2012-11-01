package studio4harkka2;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * This is a small example of how to create your own particle classes.
 * Each particle is a simple rectangle that travels around the screen and
 * dies when goes outside.
 *
 * @author Marde.
 *         Created 29.10.2012.
 */
public class LineParticle extends Particle
{

	/**
	 * This creates a new test particle that lasts indefinitely and doesn't
	 * slow down. Each particle is created to the center of the screen.
	 *
	 * @param rotationFriction how much the rotation slows each step
	 * @param xscale how much the particle is scaled horizontally
	 * @param yscale how much the particle is scaled vertically
	 * @param parentPlacer Who created this particle
	 * @param parentApplet To what screen this particle will be drawn
	 */
	
	private PImage rs;
	
	public LineParticle(Placer parentPlacer,
			PApplet parentApplet)
	{
		super(0, parentApplet.height/2, 10, 0, 40,
				0, 1, 1, -1,
				parentPlacer, parentApplet);

	}


	// INHERITED METHODS -------------------------------------------------

	@Override
	public void drawSelf()
	{
		//System.out.println("Draws a particle!");

		// Calculates the strokeWeight
		float weight = 18;

		getApplet().strokeWeight(weight);
		getApplet().stroke(0, 0, 100);

		// Draws self (Positioning is done automatically!!!)
		// Notice the origin position (25, 25)
		getApplet().line(0, -(this.getApplet().height/2), 0, this.getApplet().height/2);

	}

	@Override
	public boolean positionIsOver(int x, int y)
	{
		// It is actually really hard to know if a position is over the the rect
		// since it can be scaled and rotated randomly.
		// Notice that particle's position refer's to the
		// position of it's origin

		// Collision detection might be done by dividing the distance to
		// two vectors using cos and sin and stuff but I hate them so I don't
		//int maxDist = (int) (25*(Math.max(getXScale(), getYScale())));

		return false;
	}

	@Override 
	public void onMouseOver()
	{
		// When mouse is over the lines will start spinning uncontrollably
//		if (getRotation() == 0)
//			setRotation(20*(rand.nextDouble() - 0.5));
//		else
//			addRotation(getRotation()*0.05);
	}

	@Override
	public void onMousePressed()
	{

	}

	@Override
	public void onMouseDown()
	{
		// Doesn't do anything particular
	}
}