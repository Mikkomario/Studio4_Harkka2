package studio4harkka2;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * This placer creates LineParticles and kills them when they go outside
 * the room.
 * 
 * The placer draws the pictures, listens to mouse actions and creates an optical illusion.
 *
 * @author Marde.
 *         Created 30.10.2012.
 */
public class LinePlacer extends Placer
{
	// ATTRIBUTES --------------------------------------------------------

	private int tillCreation;
	// counting the mouse klicks
	private int klick;
	// taustalla oleva kuva
	private PImage rs;


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
		
		// here the klick number is checked and the image factors are being checked
		if(this.klick == 1){
			getApplet().tint(55, 37);
			getApplet().image(this.rs, 170, 100);
		}
		else if(this.klick == 2){
			getApplet().tint(35, 37);
			getApplet().image(this.rs, 150, 38);	
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

			int maxDist = 0;

			if (!applet.pointIsInWindow(p.getX(), p.getY(), -maxDist))
			{
				removeParticle(p);
			}
		}
	}

	/**
	 * @return How many steps will occur before a new particle is created 
	 */
	public int getTillCreation(){
		return this.tillCreation;
	}

	@Override
	public void onMousePressed(){
		super.onMousePressed();
		addClicks();
	}

	/**
	 * Adds clicks to the counter and loads the images.
	 * Defines which images to use and when.
	 * 
	 */
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
	}
}
