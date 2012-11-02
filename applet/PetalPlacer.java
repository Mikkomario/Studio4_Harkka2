package studio4harkka2;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;

/**
 * This class creates a group of petals ( = a flower) when the screen
 * is clicked. It also creates more and more flowers after some have
 * been created.
 *
 * @author Gandalf.
 *         Created 29.10.2012.
 */
public class PetalPlacer extends Placer
{
	// ATTRIBUTES	------------------------------------------------------
	
	private static Random rand = new Random();
	
	private ArrayList<Flower> flowers;
	private int tillBloom;
	private boolean white;
	
	
	// CONSTRUCTOR	------------------------------------------------------

	/**
	 * Creates a new PetalPlacer, but doesn't do much else
	 *
	 * @param parentApplet the applet to which the particles are drawn
	 * @param white Are all the flowers in black and white
	 */
	public PetalPlacer(PApplet parentApplet, boolean white)
	{
		super(parentApplet);
		
		this.flowers = new ArrayList<Flower>();
		this.tillBloom = 150;
		this.white = white;
	}
	
	
	// IMPLEMENTED METHODS	----------------------------------------------

	@Override
	public Particle generateParticle()
	{
		// Doesn't actually create anything (implemented in the flower class)
		return null;
	}

	@Override
	public void onStep()
	{
		// Notifies each flower of the event
		for (int i = 0; i < this.flowers.size(); i++)
		{
			this.flowers.get(i).onStep();
		}
		
		// Calculates whether a new bloom should occur
		// (bloom is when all flowers multiply)
		this.tillBloom --;
		if (this.tillBloom <= 0)
		{
			bloom();
		}
	}
	
	@Override
	public void onMousePressed()
	{
		// Does all things the normal placer does and also creates a new flower
		// to the place where the user clicked (if there is no flower already)
		super.onMousePressed();
		
		createFlower(getApplet().mouseX, getApplet().mouseY, 0);
	}
	
	
	// OTHER METHODS	---------------------------------------------------
	
	/**
	 * 
	 * Generates a flower and adds it to the flowers list
	 *
	 *@param newx the flower's x coordinate
	 *@param newy the flower's y coordinate
	 *@param callNumber how many "parents" would the flower have (determines
	 *the probability of actual creation) 
	 *
	 *@return the flower which was generated
	 */
	public Flower createFlower(int newx, int newy, int callNumber)
	{	
		// Checks whether a new flower should be created at all
		if (callNumber != 0 && rand.nextDouble() < callNumber * 0.1)
			return null;
		
		// Determines the flower's colour
		//int colour = rand.nextInt(101);
		// Updated: Now only gets red to yellow colours
		int colour = rand.nextInt(14);
		// And the scales
		double xscale = 0.1 + rand.nextDouble()*0.4;
		double yscale = 0.4 + rand.nextDouble()*1.7;
		// And the number of petals
		int petalnumber = 2 + rand.nextInt(19);
		
		int saturation = 100;
		
		if (this.white)
			saturation = 0;
		
		Flower newflower = new Flower(newx, newy, colour, xscale, yscale, 
				petalnumber, this, getApplet(), callNumber, saturation);
		
		// Checks whether the flower would fit into the screen
		if (getApplet() instanceof Studio4Harkka2 &&
				!((Studio4Harkka2) getApplet()).pointIsInWindow(newx, newy,
				- newflower.getRadius()))
			return null;
		
		// Checks whether the position is free and the new flower should
		// be added to the living flowers
		for (int i = 0; i < this.flowers.size(); i++)
		{
			// If any flower would collide with the new flower, the flower
			// we try to create another flower (callNumber goes up)
			if (this.flowers.get(i).Collides(newflower))
				return createFlower(newx, newy, callNumber + 1);
		}
		
		this.flowers.add(newflower);
		return newflower;
	}
	
	/**
	 * 
	 * Removes the flower from the living flowers...
	 * 
	 * @param f the flower to be "exterminated"
	 */
	public void removeFlower(Flower f)
	{
		if (this.flowers.contains(f))
			this.flowers.remove(f);
	}
	
	private void bloom()
	{
		// Records all the new flowers and makes sure those flowers won't
		// create new flowers just yet
		ArrayList<Flower> addedFlowers = new ArrayList<Flower>();
		
		this.tillBloom = 30;
		
		// Makes all flowers create more flowers
		for (int i = 0; i < this.flowers.size(); i++)
		{
			Flower f = this.flowers.get(i);
			
			// If the flower was just created, doesn't create new ones for that
			if (addedFlowers.contains(f))
				continue;
			
			// If the flower's duration is too low, it can't create new ones
			if (f.getDuration() < f.getMaxDuration() / 2)
				continue;
			
			int tries = 0;
			
			while (tries < 3)
			{
				double angle = rand.nextDouble()*2*Math.PI;
				double dist = f.getRadius()*(0.7 + rand.nextDouble()*2);
				
				tries ++;
				// Positioning gets a litle complicated but the new flower
				// would be positioned to a random range and random 
				// drection from the creator flower
				Flower newf = createFlower((int) (f.getX() + dist*Math.cos(angle)), 
						(int) (f.getY() + dist*Math.sin(angle)),
						f.getCallNumber() + 1);
				
				// Adds the just created flower to the list of new flowers
				// (who won't create new flowers this round)
				if (newf != null)
					addedFlowers.add(newf);
			}
		}
	}

}
