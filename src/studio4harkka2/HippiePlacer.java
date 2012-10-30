package studio4harkka2;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class HippiePlacer extends Placer {
	private PImage images[];
	private static Random rand = new Random();
	
	public HippiePlacer(PApplet parentApplet){
		super(parentApplet);
		this.images = new PImage[1];
		this.images[0] = this.getApplet().loadImage("flower_power.png");
		for(int n=0; n < 10; n++){
			this.generateParticle();
		}
	}

	@Override
	public Particle generateParticle() {
		int x = (int)(rand.nextDouble()*this.getApplet().width);
		int y = (int)(rand.nextDouble()*this.getApplet().height);
		double maxVelocity = 8*rand.nextDouble();
		double friction = 3*rand.nextDouble();
		double maxRotation= 8*rand.nextDouble();
		double rotationFriction = 3*rand.nextDouble();
		double xscale = 5*rand.nextDouble();
		double yscale = 5*rand.nextDouble();
		int arpa = rand.nextInt(5);
		PImage image = this.images[0];
		
		return new HippieFlower(x, y, maxVelocity, friction, maxRotation,
				rotationFriction, xscale, yscale, this, 
				this.getApplet(), image);
	}

	@Override
	public void onStep() {
		// TODO Auto-generated method stub
		
	}
	
}
