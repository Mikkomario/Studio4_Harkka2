package studio4harkka2;

import processing.core.PApplet;

/**
 * This class represents a simple line that travels from the left to the
 * right
 *
 * @author Marde.
 *         Created 29.10.2012.
 */
public class LineParticle extends Particle
{
	/**
	 * This creates a new line that travels from the left side of the screen
	 * to the right side.
	 *
	 * @param parentPlacer Who created this particle
	 * @param parentApplet To what screen this particle will be drawn
	 */
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

		// Calculates the strokeWeight
		float weight = 18;

		getApplet().strokeWeight(weight);
		getApplet().stroke(0, 0, 100);
		getApplet().line(0, -(this.getApplet().height/2), 0, this.getApplet().height/2);

	}

	@Override
	public boolean positionIsOver(int x, int y)
	{

		return false;
	}

	@Override 
	public void onMouseOver()
	{
		// Does nothing
	}

	@Override
	public void onMousePressed()
	{
		// Does nothing
	}

	@Override
	public void onMouseDown()
	{
		// Does nothing
	}
}