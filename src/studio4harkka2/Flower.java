package studio4harkka2;

import java.util.Random;

import processing.core.PApplet;

/**
 * Flower represents a group of PetalParticles. Flower handles the creation
 * of particles and collision detection with other flowers
 *
 * @author Gandalf.
 *         Created 29.10.2012.
 */
public class Flower
{
	// ATTRIBUTES	------------------------------------------------------
	
	private static Random rand = new Random();
	
	private int x;
	private int y;
	private int petals;
	private int colour;
	private double xscale;
	private double yscale;
	private int duration;
	private int maxDuration;
	private int tillCreation;
	private int maxPetals;
	private PetalPlacer placer;
	private PApplet applet;
	private int callNumber;
	
	
	// CONSTRUCTOR	------------------------------------------------------
	
	/**
	 * 
	 * This method creates a flower that starts to create new petals
	 * Each flower has different attributes
	 *
	 * @param x the x coordinate of the flower's position
	 * @param y the y coordinate of the flower's position
	 * @param colour The basic hue of the flower
	 * @param xscale how much the petals are scaled horizontally (width)
	 * @param yscale how much the petals are scaled vertically (height)
	 * @param maxPetals the number of petals on the complete flower
	 * @param parentPlacer the placer who created this flower
	 * @param parentApplet the applet to which the petals are drawn
	 * @param callNumber How many "parents" this flower has (flower that
	 * creates a flower is its parent and all its children's parent)
	 */
	public Flower(int x, int y, int colour, double xscale, double yscale,
			int maxPetals, PetalPlacer parentPlacer, PApplet parentApplet, 
			int callNumber)
	{
		// Initializes the variables
		this.x = x;
		this.y = y;
		this.colour = colour;
		this.xscale = xscale;
		this.yscale = yscale;
		this.maxPetals = maxPetals;
		this.placer = parentPlacer;
		this.applet = parentApplet;
		this.callNumber = callNumber;
		this.tillCreation = 1;
		this.duration = 250 - (callNumber * 20);
		this.maxDuration = this.duration;
		this.petals = 0;
	}
	
	
	// GETTERS & SETTERS	-----------------------------------------------
	
	/**
	 * @return the flower's position's x coordinate
	 */
	public int getX()
	{
		return this.x;
	}
	
	/**
	 * @return the flower's position's y coordinate
	 */
	public int getY()
	{
		return this.y;
	}
	
	/**
	 * 
	 * Changes the flower's position to the new coordinates
	 * NOTICE: Do NOT call this method after the flower is drawn even once!
	 * (It's still safe to call right after the flower has been created)
	 *
	 * @param x the new x-coordinate
	 * @param y the new y-coordinate
	 */
	public void setPosition(int x, int y)
	{
		// Can't do anyting if some petals have already been created
		if (this.petals > 0)
			return;
		
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return the radius of the flower's petals
	 */
	public int getRadius()
	{
		return (int) (50 * this.yscale);
	}
	
	/**
	 * @return How many "parent's" the flower has
	 */
	public int getCallNumber()
	{
		return this.callNumber;
	}
	
	/**
	 * @return how long the flower will live
	 */
	public int getDuration()
	{
		return this.duration;
	}
	
	/**
	 * @return the maximum lifetime the petals can have in this flower
	 */
	public int getMaxDuration()
	{
		return this.maxDuration;
	}
	
	
	// OTHER METHODS	---------------------------------------------------
	
	/**
	 * 
	 * This method should be called each time the screen is drawn.
	 * It handles the creation of the petals. And the inevitable death of the
	 * flower when the time comes.
	 *
	 */
	public void onStep()
	{
		this.tillCreation --;
		this.duration --;
		
		if (this.tillCreation <= 0 && this.petals < this.maxPetals)
		{
			this.tillCreation = 10;
			
			int newcolour = this.colour + rand.nextInt(21) - 10;
			if (newcolour < 0)
				newcolour += 100;
			else if (newcolour > 100)
				newcolour -= 100;
			
			PetalParticle p = new PetalParticle(getX(), getY(),
					this.xscale*(0.85 + rand.nextDouble()*0.3),
					this.yscale*(0.85 + rand.nextDouble()*0.3),
					this.maxDuration, this.placer, this.applet, newcolour);
			p.setRotation(360 / (10.0 * (this.maxPetals)));
			this.placer.addParticle(p);
			
			this.duration = this.maxDuration;
			this.petals ++;
		}
		
		// If the duration becomes non-positive, the flower dies
		if (this.duration <= 0)
		{
			this.placer.removeFlower(this);
		}
	}
	
	/**
	 * 
	 * TODO Put here a description of what this method does.
	 *
	 * @param f the flower with which the collision is checked
	 * @return do the flowers collide with each other
	 */
	public boolean Collides(Flower f)
	{	
		return (Studio4Harkka2.pointDistance(getX(), getY(), f.getX(), f.getY())
				< getRadius() + f.getRadius());
	}
}
