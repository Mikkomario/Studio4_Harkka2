package studio4harkka2;

import java.util.ArrayList;

/**
 * This class represents a single phase in the program's flow
 * Each phase creates a few placers. Each phase should include at least one
 * interactive phase
 *
 * @author Gandalf.
 *         Created 31.10.2012.
 */
public abstract class Phase
{
	// ATTRIBUTES	------------------------------------------------------
	
	private int duration;
	private int startDuration;
	private Studio4Harkka2 applet;
	private ArrayList<Placer> placers;
	private boolean active;
	
	// CONSTRUCTOR	------------------------------------------------------
	
	/**
	 * 
	 * This creates a new phase, but doesn't actually start it.
	 *
	 * @param parentApplet the applet to which the particles are drawn
	 * This applet must be Studio4Harkka2
	 * @param duration the duration of this phase in steps / frames (Negative
	 * number means infinite)
	 */
	public Phase(Studio4Harkka2 parentApplet, int duration)
	{
		this.applet = parentApplet;
		this.duration = duration;
		this.startDuration = duration;
		this.placers = new ArrayList<Placer>();
		this.active = false;
		
		//this.applet.addPhase(this);
	}
	
	
	// ABSTRACT METHODS	---------------------------------------------------
	
	/**
	 * 
	 * This method is called upon the creation of the phase
	 * It should affect the applet and possibly add some placers
	 *
	 */
	public abstract void onStart();
	
	
	// GETTERS & SETTERS	-----------------------------------------------
	
	/**
	 * @return the applet to which all the particles are drawn
	 */
	public Studio4Harkka2 getApplet()
	{
		return this.applet;
	}
	
	/**
	 * Inactive phases needn't be updated
	 *
	 * @return is the current Phase active (drawn)
	 */
	public boolean isActive()
	{
		return this.active;
	}
	
	// OTHER METHODS	---------------------------------------------------
	
	/**
	 * 
	 * This method adds a placer to the drawn placers. This method should be
	 * called instead of the Studio4Harkka2's corresponding method since it
	 * also cleans up the placer afterwards
	 *
	 * @param p the placer to be added
	 */
	public void addPlacer(Placer p)
	{
		if (!this.placers.contains(p))
			this.placers.add(p);
		this.applet.addPlacer(p);
	}
	
	/**
	 * 
	 * This method removes a placer from the drawn placers.
	 * The subclasses should use this method instead of the studio4Harkka2's one
	 *
	 * @param p
	 */
	public void removePlacer(Placer p)
	{
		if (this.placers.contains(p))
			this.placers.remove(p);
		this.applet.removePlacer(p);
	}
	
	// This method handles the duration of the phase and ends if if neccessary
	private void handleDuration()
	{
		//System.out.println(this.duration);
		
		this.duration --;
		
		if (this.duration == 0)
			end();
	}
	
	/**
	 * 
	 * Clears all placers from this phase and from the drawn placers
	 *
	 */
	public void clearPlacers()
	{
		for (int i = 0; i < this.placers.size(); i++)
			this.applet.removePlacer(this.placers.get(i));
		
		this.placers.clear();
	}
	
	/**
	 * 
	 * Makes the phase go inactive, removing all the placers and starting
	 * a new phase
	 *
	 */
	public void end()
	{
		clearPlacers();
		this.active = false;
		
		// Finds a new phase to start
		Phase newphase = this;
		while (newphase.equals(this))
		{
			newphase = this.applet.randomPhase();
			
			// Handles the most inprobable case where there are no phases
			// avaidable
			if (newphase == null)
				// In which case no new phase is started
				return;
			
			// Also checks if the current phase is the only possible
			if (newphase.equals(this) && this.applet.getPhaseNumber() == 1)
				// In which this phase is picked (unlike usually)
				break;
		}
		
		// Starts the new phase
		newphase.start();
	}
	
	/**
	 * 
	 * Makes an inactive phase start over.
	 *
	 */
	public void start()
	{
		clearPlacers();
		this.duration = this.startDuration;
		this.active = true;
		getApplet().setCurrentPhase(this);
		onStart();
	}
	
	/**
	 * 
	 * This method is called each time the screen is drawn
	 * 
	 * Use the following syntax if you want the phase do something
	 * on this event:
	 * public void onStep()
	 * {
	 * super.onStep();
	 * your code
	 * }
	 *
	 */
	public void onStep()
	{
		handleDuration();
	}
}
