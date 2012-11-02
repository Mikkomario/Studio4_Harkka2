package studio4harkka2;

import java.util.Random;

import processing.core.PApplet;

/**
  * RectPlacer extends Placer. Places Rectangles in the screen in the beginning
  * and calls their moving-method if their moving status is true.
  * 
  * @author Tiitu
  *
  */
public class RectPlacer extends Placer {
	private Random rand;

	/**
	 * Constructor
	 * 
	 * @param parentApplet defines the parent applet of the placer
	 */
	public RectPlacer(PApplet parentApplet){
		super(parentApplet);
		this.rand = new Random();
		for(int n=0; n < 20; n++){
			this.addParticle();
		}
	}

	/**
	 * Method, that creates a new random rectangle with a probability of 70 %
	 * @return a new rectangle or null
	 */
	@Override
	public Particle generateParticle() {
		if(this.rand.nextDouble() > 0.3){
			int x = (int)(this.rand.nextDouble()*this.getApplet().width);
			int y = (int)(this.rand.nextDouble()*this.getApplet().height);
			double maxVelocity = 8*this.rand.nextDouble();
			double xscale = 5*this.rand.nextDouble();
			double yscale = 5*this.rand.nextDouble();
			int strokeWeight = 2+this.rand.nextInt(8);
			
			return new Rectangle(x, y, maxVelocity, xscale, yscale, this, 
					this.getApplet(), strokeWeight);
		}
		
		return null;
	}

	/**
	 * Goes through all the rectangles of this placer and calls their moving
	 * method if they are moving 
	 */
	@Override
	public void onStep() {
		
		for(int n=0; n < this.getSize(); n++){
			Rectangle rect = (Rectangle)this.getParticle(n);
			if(rect.isMoving()){
				rect.moveParticle();
			}
		}
	}
	
	/*
	 * THIS IS JUST FOR TESTING - by Gandalf
	// Overrides mousepressed so that it also replaces some rectangles
	@Override
	public void onMousePressed()
	{
		super.onMousePressed();
		for (int i = 0; i < getSize(); i++)
		{
			Particle p = getParticle(i);
			//p.setPosition(200, 100);
			System.out.println(p.getX() + ", " + p.getY() +
					": " + p.getXScale() + ", " + p.getYScale());
		}
	}
	*/
	
}
