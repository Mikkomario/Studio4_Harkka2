package studio4harkka2;

import java.util.Random;

import processing.core.PApplet;

/**
 * This is a small example of how to create your own particle classes.
 * Each particle is a simple rectangle that ttravels around the screen and
 * dies when goes outside.
 *
 * @author Gandalf.
 *         Created 23.10.2012.
 */
public class TestParticle extends Particle
{
	// ATTRIBUTES -----------------------------------------------------------
	
	private int alpha;
	private int colour;
	private static Random rand = new Random();
	
	
	// CONSTRUCTOR ---------------------------------------------------------

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
	public TestParticle(double rotationFriction,
			double xscale, double yscale, Placer parentPlacer,
			PApplet parentApplet)
	{
		super(parentApplet.width / 2, parentApplet.height /2, 100, 0, 20,
				rotationFriction, xscale, yscale, -1,
				parentPlacer, parentApplet);
		
		// Each particle is invisible in the beginning
		this.alpha = 0;
		
		// Desides particle's colour
		this.colour = rand.nextInt(101);
	}
	
	
	// INHERITED METHODS -------------------------------------------------

	@Override
	public void drawSelf()
	{
		//System.out.println("Draws a particle!");
		
		// Calculates the strokeWeight
		double weight = 3*getXScale()*getYScale();
		if (weight < 1)
			weight = 1;
		
		//System.out.println("SW: " + weight);
		
		getApplet().strokeWeight((float) weight);

		// Changes the colour / opacity
		getApplet().stroke(this.colour, 100, 100,
				this.alpha);
		
		//System.out.println("Colour: " + this.colour);
		//System.out.println("Al");
		
		// Sets fill to none
		getApplet().noFill();
		
		// Draws self (Positioning is done automatically!!!)
		// Notice the origin position (25, 25)
		getApplet().rect(-25, -25, 50, 50);
		
	}

	@Override
	public boolean positionIsOver(int x, int y)
	{
		// It is acually really hard to know if a position is over the the rect
		// since it can be scaled and rotated randomly.
		// Notice that particle's position refer's to the
		// position of it's origin
		
		// Collision detection might be done by dividing the distance to
		// two vectors using cos and sin and stuff but I hate them so I don't
		int maxDist = (int) (25*(Math.max(getXScale(), getYScale())));
		
		return (Studio4Harkka2.pointDistance(getX(), getY(),
				x, y) < maxDist);
	}

	@Override
	public void onMouseOver()
	{
		// When mouse is over the rect it will start spinning uncontrollably
		if (getRotation() == 0)
			setRotation(20*(rand.nextDouble() - 0.5));
		else
			addRotation(getRotation()*0.05);
	}

	@Override
	public void onMousePressed()
	{
		// If particle is clicked it dies :(
		kill();
	}

	@Override
	public void onMouseDown()
	{
		// Doesn't do anything particular
	}
	
	
	// OTHER METHODS ------------------------------------------------------
	
	/**
	 * 
	 * Makes the particle more visible
	 *
	 */
	public void increaseAlpha()
	{
		if (this.alpha < 255)
			this.alpha += 5;
	}
}
