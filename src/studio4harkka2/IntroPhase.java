package studio4harkka2;

import processing.core.PImage;

/**
 * This will draw the title screen
 *
 * @author Gandalf.
 *         Created 2.11.2012.
 */
public class IntroPhase extends Phase
{
	// ATTRIBUTES	------------------------------------------------------
	
	private PImage img;
	
	
	// CONSTRUCTOR	------------------------------------------------------
	
	/**
	 * Creates the phase and downloads image(s) but doesn't draw anthing
	 *
	 * @param parentApplet the applet to which the title screen is drawn
	 */
	public IntroPhase(Studio4Harkka2 parentApplet)
	{
		super(parentApplet, 500);
		
		this.img = getApplet().loadImage("willib.png");
	}
	
	
	// IMPLEMENTED METHODS	----------------------------------------------

	@Override
	public void onStart()
	{
		// Sets simple visuals
		getApplet().setVisuals(0, 0, 0, 100, 0);
	}
	
	@Override
	public void onStep()
	{
		// Does the normal things and also draws the background
		super.onStep();
		getApplet().image(this.img, 0, 0);
	}

}
