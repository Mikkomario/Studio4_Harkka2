package studio4harkka2;

/**
 * This phase has the familiar lines and white flowers
 *
 * @author Gandalf.
 *         Created 2.11.2012.
 */
public class LineFlowerPhase extends Phase
{
	/**
	 * This creates the phase, but doesn't do anything special
	 *
	 * @param parentApplet the applet to which all the particles are drawn
	 */
	public LineFlowerPhase(Studio4Harkka2 parentApplet)
	{
		super(parentApplet, 4000);
	}

	@Override
	public void onStart()
	{
		// Changes the applet's visuals
		getApplet().setVisuals(0, 0, 0, 100, 0);
		
		// Adds placers
		addPlacer(new PetalPlacer(getApplet(), true));
		addPlacer(new LinePlacer(getApplet()));
	}

}
