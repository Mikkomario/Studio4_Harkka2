package studio4harkka2;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * HippiePlacer extends Placer. Places HippieFlowers in the screen and
 * creates a new HippieFlower when mouse is pressed.
 * 
 * @author Tiitu
 *
 */
public class HippiePlacer extends Placer {
	private PImage images[];
	private static Random rand = new Random();
	
	/**
	 * Constructor
	 * 
	 * Possible images for the hippie-flowers are loaded in the constructor
	 * @param parentApplet defines the parent applet of this placer
	 */
	public HippiePlacer(PApplet parentApplet){
		super(parentApplet);
		this.images = new PImage[6];
		this.images[0] = this.getApplet().loadImage("flower_power.png");
		this.images[1] = this.getApplet().loadImage("flower_power1.png");
		this.images[2] = this.getApplet().loadImage("flower_power2.png");
		this.images[3] = this.getApplet().loadImage("flower_power3.png");
		this.images[4] = this.getApplet().loadImage("peace.png");
		this.images[5] = this.getApplet().loadImage("randomflower.png");
		for(int n=0; n < 10; n++){
			this.addParticle();
		}
	}

	/**
	 * Generates a new random hippie-flower
	 * @return a hippie-flower
	 */
	@Override
	public Particle generateParticle() {
		int x = (int)(rand.nextDouble()*this.getApplet().width);
		int y = (int)(rand.nextDouble()*this.getApplet().height);
		double maxVelocity = 5+8*rand.nextDouble();
		double friction = 3*rand.nextDouble();
		double maxRotation= 5+5*rand.nextDouble();
		double scale = 0.1+rand.nextDouble();
		int arpa = rand.nextInt(6);
		PImage image = this.images[arpa];
				
		return new HippieFlower(x, y, maxVelocity, friction, maxRotation,
				scale, this, this.getApplet(), image);
	}

	@Override
	public void onStep()
	{
		// Makes the flowers roll wildly (by Gandalf)
		for (int i = 0; i < getSize(); i++)
		{
			// Works only with hippieflowers
			if (!(getParticle(i) instanceof HippieFlower))
				continue;
			
			HippieFlower h = (HippieFlower) getParticle(i);
			
			// Calculates the relative position
			int rx = (int) (Math.cos(Math.toRadians(h.getAngle())) *
					h.getXScale() * 100);
			int ry = (int) (Math.sin(Math.toRadians(h.getAngle())) *
					h.getXScale() * 100);
			
			// Sets the new position
			h.setPosition(h.getStartPosition()[0] + rx,
					h.getStartPosition()[1] + ry);
		}
	}
	
	/**
	 * Adds a new hippie-flower when the mouse is pressed.
	 */
	@Override
	public void onMousePressed() {
		this.addParticle();
		
	}
	
}
