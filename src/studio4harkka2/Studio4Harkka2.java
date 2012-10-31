package studio4harkka2;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;

/**
 * 
 * This class handles the window, collects data from other objects and
 *  draws it on the screen.
 *
 * @author Gandalf.
 *         Created 22.10.2012.
 */
public class Studio4Harkka2 extends PApplet
{	
	// ATTRIBUTES -------------------------------------------------------
	
	private static Random rand = new Random();
	
	private ArrayList<Placer> placers;
	private double colorangle;
	private int colourRange, mincolour;
	private double colorfrequency;
	private int brightness;
	private int alpha;
	private ArrayList<Phase> phases;
	
	
	// BASIC METHODS ----------------------------------------------------
	
	@Override
	public void setup()
	{
		size(600, 600);
		
		// Changes the colormode to HSB (Hue, saturation, brightness)
		// max value being 100
		colorMode(HSB, 100);
		
		this.placers = new ArrayList<Placer>();
		this.phases = new ArrayList<Phase>();
		this.colorangle = 0;
		this.colorfrequency = 0.01;
		this.mincolour = 0;
		this.colourRange = 14;
		this.brightness = 70;
		this.alpha = 20;
		
		// TODO: Add some placers here and remove the test placer
		//addPlacer(new RectPlacer(this));
		//addPlacer(new TestPlacer(this));
		//addPlacer(new PetalPlacer(this));
		//addPlacer(new BallPlacer(this));
		addPlacer(new HippiePlacer(this));
	}

	@Override
	public void draw()
	{
		// Draws the background with a small opacity so that old steps
		// fade away
		//background(255);
		int fillColour = (int) (this.mincolour + this.colourRange/2 +
				this.colourRange/2*Math.sin(this.colorangle));
		if (fillColour < 0)
			fillColour += 100;
		else if (fillColour > 100)
			fillColour -= 100;
		
		fill(fillColour, 100, this.brightness, this.alpha);

		noStroke();
		rect(0, 0, this.width, this.height);
		
		for (Placer p : this.placers)
		{
			// Informs the placer(s) of the screen's redraw
			p.onStep();
			// Draws all the placers and particles and stuff
			p.handleParticles();
		}
		
		this.colorangle += this.colorfrequency;
	}
	
	@Override
	public void mousePressed()
	{
		for (Placer p : this.placers)
		{
			// Informs the placer(s)
			p.onMousePressed();
		}
	}
	
	@Override
	public void mouseReleased()
	{
		for (Placer p : this.placers)
		{
			// Informs the placer(s)
			p.onMouseReleased();
		}
	}
	
	
	// GETTTERS & SETTERS	-----------------------------------------------
	
	/**
	 * @return How many possible phases there are
	 */
	public int getPhaseNumber()
	{
		return this.phases.size();
	}
	
	/**
	 * 
	 * This method changes how the screen is drawn. it changes the background
	 * color and some other aspects.
	 * 
	 * leave parameter to -1 if you don't want to change it
	 *
	 * @param mincolour the smallest possible hue [0, 100]
	 * @param colourRange how much the hue can change [0, 100]
	 * @param brightness how brigth / dark the background is [0 (dark),
	 * 100 (bright)]
	 * @param alpha how effectively the screen is redrawn
	 * [0 (every image stays), 100 (images last 1 frame)]
	 * @param colourFrequency how fast the backgroung color is changed
	 */
	public void setVisuals(int mincolour, int colourRange, int brightness,
			int alpha, double colourFrequency)
	{
		if (mincolour != -1)
			this.mincolour = mincolour;
		if (colourRange != -1)
			this.colourRange = colourRange;
		if (brightness != -1)
			this.brightness = brightness;
		if (alpha != -1)
			this.alpha = alpha;
		if (colourFrequency != -1)
			this.colorfrequency = colourFrequency;
	}
	
	
	// OTHER METHODS ------------------------------------------------------
	
	/**
	 * @return whether the mouse is on the window's area
	 */
	public boolean mouseIsInWindow()
	{
		return pointIsInWindow(this.mouseX, this.mouseY, 0);
	}
	
	/**
	 * 
	 * Tells whether a given point is within the window's area
	 *
	 * @param x point's x coordinate
	 * @param y point's y coordinate
	 * @param margin how much margin is added to the room (+ = more strict,
	 *   - = less strict)
	 * @return Is point within the window
	 */
	public boolean pointIsInWindow(int x, int y, int margin)
	{
		if (x < margin || x > this.width - margin ||
				y < margin || y > this.height - margin)
			return false;
		
		return true;
	}
	
	/**
	 * 
	 * Adds a new placer to the drawn placers
	 *
	 * @param p the placer to be added
	 * @return was the placer successfully added
	 */
	public boolean addPlacer(Placer p)
	{
		if (this.placers.contains(p))
			return false;
		
		this.placers.add(p);
		return true;
	}
	
	/**
	 * 
	 * Removes the given placer from the list of drawn placers
	 *
	 * @param p the placer to be removed
	 * @return was the placer removed successfully
	 */
	public boolean removePlacer(Placer p)
	{
		if (!this.placers.contains(p))
			return false;
		
		this.placers.remove(p);
		return true;
	}
	
	/**
	 * 
	 * Adds a phase to the possible phase candidates, this doesn't yet do
	 * anything to the new phase.
	 * 
	 * This method is called automatically when a phase is created so you
	 * should not use it anywhere else
	 *
	 *@param p The phase to be added
	 */
	public void addPhase(Phase p)
	{
		if (!this.phases.contains(p))
			this.phases.add(p);
	}
	
	/**
	 * 
	 * This method removes the phase from the possibly used phases
	 * Use this if you won't want to see a phase again
	 *
	 * @param p the phase to be removed
	 */
	public void removePhase(Phase p)
	{
		if (this.phases.contains(p))
			this.phases.remove(p);
	}
	
	/**
	 * 
	 * Pics a random phase from the phases -list
	 *
	 * @return the randomly picked phase, null if there are no possible phases
	 */
	public Phase randomPhase()
	{
		if (this.phases.size() == 0)
			return null;
		
		int phaseindex = rand.nextInt(this.phases.size());
		
		return this.phases.get(phaseindex);
	}
	
	
	// STATIC METHODS	---------------------------------------------------
	
	/**
	 * 
	 * Calculates a distance between two points.
	 *
	 * @param x1 First point's x coordinate
	 * @param y1 First point's y coordinate
	 * @param x2 Second point's x coordinate
	 * @param y2 Second point's y coordinate
	 * @return Distance between points in pixels
	 */
	public static int pointDistance(int x1, int y1, int x2, int y2)
	{
		double a = x1 - x2;
		double b = y1 - y2;
		
		return (int) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
	}
	
	/**
	 * 
	 * Calculates the direction from one point to another (in radians)
	 *
	 * @param x1 the first point's x coordinate
	 * @param y1 the first point's y coordinate
	 * @param x2 the second point's x coordinate
	 * @param y2 the second point's y coordinate
	 * @return the direction from point 1 to point 2 in radians
	 */
	public static double pointDirection(int x1, int y1, int x2, int y2)
	{
		double xdist = x2 - x1;
		double ydist = y2 - y1;
		return Math.atan2(ydist, xdist);
	}
}
