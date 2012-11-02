package studio4harkka2;

/**
 * This class is a slight variation of the ballPhase class, in this class
 * balls have different colours
 *
 * @author Gandalf.
 *         Created 2.11.2012.
 */
public class EpilepticBallPhase  extends Phase
{

	/**
	 * This creates the phase, but doesn't do anything special
	 *
	 * @param parentApplet the Applet to which all the balls are drawn
	 */
	public EpilepticBallPhase(Studio4Harkka2 parentApplet)
	{
		super(parentApplet, 4000);
	}

	@Override
	public void onStart()
	{
		// Sets the applet's visuals
		getApplet().setVisuals(0, 0, 0, 0, 0);
		// adds the placer(s)
		addPlacer(new BallPlacer(getApplet(), 0, 100));
	}

}
