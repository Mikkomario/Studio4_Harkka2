package studio4harkka2;

import processing.core.PApplet;

/**
 * This class represents a single phase in the program's flow
 * Each phase creates a few placers
 *
 * @author Gandalf.
 *         Created 31.10.2012.
 */
public abstract class Phase
{
	// ATTRIBUTES	------------------------------------------------------
	
	private int duration;
	private Studio4Harkka2 applet;
	
	/**
	 * 
	 * This creates a new phase that starts to act somehow (determined in the
	 * subclasses)
	 *
	 * @param parentApplet the applet to which the particles are drawn
	 * This applet must be Studio4Harkka2
	 * @param duration the duration of this phase in steps / frames
	 */
	public Phase(Studio4Harkka2 parentApplet, int duration)
	{
		this.applet = parentApplet;
		this.duration = duration;
		
		onCreation();
	}
	
	/**
	 * 
	 * This method is called upon the creation of the phase
	 * It should affect the applet
	 *
	 */
	public abstract void onCreation();
}
