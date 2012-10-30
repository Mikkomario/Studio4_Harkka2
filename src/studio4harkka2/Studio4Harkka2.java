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
	private double colorangle;
	
	
	// BASIC METHODS ----------------------------------------------------
	
	@Override
	public void setup()
	{
		size(600, 600);
		
		// Changes the colormode to HSB (Hue, saturation, brightness)
		// max value being 100
		colorMode(HSB, 100);
		
		this.placers = new ArrayList<Placer>();
		this.colorangle = 0;
		
		// TODO: Add some placers here and reove the test placer
		addPlacer(new RectPlacer(this));
		//addPlacer(new TestPlacer(this));
		//addPlacer(new PetalPlacer(this));
		addPlacer(new HippiePlacer(this));
	}

	@Override
	public void draw()
	{
		// Draws the background with a small opacity so that old steps
		// fade away
		//background(255);
		fill((int) (7 + 7*Math.sin(this.colorangle)), 100, 70, 20);

		noStroke();
		rect(0, 0, this.width, this.height);
		
		for (Placer p : this.placers)
		{
			// Informs the placer(s) of the screen's redraw
			p.onStep();
			// Draws all the placers and particles and stuff
			p.handleParticles();
		}
		
		this.colorangle += 0.01;
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
