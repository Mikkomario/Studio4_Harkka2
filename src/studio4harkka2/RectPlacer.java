package studio4harkka2;

import java.util.Random;

import processing.core.PApplet;
 

public class RectPlacer extends Placer {
	private int rectNumber;
	private Random rand;

	public RectPlacer(PApplet parentApplet){
		super(parentApplet);
		this.rectNumber = 0;
		this.rand = new Random();
		for(int n=0; n < 10; n++){
			this.addParticle();
		}
	}

	@Override
	public Particle generateParticle() {
		if(this.rectNumber < 10){
			int x = (int)(this.rand.nextDouble()*this.getApplet().screenWidth);
			int y = (int)(this.rand.nextDouble()*this.getApplet().screenHeight);
			double maxVelocity = 8*this.rand.nextDouble();
			double xscale = 5*this.rand.nextDouble();
			double yscale = 5*this.rand.nextDouble();
			int strokeWeight = this.rand.nextInt(10);
			this.rectNumber++;
			
			return new Rectangle(x, y, maxVelocity, xscale, yscale, this, 
					this.getApplet(), strokeWeight);
		}
		
		return null;
	}

	@Override
	public void onStep() {
		// TODO Auto-generated method stub
		
	}
	
}
