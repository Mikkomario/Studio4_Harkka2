package studio4harkka2;

/**
 * During this phase, rectangles are drawn and flowers spawn on click
 * The theme is quite yellow / orange / red
 *
 * @author Gandalf.
 *         Created 31.10.2012.
 */
public class RectFlowerPhase extends Phase
{

	/**
	 * Creates a new rectflowerPhase with a decent duration
	 *
	 * @param parentApplet the applet to which the particles are drawn
	 */
	public RectFlowerPhase(Studio4Harkka2 parentApplet)
	{
		super(parentApplet, 4000);
	}

	@Override
	public void onStart()
	{
		// Changes the applet's visual style
		getApplet().setVisuals(3, 10, 75, 25, 0.01);
		// Adds some placers
		addPlacer(new RectPlacer(getApplet()));
		addPlacer(new PetalPlacer(getApplet(), false));
	}

}
