package studio4harkka2;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

public class HippiePlacer extends Placer {
	private PImage images[];
	private static Random rand = new Random();
	
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

	@Override
	public Particle generateParticle() {
		int x = (int)(rand.nextDouble()*this.getApplet().width);
		int y = (int)(rand.nextDouble()*this.getApplet().height);
		double maxVelocity = 8*rand.nextDouble();
		double friction = 3*rand.nextDouble();
		double maxRotation= 8*rand.nextDouble();
		double rotationFriction = 3*rand.nextDouble();
		double scale = 0.1+rand.nextDouble();
		int arpa = rand.nextInt(6);
		PImage image = this.images[arpa];
		
		System.out.println(x);
		
		return new HippieFlower(x, y, maxVelocity, friction, maxRotation,
				rotationFriction, scale, scale, this, 
				this.getApplet(), image);
	}

	@Override
	public void onStep() {
		//System.out.println("hjhjj");
		
	}
	
}
