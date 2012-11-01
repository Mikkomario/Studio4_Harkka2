package studio4harkka2;

import java.util.Random;

import processing.core.PApplet;
 

public class RectPlacer extends Placer {
	private Random rand;

	public RectPlacer(PApplet parentApplet){
		super(parentApplet);
		this.rand = new Random();
		for(int n=0; n < 20; n++){
			this.addParticle();
		}
	}

	@Override
	public Particle generateParticle() {
		if(this.rand.nextDouble() > 0.3){
			int x = (int)(this.rand.nextDouble()*this.getApplet().width);
			int y = (int)(this.rand.nextDouble()*this.getApplet().height);
			double maxVelocity = 8*this.rand.nextDouble();
			double xscale = 5*this.rand.nextDouble();
			double yscale = 5*this.rand.nextDouble();
			int strokeWeight = this.rand.nextInt(10);
			
			return new Rectangle(x, y, maxVelocity, xscale, yscale, this, 
					this.getApplet(), strokeWeight);
		}
		
		return null;
	}

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
