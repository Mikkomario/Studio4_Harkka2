package studio4harkka2;

import java.util.Random;

import processing.core.PApplet;

/**
 * This class is a simple example of how to create your own placer classes.
 * This placer creates testParticles and kills them when they go outside
 * the room.
 *
 * @author Gandalf.
 *         Created 23.10.2012.
 */
public class TestPlacer extends Placer
{
	// ATTRIBUTES --------------------------------------------------------
	
	private int tillCreation;
	private static Random rand = new Random();
	
	
	// CONSTRUCTOR -------------------------------------------------------

	/**
	 * Creates a new testPlacer, which starts to create new particles
	 *
	 * @param parentApplet the window / applet to which the particles are drawn
	 */
	public TestPlacer(PApplet parentApplet)
	{
		super(parentApplet);
		
		this.tillCreation = 50;
	}
	
	
	// INHERITED METHODS -------------------------------------------------

	@Override
	public Particle generateParticle()
	{
		// Creates a new particle
		double rotfri = rand.nextDouble()*0.2;
		double xsca = 0.1 + rand.nextDouble()*4;
		double ysca = 0.1 + rand.nextDouble()*4;
		
		//System.out.println(xsca + ", " + ysca);
		
		Particle p =  new TestParticle(rotfri, xsca, ysca, this, getApplet());
		
		// Rotates it a bit and changes it's angle
		p.setAngle(rand.nextInt(360));
		p.setRotation(rand.nextDouble()*30);
		
		// Gives the particle a bit speed
		p.setVelocity(-5 + rand.nextDouble()*10, -5 + rand.nextDouble()*10);
		
		// Returns the new particle
		return p;
	}

	@Override
	public void onStep()
	{
		//System.out.println("STEP");
		
		// Here the placer creates new particles and removes old ones
		handleCreation();
		removeOutsiders();
		
		// Increases alpha for all the (test)particles
		for (int i = 0; i < getSize(); i++)
		{
			if (getParticle(i) instanceof TestParticle)
				((TestParticle) getParticle(i)).increaseAlpha();
		}
	}
	
	
	// OTHER METHODS -----------------------------------------------------
	
	// Creates particles with random delays
	private void handleCreation()
	{	
		this.tillCreation--;
		
		if (this.tillCreation == 0)
		{
			//System.out.println("Creates a particle");
			
			addParticle();
			this.tillCreation = 1 + rand.nextInt(75);
		}
	}
	
	// Removes all particles that are outside the window area
	private void removeOutsiders()
	{
		// Doesn't work if the applet is not of default form
		if (!(getApplet() instanceof Studio4Harkka2))
			return;
		
		Studio4Harkka2 applet = (Studio4Harkka2) getApplet();
		
		for (int i = 0; i < getSize(); i++)
		{
			Particle p = getParticle(i);
			
			int maxDist = (int) (Math.max(p.getXScale(), p.getYScale())*50);
			//System.out.println(maxDist);
			//System.out.println("Coordinates for " + i + " : " + p.getX() +
			//		", " + p.getY());
			
			if (!applet.pointIsInWindow(p.getX(), p.getY(), -maxDist))
			{
				//System.out.println("Kills a particle at pos: " + p.getX() + ", " + p.getY());
				removeParticle(p);
			}
		}
	}
}
