package studio4harkka2;

/**
 * In this phase balls flow around the screen and the screen is quite static
 *
 * @author Gandalf.
 *         Created 31.10.2012.
 */
public class BallPhase extends Phase
{	
	// CONSTRUCTOR	------------------------------------------------------

	/**
	 * This creates a new ballphase with a quite long duration
	 *
	 * @param parentApplet the applet to which the particles are drawn
	 */
	public BallPhase(Studio4Harkka2 parentApplet)
	{
		super(parentApplet, 4000);
	}
	
	
	// IMPLEMENTED METHODS	---------------------------------------------

	@Override
	public void onStart()
	{
		// Changes the applet's visuals
		getApplet().setVisuals(0, 10, 80, 0, 0.05);
		
		// Adds placers
		addPlacer(new BallPlacer(getApplet()));
	}

}
