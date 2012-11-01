package studio4harkka2;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * This placer creates LineParticles and kills them when they go outside
 * the room.
 *
 * @author Marde.
 *         Created 30.10.2012.
 */
public class LinePlacer extends Placer
{
	// ATTRIBUTES --------------------------------------------------------

	private int tillCreation;
	private int klick;
	private static Random rand = new Random();
	private PImage rs;
	// kuva näkyy true tai false!!


	// CONSTRUCTOR -------------------------------------------------------

	/**
	 * Creates a new LinePlacer, which starts to create new particles
	 *
	 * @param parentApplet the window / applet to which the particles are drawn
	 */
	public LinePlacer(PApplet parentApplet)
	{
		super(parentApplet);

		this.tillCreation = 10;
		this.klick = 0;
		this.rs = getApplet().loadImage("roll.png");
	}


	// INHERITED METHODS -------------------------------------------------

	@Override
	public Particle generateParticle()
	{
		// Creates a new particle
		Particle p =  new LineParticle(this, getApplet());

		// Rotates it a bit and changes it's angle
		p.setAngle(0);

		// Gives the particle a bit speed
		p.setVelocity(4, 0);

		// Returns the new particle
		return p;
	}

	@Override
	public void onStep()
	{

		// Here the placer creates new particles and removes old ones
		handleCreation();
		removeOutsiders();
		//addClicks();
		//clickCheck();
		
		if(this.klick == 1){
			getApplet().tint(55, 37);
			getApplet().image(rs, 170, 100);
		}

		// roll eli suu kiinni
		else if(this.klick == 2){
			getApplet().tint(35, 37);
			getApplet().image(rs, 150, 38);	
		}
		
		getApplet().tint(100, 100);
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
			this.tillCreation = 10;

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

			// marginaali
			int maxDist = 0;
			//	int maxDist = (int) (Math.max(p.getXScale(), p.getYScale())*50);
			//System.out.println(maxDist);
			//System.out.println("Coordinates for " + i + " : " + p.getX() +
			//		", " + p.getY());

			// "onko tämä piste x,y ikkunassa marginaalilla n?"
			if (!applet.pointIsInWindow(p.getX(), p.getY(), -maxDist))
			{
				//System.out.println("Kills a particle at pos: " + p.getX() + ", " + p.getY());
				removeParticle(p);
			}
		}
	}

	public int getTillCreation(){
		return this.tillCreation;
	}

	@Override
	public void onMousePressed(){
		super.onMousePressed();
		addClicks();
	}

	
	public void addClicks(){
		
		this.klick ++;
		
		if (this.klick > 2){
			this.klick = 1;
		}
		
		if(this.klick == 1){
			this.rs = getApplet().loadImage("rs1.png");
		}

		else if(this.klick == 2){
			this.rs = getApplet().loadImage("roll.png");
		}
		
		if(this.klick == 0){			
		}		
	}
}
