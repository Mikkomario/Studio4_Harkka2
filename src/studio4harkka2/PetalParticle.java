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
	// ATTRIBUTES ---------------------------------------------------------
	
	private int colour;
	private int saturation;
	private int startDuration;
	
	// CONSTRUCTOR --------------------------------------------------------

	/**
	 * Creates a petal flower with the given attributes
	 *
	 * @param newx
	 * @param newy
	 * @param xscale
	 * @param yscale
	 * @param duration
	 * @param parentPlacer
	 * @param parentApplet
	 * @param colour The hue of the petal
	 * @param saturation the saturation of the petal
	 */
	public PetalParticle(int newx, int newy, double xscale, double yscale,
			int duration, Placer parentPlacer, PApplet parentApplet, int colour,
			int saturation)
	{
		super(newx, newy, 20, 0.2, 60, 0, xscale,
				yscale, duration, parentPlacer, parentApplet);

		// Initializes attributes
		this.colour = colour;
		this.startDuration = duration;
		this.saturation = saturation;
	}
	
	
	// IMPLEMENTED METHODS -------------------------------------------------

	@Override
	public void drawSelf()
	{
		//float brightness = (float) (1.5*Math.PI + Math.sin((getDuration() /
				//((double) this.startDuration))*0.5*Math.PI)) * 100;
		
		// Sets the color and stuff
		getApplet().noStroke();
		getApplet().fill(this.colour, this.saturation, (float) (((getDuration()
				/ ((double) this.startDuration))) * 200), 80);
		//getApplet().fill(this.colour, 100, brightness);
		
		// Draws a rectangle with the origin on the bottom (sorta)
		//System.out.println(getYScale());
		
		getApplet().ellipse(-25, -30, 50, 50);
	}

	@Override
	public boolean positionIsOver(int x, int y)
	{
		int maxDist = (int) (50*getYScale());
		
		return (Studio4Harkka2.pointDistance(getX(), getY(),
				x, y) < maxDist);
	}

	@Override
	public void onMouseOver()
	{
		// Doesn't do anything particular
	}

	@Override
	public void onMousePressed()
	{
		// Nothing interesting here
		
	}

	@Override
	public void onMouseDown()
	{
		// Nope
		
	}
	
}
