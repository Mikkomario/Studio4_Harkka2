package studio4harkka2;

import java.util.ArrayList;

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
	
	private ArrayList<Placer> placers;
	
	
	// BASIC METHODS ----------------------------------------------------
	
	@Override
	public void setup()
	{
		size(600, 600);
		
		this.placers = new ArrayList<Placer>();
		
		// TODO: Add some placers here and reove the test placer
		addPlacer(new TestPlacer(this));
	}

	@Override
	public void draw()
	{
		// Draws the background
		// TODO: Change the opacity of the background
		// Would be nice IF IT WORKED!!!
		background(255);
		
		for (Placer p : this.placers)
		{
			// Informs the placer(s) of the screen's redraw
			p.onStep();
			// Draws all the placers and particles and stuff
			p.handleParticles();
		}
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
}
