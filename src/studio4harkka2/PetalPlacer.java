package studio4harkka2;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;

/**
 * This class creates a group of petals when the screen is clicked. It also
 * creates more and more petals after some have been created.
 *
 * @author Gandalf.
 *         Created 29.10.2012.
 */
public class PetalPlacer extends Placer
{
	// ATTRIBUTES	------------------------------------------------------
	
	private static Random rand;
	
	
	// CONSTRUCTOR	------------------------------------------------------

	/**
	 * Creates a new PetalPlacer, but doesn't do much else
	 *
	 * @param parentApplet the applet to which the particles are drawn
	 */
	public PetalPlacer(PApplet parentApplet)
	{
		super(parentApplet);
	}
	
	
	// IMPLEMENTED METHODS	----------------------------------------------

	@Override
	public Particle generateParticle()
	{
		// Doesn't actually create anything (implemented elsewhere)
		return null;
	}

	@Override
	public void onStep()
	{
		// TODO Auto-generated method stub.
		
	}
	
	
	// OTHER METHODS	---------------------------------------------------
	
	/**
	 * 
	 * Creates many petals that form a single flower and adds them to the
	 * drawing list
	 *
	 *@param newx the flower's x coordinate
	 *@param newy the flower's y coordinate
	 *
	 */
	public void createFlower(int newx, int newy)
	{	
		// Determines the basic colour
		int bcolour = rand.nextInt(101);
		// And the basic scales
		double bxscale = rand.nextDouble()*2;
		double byscale = rand.nextDouble()*4;
		
		// Creates a single petal for each 45 degrees
		for (int angle = 0; angle < 360; angle += 45)
		{
			// Creates a particle with a bit varying values
			double xscale = bxscale*(0.75 + rand.nextDouble()*0.5);
			double yscale = byscale*(0.75 + rand.nextDouble()*0.5);
			
			int colour = bcolour + rand.nextInt(30) - 15;
			if (colour > 100)
				colour -= 100;
			else if (colour < 0)
				colour += 100;
			
			Particle p = new PetalParticle(newx, newy, xscale, yscale,
					100 + angle, this, getApplet(), colour);
			p.setAngle(angle);
			
			// Adds the particle to the drawing list
			addParticle(p);
		}
	}

}
