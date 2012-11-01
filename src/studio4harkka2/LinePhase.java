package studio4harkka2;

/**
 * In this phase balls flow around the screen and the screen is quite static
 *
 * @author Marde.
 *         Created 31.10.2012.
 */
public class LinePhase extends Phase
{	
	// CONSTRUCTOR	------------------------------------------------------

	/**
	 * This creates a new linephase with a quite long duration
	 *
	 * @param parentApplet the applet to which the particles are drawn
	 */
	public LinePhase(Studio4Harkka2 parentApplet)
	{
		super(parentApplet, 4000);
	}
	
	
	// IMPLEMENTED METHODS	---------------------------------------------

	@Override
	public void onStart()
	{
		// Changes the applet's visuals
		//getApplet().setVisuals(0, 10, 80, 10, 0);
		getApplet().setVisuals(0, 0, 0, 100, 0);		
		// Adds placers
		addPlacer(new LinePlacer(getApplet()));
	}

}